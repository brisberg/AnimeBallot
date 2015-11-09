<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
<div>
    <table class="table" style="background:#DDDDDD">
        <thead>
        <tr>
            <th>Season</th>
            <th>User</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="ballot" items="${ballotList}" varStatus="rowCounter">
            <tr class="${rowCounter.count % 2 == 0 ? 'even' : 'odd'}">
                <td>
                    <a href="<c:url value="/admin/ballot/show/${ballot.id}" />">${ballot.season.title}</a>
                </td>
                <td>
                    ${ballot.user.name}
                </td>
                <td style="white-space: nowrap">
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
</div>