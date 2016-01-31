<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page import="com.incra.models.DayIndex" %>
<%@ page import="com.incra.models.Configuration" %>

<c:url var="saveUrl" value="/admin/configuration/save"/>
<form:form method="post" action="${saveUrl}">
    <form:hidden path="id"/>
    <form:hidden path="priorCurrentWeekIndex"/>

    <table class="table" style="width: 400px; margin-top: 20px;">
        <tr>
            <td>Week Start Day/Hour:</td>
            <td>
                <form:select path="weekStartDay">
                    <form:options itemLabel="label"/>
                </form:select>
                <form:select path="weekStartHour">
                    <form:options itemLabel="label"/>
                </form:select>
            </td>
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
            <td>
                <span id="arrow-left" class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span>
                <form:input id="currentWeekIndex" path="currentWeekIndex" size="15"/>
                <span id="arrow-right" class="glyphicon glyphicon-arrow-right" aria-hidden="true"></span>
            </td>
            <td><form:errors path="currentWeekIndex" cssClass="error"/></td>
        </tr>
    </table>

    <div class="botButtons">
        <input class="save" type="submit" value="Submit"/>
    </div>

    <form:hidden path="dateCreated"/>
</form:form>

<script>
    $(document).ready(function () {
        $("#arrow-left").click(function () {
            var currentWeekIndexStr = $('#currentWeekIndex').val();
            var currentWeekIndex = parseInt(currentWeekIndexStr);

            if (currentWeekIndex > 1) {
                currentWeekIndex--;
                $('#currentWeekIndex').val("" + currentWeekIndex);
            }
        });

        $("#arrow-right").click(function () {
            var currentWeekIndexStr = $('#currentWeekIndex').val();
            var currentWeekIndex = parseInt(currentWeekIndexStr);

            if (currentWeekIndex < 13) {
                currentWeekIndex++;
                $('#currentWeekIndex').val("" + currentWeekIndex);
            }
        });
    });
</script>
