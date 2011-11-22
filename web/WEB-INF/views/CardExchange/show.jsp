<%@include file="../../parts/header.jspf"%>
<%@page pageEncoding="UTF-8"%>
<%@page session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
    <h2>Burza karet</h2>
    <p><a href="add.htm?${p_exchange}">Přidej inzerát</a></p>
    <p>
        Typ nabídky:
    </p>
    <div>
        Zobrazit: <a href="show.htm?${p_exchange}${p_category}">VŠE</a> <c:forEach items="${allTypes}" var="type"> | <a href="filter.htm?${exchange}type=${type.id}&${p_category}">${type.name}</a></c:forEach>
    </div>
    <p>
        Kategorie:
    </p>
    <div>
        <a href="show.htm?${p_type}">VŠE</a>
        <c:forEach items="${allCategories}" var="categoryl1">
            <c:choose>
                <c:when test="${categoryl1.cardCategory == null}">
                   <ul><a href="filter.htm?${p_exchange}${p_type}category=${categoryl1.id}">${categoryl1.name}</a>                        
                            <c:forEach items="${allCategories}" var="categoryl2">
                                <c:choose>
                                <c:when test="${null != categoryl2.cardCategory && categoryl1.id == categoryl2.cardCategory.id}">
                                   <li> <a href="filter.htm?${p_exchange}${p_type}category=${categoryl2.id}">${categoryl2.name}</a></li>
                                </c:when>
                                </c:choose>
                            </c:forEach>                    
                       </ul>
                </c:when>
            </c:choose>
        </c:forEach>
    </div>
    <p>Inzeráty:</p>
    <div>
        
        <c:forEach items="${allOffers}" var="offer">
            <table>
                <thead>
                    <tr>
                        <th>OBRÁZEK</th><th>NÁZEV</th><th>DATUM</th><th>TYP</th><th>TEXT</th><th>INFORMACE O KARTĚ</th><th>VLOŽENO</th><th>AKCE</th><th colspan="2"></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><img style="height: 50px; width: 90px;" src="<c:choose><c:when test="${offer.offerObject.picture == true}">/BurzaMutabene/images/download.htm?id=${offer.offerObject.filePicture.id}</c:when><c:when test="${offer.offerObject.picture == false}">/BurzaMutabene/themes/noimage.png</c:when></c:choose>" /></td><td>${offer.title}</td><td>${offer.dateOfInsert}</td><td>${offer.offerType.name}</td><td>${offer.text}</td><td>Název: ${offer.offerObject.name} Popis: ${offer.offerObject.description} Cena: ${offer.offerObject.price} </td><td>${offer.user.email}</td><td><a href="respond.htm?id=${offer.id}">Odpovědět</a></td><td><a href="edit.htm?id=${offer.id}">EDIT</a></td><td><a href="delete.htm?id=${offer.id}">DEL</a></td>
                    </tr>
                </tbody>
            
            </table>
        </c:forEach>
        
    </div>
        

        <%@include file="../../parts/footer.jspf"%>