package com.chehui.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.chehui.ui.base.BaseActivity;
import com.example.myproject.R;

public class AddAccountActivity extends BaseActivity {
//	private TextView topTitle;
//	private ImageButton leftBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_account);
		initTitleView(true,R.string.add_account);
	}

//	@Override
//	protected void initTitleView() {
//		super.initTitleView();
//		leftBtn = (ImageButton) findViewById(R.id.ibLeft);
//		topTitle = (TextView) findViewById(R.id.tvTop);
//
//		leftBtn.setVisibility(View.VISIBLE);
//		topTitle.setText(R.string.add_account);
//		leftBtn.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				finish();
//			}
//		});
//	}

}
