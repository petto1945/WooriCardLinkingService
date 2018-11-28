package com.wooricard.chat.gw.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.client.AggregateIterable;
import com.mongodb.util.JSON;
import com.wooricard.chat.gw.util.DBCollections;



@Repository
public class StatsDao {
	
	@Autowired MongoOperations mongo;
	
	/**
	 * 금일 챗봇질의건수, 금일 챗봇 시나리오 건수 조회
	 * @param String date
	 * @return HashMap<String, Object>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public HashMap<String, Object> findDashCountBolt(String date) throws Exception{
		Query query = new Query();
		query.addCriteria(
			Criteria.where("date").is(date)
		);
		return  mongo.findOne(query, HashMap.class, DBCollections.STAT_DASH_COUNT);
	}
	
	/**
	 * 금일 챗봇 시나리오 진행중 건수 조회
	 * @param String date
	 * @return HashMap<String, Object>
	 * @throws Exception
	 */
	@SuppressWarnings({ "unused" })
	public long findUseScennario(String date) throws Exception {
		HashMap<String, Object> ret = new HashMap<String, Object>();
		Query query = new Query();
		query.addCriteria(
				Criteria.where("isScenario").is("Y").and("isActive").is("true")
		);
		long count = 0;
		count = mongo.count(query, HashMap.class, DBCollections.CHAT_ROOM);
		return count;
	}
	
	/**
	 * 유입채널별인입 고객수 전부 조회
	 * @param String date
	 * @return HashMap<String, Object>
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List<HashMap> findInFlow(String date) throws Exception {
		List<HashMap> ret = new ArrayList<HashMap>();
		Query query = new Query();
		query.addCriteria(
			Criteria.where("cTime").regex(date)
		);
		ret = mongo.find(query, HashMap.class, DBCollections.STAT_USER_CONN_INFLOW);
		return ret;
	}
	
	/**
	 * 로그인수단별인입 고객수 전부 조회
	 * @param String date
	 * @return HashMap<String, Object>
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List<HashMap> findLogin(String date) throws Exception {
		List<HashMap> ret = new ArrayList<HashMap>();
		Query query = new Query();
		query.addCriteria(
			Criteria.where("cTime").regex(date)
		);
		ret = mongo.find(query, HashMap.class, DBCollections.STAT_USER_CONN_LOGIN);
		return ret;
	}
	
	/**
	 * 현재 이용고객 확인
	 * @param String roomKey
	 * @return HashMap<String, Object>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public boolean findChatRoom(String roomKey) throws Exception {
		boolean ret = false;
		HashMap<String, Object> findData = new HashMap<String, Object>();
		Query query = new Query();
		query.addCriteria(
			Criteria.where("cid").is(roomKey).and("isActive").is(true)
		);
		findData = mongo.findOne(query, HashMap.class, DBCollections.CHAT_ROOM);
		if(findData != null) {
			ret = true;
		}
		return ret;
	}
	
	/**
	 * 시나리오 또는 일반 질문 TOP5를 죄회하여 가져온다
	 * qustType 로 일반 또는 시나리오 분
	 * @param date
	 * @param qustType
	 * @return List<HashMap>
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "deprecation" })
	public List<HashMap> findTop5(String date, String qustType) throws Exception {
		List<HashMap> ret = new ArrayList<HashMap>();
	
		ArrayList<Bson> list = new ArrayList<Bson>();
		String query1 = "{'$match':{'date':'"+date+"','qusttype':'"+qustType+"'}}";
		String query2 = "{'$project':{'_id':0,qids:{$concat:['$qustid_1','/','$qustid_2','/','$qustid_3']}}}";
		String query3 = "{'$group':{'_id':'$qids','count':{'$sum':1}}}";
		String query4 = "{'$sort':{'count':-1,'qids':1}}";
		list.add((Bson) JSON.parse(query1));
		list.add((Bson) JSON.parse(query2));
		list.add((Bson) JSON.parse(query3));
		list.add((Bson) JSON.parse(query4));
		
		AggregateIterable<HashMap> ago = mongo.getCollection("STAT_QUESTION").aggregate(list, HashMap.class);
		for (HashMap hashMap : ago) {
			ret.add((HashMap) hashMap);
		}
		return ret;
	}
	
	/**
	 * TOP5 가져온 정보를 가지고
	 * QUST Collection 을 qustId1,2,3 으로 조회하여 DisplayName을 조회하여 화면에 보여준다.
	 * @param request
	 * @return HashMap<String, Object>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public HashMap<String, Object> findDisplayName(HashMap<String, String> request) throws Exception {
		HashMap<String, Object> ret = new HashMap<String, Object>();
		String qustId1 = request.get("qustId1").toString();
		String qustId2 = request.get("qustId2").toString();
		String qustId3 = request.get("qustId3").toString();
		Query query = new Query();
		query.addCriteria(
			Criteria.where("qustId1").is(qustId1).and("qustId2").is(qustId2).and("qustId3").is(qustId3)
		);
		ret = mongo.findOne(query, HashMap.class, "QUST");
		return ret;
	}
}
