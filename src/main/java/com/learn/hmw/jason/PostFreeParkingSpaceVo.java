package com.learn.hmw.jason;

import java.util.List;

public class PostFreeParkingSpaceVo {

    private String parkCode;

    private Integer freeSpaceNum;

    private Integer totalSpaceNum;
    
    private List<PostFreeParkingSpaceAreaInfo> areaInfo;

	public String getParkCode() {
		return parkCode;
	}

	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}

	public Integer getFreeSpaceNum() {
		return freeSpaceNum;
	}

	public void setFreeSpaceNum(Integer freeSpaceNum) {
		this.freeSpaceNum = freeSpaceNum;
	}

	public Integer getTotalSpaceNum() {
		return totalSpaceNum;
	}

	public void setTotalSpaceNum(Integer totalSpaceNum) {
		this.totalSpaceNum = totalSpaceNum;
	}

	public List<PostFreeParkingSpaceAreaInfo> getAreaInfo() {
		return areaInfo;
	}

	public void setAreaInfo(List<PostFreeParkingSpaceAreaInfo> areaInfo) {
		this.areaInfo = areaInfo;
	}
	
}

