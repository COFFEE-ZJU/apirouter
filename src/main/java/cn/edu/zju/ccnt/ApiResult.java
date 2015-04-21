package cn.edu.zju.ccnt;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import com.mongodb.ReflectionDBObject;

@JsonAutoDetect
public class ApiResult extends ReflectionDBObject{
	private long timestamp;
	
	public ApiResult(){
		settimestamp(System.currentTimeMillis());
	}
	
	public long gettimestamp() {
		return timestamp;
	}
	public void settimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
}
