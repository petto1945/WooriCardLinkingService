package com.wooricard.chat.gw.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wooricard.chat.gw.service.StatsService;

@RestController
@RequestMapping("/common/stats")
public class StatsController {
	
	@Autowired StatsService stats;
	
	/**
	 * 금일 챗봇 질읜건수, 시나리오건수, 시나리오 진행중 건
	 * @return
	 */
	@RequestMapping(value="/todaychatbotcount", method=RequestMethod.POST)
	public HashMap<String, Object> toDayChatbotCount(){
		HashMap<String, Object> ret = new HashMap<String, Object>();
		try {
			ret = stats.toDayChatbotCount();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	/**
	 * 유입채널별인입 고객수
	 * 	- 금일누적
	 * 	- 현재이용고객
	 * 	- 합계
	 * @return
	 */
	@RequestMapping(value="/inflowchannelcustomer", method=RequestMethod.POST)
	public HashMap<String, Object> inFlowChannelCustomer() {
		HashMap<String, Object> ret = new HashMap<String, Object>();
		try {
			ret = stats.inFlowChannelCustomer();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	/**
	 * 로그인수단별인입 고객수
	 * 	- 금일누적
	 * 	- 현재잉용고객
	 * 	- 합계
	 * @return
	 */
	@RequestMapping(value="/incomingbylogin", method=RequestMethod.POST)
	public HashMap<String, Object> inComingByLogin() {
		HashMap<String, Object> ret = new HashMap<String, Object>();
		try {
			ret = stats.inComingByLogin();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	/**
	 * 시나리오 및 일반질문 TOP 5 선정
	 * @return
	 */
	@RequestMapping(value="/nosctop5", method=RequestMethod.POST)
	public HashMap<String, Object> noScTop5(){
		HashMap<String, Object> ret = new HashMap<String, Object>();
		try {
			ret = stats.noScTop5();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	/**
	 * 유입채널 고객 및 로그인 수단별 고객 데이터 적재
	 * @param request
	 */
	@RequestMapping(value="/flowlogin", method=RequestMethod.POST)
	public void flowLogin(@RequestBody HashMap<String, Object> request) {
		System.out.println("flowLogin request : " + request);
		try {
			stats.insertFlowLogin(request);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
