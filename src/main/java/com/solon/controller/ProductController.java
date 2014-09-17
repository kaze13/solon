package com.solon.controller;

import java.util.HashMap;
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
import com.solon.dto.Product;
import com.solon.service.spec.INetValueService;
import com.solon.service.spec.IProductService;

@Controller
public class ProductController {
	@Autowired
	IProductService productService;
	@Autowired
	INetValueService netValueService;
	
	@RequestMapping(value = "add_product", method = RequestMethod.POST)
	public @ResponseBody
	String addProduct(@RequestBody Product product) {
		productService.insert(product);
		return "success";
	}

	@RequestMapping(value = "update_product", method = RequestMethod.POST)
	public @ResponseBody
	String updateProduct(@RequestBody Product product) {
		productService.update(product);
		return "success";
	}

	@RequestMapping(value = "remove_product", method = RequestMethod.POST)
	public @ResponseBody
	String removeProduct(@RequestParam int id) {
		netValueService.removeByProduct(id);
		productService.remove(id);
		return "success";
	}
	
	@RequestMapping(value = "get-product")
	public String getProductById(@RequestParam int id, ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!auth.getPrincipal().equals("anonymousUser")) {
			model.addAttribute("authenticated", true);
			model.addAttribute("mode", "modify");
		}
		
		Product product = productService.findById(id);
		if(product == null){
			return "redirect:product-list";
		}
		model.addAttribute("product", product);
		Map<String, String> params = new HashMap<String, String>();
		//used for select product, only open product will be selected.
		params.put("status", "1");

		List<Product> products = productService.getProductsByPaging(params,
				Integer.valueOf(1));

		List<NetValue> netValues = netValueService.findByProductId(product
				.getProductId());
		

		model.addAttribute("netValues", netValues);
		model.addAttribute("otherProducts", products);
		

		return "product-page";
	}
	
	@RequestMapping(value = {"product-list", "admin"})
	public String getProductList(ModelMap model) {
		
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!auth.getPrincipal().equals("anonymousUser")) {
			model.addAttribute("authenticated", true);
		}
		int pages = productService.getProductsPagesCount(null);
		model.addAttribute("pages", pages);
		return "product-list";
	}

	@RequestMapping(value = "get-product-list", method = RequestMethod.POST)
	public @ResponseBody
	List<Product> getProduct(@RequestBody Map<String, String> params) {
		String id = params.get("page");
		params.remove("page");

		List<Product> products = productService.getProductsByPaging(params,
				Integer.valueOf(id));
		return products;
	}
	
	@RequestMapping(value = {"add-product"})
	public String addProduct(ModelMap model) {
		
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!auth.getPrincipal().equals("anonymousUser")) {
			model.addAttribute("authenticated", true);
		}
		else{
			return "redirect:login";
		}
		Product p = new Product();
		model.addAttribute("product", p);
		model.addAttribute("mode", "add");
		return "product-page";
	}
	
	@RequestMapping(value = {"copy-add-product"})
	public String copyAddProduct(@RequestParam int id, ModelMap model) {
		
		
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!auth.getPrincipal().equals("anonymousUser")) {
			model.addAttribute("authenticated", true);
		}
		else{
			return "redirect:login";
		}
		Product p = productService.findById(id);
		if(p == null){
			return "redirect:product-list";
		}
		model.addAttribute("product", p);
		model.addAttribute("mode", "add");
		return "product-page";
	}


}
