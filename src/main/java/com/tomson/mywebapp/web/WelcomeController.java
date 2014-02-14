package com.tomson.mywebapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tomson.mywebapp.service.WelcomeService;

@Controller
public class WelcomeController {
	@Autowired
	WelcomeService service;
	
    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String index(Model model) {
        model.addAttribute("message", service.getGreeting("tomsonxu"));
        return "welcome";
	}
    
	@RequestMapping("/testdirectoutput")
	public @ResponseBody String directOutput() {
		return "Hello world!";
	}
    
}
