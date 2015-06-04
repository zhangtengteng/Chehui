package com.chehui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.chehui.manager.comm.FragmentsManager;
import com.example.myproject.R;

public class MainActivity extends FragmentActivity implements OnClickListener {
	private TextView titleTextView;
	private ImageButton backBtn;
	private int topTitle;
	// 定义一个变量，来标识是否退出
	private static boolean isExit = false;
	// public static Fragment[] fragments;
	private static FragmentManager fMgr;
	// 当前显示页面
	public static String TAG = "order_check";
	// 用于查找回退栈中的fragment，findFragmentByTag
	public static final String ORDER_CHECK = "order_check";
	public static final String ORDER_COUNT = "order_count";
	public static final String MESSAGE = "message";
	public static final String SET = "set";

	Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			isExit = false;
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 获取FragmentManager实例
		fMgr = getSupportFragmentManager();
		FragmentsManager.getInstance().setFragmentManager(fMgr);
		initFragment();
		initTopView(R.string.main_order_check);
	}

	private void initTopView(int id) {
		backBtn = (ImageButton) findViewById(R.id.ibLeft);
		backBtn.setVisibility(View.INVISIBLE);
		titleTextView = (TextView) findViewById(R.id.tvTop);
		titleTextView.setText(id);
	}

	/**
	 * 初始化首个Fragment
	 */
	private void initFragment() {
		findViewById(R.id.rbOrderCheck).setOnClickListener(this);
		findViewById(R.id.rbOrderCount).setOnClickListener(this);
		findViewById(R.id.rbMessage).setOnClickListener(this);
		findViewById(R.id.rbSet).setOnClickListener(this);

		// fragments = new Fragment[4];

		FragmentsManager.getInstance()
				.addFragment(
						getSupportFragmentManager().findFragmentById(
								R.id.fgOrderCheck), ORDER_CHECK);

		FragmentsManager.getInstance()
				.addFragment(
						getSupportFragmentManager().findFragmentById(
								R.id.fgOrderCount), ORDER_COUNT);

		FragmentsManager.getInstance().addFragment(
				getSupportFragmentManager().findFragmentById(R.id.fgMessage),
				MESSAGE);

		FragmentsManager.getInstance().addFragment(
				getSupportFragmentManager().findFragmentById(R.id.fgSet), SET);

		FragmentsManager.getInstance().changeFragment(ORDER_CHECK);
	}

	@Override
	public void onClick(View view) {

		switch (view.getId()) {
		case R.id.rbOrderCheck:
			TAG = ORDER_CHECK;
			topTitle = R.string.main_order_check;
			break;
		case R.id.rbOrderCount:
			TAG = ORDER_COUNT;
			topTitle = R.string.main_order_count;
			break;
		case R.id.rbMessage:
			TAG = MESSAGE;
			topTitle = R.string.main_message;
			break;
		case R.id.rbSet:
			TAG = SET;
			topTitle = R.string.main_set;
			break;
		default:
			break;
		}
		initTopView(topTitle);
		FragmentsManager.getInstance().changeFragment(TAG);

		//
		// fMgr.beginTransaction().hide(fragments[0]).hide(fragments[1])
		// .hide(fragments[2]).hide(fragments[3]).show(fragments[index])
		// .commit();
		// 江苏省南京市秦淮区中华路50号江苏经贸国际大厦15楼5
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			exit();
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}

	private void exit() {
		if (!isExit) {
			isExit = true;
			Toast.makeText(getApplicationContext(), "再按一次退出程序",
					Toast.LENGTH_SHORT).show();
			// 利用handler延迟发送更改状态信息
			mHandler.sendEmptyMessageDelayed(0, 2000);
		} else {
			finish();
			System.exit(0);
		}
	}
}
