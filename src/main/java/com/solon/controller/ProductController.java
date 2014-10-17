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

	public static class SaveParam {
		private Product productValue;
		private Map<String, List<NetValue>> netValue;

		public Product getProductValue() {
			return productValue;
		}

		public void setProductValue(Product productValue) {
			this.productValue = productValue;
		}

		public Map<String, List<NetValue>> getNetValue() {
			return netValue;
		}

		public void setNetValue(Map<String, List<NetValue>> netValue) {
			this.netValue = netValue;
		}

	}

	@Autowired
	IProductService productService;
	@Autowired
	INetValueService netValueService;

	@RequestMapping(value = "add_product", method = RequestMethod.POST)
	public @ResponseBody
	String addProduct(@RequestBody SaveParam param) {
		if (!checkAndAddAuth(new ModelMap())) {
			return "redirect:login";
		}

		Product product = param.getProductValue();
		int id = productService.insert(product);
		Map<String, List<NetValue>> netValues = param.getNetValue();
		List<NetValue> toInsert = netValues.get("toInsert");
		for (NetValue val : toInsert) {
			val.setProductId(id);
		}
		netValueService.insertByBatch(toInsert);

		return "success";
	}

	@RequestMapping(value = "update_product1", method = RequestMethod.POST)
	public @ResponseBody
	String updateProduct1(@RequestBody SaveParam param) {

		if (!checkAndAddAuth(new ModelMap())) {
			return "redirect:login";
		}
		Product product = param.getProductValue();
		Map<String, List<NetValue>> netValues = param.getNetValue();

		// System.out.println("I am here");

		List<NetValue> toInsert = netValues.get("toInsert");
		List<NetValue> toDelete = netValues.get("toDelete");
		List<NetValue> toModify = netValues.get("toModify");

		netValueService.insertByBatch(toInsert);
		netValueService.deleteByBatch(toDelete);
		netValueService.updateByBatch(toModify);

		productService.update(product);

		return "success";
	}

	@RequestMapping(value = "remove_product", method = RequestMethod.POST)
	public @ResponseBody
	String removeProduct(@RequestParam int id) {
		if (!checkAndAddAuth(new ModelMap())) {
			return "redirect:login";
		}
		netValueService.removeByProduct(id);
		productService.remove(id);
		return "success";
	}

	public void addOtherProducts(ModelMap model) {
		Map<String, String> params = new HashMap<String, String>();
		// used for select product, only open product will be selected.
		params.put("status", "0");
		List<Product> products = productService.getProductsByPaging(params,
				Integer.valueOf(1));
		model.addAttribute("otherProducts", products);
	}

	public boolean checkAndAddAuth(ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!auth.getPrincipal().equals("anonymousUser")) {
			model.addAttribute("authenticated", true);

			return true;
		} else {
			return false;
		}
	}

	@RequestMapping(value = "get-product")
	public String getProductById(@RequestParam int id, ModelMap model) {

		if (checkAndAddAuth(model)) {
			model.addAttribute("mode", "modify");
		}
		Product product = productService.findById(id);
		if (product == null) {
			return "redirect:product-list";
		}
		addOtherProducts(model);
		model.addAttribute("product", product);

		List<NetValue> netValues = netValueService.findByProductId(product
				.getProductId());

		model.addAttribute("netValues", netValues);

		return "product-page";
	}

	@RequestMapping(value = { "product-list", "admin" })
	public String getProductList(ModelMap model) {

		checkAndAddAuth(model);

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

	@RequestMapping(value = { "add-product" })
	public String addProduct(ModelMap model) {

		if (!checkAndAddAuth(model)) {
			return "redirect:login";
		}

		addOtherProducts(model);

		Product p = new Product();
		model.addAttribute("product", p);
		model.addAttribute("mode", "add");
		return "product-page";
	}

	@RequestMapping(value = { "copy-add-product" })
	public String copyAddProduct(@RequestParam int id, ModelMap model) {

		if (!checkAndAddAuth(model)) {
			return "redirect:login";
		}

		addOtherProducts(model);
		Product p = productService.findById(id);
		if (p == null) {
			return "redirect:product-list";
		}
		model.addAttribute("product", p);
		model.addAttribute("mode", "add");
		return "product-page";
	}

}
