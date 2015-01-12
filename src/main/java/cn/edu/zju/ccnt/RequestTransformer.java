package cn.edu.zju.ccnt;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;

public abstract class RequestTransformer extends AbstractMessageTransformer {
	Logger logger = Logger.getLogger(RequestTransformer.class);
	
	private MuleMessage message = null;
	private String reqPath = null;
	private String[] pathSplit = null;
	private Map<String, String> query = null;
	private boolean hasFormatError = false;
	private String formatErrReason = null;
	
	public Object transformMessage(MuleMessage message, String outputEncoding)
			throws TransformerException {
		logger.info(getClass().getSimpleName() + " matched");
		this.message = message;
		
		init();
		String tmp;
		message.setInvocationProperty("reqHost", (tmp = generateReqHost()) == null ? "" : tmp );
		message.setInvocationProperty("reqPath", (tmp = generateReqPath()) == null ? "" : tmp );
		message.setInvocationProperty("httpMethod", (tmp = generateHttpMethod()) == null ? "" : tmp );
		
		message.setInvocationProperty("hasFormatError", hasFormatError);
		message.setInvocationProperty("formatErrReason", formatErrReason == null ? "" : formatErrReason);
		clear();
		
//		String reqPath = (String)message.getInboundProperty("http.request");
//		String[] pathSplit = reqPath.split("/");
//		if(pathSplit.length == 0 || pathSplit.length == 1){	// root path "/"
//			message.setInvocationProperty("reqHost", "www.juhe.cn");
//			message.setInvocationProperty("reqPath", "/");
//		} else {
//			message.setInvocationProperty("reqHost", "www.cc98.org");
//			message.setInvocationProperty("reqPath", reqPath);
//		}
//		
//		message.setInvocationProperty("invo", "testInvoVal");
//		logger.info("payload: " + message.getPayload());
		return message;
	}
	
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
	
	protected MuleMessage getMessage(){
		return message;
	}
	
	protected String getInboundReqPath(){
		if(message == null) return null;
		if(reqPath == null) reqPath = (String)message.getInboundProperty("http.request");
		
		return reqPath;
	}
	
	protected String[] getInboundReqPathSplit(){
		if(pathSplit == null){
			String path = getInboundReqPath();
			if(path == null) return null;
			
			pathSplit = path.split("/");
		}
		
		return pathSplit;
	}
	
	/**
	 * Override it and put anything you like before we invoke generateReqHost, generateReqPath and generateHttpMethod
	 */
	protected void init(){}
	
	protected boolean setPayload(Object payload){
		if(message == null) return false;
		
		message.setPayload(payload);
		return true;
	}
	
	@SuppressWarnings("unchecked")
	protected Map<String, String> getInboundQuery(){
		if(message == null) return null;
		if(query == null) query = (Map<String, String>)message.getInboundProperty("http.query.params");
		
		return query;
	}
	
	/**
	 * 
	 * @return the host of the outbound api request (like "api.open.baidu.com")
	 */
	protected abstract String generateReqHost();
	
	/**
	 * 
	 * @return the path (along with the query params) of the outbound api request (like "/weather/q?city=Hangzhou")
	 */
	protected abstract String generateReqPath();
	
	/**
	 * by default use whatever method the inbound request uses.
	 * 
	 * @return the http method type used in the api request ("GET", "POST", "PUT", etc.)
	 */
	protected String generateHttpMethod(){
		if(message == null) return null;
		
		return message.getInboundProperty("http.method");
	}

	protected void formatError(String reason){
		hasFormatError = true;
		formatErrReason = reason;
	}
	
	private void clear(){
		message = null;
		reqPath = null;
		pathSplit = null;
		query = null;
		hasFormatError = false;
		formatErrReason = null;
	}
}
