
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
<h1 class ="title">Schedule Events</h1>
	<div class="container">
		<div class="row landing mymargin">
			<div class="col-md-4">
				<c:if test="${clashStudent.size() > 0}">
					<div class="row ">
					<hr><p>Selected Student group has clashed with</p><hr>
						<div id="myCarousel" class="carousel slide" data-ride="carousel">
							  <!-- Indicators -->
							  <ol class="carousel-indicators">
							  	<c:forEach  items="${clashStudent}" var="eventValue">
							  	<c:choose>
								    <c:when test="${clashStudent.indexOf(eventValue)==0}">
								        <c:set var="displayClass" value="active"></c:set>
								    </c:when>    
								    <c:otherwise>
								        <c:set var="displayClass" value=""></c:set>
								    </c:otherwise>
								</c:choose>
							   		 <li data-target="#myCarousel" data-slide-to="${clashStudent.indexOf(eventValue) }" class="${displayClass}"></li>
							    </c:forEach>
							  </ol>
						  <!-- Wrapper for slides -->
						  <div class="carousel-inner" role="listbox">
						  <c:forEach  items="${clashStudent}" var="eventValue">
								<c:choose>
								    <c:when test="${clashStudent.indexOf(eventValue)==0}">
								        <c:set var="disClass" value="active"></c:set>
								    </c:when>    
								    <c:otherwise>
								        <c:set var="disClass" value=""></c:set>
								    </c:otherwise>
								</c:choose>
							   	<div class="item ${disClass} center-block">
							   	<div class="errordiv">
							      <p>Event Date: "${eventValue.getDate()}"</p>
							      <p>Event Start Time: "${eventValue.getStartTime()}"</p>
							      <p>Event End Time: "${eventValue.getEndTime()}"</p>
							      <p>Event Room: "${eventValue.getRoom().getRoomNumber()}"</p>
							      <p>Event Module: "${eventValue.getModule().getModuleCode()}"</p>
							      <c:forEach  items="${eventValue.getStudentGroup()}" var="studentValue">
							      <p>Event group: "${studentValue.getGroupName()}"</p>
							      
							      </c:forEach>
							      </div>
							    </div>
							    </c:forEach> 
						  </div>
						  <!-- Left and right controls -->
						  <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
						    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
						    <span class="sr-only">Previous</span>
						  </a>
						  <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
						    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
						    <span class="sr-only">Next</span>
						  </a>
					</div>
				</div>
			</c:if>
			<c:if test="${clashTutor.size() > 0}">
				<hr><p>Selected Tutor has clashed with</p><hr>
				<div class="row ">
						<div id="myCarousel" class="carousel slide" data-ride="carousel">
							  <!-- Indicators -->
							  <ol class="carousel-indicators">
							  	<c:forEach  items="${clashTutor}" var="eventValue">
							  	<c:choose>
								    <c:when test="${clashTutor.indexOf(eventValue)==0}">
								        <c:set var="displayClass" value="active"></c:set>
								    </c:when>    
								    <c:otherwise>
								        <c:set var="displayClass" value=""></c:set>
								    </c:otherwise>
								</c:choose>
							   		 <li data-target="#myCarousel" data-slide-to="${clashTutor.indexOf(eventValue) }" class="${displayClass}"></li>
							    </c:forEach>
							  </ol>
						  <!-- Wrapper for slides -->
						  <div class="carousel-inner" role="listbox">
						  <c:forEach  items="${clashTutor}" var="eventValue">
								<c:choose>
								    <c:when test="${clashTutor.indexOf(eventValue)==0}">
								        <c:set var="disClass" value="active"></c:set>
								    </c:when>    
								    <c:otherwise>
								        <c:set var="disClass" value=""></c:set>
								    </c:otherwise>
								</c:choose>
							   	<div class="item ${disClass} center-block">
							   	<div class="errordiv">
							      <p>Event Date: "${eventValue.getDate()}"</p>
							      <p>Event Start Time: "${eventValue.getStartTime()}"</p>
							      <p>Event End Time: "${eventValue.getEndTime()}"</p>
							      <p>Event Room: "${eventValue.getRoom().getRoomNumber()}"</p>
							      <p>Event Module: "${eventValue.getModule().getModuleCode()}"</p>
							      <c:forEach  items="${eventValue.getTutor()}" var="tutorValue">
							      <p>Event Tutor: "${tutorValue.getName()}"</p>
							      
							      </c:forEach>
							      </div>
							    </div>
							    </c:forEach> 
						  </div>
						  <!-- Left and right controls -->
						  <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
						    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
						    <span class="sr-only">Previous</span>
						  </a>
						  <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
						    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
						    <span class="sr-only">Next</span>
						  </a>
					</div>
				</div>
			</c:if>
			<c:if test="${clashRoom.size() > 0}">
				<hr><p>Selected Room has clashed with</p><hr>
				<div class="row ">
				<p></p>
						<div id="myCarousel" class="carousel slide" data-ride="carousel">
							  <!-- Indicators -->
							  <ol class="carousel-indicators">
							  	<c:forEach  items="${clashRoom}" var="eventValue">
							  	<c:choose>
								    <c:when test="${clashRoom.indexOf(eventValue)==0}">
								        <c:set var="displayClass" value="active"></c:set>
								    </c:when>    
								    <c:otherwise>
								        <c:set var="displayClass" value=""></c:set>
								    </c:otherwise>
								</c:choose>
							   		 <li data-target="#myCarousel" data-slide-to="${clashRoom.indexOf(eventValue) }" class="${displayClass}"></li>
							    </c:forEach>
							  </ol>
						  <!-- Wrapper for slides -->
						  <div class="carousel-inner" role="listbox">
						  <c:forEach  items="${clashRoom}" var="eventValue">
								<c:choose>
								    <c:when test="${clashRoom.indexOf(eventValue)==0}">
								        <c:set var="disClass" value="active"></c:set>
								    </c:when>    
								    <c:otherwise>
								        <c:set var="disClass" value=""></c:set>
								    </c:otherwise>
								</c:choose>
							   	<div class="item ${disClass} center-block mymargin">
							   	<div class="errordiv">
							      <p>Event Date: "${eventValue.getDate()}"</p>
							      <p>Event Start Time: "${eventValue.getStartTime()}"</p>
							      <p>Event End Time: "${eventValue.getEndTime()}"</p>
							      <p>Event Room: "${eventValue.getRoom().getRoomNumber()}"</p>
							      <p>Event Module: "${eventValue.getModule().getModuleCode()}"</p>
							      </div>
							    </div>
							    </c:forEach> 
						  </div>
						  <!-- Left and right controls -->
						  <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
						    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
						    <span class="sr-only">Previous</span>
						  </a>
						  <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
						    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
						    <span class="sr-only">Next</span>
						  </a>
					</div>
				</div>
			</c:if>
		</div>
		
	
		<div class="col-md-7 pull-right ">
			<c:choose>
				    <c:when test="${message.length()>0}">
				    		<div class='alert alert-success'><span class=''>${message}</span> </div>
				    </c:when>
			   </c:choose>
			   
			   <c:choose>
				    <c:when test="${pastDate.length()>0}">
				    		<div class='alert alert-danger'><span class=''>${pastDate}</span> </div>
				    </c:when>
			   </c:choose>
			   <c:choose>
				    <c:when test="${roomMessage.length()>0}">
				    		<div class='alert alert-danger'><span class=''>${roomMessage}</span> </div>
				    </c:when>
			   </c:choose>
			   <c:choose>
				    <c:when test="${tutorMessage.length()>0}">
				    		<div class='alert alert-danger'><span class=''>${tutorMessage}</span> </div>
				    </c:when>
			   </c:choose>
			   			   <c:choose>
				    <c:when test="${studentMessage.length()>0}">
				    		<div class='alert alert-danger'><span class=''>${studentMessage}</span> </div>
				    </c:when>
			   </c:choose>
			   	<c:choose>
				    <c:when test="${roomCapMessage.length()>0}">
				    		<div class='alert alert-danger'><span class=''>${roomCapMessage}</span> </div>
				    </c:when>
			   </c:choose>
			 
			   	<c:choose>
				    <c:when test="${timeMessage.length()>0}">
				    		<div class='alert alert-danger'><span class=''>${timeMessage}</span> </div>
				    </c:when>
			   </c:choose>
			   
			   
				<form action="createevent" method="post">
					<div class="form-group">
						<label for="Studentgroup">Date</label>
							<div class="input-group">
       							<div class="input-group-addon">
        							<i class="fa fa-calendar-check-o"></i>
      							</div>
      							<input class="form-control" id="date" required= "required" name="date" placeholder="DD/MM/YYYY" type="date"></input>
     						</div>
					</div>
					<div class="form-group">
						<label for="start">Start Time</label>
						<select class="selectpicker form-control" data-live-search="true" name="start" required= "required">
						<option value="" selected disabled>Please select start time</option>
						  <option data-tokens="09:00" value="09:00">09:00</option>
						  <option data-tokens="10:00" value="10:00">10:00</option>
						  <option data-tokens="11:00" value="11:00">11:00</option>
						  <option data-tokens="12:00" value="12:00">12:00</option>
						  <option data-tokens="13:00" value="13:00">13:00</option>
						  <option data-tokens="14:00" value="14:00">14:00</option>
						  <option data-tokens="15:00" value="15:00">15:00</option>
						  <option data-tokens="16:00" value="16:00">16:00</option>
						  
						 </select>
					</div>
					<div class="form-group">
						<label for="end">Finish Time</label>
						<select class="selectpicker form-control" data-live-search="true" name="end" required= "required">
						<option value="" selected disabled>Please select finish time</option>
						  <option data-tokens="10:00" value="10:00">10:00</option>
						  <option data-tokens="11:00" value="11:00">11:00</option>
						  <option data-tokens="12:00" value="12:00">12:00</option>
						  <option data-tokens="13:00" value="13:00">13:00</option>
						  <option data-tokens="14:00" value="14:00">14:00</option>
						  <option data-tokens="15:00" value="15:00">15:00</option>
						  <option data-tokens="16:00" value="16:00">16:00</option>
						  <option data-tokens="17:00" value="17:00">17:00</option>
						 </select>
					</div>
					 <div class="form-group">
						<label for="end">Room</label>
						<select class="selectpicker form-control" data-live-search="true" name="room" required= "required">
						<option value="" selected disabled>Please select</option>
							  <c:if test="${room.size() > 0}">
								<c:forEach  items="${room}" var="roomValue">
									<option data-tokens="${roomValue.getRoomNumber()},${roomValue.getCapacity()}" value="${roomValue.getId()}">${roomValue.getRoomNumber()}</option>
								</c:forEach>
							 </c:if>
						 </select>
					</div>
					<div class="form-group">
						<label for="end">Module</label>
						<select class="selectpicker form-control" data-live-search="true" name="module" required= "required" >
						<option value="" selected disabled>Please select</option>
						    <c:if test="${module.size() > 0}">
						<c:forEach  items="${module}" var="moduleValue">
								<option data-tokens="${moduleValue.getModuleCode()},${moduleValue.getModuleName()}" value="${moduleValue.getId()}">${moduleValue.getModuleCode()}</option>	
						</c:forEach>
					</c:if>
						 </select>
					</div>
					<div class="form-group">
						<label for="student">Student Group</label>
						<select class="selectpicker form-control" data-live-search="true" required= "required" name="student" multiple>
						     <c:if test="${student.size() > 0}">
						<c:forEach  items="${student}" var="studentValue">
								<option data-tokens="${studentValue.getGroupName()}" value="${studentValue.getGroupId()}">${studentValue.getGroupName()}</option>	
						</c:forEach>
					</c:if>
						 </select>
					</div>
					<div class="form-group">
						<label for="tutor">Tutor</label>
						<select class="selectpicker form-control" data-live-search="true" name="tutor" multiple>
						      <c:if test="${tutor.size() > 0}">
						<c:forEach  items="${tutor}" var="tutorValue">
								<option data-tokens="${tutorValue.getName()}" value="${tutorValue.getId()}">${tutorValue.getName()}</option>	
						</c:forEach>
					</c:if>
						 </select>
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
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