package cn.edu.zju.ccnt;

import org.apache.log4j.Logger;

public class DefaultRequestTransformer extends RequestTransformer {
	Logger logger = Logger.getLogger(DefaultRequestTransformer.class);

	@Override
	protected void init(){
		formatError("uncategorized api path");
	}

	@Override
	protected String generateReqHost() {
		return null;
	}

	@Override
	protected String generateReqPath() {
		return null;
	}

}
