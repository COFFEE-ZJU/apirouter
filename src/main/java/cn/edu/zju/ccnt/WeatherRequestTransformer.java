package cn.edu.zju.ccnt;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;

public class WeatherRequestTransformer extends AbstractMessageTransformer {
	static Logger logger = Logger.getLogger(WeatherRequestTransformer.class);
	final static Map<String, String> cityCodeMap = new HashMap<String, String>();
	
	private static void getCityCodeMapFromFile(){
		cityCodeMap.clear();
		BufferedReader input = null;
		try {
			input = new BufferedReader(new InputStreamReader(new FileInputStream("data/WeatherCityCode.txt"), "UTF-8"));
			String line;
			String[] split;
			while((line = input.readLine()) != null){
//				logger.info("log the lines: " + line);
				split = line.split(",");
				if(split.length != 2) throw new Exception("WeatherCityCode not correctly formatted!");
				
				cityCodeMap.put(split[1], split[0]);
			}
		} catch (Exception e) {
//			e.printStackTrace();
			logger.error(e);
		} finally {
			if(input != null){
				try {
					input.close();
				} catch (IOException e) {}
			}
		}
		
		logger.info("city codes loaded, size: " + cityCodeMap.size());
//		logger.info("try Hangzhou: " + cityCodeMap.get("º¼ÖÝ"));
	}
	
	private static String queryToString(Map<String, String> query){
		if(query == null || query.isEmpty()) return "";
		
		StringBuffer buf = new StringBuffer();
		for(Entry<String, String> entry: query.entrySet()){
			buf.append(entry.getKey());
			buf.append("=");
			buf.append(entry.getValue());
			buf.append("&");
		}
		return buf.substring(0, buf.length()-1);
	}
	
	public Object transformMessage(MuleMessage message, String outputEncoding)
			throws TransformerException {
		logger.info("matched weather regex");
		
		@SuppressWarnings("unchecked")
		Map<String, String> query = (Map<String, String>)message.getInboundProperty("http.query.params");
		String cityName = query.get("city");
		String cityCode;
		if(cityName != null){
			if(cityCodeMap.isEmpty()) getCityCodeMapFromFile();
			cityCode = cityCodeMap.get(cityName);
		} else cityCode = null;
		message.setInvocationProperty("reqHost", "weather.51wnl.com");
		message.setInvocationProperty("reqPath", 
				"/weatherinfo/GetMoreWeather?cityCode=" + (cityCode == null ? "" : cityCode) + "&weatherType=0");
		
		return message;
	}

}
