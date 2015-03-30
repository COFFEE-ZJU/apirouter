package cn.edu.zju.ccnt.result._51wnl;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
public class WeatherRes {
	private WeatherInfo weatherinfo;

	public WeatherInfo getWeatherinfo() {
		return weatherinfo;
	}

	public void setWeatherinfo(WeatherInfo weatherinfo) {
		this.weatherinfo = weatherinfo;
	}

}
