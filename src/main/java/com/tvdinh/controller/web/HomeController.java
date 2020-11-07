package com.tvdinh.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tvdinh.service.IRoleService;

@Controller(value = "homeControllerOfWeb")
public class HomeController {
	
	@Autowired
	private IRoleService roleService;
	
	@RequestMapping(value = "/trang-chu", method = RequestMethod.GET)
	public ModelAndView homePage() {
		ModelAndView mav=new ModelAndView("web/home");
		roleService.findAll();
		return mav;
	}
	
}
