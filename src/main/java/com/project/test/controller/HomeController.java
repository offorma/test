package com.project.test.controller;

import java.io.IOException;


import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;



@Controller
public class HomeController {
	
	@RequestMapping(value="/student")
	public ModelAndView student(HttpServletResponse response) throws IOException{
		return new ModelAndView("student");
	}
	@RequestMapping(value="/landing")
	public ModelAndView landing(HttpServletResponse response) throws IOException{
		return new ModelAndView("landing");
	}
	@RequestMapping(value="/basic")
	public ModelAndView basic(HttpServletResponse response) throws IOException{
		return new ModelAndView("basicsettings");
	}
	@RequestMapping(value="/room")
	public ModelAndView room(HttpServletResponse response) throws IOException{
		return new ModelAndView("room");
	}
	@RequestMapping(value="/createroom")
	public ModelAndView addroom(HttpServletResponse response) throws IOException{
		return new ModelAndView("createroom");
	}
	
	@RequestMapping(value="/tutor")
	public ModelAndView tutor(HttpServletResponse response) throws IOException{
		return new ModelAndView("tutor");
	}
	
	
	@RequestMapping(value="/module")
	public ModelAndView module(HttpServletResponse response) throws IOException{
		return new ModelAndView("module");
	}
	@RequestMapping(value="/createmodule")
	public ModelAndView createmodule(HttpServletResponse response) throws IOException{
		return new ModelAndView("createmodule");
	}
	

	
}
