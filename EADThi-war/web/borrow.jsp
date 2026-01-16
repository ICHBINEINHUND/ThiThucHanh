<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Borrow Book</title>
        </head>

        <body>
            <h1>Borrow Book</h1>

            <c:if test="${not empty resultMessage}">
                <p><strong>${resultMessage}</strong></p>
            </c:if>

            <form action="borrow" method="POST">
                <p>Username: <input type="text" name="username" value="${param.username}" required /></p>
                <p>Book ID: <input type="text" name="bookId" value="${param.bookId}" required /></p>
                <p><input type="submit" value="Borrow" /></p>
            </form>

            <p><a href="books">View All Books</a></p>
        </body>

        </html>