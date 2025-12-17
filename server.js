const express = require('express');
const app = express();
const PORT = 3000;

// Middleware
app.use(express.json());

// In-memory storage for products
let products = [
    { id: 1, productName: 'Laptop Dell', manufacturer: 'Dell', price: 1500 },
    { id: 2, productName: 'iPhone 15', manufacturer: 'Apple', price: 999 },
    { id: 3, productName: 'Samsung Galaxy S24', manufacturer: 'Samsung', price: 899 }
];

let nextId = 4;

// GET all products
app.get('/api/products', (req, res) => {
    res.json({
        success: true,
        count: products.length,
        data: products
    });
});

// GET one product by ID
app.get('/api/products/:id', (req, res) => {
    const product = products.find(p => p.id === parseInt(req.params.id));
    
    if (!product) {
        return res.status(404).json({
            success: false,
            message: 'Product not found'
        });
    }
    
    res.json({
        success: true,
        data: product
    });
});

// POST create a new product
app.post('/api/products', (req, res) => {
    const { productName, manufacturer, price } = req.body;
    
    // Validation
    if (!productName || !manufacturer || !price) {
        return res.status(400).json({
            success: false,
            message: 'Please provide productName, manufacturer, and price'
        });
    }
    
    const newProduct = {
        id: nextId++,
        productName,
        manufacturer,
        price: parseFloat(price)
    };
    
    products.push(newProduct);
    
    res.status(201).json({
        success: true,
        message: 'Product created successfully',
        data: newProduct
    });
});

// Start server
app.listen(PORT, () => {
    console.log(`Server is running on http://localhost:${PORT}`);
});
