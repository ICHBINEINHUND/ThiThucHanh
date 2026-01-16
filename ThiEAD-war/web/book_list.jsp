<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>Book List</title>
            </head>

            <body>
                <h1>Library Book Management System</h1>

                <p><a href="borrow">Borrow a Book</a></p>

                <h2>All Books</h2>

                <table border="1">
                    <tr>
                        <th>Book ID</th>
                        <th>Title</th>
                        <th>Author</th>
                        <th>Status</th>
                        <th>Borrowed Date</th>
                    </tr>
                    <c:forEach items="${bookList}" var="book">
                        <tr>
                            <td>${book.id}</td>
                            <td>${book.title}</td>
                            <td>${book.author}</td>
                            <td>${book.status}</td>
                            <td>
                                <c:if test="${book.borrowedDate != null}">
                                    <fmt:formatDate value="${book.borrowedDate}" pattern="dd/MM/yyyy HH:mm:ss" />
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </body>

            </html>