<%@include file="../../../parts/header.jspf"%>
<%@page pageEncoding="UTF-8"%>
<%@page session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
    <h2>Administrace karet</h2>
    <p>
        Přidání nové karty:
    </p>
    <div>
        <f:form action="submit.htm" commandName="formModel" method="post" enctype="multipart/form-data">
        <fieldset>
            <legend>Formulář pro přidání</legend>
            <div class="editor-label">Jméno</div>
            <f:input path="name"/>
            <f:errors path="name" cssClass="error"/>
            <div class="editor-label">Popis</div>
            <f:input path="description"/>
            <f:errors path="description" cssClass="error"/>
            <div class="editor-label">Cena</div>
            <f:input path="price"/>
            <f:errors path="price" cssClass="error"/>
            <div class="editor-label">Kategorie</div>
                <f:select path="idOtherObjectCategory">
                    <f:option label="Vyberte možnost" value="" />
                    <c:forEach items="${listOtherObjectCategories}" var="category" >
                        <c:choose>
                            <c:when test="${category['key'] == 'parent'}">
                                <f:option label="${category['value']}" value="${category['key']}" disabled="true"/>
                            </c:when>
                            <c:when test="${category['key'] != 'parent'}">
                                <f:option label="${category['value']}" value="${category['key']}"/>
                            </c:when>
                        </c:choose>
                    </c:forEach>
                </f:select>
            <f:errors path="idOtherObjectCategory" cssClass="error"/>
        </fieldset>
        <p><input type="submit" /></p>
        </f:form>
    </div>
        

<%@include file="../../../parts/footer.jspf"%>