import java.sql.*;
import java.util.Scanner;

public class Question2_StudentManagement {
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mydatabase";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";
    
    private static Connection connection = null;
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("=== Student Management System ===\n");
        
        if (!initConnection()) {
            System.out.println("Failed to connect to database. Exiting...");
            return;
        }
        
        createTableIfNotExists();
        
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    viewAllStudents();
                    break;
                case 2:
                    addNewStudent();
                    break;
                case 3:
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        }
        
        closeConnection();
    }
    
    private static void displayMenu() {
        System.out.println("========== MENU ==========");
        System.out.println("1. View all students");
        System.out.println("2. Add new student");
        System.out.println("3. Exit");
        System.out.println("==========================");
    }
    
    private static boolean initConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Database connected successfully!\n");
            return true;
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found: " + e.getMessage());
            return false;
        } catch (SQLException e) {
            System.err.println("Connection failed: " + e.getMessage());
            return false;
        }
    }
    
    private static void createTableIfNotExists() {
        String sql = "CREATE TABLE IF NOT EXISTS tbl_student (" +
                     "id INT AUTO_INCREMENT PRIMARY KEY, " +
                     "name VARCHAR(50), " +
                     "birth_of_date DATE, " +
                     "phone_number VARCHAR(10), " +
                     "grade_point_average FLOAT)";
        
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Table tbl_student is ready.\n");
        } catch (SQLException e) {
            System.err.println("Error creating table: " + e.getMessage());
        }
    }
    
    private static void viewAllStudents() {
        String sql = "SELECT * FROM tbl_student";
        
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            System.out.println("\n========== ALL STUDENTS ==========");
            System.out.printf("%-5s %-20s %-15s %-15s %-10s%n", 
                              "ID", "Name", "Birth Date", "Phone", "GPA");
            System.out.println("----------------------------------------------------------");
            
            boolean hasData = false;
            
            while (rs.next()) {
                hasData = true;
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Date birthDate = rs.getDate("birth_of_date");
                String phone = rs.getString("phone_number");
                float gpa = rs.getFloat("grade_point_average");
                
                String birthStr = (birthDate != null) ? birthDate.toString() : "N/A";
                System.out.printf("%-5d %-20s %-15s %-15s %-10.2f%n", 
                                  id, name, birthStr, phone, gpa);
            }
            
            if (!hasData) {
                System.out.println("No students found in the database.");
            }
            
            System.out.println("==================================\n");
            
        } catch (SQLException e) {
            System.err.println("Error viewing students: " + e.getMessage());
        }
    }
    
    private static void addNewStudent() {
        System.out.println("\n========== ADD NEW STUDENT ==========");
        
        scanner.nextLine();
        
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter birth date (yyyy-MM-dd): ");
        String birthDateStr = scanner.nextLine();
        
        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();
        
        float gpa = getFloatInput("Enter grade point average (GPA): ");
        
        String sql = "INSERT INTO tbl_student (name, birth_of_date, phone_number, grade_point_average) " +
                     "VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, name);
            
            try {
                java.sql.Date birthDate = java.sql.Date.valueOf(birthDateStr);
                pstmt.setDate(2, birthDate);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid date format. Using null.");
                pstmt.setNull(2, Types.DATE);
            }
            
            pstmt.setString(3, phone);
            pstmt.setFloat(4, gpa);
            
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("Student added successfully!");
            } else {
                System.out.println("Failed to add student.");
            }
            
        } catch (SQLException e) {
            System.err.println("Error adding student: " + e.getMessage());
        }
        
        System.out.println("======================================\n");
    }
    
    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. " + prompt);
            scanner.next();
        }
        return scanner.nextInt();
    }
    
    private static float getFloatInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextFloat()) {
            System.out.print("Invalid input. " + prompt);
            scanner.next();
        }
        return scanner.nextFloat();
    }
    
    private static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }
}
