package cn.edu.zju.ccnt;

import java.util.Map;

public class FlightRequestTransformer extends RequestTransformer {

	@Override
	protected String generateReqHost() {
		return "api.open.baidu.com";
	}

	@Override
	protected String generateReqPath() {
		Map<String, String> query = getInboundQuery();
		query.put("appid", "4047");
		return "/pae/channel/data/asyncqury?" + queryToString(query);
	}

}
