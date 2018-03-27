<%--
  Created by IntelliJ IDEA.
  User: jacob
  Date: 2018/3/25
  Time: 上午8:20
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <link href='../../resources/fullcalendar/fullcalendar.min.css' rel='stylesheet' />
    <link href='../../resources/fullcalendar/fullcalendar.print.min.css' rel='stylesheet' media='print' />
    <link href="${contextPath}/resources/css/welcome.css" rel="stylesheet">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <script src='../../resources/fullcalendar/lib/moment.min.js'></script>
    <script src='../../resources/fullcalendar/lib/jquery.min.js'></script>
    <script src='../../resources/fullcalendar/fullcalendar.min.js'></script>
    <script>

        $(document).ready(function() {

            $('#calendar').fullCalendar({
                header: {
                    left: 'prev,next today',
                    center: 'title',
                    right: 'listDay,listWeek,month'
                },

                // customize the button names,
                // otherwise they'd all just say "list"
                views: {
                    listDay: { buttonText: 'list day' },
                    listWeek: { buttonText: 'list week' }
                },

                defaultView: 'listWeek',
                defaultDate: '2018-03-12',
                navLinks: true, // can click day/week names to navigate views
                editable: true,
                eventLimit: true, // allow "more" link when too many events
                events: ${calendarList}
            });

        });

    </script>
    <style>
        #calendar {
            max-width: 900px;
            margin: 0 auto;
        }

    </style>
</head>
<body style="padding:0px;margin: 0px;">
<ul id="navbar" style="height: 48px">
    <li style="margin-left: 15px;"><p style="color: white;font-weight:bold;margin-right: 15px;margin-top: 10px;font-size: 22px;">Footbook</p></li>
    <li><a href="welcome">Home</a></li>
    <li><a href="friends">Friends</a></li>
    <li><a href="favorites">Favorites</a></li>
    <li><a class="active">Calendar</a></li>
    <li><a href="profile">My Profile</a></li>
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <p style="text-align: right;margin-right: 5px;color: white">Welcome ${pageContext.request.userPrincipal.name} | <a id="logout"onclick="document.forms['logoutForm'].submit()">Logout</a></p>
    </c:if>
</ul>

<hr><hr>

<div id='calendar'></div>
<%--${calendarList}--%>

<%--${user_id}--%>

</body>
</html>
