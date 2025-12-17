require('dotenv').config();
const express = require('express');
const helmet = require('helmet');
const rateLimit = require('express-rate-limit');
const mongoSanitize = require('express-mongo-sanitize');
const connectDB = require('./config/db');

// Q3: Product API với Authentication
const app = express();

// Connect to Database
connectDB();

// Security Middleware
app.use(helmet()); // Bảo vệ headers
app.use(mongoSanitize()); // Ngăn NoSQL injection

// Rate limiting
const limiter = rateLimit({
  windowMs: 15 * 60 * 1000, // 15 phút
  max: 100, // Giới hạn 100 requests mỗi IP
  message: 'Too many requests from this IP, please try again later.'
});
app.use('/api/', limiter);

// Body parser middleware
app.use(express.json({ limit: '10kb' })); // Giới hạn payload size
app.use(express.urlencoded({ extended: true, limit: '10kb' }));

// CORS (nếu cần)
app.use((req, res, next) => {
  res.header('Access-Control-Allow-Origin', '*');
  res.header('Access-Control-Allow-Methods', 'GET, POST, PUT, DELETE');
  res.header('Access-Control-Allow-Headers', 'Content-Type, Authorization');
  next();
});

// Routes
const authRoutes = require('./routes/authRoutes');
const productRoutes = require('./routes/productRoutes');

app.use('/api/auth', authRoutes);
app.use('/api/products', productRoutes);

// Home route
app.get('/', (req, res) => {
  res.json({
    message: 'Q3: Product API with Authentication',
    endpoints: {
      auth: {
        login: 'POST /api/auth/login'
      },
      products: {
        getAll: 'GET /api/products (Protected)',
        getOne: 'GET /api/products/:id (Protected)',
        create: 'POST /api/products (Protected)'
      }
    },
    note: 'All product routes require Bearer token in Authorization header'
  });
});

// 404 Handler
app.use((req, res) => {
  res.status(404).json({
    success: false,
    message: 'Route not found'
  });
});

// Global Error Handler
app.use((err, req, res, next) => {
  console.error(err.stack);
  res.status(err.status || 500).json({
    success: false,
    message: err.message || 'Internal server error'
  });
});

const PORT = process.env.PORT || 3000;

app.listen(PORT, () => {
  console.log(`\n🚀 Q3 Server running on port ${PORT}`);
  console.log(`📝 API Documentation: http://localhost:${PORT}`);
  console.log(`🔐 Login: POST http://localhost:${PORT}/api/auth/login`);
  console.log(`📦 Products: GET http://localhost:${PORT}/api/products\n`);
});

module.exports = app;
