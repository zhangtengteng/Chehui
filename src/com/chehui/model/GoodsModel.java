package com.chehui.model;

import java.io.Serializable;

/***
 * 订单实体
 * @author zhangtengteng
 *
 */
public class GoodsModel implements Serializable {
	String FWID;
	String OID;
	String OrderNum;
	String OrderPrice;
	String UserName;
	String SMSTel;
	String SignTime;
	String PayState;
	String PayTime;
	String DealState;
	String DealTime;
	String PicFace;
	String Title;
	String AdminID;
	String Intro;
	public String getFWID() {
		return FWID;
	}
	public void setFWID(String fWID) {
		FWID = fWID;
	}
	public String getOID() {
		return OID;
	}
	public void setOID(String oID) {
		OID = oID;
	}
	public String getOrderNum() {
		return OrderNum;
	}
	public void setOrderNum(String orderNum) {
		OrderNum = orderNum;
	}
	public String getOrderPrice() {
		return OrderPrice;
	}
	public void setOrderPrice(String orderPrice) {
		OrderPrice = orderPrice;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getSMSTel() {
		return SMSTel;
	}
	public void setSMSTel(String sMSTel) {
		SMSTel = sMSTel;
	}
	public String getSignTime() {
		return SignTime;
	}
	public void setSignTime(String signTime) {
		SignTime = signTime;
	}
	public String getPayState() {
		return PayState;
	}
	public void setPayState(String payState) {
		PayState = payState;
	}
	public String getPayTime() {
		return PayTime;
	}
	public void setPayTime(String payTime) {
		PayTime = payTime;
	}
	public String getDealState() {
		return DealState;
	}
	public void setDealState(String dealState) {
		DealState = dealState;
	}
	public String getDealTime() {
		return DealTime;
	}
	public void setDealTime(String dealTime) {
		DealTime = dealTime;
	}
	public String getPicFace() {
		return PicFace;
	}
	public void setPicFace(String picFace) {
		PicFace = picFace;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getAdminID() {
		return AdminID;
	}
	public void setAdminID(String adminID) {
		AdminID = adminID;
	}
	public String getIntro() {
		return Intro;
	}
	public void setIntro(String intro) {
		Intro = intro;
	}
	
}
