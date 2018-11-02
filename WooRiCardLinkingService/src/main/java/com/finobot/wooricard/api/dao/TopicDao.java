package com.finobot.wooricard.api.dao;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TopicDao {
	
	@Autowired
	private MongoTemplate mongo;
	
	public HashMap<String, String> topicTest(HashMap<String, String> request){
		HashMap<String, String> ret = new HashMap<String, String>();
		return ret;
	}
}
