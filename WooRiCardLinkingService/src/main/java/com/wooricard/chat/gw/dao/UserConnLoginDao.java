package com.wooricard.chat.gw.dao;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.wooricard.chat.gw.util.DBCollections;

@Repository
public class UserConnLoginDao {

	@Autowired MongoOperations mongo;
	
	/**
	 * STAT_USER_CONN_LOGIN 컬랙션 오늘 날짜를 조회한다.
	 * @param request
	 * @return HashMap<String, Object>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public HashMap<String, Object> selectLogin(HashMap<String, Object> request) throws Exception {
		String date =  request.get("date").toString();
		Query query = new Query();
		query.addCriteria(
			Criteria.where("date").is(date)
		);
		return mongo.findOne(query, HashMap.class, DBCollections.STAT_USER_CONN_LOGIN);
	}
	
	/**
	 * STAT_USER_CONN_LOGIN Collection 에 데이터를 적재한다.
	 * @param request
	 * @throws Exception
	 */
	public void insertLogin(HashMap<String, Object> request) throws Exception {
		mongo.insert(request, DBCollections.STAT_USER_CONN_LOGIN);
	}
	
}
