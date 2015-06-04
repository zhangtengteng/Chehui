package com.chehui.net;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.os.Handler;

import com.chehui.model.RequestParamsModel;
import com.chehui.model.ResponseResultModel;
import com.google.gson.Gson;

public abstract class JSONRequest extends Request {
	private Gson gson= new Gson();
	public JSONRequest(Handler handler) {
		super(handler);
	}

	public abstract String getAction();

	@Override
	protected void httpConnect() {

		String uriAPI = CommonData.SERVER_ADDRESS + "" + getAction() + ".ashx?"
				+ getParms();
		System.out.println("JSONRequest==url==" + uriAPI);
//		HttpGet httpRequest = new HttpGet(uriAPI);
//		try {
//			HttpResponse httpResponse = new DefaultHttpClient()
//					.execute(httpRequest);
//			if (httpResponse.getStatusLine().getStatusCode() == 200) {
//				String strResult = EntityUtils.toString(httpResponse
//						.getEntity());
//				System.out.println("JSONRequest==result==" + strResult);
//				onHttpSuccess(strResult);
//			} else {
//				onHttpFailure(0, "出错了");
//			}
//
//		} catch (Exception e) {
//
//		}

		
		
		
		HttpPost httpRequest = new HttpPost(uriAPI);
		/* 建立HTTP Post连线 */
		// Post运作传送变数必须用NameValuePair[]阵列储存
		// 传参数 服务端获取的方法为request.getParameter("name")
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("name", "this is post"));
		try {

			// 发出HTTP request
			httpRequest.setEntity(new UrlEncodedFormEntity(
					params, HTTP.UTF_8));
			// 取得HTTP response
			HttpResponse httpResponse = new DefaultHttpClient()
					.execute(httpRequest);

			// 若状态码为200 ok
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				// 取出回应字串
				String strResult = EntityUtils.toString(httpResponse
						.getEntity());
				System.out.println("JSONRequest==result==post" + strResult);
				ResponseResultModel model = gson.fromJson(strResult, ResponseResultModel.class);
				if (model.getResult()==1) {
					//成功
					onHttpSuccess(strResult);
				}else{
					onHttpFailure( CommonData.HTTP_HANDLE_FAILE,model.getMsg());
				}
				
			} else {
				onHttpFailure(0, "出问题了");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected abstract void onHttpSuccess(String str);

	protected abstract void onHttpFailure(int errorCode, String why);

	private String getParms() {
		StringBuffer strBuffer = new StringBuffer();
		List<RequestParamsModel> list = getParamList();
		for (int i = 0; i < list.size(); i++) {
			strBuffer.append(list.get(i).getKey() + "="
					+ list.get(i).getValue() + "&");
		}

		return strBuffer.toString();

	}

	protected abstract List<RequestParamsModel> getParamList();

}
