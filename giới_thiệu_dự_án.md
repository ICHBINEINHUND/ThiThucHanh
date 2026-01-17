# Student Management System - Walkthrough

## 📋 Tổng quan

Hệ thống quản lý sinh viên được xây dựng với **Servlet + JSP + JPA (Hibernate)** trên MySQL database.

---

## ✅ Các chức năng đã hoàn thành

| Chức năng | Mô tả | Điểm |
|-----------|-------|------|
| **View All Students** | Hiển thị danh sách sinh viên với Full Name, Class, DOB | 5 |
| **View Marks of a Student** | Xem điểm các môn của từng sinh viên | 6 |
| **Update a Mark** | Cập nhật điểm với validation 0-100 | 6 |
| **Delete a Mark** | Xóa điểm với confirm dialog | 3 |

---

## 📂 Cấu trúc Project

```
src/main/java/com/studentmanagement/
├── entity/
│   ├── Student.java          # JPA Entity - bảng t_student
│   └── Mark.java             # JPA Entity - bảng t_mark
├── dao/
│   ├── StudentDAO.java       # Data Access cho Student
│   └── MarkDAO.java          # Data Access cho Mark
├── servlet/
│   ├── StudentListServlet.java     # GET /students
│   ├── StudentDetailServlet.java   # GET /student-detail
│   ├── UpdateMarkServlet.java      # POST /update-mark
│   └── DeleteMarkServlet.java      # POST /delete-mark
└── util/
    └── JPAUtil.java          # EntityManagerFactory singleton

src/main/webapp/
├── student-list.jsp          # Trang danh sách sinh viên
└── student-detail.jsp        # Trang chi tiết điểm

src/main/resources/META-INF/
└── persistence.xml           # Cấu hình JPA/Hibernate
```

---

## 🔧 Cấu hình Database

**File**: `src/main/resources/META-INF/persistence.xml`

```xml
<persistence-unit name="StudentManagementPU">
  <class>com.studentmanagement.entity.Student</class>
  <class>com.studentmanagement.entity.Mark</class>
  <properties>
    <property name="jakarta.persistence.jdbc.url" 
              value="jdbc:mysql://localhost:3306/management_student"/>
    <property name="jakarta.persistence.jdbc.user" value="root"/>
    <property name="jakarta.persistence.jdbc.password" value="123456"/>
  </properties>
</persistence-unit>
```

---

## 📊 Database Schema

### Bảng `t_student`
| Column | Type | Description |
|--------|------|-------------|
| id | INT (PK, AI) | Primary Key |
| full_name | VARCHAR(50) | Họ tên sinh viên |
| class_name | VARCHAR(50) | Tên lớp |
| date_of_birth | DATETIME | Ngày sinh |

### Bảng `t_mark`
| Column | Type | Description |
|--------|------|-------------|
| id | INT (PK, AI) | Primary Key |
| subject | VARCHAR(50) | Tên môn học |
| core_value | FLOAT | Điểm số (0-100) |
| student_id | INT (FK) | Foreign Key → t_student.id |

---

## 🚀 Hướng dẫn chạy

### 1. Tạo Database
```sql
CREATE DATABASE management_student;
USE management_student;

-- Tạo bảng t_student
CREATE TABLE t_student (
  id INT(11) NOT NULL AUTO_INCREMENT,
  full_name VARCHAR(50) DEFAULT NULL,
  class_name VARCHAR(50) DEFAULT NULL,
  date_of_birth DATETIME DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Tạo bảng t_mark
CREATE TABLE t_mark (
  id INT(11) NOT NULL AUTO_INCREMENT,
  subject VARCHAR(50) DEFAULT NULL,
  core_value FLOAT DEFAULT NULL,
  student_id INT(11) DEFAULT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (student_id) REFERENCES t_student(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Insert dữ liệu mẫu
INSERT INTO t_student (id, full_name, class_name, date_of_birth) VALUES
(1, 'Alice Nguyen', '10A1', '2008-04-12 00:00:00'),
(2, 'Bob Tran', '10A2', '2008-08-25 00:00:00'),
(3, 'Charlie Le', '10A1', '2008-11-03 00:00:00'),
(4, 'Diana Pham', '10A3', '2009-01-19 00:00:00'),
(5, 'Ethan Hoang', '10A2', '2008-07-05 00:00:00');

INSERT INTO t_mark (id, subject, core_value, student_id) VALUES
(1, 'Math', 85, 1),
(2, 'English', 92, 1),
(3, 'Physics', 78, 1),
(4, 'Math', 88, 2),
(5, 'English', 75, 2),
(6, 'Chemistry', 82, 2),
(7, 'Math', 90, 3),
(8, 'Physics', 85, 3),
(9, 'History', 70, 3),
(10, 'Math', 73, 4),
(11, 'English', 80, 4),
(12, 'Biology', 77, 4),
(13, 'Math', 95, 5),
(14, 'Chemistry', 88, 5),
(15, 'Literature', 90, 5);
```

### 2. Build Project
```bash
mvnw.cmd clean package -DskipTests
```

### 3. Deploy lên Tomcat
- Copy file `target/WCD-1.0-SNAPSHOT.war` vào thư mục `webapps` của Tomcat
- Khởi động Tomcat

### 4. Truy cập ứng dụng
- **Danh sách sinh viên**: http://localhost:8080/WCD-1.0-SNAPSHOT/students
- **Chi tiết điểm**: http://localhost:8080/WCD-1.0-SNAPSHOT/student-detail?studentId=1

---

## 🎯 Flow hoạt động

```
[Browser] --GET /students--> [StudentListServlet]
                                    |
                                    v
                            [StudentDAO.findAll()]
                                    |
                                    v
                            [student-list.jsp] <-- Hiển thị danh sách
                                    |
                            [Click "View Details"]
                                    |
                                    v
[Browser] --GET /student-detail?studentId=X--> [StudentDetailServlet]
                                                      |
                                                      v
                                    [StudentDAO.findById() + MarkDAO.findByStudentId()]
                                                      |
                                                      v
                                              [student-detail.jsp] <-- Hiển thị điểm
                                                      |
                              +---------------+---------------+
                              |                               |
                        [Update Mark]                   [Delete Mark]
                              |                               |
                              v                               v
                    [UpdateMarkServlet]             [DeleteMarkServlet]
                              |                               |
                              v                               v
                    [MarkDAO.update()]              [MarkDAO.delete()]
                              |                               |
                              +---------------+---------------+
                                              |
                                              v
                                    [Redirect to student-detail]
```

---

## ✨ Tính năng nổi bật

1. **Validation điểm số**: 
   - Client-side: HTML5 `min="0" max="100"`
   - Server-side: Kiểm tra `scoreValue < 0 || scoreValue > 100`

2. **Confirm khi xóa**: JavaScript `confirm()` dialog trước khi xóa

3. **Error handling**: Hiển thị thông báo lỗi khi điểm không hợp lệ

4. **Responsive UI**: CSS styling với hover effects và shadows

5. **Clean Architecture**: Tách biệt rõ ràng Entity → DAO → Servlet → JSP

---

## 📝 Các file chính

### Entity Classes
- `Student.java`: Entity mapping với bảng `t_student`
- `Mark.java`: Entity mapping với bảng `t_mark`

### DAO Classes
- `StudentDAO.java`: `findAll()`, `findById(int id)`
- `MarkDAO.java`: `findByStudentId()`, `findById()`, `update()`, `delete()`

### Servlet Classes
- `StudentListServlet.java`: Xử lý GET `/students`
- `StudentDetailServlet.java`: Xử lý GET `/student-detail`
- `UpdateMarkServlet.java`: Xử lý POST `/update-mark`
- `DeleteMarkServlet.java`: Xử lý POST `/delete-mark`

### JSP Views
- `student-list.jsp`: Hiển thị danh sách sinh viên
- `student-detail.jsp`: Hiển thị chi tiết điểm với form Update/Delete
