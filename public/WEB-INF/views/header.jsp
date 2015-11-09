<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div id="header" class="row" style="background: #337AB7; color: white;">
    <div class="col-md-4">
        <a href="<c:url value="/" />">
            <img border=0 src="<c:url value="/assets/images/logo.gif" />"/>
        </a>
    </div>

    <div class="col-md-8"
         style="text-align: right; padding-top: 10px; font-weight: bold; font-size: 20px">
        <a style="text-decoration: none; color: black;" href="<c:url value="/admin/home" />">
            Admin
        </a>
    </div>
</div>