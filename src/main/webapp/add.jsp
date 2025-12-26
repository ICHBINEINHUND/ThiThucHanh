<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Add New Course</title>
            <style>
                body {
                    font-family: Arial, sans-serif;
                    margin: 20px;
                    background-color: #f5f5f5;
                }

                h1 {
                    color: #333;
                }

                .form-container {
                    max-width: 600px;
                    background-color: white;
                    padding: 30px;
                    border-radius: 8px;
                    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
                }

                .form-group {
                    margin-bottom: 20px;
                }

                label {
                    display: block;
                    margin-bottom: 5px;
                    font-weight: bold;
                    color: #333;
                }

                input[type="text"],
                textarea {
                    width: 100%;
                    padding: 10px;
                    border: 1px solid #ddd;
                    border-radius: 4px;
                    box-sizing: border-box;
                }

                textarea {
                    resize: vertical;
                    min-height: 100px;
                }

                .error {
                    color: #f44336;
                    background-color: #ffebee;
                    padding: 10px;
                    border-radius: 4px;
                    margin-bottom: 20px;
                }

                .required {
                    color: #f44336;
                }

                .btn {
                    padding: 10px 20px;
                    text-decoration: none;
                    color: white;
                    border: none;
                    border-radius: 4px;
                    cursor: pointer;
                    margin-right: 10px;
                }

                .btn-submit {
                    background-color: #4CAF50;
                }

                .btn-cancel {
                    background-color: #999;
                }

                .btn:hover {
                    opacity: 0.8;
                }
            </style>
        </head>

        <body>
            <h1>Add New Course</h1>

            <div class="form-container">
                <c:if test="${not empty errorMessage}">
                    <div class="error">${errorMessage}</div>
                </c:if>

                <form method="post" action="${pageContext.request.contextPath}/add">
                    <div class="form-group">
                        <label for="courseCode">Course Code <span class="required">*</span></label>
                        <input type="text" id="courseCode" name="courseCode" value="${courseCode}" required>
                    </div>

                    <div class="form-group">
                        <label for="courseName">Course Name <span class="required">*</span></label>
                        <input type="text" id="courseName" name="courseName" value="${courseName}" required>
                    </div>

                    <div class="form-group">
                        <label for="description">Description</label>
                        <textarea id="description" name="description">${description}</textarea>
                    </div>

                    <div class="form-group">
                        <button type="submit" class="btn btn-submit">Add Course</button>
                        <a href="${pageContext.request.contextPath}/list" class="btn btn-cancel">Cancel</a>
                    </div>
                </form>
            </div>
        </body>

        </html>