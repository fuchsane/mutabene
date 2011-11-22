<%@include file="../../../parts/header.jspf"%>
<%@page pageEncoding="UTF-8"%>
<%@page session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <h2>Administrace kategorií karet</h2>
    <a href="add.htm">Přidat novou kategorii</a>
    <p>
        Seznam dostupných kategorií karet:
    </p>
    <table>
        <thead>
          <tr>
              <th>ID</th><th>NÁZEV KATEGORIE</th><th>NADŘAZENA KATEGORIE</th><th>POPIS</th><th colspan="2">EDITACE</th>
          </tr>
        </thead>
        <tbody>
            <c:forEach items="${listCardCategories}" var="category">
                <tr>
                    <td>#${category.id}</td>
                    <td>${category.name}</td>
                    <td>${category.cardCategory.name}</td>
                    <td>${category.description}</td>
                    <td><a href="edit.htm?id=${category.id}">EDIT</a></td>
                    <td><a href="delete.htm?id=${category.id}">DEL</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

<%@include file="../../../parts/footer.jspf"%>