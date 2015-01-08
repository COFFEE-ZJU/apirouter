package cn.edu.zju.ccnt;

import org.apache.log4j.Logger;
import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;

public class RequestPasser extends AbstractMessageTransformer{
	private static Logger logger=Logger.getLogger(RequestPasser.class);
	public Object transformMessage(MuleMessage message, String outputEncoding)
			throws TransformerException {
		String content=null;
		try {
			content = message.getPayloadAsString();
			logger.info("orignal content:"+content);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return content;
	}

}
