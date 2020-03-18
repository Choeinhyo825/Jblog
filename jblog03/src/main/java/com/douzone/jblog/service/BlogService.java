package com.douzone.jblog.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

@Service
public class BlogService {

	@Autowired
	BlogRepository blogRepository;

	private static final String SAVE_PATH = "/jblog-uploads";
	private static final String URL = "/assets/images";

	// 블로그 메인 화면
	public ModelMap findAll(String id, Long categoryNo, Long postNo) {
		ModelMap map = new ModelMap();

		BlogVo blogVo = blogRepository.findBlog(id);
		List<CategoryVo> categoryList = blogRepository.findCategory(id);
		List<PostVo> postList = blogRepository.findPost(id);

		map.put("blogVo", blogVo);
		map.put("categoryList", categoryList);
		map.put("postList", postList);

		return map;
	}

	public BlogVo findBasic(String id) {
		return blogRepository.findBasic(id);
	}

	public Boolean updateBasic(String id, String title, MultipartFile multipartFile) {
		String url;
		BlogVo vo = new BlogVo();
		try {
			String originFilename = multipartFile.getOriginalFilename();

			int lastIndex = originFilename.lastIndexOf('.');
			String extName = originFilename.substring(lastIndex + 1);
			String saveFilename = generateSaveFilename(originFilename.substring(lastIndex + 1));

			if (!"".equals(extName)) {
				byte[] fileData = multipartFile.getBytes();
				OutputStream os = new FileOutputStream(SAVE_PATH + "/" + saveFilename);
				os.write(fileData);
				os.close();
				url = URL + "/" + saveFilename;
				vo.setLogo(url);
			}
			vo.setId(id);
			vo.setTitle(title);
			blogRepository.updateBasic(vo);

			return true;

		} catch (IOException e) {
			throw new RuntimeException("file upload error : " + e);
		}
	}

	private String generateSaveFilename(String extName) {

		String filename = "";

		Calendar calendar = Calendar.getInstance();
		filename += calendar.get(calendar.YEAR);
		filename += calendar.get(calendar.MONTH);
		filename += calendar.get(calendar.DATE);
		filename += calendar.get(calendar.HOUR);
		filename += calendar.get(calendar.MINUTE);
		filename += calendar.get(calendar.SECOND);
		filename += calendar.get(calendar.MILLISECOND);
		filename += ("." + extName);

		return filename;
	}

	public List<HashMap<String, Object>> findCategory(String id) {
		return blogRepository.findCategoryList(id);
	}

	public int insertCategory(CategoryVo vo) {
		return blogRepository.insertCategory(vo);
	}
	
	public int deleteCategory(CategoryVo vo) {
		return blogRepository.deleteCategory(vo);
	}

	public List<CategoryVo> categoryOption(String id) {
		return blogRepository.categoryOption(id);
	}

	public int writePost(String id, String title, String category, String content) {
		Map map = new HashMap();
		map.put("id", id);
		map.put("title", title);
		map.put("category", category);
		map.put("content", content);
		return blogRepository.writePost(map);
	}



}
