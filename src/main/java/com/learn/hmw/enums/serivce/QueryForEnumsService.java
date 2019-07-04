package com.learn.hmw.enums.serivce;

import java.util.List;
import java.util.Map;

public interface QueryForEnumsService {
	
	/**
	 * 
	 * 
	 * @param clazzName 权举类路径
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> queryForEnums(String clazzName) throws Exception;
}
