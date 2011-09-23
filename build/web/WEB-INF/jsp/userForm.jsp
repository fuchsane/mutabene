<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="org.springframework.security.core.context.SecurityContext"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <style type="text/css">
            .even {
                background-color: silver;
            }
        </style>
        <title>Registration Page</title>
    </head>
    <body>

        <form:form action="add.htm" commandName="user">
            <table>
                <tr>
                    <td>First Name :</td>
                    <td><form:input path="firstname" /></td>

                </tr>
                <tr>
                    <td>Last Name :</td>
                    <td><form:input path="surname" /></td>
                </tr>
                <tr>
                    <td>Password :</td>
                    <td><form:password path="password" /></td>
                </tr>
                <tr>
                    <td>Gender :</td>
                    <td><form:radiobutton path="gender" value="M" label="M" /> 
                        <form:radiobutton path="gender" value="F" label="F" />
                    </td>
                </tr>
                <tr>
                    <td>Email :</td>
                    <td><form:input path="email" /></td>
                </tr>
                <tr>
                    <td>Street :</td>
                    <td><form:input path="address.street" /></td>
                </tr>
                <tr>
                    <td>City :</td>
                    <td><form:input path="address.city" /></td>
                </tr>
                <tr>
                    <td></td>
                    <td><form:checkbox path="mailingList"
                                   label="Would you like to join our mailinglist?" /></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" value="Register"></td>
                </tr>
            </table>
        </form:form>
        <c:if test="${fn:length(userList) > 0}">
            <table cellpadding="5">
                <tr class="even">
                    <th>Name</th>
                    <th>Gender</th>
                    <th>Email</th>
                    <th>Street</th>
                    <th>City</th>
                </tr>
                <c:forEach items="${userList}" var="user" varStatus="status">
                    <tr class="<c:if test="${status.count % 2 == 0}">even</c:if>">
                        <td>${user.firstname} ${user.surname}</td>
                        <td>${user.gender}</td>
                        <td>${user.email}</td>
                        <td>${user.address.street}</td>
                        <td>${user.address.city}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
        <% if(request.getUserPrincipal() != null){}
            %>

        
    </body>
</html>