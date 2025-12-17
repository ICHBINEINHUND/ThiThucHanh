# Q3: Product API with Authentication

RESTful API cho quản lý Products với JWT Authentication và MongoDB.

## 📋 Yêu cầu

- Node.js v14+
- MongoDB (local hoặc cloud)
- npm/yarn

## 🚀 Cài đặt

```bash
# 1. Cài đặt dependencies
npm install

# 2. Cấu hình .env file
# Chỉnh sửa MONGO_URI và JWT_SECRET trong file .env

# 3. Đảm bảo MongoDB đang chạy (nếu dùng local)
# Windows: Khởi động MongoDB service
# hoặc sử dụng MongoDB Atlas (cloud)

# 4. Chạy server
npm run q3
```

## 📁 Cấu trúc thư mục

```
ThiThucHanh/
├── config/
│   └── db.js              # MongoDB connection
├── controllers/
│   └── productController.js # Business logic
├── middleware/
│   └── auth.js            # JWT authentication
├── models/
│   └── Product.js         # Product schema
├── routes/
│   ├── authRoutes.js      # Auth endpoints
│   └── productRoutes.js   # Product endpoints
├── .env                   # Environment variables
├── .gitignore
└── q3.js                  # Main application
```

## 🔐 Authentication

Tất cả Product routes đều được bảo vệ bằng JWT authentication.

### 1. Login để lấy token

**Endpoint:** `POST /api/auth/login`

**Request:**
```json
{
  "username": "test",
  "password": "test123"
}
```

**Response:**
```json
{
  "success": true,
  "message": "Login successful",
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "note": "Use this token in Authorization header: Bearer <token>"
}
```

### 2. Sử dụng token

Thêm token vào header của mỗi request:
```
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

## 📦 Product API Endpoints

### Get All Products (8 points)

**Endpoint:** `GET /api/products`

**Headers:**
```
Authorization: Bearer <your_token>
```

**Response:**
```json
{
  "success": true,
  "count": 2,
  "data": [
    {
      "_id": "65abc123...",
      "productName": "Laptop Dell XPS 15",
      "manufacturer": "Dell",
      "price": 1500,
      "createdAt": "2024-01-15T10:00:00.000Z",
      "updatedAt": "2024-01-15T10:00:00.000Z"
    }
  ]
}
```

### Get One Product (8 points)

**Endpoint:** `GET /api/products/:id`

**Headers:**
```
Authorization: Bearer <your_token>
```

**Response:**
```json
{
  "success": true,
  "data": {
    "_id": "65abc123...",
    "productName": "Laptop Dell XPS 15",
    "manufacturer": "Dell",
    "price": 1500,
    "createdAt": "2024-01-15T10:00:00.000Z",
    "updatedAt": "2024-01-15T10:00:00.000Z"
  }
}
```

### Create Product (4 points)

**Endpoint:** `POST /api/products`

**Headers:**
```
Authorization: Bearer <your_token>
Content-Type: application/json
```

**Request Body:**
```json
{
  "productName": "iPhone 15 Pro",
  "manufacturer": "Apple",
  "price": 999
}
```

**Response:**
```json
{
  "success": true,
  "message": "Product created successfully",
  "data": {
    "_id": "65abc456...",
    "productName": "iPhone 15 Pro",
    "manufacturer": "Apple",
    "price": 999,
    "createdAt": "2024-01-15T10:30:00.000Z",
    "updatedAt": "2024-01-15T10:30:00.000Z"
  }
}
```

## 🧪 Testing với cURL

### 1. Login
```bash
curl -X POST http://localhost:3000/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"test","password":"test123"}'
```

### 2. Create Product
```bash
curl -X POST http://localhost:3000/api/products \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_TOKEN_HERE" \
  -d '{"productName":"Laptop","manufacturer":"Dell","price":1500}'
```

### 3. Get All Products
```bash
curl -X GET http://localhost:3000/api/products \
  -H "Authorization: Bearer YOUR_TOKEN_HERE"
```

### 4. Get One Product
```bash
curl -X GET http://localhost:3000/api/products/PRODUCT_ID \
  -H "Authorization: Bearer YOUR_TOKEN_HERE"
```

## 🔒 Security Best Practices

✅ **Implemented:**
- JWT authentication trên tất cả product routes
- Helmet.js để bảo vệ HTTP headers
- Rate limiting (100 requests/15 phút)
- MongoDB injection protection
- Input validation
- Request payload size limit (10kb)
- Environment variables cho sensitive data
- Error handling middleware
- CORS configuration

## ⚙️ Environment Variables

```env
MONGO_URI=mongodb://localhost:27017/product_db
JWT_SECRET=your_super_secret_jwt_key_change_this_in_production_12345
PORT=3000
NODE_ENV=development
```

## 📝 Product Fields

- **productName**: String (required, max 100 chars)
- **manufacturer**: String (required, max 100 chars)
- **price**: Number (required, min 0)
- **createdAt**: Date (auto-generated)
- **updatedAt**: Date (auto-generated)

## 🎯 Coding Best Practices

- ✅ MVC pattern (Model-View-Controller)
- ✅ Separation of concerns
- ✅ Error handling và validation
- ✅ Async/await cho operations
- ✅ RESTful API conventions
- ✅ Clean code và comments
- ✅ Environment configuration
- ✅ Security middleware stack

## 📌 Notes

- Token hết hạn sau 24 giờ
- MongoDB phải đang chạy trước khi start server
- Nếu dùng MongoDB Atlas, cập nhật MONGO_URI trong .env
- Đổi JWT_SECRET thành giá trị bảo mật trong production
