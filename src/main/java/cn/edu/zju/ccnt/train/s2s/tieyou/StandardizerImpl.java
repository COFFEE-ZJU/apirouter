package cn.edu.zju.ccnt.train.s2s.tieyou;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import cn.edu.zju.ccnt.ResultStandardizer;
import cn.edu.zju.ccnt.train.Station2StationResult;
import cn.edu.zju.ccnt.train.Station2StationResult.ResultRecord;
import cn.edu.zju.ccnt.train.Station2StationResult.SeatInfo;

public class StandardizerImpl extends ResultStandardizer<Station2StationResult>{
	private static final ObjectMapper MAPPER = new ObjectMapper();
//	private static final Logger LOGGER = Logger.getLogger(StandardizerImpl.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public Station2StationResult standardize(Object input, Map<String, String>requestParams) 
			throws JsonParseException, JsonMappingException, IOException {
		String json;
		if(input instanceof String){
			json = (String)input;
		} else {
			throw new ClassCastException("failed to cast "+input.getClass().toString());
		}
		
		Map<String, Object> resData = MAPPER.readValue(json, Map.class);
		List<Map<String, Object>> oriRecords = (List<Map<String, Object>>)resData.get("data");
		
		Station2StationResult ret = new Station2StationResult();
		
		List<ResultRecord> records = new ArrayList<ResultRecord>();
		ResultRecord record;
		List<SeatInfo> seats;
		SeatInfo seat;
		List<Map<String, Object>> oriSeats;
		
		for(Map<String, Object> oriRecord : oriRecords){
			record = ret.new ResultRecord();
			record.setTrainNo((String)oriRecord.get("train_no"));
			record.setTrainNumber((String)oriRecord.get("train_number"));
			record.setFromStation((String)oriRecord.get("from_station"));
			record.setToStation((String)oriRecord.get("to_station"));
			record.setFromStationType((String)oriRecord.get("from_station_type"));
			record.setToStationType((String)oriRecord.get("to_station_type"));
			record.setFromTime((String)oriRecord.get("from_time"));
			record.setToTime((String)oriRecord.get("to_time"));
			record.setDayDifference(Integer.valueOf((String)oriRecord.get("day_diff")));
			record.setDurationTime(minToHHMM((String)oriRecord.get("use_time")));
			record.setTrainType(Station2StationResult.getTrainTypeByNumber(
					record.getTrainNumber()));
			record.setSaleTime((String)oriRecord.get("sale_time"));
			record.setControlDay((Integer)oriRecord.get("control_day"));
			
			seats = new ArrayList<SeatInfo>();
			oriSeats = (List<Map<String, Object>>)oriRecord.get("seats");
			
			for(Map<String, Object> oriSeat : oriSeats){
				seat = ret.new SeatInfo();
//				LOGGER.info(oriSeat.get("seat_price"));
				
				Object priceObj = oriSeat.get("seat_price");
				Number price;
				if(priceObj instanceof Number) price = (Number)priceObj;
				else price = Double.valueOf((String)priceObj);
				
				seat.setPrice(price);
				seat.setName((String)oriSeat.get("seat_name"));
				seat.setBookable((int)oriSeat.get("seat_bookable") == 1 ? true : false);
				seat.setRestTickets((Integer)oriSeat.get("seat_yupiao"));
				
				seats.add(seat);
			}
			record.setSeats(seats);
			
			records.add(record);
		}
		
		
		ret.setData(records);
		return ret;
	}
	
	private static final String HHMM_FORMAT = "%02d:%02d";
	
	private static String minToHHMM(String mins){
		int min = Integer.valueOf(mins);
		return String.format(HHMM_FORMAT, min / 60, min % 60);
	}

}
