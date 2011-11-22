<%@include file="../../../parts/header.jspf"%>
<%@page pageEncoding="UTF-8"%>
<%@page session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <h2>Administrace karet</h2>
    <a href="add.htm">Přidat novou kartu</a>
    <p>
        Seznam dostupných karet:
    </p>
    <table>
        <thead>
          <tr>
              <th>ID</th><th>NAME</th><th>POPIS</th><th>S/N</th><th>CENA</th><th>KATEGORIE</th><th>OBRÁZEK</th><th colspan="2">EDITACE</th>
          </tr>
        </thead>
        <tbody>
            <c:forEach items="${allCards}" var="card">
                <tr>
                    <td>#${card.id}</td>
                    <td>${card.name}</td>
                    <td>${card.description}</td>
                    <td>${card.serialNumber}</td>
                    <td>${card.price}</td>
                    <td>${card.cardCategoryEntity.name}</td>
                    <td>
                        <img style="width: 60px; height: 40px" 
                             src="
                             <c:choose>
                                 <c:when test="${card.picture == true}">
                                     /BurzaMutabene/admin/filePicture/download.htm?id=${card.filePicture.id}
                                 </c:when>
                                 <c:when test="${card.picture == false}">
                                         /BurzaMutabene/themes/noimage.png
                                 </c:when>
                             </c:choose>"/>
                    </td>
                    <td><a href="edit.htm?id=${card.id}">EDIT</a></td>
                    <td><a href="delete.htm?id=${card.id}">DEL</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
<%@include file="../../../parts/footer.jspf"%>