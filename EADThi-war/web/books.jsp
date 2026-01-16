<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>Books List</title>
            </head>

            <body>
                <h1>Library Management System</h1>

                <p><a href="borrow">Borrow Book</a></p>

                <h2>All Books</h2>

                <table border="1">
                    <tr>
                        <th>ID</th>
                        <th>ISBN</th>
                        <th>Title</th>
                        <th>Author</th>
                        <th>Status</th>
                        <th>Borrow Date</th>
                    </tr>
                    <c:forEach items="${books}" var="book">
                        <tr>
                            <td>${book.bookId}</td>
                            <td>${book.isbn}</td>
                            <td>${book.title}</td>
                            <td>${book.author}</td>
                            <td>${book.availabilityStatus}</td>
                            <td>
                                <c:if test="${book.borrowDate != null}">
                                    <fmt:formatDate value="${book.borrowDate}" pattern="dd/MM/yyyy HH:mm:ss" />
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </body>

            </html>