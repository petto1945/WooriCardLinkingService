/**
 * 0. Project  : Finobot Monstro
 *
 * 1. FileName : ChatLogDaoImpl.java
 * 2. Package : kr.co.finotek.finobot.monstro.api.dao
 * 3. Comment : 
 * 4. 작성자  : sky
 * 5. 작성일  : 2018. 5. 2. 오후 3:25:01
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    sky : 2018. 5. 2. :            : 신규 개발.
 */

package com.wooricard.chat.gw.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONException;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.wooricard.chat.gw.util.DBCollections;
import com.wooricard.chat.gw.util.DateUtil;

@Repository
public class ChatDao {
	@Autowired MongoOperations mongoOperation;
	
	public ArrayList<JSONObject> selectKeywordList(String sameKeyword) {
		Query query = new Query();
		query.addCriteria(
			Criteria.where("sameKeyword").is(sameKeyword)
		);
		
		return (ArrayList<JSONObject>) mongoOperation.find(query, JSONObject.class, DBCollections.KEYWORD);
	}

	@SuppressWarnings("rawtypes")
	public HashMap selectQust(String _id) {
		Query query = new Query();
		query.addCriteria(
			Criteria.where("_id").is(_id)
		);
		
		return mongoOperation.findOne(query, HashMap.class, DBCollections.QUST);
	}

	public JSONObject selectQust(String qustId1, String qustId2, String qustId3) {
		Query query = new Query();
		query.addCriteria(
			Criteria.where("qustId1").is(qustId1).and("qustId2").is(qustId2).and("qustId3").is(qustId3)
		);

		return mongoOperation.findOne(query, JSONObject.class, DBCollections.QUST);
	}

	public void updateChatLastList(JSONObject obj) {
		mongoOperation.save(obj, DBCollections.CHAT_LAST_LIST);
	}

	public void deleteChatLastList(String cid) {
		Query query = new Query();
		query.addCriteria(
			Criteria.where("_id").is(cid)
		);
		
		mongoOperation.remove(query, DBCollections.CHAT_LAST_LIST);
	}

	public JSONObject selectChatLastList(String cid) {
		Query query = new Query();
		query.addCriteria(
			Criteria.where("_id").is(cid)
		);
		
		return mongoOperation.findOne(query, JSONObject.class, DBCollections.CHAT_LAST_LIST);
	}
	
	@SuppressWarnings("unchecked")
	public void insertChatLastData(JSONObject insObj, String cid) throws JSONException {
		insObj.put("_id", cid);
		mongoOperation.save(insObj, DBCollections.CHAT_LAST_DATA);	
	}

	public JSONObject selectChatLastData(String cid) {
		Query query = new Query();
		query.addCriteria(
			Criteria.where("_id").is(cid)
		);
		
		return mongoOperation.findOne(query, JSONObject.class, DBCollections.CHAT_LAST_DATA);
	}
	
	public JSONObject selectSceneDataCmpl(String retId, String qustId1, String qustId2, String qustId3) {
		Query query = new Query();
		
		if (!StringUtils.isEmpty(retId)) {
			query = new Query(Criteria.where("retId").is(retId));
		} else {
			query = new Query(
				Criteria.where("qustId1").is(qustId1).and("qustId2").is(qustId2).and("qustId3").is(qustId3)
			);
		}
		
		return mongoOperation.findOne(query, JSONObject.class, DBCollections.SCENE_DATA_CMPL);
	}
	
	// 대표질문, 시나리오 디스플레이명 등의 셋팅값을 조회
	@SuppressWarnings("unchecked")
	public JSONObject selectSceneDataCmplDetail(String qustId1, String qustId2, String qustId3, String retId) throws JSONException {
		JSONObject jointjsObj = null;
		
		Query query = new Query();
		if(!qustId1.equals("") && qustId1.indexOf("#") == 0){
			query = new Query(
				Criteria.where("qustId1").is(qustId1).and("qustId2").is(qustId2).and("qustId3").is(qustId3)
			);			
		} else {
			query = new Query(Criteria.where("retId").is(retId));
		}	
		JSONObject csvObj = mongoOperation.findOne(query, JSONObject.class, DBCollections.SCENE_DATA_CMPL);
		
		if (csvObj != null){
			String sceneIds = csvObj.get("sceneId")+"";
			String [] sceneId = sceneIds.split("[|]");
			
			query = new Query(
				Criteria.where("comCode").is(sceneId[0]).and("viewId").is(sceneId[1])
			);
			
			jointjsObj = mongoOperation.findOne(query, JSONObject.class, DBCollections.SCENE_VIEW_CMPL); 
			jointjsObj.put("_id", csvObj.get("_id")+"");
		}
		
		return jointjsObj;
	}
	
	/**
	* <PRE>
	* 간략 : .
	* 상세 : .
	* <PRE>
	*/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void insertLogChat(HashMap hashMap) {
		hashMap.put("Transaction_end_time", DateUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
		mongoOperation.save(hashMap, DBCollections.LOG_CHAT);
	}

	// 기타태그 responsebody로 넘어온 데이터 저장
	public void insertChatEtcTempMsg(String cid, String request){
		Query query = new Query(Criteria.where("_id").is(cid));
		Update update = new Update();
		update.set("request", request);
		mongoOperation.upsert(query, update, DBCollections.CHAT_ETC_TEMP_MSG);
	}
	
	// 기타태그 responsebody로 넘어온 데이터 조회
	@SuppressWarnings("rawtypes")
	public HashMap selectChatEtcTempMsg(String cid){
		Query query = new Query(Criteria.where("_id").is(cid));
		return mongoOperation.findOne(query, HashMap.class, DBCollections.CHAT_ETC_TEMP_MSG);
	}
}