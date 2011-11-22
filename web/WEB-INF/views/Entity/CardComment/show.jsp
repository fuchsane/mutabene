<%@include file="../../../parts/header.jspf"%>
<%@page pageEncoding="UTF-8"%>
<%@page session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <h2>Administrace komentářů karet</h2>
    <a href="add.htm">Přidat nový komentář</a>
    <p>
        Seznam dostupných komentářů karet:
    </p>
    <table>
        <thead>
          <tr>
              <th>ID</th><th>TEXT</th><th>KARTA</th><th>UŽIVATEL</th><th>DATUM</th><th colspan="2">EDITACE</th>
          </tr>
        </thead>
        <tbody>
            <c:forEach items="${listComments}" var="comment">
                <tr>
                    <td>#${comment.id}</td>
                    <td>${comment.text}</td>
                    <td>${comment.user.email}</td>
                    <td>${comment.card.name}</td>
                    <td>${comment.dateOfComment}</td>
                    <td><a href="edit.htm?id=${comment.id}">EDIT</a></td>
                    <td><a href="delete.htm?id=${comment.id}">DEL</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

<%@include file="../../../parts/footer.jspf"%>