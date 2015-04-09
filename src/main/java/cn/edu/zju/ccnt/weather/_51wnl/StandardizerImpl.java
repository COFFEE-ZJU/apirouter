package cn.edu.zju.ccnt.weather._51wnl;

import cn.edu.zju.ccnt.ApiResult;
import cn.edu.zju.ccnt.ResultStandardizer;
import cn.edu.zju.ccnt.weather.WeatherResult;

public class StandardizerImpl extends ResultStandardizer{

	@Override
	protected ApiResult standardize(Object obj) {
		WeatherResult ret = new WeatherResult();
		WeatherInfo info = ((WeatherRes)obj).getWeatherinfo();
		
		String[] temps = info.getTemp1().split("~");
		
		ret.setcity(info.getCity());
		ret.setdate(info.getDate_y());
		ret.settempMin(temps[0]);
		ret.settempMax(temps[1]);
		ret.setweatherInfo(info.getWeather1());
		
		ret.set_id(info.getCity());
		return ret;
	}

}
