package com.wooricard.chat.gw.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wooricard.chat.gw.service.TopicService;

@RestController
@RequestMapping("/common/stats")
public class TopicController {
	
	@Autowired
	private TopicService topicService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/test")
	public HashMap<String, String> topicTest(@RequestBody HashMap<String, Object> request) {
		System.out.println(" topic request : " + request);
		HashMap<String, String> ret = new HashMap<String, String>();
		try {
			String topic = request.get("topic").toString();
			HashMap<String, String> data = (HashMap<String, String>) request.get("data");
			if(request != null && request.size() != 0) {
				switch(topic) {
					case "CHAT_INFO" :
						break;
					case "USER_CONN" :
						break;
					case "OPER_CHAT" :
						break;
					case "CHAT_REQ" : 
						break;
				}
			}
		} catch(Exception e) {
			e.getStackTrace();
		}
		return ret;
	}
	
	
}
