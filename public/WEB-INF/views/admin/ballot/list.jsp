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
    <div class="col-md-6" style="font-size: 16px; font-weight: bold">
        Find, edit, and create Ballots
    </div>
    <div class="col-md-6">
        <a href="<c:url value="/admin/ballot/create" />" class="pull-right btn btn-default" style="padding: 0px 10px">
            Create New Ballot &raquo
        </a>
    </div>
</div>
<div class="row">
    <div class="col-md-12">
        <ren:filterGrid url="/admin/ballot/list" filterDisplays="${filterDisplays}"/>
    </div>
</div>
<div>
    <table class="table" style="background:#DDDDDD">
        <thead>
        <tr>
            <ren:sortableHeader url="/admin/ballot/list" property="season" title="Season"/>
            <ren:sortableHeader url="/admin/ballot/list" property="user" title="User"/>
            <ren:sortableHeader url="/admin/ballot/list" property="dateCreated" title="Date Created"/>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="ballot" items="${ballotList}" varStatus="rowCounter">
            <tr class="${rowCounter.count % 2 == 0 ? 'even' : 'odd'}">
                <td>
                        ${ballot.season.title}
                </td>
                <td>
                        ${ballot.user.name}
                </td>
                <td>
                    <fmt:formatDate pattern="MM-dd-yyyy HH:mm"
                                    value="${ballot.dateCreated}"/>
                </td>
                <td style="white-space: nowrap">
                    <a class="btn btn-default" style="padding: 0px 10px"
                       href="<c:url value="/admin/ballot/show/${ballot.id}" />">Show</a>
                    <a class="btn btn-default" style="padding: 0px 10px"
                       href="<c:url value="/admin/ballot/edit/${ballot.id}" />">Edit</a>
                    <a class="btn btn-default" style="padding: 0px 10px"
                       href="<c:url value="/admin/ballot/delete/${ballot.id}" />">Delete</a>
                </td>
            </tr>
        </c:forEach>
        <c:if test="${empty ballotList}">
            <tr>
                <td colspan="999">No ballots found</td>
            </tr>
        </c:if>
        </tbody>
    </table>
    <ren:paginate url="/admin/ballot/list" totalCount="${ballotCount}"/>
</div>