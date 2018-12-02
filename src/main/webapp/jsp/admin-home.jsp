<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="usersTag" prefix="myTags" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
          integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
</head>
<body>
<%@ include file="header.jsp" %>

Hello, admin

<a href="/add">Add new User</a>

<div class="col-sm-offset-3 col-sm-6 err-message">
    <c:if test="${successfullyUpdated != null}">
        <div class="alert alert-success" role="alert">
            User was successfully update!
        </div>
    </c:if>
</div>

<div class="col-sm-offset-3 col-sm-6 err-message">
    <c:if test="${successfullyDeleted != null}">
        <div class="alert alert-success" role="alert">
            User was successfully delete!
        </div>
    </c:if>
</div>

<div class="col-sm-offset-3 col-sm-6 err-message">
    <c:if test="${errorDeleting != null}">
        <div class="alert alert-danger" role="alert">
            You cannot delete yourself
        </div>
    </c:if>
</div>

<myTags:printTable userList="${users}"/>


</div>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>

</body>
</html>
