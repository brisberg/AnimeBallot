<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ren" uri="/WEB-INF/RenderLibDescriptor.tld" %>

<c:if test="${flashMessage != null}">
    <div class="message">${flashMessage}</div>
</c:if>

<div class="row" style="padding-top: 8px">
    <div class="col-md-8" style="font-size: 16px; font-weight: bold">
        Find, edit, and create Series
    </div>
    <div class="col-md-4">
        <a href="<c:url value="/admin/series/create" />" class="pull-right btn btn-default" style="padding: 0 10px">
            Create New Series &raquo
        </a>
    </div>
</div>
<div class="row">
    <div class="col-md-12">
        <ren:filterGrid url="/admin/series/list" filterDisplays="${filterDisplays}"/>
    </div>
</div>
<div>
    <table class="table" style="background:#DDDDDD">
        <thead>
        <tr>
            <ren:sortableHeader url="/admin/series/list" property="title" title="Title"/>
            <ren:sortableHeader url="/admin/series/list" property="season" title="Season"/>
            <ren:sortableHeader url="/admin/series/list" property="startDate" title="Start Date"/>
            <ren:sortableHeader url="/admin/series/list" property="endDate" title="End Date"/>
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
                       href="<c:url value="/admin/series/${series.id}/episode/list" />">Episodes</a>
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
    <ren:paginate url="/admin/series/list" totalCount="${seriesCount}"/>
</div>