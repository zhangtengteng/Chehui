package com.chehui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.chehui.manage.comm.SharedPreManager;
import com.chehui.manager.comm.ActivityManager;
import com.chehui.ui.base.BaseActivity;
import com.example.myproject.R;

public class LoadingActivity extends BaseActivity {
	private String userName;
	private String passWord;
	private final int MESSAGESUCCESS = 0;
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				ActivityManager.getInstance().startNextActivity(
						LoginActivity.class);
				LoadingActivity.this.finish();
				break;

			default:
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loading);
		showLoading();
	}

	private void showLoading() {
		userName = SharedPreManager.getInstance().getString("userName", null);
		passWord = SharedPreManager.getInstance().getString("passWord", null);
		if (userName != null || passWord != null) {
			
		} else {
			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						Thread.sleep(1000);
						handler.sendEmptyMessage(MESSAGESUCCESS);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
	}
}
