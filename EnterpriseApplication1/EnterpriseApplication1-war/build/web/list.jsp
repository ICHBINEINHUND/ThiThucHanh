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
                <h1>Library Book List</h1>

                <p><a href="borrow">Borrow</a></p>

                <h2>Books</h2>

                <table border="1">
                    <tr>
                        <th>ID</th>
                        <th>Title</th>
                        <th>Author</th>
                        <th>Status</th>
                        <th>Date</th>
                    </tr>
                    <c:forEach items="${items}" var="item">
                        <tr>
                            <td>${item.bookId}</td>
                            <td>${item.bookTitle}</td>
                            <td>${item.bookAuthor}</td>
                            <td>${item.currentStatus}</td>
                            <td>
                                <c:if test="${item.dateBorrowed != null}">
                                    <fmt:formatDate value="${item.dateBorrowed}" pattern="dd/MM/yyyy HH:mm:ss" />
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </body>

            </html>