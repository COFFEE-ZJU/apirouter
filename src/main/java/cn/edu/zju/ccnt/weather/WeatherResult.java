package cn.edu.zju.ccnt.weather;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import cn.edu.zju.ccnt.ApiResult;

@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true,value = {"_id","partialObject","timestamp"})
//@JsonIgnoreProperties(ignoreUnknown = true)
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
	
	public String getcity() {
		return city;
	}
	public void setcity(String city) {
		this.city = city;
	}
	public String getdate() {
		return date;
	}
	public void setdate(String date) {
		this.date = date;
	}
	public String gettempMin() {
		return tempMin;
	}
	public void settempMin(String tempMin) {
		this.tempMin = tempMin;
	}
	public String gettempMax() {
		return tempMax;
	}
	public void settempMax(String tempMax) {
		this.tempMax = tempMax;
	}
	public String getweatherInfo() {
		return weatherInfo;
	}
	public void setweatherInfo(String weatherInfo) {
		this.weatherInfo = weatherInfo;
	}
	public String gettempMin2() {
		return tempMin2;
	}
	public void settempMin2(String tempMin2) {
		this.tempMin2 = tempMin2;
	}
	public String gettempMax2() {
		return tempMax2;
	}
	public void settempMax2(String tempMax2) {
		this.tempMax2 = tempMax2;
	}
	public String getweatherInfo2() {
		return weatherInfo2;
	}
	public void setweatherInfo2(String weatherInfo2) {
		this.weatherInfo2 = weatherInfo2;
	}
	public String gettempMin3() {
		return tempMin3;
	}
	public void settempMin3(String tempMin3) {
		this.tempMin3 = tempMin3;
	}
	public String gettempMax3() {
		return tempMax3;
	}
	public void settempMax3(String tempMax3) {
		this.tempMax3 = tempMax3;
	}
	public String getweatherInfo3() {
		return weatherInfo3;
	}
	public void setweatherInfo3(String weatherInfo3) {
		this.weatherInfo3 = weatherInfo3;
	}
}
