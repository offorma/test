package com.project.test.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@RequestMapping(value="/")
	public ModelAndView test(HttpServletResponse response) throws IOException{
		return new ModelAndView("home");
	}
	@RequestMapping(value="/landing")
	public ModelAndView landing(HttpServletResponse response) throws IOException{
		return new ModelAndView("landing");
	}
	@RequestMapping(value="/basic")
	public ModelAndView basic(HttpServletResponse response) throws IOException{
		return new ModelAndView("basicsettings");
	}
	@RequestMapping(value="/rooms")
	public ModelAndView room(HttpServletResponse response) throws IOException{
		return new ModelAndView("rooms");
	}
	@RequestMapping(value="/createroom")
	public ModelAndView addroom(HttpServletResponse response) throws IOException{
		return new ModelAndView("createroom");
	}
	@RequestMapping(value="/listrooms")
	public ModelAndView listroom(HttpServletResponse response) throws IOException{
		return new ModelAndView("listrooms");
	}
	@RequestMapping(value="/tutor")
	public ModelAndView tutor(HttpServletResponse response) throws IOException{
		return new ModelAndView("rooms");
	}
	@RequestMapping(value="/createtutor")
	public ModelAndView addtutor(HttpServletResponse response) throws IOException{
		return new ModelAndView("createtutor");
	}
	@RequestMapping(value="/listtutor")
	public ModelAndView listtutor(HttpServletResponse response) throws IOException{
		return new ModelAndView("listtutor");
	}
	@RequestMapping(value="/module")
	public ModelAndView module(HttpServletResponse response) throws IOException{
		return new ModelAndView("module");
	}
	@RequestMapping(value="/createmodule")
	public ModelAndView createmodule(HttpServletResponse response) throws IOException{
		return new ModelAndView("createmodule");
	}
	@RequestMapping(value="/listmodule")
	public ModelAndView listmodule(HttpServletResponse response) throws IOException{
		return new ModelAndView("listmodule");
	}
	@RequestMapping(value="/student")
	public ModelAndView student(HttpServletResponse response) throws IOException{
		return new ModelAndView("student");
	}
	@RequestMapping(value="/createstudent")
	public ModelAndView createstudent(HttpServletResponse response) throws IOException{
		return new ModelAndView("createstudent");
	}
	@RequestMapping(value="/liststudent")
	public ModelAndView liststudent(HttpServletResponse response) throws IOException{
		return new ModelAndView("liststudent");
	}
	
}
