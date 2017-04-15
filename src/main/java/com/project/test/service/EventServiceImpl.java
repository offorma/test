package com.project.test.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.test.model.Event;
import com.project.test.model.Module;
import com.project.test.model.Room;
import com.project.test.model.StudentGroup;
import com.project.test.model.Tutor;
import com.project.test.repositories.EventRepositoryImpl;
import com.project.test.repositories.ModuleRepositoryImpl;
import com.project.test.repositories.RoomRepositoryImpl;
import com.project.test.repositories.StudentGroupRepositoryImpl;
import com.project.test.repositories.TutorRepositoryImpl;

@Service
public class EventServiceImpl implements EventService{
	@Autowired
	RoomRepositoryImpl roomRepo;
	@Autowired
	StudentGroupRepositoryImpl studentRepo;
	@Autowired
	TutorRepositoryImpl tutorRepo;
	@Autowired
	EventRepositoryImpl eventRepo;
	@Autowired
	ModuleRepositoryImpl moduleRepo;

	public LocalDate parseDate(String date){
		LocalDate localDate = null;
		if(!date.isEmpty()){
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			//convert String to LocalDate
			localDate = LocalDate.parse(date, formatter);
		}
		return localDate;
	}

	public ArrayList<StudentGroup> getStudents(ArrayList<String> students){
		ArrayList<StudentGroup> estudent =new ArrayList<StudentGroup>();
		if(students.size()>0){
			for( String student:students){
				Long num= Long.parseLong(student);
				StudentGroup studentGroup = studentRepo.findStudentGroup(num);
				estudent.add(studentGroup);
			}
		}
		return estudent;
	}
	public ArrayList<Tutor> getTutor(ArrayList<String> tutors){
		ArrayList<Tutor> etutor =new ArrayList<Tutor>();
		if(tutors.size()>0){
			for( String tutor:tutors){
				Long num= Long.parseLong(tutor);
				Tutor mytut = tutorRepo.findTutor(num);
				etutor.add(mytut);
			}
		}
		return etutor;
	}
	@Override
	public void create(Event event) {
		eventRepo.create(event);
	}
	@Override
	public void update(long id, Event event) {
		eventRepo.update(id, event);	
	}
	@Override
	public List<Event> findEventByDate(Date date) {
			return eventRepo.findEventByDate(date);
	}
	@Override
	public List<Event> findEventByRoomAndDate(Room room, Date date) {
		ArrayList<Event> myEvent= (ArrayList<Event>) eventRepo.findEventByDate(date);
		ArrayList<Event> finalEvent= new ArrayList<Event>();
		for(Event event:myEvent){
			if (event.getRoom().getId()==room.getId()){
				finalEvent.add(event);
			}
		}
		return finalEvent;
	}
	@Override
	public List<Event> findEventByRoom(long id) {
			return eventRepo.findByTutorName(id);
	}
	@Override
	public List<Event> findByTutorName(long un) {
		// TODO Auto-generated method stub
		return eventRepo.findByTutorName(un);
	}
	@Override
	public Event findEvent(Long id){
		return eventRepo.findEvent(id);
	}
	@Override
	public void delete(Long Id) {
		
		eventRepo.delete(Id);
	
	}
	@Override
	public List<Event> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	public ArrayList<Event> checkTimeClashRoom(Date date, Room room, String startTime2,String endTime2){
		ArrayList<Event> eventsofDate=(ArrayList<Event>) findEventByRoomAndDate(room, date);

		ArrayList<Event> clashedEvents = new ArrayList<Event>();
		if(eventsofDate.size()>0){
			for(Event single :eventsofDate){
				String startTime1=single.getStartTime();
				String endTime1=single.getEndTime();
				
				String[] startSplit1 = startTime1.split(":");//split start time for event one into an array
				int startHour1 = Integer.valueOf(startSplit1[0]); //int for first start time
				
				String[] endSplit1 = endTime1.split(":");
				int endHour1 = Integer.valueOf(endSplit1[0]);
				
				String[] startSplit2 = startTime2.split(":");//split end time
				int startHour2 = Integer.valueOf(startSplit2[0]);
				
				String[] endSplit2 = endTime2.split(":");
				int endHour2 = Integer.valueOf(endSplit2[0]);
				
				if((endHour2>startHour1)&&(endHour1>startHour2)){
					if (clashedEvents.contains(single)){
						
					}else{
						clashedEvents.add(single);
						System.out.print("this is a clashed event"+single.getId());
					}
				}
				if((endHour1>startHour2)&&(endHour2>startHour1)){
					if (clashedEvents.contains(single)){
						
					}else{
						clashedEvents.add(single);
						System.out.print("this is a clashed event"+single.getId());
					}
				}
				
			}
		}
		return clashedEvents;
	}
	
	public ArrayList<Event> checkTimeClashStudent(Date date, List<StudentGroup> list, String startTime2,String endTime2){
		ArrayList<Event> eventsofDate=findEventByDateAndStudentGroup( date, list);

		ArrayList<Event> clashedEvents = new ArrayList<Event>();
		if(eventsofDate.size()>0){
			for(Event single :eventsofDate){
				String startTime1=single.getStartTime();
				String endTime1=single.getEndTime();
				
				String[] startSplit1 = startTime1.split(":");//split start time for event one into an array
				int startHour1 = Integer.valueOf(startSplit1[0]); //int for first start time
				
				String[] endSplit1 = endTime1.split(":");
				int endHour1 = Integer.valueOf(endSplit1[0]);
				
				String[] startSplit2 = startTime2.split(":");//split end time
				int startHour2 = Integer.valueOf(startSplit2[0]);
				
				String[] endSplit2 = endTime2.split(":");
				int endHour2 = Integer.valueOf(endSplit2[0]);
				
				if((endHour2>startHour1)&&(endHour1>startHour2)){
					if (clashedEvents.contains(single)){
						
					}else{
						clashedEvents.add(single);
						System.out.print("this is a clashed event"+single.getId());
					}
				}
				if((endHour1>startHour2)&&(endHour2>startHour1)){
					if (clashedEvents.contains(single)){
						
					}else{
						clashedEvents.add(single);
						System.out.print("this is a clashed event"+single.getId());
					}
				}
			}
			
		}
		return clashedEvents;
	}
	public ArrayList<Event> checkTimeClashTutor(Date date, List<Tutor> list, String startTime2,String endTime2){
		ArrayList<Event> eventsofDate=findEventByDateAndTutor( date, list);

		ArrayList<Event> clashedEvents = new ArrayList<Event>();
		if(eventsofDate.size()>0){
			for(Event single :eventsofDate){
				String startTime1=single.getStartTime();
				String endTime1=single.getEndTime();
				
				String[] startSplit1 = startTime1.split(":");//split start time for event one into an array
				int startHour1 = Integer.valueOf(startSplit1[0]); //int for first start time
				
				String[] endSplit1 = endTime1.split(":");
				int endHour1 = Integer.valueOf(endSplit1[0]);
				
				String[] startSplit2 = startTime2.split(":");//split end time
				int startHour2 = Integer.valueOf(startSplit2[0]);
				
				String[] endSplit2 = endTime2.split(":");
				int endHour2 = Integer.valueOf(endSplit2[0]);
				
				if((endHour2>startHour1)&&(endHour1>startHour2)){
					if (clashedEvents.contains(single)){
						
					}else{
						clashedEvents.add(single);
						System.out.print("this is a clashed event"+single.getId());
					}
				}
				if((endHour1>startHour2)&&(endHour2>startHour1)){
					if (clashedEvents.contains(single)){
						
					}else{
						clashedEvents.add(single);
						System.out.print("this is a clashed event"+single.getId());
					}
				}
			}
			
		}
		return clashedEvents;
	}

 public boolean validateTime(String startTime1,String endTime1){
	 String[] startSplit1 = startTime1.split(":");//split start time for event one into an array
		int startHour1 = Integer.valueOf(startSplit1[0]); //int for first start time
		
		String[] endSplit1 = endTime1.split(":");
		int endHour1 = Integer.valueOf(endSplit1[0]);
	 if(startHour1>endHour1){
		 return false;
	 }else if(startHour1==endHour1){
		 return false; 
	 }else if((endHour1-startHour1)>4){
		 return false;
	 }
	 return true;
 }
 
 //this method checks to ensure room capacity can accommodate student groups
 public boolean checkCapacity(Room room, List<StudentGroup> list){
	 int number = 0;
	 for(StudentGroup eachGroup:list){
		number += eachGroup.getNumberOfStudent();
	 }
	 if (number>room.getCapacity()){
		 return false;
	 }
	 return true;
 }
 
public ArrayList<Event> findEventByDateAndStudentGroup(Date date, List<StudentGroup> list){
	 ArrayList<Event>events=  (ArrayList<Event>) findEventByDate(date);//get all events on this date
	 ArrayList<Event>finalEvents = new ArrayList<Event>();
	 for(StudentGroup eventStudent: list){ //loop through all given student group
		 for(Event mev:events){//loop through all event for the given date
			 for(StudentGroup student:mev.getStudentGroup()){
				 if (eventStudent.getGroupId()==student.getGroupId()){
					 finalEvents.add(mev);//a list of all events involving the same student group
					 System.out.print("this event "+mev.getId()+" involves studentgroup "+eventStudent.getGroupName());
				 }else{
					 System.out.print("no event involving student group");
				 }
				 
			 }
		 }
	 }
	 Collections.sort(finalEvents, new Comparator<Event>() {
			@Override
			public int compare(Event e1, Event e2) {
				return e1.getDate().compareTo(e2.getDate());
			}
		});
	 return finalEvents;
 }
public ArrayList<Event> findEventByDateAndTutor(Date date, List<Tutor> list){
	 ArrayList<Event>events=  (ArrayList<Event>) findEventByDate(date);//get all events on this date
	 ArrayList<Event>finalEvents = new ArrayList<Event>();
	 for(Tutor eventTutor: list){ //loop through all given Tutor group
		 for(Event mev:events){//loop through all event for the given date
			 for(Tutor tutor:mev.getTutor()){
				 if (eventTutor.getId()==tutor.getId()){
					 finalEvents.add(mev);//a list of all events involving the same tutor
					 System.out.print("this is a clashed event"+mev.getId());
				 }
				 
			 }
		 }
	 }
	 Collections.sort(finalEvents, new Comparator<Event>() {
			@Override
			public int compare(Event e1, Event e2) {
				return e1.getDate().compareTo(e2.getDate());
			}
		});
	 return finalEvents;
}
@Override
public List<Event> findByRoomAndDate(long id, Date date) {
	ArrayList<Event> myEvent= (ArrayList<Event>) eventRepo.findEventByDate(date);
	Room room= roomRepo.findRoom(id);
	ArrayList<Event> finalEvent= new ArrayList<Event>();
	for(Event event:myEvent){
		if (event.getRoom().getId()==room.getId()){
			finalEvent.add(event);
		}
	}
	 Collections.sort(finalEvent, new Comparator<Event>() {
			@Override
			public int compare(Event e1, Event e2) {
				return e1.getDate().compareTo(e2.getDate());
			}
		});
	return finalEvent;
}
public List<Event> findByModuleAndDate(long id, Date date) {
	ArrayList<Event> myEvent= (ArrayList<Event>) eventRepo.findEventByDate(date);
	Module mod= moduleRepo.findModule(id);
	ArrayList<Event> finalEvent= new ArrayList<Event>();
	for(Event event:myEvent){
		if (event.getModule().getId()==mod.getId()){
			finalEvent.add(event);
		}
	}
	 Collections.sort(finalEvent, new Comparator<Event>() {
			@Override
			public int compare(Event e1, Event e2) {
				return e1.getDate().compareTo(e2.getDate());
			}
		});
	return finalEvent;
}
public ArrayList<Event> findByTutorAndDate(long id, Date date){
	 Tutor mod = tutorRepo.findTutor(id);
	 ArrayList<Event> theEvent= (ArrayList<Event>) eventRepo.findEventByDate(date);
	 ArrayList<Event> finalEvent= new ArrayList<Event>();
	 for(Event myEvent:theEvent){
		 List<Tutor> list= myEvent.getTutor();
		 for(Tutor stud:list){
			 if(stud.getId()==mod.getId()){
			 finalEvent.add(myEvent);
			 }
		 }
	 }
	 Collections.sort(finalEvent, new Comparator<Event>() {
			@Override
			public int compare(Event e1, Event e2) {
				return e1.getDate().compareTo(e2.getDate());
			}
		});
	 return finalEvent;
}
public ArrayList<Event> findByStudentAndDate(long id, Date date){
	 StudentGroup mod = studentRepo.findStudentGroup(id);
	 ArrayList<Event> theEvent= (ArrayList<Event>) eventRepo.findEventByDate(date);
	 ArrayList<Event> finalEvent= new ArrayList<Event>();
	 for(Event myEvent:theEvent){
		 List<StudentGroup> list= myEvent.getStudentGroup();
		 for(StudentGroup stud:list){
			 if(stud.getGroupId()==mod.getGroupId()){
			 finalEvent.add(myEvent);
			 }
		 }
	 }
	 Collections.sort(finalEvent, new Comparator<Event>() {
			@Override
			public int compare(Event e1, Event e2) {
				return e1.getDate().compareTo(e2.getDate());
			}
		});
	 return finalEvent;
}
 public ArrayList<Event> findEventsInRoom(long id){
	 Room room = roomRepo.findRoom(id);
	 ArrayList<Event> theEvent= (ArrayList<Event>) eventRepo.getAll();
	 ArrayList<Event> finalEvent= new ArrayList<Event>();
	 for(Event myEvent:theEvent){
		 if(myEvent.getRoom().getId()==room.getId()){
			 finalEvent.add(myEvent);
		 }
	 }
	 Collections.sort(finalEvent, new Comparator<Event>() {
			@Override
			public int compare(Event e1, Event e2) {
				return e1.getDate().compareTo(e2.getDate());
			}
		});
	 return finalEvent;
 }
 public ArrayList<Event> findEventsWithModule(long id){
	 Module mod = moduleRepo.findModule(id);
	 ArrayList<Event> theEvent= (ArrayList<Event>) eventRepo.getAll();
	 ArrayList<Event> finalEvent= new ArrayList<Event>();
	 for(Event myEvent:theEvent){
		 if(myEvent.getModule().getId()==mod.getId()){
			 finalEvent.add(myEvent);
		 }
	 }
	 Collections.sort(finalEvent, new Comparator<Event>() {
			@Override
			public int compare(Event e1, Event e2) {
				return e1.getDate().compareTo(e2.getDate());
			}
		});
	 return finalEvent;
 }
 public ArrayList<Event> findEventsWithStudent(long id){
	 StudentGroup mod = studentRepo.findStudentGroup(id);
	 ArrayList<Event> theEvent= (ArrayList<Event>) eventRepo.getAll();
	 ArrayList<Event> finalEvent= new ArrayList<Event>();
	 for(Event myEvent:theEvent){
		 List<StudentGroup> list= myEvent.getStudentGroup();
		 for(StudentGroup stud:list){
			 if(stud.getGroupId()==mod.getGroupId()){
			 finalEvent.add(myEvent);
			 }
		 }
	 }
	 Collections.sort(finalEvent, new Comparator<Event>() {
			@Override
			public int compare(Event e1, Event e2) {
				return e1.getDate().compareTo(e2.getDate());
			}
		});
	 return finalEvent;
 }
 public ArrayList<Event> findEventsWithTutor(long id){
	 Tutor mod = tutorRepo.findTutor(id);
	 ArrayList<Event> theEvent= (ArrayList<Event>) eventRepo.getAll();
	 ArrayList<Event> finalEvent= new ArrayList<Event>();
	 for(Event myEvent:theEvent){
		 List<Tutor> list= myEvent.getTutor();
		 for(Tutor stud:list){
			 if(stud.getId()==mod.getId()){
			 finalEvent.add(myEvent);
			 }
		 }
	 }
	 Collections.sort(finalEvent, new Comparator<Event>() {
			@Override
			public int compare(Event e1, Event e2) {
				return e1.getDate().compareTo(e2.getDate());
			}
		});
	 return finalEvent;
 }
 
}
