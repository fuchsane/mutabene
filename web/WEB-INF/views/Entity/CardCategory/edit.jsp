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
            <legend>Formulář pro editaci</legend>
            <div class="editor-label">Název kategorie</div>
            <f:input path="name"/>
            <f:errors path="name" cssClass="error"/>
            <div class="editor-label">Popis kategorie</div>
            <f:textarea path="description"/>
            <f:errors path="description" cssClass="error"/>
            <div class="editor-label">Nadřazena kategorie</div>
                <f:select path="idCardCategory">
                    <f:option label="Vyberte možnost" value="" />
                    <c:forEach items="${listCardCategories}" var="category" >
                        <c:choose>
                            <c:when test="${category['key'] == formModel.idCardCategory}" >
                                <f:option label="${category['value']}" value="${category['key']}" selected="true" />
                            </c:when>
                            <c:when test="${category['key'] != formModel.idCardCategory}" >
                                <f:option label="${category['value']}" value="${category['key']}"/>
                            </c:when>
                        </c:choose>   
                    </c:forEach>
                </f:select>
            <f:errors path="idCardCategory" cssClass="error"/>
            <f:hidden path="id"/>
        </fieldset>
        <p><input type="submit" /></p>
        </f:form>
    </div>

<%@include file="../../../parts/footer.jspf"%>