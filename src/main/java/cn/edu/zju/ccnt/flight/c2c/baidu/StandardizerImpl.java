package cn.edu.zju.ccnt.flight.c2c.baidu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import cn.edu.zju.ccnt.ResultStandardizer;
import cn.edu.zju.ccnt.flight.c2c.City2CityResult;
import cn.edu.zju.ccnt.flight.c2c.City2CityResult.ResultRecord;

public class StandardizerImpl extends ResultStandardizer<City2CityResult>{
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	@SuppressWarnings("unchecked")
	@Override
	public City2CityResult standardize(Object input, Map<String, String>requestParams) 
			throws JsonParseException, JsonMappingException, IOException {
		String json;
		if(input instanceof String){
			json = (String)input;
		} else {
			throw new ClassCastException("failed to cast "+input.getClass().toString());
		}
		
		Map<String, Object> resData = MAPPER.readValue(json, Map.class);
		List<Map<String, Object>> oriRecords = (List<Map<String, Object>>)resData.get("data");
		
		City2CityResult ret = new City2CityResult();
		
		List<ResultRecord> records = new ArrayList<ResultRecord>();
		ResultRecord record;
		
		for(Map<String, Object> oriRecord : oriRecords){
			record = ret.new ResultRecord();
			record.setArriveAirportName((String)oriRecord.get("arrAirport"));
			record.setArriveAirportCode((String)oriRecord.get("arrAirportCode"));
			record.setArriveTerminal((String)oriRecord.get("arrTerminal"));
			record.setArriveTime((String)oriRecord.get("arrTime"));
			record.setPlanArriveTime((String)oriRecord.get("planArrTime"));
			
			record.setDepartAirportName((String)oriRecord.get("dptAirport"));
			record.setDepartAirportCode((String)oriRecord.get("dptAirportCode"));
			record.setDepartTerminal((String)oriRecord.get("dptTerminal"));
			record.setDepartTime((String)oriRecord.get("dptTime"));
			record.setPlanDepartTime((String)oriRecord.get("planDptTime"));
			
			record.setFlightNo((String)oriRecord.get("flightNO"));
			record.setIsInter((String)oriRecord.get("isInter"));
			record.setStatus((String)oriRecord.get("status"));
			
			records.add(record);
		}
		
		
		ret.setData(records);
		return ret;
	}
	
}
