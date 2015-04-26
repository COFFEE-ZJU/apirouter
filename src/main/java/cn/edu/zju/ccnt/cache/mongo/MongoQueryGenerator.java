package cn.edu.zju.ccnt.cache.mongo;

import org.apache.log4j.Logger;
import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;

import cn.edu.zju.ccnt.ApiCatagorySpec;
import cn.edu.zju.ccnt.ApiResult;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.QueryOperators;

public class MongoQueryGenerator extends AbstractMessageTransformer {
	private static final Logger LOGGER = Logger.getLogger(MongoQueryGenerator.class);
	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding)
			throws TransformerException {
		try{
			DBObject query = new BasicDBObject();
			@SuppressWarnings("unchecked")
			ApiCatagorySpec<? extends ApiResult> apiCatagorySpec = (ApiCatagorySpec<? extends ApiResult>)message.getInvocationProperty("apiCatagorySpec");
			query.put("_id", apiCatagorySpec.generate_idFromRequestMap());
			
			if(apiCatagorySpec.hasTimeout()){
				long timeout = System.currentTimeMillis() - apiCatagorySpec.getTimeoutMillis();
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
