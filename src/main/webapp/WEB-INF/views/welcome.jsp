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

<ul id="navbar">
    <li id="logoLi"><p id="logoP">Footbook</p></li>
    <li><a class="active">Home</a></li>
    <li><a href="friends">Friends</a></li>
    <li><a href="favorites">Favorites</a></li>
    <li><a href="calendar">Calendar</a></li>
    <li><a href="profile">My Profile</a></li>
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <p id="logoutP">Welcome ${pageContext.request.userPrincipal.name} | <a id="logout"onclick="document.forms['logoutForm'].submit()">Logout</a></p>
    </c:if>
</ul>

<div class="row">
    <div class="column-left">
        <div class="card">
            <h4 class="w3-center">${myName}</h4>
            <p class="w3-center"><img src="${myImage}" class="w3-circle" style="height:106px;width:106px" alt="Avatar"></p>
            <hr>
            <p><i class="fa fa-pencil fa-fw w3-margin-right w3-text-theme"></i> Student</p>
            <p><i class="fa fa-home fa-fw w3-margin-right w3-text-theme"></i> Pittsburgh, PA</p>
            <p><i class="fa fa-birthday-cake fa-fw w3-margin-right w3-text-theme"></i> October 19, 1994</p>
        </div>
    </div>

    <div class="column-middle">
        <div class="card">
            <h6 class="w3-opacity">Post something interesting!</h6>
            <form:form method="POST" commandName="newBlog" modelAtrribute="newBlog" action="/addPost?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data">
                <spring:bind path="content">
                    <form:input path="content" type="text" class="w3-border w3-padding" cssStyle="width: 100%"></form:input>
                </spring:bind>
                <br><br>
                Select a image <input type="file" name="file" />
                <br><br>
                <button type="submit" class="w3-button" style="background-color: #3b5998;color: white;"><i class="fa fa-pencil"></i> Post</button>
            </form:form>
        </div>
        <p id="blogListSize" style="display: none"><c:out value='${blogList.size()}'/></p>
        <c:forEach items="${blogList}" var="blog">
                <div class="card">
                    <img src="${blog[1]}" alt="Profile Image" class="w3-left w3-circle w3-margin-right" style="width:60px">
                    <span class="w3-right w3-opacity">1 min</span>
                    <h4> ${blog[2]}</h4><br>
                    <hr class="w3-clear">
                    <p style="margin: 2%"> ${blog[3]}</p>
                    <div class="w3-row-padding" style="margin:0 -16px">
                        <div style="margin: 2%">
                            <img id="postImage${blog[0]}" src="${blog[4]}" onerror="this.onerror=null;this.src='notFound';this.style.display='none'" style="width:100%" alt="Post Image" class="w3-margin-bottom">
                        </div>
                    </div>
                    <p id="checkAdded${blog[0]}" style="display: none">${blog[5]}</p>
                    <form id="likeForm${blog[0]}"action="/addFavorite" method="POST" style="float: left">
                        <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
                        <button id="beforeClickLike" type="submit" name="blogId" value="${blog[0]}" class="w3-button w3-margin-bottom" style="background-color: #3b5998;color: white;"><i class="fa fa-thumbs-up"></i> Like</button>
                    </form>
                    <button id="afterClickLike${blog[0]}" type="button" class="w3-button w3-margin-bottom" style="background-color: #ccc;color: black;display: none"><i class="fa fa-thumbs-up"></i> Liked</button>
                    <button type="button" class="w3-button w3-margin-bottom" style="background-color: #3b5998;color: white;margin-left: 10px;"><i class="fa fa-comment"></i> Comment</button>
                </div>
        </c:forEach>
    </div>

    <div class="column-right">

        <div class="card" style="text-align: center">
            <p>Upcoming Event:</p>
            <img src="/resources/img/forest.jpg" alt="Forest" style="width:100%;">
            <p><strong>${upcoming.title}</strong></p>
            <p>Start: ${upcoming.start}</p>
            <p>End: ${upcoming.end}</p>
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
<script src="${contextPath}/resources/js/welcome.js"></script>
</body>



</html>
