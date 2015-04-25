package cn.edu.zju.ccnt.train;

import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import cn.edu.zju.ccnt.ApiResult;

@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrainNumberInfoResult extends ApiResult {

	private List<TrainStopInfo> data;
	
	public List<TrainStopInfo> getData() {
		return data;
	}

	public void setData(List<TrainStopInfo> data) {
		this.data = data;
	}

	public class TrainStopInfo{
		private Integer id;
		private Integer stopOrder;
		private String name;
		private String arriveTime;
		private String departTime;
		private Integer stopMins;
		private String subTrainNo;
		
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public Integer getStopOrder() {
			return stopOrder;
		}
		public void setStopOrder(Integer stopOrder) {
			this.stopOrder = stopOrder;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getArriveTime() {
			return arriveTime;
		}
		public void setArriveTime(String arriveTime) {
			this.arriveTime = arriveTime;
		}
		public String getDepartTime() {
			return departTime;
		}
		public void setDepartTime(String departTime) {
			this.departTime = departTime;
		}
		public Integer getStopMins() {
			return stopMins;
		}
		public void setStopMins(Integer stopMins) {
			this.stopMins = stopMins;
		}
		public String getSubTrainNo() {
			return subTrainNo;
		}
		public void setSubTrainNo(String subTrainNo) {
			this.subTrainNo = subTrainNo;
		}
	}

}
