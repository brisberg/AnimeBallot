<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url var="saveUrl" value="/admin/series/save"/>
<form:form method="post" action="${saveUrl}">
    <form:hidden path="id"/>
    <table>
        <tr>
            <td>Title:</td>
            <td><form:input path="title" size="40"/></td>
            <td><form:errors path="title" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Season:</td>
            <td>
                <form:select path="season">
                    <form:options items="${seasonList}" itemLabel="title" itemValue="id"/>
                </form:select>
            </td>
            <td><form:errors path="season" cssClass="error"/></td>
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
        <tr>
            <td>Episode Count:</td>
            <td><form:input path="episodeCount" size="40" value="13"/></td>
            <td><form:errors path="episodeCount" cssClass="error"/></td>
        </tr>
    </table>

    <div class="botButtons">
        <input class="save" type="submit" value="Submit"/>
    </div>
</form:form>

