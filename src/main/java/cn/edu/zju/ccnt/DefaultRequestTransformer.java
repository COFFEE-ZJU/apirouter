package cn.edu.zju.ccnt;

import org.apache.log4j.Logger;
import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;

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
