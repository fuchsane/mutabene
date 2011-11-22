<%@include file="../../../parts/header.jspf"%>
<%@page pageEncoding="UTF-8"%>
<%@page session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
    <h2>Administrace obrázků</h2>
    <p>
        Přidání nového obrázku:
    </p>
    <div>
        <f:form action="submit.htm" commandName="formModel" method="post" enctype="multipart/form-data">
        <fieldset>
            <legend>Formulář pro přidání</legend>
            <div class="editor-label">Soubor s obrázkem:</div>
            <f:input path="filePicture" type="file" />
            <f:errors path="filePicture" cssClass="error" />
        </fieldset>
        <p><input type="submit" /></p>
        </f:form>
    </div>
        

<%@include file="../../../parts/footer.jspf"%>