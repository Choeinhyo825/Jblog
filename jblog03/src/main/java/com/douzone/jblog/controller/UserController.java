package com.douzone.jblog.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.jblog.service.UserService;
import com.douzone.jblog.vo.UserVo;
import com.douzone.security.AuthUser;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	/*
	 * 회원가입 폼
	 */
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(@ModelAttribute UserVo vo) {
		return "user/join";
	}

	/*
	 * 회원가입
	 */
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute @Valid UserVo vo, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAllAttributes(result.getModel());
			return "user/join"; 
		}
		userService.join(vo);
		return "redirect:/user/joinsuccess";
	}

	/*
	 * 회원가입 성공 폼
	 */
	@RequestMapping("/joinsuccess")
	public String joinsuccess() {
		return "user/joinsuccess";
	}

	/*
	 * 로그인 폼
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@AuthUser UserVo authUser) {
		if (authUser != null) {
			return "redirect:/main";
		}
		return "user/login";
	}
	
	/*
	 * 로그인 폼
	 */
	@RequestMapping("/mypage")
	public String mypage() {
		return "blog/blog-main";
	}
}
