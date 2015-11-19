<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:if test="${flashMessage != null}">
    <div class="message">${flashMessage}</div>
</c:if>


<div class="row" style="padding-top: 8px">
    <div class="col-md-4" style="font-size: 16px; font-weight: bold">
        Find, edit, and create Series
    </div>
    <div class="col-md-4">
        <select path="season">
            <c:forEach var="season" items="${seasonList}">
                <option value="${season.id}">${season.title}</option>
            </c:forEach>
        </select>
    </div>
    <div class="col-md-4">
        <a href="<c:url value="/admin/series/create" />" class="pull-right btn btn-default" style="padding: 0px 10px">
            Create New Series &raquo
        </a>
    </div>
</div>
<div>
    <table class="table" style="background:#DDDDDD">
        <thead>
        <tr>
            <th>Title</th>
            <th>Season</th>
            <th>Start</th>
            <th>End</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="series" items="${seriesList}" varStatus="rowCounter">
            <tr class="${rowCounter.count % 2 == 0 ? 'even' : 'odd'}">
                <td>
                    <a href="<c:url value="/admin/series/show/${series.id}" />">${series.title}</a>
                </td>
                <td>
                        ${series.season.title}
                </td>
                <td>
                    <fmt:formatDate pattern="MM-dd-yyyy"
                                    value="${series.startDate}"/>
                </td>
                <td>
                    <fmt:formatDate pattern="MM-dd-yyyy"
                                    value="${series.endDate}"/>
                </td>
                <td style="white-space: nowrap">
                    <a class="btn btn-default" style="padding: 0px 10px"
                       href="<c:url value="/admin/series/edit/${series.id}" />">Edit</a>
                    <a class="btn btn-default" style="padding: 0px 10px"
                       href="<c:url value="/admin/series/delete/${series.id}" />">Delete</a>
                </td>
            </tr>
        </c:forEach>
        <c:if test="${empty seriesList}">
            <tr>
                <td colspan="999">No series found</td>
            </tr>
        </c:if>
        </tbody>
    </table>
</div>