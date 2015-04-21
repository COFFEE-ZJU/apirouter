package cn.edu.zju.ccnt;

import java.util.Map;

import org.apache.log4j.Logger;

public abstract class ResultStandardizer{
	private static final Logger LOGGER = Logger.getLogger(ResultStandardizer.class);
	
	abstract public ApiResult standardize(Object input) throws Exception;
	
	@SuppressWarnings("unchecked")
	protected static Object getMapObjByPath(Map<String, Object> map, String[] path) throws NoSuchFieldException{
		if(path == null || path.length == 0 || map == null) return map;
		try {
			Object tempObj = map;
			
			for(String field : path)
				tempObj = ((Map<String, Object>)tempObj).get(field);
			
			return tempObj;
			
		} catch (Exception e) {
			LOGGER.error(e);
			throw new NoSuchFieldException(e.getMessage());
		}
	}
}
