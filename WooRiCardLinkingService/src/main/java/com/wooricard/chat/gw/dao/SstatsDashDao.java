package com.wooricard.chat.gw.dao;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SstatsDashDao {
	
	@Autowired MongoTemplate mongo;

	/**
	 * 인입채널별
	 * @param hashMap
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> channelEntry(HashMap<String, Object> hashMap) throws Exception{
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		return result;
	}
	
	/**
	 * 로그인 수단별 
	 * @param hashMap
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> loginMeans(HashMap<String, Object> hashMap) throws Exception{
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		return result;
	}
	
	
	/**
	 * 금일대표질물
	 * @param hashMap
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> reqresentativeQuery(HashMap<String, Object> hashMap) throws Exception{
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		return result;
	}
	
	/**
	 * 금일시나리오대표질
	 * @param hashMap
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> scenarioQuery(HashMap<String, Object> hashMap) throws Exception{
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		return result;
	}
}
