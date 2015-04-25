package cn.edu.zju.ccnt.cache.mongo;

import org.apache.log4j.Logger;
import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.QueryOperators;

public class MongoQueryGenerator extends AbstractMessageTransformer {
	private static final Logger LOGGER = Logger.getLogger(MongoQueryGenerator.class);
	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding)
			throws TransformerException {
		try{
//			@SuppressWarnings("unchecked")
//			Map<String, String> params = (Map<String, String>)message.getInvocationProperty("requestParams");
			
			DBObject query = new BasicDBObject();
//			for(Entry<String, String> entry : params.entrySet()){
//				query.put(entry.getKey(), entry.getValue());
//			}
			query.put("_id", (String)message.getInvocationProperty("_id"));
			
			if((boolean)message.getInvocationProperty("hasTimeout")){
				long timeoutMillis = (long)message.getInvocationProperty("timeoutMillis");
				long timeout = System.currentTimeMillis() - timeoutMillis;
				DBObject cmp = new BasicDBObject();
				cmp.put(QueryOperators.GTE, timeout);
				query.put("timestamp", cmp);
			}
			
			message.setPayload(query);
			
			return message;
		}catch (Exception e){
			LOGGER.error(e);
			throw e;
		}
	}
}
