package cn.edu.zju.ccnt.weather._51wnl;

import java.io.IOException;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import cn.edu.zju.ccnt.ResultStandardizer;
import cn.edu.zju.ccnt.weather.WeatherResult;

public class StandardizerImpl extends ResultStandardizer<WeatherResult>{
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	@SuppressWarnings("unchecked")
	@Override
	public WeatherResult standardize(Object input, Map<String, String>requestParams) 
			throws JsonParseException, JsonMappingException, IOException {
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
		
		ret.setCity((String)resData.get("city"));
		ret.setDate((String)resData.get("date_y"));
		ret.setTempMin(temps1[0]);
		ret.setTempMax(temps1[1]);
		ret.setWeatherInfo((String)resData.get("weather1"));
		ret.setTempMin2(temps2[0]);
		ret.setTempMax2(temps2[1]);
		ret.setWeatherInfo2((String)resData.get("weather2"));
		ret.setTempMin3(temps3[0]);
		ret.setTempMax3(temps3[1]);
		ret.setWeatherInfo3((String)resData.get("weather3"));
		
		return ret;
	}

}
