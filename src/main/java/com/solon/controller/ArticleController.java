package com.solon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.solon.dto.Article;
import com.solon.service.spec.IArticleService;


@Controller
@RequestMapping("/article/")

public class ArticleController {
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
	
	@RequestMapping(value = "get-article")
	public @ResponseBody String getArticleById(@RequestBody int id) {
		articleService.findById(id);
		return "success";
	}
	
	@RequestMapping(value = "sl-news")
	public @ResponseBody String getArticleList(ModelMap model, @RequestBody int type) {
		
		List<Article> articles = null;
		if(type == 0){
			articles = articleService.findAll();
		}
		else{
			articles = articleService.findByType(type);
		}
		model.addAttribute("articles", articles);
		
		
		return "sl-news";
	}
}
