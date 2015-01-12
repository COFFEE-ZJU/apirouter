package cn.edu.zju.ccnt;

public class TrainRequestTransformer extends RequestTransformer {

	@Override
	protected String generateReqHost() { 
		return "apis.juhe.cn";
	}

	@Override
	protected String generateReqPath() {
		return getInboundReqPath();
	}

}
