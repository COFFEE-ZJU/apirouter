package cn.edu.zju.ccnt.result;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class WeatherObj {
	private String cityName;
	private String date;
	private String temperatureMin;
	private String temperatureMax;
	
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTemperatureMin() {
		return temperatureMin;
	}
	public void setTemperatureMin(String temperatureMin) {
		this.temperatureMin = temperatureMin;
	}
	public String getTemperatureMax() {
		return temperatureMax;
	}
	public void setTemperatureMax(String temperatureMax) {
		this.temperatureMax = temperatureMax;
	}
	
	
}
