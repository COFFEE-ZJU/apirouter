package cn.edu.zju.ccnt.weather;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import cn.edu.zju.ccnt.ApiCatagorySpec;
import cn.edu.zju.ccnt.ParamsValidator;
import cn.edu.zju.ccnt.ParamsValidatorFactory;
import cn.edu.zju.ccnt.RequestSpec;
import cn.edu.zju.ccnt.RequestSpec.HttpMethodType;

public class ApiCatagorySpecImpl extends ApiCatagorySpec<WeatherResult>{
	private static final List<RequestSpec<WeatherResult>> REQUEST_SPECS = 
			new ArrayList<RequestSpec<WeatherResult>>();
	private static final ParamsValidator PARAMS_VALIDATOR = 
			ParamsValidatorFactory.generateParamsValidatorByFields(new String[]{"city"}, null, false);
	
	static{
		REQUEST_SPECS.add(new RequestSpec<WeatherResult>(
				"weather.51wnl.com/weatherinfo/GetMoreWeather", 
				new cn.edu.zju.ccnt.weather._51wnl.RestRequestPramsGeneratorImpl(), 
				new cn.edu.zju.ccnt.weather._51wnl.StandardizerImpl(), HttpMethodType.GET));
		
		REQUEST_SPECS.add(new RequestSpec<WeatherResult>(
				"api.rocliu.net/api/weatherweek", 
				new cn.edu.zju.ccnt.weather.okapi.RestRequestPramsGeneratorImpl(), 
				new cn.edu.zju.ccnt.weather.okapi.StandardizerImpl(), HttpMethodType.GET));
		
		REQUEST_SPECS.add(new RequestSpec<WeatherResult>(
				"webservice.webxml.com.cn/WebServices/WeatherWebService.asmx/getWeatherbyCityName", 
				new cn.edu.zju.ccnt.weather.weatherws.RestRequestPramsGeneratorImpl(), 
				new cn.edu.zju.ccnt.weather.weatherws.StandardizerImpl(), HttpMethodType.GET));
	}
	
	public ApiCatagorySpecImpl() {
		super("/weather", true, "weatherToday", true, TimeUnit.HOURS.toMillis(1),
				PARAMS_VALIDATOR, REQUEST_SPECS);
	}

	@Override
	public String generate_idFromRequestMap() {
		Map<String, String> requestMap = getCurrentRequestParams();
		return requestMap.get("city");
	}

}
