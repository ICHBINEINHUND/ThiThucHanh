const express = require('express');
const router = express.Router();
const { authenticate } = require('../middleware/auth');
const {
  getAllProducts,
  getProductById,
  createProduct
} = require('../controllers/productController');

// Q3: Product Routes - All protected with authentication

// Get all products
router.get('/', authenticate, getAllProducts);

// Get one product by ID
router.get('/:id', authenticate, getProductById);

// Create new product
router.post('/', authenticate, createProduct);

module.exports = router;
