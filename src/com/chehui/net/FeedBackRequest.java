package com.chehui.net;

import java.util.ArrayList;
import java.util.List;

import android.os.Handler;
import android.os.Message;

import com.chehui.model.RequestParamsModel;
/**
 * 已经反馈
 *
 */
public class FeedBackRequest extends JSONRequest implements IRequestAction {

	private Handler handler;

	private String member_name;
	private String context;
	public FeedBackRequest(Handler handler) {
		super(handler);
		this.handler = handler;
	}

	public void setParams(String member_name, String context) {
		this.member_name = member_name;
		this.context = context;
	}

	@Override
	public String getAction() {
		return APPMEMBERACTION_LOGIN;
	}

	@Override
	protected void onHttpSuccess(String str) {
		Message msg = new Message();
		msg.what = CommonData.HTTP_HANDLE_SUCCESS;
		msg.obj = str;
		handler.sendMessage(msg);

	}

	@Override
	protected void onHttpFailure(int errorCode, String why) {
		Message msg = new Message();
		msg.what = CommonData.HTTP_HANDLE_FAILE;
		msg.obj = why;
		handler.sendMessage(msg);
	}

	@Override
	protected List<RequestParamsModel> getParamList() {
		List<RequestParamsModel> list = new ArrayList<RequestParamsModel>();
		RequestParamsModel model1 = new RequestParamsModel();
		model1.setKey("member_name");
		model1.setValue(member_name);
		list.add(model1);

		RequestParamsModel model2 = new RequestParamsModel();
		model2.setKey("context");
		model2.setValue(context);
		list.add(model2);
		return list;
	}

}
