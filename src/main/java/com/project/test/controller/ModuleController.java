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

import com.project.test.model.Module;
import com.project.test.model.User;
import com.project.test.repositories.ModuleRepositoryImpl;
@Controller
public class ModuleController {
	@Autowired
	ModuleRepositoryImpl moduleRepo;
	@RequestMapping(value="/listmodule")
	public ModelAndView getRooms(HttpServletResponse response,HttpSession session, @ModelAttribute("message") String message
            ) throws IOException{
		
		User user=(User)session.getAttribute("user");
		if(user==null){
			ModelAndView models = new ModelAndView("home");
			String accessmessage="Please login to access page";
			models.addObject("accessmessage",accessmessage);
			return models;
		}
		ArrayList<Module> module = (ArrayList<Module>) moduleRepo.getAll();
		
		ModelAndView model = new ModelAndView("listmodule");
		model.addObject("module", module);
		
		return model;
	}
	  @RequestMapping(value="/deletemodule/{id}", method=RequestMethod.GET)
	    public String delete(@PathVariable Long id,final RedirectAttributes redirectAttributes,HttpSession session,Model model) {
	       
		  User user=(User)session.getAttribute("user");
			if(user==null){
				String accessmessage="Please login to access page";
				model.addAttribute("accessmessage",accessmessage);
				return "home";
			}
		  
		  
	        moduleRepo.delete(id);
	
	        String messages = "Module has been successfully deleted";
			redirectAttributes.addFlashAttribute("messages", messages);
			String st="redirect:/listmodule";
			return st;
	       
	    }
	
	@RequestMapping(value = "/createmodule", method = RequestMethod.GET)
	  public String showModule(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("message") String message,
	            Model model,HttpSession session)throws IOException {
		
		 User user=(User)session.getAttribute("user");
			if(user==null){
				String accessmessage="Please login to access page";
				model.addAttribute("accessmessage",accessmessage);
				return "home";
			}
		
		model.addAttribute("message",message);
	    return "createmodule";
	  }
	@RequestMapping(value="/createmodule", method = RequestMethod.POST)
	public String createstudent(Map<String, Object> map,@RequestParam("Modulecode") String mcode, @RequestParam("Modulename") String mname, 
			@RequestParam("Weeklyhour") String whour,final RedirectAttributes redirectAttributes,HttpSession session,Model model) throws IOException{
		
		
		User user=(User)session.getAttribute("user");
		if(user==null){
			String accessmessage="Please login to access page";
			model.addAttribute("accessmessage",accessmessage);
			return "home";
		}
		
		String code;
		String name;
		int hour;
		if(mcode.length()!=0){
			code =mcode;
		}else{
			String message = "Module code cannot be empty";
			redirectAttributes.addFlashAttribute("message", message);
			String st="redirect:/createmodule";
			return st;
		}
		if(mname.length()!=0){
			name=mname;
		}else{
			String message = "Module name cannot be empty";
			redirectAttributes.addFlashAttribute("message", message);
			String st="redirect:/createmodule";
			return st;
		}
		if(whour.length()!=0){
			try{
				hour = Integer.parseInt(whour);
				
			}catch (NumberFormatException ex)
		    {
				String message = "Total weekly hour must be a number";
				redirectAttributes.addFlashAttribute("message", message);
				String st="redirect:/createmodule";
				return st;
		    }
		}else{
			String message = "Total weekly hour cannot be empty";
			redirectAttributes.addFlashAttribute("message", message);
			String st="redirect:/createmodule";
			return st;
		}
		if((code.length()!=0)&&(name.length()!=0)&&(hour>0)){
			Module module = new Module();
			module.setModuleCode(code);
			module.setModuleName(name);
			module.setTotalWeeklyHour(hour);
			moduleRepo.create(module);
		}
		String messages = "Module was successfully created";
		redirectAttributes.addFlashAttribute("messages", messages);
		String st="redirect:/createmodule";
		return st;
	}
	 @RequestMapping(value="/editmodule/{id}", method=RequestMethod.GET)
	    public ModelAndView editTeamPage(@PathVariable Long id,HttpSession session) {
		 
		 User user=(User)session.getAttribute("user");
			if(user==null){
				ModelAndView models = new ModelAndView("home");
				String accessmessage="Please login to access page";
				models.addObject("accessmessage",accessmessage);
				return models;
			}
	        ModelAndView modelAndView = new ModelAndView("editmodule");
	        Module module = moduleRepo.findModule(id);
	        modelAndView.addObject("module",module);
	        return modelAndView;
	    }
	  @RequestMapping(value="/editmodule/{id}", method = RequestMethod.POST)
	  public String updateModule(Map<String, Object> map,@RequestParam("Modulecode") String mcode, @RequestParam("Modulename") String mname, 
				@RequestParam("Weeklyhour") String whour,final RedirectAttributes redirectAttributes,@PathVariable Long id,HttpSession session, Model model) throws IOException{
		  
		  User user=(User)session.getAttribute("user");
			if(user==null){
				String accessmessage="Please login to access page";
				model.addAttribute("accessmessage",accessmessage);
				return "home";
			}
		  
		  String code;
			String name;
			int hour;
			if(mcode.length()!=0){
				code =mcode;
			}else{
				String message = "Module code cannot be empty";
				redirectAttributes.addFlashAttribute("message", message);
				String st="redirect:/editmodule/{id}";
				return st;
			}
			if(mname.length()!=0){
				name=mname;
			}else{
				String message = "Module name cannot be empty";
				redirectAttributes.addFlashAttribute("message", message);
				String st="redirect:/editmodule/{id}";
				return st;
			}
			if(whour.length()!=0){
				try{
					hour = Integer.parseInt(whour);
					
				}catch (NumberFormatException ex)
			    {
					String message = "Total weekly hour must be a number";
					redirectAttributes.addFlashAttribute("message", message);
					String st="redirect:/editmodule/{id}";
					return st;
			    }
			}else{
				String message = "Total weekly hour cannot be empty";
				redirectAttributes.addFlashAttribute("message", message);
				String st="redirect:/editmodule/{id}";
				return st;
			}
			if((code.length()!=0)&&(name.length()!=0)&&(hour>0)){
				Module module = new Module();
				module.setModuleCode(code);
				module.setModuleName(name);
				module.setTotalWeeklyHour(hour);
				moduleRepo.update(id, module);
			}
			String messages = "Module was successfully updated";
			redirectAttributes.addFlashAttribute("messages", messages);
			String st="redirect:/listmodule";
			return st;
		}
}

