package com.solon.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.solon.dto.NetValue;
import com.solon.service.spec.INetValueService;

@Controller
public class NetValueController {
	@Autowired
	INetValueService netValueService;
	
	
	public boolean checkAndAddAuth(){
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!auth.getPrincipal().equals("anonymousUser")) {
			
			return true;
		}
		else{
			return false;
		}
	}
	
	@RequestMapping(value = "net_value")
	public @ResponseBody
	List<NetValue> getNetValue(@RequestParam int productId) {
		List<NetValue> values = netValueService.findByProductId(productId);
		Collections.sort(values);
		return values;
	}
	
	
	@RequestMapping(value = "add_value", method = RequestMethod.POST)
	public @ResponseBody
	String addValue(@RequestBody NetValue value) {
		netValueService.insert(value);
		return "success";
	}

	@RequestMapping(value = "update_value", method = RequestMethod.POST)
	public @ResponseBody
	String updateValue(@RequestBody NetValue value) {
		netValueService.update(value);
		return "success";
	}
	
	

	@RequestMapping(value = "remove_value", method = RequestMethod.POST)
	public @ResponseBody
	String removeValue(@RequestParam int id) {
		netValueService.remove(id);
		return "success";
	}
	
	
	
	
}
