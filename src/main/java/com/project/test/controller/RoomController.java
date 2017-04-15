package com.project.test.controller;

import java.io.IOException;
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

import com.project.test.model.Room;
import com.project.test.model.User;
import com.project.test.service.RoomServiceImpl;


@Controller
public class RoomController {
	@Autowired
	RoomServiceImpl roomService;
	
	@RequestMapping(value = "/createroom", method = RequestMethod.GET)
	  public String showRegister(HttpServletRequest request,HttpSession session, HttpServletResponse response,@ModelAttribute("message") String message,
	            Model model)throws IOException {
		
		
		 User user=(User)session.getAttribute("user");
			if(user==null){
				String accessmessage="Please login to access page";
				model.addAttribute("accessmessage",accessmessage);
				return "home";
			}
		model.addAttribute("message",message);
	    return "createroom";
	  }
	@RequestMapping(value="/createroom", method = RequestMethod.POST)
	public String createstudent(Map<String,Object> map,HttpSession session,Model model,@RequestParam("RoomNumber") String rnumber, @RequestParam("Capacity") String cap, 
			@RequestParam("Building") String build,@RequestParam("type") String type,final RedirectAttributes redirectAttributes) throws IOException{
		
		User user=(User)session.getAttribute("user");
		if(user==null){
			String accessmessage="Please login to access page";
			model.addAttribute("accessmessage",accessmessage);
			return "home";
		}
		
		String rn = "";
		int cp;
		String building;
		String ty;
		if (rnumber.length()!=0){
			 rn=rnumber;
		}else{
			String message = "Room number cannot be empty";
			redirectAttributes.addFlashAttribute("message", message);
			String st="redirect:/createroom";
			return st;
		}
		if(cap.length()!=0){
			
			
			try{
				cp = Integer.parseInt(cap);
				
			}catch (NumberFormatException ex)
		    {
				String message = "Number of students must be a number";
				redirectAttributes.addFlashAttribute("message", message);
				String st="redirect:/createstudent";
				return st;
		    }
		}else{
			String message = "Capacity cannot be empty";
			redirectAttributes.addFlashAttribute("message", message);
			String st="redirect:/createroom";
			return st;
		}
		if(build.length()!=0){
			building=build;
		}else{
			String message = "Building cannot be empty";
			redirectAttributes.addFlashAttribute("message", message);
			String st="redirect:/createroom";
			return st;
		}
		if(type.length()!=0){
			ty=type;
		}else{
			String message = "Building cannot be empty";
			redirectAttributes.addFlashAttribute("message", message);
			String st="redirect:/createroom";
			return st;
		}
		if((ty.length()!=0)&&(building.length()!=0)&&(rn.length()!=0)&&(cp>0)){
			Room rm = new Room();
			rm.setRoomNumber(rn);
			rm.setType(ty);
			rm.setBuilding(building);
			rm.setCapacity(cp);
			roomService.create(rm);
		}
		String messages = "Room was successfully created";
		redirectAttributes.addFlashAttribute("messages", messages);
		String st="redirect:/createroom";
		return st;
			
	}
	@RequestMapping(value="/listrooms")
	public ModelAndView getRooms(HttpServletResponse response,HttpSession session, @ModelAttribute("message") String message
            ) throws IOException{
		
		User user=(User)session.getAttribute("user");
		if(user==null){
			ModelAndView models = new ModelAndView("home");
			String accessmessage="Please login to access page";
			models.addObject("accessmessage",accessmessage);
			return models;
		}
		ArrayList<Room> room = (ArrayList<Room>) roomService.getAll();
		
		ModelAndView model = new ModelAndView("listrooms");
		model.addObject("room", room);
		
		return model;
	}
	  @RequestMapping(value="/deleteroom/{id}", method=RequestMethod.GET)
	    public String delete(@PathVariable Long id,final RedirectAttributes redirectAttributes,HttpSession session,Model model) {
	       
		  User user=(User)session.getAttribute("user");
			if(user==null){
				String accessmessage="Please login to access page";
				model.addAttribute("accessmessage",accessmessage);
				return "home";
			}
		  
		  roomService.delete(id);
	
	        String messages = "Room has been deleted";
			redirectAttributes.addFlashAttribute("messages", messages);
			String st="redirect:/listrooms";
			return st;
	       
	    }
	  @RequestMapping(value="/editroom/{id}", method=RequestMethod.GET)
	    public ModelAndView editTeamPage(@PathVariable Long id,HttpSession session) {

			User user=(User)session.getAttribute("user");
			if(user==null){
				ModelAndView models = new ModelAndView("home");
				String accessmessage="Please login to access page";
				models.addObject("accessmessage",accessmessage);
				return models;
			}
			
	        ModelAndView modelAndView = new ModelAndView("editroom");
	        Room room = roomService.findRoom(id);
	        modelAndView.addObject("room",room);
	        return modelAndView;
	    }
	  @RequestMapping(value="/editroom/{id}", method = RequestMethod.POST)
		public String updateRoom(Map<String, Object> map,HttpSession session,Model model,@RequestParam("RoomNumber") String rnumber, @RequestParam("Capacity") String cap, 
				@RequestParam("Building") String build,@RequestParam("type") String type,@PathVariable Long id,final RedirectAttributes redirectAttributes) throws IOException{
			
		  User user=(User)session.getAttribute("user");
			if(user==null){
				String accessmessage="Please login to access page";
				model.addAttribute("accessmessage",accessmessage);
				return "home";
			}
		  
		  
		  String rn = "";
			int cp;
			String building;
			String ty;
			if (rnumber.length()!=0){
				 rn=rnumber;
			}else{
				String message = "Room number cannot be empty";
				redirectAttributes.addFlashAttribute("message", message);
				String st="redirect:/editroom/{id}";
				return st;
			}
			if(cap.length()!=0){
				
				
				try{
					cp = Integer.parseInt(cap);
					
				}catch (NumberFormatException ex)
			    {
					String message = "Capacity must be a number";
					redirectAttributes.addFlashAttribute("message", message);
					String st="redirect:/editroom/{id}";
					return st;
			    }
			}else{
				String message = "Capacity cannot be empty";
				redirectAttributes.addFlashAttribute("message", message);
				String st="redirect:/editroom/{id}";
				return st;
			}
			if(build.length()!=0){
				building=build;
			}else{
				String message = "Building cannot be empty";
				redirectAttributes.addFlashAttribute("message", message);
				String st="redirect:/editroom/{id}";
				return st;
			}
			if(type.length()!=0){
				ty=type;
			}else{
				String message = "Building cannot be empty";
				redirectAttributes.addFlashAttribute("message", message);
				String st="redirect:/editroom/{id}";
				return st;
			}
			if((ty.length()!=0)&&(building.length()!=0)&&(rn.length()!=0)&&(cp>0)){
				Room rm = new Room();
				rm.setRoomNumber(rn);
				rm.setType(ty);
				rm.setBuilding(building);
				rm.setCapacity(cp);
				roomService.update(id,rm);
			}
			String messages = "Room was successfully updated";
			redirectAttributes.addFlashAttribute("messages", messages);
			String st="redirect:/listrooms";
			return st;
				
		}
}


