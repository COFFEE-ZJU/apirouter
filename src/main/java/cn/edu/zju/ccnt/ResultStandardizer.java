package cn.edu.zju.ccnt;

import java.util.Map;

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
			Object payload = (Object)message.getPayload();
			
			ApiResult result = standardize(payload);
			result.settimestamp(System.currentTimeMillis());
			message.setPayload(result);
			return message;
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e);
			Message msg = CoreMessages.transformFailedFrom(ResultStandardizer.class);
			throw new TransformerException(msg);
		}
	}
	
	@SuppressWarnings("unchecked")
	protected static Object getMapObjByPath(Map<String, Object> map, String[] path) throws NoSuchFieldException{
		if(path == null || path.length == 0 || map == null) return map;
		try {
			Object tempObj = map;
			
			for(String field : path)
				tempObj = ((Map<String, Object>)tempObj).get(field);
			
			return tempObj;
			
		} catch (Exception e) {
			throw new NoSuchFieldException(e.getMessage());
		}
	}
	
	abstract protected ApiResult standardize(Object input) throws Exception;
}
