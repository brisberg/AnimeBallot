<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${flashMessage != null}">
    <div class="message">${flashMessage}</div>
</c:if>

<table class="table">
    <tr>
        <td>Title:</td>
        <td>${episode.title}</td>
    </tr>
</table>

<div class="botButtons">
    <a href="<c:url value="/admin/episode/edit/${episode.id}" />" class="btn btn-default">Edit</a>
</div>
