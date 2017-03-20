<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Module Listings</title>
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
			<c:choose>
				    <c:when test="${message.length()>0}">
				    		<div class='alert alert-danger'><span class=''>${message}</span> </div>
				    </c:when>
			   </c:choose>
			   <c:choose>
				    <c:when test='${messages.length()>0}'>
				    		<div class='alert alert-success'><span class=''>${messages}</span> </div>
				    </c:when>
			   </c:choose>
				<table id ="listrooms" class="table table-condensed table-striped">
					<thead>
						<tr>
							<th class="">Module Code</th>
							<th class="">Module Name</th>
							<th class="">Total Weekly Hours</th>
							<th class=""></th>
							<th class=""></th>
							
						</tr>
					</thead>
					<c:if test="${module.size() > 0}">
						<c:forEach  items="${module}" var="moduleValue">
							<tr>
								<td class="">${moduleValue.getModuleCode()}</td>
								<td class="">${moduleValue.getModuleName()}</td>
								<td class="">${moduleValue.getTotalWeeklyHour()}</td>
								<td class=""><a href="<c:url value="/editmodule/${moduleValue.getModuleId()}"/>"><button class="btn btn-success">edit</button></a></td>
								<td class=""><a href="<c:url value="/deletemodule/${moduleValue.getModuleId()}"/>"><button class="btn btn-danger">delete</button></a></td>
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