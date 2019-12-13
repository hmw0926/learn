package com.learn.hmw.jason;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

public class JsonDemo {

	public static void main(String[] args) {
		PostFreeParkingSpaceVo vo = new PostFreeParkingSpaceVo();
		vo.setParkCode("A区");
		vo.setFreeSpaceNum(100);
		vo.setTotalSpaceNum(200);

		List<PostFreeParkingSpaceAreaInfo> list = new ArrayList<PostFreeParkingSpaceAreaInfo>();
		PostFreeParkingSpaceAreaInfo info1 = new PostFreeParkingSpaceAreaInfo();
		info1.setAreaCode(1);
		info1.setAreaName("A区");
		info1.setAreaFreeSpaceNum(5);

		PostFreeParkingSpaceAreaInfo info2 = new PostFreeParkingSpaceAreaInfo();
		info2.setAreaCode(2);
		info2.setAreaName("B区");
		info2.setAreaFreeSpaceNum(5);

		PostFreeParkingSpaceAreaInfo info3 = new PostFreeParkingSpaceAreaInfo();
		info3.setAreaCode(3);
		info3.setAreaName("C区");
		info3.setAreaFreeSpaceNum(5);

		list.add(info1);
		list.add(info2);
		list.add(info3);
		vo.setAreaInfo(list);

		String jsonStr = objectToString(vo);

		System.out.println(jsonStr);

		Map<String, Class> classMap = new HashMap<String, Class>();
		classMap.put("areaInfo", PostFreeParkingSpaceAreaInfo.class);
		PostFreeParkingSpaceVo resVo = (PostFreeParkingSpaceVo) strToJsonObjectWithList(jsonStr,
				PostFreeParkingSpaceVo.class, classMap);

		System.out.println(resVo);
		List<PostFreeParkingSpaceAreaInfo> areaInfoList = resVo.getAreaInfo();
		for (Object obj : areaInfoList) {
			PostFreeParkingSpaceAreaInfo info = (PostFreeParkingSpaceAreaInfo) obj;
			System.out.println(info);
		}
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 功能描述：String 转 JsonObject
	 */
	private static Object strToJsonObject(String str, Class clz) {
		JSONObject jsonObj = JSONObject.fromObject(str);
		Object obj = JSONObject.toBean(jsonObj, clz);
		return obj;
	}

	/**
	 * 功能描述：String 转 JsonObject，带list
	 */
	private static Object strToJsonObjectWithList(String str, Class clz, Map<String, Class> classMap) {
		JSONObject jsonObj = JSONObject.fromObject(str);
		Object obj = JSONObject.toBean(jsonObj, clz, classMap);
		return obj;
	}

	/**
	 * 功能描述：JsonObject 转 String
	 */
	private static String objectToString(Object object) {
		JSONObject obj = JSONObject.fromObject(object);
		return obj.toString();
	}

}
