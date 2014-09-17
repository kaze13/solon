package com.solon.controller;

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

import com.solon.dto.Article;
import com.solon.service.spec.IArticleService;

@Controller
public class ArticleController {
	@Autowired
	IArticleService articleService;
	
	@RequestMapping(value = "add-article")
	public String addArticle(ModelMap model) {
		// TODO: Test Auth
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!auth.getPrincipal().equals("anonymousUser")) {
			model.addAttribute("authenticated", true);
		}
		else{
			return "redirect:login";
		}
		return "add-article";
	}


	@RequestMapping(value = "sl-news")
	public String getArticleList(ModelMap model) {
		
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!auth.getPrincipal().equals("anonymousUser")) {
			model.addAttribute("authenticated", true);
			
		}
		
		int pages = articleService.articleCount(null);
		model.addAttribute("pages", pages);
		return "sl-news";
	}
	
	@RequestMapping(value = "submit-article", method = RequestMethod.POST)
	public @ResponseBody
	String addArticle(@RequestBody Article article) {
		articleService.insert(article);
		return "success";
	}

	@RequestMapping(value = "delete-article", method = RequestMethod.POST)
	public @ResponseBody
	String deleteArticle(@RequestBody int id) {
		articleService.remove(id);
		return "success";
	}
	
	@RequestMapping(value = "get-article")
	public String getArticleById(@RequestParam int id, ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!auth.getPrincipal().equals("anonymousUser")) {
			model.addAttribute("authenticated", true);
			
		}
		
		Article article = articleService.findById(id);
		List<Article> recentArticles = articleService.findAll();
		recentArticles = recentArticles.subList(0,
				10 < recentArticles.size() ? 10 : recentArticles.size());
		model.addAttribute("article", article);
		model.addAttribute("recentArticles", recentArticles);
		return "article-page";
	}
	
	@RequestMapping(value = "get-article-list", method = RequestMethod.POST)
	public @ResponseBody
	List<Article> getArticle(@RequestBody Map<String, String> params) {
		String id = params.get("page");
		params.remove("page");

		List<Article> articles = articleService.getArticleByPaging(params,
				Integer.valueOf(id));
		return articles;
	}

}
