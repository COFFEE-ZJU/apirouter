package cn.edu.zju.ccnt.train;

import java.util.List;

import cn.edu.zju.ccnt.ApiResult;

public class Station2StationResult extends ApiResult{
	private String fromCity;
	private String toCity;
	public String getfromCity() {
		return fromCity;
	}
	public void setfromCity(String fromCity) {
		this.fromCity = fromCity;
	}
	public String gettoCity() {
		return toCity;
	}
	public void settoCity(String toCity) {
		this.toCity = toCity;
	}
	
}

class ResultRecord {
	private String trainNo;
	private String trainNumber;
	private String formStation;
	private String toStation;
	private String fromTime;
	private String toTime;
	private String dayDifference;	//间隔天数
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
	public String getFormStation() {
		return formStation;
	}
	public void setFormStation(String formStation) {
		this.formStation = formStation;
	}
	public String getToStation() {
		return toStation;
	}
	public void setToStation(String toStation) {
		this.toStation = toStation;
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
	public String getDayDifference() {
		return dayDifference;
	}
	public void setDayDifference(String dayDifference) {
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

class SeatInfo{
	private double price;
	private String name;
	private boolean bookable;
	private int restTickets;
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
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
