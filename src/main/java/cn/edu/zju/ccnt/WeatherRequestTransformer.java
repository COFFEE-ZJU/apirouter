package cn.edu.zju.ccnt;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class WeatherRequestTransformer extends RequestTransformer {
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

	@Override
	protected String generateReqHost() {
		return "weather.51wnl.com";
	}

	@Override
	protected String generateReqPath() {
		Map<String, String> query = getInboundQuery();
		String cityName = query.get("city");
		String cityCode;
		if(cityName != null){
			if(cityCodeMap.isEmpty()) getCityCodeMapFromFile();
			cityCode = cityCodeMap.get(cityName);
		} else cityCode = null;
		return "/weatherinfo/GetMoreWeather?cityCode=" + (cityCode == null ? "" : cityCode) + "&weatherType=0";
	}

}
