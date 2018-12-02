<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add User</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
          integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
</head>
<body>
<%@ include file="header.jsp" %>
<h1>Add User</h1>

<div class="col-sm-offset-3 col-sm-6 err-message">
    <c:if test="${successfullyAdded != null}">
        <div class="alert alert-success" role="alert">
            User was successfully add!
        </div>
    </c:if>
</div>

<form method="post" class="form-horizontal" action="/add">
    <input type="hidden" name="id" value="${newUser.id}">
    <input type="hidden" name="action" value="${newUser.id == null ? 'create' : 'update'}">
    <div class="form-group">
        <label class="control-label col-sm-3"
               for="login">Login:</label>
        <div class="col-sm-6">
            <input
            <c:if test="${newUser.id != null}">
                    readonly="readonly" </c:if>
                    type="text"
                    class="form-control"
                    id="login"
                    placeholder="Enter login"
                    name="login"
                    value="${newUser.login}"
                    aria-describedby="loginHelpInline"
                    required>
            <small id="loginHelpInline" class="text-muted">
                Must be 2-20 characters long. Pattern ^[a-zA-Z][a-zA-Z0-9-_\.]{1,20}$
            </small>
        </div>

        <div class="col-sm-offset-3 col-sm-6 err-message">
            <c:if test="${existLogin != null}">
                <div class="alert alert-danger" role="alert">
                    This login already exist!
                </div>
            </c:if>
        </div>
        <div class="col-sm-offset-3 col-sm-6 err-message">
            <c:if test="${loginNotPattern != null}">
                <div class="alert alert-danger" role="alert">
                    This login doesn't match pattern ^[a-zA-Z][a-zA-Z0-9-_\.]{1,20}$.
                    With a limit of 2-20 characters, which can be letters
                    and numbers, the first character is necessarily the letter.
                    For example SuttyRead
                </div>
            </c:if>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-3"
               for="password">Password:</label>
        <div class="col-sm-6">
            <input type="password" class="form-control" id="password"
                   placeholder="Enter password" name="password"
                   value="${newUser.password}" aria-describedby="passwordHelpInline" required>
            <small id="passwordHelpInline" class="text-muted">
                Lowercase and uppercase Latin letters, numbers, special characters. Minimum 8 characters
            </small>
        </div>
        <div class="col-sm-offset-3 col-sm-6 err-message">
            <c:if test="${passwordNotEquals != null}">
                <div class="alert alert-danger" role="alert">
                    Password don't match!
                </div>
            </c:if>
        </div>
        <div class="col-sm-offset-3 col-sm-6 err-message">
            <c:if test="${passwordNotPattern != null}">
                <div class="alert alert-danger" role="alert">
                    This login doesn't match pattern
                    ^[a-zA-Z][a-zA-Z0-9-_\.]{1,20}$.
                    With a limit of 2-20 characters, which can be letters
                    and numbers, the first character is necessarily the letter.
                    For example SuttyRead007
                </div>
            </c:if>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-3"
               for="confirmPassword">Confirm Password:</label>
        <div class="col-sm-6">
            <input type="password" class="form-control" id="confirmPassword"
                   placeholder="Confirm password" name="confirmPassword" required>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-3"
               for="email">Email:</label>
        <div class="col-sm-6">
            <input type="text" class="form-control" id="email"
                   placeholder="Enter email" name="email" aria-describedby=emailHelpInline" required>
        </div>

        <div class="col-sm-offset-3 col-sm-6 err-message">
            <c:if test="${emailNotPattern != null}">
                <div class="alert alert-danger" role="alert">
                    This email doesn't match pattern
                    \w+([\.-]?\w+)*@\w+([\.-]?\w+)*\.\w{2,4}
                    For example SuttyRead@gmail.com
                </div>
            </c:if>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-3"
               for="First Name">First Name:</label>
        <div class="col-sm-6">
            <input type="text" class="form-control" id="First Name"
                   placeholder="Enter first name" name="firstName" aria-describedby=firstNameHelpInline" required>
            <small id="firstNameHelpInline" class="text-muted">
                The first letter must be uppercase For example Sutty
            </small>
        </div>
        <div class="col-sm-offset-3 col-sm-6 err-message">
            <c:if test="${firstNameNotPattern != null}">
                <div class="alert alert-danger" role="alert">
                    This first name doesn't match pattern
                    ^[A-Z]{1}[a-z]{1,25}
                    For example Sutty
                </div>
            </c:if>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3"
               for="Last Name">Last Name:</label>
        <div class="col-sm-6">
            <input type="text" class="form-control" id="Last Name"
                   placeholder="Enter last name" name="lastName" aria-describedby=lastNameHelpInline" required>
            <small id="lastNameHelpInline" class="text-muted">
                The first letter must be uppercase For example Read
            </small>
        </div>
        <div class="col-sm-offset-3 col-sm-6 err-message">
            <c:if test="${lastNameNotPattern != null}">
                <div class="alert alert-danger" role="alert">
                    This last name doesn't match pattern
                    ^[A-Z]{1}[a-z]{1,25}
                    For example Read
                </div>
            </c:if>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3"
               for="Birthday">Birthday:</label>
        <div class="col-sm-6">
            <input type="date" class="form-control" id="Birthday"
                   placeholder="Enter birthday" name="birthday" required>
        </div>
        <div class="col-sm-offset-3 col-sm-6 err-message">
            <c:if test="${incorrectDate != null}">
                <div class="alert alert-danger" role="alert">
                    Incorrect birthday
                    For example 1982-7-27
                </div>
            </c:if>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-3"
               for="Role">Role:</label>
        <div class="col-sm-6">
            <select class="form-control" id="Role" name="role">
                <option value="2">User</option>
                <option value="1">Admin</option>
            </select>
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-1 col-sm-offset-4">
            <button type="submit" class="btn btn-success">OK</button>
        </div>
        <div class="col-sm-1">
            <a href="/home"
               class="btn btn-primary"
               role="button"
               aria-pressed="true">Cancel</a>
        </div>
    </div>
</form>
</body>
</html>