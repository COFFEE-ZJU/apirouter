package cn.edu.zju.ccnt.ip.baidu;

import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import cn.edu.zju.ccnt.ApiResult;
import cn.edu.zju.ccnt.ResultStandardizer;
import cn.edu.zju.ccnt.ip.IPResult;

public class StandardizerImpl extends ResultStandardizer<IPResult> {
	private static final ObjectMapper MAPPER = new ObjectMapper();
	@Override
	public IPResult standardize(Object input, Map requestParams)
			throws Exception {
		String json;
		if(input instanceof String){
			json = (String)input;
		} else {
			throw new ClassCastException("failed to cast "+input.getClass().toString());
		}
		
		Map<String, Object> resData = MAPPER.readValue(json, Map.class);
		resData = (Map<String, Object>)resData.get("retData");
		
		IPResult ret = new IPResult();
		ret.setCity((String)resData.get("city"));
		ret.setCountry((String)resData.get("country"));
		ret.setISP((String)resData.get("carrier"));
		ret.setProvince((String)resData.get("province"));
		ret.setIp((String)resData.get("ip"));
		
		ret.set_id(ret.getIp());
		return ret;
	}

}
