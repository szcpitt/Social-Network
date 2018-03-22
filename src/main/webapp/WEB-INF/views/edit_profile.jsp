<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Footbook</title>

    <link href="${contextPath}/resources/css/welcome.css" rel="stylesheet">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body style="background-color: #f7f7f7">

<ul id="navbar" style="height: 48px">
    <li style="margin-left: 15px;"><p style="color: white;font-weight:bold;margin-right: 15px;margin-top: 10px;font-size: 22px;">Footbook</p></li>
    <li><a href="welcome">Home</a></li>
    <li><a href="friends">Friends</a></li>
    <li><a href="favorites">Favorites</a></li>
    <li><a href="calendar">Calendar</a></li>
    <li><a href="profile" class="active">My Profile</a></li>
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <p style="text-align: right;margin-right: 5px;color: white">Welcome ${pageContext.request.userPrincipal.name} | <a id="logout"onclick="document.forms['logoutForm'].submit()">Logout</a></p>
    </c:if>
</ul>

<div class="row" style="margin-top: 80px;margin-left: 8%;">

    <div class="column-left2">
        <div class="card">
            <form:form method="POST" modelAttribute="profileForm" action="${contextPath}/profile">
                <p>First Name</p>
                <spring:bind path="firstName">
                    <div>
                        <form:input type="text" path="firstName" class="form-control" placeholder="First Name" autofocus="true"></form:input>
                    </div>
                </spring:bind>
                <p>Last Name</p>
                <spring:bind path="lastName">
                    <div>
                        <form:input type="text" path="lastName" class="form-control" placeholder="Last Name"></form:input>
                    </div>
                </spring:bind>
                <p>Gender</p>
                <spring:bind path="gender">
                    <div class="form-group">
                        <form:select path="gender" class="form-control">
                            <form:option value="Male"></form:option>
                            <form:option value="Female"></form:option>
                        </form:select>
                    </div>
                </spring:bind>
                <br>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
            </form:form><br>
        </div>
    </div>

    <div class="column-right">
        <div class="card" style="text-align: center">

        </div>
    </div>
</div>

<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>

<footer class="w3-container w3-padding-16" style="text-align: center;color: white">
    <h2 style="font-weight: bold">Footbook</h2>
</footer>

</html>
