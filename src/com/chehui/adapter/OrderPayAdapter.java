package com.chehui.adapter;

import java.util.List;

import com.chehui.activity.OrderDetailActivity;
import com.chehui.manager.comm.ActivityManager;
import com.chehui.manager.comm.FragmentsManager;
import com.chehui.model.GoodsModel;
import com.chehui.utils.LogN;
import com.chehui.utils.ToastUtils;
import com.example.myproject.R;

import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo.DetailedState;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class OrderPayAdapter extends BaseAdapter {
	private List<GoodsModel> list;
	private LayoutInflater inflater;
	private Context context;
	public OrderPayAdapter(List list, Context context) {
		super();
		this.list = list;
		this.context = context;
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.listview_order_item, null);
		}
		final GoodsModel goodsModel= list.get(position);
		
		// 状态
		TextView tvOrderState = (TextView) convertView
				.findViewById(R.id.tv_order_state);
		String state = goodsModel.getPayState();
		LogN.d(this, "*******************state="+state);
		if(state.equals("1")){
			tvOrderState.setText("已消费");
		}else if(state.equals("0")){
			tvOrderState.setText("已支付");
		}
		
		// 标题
		TextView tvOrderTitle = (TextView) convertView
				.findViewById(R.id.tv_order_title);
		tvOrderTitle.setText(goodsModel.getTitle());
		
		
		// 金额：19:00元 ●数量：10件
		TextView tvOrderMoney = (TextView) convertView
				.findViewById(R.id.tv_order_money);
		tvOrderMoney.setText(" 金额："+goodsModel.getOrderPrice()+"元 ●数量："+goodsModel.getOrderNum()+"件");
		
		
		// 手机号
		TextView tvOrderPhone = (TextView) convertView
				.findViewById(R.id.tv_order_phone);
		tvOrderPhone.setText(goodsModel.getSMSTel());
		
		
		// 订单号
		TextView tvOrderNumber = (TextView) convertView
				.findViewById(R.id.tv_order_number);
		tvOrderNumber.setText(goodsModel.getOID());
		// 时间
		TextView tvOrderTime = (TextView) convertView
				.findViewById(R.id.tv_order_time);
		LogN.d(this, "goodsModel.getPayTime()="+goodsModel.getPayTime());
		tvOrderTime.setText(goodsModel.getPayTime());

		
		// 点击列表进入订单详情页面
		convertView.findViewById(R.id.rl_order_item).setOnClickListener( new OnClickListener() {
			@Override
			public void onClick(View v) {
				Bundle bundle = new Bundle();
				Intent intent = new Intent(context, OrderDetailActivity.class);
				intent.putExtra("goodsModel", goodsModel);
				context.startActivity(intent);
			}
		});
		return convertView;
	}

}
