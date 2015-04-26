package cn.edu.zju.ccnt.ip.telize;

import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import cn.edu.zju.ccnt.ResultStandardizer;
import cn.edu.zju.ccnt.ip.IPResult;

public class StandardizerImpl extends ResultStandardizer<IPResult> {
	private static final ObjectMapper MAPPER = new ObjectMapper();
	@SuppressWarnings("unchecked")
	@Override
	public IPResult standardize(Object input, Map<String, String> requestParams)
			throws Exception {
		String json;
		if(input instanceof String){
			json = (String)input;
		} else {
			throw new ClassCastException("failed to cast "+input.getClass().toString());
		}
		
		Map<String, Object> resData = MAPPER.readValue(json, Map.class);
		
		IPResult ret = new IPResult();
		ret.setCity((String)resData.get("city"));
		ret.setCountry((String)resData.get("country"));
		ret.setISP((String)resData.get("isp"));
		ret.setProvince((String)resData.get("region"));
		ret.setIp((String)resData.get("ip"));
		
		return ret;
	}

}
