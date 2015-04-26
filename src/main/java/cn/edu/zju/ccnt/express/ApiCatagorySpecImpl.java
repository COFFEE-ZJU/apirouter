package cn.edu.zju.ccnt.express;

import java.util.ArrayList;
import java.util.List;
import cn.edu.zju.ccnt.ApiCatagorySpec;
import cn.edu.zju.ccnt.ParamsValidator;
import cn.edu.zju.ccnt.ParamsValidatorFactory;
import cn.edu.zju.ccnt.RequestSpec;
import cn.edu.zju.ccnt.RequestSpec.HttpMethodType;

public class ApiCatagorySpecImpl extends ApiCatagorySpec<ExpressResult>{
	private static final List<RequestSpec<ExpressResult>> REQUEST_SPECS = 
			new ArrayList<RequestSpec<ExpressResult>>();
	private static final ParamsValidator PARAMS_VALIDATOR = 
			ParamsValidatorFactory.generateParamsValidatorByFields(new String[]{"type","trackingNO"}, null, false);
	
	static{
		REQUEST_SPECS.add(new RequestSpec<ExpressResult>(
				"www.kuaidi100.com/query", 
				new cn.edu.zju.ccnt.express.kuaidi100.RestRequestPramsGeneratorImpl(), 
				new cn.edu.zju.ccnt.express.kuaidi100.StandardizerImpl(), HttpMethodType.GET));
	}
	
	public ApiCatagorySpecImpl() {
		super("/express", false, null, false, 0L,
				PARAMS_VALIDATOR, REQUEST_SPECS);
	}

	@Override
	public String generate_idFromRequestMap() {
//		Map<String, String> requestMap = getCurrentRequestParams();
		return null;
	}

}
