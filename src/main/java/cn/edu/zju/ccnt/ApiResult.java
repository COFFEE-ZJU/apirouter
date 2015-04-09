package cn.edu.zju.ccnt;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.mongodb.ReflectionDBObject;

@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResult extends ReflectionDBObject{
	@JsonIgnore
	private long timestamp;
	
	public long gettimestamp() {
		return timestamp;
	}
	public void settimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
}
