<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${flashMessage != null}">
    <div class="message">${flashMessage}</div>
</c:if>

<table class="table">
    <tr>
        <td>Week Start Date:</td>
        <td>${configuration.weekStartTime}</td>
    </tr>
    <tr>
        <td>Current Season:</td>
        <td>${configuration.currentSeason.title}</td>
    </tr>
    <tr>
        <td>Week Index:</td>
        <td>${configuration.currentWeekIndex}</td>
    </tr>
</table>
<a href="<c:url value='/admin/home' />" class="btn btn-default">Back</a>
<a href="<c:url value='/admin/configuration/edit' />" class="btn btn-default">Edit</a>