package cn.edu.zju.ccnt;

public class ErrorResultGenerator extends ResultStandardizer {

	@Override
	protected ApiResult standardize(Object input) throws Exception {
		
		return new ErrorResult("500", "failed to invoke APIs.");
	}

}
