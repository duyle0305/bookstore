<%-- 
    Document   : signUp
    Created on : Oct 17, 2021, 11:44:06 PM
    Author     : ANH DUY
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
        <h1>Create New Account </h1>
        <form action="processCreateAccount" method="POST">
            <c:set var="errors" value="${requestScope.CREATE_ERRORS}"/>
            Username* <input type="text" name="txtUsername" value="${param.txtUsername}" />(e.g. 6 -30 chars)<br/>
            <c:if test="${not empty errors.usernameLengtherr}">
                <font color="red">
                ${errors.usernameLengtherr}
                </font><br/>
            </c:if> 
            Password* <input type="password" name="txtPassword" value="" />(e.g 6 - 20 chars)<br/>
            <c:if test="${not empty errors.passwordLengtherr}">
                <font color="red">
                ${errors.passwordLengtherr}
                </font><br/>
            </c:if> 

            Confirm* <input type="password" name="txtConfirm" value="" /><br/>
            <c:if test="${not empty errors.confirmNotMatchPassword}">
                <font color="red">
                ${errors.confirmNotMatchPassword}
                </font><br/>
            </c:if> 

            Full name* <input type="text" name="txtFullname" value="${param.txtFullname}" />(e.g 2 - 50 chars)<br/>
            <c:if test="${not empty errors.fullnameLengtherr}">
                <font color="red">
                ${errors.fullnameLengtherr}
                </font><br/>
            </c:if> 

            <input type="submit" value="Create New Account" name="btAction" />
            <input type="reset" value="Reset" />
        </form>
        <c:if test="${not empty errors.usernameIsExisted}">
            <font color="red">
            ${errors.usernameIsExisted}
            </font><br/>
        </c:if> 
    </body>
</html>
