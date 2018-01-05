package com.nj.weblog.web.controllers;

import java.util.List;
import java.util.Set;

import org.junit.experimental.categories.Categories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.nj.weblog.entities.Category;
import com.nj.weblog.entities.Post;
import com.nj.weblog.entities.Tag;
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
	public String getAllPosts(Model model)
	{
		List<Post> posts = postRepository.findAll();
		model.addAttribute("allPosts", posts);
		return "index";
	}
	
	@GetMapping("/posts/{id}")
	public String getPostById(@PathVariable("id") Integer id, Model model)
	{
		Post post = postRepository.findOne(id);
		model.addAttribute("post",post);
		return "post";
	}
	
	@GetMapping("/category/{categoryname}")
	public String getByCategory(@PathVariable("categoryname") String categoryName, Model model)
	{
		Category category = categoryRepository.findByName(categoryName);
		model.addAttribute("category",category);
		return "postsbycategory";
	}
	
	@GetMapping("/tag/{tagname}")
	public String getByTag(@PathVariable("tagname") String tagName, Model model)
	{
		Tag tag = tagRepository.findByName(tagName);
		model.addAttribute("tag",tag);
		return "postsbytag";
	}
	
	@ModelAttribute(name="categories")
	public List<Category> allCategories()
	{
		List<Category> categories = categoryRepository.findAll();
		return categories;
	}
	
	@ModelAttribute(name="tags")
	public List<Tag> allTags()
	{
		List<Tag> tags = tagRepository.findAll();
		return tags;
	}
}
