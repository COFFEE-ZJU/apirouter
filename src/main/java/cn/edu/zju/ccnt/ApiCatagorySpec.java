package cn.edu.zju.ccnt;

import java.util.List;
import java.util.Map;

/**
 * @author Kefei Zhang
 * @version created time: 2015-4-25 ÏÂÎç11:26:35
 * 
 */
public abstract class ApiCatagorySpec<R extends ApiResult> {
	private String requestPath;
	private boolean needsCache;
	private String mongoCollectionName;
	private boolean hasTimeout;
	private long timeoutMillis;
	private ParamsValidator paramsValidator;
	private List<RequestSpec<R>> requestSpecs;
	private Map<String, String> currentRequestParams;
	
	protected ApiCatagorySpec(String requestPath, boolean needsCache,
			String mongoCollectionName, boolean hasTimeout, long timeoutMillis,
			ParamsValidator paramsValidator, List<RequestSpec<R>> requestSpecs) {
		super();
		this.requestPath = requestPath;
		this.needsCache = needsCache;
		this.mongoCollectionName = mongoCollectionName;
		this.hasTimeout = hasTimeout;
		this.timeoutMillis = timeoutMillis;
		this.paramsValidator = paramsValidator;
		this.requestSpecs = requestSpecs;
	}
	
	/**
	 * implement this method to define the rule to form the _id field for MongoDB cache.
	 * if the result needn't to be cached in MongoDB, just implement it by returning null.
	 * otherwise, it must be unique in the MongoDB for cache index.
	 * 
	 * @param requestParams
	 * @return the generated _id field for MongoDB
	 */
	public abstract	String generate_idFromRequestMap();

	public Map<String, String> getCurrentRequestParams() {
		return currentRequestParams;
	}

	public void setCurrentRequestParams(Map<String, String> currentRequestParams) {
		this.currentRequestParams = currentRequestParams;
	}

	public String getRequestPath() {
		return requestPath;
	}

	public boolean needsCache() {
		return needsCache;
	}

	public String getMongoCollectionName() {
		return mongoCollectionName;
	}

	public boolean hasTimeout() {
		return hasTimeout;
	}

	public long getTimeoutMillis() {
		return timeoutMillis;
	}

	public ParamsValidator getParamsValidator() {
		return paramsValidator;
	}

	public List<RequestSpec<R>> getRequestSpecs() {
		return requestSpecs;
	}
}
