package cn.edu.zju.ccnt.train.s2s.tieyou;

import java.util.Map;

import org.apache.log4j.Logger;
import cn.edu.zju.ccnt.RestRequestPramsGenerator;

public class RestRequestPramsGeneratorImpl extends RestRequestPramsGenerator {
	private static final Logger LOGGER = Logger.getLogger(RestRequestPramsGeneratorImpl.class);

	@Override
	public String generateParamString(Map<String, String> params)
			throws Exception {
		return "?param=dataApi/zhanzhanYuding.html&"+mapToParamString(params);
	}

}
