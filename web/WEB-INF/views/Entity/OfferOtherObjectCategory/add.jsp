<%@include file="../../../parts/header.jspf"%>
<%@page pageEncoding="UTF-8"%>
<%@page session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
    <h2>Administrace kategorií objektů inzerátu</h2>
    <p>
        Přidání nové kategorie objektu:
    </p>
    <div>
        <f:form action="submit.htm" commandName="formModel" method="post">
        <fieldset>
            <legend>Formulář pro přidání</legend>
            <div class="editor-label">Název kategorie</div>
            <f:input path="name"/>
            <f:errors path="name" cssClass="error"/>
            <div class="editor-label">Popis kategorie</div>
            <f:textarea path="description"/>
            <f:errors path="description" cssClass="error"/>
            <div class="editor-label">Nadřazena kategorie</div>
                <f:select path="idOtherObjectCategory">
                    <f:option label="Vyberte možnost" value="" />
                    <c:forEach items="${listOtherObjectCategories}" var="category" >
                        <f:option label="${category['value']}" value="${category['key']}"/>
                    </c:forEach>
                </f:select>
            <f:errors path="idOtherObjectCategory" cssClass="error"/>
        </fieldset>
        <p><input type="submit" /></p>
        </f:form>
    </div>
        

<%@include file="../../../parts/footer.jspf"%>