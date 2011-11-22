<%@include file="../../../parts/header.jspf"%>
<%@page pageEncoding="UTF-8"%>
<%@page session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
    <h2>Administrace obrázků</h2>
    <p>
        Editace obrázku:
    </p>
    <div>
        <div><p>Název souboru: <b>${formModel.name}</b> Velikost: ${formModel.size} bytů</p></div>
        <img style="width: 150px; height: 110px;" src="download.htm?id=${formModel.id}" />
        
        <f:form action="save.htm" commandName="formModel" method="post" enctype="multipart/form-data">
        <fieldset>
            <legend>Formulář pro editaci</legend>
            <div class="editor-label">Soubor s obrázkem:</div>          
            <f:input path="filePicture" type="file" />
            <f:errors path="filePicture" cssClass="error" />
            <f:hidden path="id"/>
        </fieldset>
        <p><input type="submit" /></p>
        </f:form>
    </div>
        

<%@include file="../../../parts/footer.jspf"%>