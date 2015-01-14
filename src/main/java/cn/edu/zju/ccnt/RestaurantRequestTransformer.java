package cn.edu.zju.ccnt;

import java.util.Map;
import org.apache.log4j.Logger;

public class RestaurantRequestTransformer extends RequestTransformer {
	Logger logger = Logger.getLogger(RestaurantRequestTransformer.class);
	
	@Override
	protected String generateReqHost() {
		return "api.map.baidu.com";
	}

	@Override
	protected String generateReqPath() {
		Map<String, String> query = getInboundQuery();
		
		// query keySets: location, keyWord, output, ak
		query.put("output", "xml");
		query.put("ak", "SGwLRBc2CO4UNwE8GGHOLFN3");
		return "/telematics/v3/local?" + queryToString(query);
	}

}
