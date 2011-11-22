<%@include file="../../../parts/header.jspf"%>
<%@page pageEncoding="UTF-8"%>
<%@page session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
    <h2>Administrace komentářů karet</h2>
    <p>
        Přidání nového komentáře karty:
    </p>
    <div>
        <f:form action="submit.htm" commandName="formModel" method="post">
        <fieldset>
            <legend>Formulář pro přidání</legend>
            <div class="editor-label">Komentář</div>
            <f:textarea path="text"/>
            <f:errors path="text" cssClass="error"/>
            <div class="editor-label">Karta</div>
                <f:select path="idCard">
                    <f:option label="Vyberte možnost" value="" />
                    <c:forEach items="${listCards}" var="card" >
                        <f:option label="${card['value']}" value="${card['key']}"/>
                    </c:forEach>
                </f:select>
            <f:errors path="idCard" cssClass="error"/>
            <div class="editor-label">Autor</div>
                <f:select path="idUser">
                    <f:option label="Vyberte možnost" value="" />
                    <c:forEach items="${listUsers}" var="user" >
                        <f:option label="${user['value']}" value="${user['key']}"/>
                    </c:forEach>
                </f:select>
            <f:errors path="idUser" cssClass="error"/>
        </fieldset>
        <p><input type="submit" /></p>
        </f:form>
    </div>
        

<%@include file="../../../parts/footer.jspf"%>