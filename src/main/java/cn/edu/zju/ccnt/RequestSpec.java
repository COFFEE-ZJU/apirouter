package cn.edu.zju.ccnt;

import java.util.Map;

public class RequestSpec<T extends ApiResult> {
	public static enum HttpMethodType {GET, POST, PUT}; 
	private String url;
	private RestRequestPramsGenerator paramsGenerator;
	private ResultStandardizer<T> resultStandardizer;
	private HttpMethodType methodType;
	
	public RequestSpec(String url, RestRequestPramsGenerator paramsGenerator,
			ResultStandardizer<T> resultStandardizer, HttpMethodType methodType) {
		this.url = url;
		this.paramsGenerator = paramsGenerator;
		this.resultStandardizer = resultStandardizer;
		this.methodType = methodType;
	}

	public String getUrl() {
		return url;
	}
	
	public String generateParamString(Map<String, String> params) throws Exception{
		return paramsGenerator.generateParamString(params);
	}
	
	public T standardize(Object input, Map<String, String>requestParams) throws Exception{
		return resultStandardizer.standardize(input, requestParams);
	}
	
	public HttpMethodType getMethodType() {
		return methodType;
	}
	
	public boolean isMethodGet(){
		return methodType == HttpMethodType.GET;
	}
	
	public boolean isMethodPost(){
		return methodType == HttpMethodType.POST;
	}
	
	public boolean isMethodPut(){
		return methodType == HttpMethodType.PUT;
	}
}
