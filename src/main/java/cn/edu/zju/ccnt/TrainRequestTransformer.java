package cn.edu.zju.ccnt;

import java.util.Map;
import java.util.regex.Pattern;

public class TrainRequestTransformer extends RequestTransformer {
	private static final Pattern P_JUHE = Pattern.compile("/train/juhe/.*");
	private static final Pattern P_TIEYOU_S2S = Pattern.compile("/train/tieyou/s2s/q\\?.*");
	private static final Pattern P_TIEYOU_CHECI = Pattern.compile("/train/tieyou/checi/q\\?.*");
	
	private String host;
	private String path;
	private String method;
	
	@Override
	protected void init(){
		String oriPath = getInboundReqPath();
		if(P_JUHE.matcher(oriPath).matches()){
			host = "apis.juhe.cn";
			path = getInboundReqPath().replace("train/juhe/", "");
			method = "GET";
		} else {
			boolean s2s = P_TIEYOU_S2S.matcher(oriPath).matches();
			boolean checi = P_TIEYOU_CHECI.matcher(oriPath).matches();
			if(s2s || checi){
				host = "m.tieyou.com";
				method = "POST";
				Map<String, String> query = getInboundQuery();
				setPayload(queryToString(query));
				
				path = s2s ? "/jy/index.php?param=dataApi/zhanzhanYuding.html" : "/jy/index.php?param=/dataApi/checi.html";
			} else {
				formatError("unknow path in train");
			}
		}
		
	}

	@Override
	protected String generateReqHost() { 
		return host;
	}

	@Override
	protected String generateReqPath() {
		return path;
	}
	
	@Override
	protected String generateHttpMethod(){
		return method;
	}

}
