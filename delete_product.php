<?php
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

if ($id <= 0) {
    echo "<script>alert('Invalid product ID.'); window.location.href='index.php';</script>";
    exit;
}

// Check if product exists
$checkProductSql = "SELECT id FROM products WHERE id = ?";
$checkProductStmt = $conn->prepare($checkProductSql);
$checkProductStmt->bind_param("i", $id);
$checkProductStmt->execute();
$productResult = $checkProductStmt->get_result();

if ($productResult->num_rows == 0) {
    echo "<script>alert('Product not found.'); window.location.href='index.php';</script>";
    $checkProductStmt->close();
    $conn->close();
    exit;
}
$checkProductStmt->close();

// Check if product has existing orders
$checkOrderSql = "SELECT id FROM orders WHERE product_id = ?";
$checkOrderStmt = $conn->prepare($checkOrderSql);
$checkOrderStmt->bind_param("i", $id);
$checkOrderStmt->execute();
$orderResult = $checkOrderStmt->get_result();

if ($orderResult->num_rows > 0) {
    // Product has orders, cannot delete
    echo "<script>alert('Product can not delete because exists in order!'); window.location.href='index.php';</script>";
    $checkOrderStmt->close();
    $conn->close();
    exit;
}
$checkOrderStmt->close();

// Delete product
$deleteSql = "DELETE FROM products WHERE id = ?";
$deleteStmt = $conn->prepare($deleteSql);
$deleteStmt->bind_param("i", $id);

if ($deleteStmt->execute()) {
    echo "<script>alert('Product deleted successfully!'); window.location.href='index.php';</script>";
} else {
    echo "<script>alert('Error deleting product: " . $conn->error . "'); window.location.href='index.php';</script>";
}

$deleteStmt->close();
$conn->close();
?>