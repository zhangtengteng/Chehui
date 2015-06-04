package com.chehui.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.chehui.diy.WiperSwitch;
import com.chehui.diy.WiperSwitch.OnChangedListener;
import com.chehui.ui.base.BaseActivity;
import com.example.myproject.R;

public class MessageSetActivity extends BaseActivity implements OnChangedListener{
//	private TextView topTitle;
//	private ImageButton leftBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message);
		init();
		initTitleView(true,R.string.set_send_message);
	}

	private void init() {
		WiperSwitch wiperSwitch = (WiperSwitch) findViewById(R.id.wiperSwitchMessage);
		wiperSwitch.setChecked(false);
		wiperSwitch.setOnChangedListener(this);
	}

	@Override
	public void OnChanged(WiperSwitch wiperSwitch, boolean checkState) {
		
	}
	
//	@Override
//	protected void initTitleView() {
//		super.initTitleView();
//		leftBtn = (ImageButton) findViewById(R.id.ibLeft);
//		topTitle = (TextView) findViewById(R.id.tvTop);
//
//		leftBtn.setVisibility(View.VISIBLE);
//		topTitle.setText(R.string.set_send_message);
//		leftBtn.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				finish();
//			}
//		});
//	}
}
