package cn.edu.zju.ccnt.flight.c2c;

import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import cn.edu.zju.ccnt.ApiResult;

@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class City2CityResult extends ApiResult{
	private List<ResultRecord> data;

	public void setData(List<ResultRecord> data) {
		this.data = data;
	}

	public List<ResultRecord> getData() {
		return data;
	}
	
	public class ResultRecord {
		private String arriveAirportName;
		private String arriveAirportCode;
		private String arriveTerminal;
		private String arriveTime;
		private String planArriveTime;
		private String departAirportName;
		private String departAirportCode;
		private String departTerminal;
		private String departTime;
		private String planDepartTime;
		private String flightNo;
		private String isInter;
		private String status;
		
		public String getArriveAirportName() {
			return arriveAirportName;
		}
		public void setArriveAirportName(String arriveAirportName) {
			this.arriveAirportName = arriveAirportName;
		}
		public String getArriveAirportCode() {
			return arriveAirportCode;
		}
		public void setArriveAirportCode(String arriveAirportCode) {
			this.arriveAirportCode = arriveAirportCode;
		}
		public String getArriveTerminal() {
			return arriveTerminal;
		}
		public void setArriveTerminal(String arriveTerminal) {
			this.arriveTerminal = arriveTerminal;
		}
		public String getArriveTime() {
			return arriveTime;
		}
		public void setArriveTime(String arriveTime) {
			this.arriveTime = arriveTime;
		}
		public String getPlanArriveTime() {
			return planArriveTime;
		}
		public void setPlanArriveTime(String planArriveTime) {
			this.planArriveTime = planArriveTime;
		}
		public String getDepartAirportName() {
			return departAirportName;
		}
		public void setDepartAirportName(String departAirportName) {
			this.departAirportName = departAirportName;
		}
		public String getDepartAirportCode() {
			return departAirportCode;
		}
		public void setDepartAirportCode(String departAirportCode) {
			this.departAirportCode = departAirportCode;
		}
		public String getDepartTerminal() {
			return departTerminal;
		}
		public void setDepartTerminal(String departTerminal) {
			this.departTerminal = departTerminal;
		}
		public String getDepartTime() {
			return departTime;
		}
		public void setDepartTime(String departTime) {
			this.departTime = departTime;
		}
		public String getPlanDepartTime() {
			return planDepartTime;
		}
		public void setPlanDepartTime(String planDepartTime) {
			this.planDepartTime = planDepartTime;
		}
		public String getFlightNo() {
			return flightNo;
		}
		public void setFlightNo(String flightNo) {
			this.flightNo = flightNo;
		}
		public String getIsInter() {
			return isInter;
		}
		public void setIsInter(String isInter) {
			this.isInter = isInter;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
	}

}

