package com.game.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("")
public class DefaultController {
	
	@RequestMapping("/")
	@ResponseBody
	public String show(HttpServletRequest request,HttpServletResponse response){
		String id = request.getSession().getId();

		return "hello";
	}
}
