package cn.edu.zju.ccnt.express;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import cn.edu.zju.ccnt.ApiResult;
/**
 * 
 * @author zheng
 * 2015-4-24 ����4:16:17
 * ��ݲ�ѯ���ؽ��
 */
@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true,value = {"_id","partialObject","timestamp"})
public class ExpressResult extends ApiResult{
	private String  trackingNO; //����
	private String  companyType; //�������
	private String isCheck;      //�������Ƿ�ǩ��
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


