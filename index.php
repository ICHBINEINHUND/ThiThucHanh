<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Management</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }

        a {
            color: blue;
            text-decoration: underline;
        }

        a:hover {
            color: darkblue;
        }

        table {
            border-collapse: collapse;
            margin-top: 10px;
        }

        th,
        td {
            border: 1px solid black;
            padding: 8px 12px;
            text-align: left;
        }

        th {
            background-color: #f0f0f0;
        }

        .action-links a {
            margin-right: 5px;
        }

        .action-links a.delete-link {
            color: red;
        }
    </style>
    <script>
        function confirmDelete(id) {
            if (confirm("Are you sure you want to delete this product?")) {
                window.location.href = "delete_product.php?id=" + id;
            }
            return false;
        }
    </script>
</head>

<body>
    <a href="add_product.php">Add Product</a>

    <table>
        <thead>
            <tr>
                <th>Product Name</th>
                <th>Price</th>
                <th>Stock</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <?php
            // Database connection
            $servername = "localhost";
            $username = "root";
            $password = "";
            $dbname = "product_management";

            $conn = new mysqli($servername, $username, $password, $dbname);

            // Check connection
            if ($conn->connect_error) {
                die("Connection failed: " . $conn->connect_error);
            }

            // Query to get all products sorted by price in descending order
            $sql = "SELECT id, product_name, price, stock FROM products ORDER BY price DESC";
            $result = $conn->query($sql);

            if ($result->num_rows > 0) {
                while ($row = $result->fetch_assoc()) {
                    echo "<tr>";
                    echo "<td>" . htmlspecialchars($row["product_name"]) . "</td>";
                    echo "<td>" . $row["price"] . "</td>";
                    echo "<td>" . $row["stock"] . "</td>";
                    echo "<td class='action-links'>";
                    echo "<a href='edit_product.php?id=" . $row["id"] . "'>Edit</a> ";
                    echo "<a href='#' class='delete-link' onclick='return confirmDelete(" . $row["id"] . ")'>Delete</a>";
                    echo "</td>";
                    echo "</tr>";
                }
            } else {
                echo "<tr><td colspan='4'>No products found</td></tr>";
            }

            $conn->close();
            ?>
        </tbody>
    </table>
</body>

</html>