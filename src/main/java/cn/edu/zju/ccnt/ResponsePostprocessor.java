package cn.edu.zju.ccnt;

import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class ResponsePostprocessor extends AbstractMessageTransformer{
	private static final Logger LOGGER = Logger.getLogger(ResponsePostprocessor.class);
	private static final ObjectMapper MAPPER = new ObjectMapper();

	@SuppressWarnings("unchecked")
	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding)
			throws TransformerException {
		RequestSpec<? extends ApiResult> spec = (RequestSpec<? extends ApiResult>)message.getInvocationProperty("requestSpec");
		try {
			ApiResult result = spec.getResultStandardizer().standardize(
					message.getPayload(), (Map<String, String>)message.getInvocationProperty("requestParams"));
			Map<String, Object> map = (Map<String, Object>)MAPPER.convertValue(result, Map.class);
			boolean needsCache = (boolean)message.getInvocationProperty("needsCache");
			
			if(needsCache){	//need to save the result into mongoDB, so generate the DBObject
				DBObject dbObj = new BasicDBObject(map);
				dbObj.put("_id", (String)message.getInvocationProperty("_id"));
				dbObj.put("timestamp", System.currentTimeMillis());
				message.setPayload(dbObj);
			}
//			map.remove("_id");
//			map.remove("partialObject");
//			map.remove("timestamp");
			String json = MAPPER.writeValueAsString(map);
			
			if(needsCache)	//need to save the result into mongoDB, so set the repsonse json string to var:responseString
				message.setInvocationProperty("responseString", json);
			else			//no need, just generate the response json string
				message.setPayload(json);
			
			return message;
		} catch (Exception e) {
			LOGGER.error(e);
			throw new TransformerException(this, e);
		}
	}
}
