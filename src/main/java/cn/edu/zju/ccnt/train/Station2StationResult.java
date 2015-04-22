package cn.edu.zju.ccnt.train;

import cn.edu.zju.ccnt.ApiResult;

public class Station2StationResult extends ApiResult{
	private String fromCity;
	private String toCity;
	public String getfromCity() {
		return fromCity;
	}
	public void setfromCity(String fromCity) {
		this.fromCity = fromCity;
	}
	public String gettoCity() {
		return toCity;
	}
	public void settoCity(String toCity) {
		this.toCity = toCity;
	}
	
}
