package com.douzone.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.UserRepository;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public Boolean join(UserVo userVo) {
		int count1 = userRepository.insertUser(userVo);
		
		BlogVo blogVo = new BlogVo();
		blogVo.setId(userVo.getId());
		blogVo.setTitle("반갑습니다. "+userVo.getName()+"님의 블로그 입니다.");
		blogVo.setLogo("/assets/upimages/logo.jpg");
		int count2 = userRepository.insertBlog(blogVo);
		
		CategoryVo categortVo = new CategoryVo();
		categortVo.setName("기타");
		categortVo.setDesc("새로 생성된 카테고리입니다.");
		categortVo.setId(userVo.getId());
		int count3 = userRepository.insertCategory(categortVo);

		if (count1 == 1 && count2 == 1 && count3 == 1) {
			return true;
		}
		return false;
	}

	public UserVo getUser(UserVo vo) {
		return userRepository.find(vo);
	}

}
