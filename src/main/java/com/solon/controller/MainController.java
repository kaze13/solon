package com.solon.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;



import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.solon.dto.Product;
import com.solon.service.spec.IProductService;



@Controller
@RequestMapping("/")
public class MainController {
	@Autowired
	IProductService productService;
	public boolean checkAndAddAuth(ModelMap model){
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!auth.getPrincipal().equals("anonymousUser")) {
			model.addAttribute("authenticated", true);
			
			return true;
		}
		else{
			return false;
		}
	}
	
	@RequestMapping(value = { "/", "index" })
	public String index(ModelMap model) {
		checkAndAddAuth(model);
		Map<String, String> param = new HashMap<String, String>();
		param.put("mark_recommend", "1");
		List<Product> recommends = productService.getProductsByPaging(param, 1);
		model.addAttribute("recommends", recommends);
		return "index";
	}

	@RequestMapping(value = "about-us")
	public String aboutUs(ModelMap model) {
		checkAndAddAuth(model);
		return "about-us";
	}

	@RequestMapping(value = "contact-us")
	public String contactUs(ModelMap model) {
		checkAndAddAuth(model);
		return "contact-us";
	}

}