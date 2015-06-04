package com.chehui.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;

import com.chehui.adapter.OrderPayAdapter;
import com.chehui.model.GoodsModel;
import com.chehui.ui.base.BaseActivity;
import com.example.myproject.R;
/***
 * 订单核验详情
 * @author zhangtengteng
 *
 */
public class OrderCheckDetailActivity extends BaseActivity {
	private ListView lv;
	private List<GoodsModel> list = new ArrayList<GoodsModel>();
	private ImageButton ibLeft;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_check_detail);
		init();
	}

	private void init() {
		Bundle bundle = this.getIntent().getExtras();
		ArrayList list2 = bundle.getParcelableArrayList("list");
		list=list2;
		lv = (ListView) findViewById(R.id.lvOrderCheckDetail);
		lv.setAdapter(new OrderPayAdapter(list, this));
		
		ibLeft=(ImageButton) findViewById(R.id.ibLeft);
		ibLeft.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		init();
	}
}
