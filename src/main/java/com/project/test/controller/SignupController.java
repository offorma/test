package com.project.test.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.test.model.User;
import com.project.test.repositories.UserRepositoryImpl;
@Controller
public class SignupController {
	
	@Autowired
	UserRepositoryImpl userRepo;
	@RequestMapping(value = "/signup", method = RequestMethod.GET)

	  public String showRegister(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("message") String message,
	            Model model)throws IOException {
		model.addAttribute("message",message);
	    return "signup";

	  }

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	  public String register(Map<String, Object> map,@RequestParam("username") String un,
			  @RequestParam("password1") String pswd1,@RequestParam("password2") String pswd2,final RedirectAttributes redirectAttributes)throws IOException {
		
		User u = new User();
	
		if((un.length()!= 0)&&(pswd1.length()!=0)&&(pswd2.length()!=0)){//checks if username is empty
			 User us =userRepo.findByUsername(un);//checks db to find if username already exists.

			if(us!=null){
				String message = "Username has already been taken";
				 redirectAttributes.addFlashAttribute("message", message);
				 String st="redirect:/signup";
					return st;	
			}else{
				u.setUserName(un);
				if ((pswd1.equals(pswd2))){
					u.setPassword(BCrypt.hashpw(pswd1, BCrypt.gensalt()));// hash the password
					userRepo.create(u);
					String message = "Hello "+u.getUsername()+"";
					redirectAttributes.addFlashAttribute("message", message);
					String st="redirect:/login";
					return st;	
				}else{
					String message = "Password fields must match";
					 redirectAttributes.addFlashAttribute("message", message); 
					 String st="redirect:/signup";
						return st;
				}
				 }
			
		}else{
			String message = "Username or passwords cannot be empty";
			 redirectAttributes.addFlashAttribute("message", message); 
			 String st="redirect:/signup";
				return st;
		}
	  }
}
