package cn.edu.zju.ccnt.rules;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;

import cn.edu.zju.ccnt.result._51wnl.WeatherRes;

public class TransformResult extends AbstractMessageTransformer {
	private static final Logger logger = Logger.getLogger(TransformResult.class);
	
	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding)
			throws TransformerException {
		WeatherRes res = (WeatherRes)message.getPayload();
		logger.log(Level.INFO, "test temp1: "+res.getWeatherinfo().getTemp1());
		message.setPayload(res.getWeatherinfo());
		return message;
	}

}
