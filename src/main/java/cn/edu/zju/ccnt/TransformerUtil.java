package cn.edu.zju.ccnt;

import java.util.Map;
import java.util.Map.Entry;

public class TransformerUtil {
	public static String queryToString(Map<String, String> query){
		if(query == null || query.isEmpty()) return "";
		
		StringBuffer buf = new StringBuffer();
		for(Entry<String, String> entry: query.entrySet()){
			buf.append(entry.getKey());
			buf.append("=");
			buf.append(entry.getValue());
			buf.append("&");
		}
		return buf.substring(0, buf.length()-1);
	}
}
