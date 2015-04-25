package cn.edu.zju.ccnt.train.trainno.tieyou;

import java.util.Map;

import cn.edu.zju.ccnt.RestRequestPramsGenerator;

public class RestRequestPramsGeneratorImpl extends RestRequestPramsGenerator {
//	private static final Logger LOGGER = Logger.getLogger(RestRequestPramsGeneratorImpl.class);
	private static final String PARAMS_FORMAT = "?param=dataApi/checi.html&date=%s&checi=%s";
	@Override
	public String generateParamString(Map<String, String> params)
			throws Exception {
		return String.format(PARAMS_FORMAT, params.get("date"), params.get("trainno"));
	}

}
