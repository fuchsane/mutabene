<%@include file="../../../parts/header.jspf"%>
<%@page pageEncoding="UTF-8"%>
<%@page session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <h2>Administrace stavů inzerátu</h2>
    <a href="add.htm">Přidat nový stav</a>
    <p>
        Seznam dostupných stavů inzerátu:
    </p>
    
    <table>
        <thead>
          <tr>
              <th>ID</th><th>NÁZEV STAVU</th><th>POPIS</th><th colspan="2">EDITACE</th>
          </tr>
        </thead>
        <tbody>
            <c:forEach items="${allStates}" var="state">
                <tr>
                    <td>#${state.id}</td><td>${state.name}</td><td>${state.description}</td><td><a href="edit.htm?id=${state.id}">EDIT</a></td><td><a href="delete.htm?id=${state.id}">DEL</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

<%@include file="../../../parts/footer.jspf"%>