
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Create Student Group</title>
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
				<form action="createstudent" method="post">
					<div class="form-group">
						<label for="Studentgroup">Student Group Name</label> <input
							type="text" class="form-control" name="Studentgroup" id= "Studentgroup"></input>
					</div>
					<div class="form-group">
						<label for="Totalnumber">Total Number</label> <input
							type="text" class="form-control" name="Totalnumber" id="Totalnumber"></input>
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>