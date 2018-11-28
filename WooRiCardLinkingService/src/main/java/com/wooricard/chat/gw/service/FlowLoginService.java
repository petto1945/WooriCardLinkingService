package com.wooricard.chat.gw.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wooricard.chat.gw.dao.UserConnInFlowDao;
import com.wooricard.chat.gw.dao.UserConnLoginDao;

@Service
public class FlowLoginService {
	
	@Autowired UserConnInFlowDao flowDao;
	@Autowired UserConnLoginDao loginDao;
	
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
