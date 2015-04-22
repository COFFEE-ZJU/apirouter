package cn.edu.zju.ccnt;

public class RequestSpec {
	public static enum HttpMethodType {GET, POST, PUT}; 
	private String url;
	private RestRequestPramsGenerator paramsGenerator;
	private ResultStandardizer resultStandardizer;
	private HttpMethodType methodType;
	
	public RequestSpec(String url, RestRequestPramsGenerator paramsGenerator,
			ResultStandardizer resultStandardizer, HttpMethodType methodType) {
		this.url = url;
		this.paramsGenerator = paramsGenerator;
		this.resultStandardizer = resultStandardizer;
		this.methodType = methodType;
	}

	public String getUrl() {
		return url;
	}

	public RestRequestPramsGenerator getParamsGenerator() {
		return paramsGenerator;
	}

	public ResultStandardizer getResultStandardizer() {
		return resultStandardizer;
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
