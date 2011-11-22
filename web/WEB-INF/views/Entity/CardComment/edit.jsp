<%@include file="../../../parts/header.jspf"%>
<%@page pageEncoding="UTF-8"%>
<%@page session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
    <h2>Administrace kategorií karet</h2>
    <p>
        Editace kategorie karty:
    </p>
    <div>
       <f:form action="save.htm" commandName="formModel" method="post">
                <fieldset>
            <legend>Formulář pro přidání</legend>
            <div class="editor-label">Komentář</div>
            <f:textarea path="text"/>
            <f:errors path="text" cssClass="error"/>
            <div class="editor-label">Karta</div>
                <f:select path="idCard">
                    <f:option label="Vyberte možnost" value="" />
                    <c:forEach items="${listCards}" var="card" >
                        <c:choose>
                            <c:when test="${card['key'] == formModel.idCard}">
                                <f:option label="${card['value']}" value="${card['key']}" selected="true" />
                            </c:when>
                            <c:when test="${card['key'] != formModel.idCard}">
                                <f:option label="${card['value']}" value="${card['key']}"/>
                            </c:when>
                        </c:choose>
                    </c:forEach>
                </f:select>
            <f:errors path="idCard" cssClass="error"/>
            <div class="editor-label">Autor</div>
                <f:select path="idUser">
                    <f:option label="Vyberte možnost" value="" />
                    <c:forEach items="${listUsers}" var="user" >
                        <c:choose>
                            <c:when test="${user['key'] == formModel.idUser}">
                                <f:option label="${user['value']}" value="${user['key']}" selected="true" />
                            </c:when>
                            <c:when test="${user['key'] != formModel.idUser}">
                                <f:option label="${user['value']}" value="${user['key']}"/>
                            </c:when>
                        </c:choose>
                    </c:forEach>
                </f:select>
            <f:errors path="idUser" cssClass="error"/>
            <f:hidden path="id"/>
        </fieldset>
        <p><input type="submit" /></p>
        </f:form>
    </div>

<%@include file="../../../parts/footer.jspf"%>