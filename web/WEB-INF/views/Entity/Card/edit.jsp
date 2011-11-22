<%@include file="../../../parts/header.jspf"%>
<%@page pageEncoding="UTF-8"%>
<%@page session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
    <h2>Administrace karet</h2>
    <p>
        Editace karty:
    </p>
    <div>
  <f:form action="save.htm" commandName="formModel" method="post" enctype="multipart/form-data">
        <fieldset>
            <legend>Formulář pro editaci</legend>
            <div class="editor-label">Jméno</div>
            <f:input path="name"/>
            <f:errors path="name" cssClass="error"/>
            <div class="editor-label">Popis</div>
            <f:input path="description"/>
            <f:errors path="description" cssClass="error"/>
            <div class="editor-label">Cena</div>
            <f:input path="price"/>
            <f:errors path="price" cssClass="error"/>
            <div class="editor-label">S/N</div>
            <f:input path="serialNumber"/>
            <f:errors path="serialNumber" cssClass="error"/>
            <div class="editor-label">Kategorie</div>
                <f:select path="idCardCategory">
                    <f:option label="Vyberte možnost" value="" />
                    <c:forEach items="${listCardCategories}" var="category" >
                                <c:choose>
                                    <c:when test="${category['key'] == formModel.idCardCategory}">
                                        <f:option label="${category['value']}" value="${category['key']}" selected="true"/>
                                    </c:when>
                                    <c:when test="${category['key'] != formModel.idCardCategory}">
                                        <f:option label="${category['value']}" value="${category['key']}"/>
                                    </c:when>
                                
                                </c:choose>
                    </c:forEach>
                </f:select>
            <f:errors path="idCardCategory" cssClass="error"/>
            <div class="editor-label">Obrazek</div>
            <f:input path="image" type="file" />
            <f:errors path="image" cssClass="error" />
            <c:choose>
                <c:when test="${formModel.picture == true}">
                    <div class="editor-label">Obrazek</div>
                    <img src="/BurzaMutabene/admin/filePicture/download.htm?id=${formModel.idFilePicture}"/>
                    <div class="editor-label">Odstranit Obrazek</div>
                    <f:checkbox path="delete" value="${formModel.delete}"/>
                    <f:errors path="delete" cssClass="error" />
                </c:when>
            </c:choose>
            <f:hidden path="id"/>
            <f:hidden path="idFilePicture"/>
        </fieldset>
        <p><input type="submit" /></p>
        </f:form>
    </div>

<%@include file="../../../parts/footer.jspf"%>