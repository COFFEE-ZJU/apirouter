package cn.edu.zju.ccnt;

import java.util.Map;

public class TrainRequestTransformer extends RequestTransformer {
	private String host;
	private String path;
	private String method;
	
	@Override
	protected void init(){
		String[] pathSplit = getInboundReqPathSplit();
		method = "GET";
		if(pathSplit.length > 2 && pathSplit[2].equals("juhe")){
			host = "apis.juhe.cn";
			path = getInboundReqPath();
		} else if(pathSplit.length > 3 && pathSplit[2].equals("tieyou")){
			host = "m.tieyou.com";
			Map<String, String> query = getInboundQuery();
			setPayload(queryToString(query));
			method = "POST";
			
			if(pathSplit[3].equals("s2s")){
				path = "/jy/index.php?param=dataApi/zhanzhanYuding.html";
			} else if(pathSplit[3].equals("trainno")){
				path = "/jy/index.php?param=/dataApi/checi.html";
			} else formatError("path after 'tieyou' should be 's2s' or 'trainno'");
		} else formatError("unknow path in train");
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
