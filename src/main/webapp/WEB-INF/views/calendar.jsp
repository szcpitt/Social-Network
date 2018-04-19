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
    <title>My Calendar</title>

    <link href='../../resources/fullcalendar/fullcalendar.min.css' rel='stylesheet' />
    <link href='../../resources/fullcalendar/fullcalendar.print.min.css' rel='stylesheet' media='print' />
    <link href="${contextPath}/resources/css/welcome.css" rel="stylesheet">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <script src='../../resources/fullcalendar/lib/moment.min.js'></script>
    <script src='../../resources/fullcalendar/lib/jquery.min.js'></script>
    <script src='../../resources/fullcalendar/fullcalendar.min.js'></script>
    <script>

        var today = new Date();
        var dd = today.getDate();
        var mm = today.getMonth()+1; //January is 0!
        var yyyy = today.getFullYear();

        if(dd<10) {
            dd = '0'+dd
        }

        if(mm<10) {
            mm = '0'+mm
        }

        today = yyyy + '-' + mm + '-' + dd;


        $(document).ready(function() {

            $('#calendar').fullCalendar({
                header: {
                    left: 'prev,next today',
                    center: 'title',
                    right: 'month, listWeek,listDay'
                },

                // customize the button names,
                // otherwise they'd all just say "list"
                views: {
                    listMonth:{buttonText: 'Month'},
                    listDay: { buttonText: 'list day' },
                    listWeek: { buttonText: 'list week' }
                },

                defaultView: 'month',
                defaultDate: today,
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
    <%--validate date--%>


</head>
<body style="padding:0px;margin: 0px;">

<ul id="navbar">
    <li id="logoLi"><p id="logoP">Footbook</p></li>
    <li><a href="welcome">Home</a></li>
    <li><a href="friends">Friends</a></li>
    <li><a href="favorites">Favorites</a></li>
    <li><a class="active">Calendar</a></li>
    <li><a href="profile">My Profile</a></li>
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <p id="logoutP">Welcome ${pageContext.request.userPrincipal.name} | <a id="logout"onclick="document.forms['logoutForm'].submit()">Logout</a></p>
    </c:if>
</ul>

<hr><hr>





<%--${calendarList}--%>

<%--${user_id}--%>
<div id='calendar'></div>

<div class="row" style="margin-top: 10px;margin-left: 8%;">
    <div class="column-middle" style="width: 71%;margin-left: 10%">
        <div class="card">
            <h6 class="w3-opacity">Add A New Calendar!!</h6>
            <%--add a calendar--%>
            <form:form method="post" modelAttribute="newCalendar">

                <spring:bind path="title">
                    Title: <form:input path="title" type="text" required="required"></form:input>
                </spring:bind>

                <spring:bind path="start">
                    <label>Start Date: <form:input path="start" type="date" required="required"></form:input></label>
                </spring:bind>

                <spring:bind path="end">
                    <label>End Date: <form:input path="end" type="date" required="required"></form:input></label>
                </spring:bind>

                <button type="submit" class="w3-button w3-block" style="background-color: #3b5998;color: white; margin-top: 5px"><i class="fa fa-pencil"></i> Add An Event!</button>
            </form:form>
        </div>
    </div>

</div>
<script>
    var start = document.getElementById('start');
    var end = document.getElementById('end');

    start.addEventListener('change', function() {
        if (start.value)
            end.min = start.value;
    }, false);

    end.addEventLiseter('change', function() {
        if (end.value)
            start.max = end.value;
    }, false);

</script>
</body>
</html>
