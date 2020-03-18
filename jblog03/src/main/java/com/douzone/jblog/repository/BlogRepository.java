package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

@Repository
public class BlogRepository {
	@Autowired
	private SqlSession sqlSession;

	public BlogVo findBlog(String id) {
		return sqlSession.selectOne("blog.findBlog", id);
	}

	public List<CategoryVo> findCategory(String id) {
		return sqlSession.selectList("blog.findCategory", id);
	}

	public List<PostVo> findPost(String id) {
		return sqlSession.selectList("blog.findPost", id);
	}

	public BlogVo findBasic(String id) {
		return sqlSession.selectOne("blog.findBasic", id);
	}

	public int updateBasic(BlogVo vo) {
		return sqlSession.update("blog.updateBasic", vo);
	}

	public List<HashMap<String, Object>> findCategoryList(String id) {
		return sqlSession.selectList("blog.findCategoryList", id);
	}

	public int insertCategory(CategoryVo vo) {
		return sqlSession.insert("blog.insertCategory", vo);
	}
	
	public int deleteCategory(CategoryVo vo) {
		sqlSession.delete("blog.deletePost", vo);
		return sqlSession.delete("blog.deleteCategory", vo);
	}

	public List<CategoryVo> categoryOption(String id) {
		return sqlSession.selectList("blog.categoryOption", id);
	}

	public int writePost(Map map) {
		int category = sqlSession.selectOne("blog.postCategory",map);
		map.put("category", category);
		return sqlSession.insert("blog.writePost", map);
	}



}
