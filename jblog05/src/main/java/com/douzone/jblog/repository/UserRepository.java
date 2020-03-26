package com.douzone.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.UserVo;

@Repository
public class UserRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public int insertUser(UserVo userVo) {
		return sqlSession.insert("user.insertUser", userVo);
	}

	public int insertBlog(BlogVo blogVo) {
		return sqlSession.insert("user.insertBlog", blogVo);
	}

	public int insertCategory(CategoryVo categoryVo) {
		return sqlSession.insert("user.insertCategory", categoryVo);
	}

	public UserVo find(UserVo vo) {
		return sqlSession.selectOne("user.findUser", vo);
	}



}
