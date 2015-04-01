package cn.edu.zju.ccnt.weather.weatherws;

import cn.edu.zju.ccnt.ResultStandardizer;
import cn.edu.zju.ccnt.weather.WeatherResult;

public class StandardizerImpl extends ResultStandardizer{

	@Override
	protected Object standardize(Object obj) {
		WeatherResult ret = new WeatherResult();
		String[] ss = ((GetWeatherbyCityNameResponse)obj).getGetWeatherbyCityNameResult();
		String[] temps = ss[5].split("/");
		
		ret.setCity(ss[1]);
		ret.setDate(ss[4].split(" ")[0]);
		ret.setTempMin(temps[0]);
		ret.setTempMax(temps[1]);
		ret.setWeatherInfo(ss[6].split(" ")[1]);
		
		return ret;
	}

}
