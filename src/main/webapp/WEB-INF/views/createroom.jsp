
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Create Rooms</title>
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
			<div class="col-md-6 col-md-offset-3">
			<c:choose>
				    <c:when test="${message.length()!=0}">
				    		<div class='alert alert-danger'><span class=''>${message}</span> </div>
				    </c:when>
			   </c:choose>
				<form method="post" action="createroom">
					<div class="form-group">
						<label for="RoomNumber">Room Number</label> <input
							type="text" class="form-control" name="RoomNumber" id= "RoomNumber"></input>
					</div>
					<div class="form-group">
						<label for="Capacity">Capacity</label> <input
							type="text" class="form-control" name="Capacity" id="Capacity"></input>
					</div>
					<div class="form-group">
						<label for="Building">Building</label> <input
							type="text" class="form-control" name="Building" id="Building"></input>
					</div>
					<div class="radio">
						<label> <input type="radio" name="type" value="Lecture" checked="checked"></input> Lecture </label>
						<label> <input type="radio" name="type" value="Labs"></input> Labs </label>
						<label> <input type="radio" name="type" value="Both"></input> Both </label>
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>