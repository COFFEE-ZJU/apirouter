package cn.edu.zju.ccnt;

import org.mule.api.MuleMessage;
import org.mule.transformer.AbstractMessageTransformer;

public abstract class ResultStandardizer  extends AbstractMessageTransformer{
	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding){
		Object obj = message.getPayload();
		ApiResult result = standardize(obj);
		result.settimestamp(System.currentTimeMillis());
		message.setPayload(result);
		return message;
	}
	
	abstract protected ApiResult standardize(Object obj);
}
