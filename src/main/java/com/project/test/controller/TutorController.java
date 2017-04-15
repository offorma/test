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

import com.project.test.model.Tutor;
import com.project.test.model.User;
import com.project.test.service.TutorServiceImpl;
@Controller
public class TutorController {
	@Autowired
	TutorServiceImpl tutorService;
	
	@RequestMapping(value = "/createtutor", method = RequestMethod.GET)
	  public String showRegister(HttpServletRequest request,HttpSession session, HttpServletResponse response,@ModelAttribute("message") String message,
	            Model model)throws IOException {
		 User user=(User)session.getAttribute("user");
			if(user==null){
				String accessmessage="Please login to access page";
				model.addAttribute("accessmessage",accessmessage);
				return "home";
			}
		model.addAttribute("message",message);
	    return "createtutor";
	  }
	@RequestMapping(value="/createtutor", method = RequestMethod.POST)
	public String createstudent(Map<String, Object> map,@RequestParam("Staffid") String sid, @RequestParam("Staffname") String sn, 
			@RequestParam("Staffdept") String sd,final RedirectAttributes redirectAttributes,HttpSession session,Model model) throws IOException{
		
		 User user=(User)session.getAttribute("user");
			if(user==null){
				String accessmessage="Please login to access page";
				model.addAttribute("accessmessage",accessmessage);
				return "home";
			}
		
		
		
		String staffId="";
		String staffName ="";
		String staffD = "";
		String st = "";
		if (sid.length()!=0){
			staffId=sid;	
		}else{
			String message = "Staff Id cannot be empty";
			redirectAttributes.addFlashAttribute("message", message);
			st="redirect:/createtutor";
		}
		if(sn.length()!=0){
			staffName=sn;
		}else{
			String message = "Staff name cannot be empty";
			redirectAttributes.addFlashAttribute("message", message);
			 st="redirect:/createtutor";
		}
		if (sd.length()!=0){
			staffD=sd;
		}else{
			String message = "Department cannot be empty";
			redirectAttributes.addFlashAttribute("message", message);
			st="redirect:/createtutor";
		}
		if ((staffD.length()!=0)&&(staffName.length()!=0)&&(staffId.length()!=0)){
					Tutor t = new Tutor();
					t.setStaffID(staffId);
					t.setName(staffName);
					t.setDepartment(staffD);
					tutorService.create(t);	
					String messages = "Tutor "+t.getName()+" was successfully created";
					redirectAttributes.addFlashAttribute("messages", messages);
					st="redirect:/createtutor";
					
				}
				return st;
	}
	@RequestMapping(value="/listtutors")
	public ModelAndView getRooms(HttpServletResponse response,HttpSession session, @ModelAttribute("message") String message
            ) throws IOException{
		User user=(User)session.getAttribute("user");
		if(user==null){
			ModelAndView models = new ModelAndView("home");
			String accessmessage="Please login to access page";
			models.addObject("accessmessage",accessmessage);
			return models;
		}
		ArrayList<Tutor> tutor = (ArrayList<Tutor>) tutorService.getAll();
		
		ModelAndView model = new ModelAndView("listtutor");
		model.addObject("tutor", tutor);
		
		return model;
	}
	@RequestMapping(value="/deletetutor/{id}", method=RequestMethod.GET)
	    public String delete(@PathVariable Long id,final RedirectAttributes redirectAttributes,HttpSession session,Model model) {
		 User user=(User)session.getAttribute("user");
			if(user==null){
				String accessmessage="Please login to access page";
				model.addAttribute("accessmessage",accessmessage);
				return "home";
			}
	       
		tutorService.delete(id);
	
	        String messages = "Tutor has been deleted";
			redirectAttributes.addFlashAttribute("messages", messages);
			String st="redirect:/listtutors";
			return st;
	       
	    }
	 @RequestMapping(value="/edittutor/{id}", method=RequestMethod.GET)
	    public ModelAndView editTutor(@PathVariable Long id,HttpSession session) {
		 User user=(User)session.getAttribute("user");
			if(user==null){
				ModelAndView models = new ModelAndView("home");
				String accessmessage="Please login to access page";
				models.addObject("accessmessage",accessmessage);
				return models;
			}
	        ModelAndView modelAndView = new ModelAndView("edittutor");
	        Tutor tutor= tutorService.findTutor(id);
	        modelAndView.addObject("tutor",tutor);
	        return modelAndView;
	    }
	@RequestMapping(value="/edittutor/{id}", method = RequestMethod.POST)
	public String updateRoom(Map<String, Object> map,@RequestParam("Staffid") String sid, @RequestParam("Staffname") String sn, 
			@RequestParam("Staffdept") String sd,@PathVariable Long id,final RedirectAttributes redirectAttributes,HttpSession session,Model model) throws IOException{
		
		
		 User user=(User)session.getAttribute("user");
			if(user==null){
				String accessmessage="Please login to access page";
				model.addAttribute("accessmessage",accessmessage);
				return "home";
			}
		
		
		if (sid.length()!=0){
			if(sn.length()!=0){
				if (sd.length()!=0){
					Tutor t = new Tutor();
					t.setStaffID(sid);
					t.setName(sn);
					t.setDepartment(sd);
					tutorService.update(id,t);	
					String messages = "Tutor "+t.getName()+" was successfully updated";
					redirectAttributes.addFlashAttribute("messages", messages);
					String st="redirect:/listtutors";
					return st;
				}else{
					String message = "Department cannot be empty";
					redirectAttributes.addFlashAttribute("message", message);
					String st="redirect:/edittutor/{id}";
					return st;
				}
			}else{
				String message = "Staff name cannot be empty";
				redirectAttributes.addFlashAttribute("message", message);
				String st="redirect:/edittutor/{id}";
				return st;
			}
		}else{
			String message = "Staff Id cannot be empty";
			redirectAttributes.addFlashAttribute("message", message);
			String st="redirect:/edittutor/{id}";
			return st;
		}
			
	}
}
