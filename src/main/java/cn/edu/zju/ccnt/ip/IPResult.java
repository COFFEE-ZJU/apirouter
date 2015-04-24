package cn.edu.zju.ccnt.ip;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import cn.edu.zju.ccnt.ApiResult;
/**
 * 
 * @author zheng
 * 2015-4-22 下午2:57:12
 * IP查询返回的标准结果
 */
@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true,value = {"_id","partialObject","timestamp"})
public class IPResult extends ApiResult{
	private String country;
	private String province;
	private String city;
	private String ISP;  //ISP:网络服务提供商
	private String ip;
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getISP() {
		return ISP;
	}
	public void setISP(String iSP) {
		ISP = iSP;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	
}
