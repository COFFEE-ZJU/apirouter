package cn.edu.zju.ccnt.weather.okapi;

import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import cn.edu.zju.ccnt.ApiResult;
import cn.edu.zju.ccnt.ResultStandardizer;
import cn.edu.zju.ccnt.weather.WeatherResult;

public class StandardizerImpl extends ResultStandardizer {
	private static final ObjectMapper MAPPER = new ObjectMapper();
	private static final String[] WEATHER_INFOS = {"晴","多云","阴","阵雨","雷阵雨",
		"雷阵雨伴有冰雹","雨夹雪","小雨","中雨","大雨","暴雨","大暴雨","特大暴雨","阵雪",
		"小雪","中雪","大雪","暴雪","雾","冻雨","沙尘暴","小到中雨","中到大雨","大到暴雨",
		"暴雨到大暴雨","大暴雨到特大暴雨","小到中雪","中到大雪","大到暴雪","浮尘","扬沙","强沙尘暴"};
	
	@SuppressWarnings("unchecked")
	@Override
	public ApiResult standardize(Object input) throws Exception {
		String json;
		if(input instanceof String){
			json = (String)input;
		} else {
			throw new ClassCastException("failed to cast "+input.getClass().toString());
		}
		
		Map<String, Object> resData = MAPPER.readValue(json, Map.class);
		
		WeatherResult ret = new WeatherResult();
		ret.setcity((String)getMapObjByPath(resData,new String[]{"c","c3"}));
		ret.set_id(ret.getcity());
		ret.setdate(((String)resData.get("t")).substring(0, 8));
		ret.settempMax((String)getMapObjByPath(resData,new String[]{"w1","fc"}));
		ret.settempMin((String)getMapObjByPath(resData,new String[]{"w1","fd"}));
		String info = (String)getMapObjByPath(resData,new String[]{"w1","fa"});
		info = (info == null || info.equals("")) ? (String)getMapObjByPath(resData,new String[]{"w1","fb"}) : info;
		ret.setweatherInfo(infoCode2String(info));
		
		ret.settempMax2((String)getMapObjByPath(resData,new String[]{"w2","fc"}));
		ret.settempMin2((String)getMapObjByPath(resData,new String[]{"w2","fd"}));
		ret.setweatherInfo2(infoCode2String((String)getMapObjByPath(resData,new String[]{"w2","fa"})));
		
		ret.settempMax3((String)getMapObjByPath(resData,new String[]{"w3","fc"}));
		ret.settempMin3((String)getMapObjByPath(resData,new String[]{"w3","fd"}));
		ret.setweatherInfo3(infoCode2String((String)getMapObjByPath(resData,new String[]{"w3","fa"})));
		
		return ret;
	}
	
	private String infoCode2String(String code){
		try{
			int codeInt = Integer.valueOf(code);
			if(codeInt >= 0 && codeInt < WEATHER_INFOS.length)
				return WEATHER_INFOS[codeInt];
			else if (codeInt == 53) return "霾";
			else return code;
		} catch (Exception e){
			return "";
		}
	}
}
