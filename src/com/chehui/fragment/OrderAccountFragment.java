package com.chehui.fragment;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

import com.chehui.adapter.OrderPayAdapter;
import com.chehui.afinal.utils.JSONUtil;
import com.chehui.model.GoodsModel;
import com.chehui.net.CommonData;
import com.chehui.net.OrderAccountRequest;
import com.chehui.ui.base.BaseFragment;
import com.chehui.utils.LogN;
import com.chehui.utils.ToastUtils;
import com.example.myproject.R;

/***
 * 订单统计页面
 * 
 * @author zhangtengteng
 * 
 */
public class OrderAccountFragment extends BaseFragment implements
		OnClickListener {
	// 每次获取多少条数据
	private int pageCount = CommonData.pageSize;
	// 最大页面数
	private int maxPage = 1;
	// 是否正在加载
	private boolean loadFinish = true;
	// 当前页
	private int currentPage;
	// 下一页
	private int nextpage;
	private int tag;
	//1.已消费、0.已支付
	private String flag="0";
	// listview页尾
	private View footer;
	private OrderPayAdapter orderPayAdapter;
	private ListView currentListView;
	private List<GoodsModel> list = new ArrayList<GoodsModel>();
	private ListView lvTabPay, lvTabTime, lvTabConsume;
	private TabHost tabHost;

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 100:
				// 加载数据
				// 刷新
				if (orderPayAdapter != null) {
					orderPayAdapter.notifyDataSetChanged();
					if (currentListView.getFooterViewsCount() > 0)
						currentListView.removeFooterView(footer);
					loadFinish = true;
				} else {
					currentListView.removeFooterView(footer);
					LogN.d(this, "orderPayAdapter is null");
				}
				break;
			// 成功
			case 1:
				dismissWaitDialog();
				int t = msg.arg1;
				LogN.d(this, "根基时间查询订单");
				doAfterSuccessLv1(msg.obj.toString());
				if (t == 2) {// 时间
					orderPayAdapter = new OrderPayAdapter(list, getActivity());
					lvTabTime.setAdapter(orderPayAdapter);
				} else if (t == 0) {// 已支付
					LogN.d(this, "已支付订单");
					orderPayAdapter = new OrderPayAdapter(list, getActivity());
					lvTabPay.setAdapter(orderPayAdapter);
				} else if (t == 1) {// 已消费
					LogN.d(this, "已消费订单");
					orderPayAdapter = new OrderPayAdapter(list, getActivity());
					lvTabConsume.setAdapter(orderPayAdapter);
				} else {
					ToastUtils.showToast(getActivity(), "未知错误！", 2000);
				}
				break;
			// 失败
			case 0:
				dismissWaitDialog();
				ToastUtils.showToast(getActivity(), "获取数据失败！", 2000);
				break;

			default:
				break;
			}
		};
	};

	private void doAfterSuccessLv1(String str) {
		try {
			JSONObject o = new JSONObject(str);
			String result = o.getString("result");
			if ("1".equals(result)) {
				JSONArray dataArray = o.getJSONArray("data");
				for (int i = 0; i < dataArray.length(); i++) {
					JSONObject obj = dataArray.getJSONObject(i);
					GoodsModel model = null;
					try {
						model = (GoodsModel) JSONUtil.fromJsonToJava(obj,
								GoodsModel.class);
					} catch (Exception e) {
						e.printStackTrace();
					}
					list.add(model);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_order_account, container,
				false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		init();
	}

	private void init() {

		RelativeLayout tabPay = (RelativeLayout) LayoutInflater.from(
				getView().getContext()).inflate(
				R.layout.tabhost_select_item_pay, null);

		RelativeLayout tabTime = (RelativeLayout) LayoutInflater.from(
				getView().getContext()).inflate(
				R.layout.tabhost_select_item_time, null);

		RelativeLayout tabConsume = (RelativeLayout) LayoutInflater.from(
				getView().getContext()).inflate(
				R.layout.tabhost_select_item_consume, null);

		// 获取TabHost对象
		tabHost = (TabHost) getView().findViewById(R.id.tabhost);
		// 如果没有继承TabActivity时，通过该种方法加载启动tabHost
		tabHost.setup();
		// tabHost.addTab(tabHost
		// .newTabSpec("tabPlay")
		// .setIndicator(,
		// sgetResource().getDrawable(R.drawable.selector_tab_pay))
		// .setContent(R.id.lv_pay));
		tabHost.addTab(tabHost.newTabSpec("tabPay").setIndicator(tabPay)
				.setContent(R.id.lv_pay));

		tabHost.addTab(tabHost.newTabSpec("tabTime").setIndicator(tabTime)
				.setContent(R.id.lv_time));

		tabHost.addTab(tabHost.newTabSpec("tabConsume")
				.setIndicator(tabConsume).setContent(R.id.lv_consume));

		// tabHost.addTab(tabHost.newTabSpec("tabTime").setIndicator("时间")
		// .setContent(R.id.lv_time));
		// tabHost.addTab(tabHost.newTabSpec("tabTime").setIndicator("已消费")
		// .setContent(R.id.lv_consume));
		footer = getActivity().getLayoutInflater().inflate(
				R.layout.listview_footer, null);

		lvTabPay = (ListView) getView().findViewById(R.id.lv_pay);
		lvTabPay.setOnScrollListener(new ScrollListener());
		lvTabPay.addFooterView(footer);

		lvTabTime = (ListView) getView().findViewById(R.id.lv_time);
		lvTabTime.setOnScrollListener(new ScrollListener());
		lvTabTime.addFooterView(footer);

		lvTabConsume = (ListView) getView().findViewById(R.id.lv_consume);
		lvTabConsume.setOnScrollListener(new ScrollListener());
		lvTabConsume.addFooterView(footer);

		// orderPayAdapter = new OrderPayAdapter(list, this.getActivity());
		// lvTabPay.setAdapter(orderPayAdapter);
		lvTabPay.removeFooterView(footer);

		// orderPayAdapter = new OrderPayAdapter(list, this.getActivity());
		// lvTabTime.setAdapter(orderPayAdapter);
		 lvTabTime.removeFooterView(footer);
		//
		// orderPayAdapter = new OrderPayAdapter(list, this.getActivity());
		// lvTabConsume.setAdapter(orderPayAdapter);
		 lvTabConsume.removeFooterView(footer);

		getData("0", "1", "1", pageCount + "", 0);//支付
		getData("1", "1", "1", pageCount + "", 1);//消费 
		getData("0", "1", "1", pageCount + "", 2);//时间
		// //去掉tabhost默认背景色
		// int i;
		// for (i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
		// tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.WHITE);}
		
		tabHost.setOnTabChangedListener(new OnTabChangeListener() {
			
			@Override
			public void onTabChanged(String tabId) {
				LogN.d(this, "*******************tabId="+tabId);
				if(tabId.equals("tabPay")){
					flag="0";
				}else if(tabId.equals("tabConsume")){
					flag="1";
				}
			}
			
		});
	}

	private void getData(String flag, String time, String nextpage,
			String pageCount, int tag) {
		OrderAccountRequest orderAccountRequest = new OrderAccountRequest(
				handler);
	
		SharedPreferences sharedPreferences = getActivity().getSharedPreferences(
				"chehui",Activity.MODE_PRIVATE);
		
		String name = sharedPreferences.getString("username", "");
		orderAccountRequest.setParams(name, flag, time, nextpage + "",
				pageCount + "", tag);
		orderAccountRequest.sendRequest();
		showWaitDialog(R.string.common_querying);
	}

	@Override
	public void onClick(View v) {
	}

	private final class ScrollListener implements OnScrollListener {

		@Override
		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {
			currentListView = getCurrentListView();
			System.out.println("currentListView=" + currentListView);
			int lastItmeId = currentListView.getLastVisiblePosition();
			if ((lastItmeId + 1) == totalItemCount) {
				// 当前页
				int cp = totalItemCount % pageCount == 0 ? totalItemCount
						/ pageCount : totalItemCount / pageCount + 1;
				LogN.d(this, "当前页="+cp);
				nextpage = currentPage + 1;
				if (nextpage <= maxPage && loadFinish) {
					loadFinish = false;
					System.out.println("添加页尾");
					currentListView.addFooterView(footer);
					Message message = new Message();
					message.what = 100;
					handler.sendMessage(message);
					// 加载数据
				}else{
					LogN.d(getActivity(), "最后一页");
				}
			}
		}

		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {

		}

	}

	/***
	 * 获取当前listview
	 * 
	 * @return
	 */
	private ListView getCurrentListView() {
		String tag = tabHost.getCurrentTabTag();
		ListView listview = null;
		if (tag.equals("tabPay")) {
			listview = lvTabPay;
		} else if (tag.equals("tabTime")) {
			listview = lvTabTime;
		} else if (tag.equals("tabConsume")) {
			listview = lvTabConsume;
		}
		return listview;
	}
}
