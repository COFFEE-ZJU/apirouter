package cn.edu.zju.ccnt.weather;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import cn.edu.zju.ccnt.ApiResult;

@JsonAutoDetect
//@JsonIgnoreProperties(ignoreUnknown = true,value = {"_id","partialObject","timestamp"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResult extends ApiResult {
	
	private String city;
	private String date;
	private String tempMin;
	private String tempMax;
	private String weatherInfo;
	private String tempMin2;
	private String tempMax2;
	private String weatherInfo2;
	private String tempMin3;
	private String tempMax3;
	private String weatherInfo3;
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTempMin() {
		return tempMin;
	}
	public void setTempMin(String tempMin) {
		this.tempMin = tempMin;
	}
	public String getTempMax() {
		return tempMax;
	}
	public void setTempMax(String tempMax) {
		this.tempMax = tempMax;
	}
	public String getWeatherInfo() {
		return weatherInfo;
	}
	public void setWeatherInfo(String weatherInfo) {
		this.weatherInfo = weatherInfo;
	}
	public String getTempMin2() {
		return tempMin2;
	}
	public void setTempMin2(String tempMin2) {
		this.tempMin2 = tempMin2;
	}
	public String getTempMax2() {
		return tempMax2;
	}
	public void setTempMax2(String tempMax2) {
		this.tempMax2 = tempMax2;
	}
	public String getWeatherInfo2() {
		return weatherInfo2;
	}
	public void setWeatherInfo2(String weatherInfo2) {
		this.weatherInfo2 = weatherInfo2;
	}
	public String getTempMin3() {
		return tempMin3;
	}
	public void setTempMin3(String tempMin3) {
		this.tempMin3 = tempMin3;
	}
	public String getTempMax3() {
		return tempMax3;
	}
	public void setTempMax3(String tempMax3) {
		this.tempMax3 = tempMax3;
	}
	public String getWeatherInfo3() {
		return weatherInfo3;
	}
	public void setWeatherInfo3(String weatherInfo3) {
		this.weatherInfo3 = weatherInfo3;
	}
}
