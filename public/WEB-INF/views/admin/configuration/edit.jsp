<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url var="saveUrl" value="/admin/configuration/save"/>
<form:form method="post" action="${saveUrl}">
    <form:hidden path="id"/>

    <table>
        <tr>
            <td>Week Start Date:</td>
            <td><form:input path="weekStartTime" size="40"/></td>
            <td><form:errors path="weekStartTime" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Season:</td>
            <td>
                <form:select path="currentSeason">
                    <form:options items="${seasonList}" itemLabel="title" itemValue="id"/>
                </form:select>
            </td>
            <td><form:errors path="currentSeason" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Week Index (1-13):</td>
            <td><form:input path="currentWeekIndex" size="40"/></td>
            <td><form:errors path="currentWeekIndex" cssClass="error"/></td>
        </tr>
    </table>

    <div class="botButtons">
        <input class="save" type="submit" value="Submit"/>
    </div>

    <form:hidden path="dateCreated"/>
</form:form>
