const mongoose = require('mongoose');

// Q3: Product Schema
const productSchema = new mongoose.Schema({
  productName: {
    type: String,
    required: [true, 'Product name is required'],
    trim: true,
    maxlength: [100, 'Product name cannot exceed 100 characters']
  },
  manufacturer: {
    type: String,
    required: [true, 'Manufacturer is required'],
    trim: true,
    maxlength: [100, 'Manufacturer name cannot exceed 100 characters']
  },
  price: {
    type: Number,
    required: [true, 'Price is required'],
    min: [0, 'Price cannot be negative']
  }
}, {
  timestamps: true // Tự động thêm createdAt và updatedAt
});

// Index để tối ưu tìm kiếm
productSchema.index({ productName: 1 });
productSchema.index({ manufacturer: 1 });

module.exports = mongoose.model('Product', productSchema);
