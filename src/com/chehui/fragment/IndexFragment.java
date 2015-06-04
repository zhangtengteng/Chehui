package com.chehui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myproject.R;

public class IndexFragment extends Fragment {
	public static final String ORDER_CHECK = "order_check";
	public static final String ORDER_COUNT = "order_count";
	public static final String MESSAGE = "message";
	public static final String SET = "set";

	public static final String TAG = "IndexFragment";

	public static final String SHOW_TEXT = "show_text";

	public View mTotalView;

	public IndexFragment() {

	}

	public static IndexFragment newInstance(String showText) {

		IndexFragment mIndexFragment = new IndexFragment();

		Bundle bundle = new Bundle();
		bundle.putString(SHOW_TEXT, showText);
		mIndexFragment.setArguments(bundle);

		return mIndexFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		String tag = getArguments().getString(SHOW_TEXT);
		if (tag.equals(ORDER_CHECK)) {
			mTotalView = inflater.inflate(R.layout.fragment_order_check,
					container, false);
		} else if (tag.equals(ORDER_COUNT)) {
			mTotalView = inflater.inflate(R.layout.fragment_order_account,
					container, false);
		} else if (tag.equals(MESSAGE)) {
			mTotalView = inflater.inflate(R.layout.fragment_messasge,
					container, false);
		} else if (tag.equals(SET)) {
			mTotalView = inflater.inflate(R.layout.fragment_set,
					container, false);
		}
		return mTotalView;
	}
}
