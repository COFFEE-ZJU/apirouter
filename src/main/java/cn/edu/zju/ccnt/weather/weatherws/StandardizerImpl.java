package cn.edu.zju.ccnt.weather.weatherws;

import com.thoughtworks.xstream.XStream;

import cn.edu.zju.ccnt.ApiResult;
import cn.edu.zju.ccnt.ResultStandardizer;
import cn.edu.zju.ccnt.weather.WeatherResult;

public class StandardizerImpl extends ResultStandardizer{
	
	@Override
	protected ApiResult standardize(String jsonStr) {
		
		WeatherResult ret = new WeatherResult();
		String[] ss = ((GetWeatherbyCityNameResponse)obj).getGetWeatherbyCityNameResult();
		String[] temps1 = ss[5].split("/");
		String[] temps2 = ss[12].split("/");
		String[] temps3 = ss[17].split("/");
		
		ret.setcity(ss[1]);
		ret.setdate(ss[4].split(" ")[0]);
		ret.settempMin(temps1[0]);
		ret.settempMax(temps1[1]);
		ret.setweatherInfo(ss[6].split(" ")[1]);
		
		ret.settempMin2(temps2[0]);
		ret.settempMax2(temps2[1]);
		ret.setweatherInfo2(ss[13].split(" ")[1]);
		
		ret.settempMin3(temps3[0]);
		ret.settempMax3(temps3[1]);
		ret.setweatherInfo3(ss[18].split(" ")[1]);
		
		ret.set_id(ss[1]);
		return ret;
	}

}
