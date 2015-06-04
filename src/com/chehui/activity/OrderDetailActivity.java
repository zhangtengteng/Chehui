package com.chehui.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.chehui.model.GoodsModel;
import com.chehui.ui.base.BaseActivity;
import com.example.myproject.R;

public class OrderDetailActivity extends BaseActivity {
	private ImageView ivTopIcon;
	private TextView tvTopTitle;
	private TextView tvState;
	private TextView tvNumber;
	private TextView tvCount;
	private TextView tvPrice;
	private TextView tvPhone;
	private TextView tvIntroduction;
	private TextView tvPayTime;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_detail);
		initTitleView(true,R.string.order_detail);
		init();
	}

	private void init() {
		Bundle extras = getIntent().getExtras();
		GoodsModel goodsModel = (GoodsModel) extras.get("goodsModel");
		
		
		ivTopIcon=(ImageView) findViewById(R.id.iv_top_icon);
		
		tvTopTitle=(TextView) findViewById(R.id.tv_top_title);
		tvTopTitle.setText(goodsModel.getTitle());
		
		tvState=(TextView) findViewById(R.id.tv_order_detial_state);
		if(goodsModel.getPayState().equals("1")){
			tvState.setText("已消费");
		}else if(goodsModel.getPayState().equals("0")){
			tvState.setText("已支付");
		}
		
		
		tvNumber=(TextView) findViewById(R.id.tv_order_detial_number);
		tvNumber.setText(goodsModel.getOID());
		
		
		tvCount=(TextView) findViewById(R.id.tv_order_detial_count);
		tvCount.setText(goodsModel.getOrderNum()+"件");
		
		
		tvPrice=(TextView) findViewById(R.id.tv_order_detial_price);
		tvPrice.setText(goodsModel.getOrderPrice()+"元");
		
		tvPhone=(TextView) findViewById(R.id.tv_order_detial_phone);
		tvPhone.setText(goodsModel.getSMSTel());
		
		tvIntroduction=(TextView) findViewById(R.id.tv_order_detial_introduction);
		tvIntroduction.setText(goodsModel.getIntro());
		
		tvPayTime=(TextView) findViewById(R.id.tv_order_detial_pay_time);
		tvPayTime.setText(goodsModel.getPayTime());
	}
	
	
}
