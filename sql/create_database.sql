-- ============================================
-- DATABASE: jakarta_ws_exam
-- TABLE: courses
-- ============================================

CREATE DATABASE IF NOT EXISTS jakarta_ws_exam;
USE jakarta_ws_exam;

DROP TABLE IF EXISTS courses;

CREATE TABLE courses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(20) NOT NULL,
    title VARCHAR(150) NOT NULL,
    credit INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO courses (code, title, credit) VALUES
('CS101', 'Introduction to Programming', 3),
('CS201', 'Data Structures', 4),
('CS301', 'Web Development', 3);
