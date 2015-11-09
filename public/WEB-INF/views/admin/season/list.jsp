<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${flashMessage != null}">
    <div class="message">${flashMessage}</div>
</c:if>


<div class="row" style="padding-top: 8px">
    <div class="col-md-6" style="font-size: 16px; font-weight: bold">
        Find, edit, and create Seasons
    </div>
    <div class="col-md-6">
        <a href="<c:url value="/admin/season/create" />" class="pull-right btn btn-default" style="padding: 0px 10px">
            Create New Season &raquo
        </a>
    </div>
</div>
<div>
    <table class="table" style="background:#DDDDDD">
        <thead>
        <tr>
            <th>Title</th>
            <th>Start</th>
            <th>End</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="season" items="${seasonList}" varStatus="rowCounter">
            <tr class="${rowCounter.count % 2 == 0 ? 'even' : 'odd'}">
                <td>
                    <a href="<c:url value="/admin/season/show/${season.id}" />">${season.title}</a>
                </td>
                <td>
                    <fmt:formatDate pattern="MM-dd-yyyy"
                                    value="${season.startDate}" />
                </td>
                <td>
                    <fmt:formatDate pattern="MM-dd-yyyy"
                                    value="${season.endDate}" />
                </td>
                <td style="white-space: nowrap">
                    <a class="btn btn-default" style="padding: 0px 10px"
                       href="<c:url value="/admin/season/edit/${survey.id}" />">Edit</a>
                    <a class="btn btn-default" style="padding: 0px 10px"
                       href="<c:url value="/admin/season/delete/${survey.id}" />">Delete</a>
                </td>
            </tr>
        </c:forEach>
        <c:if test="${empty seasonList}">
            <tr>
                <td colspan="999">No seasons found</td>
            </tr>
        </c:if>
        </tbody>
    </table>
</div>