
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Basic Settings</title>
<link href="<c:url value = "/resources/css/bootstrap.min.css"/>"
	rel="stylesheet" />

<link href="<c:url value = "/resources/font/css/font-awesome.min.css"/>"
	rel="stylesheet" />
<link href="<c:url value = "/resources/css/style.css"/>"
	rel="stylesheet" />
<script src="<c:url value ="/resources/js/bootstrap.min.js"/>">
	
</script>
<script src="<c:url value ="/resources/js/jquery-3.1.1.min.js"/>">
	
</script>
</head>
<body>

	<div class="container">
		<div class="row landing">
			<div class="col-md-4 col-md-offset-2">
				<i class="fa fa-plus-square-o fabasic center-block img-respinsive"></i>
				<a href="<c:url value="/createroom"/>" class ="landing-para center-block">Create Room</a>
			</div>
			<div class="col-md-4">
				<i class="fa fa-list fabasic center-block img-respinsive"></i>
				<a href="<c:url value="/listrooms"/>" class ="landing-para center-block">List Rooms</a>
			</div>
			
		</div>

	</div>
</body>
</html>