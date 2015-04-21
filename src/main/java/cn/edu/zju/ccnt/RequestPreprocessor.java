package cn.edu.zju.ccnt;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.config.i18n.CoreMessages;
import org.mule.config.i18n.Message;
import org.mule.transformer.AbstractMessageTransformer;

import edu.emory.mathcs.backport.java.util.LinkedList;


public class RequestPreprocessor extends AbstractMessageTransformer {
	private static final Logger LOGGER = Logger.getLogger(RequestPreprocessor.class);
	private static final Pattern P_WEATHER = Pattern.compile("/weather/q\\?.*");
	private static final Pattern P_FLIGHT = Pattern.compile("/flight/q\\?.*");
	private static final Pattern P_TRAIN = Pattern.compile("/train/q\\?.*");
	private static final Pattern P_HOTEL = Pattern.compile("/hotel/q\\?.*");
	private static final Pattern P_RESTAURANT = Pattern.compile("/restaurant/q\\?.*");
	private static final Pattern P_TAXI = Pattern.compile("/taxi/q\\?.*");
	
	private static final List<RequestSpec> REQUEST_SPECS = new ArrayList<RequestSpec>();
	static{
		REQUEST_SPECS.add(new RequestSpec(
				"weather.51wnl.com/weatherinfo/GetMoreWeather", 
				new cn.edu.zju.ccnt.weather._51wnl.RestRequestPramsGeneratorImpl(), 
				new cn.edu.zju.ccnt.weather._51wnl.StandardizerImpl()));
		
		REQUEST_SPECS.add(new RequestSpec(
				"api.rocliu.net/api/weatherweek", 
				new cn.edu.zju.ccnt.weather.okapi.RestRequestPramsGeneratorImpl(), 
				new cn.edu.zju.ccnt.weather.okapi.StandardizerImpl()));
		
		REQUEST_SPECS.add(new RequestSpec(
				"webservice.webxml.com.cn/WebServices/WeatherWebService.asmx/getWeatherbyCityName", 
				new cn.edu.zju.ccnt.weather.weatherws.RestRequestPramsGeneratorImpl(), 
				new cn.edu.zju.ccnt.weather.weatherws.StandardizerImpl()));
	}

	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding)
			throws TransformerException {
		String reqPath = (String)message.getInboundProperty("http.request");
		message.setInvocationProperty("systime", System.currentTimeMillis());
		message.setInvocationProperty("requstParams", message.getInboundProperty("http.query.params"));
		
		if(P_WEATHER.matcher(reqPath).matches()){
			message.setInvocationProperty("requestSpecs", new LinkedList(REQUEST_SPECS));
			message.setInvocationProperty("mongoCollectionName", "weatherToday");
			message.setInvocationProperty("timeoutMillis", 36000000L);
		} else {
			LOGGER.error("request path not found.");
			Message msg = CoreMessages.transformFailedFrom(ResultStandardizer.class);
			throw new TransformerException(msg);
		}
		
		return message;
	}
	
}
