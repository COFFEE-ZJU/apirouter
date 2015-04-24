package cn.edu.zju.ccnt.phone.paipai;

import java.util.Map;

import cn.edu.zju.ccnt.RestRequestPramsGenerator;

public class RestRequestPramsGeneratorImpl extends RestRequestPramsGenerator {

	@Override
	public String generateParamString(Map<String, String> params)
			throws Exception {
		String tel = params.get("tel");
		if(tel == null) throw new Exception("missing param tel");
		return "?mobile="+tel+"&amount=10000&callname=getPhoneNumInfoExtCallback";
	}

}
