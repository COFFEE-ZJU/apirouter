package cn.edu.zju.ccnt;

import java.util.List;

/**
 * @author Kefei Zhang
 * @version created time: 2015-4-25 обнГ11:26:35
 * 
 */
public class ApiCatagorySpec<R extends ApiResult> {
	private Class<? extends ApiResult> resultClazz;
	private String requestPath;
	private boolean needCache;
	private String mongoCollectionName;
	private boolean hasTimeout;
	private long timeoutMillis;
	private ParamsValidator paramsValidator;
	private List<RequestSpec<R>> requestSpecs;
	
}
