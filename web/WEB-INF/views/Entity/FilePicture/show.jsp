<%@include file="../../../parts/header.jspf"%>
<%@page pageEncoding="UTF-8"%>
<%@page session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <h2>Administrace obrázků</h2>
    <a href="add.htm">Přidat novoý obrázek</a>
    <p>
        Seznam dostupných obrázků:
    </p>
    <table>
        <thead>
          <tr>
              <th>ID</th><th>NÁZEV SOUBORU</th><th>VELIKOST</th><th>DOWNLOAD</th><th colspan="2">EDITACE</th>
          </tr>
        </thead>
        <tbody>
            <c:forEach items="${allFiles}" var="file">
                <tr>
                    <td>#${file.id}</td><td>${file.name}</td><td>${file.fileSize}</td><td><a href="download.htm?id=${file.id}">LINK</a></td><td><a href="edit.htm?id=${file.id}">EDIT</a></td><td><a href="delete.htm?id=${file.id}">DEL</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

<%@include file="../../../parts/footer.jspf"%>