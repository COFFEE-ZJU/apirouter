package cn.edu.zju.ccnt.phone.paipai;

import java.util.HashMap;
import java.util.Map;

import cn.edu.zju.ccnt.ApiResult;
import cn.edu.zju.ccnt.ResultStandardizer;
import cn.edu.zju.ccnt.phone.PhoneResult;

public class StandardizerImpl extends ResultStandardizer<PhoneResult> {

	@Override
	public PhoneResult standardize(Object input, Map requestParams)
			throws Exception {
		String json;
		if(input instanceof String){
			json = (String)input;
		} else {
			throw new ClassCastException("failed to cast "+input.getClass().toString());
		}
		json=json.substring(json.indexOf("{")+1,json.indexOf("}")); 
		json=json.replace("'", "");
		Map<String,String>map=new HashMap<String,String>();
		String[] strs=json.split(",");
		for(String s:strs){
			String[]ss=s.split(":");
			map.put(ss[0], ss[1]);
		}
		PhoneResult ret = new PhoneResult();
		 
		ret.setTel(map.get("mobile"));
		ret.setProvince(map.get("province"));
		ret.setCarrier(map.get("isp"));
		
		
		ret.set_id(ret.getTel());
		return ret;
	}

}
