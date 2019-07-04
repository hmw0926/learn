package com.learn.hmw.enums;

import java.util.List;
import java.util.Map;

import com.learn.hmw.enums.serivce.QueryForEnumsService;
import com.learn.hmw.enums.serivce.impl.QueryForEnumsServiceImpl;

public class Test {
	
	public static void main(String[] args){
		
		QueryForEnumsService service = new QueryForEnumsServiceImpl();
		
		List<Map<String, Object>> list = null;
		try {
			list = service.queryForEnums("com.learn.hmw.enums.bean.ColorEnums");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for (Map<String, Object> map : list) {
			System.out.println(map.toString());
		}
		
	}
}
