<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<style>
    .error {
        color: #ff0000;
    }
</style>

<c:if test="${flashMessage != null}">
    <div class="message">${flashMessage}</div>
</c:if>

<c:url var="saveUrl" value="/admin/week/save"/>
<form:form method="post" action="${saveUrl}">
    <form:hidden path="id"/>
    <table>
        <tr>
            <td>Title:</td>
            <td><form:input path="title" size="40"/></td>
            <td><form:errors path="title" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Start Date:</td>
            <td><form:input path="startDate" size="40"/></td>
            <td><form:errors path="startDate" cssClass="error"/></td>
        </tr>
        <tr>
            <td>End Date:</td>
            <td><form:input path="endDate" size="40"/></td>
            <td><form:errors path="endDate" cssClass="error"/></td>
        </tr>
    </table>

    <div class="botButtons">
        <input class="save" type="submit" value="Submit"/>
    </div>

    <form:hidden path="dateCreated"/>
</form:form>