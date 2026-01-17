<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Product</title>
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
    <h1>Edit Product</h1>

    <?php
    $error = "";
    $success = "";
    $product = null;

    // Database connection
    $servername = "localhost";
    $username = "root";
    $password = "";
    $dbname = "product_management";

    $conn = new mysqli($servername, $username, $password, $dbname);

    if ($conn->connect_error) {
        die("Connection failed: " . $conn->connect_error);
    }

    // Get product ID from URL
    $id = isset($_GET["id"]) ? intval($_GET["id"]) : 0;

    // Check if ID exists
    if ($id <= 0) {
        $error = "Invalid product ID.";
    } else {
        // Fetch product details
        $fetchSql = "SELECT id, product_name, price, stock FROM products WHERE id = ?";
        $fetchStmt = $conn->prepare($fetchSql);
        $fetchStmt->bind_param("i", $id);
        $fetchStmt->execute();
        $result = $fetchStmt->get_result();

        if ($result->num_rows == 0) {
            $error = "Product not found.";
        } else {
            $product = $result->fetch_assoc();
        }
        $fetchStmt->close();
    }

    // Process form submission
    if ($_SERVER["REQUEST_METHOD"] == "POST" && $product) {
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

        // Check if product_name is unique (exclude current product)
        if ($isValid) {
            $checkSql = "SELECT id FROM products WHERE product_name = ? AND id != ?";
            $checkStmt = $conn->prepare($checkSql);
            $checkStmt->bind_param("si", $product_name, $id);
            $checkStmt->execute();
            $checkResult = $checkStmt->get_result();

            if ($checkResult->num_rows > 0) {
                $error = "Product name already exists. Please choose a different name.";
                $isValid = false;
            }
            $checkStmt->close();
        }

        // Update product if valid
        if ($isValid) {
            $updateSql = "UPDATE products SET product_name = ?, price = ?, stock = ? WHERE id = ?";
            $updateStmt = $conn->prepare($updateSql);
            $updateStmt->bind_param("siii", $product_name, $price, $stock, $id);

            if ($updateStmt->execute()) {
                $success = "Product updated successfully!";
                // Update product array with new values
                $product["product_name"] = $product_name;
                $product["price"] = $price;
                $product["stock"] = $stock;
            } else {
                $error = "Error updating product: " . $conn->error;
            }
            $updateStmt->close();
        } else {
            // Keep the submitted values in the form
            $product["product_name"] = $product_name;
            $product["price"] = $price;
            $product["stock"] = $stock;
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

    <?php if ($product): ?>
        <form method="POST" action="edit_product.php?id=<?php echo $id; ?>">
            <div class="form-group">
                <label>Product Name:</label>
                <input type="text" name="product_name" value="<?php echo htmlspecialchars($product["product_name"]); ?>">
            </div>

            <div class="form-group">
                <label>Product Price:</label>
                <input type="number" name="price" min="1" value="<?php echo htmlspecialchars($product["price"]); ?>">
            </div>

            <div class="form-group">
                <label>Product Quantity:</label>
                <input type="number" name="stock" min="1" value="<?php echo htmlspecialchars($product["stock"]); ?>">
            </div>

            <input type="submit" value="Update Product">
        </form>
    <?php endif; ?>

    <br>
    <a href="index.php">Back to Product List</a>

    <?php $conn->close(); ?>
</body>

</html>