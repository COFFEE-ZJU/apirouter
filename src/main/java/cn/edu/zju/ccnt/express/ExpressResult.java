package cn.edu.zju.ccnt.express;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import cn.edu.zju.ccnt.ApiResult;
/**
 * 
 * @author zheng
 * 2015-4-24 下午4:16:17
 * 快递查询返回结果
 */
@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true,value = {"_id","partialObject","timestamp"})
public class ExpressResult extends ApiResult{
	private String  trackingNO; //单号
	private String  companyType; //快递类型
	private String isCheck;      //可能是是否签收
	private List<ExpressRecord> data;
	public String getTrackingNO() {
		return trackingNO;
	}
	public void setTrackingNO(String trackingNO) {
		this.trackingNO = trackingNO;
	}
	public String getCompanyType() {
		return companyType;
	}
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}
	public String getIsCheck() {
		return isCheck;
	}
	public void setIsCheck(String isCheck) {
		this.isCheck = isCheck;
	}
	public List<ExpressRecord> getData() {
		return data;
	}
	public void setData(List<ExpressRecord> data) {
		this.data = data;
	}
	
}


