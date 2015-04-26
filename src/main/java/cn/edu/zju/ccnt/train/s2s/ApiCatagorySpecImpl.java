package cn.edu.zju.ccnt.train.s2s;

import java.util.ArrayList;
import java.util.List;
import cn.edu.zju.ccnt.ApiCatagorySpec;
import cn.edu.zju.ccnt.ParamsValidator;
import cn.edu.zju.ccnt.ParamsValidatorFactory;
import cn.edu.zju.ccnt.RequestSpec;
import cn.edu.zju.ccnt.RequestSpec.HttpMethodType;

public class ApiCatagorySpecImpl extends ApiCatagorySpec<Station2StationResult>{
	private static final List<RequestSpec<Station2StationResult>> REQUEST_SPECS = 
			new ArrayList<RequestSpec<Station2StationResult>>();
	private static final ParamsValidator PARAMS_VALIDATOR = 
			ParamsValidatorFactory.generateParamsValidatorByFields(new String[]{"date","from","to"}, null, false);
	
	static{
		REQUEST_SPECS.add(new RequestSpec<Station2StationResult>(
				"m.tieyou.com/jy/index.php", 
				new cn.edu.zju.ccnt.train.s2s.tieyou.RestRequestPramsGeneratorImpl(), 
				new cn.edu.zju.ccnt.train.s2s.tieyou.StandardizerImpl(), HttpMethodType.GET));
	}
	
	public ApiCatagorySpecImpl() {
		super("/train/s2s", false, null, false, 0L,
				PARAMS_VALIDATOR, REQUEST_SPECS);
	}

	@Override
	public String generate_idFromRequestMap() {
//		Map<String, String> requestMap = getCurrentRequestParams();
		return null;
	}

}
