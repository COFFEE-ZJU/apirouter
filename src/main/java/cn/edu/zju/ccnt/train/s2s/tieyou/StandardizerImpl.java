package cn.edu.zju.ccnt.train.s2s.tieyou;

import java.io.IOException;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import cn.edu.zju.ccnt.ResultStandardizer;
import cn.edu.zju.ccnt.train.Station2StationResult;

public class StandardizerImpl extends ResultStandardizer<Station2StationResult>{
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	@SuppressWarnings("unchecked")
	@Override
	public Station2StationResult standardize(Object input, Map<String, String>requestParams) 
			throws JsonParseException, JsonMappingException, IOException {
		String json;
		if(input instanceof String){
			json = (String)input;
		} else {
			throw new ClassCastException("failed to cast "+input.getClass().toString());
		}
		
		Map<String, Object> resData = MAPPER.readValue(json, Map.class);
		
		Station2StationResult ret = new Station2StationResult();
		
		ret.setfromCity("testFrom");
		ret.settoCity("testTo");
		
		ret.set_id("testId");
		return ret;
	}

}
