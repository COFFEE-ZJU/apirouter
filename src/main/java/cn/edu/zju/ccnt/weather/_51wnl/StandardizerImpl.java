package cn.edu.zju.ccnt.weather._51wnl;

import java.io.IOException;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import cn.edu.zju.ccnt.ApiResult;
import cn.edu.zju.ccnt.ResultStandardizer;
import cn.edu.zju.ccnt.weather.WeatherResult;

public class StandardizerImpl extends ResultStandardizer{
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	@SuppressWarnings("unchecked")
	@Override
	public ApiResult standardize(Object input) throws JsonParseException, JsonMappingException, IOException {
		String json;
		if(input instanceof String){
			json = (String)input;
		} else {
			throw new ClassCastException("failed to cast "+input.getClass().toString());
		}
		
		Map<String, Object> resData = MAPPER.readValue(json, Map.class);
		resData = (Map<String, Object>)resData.get("weatherinfo");
		
		WeatherResult ret = new WeatherResult();
		
		String[] temps1 = ((String)resData.get("temp1")).split("~");
		String[] temps2 = ((String)resData.get("temp2")).split("~");
		String[] temps3 = ((String)resData.get("temp3")).split("~");
		
		ret.setcity((String)resData.get("city"));
		ret.setdate((String)resData.get("date_y"));
		ret.settempMin(temps1[0]);
		ret.settempMax(temps1[1]);
		ret.setweatherInfo((String)resData.get("weather1"));
		ret.settempMin2(temps2[0]);
		ret.settempMax2(temps2[1]);
		ret.setweatherInfo2((String)resData.get("weather2"));
		ret.settempMin3(temps3[0]);
		ret.settempMax3(temps3[1]);
		ret.setweatherInfo3((String)resData.get("weather3"));
		
		ret.set_id(ret.getcity());
		return ret;
	}

}
