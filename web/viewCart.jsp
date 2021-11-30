<%-- 
    Document   : viewCart
    Created on : Oct 17, 2021, 8:31:11 PM
    Author     : ANH DUY
--%>

<%@page import="java.util.Map"%>
<%@page import="duyla.cart.CartObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store</title>
    </head>
    <body>
        <h1>Book Store</h1>
        <h2>Your cart includes</h2>
        <c:set var="cart" value="${sessionScope.CART}"/>
        <c:if test="${not empty cart}">
            <c:set var="items" value="${cart.items}"/>
            <c:if test="${not empty items}">
                <form action="processRemoveBookFromCart">

                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Title</th>
                                <th>Quantity</th>
                                <th>Action</th>

                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${items}" varStatus="counter">
                                <tr>
                                    <td>
                                        ${counter.count}
                                    </td>
                                    <td>
                                        ${item.key}
                                    </td>
                                    <td>
                                        ${item.value}
                                    </td>
                                    <td>
                                        <input type="checkbox" name="chkItem" 
                                               value="${item.key}" />
                                    </td>

                                </tr>
                            </c:forEach>
                            <tr>
                                <td colspan="3">
                                    <a href="processListBook">Add More Book to Your Cart</a>
                                </td>
                                <td>
                                    <input type="submit" value="Remove Selected Books" name="btAction" />
                                </td>


                            </tr>
                        </tbody>
                </form>
            </c:if>
        </c:if>
        <c:if test="${empty cart}">
            <h2>
                No Cart is existed!!!!
            </h2>
        </c:if>

        <%-- <%
            //1.cust  Goes to  his/her cart
            if (session != null) {
                //2. take cart
                CartObject cart = (CartObject) session.getAttribute("CART");
                if (cart != null) {
                    //3. take items
                    Map<String, Integer> items = cart.getItems();
                    if (items != null) {
        %> 
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Title</th>
                    <th>Quantity</th>
                    <th>Action</th>
                </tr>
            </thead>
            <form action="DispatcherController">
                <tbody>
                    <%
                        int count = 0;
                        for (String title : items.keySet()) {
                    %> 
                    <tr>
                        <td>
                            <%=++count%>
                        </td>
                        <td>
                            <%= title%>
                        </td>
                        <td>
                            <%= items.get(title)%>
                        </td>
                        <td>
                            <input type="checkbox" name="chkItem" 
                                   value="<%= title%>" />
                        </td>
                    </tr>
                    <%
                        }//end if traversal items using key
                    %>
                    <tr>
                        <td colspan="3">
                            <a href="shoping.html">Add More Book to Your Cart</a>
                        </td>
                        <td>
                            <input type="submit" value="Remove Selected Books" name="btAction" />
                        </td>
                    </tr>
                </tbody>
            </form>

        </table>

        <%
                        return;
                    }//end if items has already existed
                }// cart in exist
            }//end if cust has alredy added books
%> 

        <h2>
            No Cart is existed!!!!
        </h2>--%>
    </body>
</html>
