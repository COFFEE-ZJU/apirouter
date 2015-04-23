package cn.edu.zju.ccnt.train;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import cn.edu.zju.ccnt.ApiResult;

@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true,value = {"_id","partialObject","timestamp"})
public class Station2StationResult extends ApiResult{
	private static final Map<Character, String> TRAIN_LETTER_TO_TYPE = new HashMap<Character, String>();
	
	static{
		TRAIN_LETTER_TO_TYPE.put('K', "快速"	);
		TRAIN_LETTER_TO_TYPE.put('G', "高铁"	);
		TRAIN_LETTER_TO_TYPE.put('C', "城际"	);
		TRAIN_LETTER_TO_TYPE.put('D', "动车"	);
		TRAIN_LETTER_TO_TYPE.put('Z', "直达"	);
		TRAIN_LETTER_TO_TYPE.put('T', "特快"	);
	}
	
	public static String getTrainTypeByNumber(String trainNumber){
		if(trainNumber == null || trainNumber.length() == 0) return "其他";
		
		char letter = trainNumber.charAt(0);
		if(letter >= '0' && letter <= '9') return "普通";
		String type = TRAIN_LETTER_TO_TYPE.get(letter);
		return type == null ? "其他" : type;
	}
	
	private List<ResultRecord> data;

	public void setData(List<ResultRecord> data) {
		this.data = data;
	}

	public List<ResultRecord> getData() {
		return data;
	}
	
	public class ResultRecord {
		private String trainNo;
		private String trainNumber;
		private String fromStation;
		private String toStation;
		private String fromStationType;
		private String toStationType;
		private String fromTime;
		private String toTime;
		private Integer dayDifference;	//间隔天数
		private String durationTime;	//历时"hh:mm"
		private String trainType;
		private String saleTime;	//售票时间"hh:mm"
		private int controlDay;
		private List<SeatInfo> seats;
		
		public String getTrainNo() {
			return trainNo;
		}
		public void setTrainNo(String trainNo) {
			this.trainNo = trainNo;
		}
		public String getTrainNumber() {
			return trainNumber;
		}
		public void setTrainNumber(String trainNumber) {
			this.trainNumber = trainNumber;
		}
		public String getFromStation() {
			return fromStation;
		}
		public void setFromStation(String formStation) {
			this.fromStation = formStation;
		}
		public String getToStation() {
			return toStation;
		}
		public void setToStation(String toStation) {
			this.toStation = toStation;
		}
		public String getFromStationType() {
			return fromStationType;
		}
		public void setFromStationType(String fromStationType) {
			this.fromStationType = fromStationType;
		}
		public String getToStationType() {
			return toStationType;
		}
		public void setToStationType(String toStationType) {
			this.toStationType = toStationType;
		}
		public String getFromTime() {
			return fromTime;
		}
		public void setFromTime(String fromTime) {
			this.fromTime = fromTime;
		}
		public String getToTime() {
			return toTime;
		}
		public void setToTime(String toTime) {
			this.toTime = toTime;
		}
		public Integer getDayDifference() {
			return dayDifference;
		}
		public void setDayDifference(Integer dayDifference) {
			this.dayDifference = dayDifference;
		}
		public String getDurationTime() {
			return durationTime;
		}
		public void setDurationTime(String durationTime) {
			this.durationTime = durationTime;
		}
		public String getTrainType() {
			return trainType;
		}
		public void setTrainType(String trainType) {
			this.trainType = trainType;
		}
		public String getSaleTime() {
			return saleTime;
		}
		public void setSaleTime(String saleTime) {
			this.saleTime = saleTime;
		}
		public int getControlDay() {
			return controlDay;
		}
		public void setControlDay(int controlDay) {
			this.controlDay = controlDay;
		}
		public List<SeatInfo> getSeats() {
			return seats;
		}
		public void setSeats(List<SeatInfo> seats) {
			this.seats = seats;
		}
	}

	public class SeatInfo{
		private Number price;
		private String name;
		private boolean bookable;
		private Integer restTickets;
		
		public Number getPrice() {
			return price;
		}
		public void setPrice(Number price) {
			this.price = price;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public boolean isBookable() {
			return bookable;
		}
		public void setBookable(boolean bookable) {
			this.bookable = bookable;
		}
		public int getRestTickets() {
			return restTickets;
		}
		public void setRestTickets(int restTickets) {
			this.restTickets = restTickets;
		}
	}

}

