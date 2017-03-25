
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Login</title>
<link href="<c:url value = "/resources/css/bootstrap.min.css"/>"
	rel="stylesheet" />

<link href="<c:url value = "/resources/font/css/font-awesome.min.css"/>"
	rel="stylesheet" />
<link href="<c:url value = "/resources/css/style.css"/>"
	rel="stylesheet" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

 <nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="">Timetable Project</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#"><i class="fa fa-cogs" aria-hidden="true"></i>
        Manage Rooms
        <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="<c:url value="/createroom"/>">Create Rooms</a></li>
          <li><a href="<c:url value="/listrooms"/>">List Rooms</a></li>
        </ul>
      </li>
      <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#"><i class="fa fa-cogs" aria-hidden="true"></i>
        Manage Tutors
        <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="<c:url value="/createtutor"/>">Create Tutors</a></li>
          <li><a href="<c:url value="/listtutors"/>">List Tutors</a></li>
        </ul>
      </li>
      <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#"><i class="fa fa-cogs" aria-hidden="true"></i>
        Manage Modules
        <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="<c:url value="/createmodule"/>">Create Module</a></li>
          <li><a href="<c:url value="/listmodule"/>">List Modules</a></li>
        </ul>
      </li>
      <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#"><i class="fa fa-cogs" aria-hidden="true"></i>
        Manage Student Groups
        <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="<c:url value="/createstudent"/>">Create Student Group</a></li>
          <li><a href="<c:url value="/liststudent"/>">List Student Group</a></li>
        </ul>
      </li>
      <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#"><i class="fa fa-cogs" aria-hidden="true"></i>
        Manage Events
        <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="<c:url value="/createevent"/>">Schedule Event</a></li>
          <li><a href="<c:url value="/liststudent"/>">View Events</a></li>
        </ul>
      </li>
    </ul>
  </div>
</nav>
	<div class="container">
		<div class="row landing">
		<div class="col-md-4 col-md-offset-2">
		<img id="settings-img"class="center-block img-respinsive" src="<c:url value = "/resources/img/settings.png"/>" alt="" />
		<a href="<c:url value="/basic"/>" class ="landing-para center-block">Basic Settings</a>
		</div>
		<div class="col-md-4">
		<img id= "schedule-img" class="center-block img-respinsive"  src="<c:url value = "/resources/img/schedule.png"/>" alt="" />
		<a href="<c:url value="/event"/>"class ="landing-para center-block">Schedule Events</a>
		</div>
		</div>
  
	</div>
</body>
</html>