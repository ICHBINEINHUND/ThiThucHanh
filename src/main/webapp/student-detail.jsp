<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <title>Student Details</title>
            <style>
                body {
                    font-family: Arial, sans-serif;
                    margin: 40px;
                    background-color: #f5f5f5;
                }

                h1,
                h2 {
                    color: #333;
                }

                .student-info {
                    background-color: white;
                    padding: 20px;
                    margin-bottom: 20px;
                    border-radius: 5px;
                    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
                }

                .student-info p {
                    margin: 10px 0;
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

                input[type="number"] {
                    padding: 5px;
                    width: 80px;
                }

                button {
                    padding: 8px 16px;
                    margin-right: 5px;
                    border: none;
                    border-radius: 4px;
                    cursor: pointer;
                }

                .btn-update {
                    background-color: #4CAF50;
                    color: white;
                }

                .btn-update:hover {
                    background-color: #45a049;
                }

                .btn-delete {
                    background-color: #f44336;
                    color: white;
                }

                .btn-delete:hover {
                    background-color: #da190b;
                }

                .btn-back {
                    background-color: #2196F3;
                    color: white;
                    display: inline-block;
                    text-decoration: none;
                    margin-top: 20px;
                }

                .btn-back:hover {
                    background-color: #0b7dda;
                }

                .error {
                    color: red;
                    margin: 10px 0;
                }
            </style>
        </head>

        <body>
            <h1>Student Details</h1>

            <div class="student-info">
                <h2>${student.full_name}</h2>
                <p><strong>Class:</strong> ${student.class_name}</p>
                <p><strong>Date of Birth:</strong> ${student.date_of_birth}</p>
            </div>

            <c:if test="${param.error == 'invalid_score'}">
                <div class="error">Error: Score must be between 0 and 100</div>
            </c:if>

            <h2>Marks</h2>
            <table>
                <thead>
                    <tr>
                        <th>Subject</th>
                        <th>Score</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="mark" items="${marks}">
                        <tr>
                            <td>${mark.subject}</td>
                            <td>
                                <form action="${pageContext.request.contextPath}/update-mark" method="post"
                                    style="display:inline;">
                                    <input type="hidden" name="markId" value="${mark.id}">
                                    <input type="hidden" name="studentId" value="${student.id}">
                                    <input type="number" name="scoreValue" value="${mark.score_value}" min="0" max="100"
                                        step="0.1">
                                    <button type="submit" class="btn-update">Update</button>
                                </form>
                            </td>
                            <td>
                                <form action="${pageContext.request.contextPath}/delete-mark" method="post"
                                    style="display:inline;"
                                    onsubmit="return confirm('Are you sure you want to delete this mark?');">
                                    <input type="hidden" name="markId" value="${mark.id}">
                                    <input type="hidden" name="studentId" value="${student.id}">
                                    <button type="submit" class="btn-delete">Delete</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <a href="${pageContext.request.contextPath}/students" class="btn-back">Back to Student List</a>
        </body>

        </html>