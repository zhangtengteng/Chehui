package com.chehui.activity;
/****
 * 关于页面
 */
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.chehui.ui.base.BaseActivity;
import com.chehui.update.UpdateManager;
import com.example.myproject.R;

public class AboutActivity extends BaseActivity implements OnClickListener {
	//版本升级
	private RelativeLayout rlUpgrade;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		initTitleView(true,R.string.set_about);
		init();
	}
	private void init() {
		rlUpgrade= (RelativeLayout) findViewById(R.id.rlUpgrade);
		rlUpgrade.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rlUpgrade:
			new UpdateManager(this).checkUpdateInfo();
			break;

		default:
			break;
		}
	}


}
