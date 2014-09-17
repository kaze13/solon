package com.solon.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/")
public class MainController {


	@RequestMapping(value = { "/", "index" })
	public String index(Model model) {
		return "index";
	}

	@RequestMapping(value = "about-us")
	public String aboutUs() {
		return "about-us";
	}

	@RequestMapping(value = "contact-us")
	public String contactUs() {
		return "contact-us";
	}

}