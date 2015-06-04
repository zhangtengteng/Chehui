package com.chehui.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.chehui.ui.base.BaseActivity;
import com.example.myproject.R;

public class FeedBackActivity extends BaseActivity {
	private TextView tvFeedBack;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feedback);
		initTitleView(true,R.string.set_feedback);
		init();
	}
	private void init() {
		tvFeedBack=(TextView) findViewById(R.id.tv_feedback);
	}
}
