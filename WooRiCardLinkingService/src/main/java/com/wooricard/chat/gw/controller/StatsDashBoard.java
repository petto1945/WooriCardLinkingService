package com.wooricard.chat.gw.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/dashbo")
public class StatsDashBoard {
	
	@RequestMapping(value="/dashbo", method=RequestMethod.GET)
	public ModelAndView dashBoard() {
		ModelAndView mv = new ModelAndView();
		try {
			mv.setViewName("/dashbo");
		} catch(Exception e) {
			e.getStackTrace();
		}
		return mv;
	}
	
}
