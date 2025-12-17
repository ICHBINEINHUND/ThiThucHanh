<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <title>Student List</title>
            <style>
                body {
                    font-family: Arial, sans-serif;
                    margin: 40px;
                    background-color: #f5f5f5;
                }

                h1 {
                    color: #333;
                }

                table {
                    width: 100%;
                    border-collapse: collapse;
                    background-color: white;
                    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
                }

                th,
                td {
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

                a {
                    color: #4CAF50;
                    text-decoration: none;
                }

                a:hover {
                    text-decoration: underline;
                }
            </style>
        </head>

        <body>
            <h1>Student List</h1>
            <table>
                <thead>
                    <tr>
                        <th>Full Name</th>
                        <th>Class Name</th>
                        <th>Date of Birth</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="student" items="${students}">
                        <tr>
                            <td>${student.full_name}</td>
                            <td>${student.class_name}</td>
                            <td>${student.date_of_birth}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/student-detail?studentId=${student.id}">View
                                    Details</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </body>

        </html>