package cn.edu.zju.ccnt.phone.baidu;

import java.util.Map;

import cn.edu.zju.ccnt.RestRequestPramsGenerator;

public class RestRequestPramsGeneratorImpl extends RestRequestPramsGenerator {

	@Override
	public String generateParamString(Map<String, String> params)
			throws Exception {
		String tel = params.get("tel");
		if(tel == null) throw new Exception("missing param tel");
		return "?tel="+tel;
	}

}
