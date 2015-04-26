package cn.edu.zju.ccnt.ip;

import java.util.ArrayList;
import java.util.List;
import cn.edu.zju.ccnt.ApiCatagorySpec;
import cn.edu.zju.ccnt.ParamsValidator;
import cn.edu.zju.ccnt.ParamsValidatorFactory;
import cn.edu.zju.ccnt.RequestSpec;
import cn.edu.zju.ccnt.RequestSpec.HttpMethodType;

public class ApiCatagorySpecImpl extends ApiCatagorySpec<IPResult>{
	private static final List<RequestSpec<IPResult>> REQUEST_SPECS = 
			new ArrayList<RequestSpec<IPResult>>();
	private static final ParamsValidator PARAMS_VALIDATOR = 
			ParamsValidatorFactory.generateParamsValidatorByFields(new String[]{"ip"}, null, false);
	
	static{
		REQUEST_SPECS.add(new RequestSpec<IPResult>(
				"apistore.baidu.com/microservice/iplookup", 
				new cn.edu.zju.ccnt.ip.baidu.RestRequestPramsGeneratorImpl(), 
				new cn.edu.zju.ccnt.ip.baidu.StandardizerImpl(), HttpMethodType.GET));
		
		REQUEST_SPECS.add(new RequestSpec<IPResult>(
				"www.telize.com/geoip", 
				new cn.edu.zju.ccnt.ip.telize.RestRequestPramsGeneratorImpl(), 
				new cn.edu.zju.ccnt.ip.telize.StandardizerImpl(), HttpMethodType.GET));
	}
	
	public ApiCatagorySpecImpl() {
		super("/ip", false, null, false, 0L,
				PARAMS_VALIDATOR, REQUEST_SPECS);
	}

	@Override
	public String generate_idFromRequestMap() {
//		Map<String, String> requestMap = getCurrentRequestParams();
		return null;
	}

}
