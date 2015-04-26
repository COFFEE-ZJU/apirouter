package cn.edu.zju.ccnt.express.w56114;

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
		return "?qsname="+type+"&dh="+trackingNO+"&act=2";
	}

}
