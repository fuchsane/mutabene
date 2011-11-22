<%@include file="../../parts/header.jspf"%>
<%@page pageEncoding="UTF-8"%>
<%@page session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
    <h2>Administrace katalogu</h2>
    <a href="add.htm">Přidat novou kartu</a>
    <p>
        Kategorie:
    </p>
    <div>
        <a href="show.htm">VŠE</a>
        <c:forEach items="${allCategories}" var="categoryl1">
            <c:choose>
                <c:when test="${categoryl1.cardCategory == null}">
                   <ul><a href="filter.htm?category=${categoryl1.id}">${categoryl1.name}</a>                        
                            <c:forEach items="${allCategories}" var="categoryl2">
                                <c:choose>
                                <c:when test="${null != categoryl2.cardCategory && categoryl1.id == categoryl2.cardCategory.id}">
                                   <li> <a href="filter.htm?category=${categoryl2.id}">${categoryl2.name}</a></li>
                                </c:when>
                                </c:choose>
                            </c:forEach>                    
                       </ul>
                </c:when>
            </c:choose>
        </c:forEach>
    </div>
    <p>
        Seznam dostupných karet:
    </p>
    <table>
        <thead>
          <tr>
              <th>OBRÁZEK</th><th>NAME</th><th>CENA</th><th>DETAIL</th><th colspan="2"></th>
          </tr>
        </thead>
        <tbody>
            <c:forEach items="${allCards}" var="card">
                <tr>
                    <td>
                        <img style="width: 120px; height: 90px" src="<c:choose><c:when test="${card.picture == true}">/BurzaMutabene/admin/filePicture/download.htm?id=${card.filePicture.id}</c:when><c:when test="${card.picture == false}">/BurzaMutabene/themes/noimage.png</c:when></c:choose>"/>
                    </td>
                    <td>${card.name}</td>
                    <td>${card.price}</td>
                    <td><a href="detail.htm?id=${card.id}">DETAIL</a></td>
                    <td><a href="edit.htm?id=${card.id}">EDIT</a></td>
                    <td><a href="delete.htm?id=${card.id}">DEL</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
<%@include file="../../parts/footer.jspf"%>