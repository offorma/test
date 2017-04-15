package com.project.test.controller;

import java.io.IOException;
import java.util.ArrayList;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.test.model.User;
import com.project.test.repositories.UserRepositoryImpl;

@Controller

public class LoginController {
	@Autowired
	UserRepositoryImpl userRepo;
	
	@RequestMapping(value="/")
	public String test(HttpServletResponse response, @ModelAttribute("message") String message,
            Model model) throws IOException{
		model.addAttribute("message",message);
		return "home";
	}
	@RequestMapping(value="/login")
	public String getlogin(HttpServletResponse response, @ModelAttribute("message") String message,
            Model model) throws IOException{
		model.addAttribute("message",message);
		return "home";
	}
	@RequestMapping(value="/logout")
	public String getlogout(HttpServletResponse response, HttpSession session,
            Model model) throws IOException{
		session.invalidate(); 
		String logoutmessage="You have successfully logged out ";
		model.addAttribute("logoutmessage",logoutmessage);
		return "home";
	}
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String testLogin(Map<String, Object> map,HttpSession session,@RequestParam("Username") String un, @RequestParam("Password") String pwd
			,final RedirectAttributes redirectAttributes) throws IOException{
	 
	if(un.length()!=0 ||pwd.length()!=0){
		User us =userRepo.findByUsername(un);
		if (us !=null){
			if(BCrypt.checkpw(pwd,us.getPassword())){
				session.setAttribute("user", us);
				String st="redirect:/landing";
				return st;
			}else{
				String message = "Invalid username or password";
				redirectAttributes.addFlashAttribute("message", message);
				String st="redirect:/login";
				return st;
				 }
		}else{
			String message = "Invalid username or password";
			String st="redirect:/login";
		 redirectAttributes.addFlashAttribute("message", message);
		 return st; 
		}
	}else{
		String message = "Username or Password cannot be empty";
		String st="redirect:/login";
		 redirectAttributes.addFlashAttribute("message", message);
		 return st;
		}
	}
	@RequestMapping(value="/listusers")
	public ModelAndView getUsers(HttpServletResponse response, @ModelAttribute("message") String message
            ) throws IOException{
		ArrayList<User> user = (ArrayList<User>) userRepo.getAll();
		
		ModelAndView model = new ModelAndView("listusers");
		model.addObject("user", user);
		
		return model;
	}
}
