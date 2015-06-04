package com.chehui.fragment;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.chehui.activity.LoginActivity;
import com.chehui.activity.MainActivity;
import com.chehui.activity.OrderCheckDetailActivity;
import com.chehui.afinal.utils.JSONUtil;
import com.chehui.diy.EditTextWithDel;
import com.chehui.model.GoodsModel;
import com.chehui.net.OrderAccountRequest;
import com.chehui.net.OrderCheckRequest;
import com.chehui.ui.base.BaseFragment;
import com.chehui.utils.ToastUtils;
import com.chehui.utils.Utils;
import com.example.myproject.R;
/***
 * 订单核验
 * @author zhangtengteng
 *
 */
public class OrderCheckFragment extends BaseFragment {
	private Button btnCheck;
	private EditTextWithDel etwPhone;
	private EditTextWithDel etwPwd;
	private List<GoodsModel> list = new ArrayList<GoodsModel>();
	private Handler handler= new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				dismissWaitDialog();
				ToastUtils.showToast(getActivity(), "获取数据失败！", 2000);
				break;
			case 1:
				dismissWaitDialog();
				 doAfterSuccessLv1(msg.obj.toString());
				 Intent intent = new Intent(getActivity(),OrderCheckDetailActivity.class);
				 Bundle bundle=new Bundle();
				 bundle.putParcelableArrayList("list", (ArrayList)list);
				 intent.putExtras(bundle);
				 startActivity(intent);
				break;

			default:
				break;
			}
		}
	};
	
	private void doAfterSuccessLv1(String str) {
		try {
			JSONObject o = new JSONObject(str);
			String result = o.getString("result");
			if ("1".equals(result)) {
				JSONArray dataArray = o.getJSONArray("data");
				for (int i = 0; i < dataArray.length(); i++) {
					JSONObject obj = dataArray.getJSONObject(i);
					GoodsModel model = null;
					try {
						model = (GoodsModel) JSONUtil.fromJsonToJava(obj,
								GoodsModel.class);
					} catch (Exception e) {
						e.printStackTrace();
					}
					list.add(model);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater
				.inflate(R.layout.fragment_order_check, container, false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		init();
	}

	private void init() {
		etwPhone=(EditTextWithDel) getView().findViewById(R.id.etOrderCheckPhone);
		etwPwd=(EditTextWithDel) getView().findViewById(R.id.etOrderCheckPwd);
		btnCheck=(Button) getView().findViewById(R.id.btn_order_check);
		btnCheck.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				etwPhone.setText("13813887799");
				etwPwd.setText("111");
				if (etwPhone.getText().toString() == null
						|| etwPhone.getText().length() <= 0) {
					ToastUtils.showShortToast(getActivity(), "手机号码不能为空");
					return;
				}

				if (etwPwd.getText().toString() == null
						|| etwPwd.getText().length() <= 0) {
					ToastUtils.showShortToast(getActivity(), "验证码不能为空");
					return;
				}

				if (Utils.isMobileNO(etwPhone.getText().toString())) {
				} else {
					ToastUtils.showShortToast(getActivity(), "请输入正确的手机号码");
				}
				OrderCheckRequest orderCheckRequest = new OrderCheckRequest(handler);
				orderCheckRequest.setParams(etwPhone.getText().toString().trim(), etwPwd.getText().toString().trim());
				orderCheckRequest.sendRequest();
				showWaitDialog(R.string.common_querying);
			}
		});
	}

}
