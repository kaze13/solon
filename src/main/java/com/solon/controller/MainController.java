package com.solon.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.solon.dto.Article;
import com.solon.dto.NetValue;
import com.solon.dto.Product;
import com.solon.dto.User;
import com.solon.service.spec.IArticleService;
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
	

	@RequestMapping(value = {"/", "index"})
	public String index(Model model) {
		return "index";
	}
	


//	@RequestMapping(value = "admin")
//	public String admin(Model model) {
//		List<Product> products = productService.findAll();
//		List<User> users = userService.findAll();
//		List<NetValue> netValues = netValueService.findAll();
//		model.addAttribute("products", products);
//		model.addAttribute("users", users);
//		model.addAttribute("netValues", netValues);
//		return "admin";
//	}

	@RequestMapping(value = { "show", "admin" })
	public String product(ModelMap model) {
		List<Product> products = productService.findAll();
		Map<Integer, List<NetValue>> valueMapping = new HashMap<Integer, List<NetValue>>();
		for (Product product : products) {
			valueMapping.put(product.getProductId(),
					netValueService.findByProductId(product.getProductId()));
		}
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!auth.getPrincipal().equals("anonymousUser")) {
			model.addAttribute("authenticated", true);
		}
		model.addAttribute("products", products);
		model.addAttribute("valueMapping", valueMapping);
		return "product";
	}

	@RequestMapping(value = "products")
	public @ResponseBody List<Product> getAllProducts() {
		return productService.findAll();
	}

	@RequestMapping(value = "product")
	public @ResponseBody Product getProduct(@RequestParam int id) {
		return productService.findById(id);
	}

	@RequestMapping(value = "net_value")
	public @ResponseBody List<NetValue> getNetValue(@RequestParam int productId) {
		List<NetValue> values = netValueService.findByProductId(productId);
		Collections.sort(values);
		return values;
	}

	@RequestMapping(value = "user")
	public @ResponseBody User getUser(@RequestParam String id) {
		return userService.findById(id);
	}

	@RequestMapping(value = "add_product", method = RequestMethod.POST)
	public @ResponseBody String addProduct(@RequestBody Product product) {
		productService.insert(product);
		return "success";
	}

	@RequestMapping(value = "update_product", method = RequestMethod.POST)
	public @ResponseBody String updateProduct(@RequestBody Product product) {
		productService.update(product);
		return "success";
	}
	
	@RequestMapping(value = "remove_product", method = RequestMethod.POST)
	public @ResponseBody String removeProduct(@RequestParam int id){
		productService.remove(id);
		return "success";
	}

	@RequestMapping(value = "add_value", method = RequestMethod.POST)
	public @ResponseBody String addValue(@RequestBody NetValue value) {
		netValueService.insert(value);
		return "success";
	}
	
	@RequestMapping(value = "update_value", method = RequestMethod.POST)
	public @ResponseBody String updateValue(@RequestBody NetValue value) {
		netValueService.update(value);
		return "success";
	}
	
	@RequestMapping(value = "remove_value", method = RequestMethod.POST)
	public @ResponseBody String removeValue(@RequestParam int id){
		netValueService.remove(id);
		return "success";
	}
	

	@RequestMapping(value="about-us")
	public String aboutUs(){
		
		return "about-us";
	}
	
	@RequestMapping(value="contact-us")
	public String contactUs(){
		
		return "contact-us";
	}
	
	@RequestMapping(value="add-article")
	public String addArticle(){
		//TODO: Test Auth
		return "add-article";
	}
	
	
	@Autowired
	IArticleService articleService;
	

	
	@RequestMapping(value = "submit-article", method = RequestMethod.POST)
	public @ResponseBody String addArticle(@RequestBody Article article) {
		articleService.insert(article);
		return "success";
	}
	
	@RequestMapping(value = "delete-article", method = RequestMethod.POST)
	public @ResponseBody String deleteArticle(@RequestBody int id) {
		articleService.remove(id);
		return "success";
	}
	
	@RequestMapping(value = "get-product")
	public String getProductById(@RequestParam int id, ModelMap model) {
		Product product = productService.findById(id);
		model.addAttribute("product", product);
		List<NetValue> netValues = netValueService.findByProductId(product.getProductId());
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!auth.getPrincipal().equals("anonymousUser")) {
			model.addAttribute("authenticated", true);
		}
		
		model.addAttribute("netValues", netValues);
		
		return "product-page";
	}
	
	
	@RequestMapping(value = "get-article")
	public String getArticleById(@RequestParam int id, ModelMap model) {
		Article article = articleService.findById(id);
		List<Article> recentArticles = articleService.findAll();
		recentArticles = recentArticles.subList(0, 10 < recentArticles.size() ? 10 : recentArticles.size());
		model.addAttribute("article", article);
		model.addAttribute("recentArticles", recentArticles);
		return "article-page";
	}
	
	@RequestMapping(value = "get-article-list", method = RequestMethod.POST)
	public @ResponseBody List<Article> getArticle(@RequestBody Map<String, String> params) {
		String id = params.get("page");
		params.remove("page");
		
		List<Article> articles = articleService.getArticleByPaging(params, Integer.valueOf(id));
		return articles;
	}
	
	@RequestMapping(value = "sl-news")
	public String getArticleList(ModelMap model) {
		int pages = articleService.articleCount(null);
		model.addAttribute("pages", pages);
		return "sl-news";
	}
	
	@RequestMapping(value = "product-list")
	public String getProductList(ModelMap model) {
		int pages = productService.getProductsPagesCount(null);
		model.addAttribute("pages", pages);
		return "product-list";
	}
	
	@RequestMapping(value = "get-product-list", method = RequestMethod.POST)
	public @ResponseBody List<Product> getProduct(@RequestBody Map<String, String> params) {
		String id = params.get("page");
		params.remove("page");
		
		List<Product> products = productService.getProductsByPaging(params, Integer.valueOf(id));
		return products;
	}
	
	
	
	


}