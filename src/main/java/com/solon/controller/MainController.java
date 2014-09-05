package com.solon.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.solon.dto.NetValue;
import com.solon.dto.Product;
import com.solon.dto.User;
import com.solon.service.spec.INetValueService;
import com.solon.service.spec.IProductService;
import com.solon.service.spec.IUserService;

@Controller
@RequestMapping("/")
public class MainController {

	@Autowired
	IProductService productService;
	@Autowired
	IUserService userService;
	@Autowired
	INetValueService netValueService;

	@RequestMapping
	public String index(Model model) {
		return "index";
	}

	@RequestMapping(value = "show")
	public String product(ModelMap model) {
		List<Product> products = productService.findAll();
		Map<Integer, List<NetValue>> valueMapping = new HashMap<Integer, List<NetValue>>();
		for (Product product : products) {
			valueMapping.put(product.getProductId(),
					netValueService.findByProductId(product.getProductId()));
		}
		model.addAttribute("products", products);
		model.addAttribute("valueMapping", valueMapping);
		return "product";
	}

	@RequestMapping(value = "products")
	public @ResponseBody
	List<Product> getAllProducts() {
		return productService.findAll();
	}

	@RequestMapping(value = "product")
	public @ResponseBody
	Product getProduct(@RequestParam int id) {
		return productService.findById(id);
	}

	@RequestMapping(value = "net_value")
	public @ResponseBody
	List<NetValue> getNetValue(@RequestParam int productId) {
		List<NetValue> values = netValueService.findByProductId(productId);
		Collections.sort(values);
		return values;
	}

	@RequestMapping(value = "user")
	public @ResponseBody
	User getUser(@RequestParam String id) {
		return userService.findById(id);
	}

}