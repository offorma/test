
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Room Listings</title>
<link href="<c:url value = "/resources/css/bootstrap.min.css"/>"
	rel="stylesheet" />

<link href="<c:url value = "/resources/font/css/font-awesome.min.css"/>"
	rel="stylesheet" />
	<link href="<c:url value = "https://cdn.datatables.net/1.10.13/css/jquery.dataTables.min.css"/>"
	rel="stylesheet" />
<link href="<c:url value = "/resources/css/style.css"/>"
	rel="stylesheet" />
<script src="<c:url value ="/resources/js/bootstrap.min.js"/>">
	
</script>
<script src="<c:url value ="/resources/js/jquery-3.1.1.min.js"/>">
	
</script>
<script src="<c:url value ="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"/>">
	
</script>
<script src="<c:url value ="https://cdn.datatables.net/1.10.13/js/dataTables.bootstrap.min.js"/>">
	
</script>
</head>
<body>

	<div class="container">
		<div class="row landing">
			<div class="col-md-8 col-md-offset-2">
				<table id ="listrooms" class="table table-condensed table-striped">
					<thead>
						<tr>
							<th class="">Student Group Name</th>
							<th class="">Total Number</th>
							<th class=""></th>
							<th class=""></th>
							
						</tr>
					</thead>
					<c:if test="${student.size() > 0}">
						<c:forEach  items="${student}" var="studentValue">
							<tr>
								<td class="">${studentValue.getGroupName()}</td>
								<td class="">${studentValue.getNumberOfStudent()}</td>
								
								
								<td class=""><a href="<c:url value="/editstudent/${studentValue.getGroupId()}"/>"><button class="btn btn-success">edit</button></a></td>
								<td class=""><a href="<c:url value="/deletestudent/${studentValue.getGroupId()}"/>"><button class="btn btn-danger">delete</button></a></td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
			</div>

		</div>

	</div>
	<script type="text/javascript">
	$(document).ready(function() {
	    $('#listrooms').DataTable();
	} );
	</script>
</body>
</html>