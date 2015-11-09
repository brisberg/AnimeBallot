<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${flashMessage != null}">
    <div class="message">${flashMessage}</div>
</c:if>

<table class="table">
    <tr>
        <td>Title:</td>
        <td>${season.title}</td>
    </tr>
    <tr>
        <td>Start Date:</td>
        <td>${season.startDate}</td>
    </tr>
    <tr>
        <td>End Date:</td>
        <td>${season.endDate}</td>
    </tr>
</table>

<table class="table" style="background:#DDDDDD">
    <!-- TODO add a print out of the series in this season -->
</table>

<div class="botButtons">
    <a href="<c:url value="/admin/season/edit/${season.id}" />" class="btn btn-default">Edit</a>
</div>
