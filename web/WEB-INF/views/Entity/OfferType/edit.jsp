<%@include file="../../../parts/header.jspf"%>
<%@page pageEncoding="UTF-8"%>
<%@page session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
    <h2>Administrace typů inzerátu</h2>
    <p>
        Editace typu inzerátu:
    </p>
    <div>
        <f:form action="save.htm" commandName="formModel" method="post">
        <fieldset>
            <legend>Formulář pro editaci</legend>
            <div class="editor-label">Název typu</div>
            <f:hidden path="id" />
            <f:input path="name"/>
            <f:errors path="name" cssClass="error"/>
            <div class="editor-label">Popis typu</div>
            <f:textarea cols="40" rows="5" path="description"/>
            <f:errors path="description" cssClass="error"/>
        </fieldset>
        <p><input type="submit" /></p>
        </f:form>
    </div>

<%@include file="../../../parts/footer.jspf"%>