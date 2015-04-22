package cn.edu.zju.ccnt.weather.weatherws;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.mule.api.transformer.TransformerException;
import org.mule.module.json.transformers.XmlToJson;

import cn.edu.zju.ccnt.ResultStandardizer;
import cn.edu.zju.ccnt.weather.WeatherResult;

public class StandardizerImpl extends ResultStandardizer<WeatherResult>{
	private static final XmlToJson XML_TO_JSON = new XmlToJson();
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	@SuppressWarnings("unchecked")
	@Override
	public WeatherResult standardize(Object input, Map<String, String>requestParams) 
			throws TransformerException, JsonParseException, JsonMappingException, IOException {
		
//		String json = (String)XML_TO_JSON.transform(ARRAY_TO_HEX_STRING.transform(input));
		String json = (String)XML_TO_JSON.transform(input);
		Map<String, Object> resData = MAPPER.readValue(json, Map.class);
		Logger logger = Logger.getLogger(StandardizerImpl.class);
		logger.info(resData);
		WeatherResult ret = new WeatherResult();
		resData = (Map<String, Object>)resData.get("ArrayOfString");
		
		List<String> list = (List<String>)resData.get("string");
		String[] ss = {};
		ss = list.toArray(ss);
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
