package cn.edu.zju.ccnt.ip.telize;

import java.util.Map;

import cn.edu.zju.ccnt.RestRequestPramsGenerator;

public class RestRequestPramsGeneratorImpl extends RestRequestPramsGenerator {

	@Override
	public String generateParamString(Map<String, String> params)
			throws Exception {
		String ip = params.get("ip");
		if(ip == null) throw new Exception("missing param ip");
		//ֱ�ӷ���ip ������ ip="..."
		return "/"+ip;
	}

}
