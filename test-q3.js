// Q3 - Quick Test Script
// Chạy file này sau khi server đã start để test API

const baseUrl = 'http://localhost:3000';
let authToken = '';

console.log('🧪 Q3 API Testing\n');

// Helper function
async function makeRequest(method, endpoint, data = null, useAuth = false) {
  const headers = {
    'Content-Type': 'application/json'
  };
  
  if (useAuth && authToken) {
    headers['Authorization'] = `Bearer ${authToken}`;
  }
  
  const options = {
    method,
    headers
  };
  
  if (data) {
    options.body = JSON.stringify(data);
  }
  
  try {
    const response = await fetch(baseUrl + endpoint, options);
    const result = await response.json();
    return { status: response.status, data: result };
  } catch (error) {
    return { error: error.message };
  }
}

async function runTests() {
  // Test 1: Login
  console.log('1️⃣ Testing Login...');
  const loginResult = await makeRequest('POST', '/api/auth/login', {
    username: 'test',
    password: 'test123'
  });
  
  if (loginResult.data && loginResult.data.token) {
    authToken = loginResult.data.token;
    console.log('✅ Login successful');
    console.log('Token:', authToken.substring(0, 50) + '...\n');
  } else {
    console.log('❌ Login failed:', loginResult);
    return;
  }
  
  // Test 2: Create Products
  console.log('2️⃣ Creating products...');
  
  const products = [
    { productName: 'Laptop Dell XPS 15', manufacturer: 'Dell', price: 1500 },
    { productName: 'iPhone 15 Pro', manufacturer: 'Apple', price: 999 },
    { productName: 'Samsung Galaxy S24', manufacturer: 'Samsung', price: 899 }
  ];
  
  for (const product of products) {
    const result = await makeRequest('POST', '/api/products', product, true);
    if (result.data && result.data.success) {
      console.log(`✅ Created: ${product.productName}`);
    } else {
      console.log(`❌ Failed to create: ${product.productName}`, result);
    }
  }
  
  console.log('');
  
  // Test 3: Get All Products
  console.log('3️⃣ Getting all products...');
  const allProducts = await makeRequest('GET', '/api/products', null, true);
  
  if (allProducts.data && allProducts.data.success) {
    console.log(`✅ Found ${allProducts.data.count} products:`);
    allProducts.data.data.forEach((p, i) => {
      console.log(`   ${i + 1}. ${p.productName} - $${p.price} (${p.manufacturer})`);
    });
    console.log('');
    
    // Test 4: Get One Product
    if (allProducts.data.data.length > 0) {
      const firstProductId = allProducts.data.data[0]._id;
      console.log('4️⃣ Getting product by ID...');
      const oneProduct = await makeRequest('GET', `/api/products/${firstProductId}`, null, true);
      
      if (oneProduct.data && oneProduct.data.success) {
        console.log('✅ Product details:');
        console.log('   Name:', oneProduct.data.data.productName);
        console.log('   Manufacturer:', oneProduct.data.data.manufacturer);
        console.log('   Price:', oneProduct.data.data.price);
      }
    }
  } else {
    console.log('❌ Failed to get products:', allProducts);
  }
  
  console.log('\n✨ Testing complete!');
}

// Run tests
runTests().catch(console.error);
