package cn.edu.zju.ccnt.zheng.test;

import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;

public class Transformer extends AbstractMessageTransformer{

	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding)
			throws TransformerException {
		// TODO Auto-generated method stub
		System.out.println("This is in Transformer Class!");
		System.out.println(message.getPayload());
		System.out.println("end!");
		return message;
	}

}
