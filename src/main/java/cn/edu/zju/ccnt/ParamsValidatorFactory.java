package cn.edu.zju.ccnt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Kefei Zhang
 * @version created time: 2015-4-25 ÏÂÎç9:45:37
 * 
 */
public class ParamsValidatorFactory {
	private static final Set<String> EMPTY_SET = new HashSet<String>();
	private ParamsValidatorFactory(){};
	
	public static ParamsValidator generateParamsValidatorByFields(String[] obligatoryFields){
		return generateParamsValidatorByFields(obligatoryFields, null, true);
	}
	
	public static ParamsValidator generateParamsValidatorByFields(Set<String> obligatoryFields){
		return generateParamsValidatorByFields(obligatoryFields, null, true);
	}
	
	public static ParamsValidator generateParamsValidatorByFields(
			String[] obligatoryFields, String[] optionalFields, boolean allowUnknownFields){
		return generateParamsValidatorByFields(
				obligatoryFields == null ? EMPTY_SET : new HashSet<String>(Arrays.asList(obligatoryFields)), 
				optionalFields == null ? EMPTY_SET : new HashSet<String>(Arrays.asList(optionalFields)),
				allowUnknownFields);
	}
	
	public static ParamsValidator generateParamsValidatorByFields(
			Set<String> obligatoryFields, Set<String> optionalFields, boolean allowUnknownFields){
		return new ParamsValidatorImpl(obligatoryFields, optionalFields, allowUnknownFields);
	}
	
	static class ParamsValidatorImpl extends ParamsValidator{
		private final Set<String> obligatoryFields;
		private final Set<String> optionalFields;
		private final boolean allowUnknownFields;
		private String failMsg;
		
		public ParamsValidatorImpl(Set<String> obligatoryFields,
				Set<String> optionalFields, boolean allowUnknownFields) {
			super();
			this.obligatoryFields = obligatoryFields == null ? EMPTY_SET : obligatoryFields;
			this.optionalFields = optionalFields == null ? EMPTY_SET : optionalFields;
			this.allowUnknownFields = allowUnknownFields;
		}

		@Override
		public boolean validate(Map<String, String> requestParams) {
			Set<String> paramsSet = requestParams == null ? EMPTY_SET : requestParams.keySet();
			
			// no obligatory fields or optional fields, then all fields are unknown.
			// then if it allows unknown fields, return true
			// otherwise, unless no params(which returns true), return false
//			if(obligatoryFields.isEmpty() && optionalFields.isEmpty())
//				return allowUnknownFields || paramsSet.isEmpty();
			
			// checks all the obligatory fields
			for(String field : obligatoryFields)
				if(!paramsSet.contains(field)){
					failMsg = "missing obligatory param " + field;
					return false;
				}
			
			// if it allows unknown fields, no need to check optional fields, return true
			if(allowUnknownFields) return true;
			
			// make sure there is no unknown fields
			for(String field : paramsSet)
				if(!obligatoryFields.contains(field) && !optionalFields.contains(field)){
					failMsg = "unknown param " + field + " not allowed";
					return false;
				}
			
			return true;
		}

		@Override
		public void validateWithException(Map<String, String> requestParams)
				throws ParamsValidationException {
			if(validate(requestParams)) return;
			else throw new ParamsValidationException(failMsg);
			
		}

		@Override
		public String generate_idFromRequestMap(
				Map<String, String> requestParams) {
			List<Map.Entry<String,String>> list = new ArrayList<Map.Entry<String,String>>(requestParams.entrySet());
	        Collections.sort(list,new Comparator<Map.Entry<String,String>>() {
	            @Override
	            public int compare(Map.Entry<String, String> o1,
	            		Map.Entry<String, String> o2) {
	                return o1.getKey().compareTo(o2.getKey());
	            }
	        });
	        
	        StringBuilder sb = new StringBuilder();
	        for(Map.Entry<String, String> entry : list){
	        	sb.append(entry.getValue());
	        	sb.append("&");
	        }
			return sb.toString();
		}
	}
}


