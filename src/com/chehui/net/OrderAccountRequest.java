package com.chehui.net;

import java.util.ArrayList;
import java.util.List;

import android.os.Handler;
import android.os.Message;

import com.chehui.model.RequestParamsModel;
/**
 * 订单统计
 *
 */
public class OrderAccountRequest extends JSONRequest implements IRequestAction {
//http://192.168.4.252:8081/appMemberAction_orderPay.ashx?member_name=admin&flag=1&time=1&pageNumber=1&pageCount=10
	private Handler handler;

	private String member_name;
	private String flag;//1.已消费、0.已支付
	private String time;//0.全部、1.一个月之内2.一个星期之内
	private String pageNumber;// 第几页
	private String pageCount;// 一下显示几个
	
	private int  tag;//1.已支付 ；0.一个月 ；2.已消费
	public OrderAccountRequest(Handler handler) {
		super(handler);
		this.handler = handler;
	}

	public void setParams(String member_name, String flag,String time,String pageNumber,String pageCount,int tag) {
		this.member_name = member_name;
		this.flag = flag;
		this.time = time;
		this.pageNumber = pageNumber;
		this.pageCount = pageCount;
		this.tag=tag;
	}

	@Override
	public String getAction() {
		return APPMEMBERACTION_ORDERPAY;
	}

	@Override
	protected void onHttpSuccess(String str) {
		Message msg = new Message();
		msg.what = CommonData.HTTP_HANDLE_SUCCESS;
		msg.arg1=tag;
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
		model2.setKey("flag");
		model2.setValue(flag);
		list.add(model2);
		
		RequestParamsModel model3 = new RequestParamsModel();
		model3.setKey("time");
		model3.setValue(time);
		list.add(model3);
		
		RequestParamsModel model4 = new RequestParamsModel();
		model4.setKey("pageNumber");
		model4.setValue(pageNumber);
		list.add(model4);
		
		RequestParamsModel model5 = new RequestParamsModel();
		model5.setKey("pageCount");
		model5.setValue(pageCount);
		list.add(model5);
		
		
		return list;
	}

}
