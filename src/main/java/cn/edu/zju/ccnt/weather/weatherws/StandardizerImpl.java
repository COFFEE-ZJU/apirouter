package cn.edu.zju.ccnt.weather.weatherws;

import cn.edu.zju.ccnt.ApiResult;
import cn.edu.zju.ccnt.ResultStandardizer;
import cn.edu.zju.ccnt.weather.WeatherResult;

public class StandardizerImpl extends ResultStandardizer{

	@Override
	protected ApiResult standardize(Object obj) {
		WeatherResult ret = new WeatherResult();
		String[] ss = ((GetWeatherbyCityNameResponse)obj).getGetWeatherbyCityNameResult();
		String[] temps = ss[5].split("/");
		
		ret.setcity(ss[1]);
		ret.setdate(ss[4].split(" ")[0]);
		ret.settempMin(temps[0]);
		ret.settempMax(temps[1]);
		ret.setweatherInfo(ss[6].split(" ")[1]);
		
		ret.set_id(ss[1]);
		return ret;
	}

}
