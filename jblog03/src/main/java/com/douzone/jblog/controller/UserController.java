package com.douzone.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.jblog.service.UserService;
import com.douzone.jblog.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	/*
	 * 회원가입 폼
	 */
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {
		return "user/join";
	}

	/*
	 * 회원가입
	 */
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(UserVo vo) {
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
	public String login() {
		return "user/login";
	}
	
	/*
	 * 로그인 폼
	 */
	@RequestMapping("/mypage")
	public String mypage() {
		return "blog/blog-main";
	}
	
	
//
//	/*
//	 * 회원정보 수정 폼
//	 */
//	@RequestMapping(value = "/update", method = RequestMethod.GET)
//	public String update(Model model, @AuthUser UserVo authUser) {
////		UserVo vo = userService.getUser(authUser.getNo());
////		model.addAttribute("userVo", vo);
//		return "/user/update";
//	}
//
//	/*
//	 * 회원정보 수정
//	 */
//	@RequestMapping(value = "/update", method = RequestMethod.POST)
//	public String update(UserVo vo, @AuthUser UserVo authUser) {
////		vo.setNo(authUser.getNo());
//		userService.updateUser(vo);
//		authUser.setName(vo.getName());
//		return "/user/updatesuccess";
//	}
//	@ExceptionHandler(Exception.class)
//	public String handleException() {
//		return "error/exception";
//	}
}
