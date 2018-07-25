package com.cicec.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cicec.pojo.ResponseMessage;
import com.cicec.pojo.User;
import com.cicec.pojo.UserResponseMessage;
import com.cicec.service.UserService;
import com.cicec.tool.PathConfig;
import com.cicec.tool.Tools;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping("/getuserall")
	public @ResponseBody List<User> getUserAll() {
		List<User> userList = userService.getUserAll();
		return userList;
	}

	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public @ResponseBody ResponseMessage signIn(@RequestBody User user, HttpSession session) {
		ResponseMessage rm;
		if (user.getName().isEmpty() || user.getPassword().isEmpty())
			rm = new ResponseMessage(0, "填写信息不完整");
		List<User> result = userService.getUserForName(user.getName());
		if (!result.isEmpty()) {
			User rUser = result.get(0);
			if (user.getPassword().equals(rUser.getPassword())) {
				rUser.setAvatar(PathConfig.onlineImagePath + "/" + rUser.getAvatar());
				session.setAttribute("currentUser", rUser);
				rm = new ResponseMessage(1, "用户登录成功");
			} else {
				rm = new ResponseMessage(0, "密码错误");
			}
		} else {
			rm = new ResponseMessage(0, "用户名不存在");
		}
		return rm;
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public @ResponseBody ResponseMessage signUp(@RequestBody User user) {
		ResponseMessage rm;
		if (user.getName() == null || user.getPassword() == null)
			rm = new ResponseMessage(0, "填写信息不完整");
		List<User> userList = userService.getUserForName(user.getName());
		if (!userList.isEmpty()) {
			rm = new ResponseMessage(0, "用户名已存在");
		} else {
			user.setAvatar("avatar-default.jpg");
			user.setDescription("简单介绍一下自己");
			if (user.getPhone().isEmpty())
				user.setPhone("请添加手机号码");
			if (user.getEmail().isEmpty())
				user.setEmail("请添加邮箱地址");
			userService.addUser(user);
			rm = new ResponseMessage(1, "用户注册成功");
		}
		return rm;
	}

	@RequestMapping(value = "/getuserinfo", method = RequestMethod.GET)
	public @ResponseBody ResponseMessage getUserInfo(HttpSession session) {
		UserResponseMessage rm;
		User user = (User) session.getAttribute("currentUser");
		if (user != null) {
			rm = new UserResponseMessage(1, "用户已登录", user);
		} else {
			rm = new UserResponseMessage(0, "用户未登录", null);
		}
		return rm;
	}

	@RequestMapping(value = "/modifyuserinfo", method = RequestMethod.POST)
	public @ResponseBody ResponseMessage modifyUserInfo(@RequestBody User userInfo, HttpSession session) {
		UserResponseMessage rm;
		User currrentUser = (User) session.getAttribute("currentUser");
		if (currrentUser != null) {
			if (userInfo.getName() != null) {
				List<User> result = userService.getUserForName(userInfo.getName());
				if (!result.isEmpty()) {
					rm = new UserResponseMessage(0, "用户名已存在", null);
					return rm;
				}
			}
			if (userInfo.getAvatar() != null) {
				String imageName = Tools.saveBase64ToImage(userInfo.getAvatar());
				userInfo.setAvatar(imageName);
			}
			userInfo.setId(currrentUser.getId());
			userService.modifyUser(userInfo);
			User modifiedUser = userService.getUserForId(userInfo.getId()).get(0);
			modifiedUser.setAvatar(PathConfig.onlineImagePath + "/" + modifiedUser.getAvatar());
			session.setAttribute("currentUser", modifiedUser);
			rm = new UserResponseMessage(1, "用户信息修改成功", modifiedUser);
		} else {
			rm = new UserResponseMessage(0, "用户未登录", null);
		}
		return rm;
	}
	
	@RequestMapping(value = "/signout", method = RequestMethod.GET)
	public @ResponseBody ResponseMessage signOut(HttpSession session) {
		ResponseMessage rm;
		User user = (User) session.getAttribute("currentUser");
		if (user != null) {
			session.removeAttribute("currentUser");
			session.invalidate();
			rm = new ResponseMessage(1, "退出登录成功");
		} else {
			rm = new ResponseMessage(0, "用户未登录");
		}
		return rm;
	}
}
