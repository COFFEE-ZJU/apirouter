package cn.edu.zju.ccnt.rules;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class WeatherRule51Wnl implements TransformRule{
	private static Logger logger = Logger.getLogger(WeatherRule51Wnl.class);
	private final static Map<String, String> cityCodeMap = new HashMap<String, String>();

	@Override
	public boolean isRestful() {
		return true;
	}

	@Override
	public boolean returnsJson() {
		return true;
	}

	@Override
	public Map<String, String> transParam(Map<String, String> oriParam)
			throws TransformException {
		String cityName = oriParam.get("city");
		if(cityName == null) throw new TransformException("missing param 'city'.");
		
		if(cityCodeMap.isEmpty()) getCityCodeMapFromFile();
		final String cityCode = cityCodeMap.get(cityName);
		
		if(cityCode == null) throw new TransformException("city not found");
		
		return new HashMap<String, String>(){{put("cityCode",cityCode);}};
	}

	@Override
	public Map<String, String> transResult(Map<String, String> oriResult)
			throws TransformException {
		String city;
		return null;
	}

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
}
