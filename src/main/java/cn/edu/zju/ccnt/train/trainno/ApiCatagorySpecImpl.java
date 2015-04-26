package cn.edu.zju.ccnt.train.trainno;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.edu.zju.ccnt.ApiCatagorySpec;
import cn.edu.zju.ccnt.ParamsValidator;
import cn.edu.zju.ccnt.ParamsValidatorFactory;
import cn.edu.zju.ccnt.RequestSpec;
import cn.edu.zju.ccnt.RequestSpec.HttpMethodType;

public class ApiCatagorySpecImpl extends ApiCatagorySpec<TrainNumberInfoResult>{
	private static final List<RequestSpec<TrainNumberInfoResult>> REQUEST_SPECS = 
			new ArrayList<RequestSpec<TrainNumberInfoResult>>();
	private static final ParamsValidator PARAMS_VALIDATOR = 
			ParamsValidatorFactory.generateParamsValidatorByFields(new String[]{"date","trainno"}, null, false);
	
	static{
		REQUEST_SPECS.add(new RequestSpec<TrainNumberInfoResult>(
				"m.tieyou.com/jy/index.php", 
				new cn.edu.zju.ccnt.train.trainno.tieyou.RestRequestPramsGeneratorImpl(), 
				new cn.edu.zju.ccnt.train.trainno.tieyou.StandardizerImpl(), HttpMethodType.GET));
	}
	
	public ApiCatagorySpecImpl() {
		super("/train/trainno", true, "trainnoCache", false, 0L,
				PARAMS_VALIDATOR, REQUEST_SPECS);
	}

	@Override
	public String generate_idFromRequestMap() {
		Map<String, String> requestMap = getCurrentRequestParams();
		return requestMap.get("date") + "&" + requestMap.get("trainno");
	}

}
