package com.wooricard.chat.gw.service;

import java.util.HashMap;
import java.util.Iterator;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.json.simple.JSONObject;

import com.wooricard.chat.gw.dao.ChatDao;
import com.wooricard.chat.gw.dao.TopicDao;
import com.wooricard.chat.gw.util.DateUtil;


@Service
public class TopicService {
	
	@Autowired TopicDao topicDao;
	@Autowired ChatDao chatDao;
	
	/**
	 * <PRE>
	 * 간략 : 통계저장 .
	 * 상세 : .
	 * <PRE>
	 * @throws Exception 
	 */
	public void insertChatInfo(HashMap<String, String> hashMap) throws Exception {
		String time = DateUtil.getCurrentRealTime();
		hashMap.put("date"    , time.substring(0, 8));
		hashMap.put("date"    , time.substring(8, 10));
//		jsonDataObj.put("date"    , time.substring(0, 8));
//		jsonDataObj.put("time"    , time.substring(8, 10));
//		jsonDataObj.put("qustid_1", chatLogInfo.getQid_1());
//		jsonDataObj.put("qustid_2", chatLogInfo.getQid_2());
//		jsonDataObj.put("qustid_3", chatLogInfo.getQid_3());
//		jsonDataObj.put("question", chatLogInfo.getQ());
//		jsonDataObj.put("qusttype", chatLogInfo.getIsScenario().equals("Y")?"scenario":"normal");
//		jsonDataObj.put("status"  , chatLogInfo.getMessageType().equals("NOT_STUDY_REQUEST") || chatLogInfo.getMessageType().equals("NO_DATA") ? "false" : "true");
//		jsonDataObj.put("uid"  , chatLogInfo.getUid());
		this.chatInfo(hashMap);
	}
	
	/**
	 * <PRE>
	 * 간략 : 통계저장 .
	 * 상세 : .
	 * <PRE>
	 * @throws Exception 
	 */
	public void insertUserConn(String uid) throws Exception {
		String time = DateUtil.getCurrentRealTime();
		HashMap<String, String> jsonDataObj = new HashMap<String, String>();
		jsonDataObj.put("date"    , time.substring(0, 8));
		jsonDataObj.put("uid"  , uid);
		
		this.userConn(jsonDataObj);
	}
	
	@SuppressWarnings("rawtypes")
	private void userConn(HashMap request) throws Exception {
		this.userConnection(request);
	}
	
	@SuppressWarnings("rawtypes")
	private void chatInfo(HashMap request) throws Exception {
		String qusttype = request.containsKey("qusttype") ? request.get("qusttype").toString() : "";
		if("scenario".equals(qusttype)){
			String qustid_1 = request.containsKey("qustid_1") ? request.get("qustid_1").toString() : "";
			String qustid_2 = request.containsKey("qustid_2") ? request.get("qustid_2").toString() : "";
			String qustid_3 = request.containsKey("qustid_3") ? request.get("qustid_3").toString() : "";
			JSONObject sceneObj = chatDao.selectSceneDataCmpl("", qustid_1, qustid_2, qustid_3);
			String step = sceneObj.containsKey("step") ? sceneObj.get("step").toString() : "";
			if(!"S".equals(step)) return ;
		}
		
		this.dashCountBolt(request);
		this.questionProcessBolt(request);
		this.writeUserProcessBolt(request);
	}
	
	/**
	 * 사용자정보 체크
	 * 
	 * @param request
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void userConnection(HashMap hashMap) throws Exception {
		System.out.println("userConnection input data ================== : " + hashMap);

		String uid = hashMap.containsKey("uid") ? hashMap.get("uid").toString() : "";
		String date = hashMap.containsKey("date") ? hashMap.get("date").toString() : "";

		if (!"".equals(uid) && !"".equals(date)) {
			hashMap.put("qusttype", "userConn");
			this.dashCountProcessBolt(hashMap);
		}
	}

	/**
	 * DashCountBolt 질문 성공 / 실패 확인
	 * 
	 * @param request
	 */
	@SuppressWarnings({"rawtypes", "unchecked" })
	public void dashCountBolt(HashMap hashMap) throws Exception{
		System.out.println("dashCountBolt input data ================== : " + hashMap);
		System.out.println("dashCountBolt input qusttype ================== : " + hashMap.get("qusttype"));

		String status = hashMap.containsKey("status") ? hashMap.get("status").toString() : "";
		String date = hashMap.containsKey("date") ? hashMap.get("date").toString() : "";
		String qusttype = hashMap.containsKey("qusttype") ? hashMap.get("qusttype").toString() : "";

		if (!"".equals(status) && !"".equals(date) && !"".equals(qusttype)) {
			if ("true".equals(status)) {
				this.dashCountProcessBolt(hashMap);
			} else {
				hashMap.put("qusttype", "fail");
				this.dashCountProcessBolt(hashMap);
			}
		}
	}

	/**
	 * QuestionProcessBolt 서비스 로직
	 * 
	 * @param request
	 */
	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	public void questionProcessBolt(HashMap hashMap) throws Exception{
		System.out.println("questionProcessBolt input data ================== : " + hashMap);
		System.out.println("questionProcessBolt input qusttype ================== : " + hashMap.get("qusttype"));

		String uid = hashMap.containsKey("uid") ? hashMap.get("uid").toString() : "";
		String date = hashMap.containsKey("date") ? hashMap.get("date").toString() : "";
		String result = hashMap.containsKey("status") ? hashMap.get("status").toString() : "";
		if (!"".equals(result) && !"null".equals(result)) {
			Document doc = new Document();
			doc = this.setDoc(doc, hashMap);
			doc.remove("uid");
			doc.remove("status");

			if ("true".equals(result)) {
				// 1. VCS_REPORT_QUESTION 저장 시작
				this.saveVcsReportQuestion(doc);
			}
			
			doc.put("result", result);
			// 2. VCS_REPORT_ANSWER_STATUS 저장 시작
			this.saveVcsReportAnswerStatus(doc);
		}
	}

	/**
	 * WriteUserProcessBolt 시간별 사용자 정보 Insert 또는 Update
	 * 
	 * @param request
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void writeUserProcessBolt(HashMap hashMap) throws Exception {
		System.out.println("writeuserprocessbolt input data ================== : " + hashMap);
		System.out.println("writeuserprocessbolt input qusttype ================== : " + hashMap.get("qusttype"));
		HashMap reqHashMap = hashMap;

		String uid = hashMap.containsKey("uid") ? hashMap.get("uid").toString() : "";
		String date = hashMap.containsKey("date") ? hashMap.get("date").toString() : "";
		String time = hashMap.containsKey("time") ? hashMap.get("time").toString() : "";

		if (!"".equals(uid) && !"".equals(date) && !"".equals(time)) {
			reqHashMap.remove("status");
			reqHashMap.remove("qustid_1");
			reqHashMap.remove("qustid_2");
			reqHashMap.remove("qustid_3");
			reqHashMap.remove("qusttype");
			reqHashMap.remove("question");

			Document doc = new Document();
			doc = this.setDoc(doc, reqHashMap);

			HashMap<String, Object> findMap = topicDao.selectStatUserConn(uid, date, time);
			if (findMap == null) {
				doc.put("count", 1);
				topicDao.insertStatUserConn(doc);
			} else {
				topicDao.updateStatUserConn(uid, date, time);
			}
		}
	}

	/**
	 * DashCountProcessBolt (건수 저장)
	 * 
	 * @param request
	 * @throws Exception 
	 */
	private void dashCountProcessBolt(HashMap<String, Object> request) throws Exception {
		System.out.println("DashCountProcessBolt input data ================== : " + request);
		System.out.println("DashCountProcessBolt input qusttype ================== : " + request.get("qusttype"));

		String qusttype = request.containsKey("qusttype") ? request.get("qusttype").toString() : "";
		String strDate = request.containsKey("date") ? request.get("date").toString() : "";
		
		HashMap<String, Object> findMap = topicDao.selectDashCount(strDate);

		if (findMap != null) {
			this.updateDashData(strDate, qusttype);
		} else {
			this.insertDashData(strDate, qusttype);
		}
	}

	/**
	 * DashCountProcessBolt VCS_REPORT_DASH_COUNT 오늘날짜 데이터가 없는경우 insert
	 * 
	 * @param request
	 * @param strDate
	 * @param qusttype
	 * @throws Exception
	 */
	private void insertDashData(String strDate, String qusttype)
			throws Exception {
		int conn_count = 0;
		int general_count = 0;
		int scenario_count = 0;
		int answer_failed_count = 0;

		if ("userConn".equals(qusttype)) {
			conn_count++;
		} else if ("normal".equals(qusttype)) {
			general_count++;
		} else if ("scenario".equals(qusttype)) {
			scenario_count++;
		} else {
			answer_failed_count++;
		}

		Document user = new Document();
		user.put("date", strDate);
		user.put("conn_count", conn_count);
		user.put("general_count", general_count);
		user.put("scenario_count", scenario_count);
		user.put("answer_failed_count", answer_failed_count);

		topicDao.insertDashCount(user);
	}

	/**
	 * DashCountProcessBolt VCS_REPORT_DASH_COUNT 오늘날짜가 있는경우 카운팅 후 Update
	 * 
	 * @param request
	 * @param strDate
	 * @param qusttype
	 */
	private void updateDashData(String strDate, String qusttype) {
		if ("userConn".equals(qusttype)) {
			topicDao.updateDashCount(strDate, "conn_count");
		} else if ("normal".equals(qusttype)) {
			topicDao.updateDashCount(strDate, "general_count");
		} else if ("scenario".equals(qusttype)) {
			topicDao.updateDashCount(strDate, "scenario_count");
		} else {
			topicDao.updateDashCount(strDate, "answer_failed_count");
		}
	}

	/**
	 * QuestionProcessBolt Collection Name : VCS_REPORT_QUESTION 일반/시나리오 성공한 정보를
	 * 저장한다. 랭킹용
	 * 
	 * @param db
	 * @param dataObj
	 * @return
	 * @throws Exception
	 */
	private void saveVcsReportQuestion(Document doc) throws Exception {
		topicDao.insertStatQust(doc);
	}

	/**
	 * QuestionProcessBolt Collection Name : VCS_REPORT_ANSWER_STATUS 질문에 대한 성공/실패
	 * 정보를 저장한다.
	 * 
	 * @param db
	 * @param dataObj
	 * @param doc
	 * @throws Exception
	 */
	private void saveVcsReportAnswerStatus(Document doc) throws Exception {
		doc.remove("status");
		doc.remove("qustid_1");
		doc.remove("qustid_2");
		doc.remove("qustid_3");
		doc.remove("qusttype");
		doc.remove("itype");
		topicDao.insertStatAnswerStatus(doc);
	}
	
	
	/**
	 * HashMap -> Document
	 * @param doc
	 * @param jsonObj
	 * @return Document
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	private Document setDoc(HashMap<String, Object> jsonObj){
		Document doc = new Document();
		Iterator itr = jsonObj.keySet().iterator();
		while(itr.hasNext()){
			String strKey = itr.next().toString();
			doc.put(strKey, jsonObj.get(strKey));
		}
		System.out.println(" return doc =============== : " + doc);
		return doc;
	}
	
	/**
	 * HashMap -> Document
	 * 
	 * @param doc
	 * @param jsonObj
	 * @return Document
	 */
	@SuppressWarnings("rawtypes")
	private Document setDoc(Document doc, HashMap<String, Object> jsonObj) {
		Iterator itr = jsonObj.keySet().iterator();
		while (itr.hasNext()) {
			String strKey = itr.next().toString();
			doc.put(strKey, jsonObj.get(strKey));
		}
		System.out.println(" return doc =============== : " + doc);
		return doc;
	}
}
