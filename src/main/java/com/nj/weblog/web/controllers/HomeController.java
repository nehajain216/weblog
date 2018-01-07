package com.nj.weblog.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.nj.weblog.entities.Category;
import com.nj.weblog.entities.Post;
import com.nj.weblog.entities.Tag;
import com.nj.weblog.models.Archival;
import com.nj.weblog.repositories.CategoryRepository;
import com.nj.weblog.repositories.PostRepository;
import com.nj.weblog.repositories.TagRepository;

@Controller
public class HomeController 
{
	private final PostRepository postRepository;
	private final CategoryRepository categoryRepository;
	private final TagRepository tagRepository;
	
	@Autowired
	public HomeController(PostRepository postRepository, CategoryRepository categoryRepository, TagRepository tagRepository) {
		super();
		this.postRepository = postRepository;
		this.categoryRepository = categoryRepository;
		this.tagRepository = tagRepository;
	}
	
	@GetMapping("/")
	public String getAllPosts(Model model, @RequestParam(defaultValue="0") int page)
	{
		Page<Post> allPosts = postRepository.findAll(new PageRequest(page, 2));
		model.addAttribute("allPosts", allPosts);
		model.addAttribute("currentPage", page);
		return "index";
	}
	
	@GetMapping("/posts/{id}")
	public String getPostById(@PathVariable("id") Integer id, Model model)
	{
		model.addAttribute("post",postRepository.findOne(id));
		return "post";
	}
	
	@GetMapping("/category/{categoryname}")
	public String getByCategory(@PathVariable("categoryname") String categoryName, Model model)
	{
		model.addAttribute("category",categoryRepository.findByName(categoryName));
		return "postsbycategory";
	}
	
	@GetMapping("/tag/{tagname}")
	public String getByTag(@PathVariable("tagname") String tagName, Model model)
	{
		model.addAttribute("tag",tagRepository.findByName(tagName));
		return "postsbytag";
	}
	
	@GetMapping("/archival/{year}-{month}")
	public String getByArchival(@PathVariable int year, @PathVariable int month, Model model)
	{
		List<Post> posts = postRepository.findByArchival(month, year);
		model.addAttribute("posts",posts);
		return "postsbyarchival";
	}
	
	@ModelAttribute(name="categories")
	public List<Category> allCategories()
	{
		return categoryRepository.findAll();
	}
	
	@ModelAttribute(name="tags")
	public List<Tag> allTags()
	{
		return tagRepository.findAll();
	}
	
	@ModelAttribute(name="archivals")
	public List<Archival> archival(Model model)
	{
		List<Archival> archivals = postRepository.findByCreatedOn();
		return archivals;
	}
	
}
