
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Timetable Project</title>
<link href="<c:url value = "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>"
	rel="stylesheet" />
<link href="https://cdn.datatables.net/1.10.13/css/jquery.dataTables.min.css" rel="stylesheet">
<link href="<c:url value = "/resources/font/css/font-awesome.min.css"/>"
	rel="stylesheet" />
<link href="<c:url value = "/resources/css/style.css"/>"
	rel="stylesheet" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<SCRIPT src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js" type="text/javascript"></SCRIPT>
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
          <li><a href="<c:url value="/searchevent"/>">Search Events</a></li>
        </ul>
      </li>
    </ul>
     <ul class="nav navbar-nav pull-right" >
                <li><a href="<c:url value="/logout"/>" class="pull-right">Logout</a></li>
            </ul>
  </div>
</nav>
<H1 class="title">View Schedules</H1>
	<div class="container">
		<div class="row">
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
			</div>
		</div>
	</div>
	
	<div class="container">
		<div class="row landing">
			<div class="col-md-12 ">
				<table id ="listrooms" class="table table-condensed table-striped">
						<thead>
							<tr>
								<th class="">Event Date</th>
								<th class="">09:00-10:00</th>
								<th class="">10:00-11:00</th>
								<th class="">11:00-12:00</th>
								<th class="">12:00-13:00</th>
								<th class="">13:00-14:00</th>
								<th class="">14:00-15:00</th>
								<th class="">15:00-16:00</th>
								<th class="">16:00-17:00</th>
							</tr>
						</thead>
						<c:if test="${events.size() > 0}">
						<c:forEach  items="${dates}" var="dateValue">
						<c:forEach  items="${events}" var="eventValue">
						<c:if test="${dateValue.equals(eventValue.getDate())}">
							<c:set var="startTime" value='${eventValue.getStartTime().split(":")}'/>
							<c:set var="intstart" value='${Integer.valueOf(startTime[0])}'/>
							<c:set var="endTime" value='${eventValue.getEndTime().split(":")}'/>
							<c:set var="intend" value='${Integer.valueOf(endTime[0])}'/>
							<tr>
							<td class="">${dateValue}</td>
							<td class="">
							
							<c:if test="${intstart==09}">
								<p>${eventValue.getModule().getModuleCode()}</p>
								<p>${eventValue.getRoom().getRoomNumber()}</p>
								<c:forEach  items="${eventValue.getStudentGroup()}" var="eventStudent">
									<p>${eventStudent.getGroupName()}</p>
								</c:forEach>
								<c:forEach  items="${eventValue.getTutor()}" var="eventTutor">
									<p>${eventTutor.getName()}</p>
								</c:forEach>
								<div>
								<a href="<c:url value="/editevent/${eventValue.getId()}"/>" ><button class="btn btn-success">Edit <i class="fa fa-pencil-square-o"></i></button></a>
								<button class="btn btn-danger" data-href="<c:url value="/deleteevent/${eventValue.getId()}"/>" data-toggle="modal" data-target="#confirm-delete"> Delete
								<i class="fa fa-trash-o"></i></button>
								</div>
							</c:if>
							</td>
							<td class="">
							
							<c:if test="${(intstart<10 || intstart==10)&&(intend>=11)}">
								<p>${eventValue.getModule().getModuleCode()}</p>
								<p>${eventValue.getRoom().getRoomNumber()}</p>
								<c:forEach  items="${eventValue.getStudentGroup()}" var="eventStudent">
									<p>${eventStudent.getGroupName()}</p>
								</c:forEach>
								<c:forEach  items="${eventValue.getTutor()}" var="eventTutor">
									<p>${eventTutor.getName()}</p>
								</c:forEach>
								<div>
								<a href="<c:url value="/editevent/${eventValue.getId()}"/>"><button class="btn btn-success">Edit <i class="fa fa-pencil-square-o"></i></button></a>
								<button class="btn btn-danger" data-href="<c:url value="/deleteevent/${eventValue.getId()}"/>" data-toggle="modal" data-target="#confirm-delete"> Delete
								<i class="fa fa-trash-o"></i></button>
								</div>
							</c:if>
							
							</td>
							<td class=""><c:if test="${(intstart<11 || intstart==11)&&(intend>=12)}">
								<p>${eventValue.getModule().getModuleCode()}</p>
								<p>${eventValue.getRoom().getRoomNumber()}</p>
								<c:forEach  items="${eventValue.getStudentGroup()}" var="eventStudent">
									<p>${eventStudent.getGroupName()}</p>
								</c:forEach>
								<c:forEach  items="${eventValue.getTutor()}" var="eventTutor">
									<p>${eventTutor.getName()}</p>
								</c:forEach>
								<div>
								<a href="<c:url value="/editevent/${eventValue.getId()}"/>"><button class="btn btn-success">Edit <i class="fa fa-pencil-square-o"></i></button></a>
								<button class="btn btn-danger" data-href="<c:url value="/deleteevent/${eventValue.getId()}"/>" data-toggle="modal" data-target="#confirm-delete"> Delete
								<i class="fa fa-trash-o"></i></button>
								</div>
							</c:if></td>
							<td class="">
							<c:if test="${(intstart<12 || intstart==12)&&(intend>=13)}">
								<p>${eventValue.getModule().getModuleCode()}</p>
								<p>${eventValue.getRoom().getRoomNumber()}</p>
								<c:forEach  items="${eventValue.getStudentGroup()}" var="eventStudent">
									<p>${eventStudent.getGroupName()}</p>
								</c:forEach>
								<c:forEach  items="${eventValue.getTutor()}" var="eventTutor">
									<p>${eventTutor.getName()}</p>
								</c:forEach>
								<div>
								<a href="<c:url value="/editevent/${eventValue.getId()}"/>"><button class="btn btn-success">Edit <i class="fa fa-pencil-square-o"></i></button></a>
								<button class="btn btn-danger" data-href="<c:url value="/deleteevent/${eventValue.getId()}"/>" data-toggle="modal" data-target="#confirm-delete"> Delete
								<i class="fa fa-trash-o"></i></button>
								</div>
							</c:if></td>
							<td class="">
							<c:if test="${(intstart<13 || intstart==13)&&(intend>=14)}">
								<p>${eventValue.getModule().getModuleCode()}</p>
								<p>${eventValue.getRoom().getRoomNumber()}</p>
								<c:forEach  items="${eventValue.getStudentGroup()}" var="eventStudent">
									<p>${eventStudent.getGroupName()}</p>
								</c:forEach>
								<c:forEach  items="${eventValue.getTutor()}" var="eventTutor">
									<p>${eventTutor.getName()}</p>
								</c:forEach>
								<div>
								<a href="<c:url value="/editevent/${eventValue.getId()}"/>"><button class="btn btn-success">Edit <i class="fa fa-pencil-square-o"></i></button></a>
								<button class="btn btn-danger" data-href="<c:url value="/deleteevent/${eventValue.getId()}"/>" data-toggle="modal" data-target="#confirm-delete"> Delete
								<i class="fa fa-trash-o"></i></button>
								</div>
							</c:if>
							</td>
							<td class="">
								<c:if test="${(intstart<14 || intstart==14)&&(intend>=15)}">
								<p>${eventValue.getModule().getModuleCode()}</p>
								<p>${eventValue.getRoom().getRoomNumber()}</p>
								<c:forEach  items="${eventValue.getStudentGroup()}" var="eventStudent">
									<p>${eventStudent.getGroupName()}</p>
								</c:forEach>
								<c:forEach  items="${eventValue.getTutor()}" var="eventTutor">
									<p>${eventTutor.getName()}</p>
								</c:forEach>
								<div>
								<a href="<c:url value="/editevent/${eventValue.getId()}"/>"><button class="btn btn-success">Edit <i class="fa fa-pencil-square-o"></i></button></a>
								<button class="btn btn-danger" data-href="<c:url value="/deleteevent/${eventValue.getId()}"/>" data-toggle="modal" data-target="#confirm-delete"> Delete
								<i class="fa fa-trash-o"></i></button>
								</div>
							</c:if>
							</td>
							<td class="">
								<c:if test="${(intstart<15 || intstart==15)&&(intend>=16)}">
								<p>${eventValue.getModule().getModuleCode()}</p>
								<p>${eventValue.getRoom().getRoomNumber()}</p>
								<c:forEach  items="${eventValue.getStudentGroup()}" var="eventStudent">
									<p>${eventStudent.getGroupName()}</p>
								</c:forEach>
								<c:forEach  items="${eventValue.getTutor()}" var="eventTutor">
									<p>${eventTutor.getName()}</p>
								</c:forEach>
								<div>
								<a href="<c:url value="/editevent/${eventValue.getId()}"/>"><button class="btn btn-success">Edit <i class="fa fa-pencil-square-o"></i></button></a>
								<button class="btn btn-danger" data-href="<c:url value="/deleteevent/${eventValue.getId()}"/>" data-toggle="modal" data-target="#confirm-delete"> Delete
								<i class="fa fa-trash-o"></i></button>
								</div>
							</c:if>
							
							</td>
							<td class="">
								<c:if test="${(intstart<16 || intstart==16)&&(intend>=17)}">
								<p>${eventValue.getModule().getModuleCode()}</p>
								<p>${eventValue.getRoom().getRoomNumber()}</p>
								<c:forEach  items="${eventValue.getStudentGroup()}" var="eventStudent">
									<p>${eventStudent.getGroupName()}</p>
								</c:forEach>
								<c:forEach  items="${eventValue.getTutor()}" var="eventTutor">
									<p>${eventTutor.getName()}</p>
								</c:forEach>
								<div>
								<a href="<c:url value="/editevent/${eventValue.getId()}"/>"><button class="btn btn-success">Edit <i class="fa fa-pencil-square-o"></i></button></a>
								<button class="btn btn-danger" data-href="<c:url value="/deleteevent/${eventValue.getId()}"/>" data-toggle="modal" data-target="#confirm-delete"> Delete
								<i class="fa fa-trash-o"></i></button>
								</div>
							</c:if>
							</td>
							</tr>
						</c:if>
						</c:forEach>
						</c:forEach>
					</c:if>
						
					</table>
			</div>
		</div>
	</div>
	<div id="confirm-delete" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Confirmation</h4>
            </div>
            <div class="modal-body">
                <p>Are you sure that you want to delete this event</p>
                <p class="text-warning"><small>If you click "delete" your data will be lost permanently</small></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close <i class="fa fa-times-circle-o "></i></button>
                <button type="button" class="btn btn-danger btn-ok">Delete</button>
            </div>
        </div>
    </div>
</div>
	<script type="text/javascript">
	$(document).ready(function() {
	    $('#confirm-delete').on('show.bs.modal', function(e) {
	            var deleteid = $(e.relatedTarget).data('href');
	            $('.btn-ok').click(function() {
	            	 window.location.assign(deleteid)
	            });
	    });
	});
	</script>
</body>
</html>