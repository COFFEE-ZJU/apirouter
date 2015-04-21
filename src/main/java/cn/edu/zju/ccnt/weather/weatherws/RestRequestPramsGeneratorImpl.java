package cn.edu.zju.ccnt.weather.weatherws;

import java.util.Map;

import cn.edu.zju.ccnt.RestRequestPramsGenerator;

public class RestRequestPramsGeneratorImpl extends RestRequestPramsGenerator {

	@Override
	public String generateParamString(Map<String, String> params) throws Exception {
		String city = params.get("city");
		if(city == null) throw new Exception("missing param city");
		
		return "city="+city+"&theUserID=ecf61c646e1946ac986c71ec3b99ae5e";
	}

}
