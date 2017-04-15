<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Timetable Project</title>

	<link href="<c:url value = "/resources/css/bootstrap-datepicker3.css"/>"
	rel="stylesheet" />
	<link href="<c:url value = "/resources/css/bootstrap-datepicker3.css"/>"
	rel="stylesheet" />

<link href="<c:url value = "resources/css/bootstrap-select.min.css"/>"
	rel="stylesheet" />
<link href="<c:url value = "resources/font/css/font-awesome.min.css"/>"
	rel="stylesheet" />
	<link href="<c:url value = "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>"
	rel="stylesheet" />
<link href="<c:url value = "/resources/css/style.css"/>"
	rel="stylesheet" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="<c:url value = "/resources/js/bootstrap-select.min.js"/>"></script>
<script src="<c:url value = "/resources/js/bootstrap-datepicker.min.js"/>"></script>
</head>
<body>

 <nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="/test">Timetable Project</a>
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
          <li><a href="<c:url value="/searchevent"/>">Search Events</a></li>
        </ul>
      </li>
    </ul>
     <ul class="nav navbar-nav pull-right" >
                <li><a href="<c:url value="/logout"/>" class="pull-right">Logout</a></li>
            </ul>
  </div>
</nav>
<H1 class="title">Search Events</H1>
<div class="container">
<div class="row">
<div class="col-md-6 col-md-offset-3">
<form action="searchevent" method="post">
				<c:choose>
				    <c:when test="${param.msg.length()>0}">
				    		<div class='alert alert-danger'><span class=''><c:out value="${param.msg}"></c:out></span> </div>
				    </c:when>
			   </c:choose>
			   <c:choose>
				    <c:when test="${message.length()>0}">
				    		<div class='alert alert-success'><span class=''><c:out value="${message}"></c:out></span> </div>
				    </c:when>
			   </c:choose>
			   <c:choose>
				    <c:when test="${messages.length()>0}">
				    		<div class='alert alert-success'><span class=''><c:out value="${messages}"></c:out></span> </div>
				    </c:when>
			   </c:choose>
			   
			   
					<div class="form-group">
						<label for="Studentgroup">Date</label>
							<div class="input-group">
       							<div class="input-group-addon">
        							<i class="fa fa-calendar-check-o"></i>
      							</div>
      							<input class="form-control" id="date"  name="date" placeholder="DD/MM/YYYY" type="date"></input>
     						</div>
					</div>
					
					 <div class="form-group">
						<label for="end">Room</label>
						<select class="selectpicker form-control" data-live-search="true" name="room">
						<option value="" selected >Please select</option>
							  <c:if test="${room.size() > 0}">
								<c:forEach  items="${room}" var="roomValue">
									<option data-tokens="${roomValue.getRoomNumber()},${roomValue.getCapacity()}" value="${roomValue.getId()}">${roomValue.getRoomNumber()}</option>
								</c:forEach>
							 </c:if>
						 </select>
					</div>
					<div class="form-group">
						<label for="end">Module</label>
						<select class="selectpicker form-control" data-live-search="true" name="module" >
						<option value="" selected >Please select</option>
						    <c:if test="${module.size() > 0}">
						<c:forEach  items="${module}" var="moduleValue">
								<option data-tokens="${moduleValue.getModuleCode()},${moduleValue.getModuleName()}" value="${moduleValue.getId()}">${moduleValue.getModuleCode()}</option>	
						</c:forEach>
					</c:if>
						 </select>
					</div>
					<div class="form-group">
						<label for="student">Student Group</label>
						<select class="selectpicker form-control" data-live-search="true" name="student" >
						     <option value="" selected >Please select</option>
						     <c:if test="${student.size() > 0}">
						<c:forEach  items="${student}" var="studentValue">
								<option data-tokens="${studentValue.getGroupName()}" value="${studentValue.getGroupId()}">${studentValue.getGroupName()}</option>	
						</c:forEach>
					</c:if>
						 </select>
					</div>
					<div class="form-group">
						<label for="tutor">Tutor</label>
						<select class="selectpicker form-control" data-live-search="true" name="tutor">
						     <option value="" selected >Please select</option>
						      <c:if test="${tutor.size() > 0}">
						<c:forEach  items="${tutor}" var="tutorValue">
								<option data-tokens="${tutorValue.getName()}" value="${tutorValue.getId()}">${tutorValue.getName()}</option>	
						</c:forEach>
					</c:if>
						 </select>
					</div>
					<button type="submit" class="btn btn-default">Search for Events</button>
				</form>
			</div>
		</div>
	</div>
<SCRIPT type="text/javascript">
$(document).ready(function(e) {
	  $('.selectpicker').selectpicker();
	});
	  $(document).ready(function(){
	        var date_input=$('input[name="date"]'); //our date input has the name "date"
	        var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
	        date_input.datepicker({
	            format: 'dd/mm/yyyy',
	            container: 'body',
	            todayHighlight: true,
	            autoclose: true,
	        })
	    });

	</SCRIPT>
</body>
</html>