package cn.edu.zju.ccnt.phone;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.edu.zju.ccnt.ApiCatagorySpec;
import cn.edu.zju.ccnt.ParamsValidator;
import cn.edu.zju.ccnt.ParamsValidatorFactory;
import cn.edu.zju.ccnt.RequestSpec;
import cn.edu.zju.ccnt.RequestSpec.HttpMethodType;

public class ApiCatagorySpecImpl extends ApiCatagorySpec<PhoneResult>{
	private static final List<RequestSpec<PhoneResult>> REQUEST_SPECS = 
			new ArrayList<RequestSpec<PhoneResult>>();
	private static final ParamsValidator PARAMS_VALIDATOR = 
			ParamsValidatorFactory.generateParamsValidatorByFields(new String[]{"tel"}, null, false);
	
	static{
		REQUEST_SPECS.add(new RequestSpec<PhoneResult>(
				"virtual.paipai.com/extinfo/GetMobileProductInfo", 
				new cn.edu.zju.ccnt.phone.paipai.RestRequestPramsGeneratorImpl(), 
				new cn.edu.zju.ccnt.phone.paipai.StandardizerImpl(), HttpMethodType.GET));
		
		REQUEST_SPECS.add(new RequestSpec<PhoneResult>(
				"apistore.baidu.com/microservice/mobilephone", 
				new cn.edu.zju.ccnt.phone.baidu.RestRequestPramsGeneratorImpl(), 
				new cn.edu.zju.ccnt.phone.baidu.StandardizerImpl(), HttpMethodType.GET));
	}
	
	public ApiCatagorySpecImpl() {
		super("/phone", true, "phoneCache", false, 0L,
				PARAMS_VALIDATOR, REQUEST_SPECS);
	}

	@Override
	public String generate_idFromRequestMap() {
		Map<String, String> requestMap = getCurrentRequestParams();
		return requestMap.get("tel");
	}

}
