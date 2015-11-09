<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${flashMessage != null}">
    <div class="message">${flashMessage}</div>
</c:if>

<table class="table">
    <tr>
        <td>Title:</td>
        <td>${series.title}</td>
    </tr>
    <tr>
        <td>Season:</td>
        <td>${series.season.title}</td>
    </tr>
    <tr>
        <td>Start Date:</td>
        <td>${series.startDate}</td>
    </tr>
    <tr>
        <td>End Date:</td>
        <td>${series.endDate}</td>
    </tr>
</table>

<table class="table" style="background:#DDDDDD">
    <!-- TODO add a print out of the series in this season -->
</table>

<div class="botButtons">
    <a href="<c:url value="/admin/series/edit/${series.id}" />" class="btn btn-default">Edit</a>
</div>
