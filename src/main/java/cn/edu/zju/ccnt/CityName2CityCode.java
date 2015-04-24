package cn.edu.zju.ccnt;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;

public class CityName2CityCode extends AbstractMessageTransformer {
	private static Logger logger = Logger.getLogger(CityName2CityCode.class);
	private final static Map<String, String> cityCodeMap = new HashMap<String, String>();
	
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
	}
	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding)
			throws TransformerException {
		Map<String, String> map = (Map<String, String>)message.getInboundProperty("http.query.params");
		String cityName = map.get("city");
		if(cityName == null) return null;	//TODO deal exceptions.
		if(cityCodeMap.isEmpty()) getCityCodeMapFromFile();
		String cityCode = cityCodeMap.get(cityName);
		if(cityCode == null) return null;	//TODO deal exceptions.
		
		message.setInvocationProperty("cityCode", cityCode);
		
		return message;
	}	
}
