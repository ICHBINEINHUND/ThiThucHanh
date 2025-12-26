<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Course Management - List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f5f5f5;
        }
        h1 {
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            background-color: white;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        tr:hover {
            background-color: #f5f5f5;
        }
        .btn {
            padding: 8px 16px;
            text-decoration: none;
            color: white;
            border-radius: 4px;
            display: inline-block;
            margin: 5px 2px;
        }
        .btn-add {
            background-color: #4CAF50;
        }
        .btn-edit {
            background-color: #2196F3;
        }
        .btn-delete {
            background-color: #f44336;
        }
        .btn:hover {
            opacity: 0.8;
        }
        .actions {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <h1>Course Management System</h1>
    
    <div class="actions">
        <a href="${pageContext.request.contextPath}/add" class="btn btn-add">Add New Course</a>
    </div>
    
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Course Code</th>
                <th>Course Name</th>
                <th>Description</th>
                <th>Created At</th>
                <th>Updated At</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="course" items="${courses}">
                <tr>
                    <td>${course.id}</td>
                    <td>${course.courseCode}</td>
                    <td>${course.courseName}</td>
                    <td>${course.description}</td>
                    <td>${course.createdAt}</td>
                    <td>${course.updatedAt}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/edit?id=${course.id}" class="btn btn-edit">Edit</a>
                        <a href="${pageContext.request.contextPath}/delete?id=${course.id}" 
                           class="btn btn-delete" 
                           onclick="return confirm('Are you sure you want to delete this course?');">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty courses}">
                <tr>
                    <td colspan="7" style="text-align: center;">No courses found. Click "Add New Course" to create one.</td>
                </tr>
            </c:if>
        </tbody>
    </table>
</body>
</html>
