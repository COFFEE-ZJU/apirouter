package cn.edu.zju.ccnt;

import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;

public class ErrorResultGenerator extends AbstractMessageTransformer {

	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding)
			throws TransformerException {
		message.setPayload(new ErrorResult("500", "failed to invoke APIs."));
		return message;
	}

}
