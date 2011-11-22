<%@include file="../../../parts/header.jspf"%>
<%@page pageEncoding="UTF-8"%>
<%@page session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
    <h2>Administrace inzerátů</h2>
    <p>
        Přidání nového inzerátu:
    </p>
    <div>
        <f:form action="submit.htm" commandName="formModel" method="post" enctype="multipart/form-data">
        <fieldset>
            <legend>Formulář pro přidání</legend>
            <div class="editor-label">Titulek</div>
            <f:input path="title"/>
            <f:errors path="title" cssClass="error"/>
            <div class="editor-label">Text inzerátu</div>
            <f:textarea path="text"/>
            <f:errors path="text" cssClass="error"/>
            <div class="editor-label">Autor inzerátu</div>
                <f:select path="idUser">
                    <f:option label="Vyberte možnost" value="" />
                    <c:forEach items="${listUsers}" var="user" >
                                <f:option label="${user['value']}" value="${user['key']}"/>
                    </c:forEach>
                </f:select>
            <f:errors path="idUser" cssClass="error"/>
            <div class="editor-label">Typ inzerátu</div>
                <f:select path="idOfferType">
                    <f:option label="Vyberte možnost" value=""/>
                    <c:forEach items="${listOfferTypes}" var="type" >
                                <f:option label="${type['value']}" value="${type['key']}"/>
                    </c:forEach>
                </f:select>
            <f:errors path="idOfferType" cssClass="error"/>
            <div class="editor-label">Stav inzerátu</div>
                <f:select path="idOfferState">
                    <f:option label="Vyberte možnost" value=""/>
                    <c:forEach items="${listOfferState}" var="state" >
                                <f:option label="${state['value']}" value="${state['key']}"/>
                    </c:forEach>
                </f:select>
            <f:errors path="idOfferState" cssClass="error"/>
            <div class="editor-label">Věc inzerátu</div>
                <f:select path="idOfferObject">
                    <f:option label="Vyberte možnost" value=""/>
                    <c:forEach items="${listObjects}" var="object" >
                                <f:option label="${object['value']}" value="${object['key']}"/>
                    </c:forEach>
                </f:select>
            <f:errors path="idOfferObject" cssClass="error"/>
        </fieldset>
        <p><input type="submit" /></p>
        </f:form>
    </div>
        

<%@include file="../../../parts/footer.jspf"%>