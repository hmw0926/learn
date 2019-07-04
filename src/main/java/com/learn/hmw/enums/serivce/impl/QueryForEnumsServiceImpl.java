package com.learn.hmw.enums.serivce.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.learn.hmw.enums.serivce.QueryForEnumsService;

public class QueryForEnumsServiceImpl implements QueryForEnumsService{

	public List<Map<String, Object>> queryForEnums(String clazzName) throws Exception{
		
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		Class<?> clz = null;
		try {
			clz = Class.forName(clazzName);
		} catch (ClassNotFoundException e) {
			throw new Exception("ClassNotFound");
		}
		
		if(!clz.isEnum()){
			throw new Exception(clazzName + "is not Enums");
		}
		
		Field[] fields = clz.getDeclaredFields(); //private code, private des
		List<?> list = Arrays.asList(clz.getEnumConstants());  //List.put(RED)...List.put(GREEN)...List.put(BLUE)
		
		Map<String, Object> map = null;
		for (Object enums : list) {
			map = new HashMap<String, Object>();
			map.put("enums", enums.toString());
			for(Field field :fields){
				if(field.getType().toString().equals("class java.lang.Integer") 
						|| field.getType().toString().equals("class java.lang.String")){
					String firstChar = field.getName().substring(0, 1);
					String otherChar = field.getName().substring(1, field.getName().length());
					String methodName = "get" + firstChar.toUpperCase() + otherChar;
					try {
						map.put(field.getName(), clz.getMethod(methodName).invoke(enums));
					} catch (Exception e) {
						System.out.println("field" + field.getName() + "failed to reach method:" + methodName);
						continue;
					}
				}
			}
			resultList.add(map);
		}
		
		return resultList;
	}

}
