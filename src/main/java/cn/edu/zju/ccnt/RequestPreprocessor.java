package cn.edu.zju.ccnt;

import java.util.LinkedList;
import java.util.Map;

import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;

import cn.edu.zju.ccnt.ParamsValidator.ParamsValidationException;

public class RequestPreprocessor extends AbstractMessageTransformer {
//	private static final Logger LOGGER = Logger.getLogger(RequestPreprocessor.class);

	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding)
			throws TransformerException {
		String reqPath = (String)message.getInboundProperty("http.request.path");
//		message.setInvocationProperty("systime", System.currentTimeMillis());
		
		@SuppressWarnings("unchecked")
		Map<String, String> requestParams = (Map<String, String>)message.getInboundProperty("http.query.params");
//		message.setInvocationProperty("requestParams", requestParams);
		
		for(ApiCatagorySpec<? extends ApiResult> spec : ApiCatagorySpecFactory.getApiCatagorySpecs()){
			if(reqPath.equals(spec.getRequestPath())){
				spec.setCurrentRequestParams(requestParams);
				message.setInvocationProperty("apiCatagorySpec", spec);
				message.setInvocationProperty("requestSpecs", new LinkedList<RequestSpec<? extends ApiResult>>(spec.getRequestSpecs()));
				try {
					spec.getParamsValidator().validateWithException(requestParams);
				} catch (ParamsValidationException e) {
					throw new TransformerException(this, e);
				}
			}
		}
		
		return message;
	}
	
}
