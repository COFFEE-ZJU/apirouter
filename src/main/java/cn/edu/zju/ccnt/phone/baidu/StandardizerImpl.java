package cn.edu.zju.ccnt.phone.baidu;

import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import cn.edu.zju.ccnt.ResultStandardizer;
import cn.edu.zju.ccnt.phone.PhoneResult;

public class StandardizerImpl extends ResultStandardizer<PhoneResult> {
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	@Override
	public PhoneResult standardize(Object input, Map requestParams)
			throws Exception {
		String json;
		if(input instanceof String){
			json = (String)input;
		} else {
			throw new ClassCastException("failed to cast "+input.getClass().toString());
		}
		
		Map<String, Object> resData = MAPPER.readValue(json, Map.class);
		resData = (Map<String, Object>)resData.get("retData");
		PhoneResult ret = new PhoneResult();
		
		ret.setTel((String)resData.get("telString"));
		ret.setProvince((String)resData.get("province"));
		ret.setCarrier((String)resData.get("carrier"));
		
		
		ret.set_id(ret.getTel());
		return ret;
	}

}
