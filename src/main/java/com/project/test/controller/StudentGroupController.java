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

import com.project.test.model.StudentGroup;
import com.project.test.model.User;
import com.project.test.service.StudentGroupServiceImpl;



@Controller
public class StudentGroupController {
	@Autowired
	StudentGroupServiceImpl studentService;
	
	@RequestMapping(value = "/createstudent", method = RequestMethod.GET)
	  public String showRegister(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("message") String message,
			  HttpSession session,Model model)throws IOException {
		
		  User user=(User)session.getAttribute("user");
				if(user==null){
					String accessmessage="Please login to access page";
					model.addAttribute("accessmessage",accessmessage);
					return "home";
				}
		model.addAttribute("message",message);
	    return "createstudent";

	  }
	@RequestMapping(value="/createstudent", method = RequestMethod.POST)
	public String createstudent(Map<String, Object> map,HttpSession session, Model model,@RequestParam("Studentgroup") String sg, @RequestParam("Totalnumber") String total
			,final RedirectAttributes redirectAttributes) throws IOException{
		
		  User user=(User)session.getAttribute("user");
				if(user==null){
					String accessmessage="Please login to access page";
					model.addAttribute("accessmessage",accessmessage);
					return "home";
				}
		
		
		if((sg.length()!=0) || (total.length()!=0)){
			int t;
			try{
				t = Integer.parseInt(total);
				StudentGroup student= new StudentGroup();
				student.setGroupName(sg);
				student.setNumberOfStudent(t);
				studentService.create(student);
				String messages = "Student Group was successful created";
				redirectAttributes.addFlashAttribute("messages", messages);
				String st="redirect:/createstudent";
				return st;
				
			}catch (NumberFormatException ex)
		    {
				String message = "Number of students must be a number";
				redirectAttributes.addFlashAttribute("message", message);
				String st="redirect:/createstudent";
				return st;
		    }
		}else{
			String message = "Student group name or total number cannot be empty";
			redirectAttributes.addFlashAttribute("message", message);
			String st="redirect:/createstudent";
			return st;
		}
		
		
	}
	@RequestMapping(value="/liststudent", method = RequestMethod.GET)
	public ModelAndView getRooms(HttpServletResponse response,HttpSession session, @ModelAttribute("message") String message
            ) throws IOException{
		User user=(User)session.getAttribute("user");
		if(user==null){
			ModelAndView models = new ModelAndView("home");
			String accessmessage="Please login to access page";
			models.addObject("accessmessage",accessmessage);
			return models;
		}
		ArrayList<StudentGroup> student = (ArrayList<StudentGroup>) studentService.getAll();
		
		ModelAndView model = new ModelAndView("liststudent");
		model.addObject("student", student);
		
		return model;
	}
	@RequestMapping(value="/deletestudent/{id}", method = RequestMethod.GET)
	    public String delete(@PathVariable Long id,HttpSession session, Model model,final RedirectAttributes redirectAttributes)throws IOException{
	       
		  User user=(User)session.getAttribute("user");
				if(user==null){
					String accessmessage="Please login to access page";
					model.addAttribute("accessmessage",accessmessage);
					return "home";
				}
		
		studentService.delete(id);
	
	        String messages = "Student group has been deleted";
			redirectAttributes.addFlashAttribute("messages", messages);
			String st="redirect:/liststudent";
			return st;
	       
	    }@RequestMapping(value="/editstudent/{id}", method=RequestMethod.GET)
	    public ModelAndView editStudent(@PathVariable Long id,HttpSession session) {
	    	User user=(User)session.getAttribute("user");
			if(user==null){
				ModelAndView models = new ModelAndView("home");
				String accessmessage="Please login to access page";
				models.addObject("accessmessage",accessmessage);
				return models;
			}
	        ModelAndView modelAndView = new ModelAndView("editstudent");
	        StudentGroup student = studentService.findStudentGroup(id);
	        modelAndView.addObject("student",student);
	        return modelAndView;
	    }
	  @RequestMapping(value="/editstudent/{id}", method = RequestMethod.POST)
	  public String editStudent(Map<String, Object> map,HttpSession session, Model model,@RequestParam("Studentgroup") String sg, @RequestParam("Totalnumber") String total
				,final RedirectAttributes redirectAttributes,@PathVariable Long id) throws IOException{
		  
		  User user=(User)session.getAttribute("user");
			if(user==null){
				String accessmessage="Please login to access page";
				model.addAttribute("accessmessage",accessmessage);
				return "home";
			}
		  
		  
		  String stgrp = "";
		  int totalNumber;
		  if(!sg.equals("null")){
			  stgrp=sg;
		  }else{
				String message = "Student group name cannot be empty";
				redirectAttributes.addFlashAttribute("message", message);
				String st="redirect:/editstudent/{id}";
				return st;
			}
		  if(!total.equals("null")){
				
				
				try{
					totalNumber = Integer.parseInt(total);
					
				}catch (NumberFormatException ex)
			    {
					String message = "Total number must be a number";
					redirectAttributes.addFlashAttribute("message", message);
					String st="redirect:/editstudent/{id}";
					return st;
			    }
			}else{
				String message = "Total number cannot be empty";
				redirectAttributes.addFlashAttribute("message", message);
				String st="redirect:/editstudent/{id}";
				return st;
			}
		  if((stgrp.length()!=0)&&(totalNumber>0)){
				StudentGroup student = new StudentGroup();
				student.setGroupName(stgrp);
				student.setNumberOfStudent(totalNumber);
				
				studentService.update(id,student);
			}
			String messages = "Student group was successfully updated";
			redirectAttributes.addFlashAttribute("messages", messages);
			String st="redirect:/liststudent";
			return st;
	  }
				
}
