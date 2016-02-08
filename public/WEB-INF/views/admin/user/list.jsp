<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ren" uri="/WEB-INF/RenderLibDescriptor.tld" %>

<div class="row">
    <div class="col-md-12">
        <ren:filterGrid url="/admin/user/list" filterDisplays="${filterDisplays}"/>
    </div>
</div>
<div>
    <table class="table" style="background:#DDDDDD">
        <thead>
        <tr>
            <ren:sortableHeader url="/admin/user/list" property="email" title="Email"/>
            <ren:sortableHeader url="/admin/user/list" property="firstName" title="First Name"/>
            <ren:sortableHeader url="/admin/user/list" property="lastName" title="Last Name"/>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${userList}" varStatus="rowCounter">
            <tr class="${rowCounter.count % 2 == 0 ? 'even' : 'odd'}">
                <td>
                    <a href="<c:url value="/admin/user/show/${user.id}" />">${user.email}</a>
                </td>
                <td>
                        ${user.firstName}
                </td>
                <td>
                        ${user.lastName}
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
    <ren:paginate url="/admin/user/list" totalCount="${userCount}"/>
</div>