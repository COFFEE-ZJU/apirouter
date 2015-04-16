package cn.edu.zju.ccnt;

public class RequestSpec {
	private String url;
	private RestRequestPramsGenerator paramsGenerator;
	private ResultStandardizer resultStandardizer;
	
	public RequestSpec(String url, RestRequestPramsGenerator paramsGenerator,
			ResultStandardizer resultStandardizer) {
		this.url = url;
		this.paramsGenerator = paramsGenerator;
		this.resultStandardizer = resultStandardizer;
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
	
}
