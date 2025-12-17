const express = require('express');
const router = express.Router();
const jwt = require('jsonwebtoken');

// Q3: Auth Routes - Tạo token để test API

// POST /api/auth/login - Tạo JWT token
router.post('/login', (req, res) => {
  const { username, password } = req.body;
  
  // Demo authentication (trong thực tế cần verify với database)
  if (!username || !password) {
    return res.status(400).json({
      success: false,
      message: 'Please provide username and password'
    });
  }
  
  // Demo: accept any username/password cho testing
  // Trong production, cần verify với database
  const payload = {
    id: '123',
    username: username,
    role: 'user'
  };
  
  // Tạo token với expiry 24h
  const token = jwt.sign(payload, process.env.JWT_SECRET, {
    expiresIn: '24h'
  });
  
  res.status(200).json({
    success: true,
    message: 'Login successful',
    token: token,
    note: 'Use this token in Authorization header: Bearer <token>'
  });
});

module.exports = router;
