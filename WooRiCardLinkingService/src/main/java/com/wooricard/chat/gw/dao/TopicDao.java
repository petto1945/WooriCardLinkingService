package com.wooricard.chat.gw.dao;

import java.util.HashMap;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.wooricard.chat.gw.util.DBCollections;


@Repository
public class TopicDao {
	
	@Autowired
	private MongoTemplate mongo;
	
	public HashMap<String, String> topicTest(HashMap<String, String> request) throws Exception{
		HashMap<String, String> ret = new HashMap<String, String>();
		ret.put("Hi", "Hellow");
		return ret;
	}
	
	@SuppressWarnings("unchecked")
	public HashMap<String, Object> selectStatUserConn(String uid, String date, String time){
		Query query = new Query();
		query.addCriteria(
			Criteria.where("uid").is(uid)
			.and("date").is(date)
			.and("time").is(time)
		);
		
		return mongo.findOne(query, HashMap.class, DBCollections.STAT_USER_CONN);
	}
	
	public void insertStatUserConn(Document doc){
		mongo.insert(doc, DBCollections.STAT_USER_CONN);
	}
	
	public void updateStatUserConn(String uid, String date, String time){
		Query query = new Query();
		query.addCriteria(
			Criteria.where("uid").is(uid)
			.and("date").is(date)
			.and("time").is(time)
		);
		
		mongo.updateFirst(query, new Update().inc("count", 1), DBCollections.STAT_USER_CONN);
	}
	
	@SuppressWarnings("unchecked")
	public HashMap<String, Object> selectDashCount(String date){
		Query query = new Query();
		query.addCriteria(
			Criteria.where("date").is(date)
		);
		
		return mongo.findOne(query, HashMap.class, DBCollections.STAT_DASH_COUNT);
	}
	
	public void insertDashCount(Document doc){
		mongo.insert(doc, DBCollections.STAT_DASH_COUNT);
	}
	
	public void updateDashCount(String date, String inc){
		Query query = new Query();
		query.addCriteria(
			Criteria.where("date").is(date)
		);
		Update update = new Update().inc(inc, 1);
		mongo.updateFirst(query, update, DBCollections.STAT_DASH_COUNT);
	}
	
	public void insertStatQust(Document doc){
		mongo.insert(doc, DBCollections.STAT_QUESTION);
	}
	
	public void insertStatAnswerStatus(Document doc){
		mongo.insert(doc, DBCollections.STAT_ANSWER_STATUS);
	}
	
}
