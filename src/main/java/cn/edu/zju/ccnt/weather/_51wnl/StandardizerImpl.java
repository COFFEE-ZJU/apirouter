package cn.edu.zju.ccnt.weather._51wnl;

import cn.edu.zju.ccnt.ApiResult;
import cn.edu.zju.ccnt.ResultStandardizer;
import cn.edu.zju.ccnt.weather.WeatherResult;

public class StandardizerImpl extends ResultStandardizer{

	@Override
	protected ApiResult standardize(Object obj) {
		WeatherResult ret = new WeatherResult();
		WeatherInfo info = ((WeatherRes)obj).getWeatherinfo();
		
		String[] temps1 = info.getTemp1().split("~");
		String[] temps2 = info.getTemp2().split("~");
		String[] temps3 = info.getTemp3().split("~");
		
		ret.setcity(info.getCity());
		ret.setdate(info.getDate_y());
		ret.settempMin(temps1[0]);
		ret.settempMax(temps1[1]);
		ret.setweatherInfo(info.getWeather1());
		ret.settempMin2(temps2[0]);
		ret.settempMax2(temps2[1]);
		ret.setweatherInfo2(info.getWeather2());
		ret.settempMin3(temps3[0]);
		ret.settempMax3(temps3[1]);
		ret.setweatherInfo3(info.getWeather3());
		
		ret.set_id(info.getCity());
		return ret;
	}

}
