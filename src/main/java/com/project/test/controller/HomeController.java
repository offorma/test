package com.project.test.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;

import com.project.test.model.User;


@Controller
public class HomeController {
	
	@RequestMapping(value="/student")
	public ModelAndView student(HttpServletResponse response) throws IOException{
		return new ModelAndView("student");
	}
	@RequestMapping(value="/landing")
	public ModelAndView landing(HttpServletResponse response,HttpSession session) throws IOException{
		User user=(User)session.getAttribute("user");
		if(user==null){
			ModelAndView models = new ModelAndView("home");
			String accessmessage="Please login to access page";
			models.addObject("accessmessage",accessmessage);
			return models;
		}
		ModelAndView model= new ModelAndView("landing");
		
		return model;
	}
	@RequestMapping(value="/basic")
	public ModelAndView basic(HttpServletResponse response,HttpSession session) throws IOException{
		User user=(User)session.getAttribute("user");
		if(user==null){
			ModelAndView models = new ModelAndView("home");
			String accessmessage="Please login to access page";
			models.addObject("accessmessage",accessmessage);
			return models;
		}
		ModelAndView model= new ModelAndView("basicsettings");
		
		return model;
		
	}
	@RequestMapping(value="/room")
	public ModelAndView room(HttpServletResponse response,HttpSession session) throws IOException{
		User user=(User)session.getAttribute("user");
		if(user==null){
			ModelAndView models = new ModelAndView("home");
			String accessmessage="Please login to access page";
			models.addObject("accessmessage",accessmessage);
			return models;
		}
		ModelAndView model= new ModelAndView("room");
		
		return model;
		
	}
	@RequestMapping(value="/createroom")
	public ModelAndView addroom(HttpServletResponse response,HttpSession session) throws IOException{
		
		User user=(User)session.getAttribute("user");
		if(user==null){
			ModelAndView models = new ModelAndView("home");
			String accessmessage="Please login to access page";
			models.addObject("accessmessage",accessmessage);
			return models;
		}
		ModelAndView model= new ModelAndView("createroom");
		
		return model;
	
	}
	
	@RequestMapping(value="/tutor")
	public ModelAndView tutor(HttpServletResponse response,HttpSession session) throws IOException{

		User user=(User)session.getAttribute("user");
		if(user==null){
			ModelAndView models = new ModelAndView("home");
			String accessmessage="Please login to access page";
			models.addObject("accessmessage",accessmessage);
			return models;
		}
		ModelAndView model= new ModelAndView("tutor");
		
		return model;
		
	}
	
	
	@RequestMapping(value="/module")
	public ModelAndView module(HttpServletResponse response,HttpSession session) throws IOException{
		User user=(User)session.getAttribute("user");
		if(user==null){
			ModelAndView models = new ModelAndView("home");
			String accessmessage="Please login to access page";
			models.addObject("accessmessage",accessmessage);
			return models;
		}
		ModelAndView model= new ModelAndView("module");
		
		return model;
		
	}
	@RequestMapping(value="/event")
	public ModelAndView event(HttpServletResponse response,HttpSession session) throws IOException{
		
		User user=(User)session.getAttribute("user");
		if(user==null){
			ModelAndView models = new ModelAndView("home");
			String accessmessage="Please login to access page";
			models.addObject("accessmessage",accessmessage);
			return models;
		}
		ModelAndView model= new ModelAndView("event");
		
		return model;
	}
	@RequestMapping(value="/createmodule")
	public ModelAndView createmodule(HttpServletResponse response,HttpSession session) throws IOException{
		
		User user=(User)session.getAttribute("user");
		if(user==null){
			ModelAndView models = new ModelAndView("home");
			String accessmessage="Please login to access page";
			models.addObject("accessmessage",accessmessage);
			return models;
		}
		ModelAndView model= new ModelAndView("createmodule");
		
		return model;
	}
	

	
}
