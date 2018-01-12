package com.nj.weblog.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.nj.weblog.entities.Post;
import com.nj.weblog.repositories.CategoryRepository;
import com.nj.weblog.repositories.PostRepository;
import com.nj.weblog.repositories.TagRepository;

@Controller
public class HomeController extends BaseController {
	
	private final PostRepository postRepository;
	private final CategoryRepository categoryRepository;
	private final TagRepository tagRepository;
	
	public HomeController(CategoryRepository categoryRepository, TagRepository tagRepository,
			PostRepository postRepository) {
		super(categoryRepository, tagRepository, postRepository);
		this.categoryRepository = categoryRepository;
		this.tagRepository = tagRepository;
		this.postRepository = postRepository;
	}

	@GetMapping("/")
	public String getAllPosts(Model model, @RequestParam(defaultValue = "0") int page) {
		Page<Post> allPosts = postRepository.findAll(new PageRequest(page, 2,Sort.Direction.DESC,"createdOn"));
		model.addAttribute("allPosts", allPosts);
		model.addAttribute("currentPage", page);
		return "index";
	}

	@GetMapping("/posts/{id}")
	public String getPostById(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("post", postRepository.findOne(id));
		return "post";
	}

	@GetMapping("/category/{categoryname}")
	public String getByCategory(@PathVariable("categoryname") String categoryName, Model model) {
		model.addAttribute("category", categoryRepository.findByName(categoryName));
		return "postsbycategory";
	}

	@GetMapping("/tag/{tagname}")
	public String getByTag(@PathVariable("tagname") String tagName, Model model) {
		model.addAttribute("tag", tagRepository.findByName(tagName));
		return "postsbytag";
	}

	@GetMapping("/archival/{year}-{month}")
	public String getByArchival(@PathVariable int year, @PathVariable int month, Model model) {
		List<Post> posts = postRepository.findByArchival(month, year);
		model.addAttribute("posts", posts);
		return "postsbyarchival";
	}

}
