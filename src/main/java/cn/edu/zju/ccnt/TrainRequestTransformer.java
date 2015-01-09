package cn.edu.zju.ccnt;

import org.apache.log4j.Logger;
import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;

public class TrainRequestTransformer extends AbstractMessageTransformer {
	Logger logger = Logger.getLogger(TrainRequestTransformer.class);
	
	public Object transformMessage(MuleMessage message, String outputEncoding)
			throws TransformerException {
		logger.info("matched train regex");
		
		String reqPath = (String)message.getInboundProperty("http.request");
		message.setInvocationProperty("reqHost", "apis.juhe.cn");
		message.setInvocationProperty("reqPath", reqPath);
		
		return message;
	}

}
