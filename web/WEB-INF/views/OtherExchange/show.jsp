<%@include file="../../parts/header.jspf"%>
<%@page pageEncoding="UTF-8"%>
<%@page session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
    <h2>Burza věcí</h2>
    <p>
        Typ nabídky:
    </p>
   <div>
        Zobrazit: <a href="show.htm">VŠE</a> <c:forEach items="${allTypes}" var="type">| <a href="filter.htm?exchange=other&type=${type.id}">${type.name}</a></c:forEach>
    </div>
    <p>
        Kategorie:
    </p>
    <div>
        <a href="#">VŠE</a>
        <c:forEach items="${allCategories}" var="categoryl1">
            <c:choose>
                <c:when test="${categoryl1.offerOtherObjectCategory == null}">
                   <ul><a href="filter.htm?exchange=other&${type_p}category=${categoryl1.id}">${categoryl1.name}</a>                        
                            <c:forEach items="${allCategories}" var="categoryl2">
                                <c:choose>
                                <c:when test="${null != categoryl2.offerOtherObjectCategory && categoryl1.id == categoryl2.offerOtherObjectCategory.id}">
                                   <li> <a href="filter.htm?exchange=other&${type_p}category=${categoryl2.id}">${categoryl2.name}</a></li>
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
            <tr>
                <th>${offer}</th>
            </tr>
            </table>
        </c:forEach>
        
    </div>
        

<%@include file="../../parts/footer.jspf"%>