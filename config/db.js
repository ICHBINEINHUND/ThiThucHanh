const mongoose = require('mongoose');

// Q3: Database Configuration
const connectDB = async () => {
  try {
    const conn = await mongoose.connect(process.env.MONGO_URI, {
      // Mongoose 6+ không cần các options này nữa
      // useNewUrlParser: true,
      // useUnifiedTopology: true,
    });
    
    console.log(`✅ MongoDB Connected: ${conn.connection.host}`);
  } catch (error) {
    console.error(`❌ MongoDB Connection Error: ${error.message}`);
    process.exit(1); // Exit với failure
  }
};

module.exports = connectDB;
