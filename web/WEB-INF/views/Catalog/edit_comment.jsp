<%@include file="../../parts/header.jspf"%>
<%@page pageEncoding="UTF-8"%>
<%@page session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
    <h2>Administrace komentářů karet</h2>
    <p>
        Editace komentáře karty:
    </p>
    <div>
       <f:form action="saveComment.htm" commandName="formModel" method="post">
                <fieldset>
            <legend>Formulář pro přidání</legend>
            <div class="editor-label">Komentář</div>
            <f:textarea path="text"/>
            <f:errors path="text" cssClass="error"/>
            <f:hidden path="idCard" />
            <f:hidden path="idUser" />
            <f:hidden path="id"/>
        </fieldset>
        <p><input type="submit" /></p>
        </f:form>
    </div>

<%@include file="../../parts/footer.jspf"%>