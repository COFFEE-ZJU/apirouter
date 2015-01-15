package cn.edu.zju.ccnt;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class WeatherRequestTransformer extends RequestTransformer {
	private static Logger logger = Logger.getLogger(WeatherRequestTransformer.class);
	private final static Map<String, String> cityCodeMap = new HashMap<String, String>();
	private static final Pattern P_51WNL = Pattern.compile("/weather/51wnl/q\\?.*");
	private static final Pattern P_ZGTQW_ZS = Pattern.compile("/weather/zgtqw/zs/q\\?.*");
	private static final Pattern P_ZGTQW_SK = Pattern.compile("/weather/zgtqw/sk/q\\?.*");
	
	private String host;
	private String path;
	
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
	protected void init(){
		String oriPath = getInboundReqPath();
		Map<String, String> query = getInboundQuery();
		String cityName = query.get("city");
		if(cityName == null){
			formatError("city param missing");
			return;
		}
		
		String cityCode;
		if(cityCodeMap.isEmpty()) getCityCodeMapFromFile();
		cityCode = cityCodeMap.get(cityName);
		
		if(cityCode == null){
			formatError("city not found");
			return;
		}
		
		if(P_51WNL.matcher(oriPath).matches()){
			host = "weather.51wnl.com";
			path = "/weatherinfo/GetMoreWeather?cityCode=" + cityCode + "&weatherType=0";
		} else if(P_ZGTQW_ZS.matcher(oriPath).matches()){
			host = "www.weather.com.cn";
			path = "/data/zs/" + cityCode + ".html";
		} else if(P_ZGTQW_SK.matcher(oriPath).matches()){
			host = "www.weather.com.cn";
			path = "/data/sk/" + cityCode + ".html";
		} else {
			formatError("unknow path in weather");
		}
		
	}
	
	@Override
	protected String generateReqHost() {
		return host;
	}

	@Override
	protected String generateReqPath() {
		return path;
	}

}
