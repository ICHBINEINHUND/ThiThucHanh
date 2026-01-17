# Product Management - Hướng dẫn chạy với XAMPP

## 1. Khởi động XAMPP
- Mở XAMPP Control Panel
- Start **Apache** và **MySQL**

## 2. Copy project vào htdocs

Mở PowerShell và chạy lệnh sau:

```
xcopy "c:\Users\AD\PHP_Lab313" "C:\xampp\htdocs\PHP_Lab313\" /E /I /Y
```

## 3. Tạo Database

Truy cập phpMyAdmin: http://localhost/phpmyadmin

Chạy SQL sau:

```sql
CREATE DATABASE product_management;
USE product_management;

CREATE TABLE products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL UNIQUE,
    price INT CHECK (price > 0),
    stock INT CHECK (stock >= 1)
);

CREATE TABLE orders (
    id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT,
    quantity INT CHECK (quantity > 0),
    order_date DATE DEFAULT (CURDATE()),
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE RESTRICT
);

INSERT INTO products (product_name, price, stock) VALUES
('Dell Inspiron Laptop', 15000000, 20),
('HP Pavilion Laptop', 18000000, 5),
('MacBook Air M1', 25000000, 15),
('Logitech Wireless Mouse', 500000, 50),
('Razer Mechanical Keyboard', 2000000, 8),
('Sony WH-1000XM4 Headphones', 7000000, 12),
('LG 24-inch Monitor', 3500000, 25),
('Samsung 1TB SSD', 3200000, 18);

INSERT INTO orders (product_id, quantity) VALUES
(1, 2),
(3, 1),
(6, 3);
```

## 4. Truy cập ứng dụng

Mở trình duyệt và truy cập:

```
http://localhost/PHP_Lab313/
```

## Các trang chức năng

| Trang | URL |
|-------|-----|
| Danh sách sản phẩm | http://localhost/PHP_Lab313/index.php |
| Thêm sản phẩm | http://localhost/PHP_Lab313/add_product.php |
| Sửa sản phẩm | http://localhost/PHP_Lab313/edit_product.php?id=1 |
