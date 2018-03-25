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
    <li><a class="active">Home</a></li>
    <li><a href="friends">Friends</a></li>
    <li><a href="favorites">Favorites</a></li>
    <li><a href="calendar">Calendar</a></li>
    <li><a href="profile">My Profile</a></li>
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <p style="text-align: right;margin-right: 5px;color: white">Welcome ${pageContext.request.userPrincipal.name} | <a id="logout"onclick="document.forms['logoutForm'].submit()">Logout</a></p>
    </c:if>
</ul>

<div class="row" style="margin-top: 80px;margin-left: 8%;">
    <div class="column-left">
        <div class="card">
            <h4 class="w3-center">My Profile</h4>
            <p class="w3-center"><img src="/resources/img/avatar3.png" class="w3-circle" style="height:106px;width:106px" alt="Avatar"></p>
            <hr>
            <p><i class="fa fa-pencil fa-fw w3-margin-right w3-text-theme"></i> Designer, UI</p>
            <p><i class="fa fa-home fa-fw w3-margin-right w3-text-theme"></i> London, UK</p>
            <p><i class="fa fa-birthday-cake fa-fw w3-margin-right w3-text-theme"></i> April 1, 1988</p>
        </div>
    </div>

    <div class="column-middle">
        <div class="card">
            <h6 class="w3-opacity">Post something interesting!</h6>
            <form:form method="POST" modelAtrribute="newBlog" action="/addPost">
                <p>Share something interesting!</p>
                <spring:bind path="content">
                    <form:input path="content" type="text" class="w3-border w3-padding"></form:input>
                </spring:bind>
                <button type="submit" class="w3-button" style="background-color: #3b5998;color: white;"><i class="fa fa-pencil"></i> Post</button>
            </form:form>
        </div>

        <c:forEach items="${blogMap.keySet()}" var="key">
            <div class="card">
                <img src="/resources/img/avatar3.png" alt="Avatar" class="w3-left w3-circle w3-margin-right" style="width:60px">
                <span class="w3-right w3-opacity">1 min</span>
                <h4>${key}</h4><br>
                <hr class="w3-clear">
                <p> ${blogMap[key]}</p>
                <div class="w3-row-padding" style="margin:0 -16px">
                    <div class="w3-half">
                        <img src="/resources/img/lights.jpg" style="width:100%" alt="Northern Lights" class="w3-margin-bottom">
                    </div>
                    <div class="w3-half">
                        <img src="/resources/img/nature.jpg" style="width:100%" alt="Nature" class="w3-margin-bottom">
                    </div>
                </div>
                <button type="button" class="w3-button w3-margin-bottom" style="background-color: #3b5998;color: white;"><i class="fa fa-thumbs-up"></i> Like</button>
                <button type="button" class="w3-button w3-margin-bottom" style="background-color: #3b5998;color: white;"><i class="fa fa-comment"></i> Comment</button>
            </div>
        </c:forEach>
    </div>

    <div class="column-right">

        <div class="card" style="text-align: center">
            <p>Upcoming Events:</p>
            <img src="/resources/img/forest.jpg" alt="Forest" style="width:100%;">
            <p><strong>Holiday</strong></p>
            <p>Friday 15:00</p>
            <p><button class="w3-button w3-block w3-theme-l4">Info</button></p>
        </div>

        <div class="card" style="text-align: center">
            <p>Friend Request</p>
            <img src="/resources/img/avatar6.png" alt="Avatar" style="width:50%"><br>
            <span>Jane Doe</span>
            <div class="w3-row w3-opacity">
                <div class="w3-half">
                    <button class="w3-button w3-block w3-green w3-section" title="Accept"><i class="fa fa-check"></i></button>
                </div>
                <div class="w3-half">
                    <button class="w3-button w3-block w3-red w3-section" title="Decline"><i class="fa fa-remove"></i></button>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>

<footer class="w3-container w3-padding-16" style="text-align: center;color: white;height:70px">
    <h2 style="font-weight: bold;margin-top: 0px;">Footbook</h2>
</footer>

</html>
