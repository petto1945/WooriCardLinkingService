package com.finobot.wooricard.api.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finobot.wooricard.api.service.TopicService;

@RestController
@RequestMapping("/topic")
public class TopicController {
	
	@Autowired
	private TopicService topicService;
	
	public HashMap<String, String> topicTest(@RequestBody HashMap<String, String> request) {
		HashMap<String, String> ret = new HashMap<String, String>();
		try {
			
		} catch(Exception e) {
			e.getStackTrace();
		}
		return ret;
	}
}
