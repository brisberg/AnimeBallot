<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${flashMessage != null}">
    <div class="message">${flashMessage}</div>
</c:if>

<table class="table">
    <tr>
        <td>User:</td>
        <td>${ballot.user.name}</td>
        <td>Season:</td>
        <td>${ballot.season.title}</td>
        <td>Week:</td>
        <td>${ballot.weekIndex}</td>
    </tr>
    <tr>
        <table class="table" style="background:#DDDDDD">
            <thead>
            <tr>
                <th>Series</th>
                <th>Episode</th>
                <th>Score</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="ballotVote" items="${ballot.ballotVotes}" varStatus="rowCounter">
                <tr class="${rowCounter.count % 2 == 0 ? 'even' : 'odd'}">
                    <td>
                        <a href="<c:url value="/admin/series/show/${ballotVote.episode.series.id}" />">${ballotVote.episode.series.title}</a>
                    </td>
                    <td>
                            ${ballotVote.episode.episodeIndex}
                    </td>
                    <td>
                            ${ballotVote.score}
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty ballot.ballotVotes}">
                <tr>
                    <td colspan="999">No votes found</td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </tr>
</table>

<table class="table" style="background:#DDDDDD">
    <!-- TODO add a print out of the series in this season -->
</table>

<div class="botButtons">
    <a href="<c:url value="/admin/ballot/list" />" class="btn btn-default">Back</a>
</div>
