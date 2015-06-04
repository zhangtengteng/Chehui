package com.chehui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chehui.activity.AboutActivity;
import com.chehui.activity.AccountManagerActivity;
import com.chehui.activity.AddAccountActivity;
import com.chehui.activity.FeedBackActivity;
import com.chehui.activity.LoginActivity;
import com.chehui.activity.MessageSetActivity;
import com.chehui.manage.comm.SharedPreManager;
import com.chehui.manager.comm.ActivityManager;
import com.chehui.ui.base.BaseFragment;
import com.example.myproject.R;

public class SetFragment extends BaseFragment implements OnClickListener {
	// abount
	private LinearLayout llSetAbout;
	// 账号管理
	private LinearLayout llAccountManager;
	// 消息推送
	private LinearLayout llSendMessage;
	// 意见反馈
	private LinearLayout llFeedBack;
	private Button btnLoginOut;
	private Intent intent;

	private TextView titleTextView;
	private ImageButton backBtn;
	private int topTitle;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		return inflater.inflate(R.layout.fragment_set, container, false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		init();
	}

	private void initTopView(int id) {
		backBtn = (ImageButton) getView().findViewById(R.id.ibLeft);
		backBtn.setVisibility(View.VISIBLE);
		titleTextView = (TextView) getView().findViewById(R.id.tvTop);
		titleTextView.setText(id);
	}

	private void init() {
		llSetAbout = (LinearLayout) getView().findViewById(R.id.llSetAbout);
		llSetAbout.setOnClickListener(this);

		llAccountManager = (LinearLayout) getView().findViewById(
				R.id.llAccountManage);
		llAccountManager.setOnClickListener(this);

		llSendMessage = (LinearLayout) getView().findViewById(
				R.id.llSendMessage);
		llSendMessage.setOnClickListener(this);

		llFeedBack = (LinearLayout) getView().findViewById(R.id.llFeedBack);
		llFeedBack.setOnClickListener(this);

		btnLoginOut = (Button) getView().findViewById(R.id.btn_login_out);
		btnLoginOut.setOnClickListener(this);

	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		// 关于页面
		case R.id.llSetAbout:
			intent = new Intent(getActivity(), AboutActivity.class);
			startActivity(intent);
			break;
		// 账号管理
		case R.id.llAccountManage:
			intent = new Intent(getActivity(), AccountManagerActivity.class);
			startActivity(intent);
			break;
		// 消息推送
		case R.id.llSendMessage:
			intent = new Intent(getActivity(), MessageSetActivity.class);
			startActivity(intent);
			break;
		// 意见反馈
		case R.id.llFeedBack:
			intent = new Intent(getActivity(), FeedBackActivity.class);
			startActivity(intent);
			break;
		// 注销
		case R.id.btn_login_out:
			SharedPreManager.getInstance().setString("username", "");
			intent = new Intent(getActivity(), LoginActivity.class);
//			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
//					| Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
			System.exit(0);
			break;
		default:
			break;
		}
	}

}
