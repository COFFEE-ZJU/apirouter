package cn.edu.zju.ccnt;

import org.apache.log4j.Logger;
import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;

public class DefaultRequestTransformer extends AbstractMessageTransformer {
	Logger logger = Logger.getLogger(DefaultRequestTransformer.class);
	
	public Object transformMessage(MuleMessage message, String outputEncoding)
			throws TransformerException {
		logger.info("matched default regex");
		return message;
	}

}
