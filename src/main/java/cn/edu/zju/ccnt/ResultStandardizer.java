package cn.edu.zju.ccnt;

import org.mule.api.MuleMessage;
import org.mule.transformer.AbstractMessageTransformer;

public abstract class ResultStandardizer  extends AbstractMessageTransformer{
	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding){
		Object obj = message.getPayload();
		message.setPayload(standardize(obj));
		return message;
	}
	
	abstract protected Object standardize(Object obj);
}
