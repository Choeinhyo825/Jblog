package com.douzone.jblog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;

@Controller
@RequestMapping("/{id:(?!assets).*}")
//@RequestMapping("/{id:(?!(assets|user)).*}")
public class BlogController {

	@Autowired
	private BlogService blogService;

	// 블로그 메인 화면
	@RequestMapping({ "", "/{pathNo1}", "/{pathNo1}/{pathNo2}" })
	public String index(@PathVariable String id, 
			@PathVariable Optional<Long> pathNo1,
			@PathVariable Optional<Long> pathNo2, 
			ModelMap model) {

		Long categoryNo = 0L;
		Long postNo = 0L;

		if (pathNo2.isPresent()) {
			postNo = pathNo2.get();
			categoryNo = pathNo1.get();
		} else if (pathNo1.isPresent()) {
			categoryNo = pathNo1.get();
		}
		model.putAll(blogService.findAll(id, categoryNo, postNo));
		return "blog/blog-main";
	}

	// 블로그 기본 관리
	@RequestMapping(value = "/admin/basic", method = RequestMethod.GET)
	public String basic(@PathVariable String id, Model model) {
		BlogVo vo = blogService.findBasic(id);
		model.addAttribute("BlogVo",vo);
		return "blog/blog-admin-basic";
	}

	// 블로그 기본 수정
	@RequestMapping(value = "/admin/basic", method = RequestMethod.POST)
	public String basic(@PathVariable String id, String title,  @RequestParam(value = "logo-file") MultipartFile multipartFile) {
		blogService.updateBasic(id,title,multipartFile);
		return "redirect:basic";
	}

	// 블로그 카테고리 관리
	@RequestMapping(value = "/admin/category", method = RequestMethod.GET)
	public String category(@PathVariable String id, Model model) {
		System.out.println("id!!!!!!!" + id);
		List<HashMap<String, Object>> categoryList = blogService.findCategory(id);
		model.addAttribute("categoryList",categoryList);
		return "blog/blog-admin-category";
	}

	// 블로그 카테고리 수정
	@RequestMapping(value = "/admin/category", method = RequestMethod.POST)
	public String category(@PathVariable String id, String name, String desc) {
		CategoryVo vo = new CategoryVo();
		vo.setId(id);
		vo.setName(name);
		vo.setDesc(desc);
		blogService.insertCategory(vo);
		return "redirect:category";
	}
	
	// 블로그 카테고리 삭제
	@RequestMapping(value = "/admin/category/{no}", method = RequestMethod.GET)
	public String category(@PathVariable String id, @PathVariable Long no) {
		CategoryVo vo = new CategoryVo();
		vo.setId(id);
		vo.setNo(no);
		blogService.deleteCategory(vo);
		System.out.println("!!!!!!!!!!!!!!!!!!!!1");
		return "redirect:category";
	}

	// 블로그 글 작성 폼
	@RequestMapping(value = "/admin/write", method = RequestMethod.GET)
	public String categoryOption(@PathVariable String id, Model model) {
		List<CategoryVo> category = blogService.categoryOption(id);
		model.addAttribute("category", category);
		return "blog/blog-admin-write";
	}

	// 블로그 글 등록
	@RequestMapping(value = "/admin/write", method = RequestMethod.POST)
	public String writePost(@PathVariable String id, String title, String category, String content) {
		blogService.writePost(id,title,category,content);
		return "redirect:write";
	}

}
