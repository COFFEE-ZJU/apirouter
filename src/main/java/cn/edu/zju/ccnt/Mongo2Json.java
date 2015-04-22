package cn.edu.zju.ccnt;

import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.module.mongo.transformers.DbobjectToJsonTransformer;
import org.mule.transformer.AbstractMessageTransformer;

import com.mongodb.DBObject;

public class Mongo2Json extends AbstractMessageTransformer{
	private static final DbobjectToJsonTransformer D2JT = new DbobjectToJsonTransformer();
	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding)
			throws TransformerException {
		DBObject obj = (DBObject)message.getPayload();
		obj.removeField("_id");
		obj.removeField("partialObject");
		obj.removeField("timestamp");
		message.setPayload(D2JT.transform(obj));
		return message;
	}

}
