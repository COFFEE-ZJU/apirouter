package cn.edu.zju.ccnt;

import java.util.Map;

/**
 * @author Kefei Zhang
 * @version created time: 2015-4-25 ÏÂÎç9:12:08
 * 
 */
public abstract class ParamsValidator {
	public static class ParamsValidationException extends Exception{
		private static final long serialVersionUID = 8726810540213584840L;
		
		public ParamsValidationException(){
			super();
		}
		
		public ParamsValidationException(String arg0){
			super(arg0);
		}
	}
	
	public abstract boolean validate(Map<String, String> requestParams);
	
	public abstract void validateWithException(Map<String, String> requestParams) throws ParamsValidationException;
	
	/**
	 * implement this method to define the rule to form the _id field for MongoDB cache.
	 * if the result needn't to be cached in MongoDB, just implement it by returning null.
	 * otherwise, it must be unique in the MongoDB for cache index.
	 * 
	 * @param requestParams
	 * @return the generated _id field for MongoDB
	 */
	public abstract String generate_idFromRequestMap(Map<String, String> requestParams);
}
