package com.wooricard.chat.gw.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.wooricard.chat.gw.service.StatsDashBoardService;

@RestController
@RequestMapping("/stats")
public class StatsDashBoard {
	
	@Autowired StatsDashBoardService statsService;
	
	
	@RequestMapping(value="dashboard", method=RequestMethod.POST)
	public ModelAndView dashBoard(@RequestBody HashMap<String, String> hashMap) {
		ModelAndView mv = new ModelAndView();
		try {
			HashMap<String, Object> result = statsService.dashBoard(hashMap);
			if(result != null && result.size() > 0) {
				mv.addAllObjects(result);
				mv.setViewName("dashBoard");
			}
		} catch(Exception e) {
			e.getStackTrace();
		}
		return mv;
	}
}
