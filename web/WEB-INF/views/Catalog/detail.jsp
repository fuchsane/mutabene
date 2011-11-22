<%@include file="../../parts/header.jspf"%>
<%@page pageEncoding="UTF-8"%>
<%@page session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
    <h2>Administrace katalogu</h2>
   
    <p>
        Detail karty:
    </p>
    <a href="show.htm">Seznam karet</a>
    <table>
        <thead>
          <tr>
              <th>OBRÁZEK</th><th>NAME</th><th>POPIS</th><th>S/N</th><th>CENA</th><th colspan="2"></th>
          </tr>
        </thead>
        <tbody>
                <tr>
                    <td>
                        <img style="width: 120px; height: 90px" src="<c:choose><c:when test="${card.picture == true}">/BurzaMutabene/admin/filePicture/download.htm?id=${card.filePicture.id}</c:when><c:when test="${card.picture == false}">/BurzaMutabene/themes/noimage.png</c:when></c:choose>"/>
                    </td>
                    <td>${card.name}</td>
                    <td>${card.description}</td>
                    <td>${card.serialNumber}</td>
                    <td>${card.price}</td>
                    <td><a href="edit.htm?id=${card.id}">EDIT</a></td>
                    <td><a href="delete.htm?id=${card.id}">DEL</a></td>
                </tr>
        </tbody>
    </table>
    <p>
        Komentáře karty:
    </p>
    <f:form action="submitComment.htm" commandName="formModel" method="post">
        <fieldset>
            <legend>Formulář pro přidání</legend>
            <div class="editor-label">Komentář</div>
            <f:textarea path="text"/>
            <f:errors path="text" cssClass="error"/>
            <f:hidden path="idCard"/>
        </fieldset>
        <p><input type="submit" /></p>
        </f:form>
    
  <c:forEach items="${allComments}" var="comment">
        <table>
        <thead>
            <tr>
                <th>${comment.user.email}</th><th>${comment.dateOfComment}</th><th colspan="2"></th>
            </tr>
        </thead>
        <tbody>
            <tr>
        <td colspan="2">${comment.text}</td>
        <td><a href="editComment.htm?id=${comment.id}">EDIT</a></td>      
        <td><a href="deleteComment.htm?id=${comment.id}">DEL</a></td>
            </tr>
        </tbody>
    </table>
    </c:forEach>

<%@include file="../../parts/footer.jspf"%>