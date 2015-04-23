package cn.edu.zju.ccnt;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.config.i18n.CoreMessages;
import org.mule.config.i18n.Message;
import org.mule.transformer.AbstractMessageTransformer;

import cn.edu.zju.ccnt.RequestSpec.HttpMethodType;

import edu.emory.mathcs.backport.java.util.LinkedList;


public class RequestPreprocessor extends AbstractMessageTransformer {
	private static final Logger LOGGER = Logger.getLogger(RequestPreprocessor.class);
//	private static final Pattern P_WEATHER = Pattern.compile("/weather/q\\?.*");
//	private static final Pattern P_FLIGHT = Pattern.compile("/flight/q\\?.*");
//	private static final Pattern P_TRAIN_S2S = Pattern.compile("/train/s2s/q\\?.*");
//	private static final Pattern P_TRAIN_TNO = Pattern.compile("/train/trainno/q\\?.*");
//	private static final Pattern P_HOTEL = Pattern.compile("/hotel/q\\?.*");
//	private static final Pattern P_RESTAURANT = Pattern.compile("/restaurant/q\\?.*");
//	private static final Pattern P_TAXI = Pattern.compile("/taxi/q\\?.*");
	
	private static final String WEATHER_PATH = "/weather";
	private static final String FLIGHT_PATH = "/flight";
	private static final String TRAIN_S2S_PATH = "/train/s2s";
	private static final String TRAIN_TNO_PATH = "/train/trainno";
	private static final String HOTEL_PATH = "/hotel";
	private static final String RESTAURANT_PATH = "/restaurant";
	private static final String TAXI_PATH = "/taxi";
	
	
	
	private static final List<RequestSpec> WEATHER_REQUEST_SPECS = new ArrayList<RequestSpec>();
	private static final List<RequestSpec> TRAIN_S2S_REQUEST_SPECS = new ArrayList<RequestSpec>();
	static{
		WEATHER_REQUEST_SPECS.add(new RequestSpec(
				"weather.51wnl.com/weatherinfo/GetMoreWeather", 
				new cn.edu.zju.ccnt.weather._51wnl.RestRequestPramsGeneratorImpl(), 
				new cn.edu.zju.ccnt.weather._51wnl.StandardizerImpl(), HttpMethodType.GET));
		
		WEATHER_REQUEST_SPECS.add(new RequestSpec(
				"api.rocliu.net/api/weatherweek", 
				new cn.edu.zju.ccnt.weather.okapi.RestRequestPramsGeneratorImpl(), 
				new cn.edu.zju.ccnt.weather.okapi.StandardizerImpl(), HttpMethodType.GET));
		
		WEATHER_REQUEST_SPECS.add(new RequestSpec(
				"webservice.webxml.com.cn/WebServices/WeatherWebService.asmx/getWeatherbyCityName", 
				new cn.edu.zju.ccnt.weather.weatherws.RestRequestPramsGeneratorImpl(), 
				new cn.edu.zju.ccnt.weather.weatherws.StandardizerImpl(), HttpMethodType.GET));
		
		TRAIN_S2S_REQUEST_SPECS.add(new RequestSpec(
				"m.tieyou.com/jy/index.php", 
				new cn.edu.zju.ccnt.train.s2s.tieyou.RestRequestPramsGeneratorImpl(), 
				new cn.edu.zju.ccnt.train.s2s.tieyou.StandardizerImpl(), HttpMethodType.GET));
	}

	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding)
			throws TransformerException {
//		String reqPath = (String)message.getInboundProperty("http.request");
		String reqPath = (String)message.getInboundProperty("http.request.path");
		message.setInvocationProperty("systime", System.currentTimeMillis());
		message.setInvocationProperty("requstParams", message.getInboundProperty("http.query.params"));
		
		switch (reqPath) {
		case WEATHER_PATH:
			message.setInvocationProperty("requestSpecs", new LinkedList(WEATHER_REQUEST_SPECS));
			
			message.setInvocationProperty("needsCache", true);
			message.setInvocationProperty("mongoCollectionName", "weatherToday");
			message.setInvocationProperty("timeoutMillis", TimeUnit.HOURS.toMillis(1));
			break;
		case TRAIN_S2S_PATH:
			message.setInvocationProperty("requestSpecs", new LinkedList(TRAIN_S2S_REQUEST_SPECS));
			
			message.setInvocationProperty("needsCache", false);
			break;

		default:
			LOGGER.error("request path not found.");
			Message msg = CoreMessages.transformFailedFrom(ResultStandardizer.class);
			throw new TransformerException(msg);
		}
		
//		if(P_WEATHER.matcher(reqPath).matches()){
//			message.setInvocationProperty("requestSpecs", new LinkedList(WEATHER_REQUEST_SPECS));
//			message.setInvocationProperty("mongoCollectionName", "weatherToday");
//			message.setInvocationProperty("timeoutMillis", TimeUnit.HOURS.toMillis(1));
//		} else if(P_TRAIN_S2S.matcher(reqPath).matches()){
//			message.setInvocationProperty("requestSpecs", new LinkedList(TRAIN_S2S_REQUEST_SPECS));
//			message.setInvocationProperty("mongoCollectionName", "trainStation2Station");
//			message.setInvocationProperty("timeoutMillis", TimeUnit.SECONDS.toMillis(5));
//		}
//		else {
//			LOGGER.error("request path not found.");
//			Message msg = CoreMessages.transformFailedFrom(ResultStandardizer.class);
//			throw new TransformerException(msg);
//		}
		
		return message;
	}
	
}
