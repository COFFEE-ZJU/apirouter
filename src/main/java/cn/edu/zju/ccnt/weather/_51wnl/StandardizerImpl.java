package cn.edu.zju.ccnt.weather._51wnl;

import cn.edu.zju.ccnt.ResultStandardizer;
import cn.edu.zju.ccnt.weather.WeatherResult;

public class StandardizerImpl extends ResultStandardizer{

	@Override
	protected Object standardize(Object obj) {
		WeatherResult ret = new WeatherResult();
		WeatherInfo info = ((WeatherRes)obj).getWeatherinfo();
		
		String[] temps = info.getTemp1().split("~");
		
		ret.setCity(info.getCity());
		ret.setDate(info.getDate_y());
		ret.setTempMin(temps[0]);
		ret.setTempMax(temps[1]);
		ret.setWeatherInfo(info.getWeather1());
		
		return ret;
	}

}
