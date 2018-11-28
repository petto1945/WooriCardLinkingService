/**
 * 0. Project  : Finobot Monstro
 *
 * 1. FileName : CollectionName.java
 * 2. Package : kr.co.finotek.finobot.monstro.common.constant
 * 3. Comment : 
 * 4. 작성자  : sky
 * 5. 작성일  : 2018. 1. 23. 오후 2:33:19
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    sky : 2018. 1. 23. :            : 신규 개발.
 */

package com.wooricard.chat.gw.util;

public interface DBCollections {
	// Configuration
	public static String CHAT_CONF_MSG		= "CHAT_CONF_MSG";		// 기본채팅메세지 정보
	public static String CHAT_CONF_IMG		= "CHAT_CONF_IMG";		// 채팅 이미지 리스트
	public static String CHAT_CSS			= "CHAT_CSS";			// 채팅 CSS
	public static String CHAT_MENU			= "CHAT_MENU";			// 채팅 메뉴 리스트
	public static String CHAT_NOTICE		= "CHAT_NOTICE";		// 공지 사항
	public static String CHAT_NICKNAME		= "CHAT_NICKNAME";		// 챗봇 이름 정보
	public static String CHAT_SWEARWORD		= "CHAT_SWEARWORD";		// 욕설 리스
	
	// Chat Message Process
	public static String CHAT_ETC_TEMP_MSG	= "CHAT_ETC_TEMP_MSG";	// 임시 질문 데이터 - 기타테그 response body 임시저장하여 다음시나리오에 사용
	public static String CHAT_INOUT_DATA	= "CHAT_INOUT_DATA";	// in/out tag data 저장
	public static String CHAT_LAST_DATA		= "CHAT_LAST_DATA";		// 이전 답변 데이터 관리
	public static String CHAT_LAST_LIST		= "CHAT_LAST_LIST";		// 답변 데이터 관리(시나리오 답변정보) - 객관식, 키워드 답변 목록 등
	public static String CHAT_MISMATCH		= "CHAT_MISMATCH";		// 미응답 메시지 (유사율등 못찾은 답변 관리)
	public static String CHAT_MISMATCH_CNT	= "CHAT_MISMATCH_CNT";	// NO_DATA일 때 fail count
	public static String CHAT_MSG			= "CHAT_MSG";			// 체팅 이력
	public static String CHAT_ROOM			= "CHAT_ROOM";			// 방 리스트
	public static String CHAT_SCENE_HOLD	= "CHAT_SCENE_HOLD"; 	// 사용자가 중단한 시나리오 정보 - 새로운상담하기로 인하여 중단된 시나리오 정보를 저장 관리
	public static String CHAT_USER			= "CHAT_USER";			// 사용자 정보

	// Geppotto Question & Scenario
	public static String FILTER_OPTION		= "FILTER_OPTION";		// 유사도 옵션정보
	public static String QUST				= "QUST";				// 일반 및 시나리오 대표질문
	public static String SCENE_DIRECT_URL	= "SCENE_DIRECT_URL";	// 시나리오 직접연결 정보
	public static String SCENE_DATA_CMPL	= "SCENE_DATA_CMPL";	// 머신러닝된 시나리오 데이터
	public static String SCENE_VIEW_CMPL	= "SCENE_VIEW_CMPL";	// 제페토 jointjs 시나리오 정보

	// Tag Info
	public static String TAG_ETC			= "TAG_ETC";			// 기타태그 리스트
	public static String TAG_INOUT			= "TAG_INOUT";			// 태그 리스트
	public static String TAG_INOUT_VALID	= "TAG_INOUT_VALID";	// 입력검증 함수
	public static String TAG_LAYER			= "TAG_LAYER";			// 레이어태그 정보
	public static String TAG_UNIT			= "TAG_UNIT";			// 유닛태그 정보

	public static String SHORT_QUST_CMPL	= "SHORT_QUST_CMPL";	// 단문키워드 정보
	public static String KEYWORD			= "KEYWORD";			// 키워드 관리
	public static String KEYWORD_NOT_ML		= "KEYWORD_NOT_ML";		// 학습안된키워드 정보
	
	// log chatting
	public static String LOG_CHAT			= "LOG_CHAT";			// 채팅이력관리 - 통계로그
	
	// statistics 
	public static String STAT_USER_CONN		= "STAT_USER_CONN";			// 통계 접속유저
	public static String STAT_USER_CONN_INFLOW = "STAT_USER_CONN_INFLOW";	// 통계 유입채널별인입 고객수
	public static String STAT_USER_CONN_LOGIN =  "STAT_USER_CONN_LOGIN";	// 통계 로그인수단별인입 고객수
	public static String STAT_DASH_COUNT	= "STAT_DASH_COUNT";			// 통계 카운트  
	public static String STAT_QUESTION		= "STAT_QUESTION";		// 통계 랭킹용 
	public static String STAT_ANSWER_STATUS	= "STAT_ANSWER_STATUS";	// 통계 질문 성공/실패  
	
	// 핵심 단어 
	public static String CORE_WORD			= "CORE_WORD";			// 핵심단어 
	
}