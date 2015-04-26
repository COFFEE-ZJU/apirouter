package cn.edu.zju.ccnt.flight.c2c;

import java.util.ArrayList;
import java.util.List;
import cn.edu.zju.ccnt.ApiCatagorySpec;
import cn.edu.zju.ccnt.ParamsValidator;
import cn.edu.zju.ccnt.ParamsValidatorFactory;
import cn.edu.zju.ccnt.RequestSpec;
import cn.edu.zju.ccnt.RequestSpec.HttpMethodType;

public class ApiCatagorySpecImpl extends ApiCatagorySpec<City2CityResult>{
	private static final List<RequestSpec<City2CityResult>> REQUEST_SPECS = 
			new ArrayList<RequestSpec<City2CityResult>>();
	private static final ParamsValidator PARAMS_VALIDATOR = 
			ParamsValidatorFactory.generateParamsValidatorByFields(new String[]{"date","from","to"}, null, false);
	
	static{
		REQUEST_SPECS.add(new RequestSpec<City2CityResult>(
				"api.open.baidu.com/pae/channel/data/asyncqury", 
				new cn.edu.zju.ccnt.flight.c2c.baidu.RestRequestPramsGeneratorImpl(), 
				new cn.edu.zju.ccnt.flight.c2c.baidu.StandardizerImpl(), HttpMethodType.GET));
	}
	
	public ApiCatagorySpecImpl() {
		super("/flight/c2c", false, null, false, 0L,
				PARAMS_VALIDATOR, REQUEST_SPECS);
	}

	@Override
	public String generate_idFromRequestMap() {
//		Map<String, String> requestMap = getCurrentRequestParams();
		return null;
	}

}
