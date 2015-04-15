package cn.edu.zju.ccnt;

import org.apache.log4j.Logger;
import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.config.i18n.CoreMessages;
import org.mule.config.i18n.Message;
import org.mule.transformer.AbstractMessageTransformer;

public abstract class ResultStandardizer  extends AbstractMessageTransformer{
	private static final Logger LOGGER = Logger.getLogger(ResultStandardizer.class);
	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding)
			throws TransformerException {
		try {
			String json = (String)message.getPayload();
			ApiResult result = standardize(json);
			result.settimestamp(System.currentTimeMillis());
			message.setPayload(result);
			return message;
		} catch (Exception e) {
			LOGGER.error(e);
			Message msg = CoreMessages.transformFailedFrom(ResultStandardizer.class);
			throw new TransformerException(msg);
		}
	}
	
	abstract protected ApiResult standardize(String jsonStr) throws Exception;
}
