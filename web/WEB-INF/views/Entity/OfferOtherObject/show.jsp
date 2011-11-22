<%@include file="../../../parts/header.jspf"%>
<%@page pageEncoding="UTF-8"%>
<%@page session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <h2>Administrace objektů inzerátu</h2>
    <a href="add.htm">Přidat nový objekt</a>
    <p>
        Seznam dostupných objektů:
    </p>
    <table>
        <thead>
          <tr>
              <th>ID</th><th>NAME</th><th>POPIS</th><th>CENA</th><th>KATEGORIE</th><th colspan="2">EDITACE</th>
          </tr>
        </thead>
        <tbody>
            <c:forEach items="${allObjects}" var="object">
                <tr>
                    <td>#${object.id}</td>
                    <td>${object.name}</td>
                    <td>${object.description}</td>
                    <td>${object.price}</td>
                    <td>${object.offerOtherObjectCategory.name}</td>
                    
                    <td><a href="edit.htm?id=${object.id}">EDIT</a></td>
                    <td><a href="delete.htm?id=${object.id}">DEL</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
<%@include file="../../../parts/footer.jspf"%>