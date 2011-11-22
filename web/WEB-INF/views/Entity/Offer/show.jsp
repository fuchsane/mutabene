<%@include file="../../../parts/header.jspf"%>
<%@page pageEncoding="UTF-8"%>
<%@page session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <h2>Administrace inzerátů</h2>
    <a href="add.htm">Přidat nový inzerát</a>
    <p>
        Seznam dostupných inzerátů:
    </p>
    <table>
        <thead>
          <tr>
              <th>ID</th><th>TITULEK</th><th>TEXT</th><th>DATUM</th><th>UŽIVATEL</th><th>TYP</th><th>STATUS</th><th>VĚC</th><th colspan="2">EDITACE</th>
          </tr>
        </thead>
        <tbody>
            <c:forEach items="${allOffers}" var="offer">
                <tr>
                    <td>#${offer.id}</td>
                    <td>${offer.title}</td>
                    <td>${offer.text}</td>
                    <td>${offer.dateOfInsert}</td>
                    <td>${offer.user.email}</td>
                    <td>${offer.offerType.name}</td>
                    <td>${offer.offerState.name}</td>
                    <td>${offer.offerObject.name}</td>
                    <td><a href="edit.htm?id=${offer.id}">EDIT</a></td>
                    <td><a href="delete.htm?id=${offer.id}">DEL</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
<%@include file="../../../parts/footer.jspf"%>