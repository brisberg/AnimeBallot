<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div>
    <table class="table" style="background:#DDDDDD">
        <thead>
        <tr>
            <th>Name</th>
            <th>Email</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${userList}" varStatus="rowCounter">
            <tr class="${rowCounter.count % 2 == 0 ? 'even' : 'odd'}">
                <td>
                    <a href="<c:url value="/admin/user/show/${user.id}" />">${user.name}</a>
                </td>
                <td>
                        ${user.email}
                </td>
                <td style="white-space: nowrap">
                    <a class="btn btn-default" style="padding: 0px 10px"
                       href="<c:url value="/admin/user/edit/${user.id}" />">Edit</a>
                    <a class="btn btn-default" style="padding: 0px 10px"
                       href="<c:url value="/admin/user/delete/${user.id}" />">Delete</a>
                </td>
            </tr>
        </c:forEach>
        <c:if test="${empty userList}">
            <tr>
                <td colspan="999">No users found</td>
            </tr>
        </c:if>
        </tbody>
    </table>
</div>