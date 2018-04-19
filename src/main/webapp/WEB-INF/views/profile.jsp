<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

<ul id="navbar">
    <li id="logoLi"><p id="logoP">Footbook</p></li>
    <li><a href="welcome">Home</a></li>
    <li><a href="friends">Friends</a></li>
    <li><a href="favorites">Favorites</a></li>
    <li><a href="calendar">Calendar</a></li>
    <li><a class="active">My Profile</a></li>
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <p id="logoutP">Welcome ${pageContext.request.userPrincipal.name} | <a id="logout"onclick="document.forms['logoutForm'].submit()">Logout</a></p>
    </c:if>
</ul>

<div class="row">

    <div class="column-middle" id="profileCol">

        <div class="card" style="padding: 6%;font-size: 18px;">
            <p class="w3-center"><img src="${myImage}" class="w3-circle" style="height:106px;width:106px"></p><br>
            <p class="w3-center">First name: ${firstName}</p>
            <p class="w3-center">Last name: ${lastName}</p>
            <p class="w3-center">Gender: ${gender}</p>
            <button class="w3-button w3-margin-bottom" id="editProfile"><a href="/edit_profile" style="text-decoration:none;" >Edit My Profile</a></button>
        </div>
    </div>


</div>

<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>


</html>
