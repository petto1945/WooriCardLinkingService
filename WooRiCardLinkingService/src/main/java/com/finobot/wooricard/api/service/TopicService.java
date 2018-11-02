package com.finobot.wooricard.api.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finobot.wooricard.api.dao.TopicDao;

@Service
public class TopicService {
	
	@Autowired
	private TopicDao topicDao;
	
	public HashMap<String, String> topicTest(HashMap<String, String> request) throws Exception{
		HashMap<String, String> ret = new HashMap<String, String>();
		return ret;
	}
}
