package cn.edu.zju.ccnt.train.trainno.tieyou;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import cn.edu.zju.ccnt.ResultStandardizer;
import cn.edu.zju.ccnt.train.TrainNumberInfoResult;
import cn.edu.zju.ccnt.train.TrainNumberInfoResult.TrainStopInfo;

public class StandardizerImpl extends ResultStandardizer<TrainNumberInfoResult>{
	private static final ObjectMapper MAPPER = new ObjectMapper();
//	private static final Logger LOGGER = Logger.getLogger(StandardizerImpl.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public TrainNumberInfoResult standardize(Object input, Map<String, String>requestParams) 
			throws JsonParseException, JsonMappingException, IOException, NoSuchFieldException {
		String json;
		if(input instanceof String){
			json = (String)input;
		} else {
			throw new ClassCastException("failed to cast "+input.getClass().toString());
		}
		
		Map<String, Object> resData = MAPPER.readValue(json, Map.class);
		List<Map<String, Object>> oriStops = (List<Map<String, Object>>)getMapObjByPath(
				resData, new String[]{"data","stations"});
		
		TrainNumberInfoResult ret = new TrainNumberInfoResult();
		
		List<TrainStopInfo> stops = new ArrayList<TrainStopInfo>();
		TrainStopInfo stop;
		
		for(Map<String, Object> oriStop : oriStops){
			stop = ret.new TrainStopInfo();
			stop.setId(Integer.valueOf((String)oriStop.get("station_id")));
			stop.setStopOrder(Integer.valueOf((String)oriStop.get("station_no")));
			stop.setName((String)oriStop.get("station_name"));
			stop.setArriveTime((String)oriStop.get("arrive_time"));
			stop.setDepartTime((String)oriStop.get("start_time"));
			
			String stopMins = (String)oriStop.get("stop_time");
			try {
				stop.setStopMins(Integer.parseInt(stopMins));
			} catch (NumberFormatException e) {
				stop.setStopMins(0);
			}
			
			stop.setSubTrainNo((String)oriStop.get("sub_train_num"));
			
			stops.add(stop);
		}
		
		
		ret.setData(stops);
		return ret;
	}
	
}
