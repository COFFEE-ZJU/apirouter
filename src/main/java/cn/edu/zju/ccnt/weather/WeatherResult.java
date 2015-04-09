package cn.edu.zju.ccnt.weather;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import cn.edu.zju.ccnt.ApiResult;

@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResult extends ApiResult {
	private String city;
	private String date;
	private String tempMin;
	private String tempMax;
	private String weatherInfo;
	
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
}
