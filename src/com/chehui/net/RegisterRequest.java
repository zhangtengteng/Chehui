package com.chehui.net;

import java.util.ArrayList;
import java.util.List;

import android.os.Handler;
import android.os.Message;

import com.chehui.model.RequestParamsModel;

/**
 * 注册
 * 
 */
public class RegisterRequest extends JSONRequest implements IRequestAction {
	// 01-18 00:49:57.639: I/System.out(17935):
	// JSONRequest==url==http://812860598-com.f3322.net:8081/yg/admin/appMemberAction_register.do?member_name=13357736225&psw=123456&
	// 01-18 00:49:57.859: I/System.out(17935):
	// JSONRequest==result==post{"result":1,"data":{"dataflag":0,"birthday":null,"sex":"","zip_code":"","remark":"","psw":"","identity_card":"","city":"","member_id":"5babb68fb3ba47408af48e509c24f281","createtime":null,"login_time":null,"addr_detail":"","county":"","email":"","real_name":"","register_type":"","province":"","member_name":"13357736225","telephone":"","mobile":""},"msg":"操作成功"}

	private Handler handler;

	private String member_name;
	private String psw;

	public RegisterRequest(Handler handler) {
		super(handler);
		this.handler = handler;
	}

	public void setParams(String member_name, String psw) {
		this.member_name = member_name;
		this.psw = psw;
	}

	@Override
	public String getAction() {
		return APPMEMBERACTION_ORDERPAY;
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
		model2.setKey("psw");
		model2.setValue(psw);
		list.add(model2);
		return list;
	}
}
