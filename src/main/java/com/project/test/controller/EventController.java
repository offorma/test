package com.project.test.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.test.model.Event;
import com.project.test.model.Module;
import com.project.test.model.Room;
import com.project.test.model.StudentGroup;
import com.project.test.model.Tutor;
import com.project.test.model.User;
import com.project.test.service.EventServiceImpl;
import com.project.test.service.ModuleServiceImpl;
import com.project.test.service.RoomServiceImpl;
import com.project.test.service.StudentGroupServiceImpl;
import com.project.test.service.TutorServiceImpl;

@Controller
public class EventController {
	@Autowired
	ModuleServiceImpl moduleService;
	@Autowired
	RoomServiceImpl roomService;
	@Autowired
	StudentGroupServiceImpl studentService;
	@Autowired
	TutorServiceImpl tutorService;
	@Autowired
	EventServiceImpl eventService;
	
	@RequestMapping(value="/createevent", method = RequestMethod.GET)
	public ModelAndView student(HttpServletResponse response,HttpSession session) throws IOException{
		User user=(User)session.getAttribute("user");
		if(user==null){
			ModelAndView model = new ModelAndView("home");
			String accessmessage="Please login to access page";
			model.addObject("accessmessage",accessmessage);
			return model;
		}
		ArrayList<Module> module = (ArrayList<Module>) moduleService.getAll();
		ArrayList<Tutor> tutor = (ArrayList<Tutor>) tutorService.getAll();
		ArrayList<Room> room = (ArrayList<Room>) roomService.getAll();
		ArrayList<StudentGroup> student = (ArrayList<StudentGroup>) studentService.getAll();
		
		ModelAndView model = new ModelAndView("createevent");
		model.addObject("module", module);
		model.addObject("tutor", tutor);
		model.addObject("room", room);
		model.addObject("student", student);
		return model;
	}
	@RequestMapping(value="/searchevent", method = RequestMethod.GET)
	public ModelAndView viewEvents(HttpServletRequest request,HttpSession session, HttpServletResponse response,@ModelAttribute("msg") String msg,
            Model model) throws IOException{
		User user=(User)session.getAttribute("user");
		if(user==null){
			ModelAndView models = new ModelAndView("home");
			String accessmessage="Please login to access page";
			models.addObject("accessmessage",accessmessage);
			return models;
		}
		
		ArrayList<Module> module = (ArrayList<Module>) moduleService.getAll();
		ArrayList<Tutor> tutor = (ArrayList<Tutor>) tutorService.getAll();
		ArrayList<Room> room = (ArrayList<Room>) roomService.getAll();
		ArrayList<StudentGroup> student = (ArrayList<StudentGroup>) studentService.getAll();
		ModelAndView models = new ModelAndView("searchevent");
		models.addObject("module", module);
		models.addObject("tutor", tutor);
		models.addObject("room", room);
		models.addObject("student", student);
		
		return models;
	}
	@RequestMapping(value = "/viewevent", method = RequestMethod.GET)
	  public String showModule(HttpServletRequest request, HttpSession session,HttpServletResponse response,@ModelAttribute("events")ArrayList<Event> events,
	            Model model)throws IOException {
		User user=(User)session.getAttribute("user");
		if(user==null){
			String accessmessage="Please login to access page";
			model.addAttribute("accessmessage",accessmessage);
			return "home";
		}
		
	    return "viewevent";
	  }

	@RequestMapping(value="/searchevent", method = RequestMethod.POST)
	public ModelAndView searchevent(@RequestParam("date") String date,HttpSession session, @RequestParam("room") String room, @RequestParam("student")String student,
			@RequestParam("tutor") String tutor,@RequestParam("module") String module,final RedirectAttributes redirectAttributes, Model mod) throws IOException, ParseException{
		
		
		User user=(User)session.getAttribute("user");
		if(user==null){
			ModelAndView models = new ModelAndView("home");
			String accessmessage="Please login to access page";
			models.addObject("accessmessage",accessmessage);
			return models;
		}
		
		System.out.println(date.length());
		Date d =new Date();
		LocalDate eDate = null;
		if(date.length()>0){
			eDate= eventService.parseDate(date);
			d = new SimpleDateFormat("yyyy-MM-dd").parse(eDate.toString());
		}
		ModelAndView model = new ModelAndView();
		if ((room.length()>0)&&(date.length()>0)){
			try{
				long num = Long.parseLong(room);
				ArrayList<Event>events=(ArrayList<Event>) eventService.findByRoomAndDate(num, d);
				ArrayList<Date>dates=new ArrayList<Date>();
				for(Event tevents:events){
					if(dates.contains(tevents.getDate())){
						
					}else{
					dates.add(tevents.getDate());
					}
				}
				model.addObject("dates", dates);
				model.setViewName("viewevent");
				model.addObject("events", events);
				
				
			}catch(NumberFormatException ex){
				ex.printStackTrace();
				}
		}
		if ((module.length()>0)&&(date.length()>0)){
			try{
				long num = Long.parseLong(module);
				ArrayList<Event>events=(ArrayList<Event>) eventService.findByModuleAndDate(num, d);
				ArrayList<Date>dates=new ArrayList<Date>();
				for(Event tevents:events){
					if(dates.contains(tevents.getDate())){
						
					}else{
					dates.add(tevents.getDate());
					}
				}
				model.addObject("dates", dates);
				model.setViewName("viewevent");
				model.addObject("events", events);
			}catch(NumberFormatException ex){
				ex.printStackTrace();
				}
		}
		if ((tutor.length()>0)&&(date.length()>0)){
			try{
				long num = Long.parseLong(tutor);
				ArrayList<Event>events=(ArrayList<Event>) eventService.findByTutorAndDate(num, d);
				ArrayList<Date>dates=new ArrayList<Date>();
				for(Event tevents:events){
					if(dates.contains(tevents.getDate())){
						
					}else{
					dates.add(tevents.getDate());
					}
				}
				model.addObject("dates", dates);
				model.setViewName("viewevent");
				model.addObject("events", events);
			}catch(NumberFormatException ex){
				ex.printStackTrace();
				}
		}
		if ((student.length()>0)&&(date.length()>0)){
			try{
				long num = Long.parseLong(student);
				ArrayList<Event>events=(ArrayList<Event>) eventService.findByStudentAndDate(num, d);
				ArrayList<Date>dates=new ArrayList<Date>();
				for(Event tevents:events){
					if(dates.contains(tevents.getDate())){
						
					}else{
					dates.add(tevents.getDate());
					}
				}
				model.addObject("dates", dates);
				model.setViewName("viewevent");
				model.addObject("events", events);
			}catch(NumberFormatException ex){
				ex.printStackTrace();
				}
		}
		if ((room.length()>0)&&(date.length()==0)&&(student.length()==0)&&(tutor.length()==0)&&(module.length()==0)){
			try{
				long num = Long.parseLong(room);
				ArrayList<Event>events=(ArrayList<Event>) eventService.findEventsInRoom(num);
				ArrayList<Date>dates=new ArrayList<Date>();
				for(Event tevents:events){
					if(dates.contains(tevents.getDate())){
						
					}else{
					dates.add(tevents.getDate());
					}
				}
				model.addObject("dates", dates);
				model.setViewName("viewevent");
				model.addObject("events", events);
			}catch(NumberFormatException ex){
				ex.printStackTrace();
				}
		}
		if ((module.length()>0)&&(date.length()==0)&&(student.length()==0)&&(tutor.length()==0)&&(room.length()==0)){
			try{
				long num = Long.parseLong(module);
				ArrayList<Event>events=(ArrayList<Event>) eventService.findEventsWithModule(num);
				ArrayList<Date>dates=new ArrayList<Date>();
				for(Event tevents:events){
					if(dates.contains(tevents.getDate())){
						
					}else{
					dates.add(tevents.getDate());
					}
				}
				model.addObject("dates", dates);
				model.setViewName("viewevent");
				model.addObject("events", events);
			}catch(NumberFormatException ex){
				ex.printStackTrace();
				}
		}
		if ((student.length()>0)&&(date.length()==0)&&(module.length()==0)&&(tutor.length()==0)&&(room.length()==0)){
			try{
				long num = Long.parseLong(student);
				ArrayList<Event>events=(ArrayList<Event>) eventService.findEventsWithStudent(num);
				ArrayList<Date>dates=new ArrayList<Date>();
				for(Event tevents:events){
					if(dates.contains(tevents.getDate())){
						
					}else{
					dates.add(tevents.getDate());
					}
				}
				model.addObject("dates", dates);
				model.setViewName("viewevent");
				model.addObject("events", events);
			}catch(NumberFormatException ex){
				ex.printStackTrace();
				}
		}
		if ((student.length()==0)&&(date.length()==0)&&(module.length()==0)&&(tutor.length()>0)&&(room.length()==0)){
			try{
				long num = Long.parseLong(tutor);
				ArrayList<Event>events=(ArrayList<Event>) eventService.findEventsWithTutor(num);
				ArrayList<Date>dates=new ArrayList<Date>();
				for(Event tevents:events){
					if(dates.contains(tevents.getDate())){
						
					}else{
					dates.add(tevents.getDate());
					}
				}
				model.addObject("dates", dates);
				model.setViewName("viewevent");
				model.addObject("events", events);
				
			}catch(NumberFormatException ex){
				ex.printStackTrace();
				}
		}
		if ((student.length()==0)&&(date.length()>0)&&(module.length()==0)&&(tutor.length()==0)&&(room.length()==0)){
			
				ArrayList<Event>events=(ArrayList<Event>) eventService.findEventByDate(d);
				ArrayList<Date>dates=new ArrayList<Date>();
				for(Event tevents:events){
					if(dates.contains(tevents.getDate())){
						
					}else{
					dates.add(tevents.getDate());
					}
				}
				model.addObject("dates", dates);
				model.setViewName("viewevent");
				model.addObject("events", events);
			
		}
		if ((student.length()==0)&&(date.length()==0)&&(module.length()==0)&&(tutor.length()==0)&&(room.length()==0)){
			String msg= "Please select a date and any of the fields to show events involving the field for that date "
					+ "or select a field to show all events for that field";
			mod.addAttribute("msg", msg);
			model.setViewName("redirect:/searchevent");
		
		}
		return model;
	}
	@RequestMapping(value="/createevent", method = RequestMethod.POST)
	public String createstudent(Map<String, Object> map,HttpSession session, @RequestParam("date") String date, @RequestParam("start") String start, 
			 @RequestParam("end") String end,@RequestParam("room") String room,@RequestParam("module") String mod,
			 @RequestParam("student") ArrayList<String> student,@RequestParam("tutor") ArrayList<String> tutor,final RedirectAttributes redirectAttributes, Model model) throws IOException{
		
		User user=(User)session.getAttribute("user");
		if(user==null){
			String accessmessage="Please login to access page";
			model.addAttribute("accessmessage",accessmessage);
			return "home";
		}
		String st= null;
	
		LocalDate eventDate = null;
		String eventStartTime =null;
		String eventEndTime =null;
		Module module = null;
		Room rm =null;
		
		Event ev = new Event();
		
		ArrayList<StudentGroup> studentGroup = null;
		ArrayList<Tutor> tutors = null;
		
		if(eventService.parseDate(date)instanceof LocalDate){
			LocalDate eDate= eventService.parseDate(date);//convert string date to LocalDate
			LocalDate today = LocalDate.now();//Get the current date
			if(eDate.isBefore(today)){//check if date is in the past
				 String pastDate = "Date cannot be in the past";
					redirectAttributes.addFlashAttribute("pastDate", pastDate);
					st="redirect:/createevent";
			}else{
			eventDate = eDate;
			}
		}
		if(!start.equals(null)){
			eventStartTime=start;	 
		}else{
			String startEvent = "Start time must be in the format HH:mm";
			redirectAttributes.addFlashAttribute("startEvent", startEvent);
			st="redirect:/createevent";	
		}
		if(!end.equals(null)){
			eventEndTime=end;	
		}else{
			String endEvent = "Finish time must be in the format HH:mm";
			redirectAttributes.addFlashAttribute("endEvent", endEvent);
			st="redirect:/createevent";	
		}
		if ((room!=null)){
			try{
				Long num = Long.parseLong(room);
				rm =roomService.findRoom(num);
			}catch(NumberFormatException ex){
				ex.printStackTrace();
				}

		}else{
			String message = "Room field is required";
			redirectAttributes.addFlashAttribute("message", message);
			st="redirect:/createevent";
		}
		
		if (mod!=null){
			try{
				Long num = Long.parseLong(mod);
				module =moduleService.findModule(num);
			}catch(NumberFormatException Ex){
				Ex.printStackTrace();
				}
			
		}else{
			String message = "Module field is required";
			redirectAttributes.addFlashAttribute("message", message);
			st="redirect:/createevent";
		}
		if (student.size()>0){//check if student array has content
			 studentGroup=eventService.getStudents(student);//get student group from event service
		
		}else{
			String message = "Student group is a required field ";
			redirectAttributes.addFlashAttribute("message", message);
			st="redirect:/createevent";
		}
		if (tutor.size()>0){
			 tutors=eventService.getTutor(tutor);
			
		}else{
			String message = "Tutor is a required field ";
			redirectAttributes.addFlashAttribute("message", message);
			st="redirect:/createevent";
		}
		if((eventDate!=null)&&(eventStartTime !=null)&&(eventEndTime!=null)&&(module!=null)
				&&(rm !=null)&&(studentGroup != null)&&(tutors != null)){
			ev.setDate(java.sql.Date.valueOf(eventDate));
			ev.setStartTime(eventStartTime);
			ev.setEndTime(eventEndTime);
			
			ev.setModule(module);
			ev.setRoom(rm);
			ev.setStudentGroup(studentGroup);
			ev.setTutor(tutors);
			boolean validateTime = eventService.validateTime(ev.getStartTime(), ev.getEndTime());
			if(validateTime==false){
				String timeMessage ="Finish time cannot be before Start time or the same time";
				redirectAttributes.addFlashAttribute("timeMessage", timeMessage);
			}
			boolean checkCapacity = eventService.checkCapacity(ev.getRoom(), ev.getStudentGroup());
			if(checkCapacity==false){
				String roomCapMessage ="The room you have selected do not have adequate capacity";
				redirectAttributes.addFlashAttribute("roomCapMessage", roomCapMessage);
			}
			boolean isClashStudent =false;
			boolean isClashTutor =false;
			boolean isClashRoom =false;
			System.out.println("/n this is valid "+validateTime+"/n");
			System.out.println("/n this is capacity "+checkCapacity+"/n");
			
			ArrayList<Event> clashStudent = eventService.checkTimeClashStudent(ev.getDate(), ev.getStudentGroup(), ev.getStartTime(), ev.getEndTime());
			if(clashStudent.size()>0){
				isClashStudent=true;
				for(Event c:clashStudent){
					System.out.println(isClashStudent+" "+c.getId());
					String studentMessage ="There is a clash with the student group you have selected";
					redirectAttributes.addFlashAttribute("clashStudent", clashStudent);
					redirectAttributes.addFlashAttribute("studentMessage", studentMessage);
				}
			}
				ArrayList<Event> clashTutor = eventService.checkTimeClashTutor(ev.getDate(), ev.getTutor(), ev.getStartTime(), ev.getEndTime());
				if(clashTutor.size()>0){
					isClashTutor=true;
					for(Event c:clashTutor){
						System.out.println(isClashTutor+" "+c.getId());
						String tutorMessage ="There is a clash with the tutor you have selected";
						redirectAttributes.addFlashAttribute("clashTutor", clashTutor);
						redirectAttributes.addFlashAttribute("tutorMessage", tutorMessage);
					}
			}
				ArrayList<Event> clashRoom = eventService.checkTimeClashRoom(ev.getDate(), ev.getRoom(), ev.getStartTime(), ev.getEndTime());
				if(clashRoom.size()>0){
					isClashRoom=true;
					for(Event c:clashRoom){
						System.out.println(isClashRoom+" "+c.getId());
						String roomMessage ="There is a clash with the room you have selected";
						redirectAttributes.addFlashAttribute("clashRoom", clashRoom);
						redirectAttributes.addFlashAttribute("roomMessage", roomMessage);
					}
			}
			if((validateTime==true)&&(checkCapacity==true)&&(isClashStudent==false)&&(isClashTutor==false)&&(isClashRoom==false))
			{
			eventService.create(ev);
			String message = "Event was successfully Scheduled ";
			redirectAttributes.addFlashAttribute("message", message);
			st="redirect:/createevent";
			}else{
				st="redirect:/createevent";
			}
			
		}
		return st;
	}
	 @RequestMapping(value="/editevent/{id}", method=RequestMethod.GET)
	    public ModelAndView editTeamPage(@PathVariable Long id,HttpSession session) {
		 
		 User user=(User)session.getAttribute("user");
			if(user==null){
				ModelAndView models = new ModelAndView("home");
				String accessmessage="Please login to access page";
				models.addObject("accessmessage",accessmessage);
				return models;
			}
			
			ArrayList<Module> module = (ArrayList<Module>) moduleService.getAll();
			ArrayList<Tutor> tutor = (ArrayList<Tutor>) tutorService.getAll();
			ArrayList<Room> room = (ArrayList<Room>) roomService.getAll();
			ArrayList<StudentGroup> student = (ArrayList<StudentGroup>) studentService.getAll();
			
	        ModelAndView modelAndView = new ModelAndView("editevent");
	        Event event = eventService.findEvent(id);
	        Date date=event.getDate();
	        SimpleDateFormat pd = new SimpleDateFormat("dd/MM/yyyy");
	        String newdate= pd.format(date);
	        List<Tutor> eventtutor = (List<Tutor>) event.getTutor();
	        List<StudentGroup> eventstudent = (List<StudentGroup>) event.getStudentGroup();
	        
	        modelAndView.addObject("eventstudent",eventstudent);
	        modelAndView.addObject("eventtutor",eventtutor);
	        modelAndView.addObject("newdate",newdate);
	        modelAndView.addObject("event",event);
	        modelAndView.addObject("module", module);
			modelAndView.addObject("tutor", tutor);
			modelAndView.addObject("room", room);
			modelAndView.addObject("student", student);
	        return modelAndView;
	    }
	@RequestMapping(value="/deleteevent/{id}", method=RequestMethod.GET)
    public String delete(@PathVariable Long id,final RedirectAttributes redirectAttributes, HttpSession session, Model model) {
		
		User user=(User)session.getAttribute("user");
		if(user==null){
			String accessmessage="Please login to access page";
			model.addAttribute("accessmessage",accessmessage);
			return "home";
		}
       
        eventService.delete(id);

        String messages = "Event was deleted successfully";
		redirectAttributes.addFlashAttribute("messages", messages);
		String st="redirect:/searchevent";
		return st;
       
    }
	@RequestMapping(value="/editevent/{id}", method = RequestMethod.POST)
	public String editEvent(Map<String, Object> map,@PathVariable Long id,HttpSession session, @RequestParam("date") String date, @RequestParam("start") String start, 
			 @RequestParam("end") String end,@RequestParam("room") String room,@RequestParam("module") String mod,
			 @RequestParam("student") ArrayList<String> student,@RequestParam("tutor") ArrayList<String> tutor,final RedirectAttributes redirectAttributes, Model model) throws IOException{
		
		User user=(User)session.getAttribute("user");
		if(user==null){
			String accessmessage="Please login to access page";
			model.addAttribute("accessmessage",accessmessage);
			return "home";
		}
		String st= null;
	
		LocalDate eventDate = null;
		String eventStartTime =null;
		String eventEndTime =null;
		Module module = null;
		Room rm =null;
		
		Event ev = new Event();
		
		ArrayList<StudentGroup> studentGroup = null;
		ArrayList<Tutor> tutors = null;
		
		if(eventService.parseDate(date)instanceof LocalDate){
			LocalDate eDate= eventService.parseDate(date);//convert string date to LocalDate
			LocalDate today = LocalDate.now();//Get the current date
			if(eDate.isBefore(today)){//check if date is in the past
				 String pastDate = "Date cannot be in the past";
					redirectAttributes.addFlashAttribute("pastDate", pastDate);
					st="redirect:/editevent/"+id;
			}else{
			eventDate = eDate;
			}
		}
		if(!start.equals(null)){
			eventStartTime=start;	 
		}else{
			String startEvent = "Start time must be in the format HH:mm";
			redirectAttributes.addFlashAttribute("startEvent", startEvent);
			st="redirect:/editevent/"+id;	
		}
		if(!end.equals(null)){
			eventEndTime=end;	
		}else{
			String endEvent = "Finish time must be in the format HH:mm";
			redirectAttributes.addFlashAttribute("endEvent", endEvent);
			st="redirect:/editevent/"+id;	
		}
		if ((room!=null)){
			try{
				Long num = Long.parseLong(room);
				rm =roomService.findRoom(num);
			}catch(NumberFormatException ex){
				ex.printStackTrace();
				}

		}else{
			String message = "Room field is required";
			redirectAttributes.addFlashAttribute("message", message);
			st="redirect:/editevent/"+id;
		}
		
		if (mod!=null){
			try{
				Long num = Long.parseLong(mod);
				module =moduleService.findModule(num);
			}catch(NumberFormatException Ex){
				Ex.printStackTrace();
				}
			
		}else{
			String message = "Module field is required";
			redirectAttributes.addFlashAttribute("message", message);
			st="redirect:/editevent/"+id;
		}
		if (student.size()>0){//check if student array has content
			 studentGroup=eventService.getStudents(student);//get student group from event service
		
		}else{
			String message = "Student group is a required field ";
			redirectAttributes.addFlashAttribute("message", message);
			st="redirect:/editevent/"+id;
		}
		if (tutor.size()>0){
			 tutors=eventService.getTutor(tutor);
			
		}else{
			String message = "Tutor is a required field ";
			redirectAttributes.addFlashAttribute("message", message);
			st="redirect:/editevent/"+id;
		}
		if((eventDate!=null)&&(eventStartTime !=null)&&(eventEndTime!=null)&&(module!=null)
				&&(rm !=null)&&(studentGroup != null)&&(tutors != null)){
			ev.setDate(java.sql.Date.valueOf(eventDate));
			ev.setStartTime(eventStartTime);
			ev.setEndTime(eventEndTime);
			
			ev.setModule(module);
			ev.setRoom(rm);
			ev.setStudentGroup(studentGroup);
			ev.setTutor(tutors);
			boolean validateTime = eventService.validateTime(ev.getStartTime(), ev.getEndTime());
			if(validateTime==false){
				String timeMessage ="Finish time cannot be before Start time or the same time";
				redirectAttributes.addFlashAttribute("timeMessage", timeMessage);
			}
			boolean checkCapacity = eventService.checkCapacity(ev.getRoom(), ev.getStudentGroup());
			if(checkCapacity==false){
				String roomCapMessage ="The room you have selected do not have adequate capacity";
				redirectAttributes.addFlashAttribute("roomCapMessage", roomCapMessage);
			}
			boolean isClashStudent =false;
			boolean isClashTutor =false;
			boolean isClashRoom =false;
			
			ArrayList<Event> clashStudent = eventService.checkTimeClashStudent(ev.getDate(), ev.getStudentGroup(), ev.getStartTime(), ev.getEndTime());
			if(clashStudent.size()>0){
				isClashStudent=true;
				for(Event c:clashStudent){
					System.out.println(isClashStudent+" "+c.getId());
					String studentMessage ="There is a clash with the student group you have selected";
					redirectAttributes.addFlashAttribute("clashStudent", clashStudent);
					redirectAttributes.addFlashAttribute("studentMessage", studentMessage);
				}
			}
				ArrayList<Event> clashTutor = eventService.checkTimeClashTutor(ev.getDate(), ev.getTutor(), ev.getStartTime(), ev.getEndTime());
				if(clashTutor.size()>0){
					isClashTutor=true;
					for(Event c:clashTutor){
						System.out.println(isClashTutor+" "+c.getId());
						String tutorMessage ="There is a clash with the tutor you have selected";
						redirectAttributes.addFlashAttribute("clashTutor", clashTutor);
						redirectAttributes.addFlashAttribute("tutorMessage", tutorMessage);
					}
			}
				ArrayList<Event> clashRoom = eventService.checkTimeClashRoom(ev.getDate(), ev.getRoom(), ev.getStartTime(), ev.getEndTime());
				if(clashRoom.size()>0){
					isClashRoom=true;
					for(Event c:clashRoom){
						System.out.println(isClashRoom+" "+c.getId());
						String roomMessage ="There is a clash with the room you have selected";
						redirectAttributes.addFlashAttribute("clashRoom", clashRoom);
						redirectAttributes.addFlashAttribute("roomMessage", roomMessage);
					}
			}
			if((validateTime==true)&&(checkCapacity==true)&&(isClashStudent==false)&&(isClashTutor==false)&&(isClashRoom==false))
			{
			eventService.update(id, ev);
			String message = "Event was edited successfully";
			redirectAttributes.addFlashAttribute("message", message);
			st="redirect:/createevent";
			}else{
				st="redirect:/createevent";
			}
			
		}
		return st;
	}
	
}
