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

        body {
            margin: 40px 10px;
            padding: 0;
            font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
            font-size: 14px;
        }

        #calendar {
            max-width: 900px;
            margin: 0 auto;
        }

    </style>
</head>
<body>

<div id='calendar'></div>
<%--${calendarList}--%>

<%--${user_id}--%>

</body>
</html>
