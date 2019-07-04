package com.learn.hmw.enums.bean;

public enum ColorEnums {
	
	RED("0","red"),
	GREEN("1","green"),
	BLUE("2","blue");
	
	private String code;
	private String des;
	
	ColorEnums(String code, String des) {
		this.code = code;
		this.des = des;
	}
	
	public static ColorEnums getByCode(String code){
		
		if(code == null){
			return null;
		}
		
		for (ColorEnums colorEnums : values()) {
			if(colorEnums.getCode().equals(code)){
				return colorEnums;
			}
		}
		return null;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}
	
}
