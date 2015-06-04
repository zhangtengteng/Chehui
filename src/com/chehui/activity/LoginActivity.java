package com.chehui.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.chehui.adapter.OptionsAdapter;
import com.chehui.diy.EditTextWithDel;
import com.chehui.net.LoginRequest;
import com.chehui.ui.base.BaseActivity;
import com.chehui.utils.LogN;
import com.chehui.utils.ToastUtils;
import com.example.myproject.R;

public class LoginActivity extends BaseActivity {

	private EditTextWithDel etPwd;
	private RelativeLayout rlLogin;
	private String username;
	private String pwd;
	private Button btnLogin;
	// PopupWindow对象
	private PopupWindow selectPopupWindow = null;
	// 自定义Adapter
	private OptionsAdapter optionsAdapter = null;
	// 下拉框选项数据源
	private ArrayList<String> datas = new ArrayList<String>();;
	// 下拉框依附组件
	private LinearLayout parent;
	// 下拉框依附组件宽度，也将作为下拉框的宽度
	private int pwidth;
	// 用户名文本框
	private EditTextWithDel etUser;
	// 下拉箭头图片组件
	private ImageView ivSelect;
	// 恢复数据源按钮
	private Button button;
	// 展示所有下拉选项的ListView
	private ListView listView = null;
	// 是否初始化完成标志
	private boolean flag = false;

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			Bundle data = msg.getData();
			switch (msg.what) {
			case 100:
				// 选中下拉项，下拉框消失
				int selIndex = data.getInt("selIndex");
				etUser.setText(datas.get(selIndex));
				dismiss();
				break;
			case 200:
				// 移除下拉项数据
				int delIndex = data.getInt("delIndex");
				datas.remove(delIndex);
				// 刷新下拉列表
				optionsAdapter.notifyDataSetChanged();
				break;
			case 0:// 登陆失败
				dismissWaitDialog();
				ToastUtils.showShortToast(LoginActivity.this,
						msg.obj.toString());
				break;
			case 1:// 登陆成功
				dismissWaitDialog();
				// SharedPreManager.getInstance().setString("username",username);
			
				System.out.println("=====================username="+username);
				// 实例化SharedPreferences对象（第一步）
				SharedPreferences mySharedPreferences = getSharedPreferences(
						"chehui", Activity.MODE_PRIVATE);
				// 实例化SharedPreferences.Editor对象（第二步）
				SharedPreferences.Editor editor = mySharedPreferences.edit();
				// 用putString的方法保存数据
				editor.putString("username", username);
				editor.commit();
				SharedPreferences sharedPreferences = getSharedPreferences(
						"chehui", Activity.MODE_PRIVATE);
				// 使用getString方法获得value，注意第2个参数是value的默认值
				String name = sharedPreferences.getString("username", "");
//				 System.out.println("****************************name="+name);
//				// 提交当前数据
				Intent intent = new Intent(LoginActivity.this,
						MainActivity.class);
				startActivity(intent);
				finish();
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		init();
	}

	/**
	 * 没有在onCreate方法中调用initWedget()，而是在onWindowFocusChanged方法中调用，
	 * 是因为initWedget()中需要获取PopupWindow浮动下拉框依附的组件宽度，在onCreate方法中是无法获取到该宽度的
	 */
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		while (!flag) {
			initWedget();
			flag = true;
		}

	}

	private void initWedget() {
		ivSelect = (ImageView) findViewById(R.id.iv_select);
		parent = (LinearLayout) findViewById(R.id.ll_user);
		// 获取下拉框依附的组件宽度
		int width = parent.getWidth();
		pwidth = width;
		// 初始化PopupWindow
		initPopuWindow();
		// 设置点击下拉箭头图片事件，点击弹出PopupWindow浮动下拉框
		ivSelect.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (flag) {
					// 显示PopupWindow窗口
					popupWindwShowing();
				}
			}
		});

	}

	private void init() {
		btnLogin = (Button) findViewById(R.id.btn_login);
		btnLogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				etUser.setText("admin");
				etPwd.setText("admin888");

				username = etUser.getText().toString();
				if (etUser.getText().toString() == null
						|| etUser.getText().length() <= 0) {
					ToastUtils.showShortToast(LoginActivity.this, "用户名不能为空");
					return;
				}

				if (etPwd.getText().toString() == null
						|| etPwd.getText().length() <= 0) {
					ToastUtils.showShortToast(LoginActivity.this, "验证码不能为空");
					return;
				}

				// if (Utils.isMobileNO(etUser.getText().toString())) {
				LoginRequest loginRequest = new LoginRequest(handler);
				loginRequest.setParams(etUser.getText().toString(), etPwd
						.getText().toString());
				loginRequest.sendRequest();
				showWaitDialog(R.string.common_logining);

				// Intent intent = new Intent(LoginActivity.this,
				// MainActivity.class);
				// startActivity(intent);
				// finish();
				// } else {
				// ToastUtils.showShortToast(LoginActivity.this, "请输入正确的手机号码");
				// }

			}
		});
		// 动态改变光标的位置
		rlLogin = (RelativeLayout) findViewById(R.id.rl_login);
		etUser = (EditTextWithDel) findViewById(R.id.et_userName);
		etPwd = (EditTextWithDel) findViewById(R.id.et_pwd);

		etUser.post(new Runnable() {

			@Override
			public void run() {
				etUser.setSelection(0);
				// etPwd.setSelection(0);
			}
		});

	}

	/**
	 * 初始化填充Adapter所用List数据
	 */
	private void initDatas() {

		datas.clear();

		datas.add("北京");
		datas.add("上海");
		datas.add("广州");
		datas.add("深圳");
		datas.add("重庆");
		datas.add("青岛");
		datas.add("石家庄");
	}

	/**
	 * 初始化PopupWindow
	 */
	private void initPopuWindow() {

		initDatas();

		// PopupWindow浮动下拉框布局
		View loginwindow = (View) this.getLayoutInflater().inflate(
				R.layout.options, null);
		listView = (ListView) loginwindow.findViewById(R.id.list);

		// 设置自定义Adapter
		optionsAdapter = new OptionsAdapter(this, handler, datas);
		listView.setAdapter(optionsAdapter);

		selectPopupWindow = new PopupWindow(loginwindow, pwidth,
				LayoutParams.WRAP_CONTENT, true);

		selectPopupWindow.setOutsideTouchable(true);

		// 这一句是为了实现弹出PopupWindow后，当点击屏幕其他部分及Back键时PopupWindow会消失，
		// 没有这一句则效果不能出来，但并不会影响背景
		// 本人能力极其有限，不明白其原因，还望高手、知情者指点一下
		selectPopupWindow.setBackgroundDrawable(new BitmapDrawable());
	}

	/**
	 * 显示PopupWindow窗口
	 * 
	 * @param popupwindow
	 */
	public void popupWindwShowing() {
		// 将selectPopupWindow作为parent的下拉框显示，并指定selectPopupWindow在Y方向上向上偏移3pix，
		// 这是为了防止下拉框与文本框之间产生缝隙，影响界面美化
		// （是否会产生缝隙，及产生缝隙的大小，可能会根据机型、Android系统版本不同而异吧，不太清楚）
		selectPopupWindow.showAsDropDown(parent, 0, -3);
	}

	/**
	 * PopupWindow消失
	 */
	public void dismiss() {
		selectPopupWindow.dismiss();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		LogN.d(this, "LoginActivity onDestroy");
	}
}
