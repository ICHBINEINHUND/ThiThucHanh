<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Borrow Form</title>
        </head>

        <body>
            <h1>Borrow Form</h1>

            <c:if test="${not empty msg}">
                <p><strong>${msg}</strong></p>
            </c:if>

            <form action="borrow" method="POST">
                <p>Username: <input type="text" name="username" value="${param.username}" required /></p>
                <p>Book ID: <input type="text" name="bookId" value="${param.bookId}" required /></p>
                <p><input type="submit" value="Submit" /></p>
            </form>

            <p><a href="books">Back</a></p>
        </body>

        </html>