<%@include file="../../../parts/header.jspf"%>
<%@page pageEncoding="UTF-8"%>
<%@page session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <h2>Administrace typu inzerátu</h2>
    <a href="add.htm">Přidat nový typ</a>
    <p>
        Seznam dostupných typů inzerátu:
    </p>
    
    <table>
        <thead>
          <tr>
              <th>ID</th><th>NÁZEV TYPU</th><th>POPIS</th><th colspan="2">EDITACE</th>
          </tr>
        </thead>
        <tbody>
            <c:forEach items="${allTypes}" var="type">
                <tr>
                    <td>#${type.id}</td><td>${type.name}</td><td>${type.description}</td><td><a href="edit.htm?id=${type.id}">EDIT</a></td><td><a href="delete.htm?id=${type.id}">DEL</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

<%@include file="../../../parts/footer.jspf"%>