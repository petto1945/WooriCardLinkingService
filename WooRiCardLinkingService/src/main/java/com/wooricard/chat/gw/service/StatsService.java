package com.wooricard.chat.gw.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wooricard.chat.gw.dao.StatsDao;
import com.wooricard.chat.gw.dao.UserConnInFlowDao;
import com.wooricard.chat.gw.dao.UserConnLoginDao;
import com.wooricard.chat.gw.util.DateUtil;

@Service
public class StatsService {

	@Autowired StatsDao statsDao;
	@Autowired UserConnInFlowDao flowDao;
	@Autowired UserConnLoginDao loginDao;
	
	/**
	 * 금일 챗봇 질의 건수, 금일 챗봇 시나리오 건순, 금일 챗봇 시나리오 진행중 건수
	 * @return HashMap<String, Object>
	 * @throws Exception
	 */
	public HashMap<String, Object> toDayChatbotCount() throws Exception {
		HashMap<String, Object> ret = new HashMap<String, Object>();
		String time = DateUtil.getCurrentRealTime();
		
		String date = time.substring(0, 8);
		HashMap<String, Object> dashCountBolt = new HashMap<String, Object>();
		dashCountBolt = statsDao.findDashCountBolt(date);
		int scenarioCount = 0;
		int endScenarioCount = 0;
		// 조회된 데이터가 있는 경우 데이터 셋
		if(dashCountBolt != null && dashCountBolt.size() > 0) {
			scenarioCount = Integer.parseInt(dashCountBolt.get("scenario_count").toString());
			endScenarioCount = Integer.parseInt(dashCountBolt.get("endScenario_count").toString());
			ret.put("generalCount", dashCountBolt.get("general_count"));		// 금일 챗봇 질의견수
			ret.put("scenarioCount", scenarioCount);	// 금일 시나리오 질의건수 
			ret.put("useScenarioCount", (scenarioCount - endScenarioCount));	// 금일 챗봇 시나리오 진행중 건수
		} else {
			// 조회된 데이터가 없는 경우 0으로 처리
			ret.put("generalCount", 0);
			ret.put("scenarioCount", 0);
			ret.put("useScenarioCount", 0);
		}
		return ret;
	}
	
	/**
	 * 유입채널별인입 고객수
	 * 	- 금일누적
	 * 	- 현재이용고객
	 * 	- 합계
	 * @return HashMap<String, Object>
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public HashMap<String, Object> inFlowChannelCustomer() throws Exception {
		String time = DateUtil.getCurrentRealTime();
		String date = time.substring(0, 8);
		HashMap<String, Object> ret = new HashMap<String, Object>();
		
		List<HashMap> retList = new ArrayList<HashMap>();
		HashMap retCount = new HashMap();	// 유입채널별 고객수 카운팅
		HashMap retUseCount = new HashMap();	// 현재 진행중인 고객수 카운팅
		long todayTotalCount = 0;	//	금일 누적 건
		long useCount = 0;			//	현재이용중인 고객수 합
		// chanel 채널 정보 
		String chanel[] = {"app", "mweb", "pcweb", "fds", "ars", "kakao"};
		for (String string : chanel) {
			// 채널정보 마다 초기값 0으로 셋
			retCount.put(string, 0);
			retUseCount.put(string, 0);
		}
		boolean use = false;
		List<HashMap> inFlow = inFlow = statsDao.findInFlow(date);
		// 채널 정보를 가지고 카운팅 데이터 셋팅
		for (HashMap hashMap : inFlow) {
			String sch = hashMap.get("sch").toString();
			for (String string : chanel) {
				if(string.equals(sch)) {
					retCount.put(sch, Integer.parseInt(retCount.get(sch).toString()) + 1);
					todayTotalCount++;
					String roomKey = hashMap.get("roomKey").toString();
					use = statsDao.findChatRoom(roomKey); // CHAT_ROOM 을 조회하여 현재 사용중인 챗봇인지 확인
					if (use) {
						useCount++;
						retUseCount.put(sch,  useCount);
					}
					break;
				}
			}
		}
		
		retList.add(retCount);
		retList.add(retUseCount);
		ret.put("inFlowChannel", retList);
		ret.put("todayTotalCount", todayTotalCount);
		ret.put("useCount", useCount);
		
		return ret;
	}
	
	/**
	 * 로그인수단별인입 고객수
	 * 	- 금일누적
	 * 	- 현재이용고객
	 * 	- 합계
	 * @return HashMap<String, Object>
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public HashMap<String, Object> inComingByLogin() throws Exception {
		String time = DateUtil.getCurrentRealTime();
		String date = time.substring(0, 8);
		HashMap<String, Object> ret = new HashMap<String, Object>();
		
		List<HashMap> retList = new ArrayList<HashMap>(); // 최종 return 전 List로 데이터 파싱
		HashMap retCount = new HashMap();	// 로그인 인입 고객별 카운팅
		HashMap retUseCount = new HashMap();	// 현재 이용중인 고객 카운
		long loginTotalCount = 0;	// 누적건수
		long loginUseCount = 0;		// 현재 이용중인 고객 합계
		// chanel 채널 정보 
		String chanel[] = {"loginChannel", "loginCard", "loginHp", "loginNick", "loginArs"};
		for (String string : chanel) {
			// 채널정보 마다 초기값 0으로 셋
			retCount.put(string, 0);
			retUseCount.put(string, 0);
		}
		boolean use = false;
		List<HashMap> inFlow = inFlow = statsDao.findLogin(date);
		// 채널 정보를 가지고 카운팅 데이터 셋
		for (HashMap hashMap : inFlow) {
			String loginType = hashMap.get("loginType").toString();
			for (String string : chanel) {
				if(string.equals(loginType)) {
					retCount.put(loginType, Integer.parseInt(retCount.get(loginType).toString()) + 1);
					loginTotalCount++;
					String roomKey = hashMap.get("roomKey").toString();
					use = statsDao.findChatRoom(roomKey); // CHAT_ROOM 을 조회하여 현재 사용중인 챗봇인지 확
					if (use) {
						loginUseCount++;
						retUseCount.put(loginType, loginUseCount);
					}
					break;
				}
			}
		}
		
		retList.add(retCount);
		retList.add(retUseCount);
		ret.put("loginMeans", retList);
		ret.put("loginTotalCount", loginTotalCount);
		ret.put("loginUseCount", loginUseCount);
		
		return ret;
	}
	
	/**
	 * 금일 대표질문 TOP5
	 * 금일 시나리오 TOP5
	 * @return HashMap<String, Object>
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public HashMap<String, Object> noScTop5() throws Exception {
		HashMap<String, Object> ret = new HashMap<String, Object>();

		List<HashMap> listNormal = new ArrayList<HashMap>();		// TOP5 조회
		List<HashMap> listScenario = new ArrayList<HashMap>();	// TOP5 조회
		String time = DateUtil.getCurrentRealTime();
		String date = time.substring(0, 8);
		
		listNormal = statsDao.findTop5(date, "normal");		// 일반 질문 전체를 카운트가 높은순으로 조회
		listScenario = statsDao.findTop5(date, "scenario");	// 시나리오 질의 전체 카운트 높은순으로 조
		
		List<String> retNoDisplay = new ArrayList<String>();	// TOP5 Normal DisplayName 
		List<String> retScDisplay = new ArrayList<String>();	// TOP5 Scenario DisplayName
		// 전체를 가져와서 TOP5만 가져온
		for(int i=0; i<5; i++) {
			HashMap<String, String> noReqDeisplay = new HashMap<String, String>();
			HashMap<String, String> scReqDeisplay = new HashMap<String, String>();
			String[] normalQid  = null;
			if(listNormal != null && !(listNormal.size() <= i)) {
				HashMap normalMap = listNormal.get(i);
				normalQid = normalMap.get("_id").toString().split("/");
			}
			String[] scenarioQid = null;
			if(listScenario != null && !(listScenario.size() <= i)) {
				HashMap scenarioMap = listScenario.get(i);
				scenarioQid = scenarioMap.get("_id").toString().split("/");
			}
			
			// qustid 를 HashMap 에 넣어 DisplayName 을 조회한다.
			for (int j=0; j<3; j++) {
				if(normalQid != null && normalQid.length > 0) {
					noReqDeisplay.put("qustId"+(j+1), normalQid[j]);
				}
				if(scenarioQid != null && scenarioQid.length > 0) {
					scReqDeisplay.put("qustId"+(j+1), scenarioQid[j]);
				}
			}
			
			// 일반질분 최종 데이터 파싱
			HashMap display = null;
			if(noReqDeisplay != null && noReqDeisplay.size() > 0) {
				display = statsDao.findDisplayName(noReqDeisplay);
				// DisplayName 조회한 결과가 있을때문 데어타 파싱 없는 경우 "-" 처리
				if(display != null && display.size() > 0) {
					retNoDisplay.add(display.get("displayName").toString());
				}
			}
			
			// 시나리오 질문 최종 데이터 파싱
			display = null;
			if(scReqDeisplay != null && scReqDeisplay.size() > 0) {
				display = statsDao.findDisplayName(scReqDeisplay);
				// DisplayName 조회한 결과가 있을때문 데어타 파싱 없는 경우 "-" 처리
				if(display != null && display.size() > 0) {
					retScDisplay.add(display.get("displayName").toString());
				}
			}
		}
		
		// return 데이터 파싱
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("scenario", retScDisplay);
		data.put("normal", retNoDisplay);
		ret.put("top5", data);
		
		return ret;
	}
	
	/**
	 * 유입채널별 및 로그인 수단별 데이터 적재
	 * @param request
	 * @throws Exception
	 */
	public void insertFlowLogin(HashMap<String, Object> request) throws Exception {
		String loginType = request.get("loginType").toString();
		//TODO Monstro 에서 보내주는 데이터가 대문자냐 소문자냐에 따라 변경될수 있
		if(loginType == null && "".equals(loginType)) {
			flowDao.insertInFlow(request);
		} else {
			flowDao.insertInFlow(request);
			loginDao.insertLogin(request);
		}
	}
	 	
}
