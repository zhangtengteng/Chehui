package com.chehui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chehui.activity.AboutActivity;
import com.chehui.activity.AddAccountActivity;
import com.chehui.manager.comm.ActivityManager;
import com.chehui.ui.base.BaseFragment;
import com.example.myproject.R;

public class MessageFragment extends BaseFragment implements OnClickListener{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		return inflater.inflate(R.layout.activity_message, container, false);
	}
	
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		init();
	}
	
	private void init() {
		
	}
	
	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.llSetAbout:
			break;
		case R.id.llAccountManage:
			break;
		default:
			break;
		}
	}
	
	
	
}
