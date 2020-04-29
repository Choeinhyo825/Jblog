package com.douzone.jblog.controller.api;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.jblog.dto.JsonResult;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.vo.CategoryVo;

@RestController("ApiController")
@RequestMapping("/{id:(?!assets).*}/api")

public class BlogController {

	@Autowired
	private BlogService blogService;
	
	@GetMapping("/category/list")
	public JsonResult list(@PathVariable String id ) {
		List<HashMap<String, Object>> list = blogService.findCategory(id);
		return JsonResult.success(list);
	}

	@PostMapping("/category/add")
	public JsonResult add(@PathVariable String id, @RequestBody CategoryVo vo) {
		vo.setId(id);
		blogService.insertCategory(vo);
		List<HashMap<String, Object>> list = blogService.findCategory(id);
		return JsonResult.success(list);
	}
	
	@DeleteMapping("/category/delete")
	public JsonResult delete(@PathVariable String id,
			@RequestParam(value = "no", required = true, defaultValue = "") Long no) {
		int result = blogService.deleteCategory(new CategoryVo(no,id));
		return JsonResult.success(result==1 ? no : -1);
	}

	
}
