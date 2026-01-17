<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Product</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }

        h1 {
            margin-bottom: 20px;
        }

        .form-group {
            margin-bottom: 15px;
        }

        label {
            display: block;
            margin-bottom: 5px;
            text-decoration: underline;
        }

        input[type="text"],
        input[type="number"] {
            width: 300px;
            padding: 8px;
            border: 1px solid #ccc;
            box-sizing: border-box;
        }

        input[type="submit"] {
            padding: 8px 16px;
            cursor: pointer;
        }

        .error {
            color: red;
            margin-top: 5px;
        }

        .success {
            color: green;
            margin-bottom: 15px;
        }

        a {
            color: blue;
            text-decoration: underline;
        }
    </style>
</head>

<body>
    <h1>Add Product</h1>

    <?php
    $error = "";
    $success = "";

    // Database connection
    $servername = "localhost";
    $username = "root";
    $password = "";
    $dbname = "product_management";

    $conn = new mysqli($servername, $username, $password, $dbname);

    if ($conn->connect_error) {
        die("Connection failed: " . $conn->connect_error);
    }

    // Process form submission
    if ($_SERVER["REQUEST_METHOD"] == "POST") {
        $product_name = trim($_POST["product_name"]);
        $price = $_POST["price"];
        $stock = $_POST["stock"];

        // Validation
        $isValid = true;

        // Check if fields are empty
        if (empty($product_name) || empty($price) || empty($stock)) {
            $error = "All fields are required.";
            $isValid = false;
        }

        // Validate price > 0
        if ($isValid && $price <= 0) {
            $error = "Price must be greater than 0.";
            $isValid = false;
        }

        // Validate stock >= 1
        if ($isValid && $stock < 1) {
            $error = "Stock must be at least 1.";
            $isValid = false;
        }

        // Check if product_name is unique
        if ($isValid) {
            $checkSql = "SELECT id FROM products WHERE product_name = ?";
            $checkStmt = $conn->prepare($checkSql);
            $checkStmt->bind_param("s", $product_name);
            $checkStmt->execute();
            $checkResult = $checkStmt->get_result();

            if ($checkResult->num_rows > 0) {
                $error = "Product name already exists. Please choose a different name.";
                $isValid = false;
            }
            $checkStmt->close();
        }

        // Insert product if valid
        if ($isValid) {
            $insertSql = "INSERT INTO products (product_name, price, stock) VALUES (?, ?, ?)";
            $insertStmt = $conn->prepare($insertSql);
            $insertStmt->bind_param("sii", $product_name, $price, $stock);

            if ($insertStmt->execute()) {
                $success = "Product added successfully!";
                // Clear form values after successful insert
                $product_name = "";
                $price = "";
                $stock = "";
            } else {
                $error = "Error adding product: " . $conn->error;
            }
            $insertStmt->close();
        }
    }
    ?>

    <?php if (!empty($success)): ?>
        <p class="success">
            <?php echo $success; ?>
        </p>
    <?php endif; ?>

    <?php if (!empty($error)): ?>
        <p class="error">
            <?php echo $error; ?>
        </p>
    <?php endif; ?>

    <form method="POST" action="">
        <div class="form-group">
            <label>Product Name:</label>
            <input type="text" name="product_name"
                value="<?php echo isset($product_name) ? htmlspecialchars($product_name) : ''; ?>">
        </div>

        <div class="form-group">
            <label>Product Price:</label>
            <input type="number" name="price" min="1"
                value="<?php echo isset($price) ? htmlspecialchars($price) : ''; ?>">
        </div>

        <div class="form-group">
            <label>Product Quantity:</label>
            <input type="number" name="stock" min="1"
                value="<?php echo isset($stock) ? htmlspecialchars($stock) : ''; ?>">
        </div>

        <input type="submit" value="Add Product">
    </form>

    <br>
    <a href="index.php">Back to Product List</a>

    <?php $conn->close(); ?>
</body>

</html>