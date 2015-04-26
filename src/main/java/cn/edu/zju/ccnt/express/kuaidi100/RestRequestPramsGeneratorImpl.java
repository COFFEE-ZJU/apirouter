package cn.edu.zju.ccnt.express.kuaidi100;

import java.util.Map;

import cn.edu.zju.ccnt.RestRequestPramsGenerator;

public class RestRequestPramsGeneratorImpl extends RestRequestPramsGenerator {

	@Override
	public String generateParamString(Map<String, String> params)
			throws Exception {
		String type = params.get("type");
		if(type == null) throw new Exception("missing param type");
		String trackingNO=params.get("trackingNO");
		if(trackingNO == null) throw new Exception("missing param trackingNO");
		return "?type="+type+"&postid="+trackingNO;
	}

}
