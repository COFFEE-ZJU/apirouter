package cn.edu.zju.ccnt;

import org.apache.log4j.Logger;
import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;

public class RequestTransformer extends AbstractMessageTransformer {
	Logger logger = Logger.getLogger(RequestTransformer.class);
	
	public Object transformMessage(MuleMessage message, String outputEncoding)
			throws TransformerException {
		String reqPath = (String)message.getInboundProperty("http.request");
		String[] pathSplit = reqPath.split("/");
		if(pathSplit.length == 0 || pathSplit.length == 1){	// root path "/"
			message.setInvocationProperty("reqHost", "www.juhe.cn");
			message.setInvocationProperty("reqPath", "/");
		} else {
			message.setInvocationProperty("reqHost", "www.cc98.org");
			message.setInvocationProperty("reqPath", reqPath);
		}
		
//		message.setInvocationProperty("invo", "testInvoVal");
		logger.info("payload: " + message.getPayload());
		return message;
	}

}
