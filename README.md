# Q3: Product API (12 points)

## API Endpoints

### Get All Products (8 points)
```
GET /api/products
```

**Response:**
```json
{
    "success": true,
    "count": 3,
    "data": [
        {
            "id": 1,
            "productName": "Laptop Dell",
            "manufacturer": "Dell",
            "price": 1500
        }
    ]
}
```

### Get One Product (8 points)
```
GET /api/products/:id
```

**Response:**
```json
{
    "success": true,
    "data": {
        "id": 1,
        "productName": "Laptop Dell",
        "manufacturer": "Dell",
        "price": 1500
    }
}
```

### Create New Product (4 points)
```
POST /api/products
Content-Type: application/json

{
    "productName": "Product Name",
    "manufacturer": "Manufacturer Name",
    "price": 100
}
```

**Response:**
```json
{
    "success": true,
    "message": "Product created successfully",
    "data": {
        "id": 4,
        "productName": "Product Name",
        "manufacturer": "Manufacturer Name",
        "price": 100
    }
}
```

## Product Fields
- **Product Name**: String (required)
- **Manufacturer**: String (required)
- **Price**: Number (required)

## How to Run

1. Install dependencies:
```bash
npm install
```

2. Start the server:
```bash
node server.js
```

3. Server will run on: http://localhost:3000

## Testing with cURL

### Get all products:
```bash
curl http://localhost:3000/api/products
```

### Get one product:
```bash
curl http://localhost:3000/api/products/1
```

### Create a new product:
```bash
curl -X POST http://localhost:3000/api/products -H "Content-Type: application/json" -d "{\"productName\":\"Test Product\",\"manufacturer\":\"Test Manufacturer\",\"price\":500}"
```
