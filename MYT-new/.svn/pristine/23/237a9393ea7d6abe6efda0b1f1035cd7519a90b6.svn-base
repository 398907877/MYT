package com.yihu.myt.vo;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.boss.sdk.AccountInfo;
import com.common.json.JSONObject;

/**
 * @author Administrator
 * 
 */
public class BossAccountBean {
	private String cardID;

	public String getCardID() {
		return cardID;
	}

	public void setCardID(String cardID) {
		this.cardID = cardID;
	}

	public int getAccountSN() {
		return accountSN;
	}

	public void setAccountSN(int accountSN) {
		this.accountSN = accountSN;
	}

	public int getCardtypesn() {
		return cardtypesn;
	}

	public void setCardtypesn(int cardtypesn) {
		this.cardtypesn = cardtypesn;
	}

	public int getCardState() {
		return cardState;
	}

	public void setCardState(int cardState) {
		this.cardState = cardState;
	}

	private int accountSN;
	private int cardtypesn;
	private int cardState;
	private String cards;
	private int cardtypegroup;
	private int orgid;
	private int logintype;

	public int getLogintype() {
		return logintype;
	}

	public void setLogintype(int logintype) {
		this.logintype = logintype;
	}

	public int getOrgid() {
		return orgid;
	}

	public void setOrgid(int orgid) {
		this.orgid = orgid;
	}

	public int getCityid() {
		return cityid;
	}

	public void setCityid(int cityid) {
		this.cityid = cityid;
	}

	private int cityid;

	public int getCardtypegroup() {
		return cardtypegroup;
	}

	public void setCardtypegroup(int cardtypegroup) {
		this.cardtypegroup = cardtypegroup;
	}

	public String getCards() {
		return cards;
	}

	public void setCards(String cards) {
		this.cards = cards;
	}

	public BossAccountBean() {

	}

	public BossAccountBean(AccountInfo info) {
		if (info != null) {
			this.accountSN = info.getAccountSN();
			this.cardID = info.getCardID();
			this.cards = info.getCards();
			this.cardState = info.getCardState();
			this.cardtypegroup = info.getCardtypegroup();
			this.cardtypesn = info.getCardtypesn();
			this.cityid = info.getCityid();
			this.logintype = info.getLogintype();
			this.orgid = info.getOrgid();
		}
	}

	public String toJSON() throws Exception {
		JSONObject json = new JSONObject();
		json.put("accountsn", this.accountSN);
		json.put("cardstate", this.cardState);
		json.put("cardtypegroup", this.cardtypegroup);
		json.put("cardtypesn", this.cardtypesn);
		json.put("cityid", this.cityid);
		json.put("logintype", this.logintype);
		json.put("orgid", this.orgid);
		json.put("cardid", this.cardID == null ? "" : this.cardID);
		json.put("cards", this.cards == null ? "" : this.cards);
		return json.toString();
	}

	public BossAccountBean(JSONObject json) throws Exception {
		this.accountSN = json.isNull("accountsn") ? 0 : json
				.getInt("accountsn");
		this.cardID = json.isNull("cardid") ? "" : json.getString("cardid");
		this.cards = json.isNull("cards") ? "" : json.getString("cards");
		this.cardState = json.isNull("cardstate") ? 0 : json
				.getInt("cardstate");
		this.cardtypegroup = json.isNull("cardtypegroup") ? 0 : json
				.getInt("cardtypegroup");
		this.cardtypesn = json.isNull("cardtypesn") ? 0 : json
				.getInt("cardtypesn");
		this.cityid = json.isNull("cityid") ? 0 : json.getInt("cityid");
		this.logintype = json.isNull("logintype") ? 0 : json
				.getInt("logintype");
		this.orgid = json.isNull("orgid") ? 0 : json.getInt("orgid");
	}

	public String toString() {
		return new ReflectionToStringBuilder(this).toString();
	}

}
