package cn.edu.zju.ccnt.phone;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import cn.edu.zju.ccnt.ApiResult;
/**
 * 
 * @author zheng
 * 2015-4-23 下午9:27:51
 */
@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true,value = {"_id","partialObject","timestamp"})
public class PhoneResult extends ApiResult{
	private String tel;
	private String province;
	private String carrier;  //运营商
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	
	
}
