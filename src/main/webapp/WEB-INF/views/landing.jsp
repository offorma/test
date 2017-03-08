<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Rgu Scheduler</title>
<link href ="<c:url value = "/resources/css/bootstrap.min.css"/>" rel ="stylesheet"/>
<link href = "<c:url value = "/resources/css/style.css"/>" rel ="stylesheet"/>
<script src = "<c:url value ="/resources/js/bootstrap.min.js"/>"> </script>
<script src = "<c:url value ="/resources/js/jquery-3.1.1.min.js"/>"> </script>
</head>
<body>
	<div class="container">
		<div class="row landing">
		<div class="col-md-4 col-md-offset-2">
		<img id="settings-img"class="center-block img-respinsive" src="<c:url value = "/resources/img/settings.png"/>" alt="" />
		<a href="<c:url value="/basic"/>" class ="landing-para">Basic Settings</a>
		</div>
		<div class="col-md-4">
		<img id= "schedule-img" class="center-block img-respinsive"  src="<c:url value = "/resources/img/schedule.png"/>" alt="" />
		<a href="<c:url value="/event"/>"class ="landing-para">Schedule Events</a>
		</div>
		</div>
  
	</div>
</body>
</html>