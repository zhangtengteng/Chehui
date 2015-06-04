package com.chehui.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.chehui.ui.base.BaseActivity;
import com.example.myproject.R;

public class AccountManagerActivity extends BaseActivity implements
		OnClickListener {
//	private TextView topTitle;
//	private ImageButton leftBtn;
	private TextView tvAddAccount;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manager_account);
		init();
		initTitleView(true,R.string.set_account);
	}

	private void init() {
		tvAddAccount = (TextView) findViewById(R.id.tv_add_account);
		tvAddAccount.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_add_account:
			activityManager.getInstance().startNextActivity(
					AddAccountActivity.class);
			break;

		default:
			break;
		}
	}

//	@Override
//	protected void initTitleView() {
//		super.initTitleView();
//		leftBtn = (ImageButton) findViewById(R.id.ibLeft);
//		topTitle = (TextView) findViewById(R.id.tvTop);
//
//		leftBtn.setVisibility(View.VISIBLE);
//		topTitle.setText(R.string.set_account);
//		leftBtn.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				finish();
//			}
//		});
//	}
}
