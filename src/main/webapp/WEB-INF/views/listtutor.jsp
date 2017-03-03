
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Tutor Listings</title>
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
							<th class="">Staff Name</th>
							<th class="">Department</th>
							<th class=""></th>
							<th class=""></th>
							
						</tr>
					</thead>
					<tr>
						<td class="">John Smith</td>
						<td class="">computing</td>
						<td class=""><button class="btn btn-success">edit</button></td>
						<td class=""><button class="btn btn-danger">delete</button></td>
					</tr>
						<tr>
						<td class="">Sheldon Cooper</td>
						<td class="">Engineering</td>
						<td class=""><button class="btn btn-success">edit</button></td>
						<td class=""><button class="btn btn-danger">delete</button></td>
					</tr>
						<tr>
						<td class="">Richard Branson</td>
						<td class="">ABS</td>
						<td class=""><button class="btn btn-success">edit</button></td>
						<td class=""><button class="btn btn-danger">delete</button></td>
					</tr>
					<tr>
						<td class="">Mike Peterson</td>
						<td class="">Computing</td>
						<td class=""><button class="btn btn-success">edit</button></td>
						<td class=""><button class="btn btn-danger">delete</button></td>
					</tr>
						<tr>
						<td class="">Jill Green</td>
						<td class="">Enginnering</td>
						<td class=""><button class="btn btn-success">edit</button></td>
						<td class=""><button class="btn btn-danger">delete</button></td>
					</tr>
						<tr>
						<td class="">Robert Scholes</td>
						<td class="">pharmacy</td>
						<td class=""><button class="btn btn-success">edit</button></td>
						<td class=""><button class="btn btn-danger">delete</button></td>
					</tr>
					
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