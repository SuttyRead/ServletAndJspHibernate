<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
          integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
</head>
<body>
<%@ include file = "header.jsp" %>
<c:if test="${errorMessage != null}">
    <div class="alert alert-danger" role="alert">
        Incorrect login or password!
    </div>
</c:if>
<div>
    <form action="${pageContext.request.contextPath}/login" method="post" >
        <div class="form-group">
            <label for="exampleInputLogin">Login</label>
            <input type="text" class="form-control" id="exampleInputLogin" aria-describedby="emailHelp"
                   placeholder="Enter login" name="login" required>
        </div>
        <div class="form-group">
            <label for="exampleInputPassword1">Password</label>
            <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Enter password" name="password" required>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>

</body>
</html>