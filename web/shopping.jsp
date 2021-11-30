<%-- 
    Document   : shopping
    Created on : Nov 2, 2021, 11:08:36 PM
    Author     : ANH DUY
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BOOK STORE</title>
    </head>
    <body>
        <h1>LIST BOOK</h1>
        <c:set var="listBook" value="${requestScope.LIST}"/>
        <c:if test="${not empty listBook}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>ID Book</th>
                        <th>Name</th>
                        <th>Author</th>
                        <th>Publish Year</th>
                        <th>Add to cart</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${listBook}" varStatus="counter">
                    <form action="processAddBookToCart">
                        <tr>
                            <td>
                                ${counter.count}
                                .</td>
                            <td>
                                ${dto.id}
                            </td>
                            <td>
                                ${dto.title}
                                <input type="hidden" name="txtNameBook" value="${dto.title}" />
                            </td>
                            <td>
                                ${dto.author}
                            </td>
                            <td>
                                ${dto.year}
                            </td>
                           
                            <td>
                                <input type="submit" value="Add to your cart"/>
                            </td>
                        </tr>
                    </form>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty listBook}">
        <h2>
            NO BOOK
        </h2>
    </c:if>
    <br>
    <a href="viewCart">View your cart</a>
</body>
</html>
