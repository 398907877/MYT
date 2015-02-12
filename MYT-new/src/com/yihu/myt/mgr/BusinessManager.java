package com.yihu.myt.mgr;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.yihu.myt.util.DateUtil;
import org.apache.commons.lang3.StringUtils;


import com.common.json.JSONArray;
import com.common.json.JSONObject;

import com.coreframework.db.DB;
import com.coreframework.db.Sql;
import com.coreframework.ioc.Ioc;
import com.coreframework.remoting.reflect.Rpc;
import com.coreframework.remoting.standard.DateOper;
import com.coreframework.vo.ServiceResult;
import com.yihu.account.api.AccAccountBean;
import com.yihu.account.api.AccCardtypeBean;
import com.yihu.account.api.BalanceReturnBean;
import com.yihu.account.api.IAccountService;
import com.yihu.baseinfo.api.DoctorServiceApi;
import com.yihu.myt.ConfigUtil;
import com.yihu.myt.IArraworkService;
import com.yihu.myt.IDoctorAccPriceViewService;
import com.yihu.myt.IPauseService;
import com.yihu.myt.enums.AccFeeConst;
import com.yihu.myt.enums.BaseDictionary;
import com.yihu.myt.enums.MyDatabaseEnum;
import com.yihu.myt.enums.MySqlNameEnum;
import com.yihu.myt.service.BusinessRule;
import com.yihu.myt.service.BusinessRule.MYTRule;
import com.yihu.myt.service.BusinessRuleParse;
import com.yihu.myt.service.DoctorOnLine;
import com.yihu.myt.service.service.IPostService;
import com.yihu.myt.util.DateUtil;
import com.yihu.myt.util.StringUtil;
import com.yihu.myt.vo.BossAccountBean;
import com.yihu.myt.vo.MytArraphoneViewBean;
import com.yihu.myt.vo.MytArraworkBean;
import com.yihu.myt.vo.MytPauseworkBean;

public class BusinessManager {

	private static IArraworkService arraworkService = Ioc
			.get(IArraworkService.class);
	private static IPauseService pauseService = Ioc.get(IPauseService.class);
	private static IPostService PostService = Ioc.get(IPostService.class);

	private static IDoctorAccPriceViewService doctorAccPriceViewService = Ioc
			.get(IDoctorAccPriceViewService.class);
	private static IAccountService getAccountService() {
		try {
			return Rpc.get(IAccountService.class, ConfigUtil.getInstance()
					.getUrl("url.account"), 8000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 排班
	 * 
	 * @param balancetype
	 * @param operatorid
	 * @param day
	 * @param operConfid
	 * @param scope
	 * @param doctorLevel
	 * @param deptID
	 * @param bossaccount
	 * @param doctorOrg
	 * @return
	 * @throws Exception
	 */
	public static List<MytArraworkBean> getArraWorks(String balancetype,
			int operatorid, String day, String operConfid, String scope,
			String doctorLevel, String deptID, BossAccountBean bossaccount,
			int doctorOrg) throws Exception {
		//System.out.println(DateUtil.dateFormat(new Date()));
		// 获取session值
		//System.out.println(DateUtil.dateFormat(new Date()));

		IAccountService iface = Rpc.get(IAccountService.class, ConfigUtil
				.getInstance().getUrl("url.account"), 8000);
		String cardID = bossaccount.getCardID();
		String cardState = bossaccount.getCardState() + "";
		String cardType = bossaccount.getCardtypesn() + "";
		String operatorID = operatorid + "";
		String feeName = "无卡号、卡状态异常！";// 排班描述
		
		// 判断卡号是否存在，状态是否正常
		if (StringUtils.isNotEmpty(cardID) && StringUtils.isNotEmpty(cardState)
				&& "3".equals(cardState)) {
			// 判断是否要获取资费
			int isFee = 0;
			try {
				if (!iface.isPermitConsult(cardID, Integer.parseInt(cardType),
						Integer.parseInt(doctorLevel)))
					feeName = "不能咨询该级别医生！";
				else
					isFee = 1;
			} catch (Exception e) {
				feeName = "咨费未确定，不能咨询！";
			}
			if (isFee == 1) {

				int[] feeArr = { 0, 0, 0 };

				// 判断咨询的是全科医生还是其他医生
				try {
					JSONObject dcPrice = doctorAccPriceViewService
							.changeBilling(Integer.valueOf(operConfid), 1);
					feeName = (String) dcPrice.get("message");
					/*
					 * if (balancetype.equals("0")) { feeName = "免费咨询！"; } else
					 * { if ("7".equals(doctorLevel)) {// 全科医生 feeArr =
					 * AccFeeConst.const_3(Integer.parseInt(doctorLevel),
					 * doctorOrg); } else { feeArr =
					 * AccFeeConst.const_3(Integer.parseInt(doctorLevel),
					 * doctorOrg); }
					 * 
					 * if (feeArr == null) feeName = "咨费未确定，不能咨询！"; else feeName
					 * = "前" + feeArr[0] + "分钟" + feeArr[1] / 100 + "元，后每分钟" +
					 * feeArr[2] / 100 + "元";
					 * 
					 * }
					 */

				} catch (Exception e) {
					feeName = "咨费未确定，不能咨询！";
				}
			}
		}

		// 获取isShowPhone
		int isSP = 0;

		// 当前日期、时间、星期
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = dateformat.format(new Date());
		dateformat.applyPattern("HH:mm");
		String nowTime = dateformat.format(new Date());
		Calendar cal = Calendar.getInstance();
		int nowWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;

		// 初始化，调用查询方法
		MytArraworkBean arraWork = new MytArraworkBean();
		Calendar calq = Calendar.getInstance();
		calq.setTime(new Date());
		int week = calq.get(Calendar.DAY_OF_WEEK) - 1;
		if (day.equals("one")) {
			arraWork.setWeekid(String.valueOf(week));
		}
		arraWork.setOperconfid(Integer.valueOf(operConfid));
		arraWork.setState(1);
		//System.out.println(DateUtil.dateFormat(new Date()));

		ServiceResult<List<MytArraworkBean>> sr = arraworkService
				.getMytArraworkList(arraWork);

		List<MytArraworkBean> lstMaBean = null;
		if (sr.getCode() > 0) {
			lstMaBean = sr.getResult();
			for (int i = 0; i < lstMaBean.size(); i++) {
				MytArraworkBean o = lstMaBean.get(i);
				int whichColor = 0;

				// 排班在线
				if (o.getWeekid().equals(nowWeek + "")
						&& nowTime.compareTo(o.getStarttime()) >= 0
						&& nowTime.compareTo(o.getEndtime()) <= 0) {
					whichColor = 1;

					// 判断医生是否停诊
					MytPauseworkBean qryMpBean = new MytPauseworkBean();
					ServiceResult<MytPauseworkBean> srMp = pauseService
							.getEntity(o.getOperconfid(), 
									DateOper.getNow(DateUtil.HMS_FORMAT),
									DateOper.getNow(DateUtil.YMD_FORMAT));
					MytPauseworkBean rltMpBean = srMp.getCode() > 0 ? srMp
							.getResult() : null;
					if (rltMpBean != null)
						// 医生停诊
						whichColor = 2;
					else {
						if (DoctorOnLine.get(o.getOperconfid()) != null)
							whichColor = 4;
						if (arraworkService.isBusy(o.getOperconfid()))
							whichColor = 4;
					}
				}

				// 判断是否有查看名医通医生电话的权限
				String consPhone = "";
				String dealPhone = "";
				String consphonetwo = "";

				if (isSP != 1) {
					ServiceResult<List<MytArraphoneViewBean>> srMav = arraworkService
							.getMytArraphoneView(o.getArraworkid() + "");
					if (srMav.getCode() > 0) {
						for (MytArraphoneViewBean mavBean : srMav.getResult()) {
							consPhone = mavBean.getConsphone();
							int length = consPhone.length();
							if (length == 11)
								dealPhone = consPhone.substring(0, 3) + "****"
										+ consPhone.substring(7);
							else if (length == 12)
								dealPhone = consPhone.substring(0, 4) + "****"
										+ consPhone.substring(8);
							else if (length == 7)
								dealPhone = consPhone.substring(0, 2) + "***"
										+ consPhone.substring(5);
							else if (length == 8)
								dealPhone = consPhone.substring(0, 2) + "****"
										+ consPhone.substring(6);
							if ("".equals(consphonetwo)) {
								consphonetwo = dealPhone;

							} else {
								consphonetwo += "," + dealPhone;
							}
						}
					}
				}
				o.setOperatorname(whichColor + "");
				o.setSevendoctorid(consphonetwo);
				o.setFeeid(feeName);
				o.setStarttime(o.getStarttime() + "--" + o.getEndtime());
				lstMaBean.remove(i);
				lstMaBean.add(i, o);
			}
		}
	//	System.out.println(DateUtil.dateFormat(new Date()));

		return lstMaBean;
	}

	public static List<MytArraworkBean> getArraWorkstwo(String balancetype,
			int operatorid, String day, String operConfid, String scope,
			String doctorLevel, String deptID, BossAccountBean bossaccount,
			int doctorOrg) throws Exception {

		// 调用Account远程API接口
		IAccountService iface = Rpc.get(IAccountService.class, ConfigUtil
				.getInstance().getUrl("url.account"), 8000);
		String cardID = bossaccount.getCardID();
		String cardState = bossaccount.getCardState() + "";
		String cardType = bossaccount.getCardtypesn() + "";
		String operatorID = operatorid + "";
		String feeName = "无卡号、卡状态异常！";// 排班描述

		// 判断卡号是否存在，状态是否正常
		if ((cardID != null) && (!cardID.equals("")) && (cardState != null)
				&& (cardState.equals("3"))) {

			// 判断是否要获取资费
			int isFee = 0;

			try {
				if (!iface.isPermitConsult(cardID, Integer.parseInt(cardType),
						Integer.parseInt(doctorLevel)))
					feeName = "不能咨询该级别医生！";
				else
					isFee = 1;
			} catch (Exception e) {
				feeName = "咨费未确定，不能咨询！";
			}

			if (isFee == 1) {
				int[] feeArr = { 0, 0, 0 };

				// 判断咨询的是全科医生还是其他医生
				try {
					JSONObject dcPrice = doctorAccPriceViewService.getBill(
							operatorid, 1, 1);

					/*
					 * if (balancetype.equals("0")) { feeName = "免费咨询！"; } else
					 * { if ("7".equals(doctorLevel)) {// 全科医生 // feeArr = //
					 * chargservice.getMytQK(cardID,Integer.parseInt(cardType));
					 * feeArr =
					 * AccFeeConst.const_3(Integer.parseInt(doctorLevel),
					 * doctorOrg); } else { //
					 * feeArr=chargservice.getMyTFee(cardID
					 * ,Integer.parseInt(cardType), //
					 * Integer.parseInt(doctorLevel), doctorOrg); feeArr =
					 * AccFeeConst.const_3(Integer.parseInt(doctorLevel),
					 * doctorOrg); }
					 * 
					 * if (feeArr == null) feeName = "咨费未确定，不能咨询！"; else feeName
					 * = "前" + feeArr[0] + "分钟" + feeArr[1] / 100 + "元，后每分钟" +
					 * feeArr[2] / 100 + "元";
					 * 
					 * }
					 */

				} catch (Exception e) {
					feeName = "咨费未确定，不能咨询！";
				}
			}
		}

		// 获取isShowPhone
		int isSP = 0;

		// 当前日期、时间、星期
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = dateformat.format(new Date());
		dateformat.applyPattern("HH:mm");
		String nowTime = dateformat.format(new Date());
		Calendar cal = Calendar.getInstance();
		int nowWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;

		// 初始化，调用查询方法
		MytArraworkBean arraWork = new MytArraworkBean();
		Calendar calq = Calendar.getInstance();
		calq.setTime(new Date());
		int week = calq.get(Calendar.DAY_OF_WEEK) - 1;
		if (day.equals("one") || day.equals("chaxun")) {
			arraWork.setWeekid(String.valueOf(week));
			// arraWork
		}
		arraWork.setOperconfid(Integer.valueOf(operConfid));
		arraWork.setState(1);
		ServiceResult<List<MytArraworkBean>> sr = arraworkService
				.getMytArraworkList(arraWork);
		List<MytArraworkBean> lstMaBean = null;
		if (sr.getCode() > 0) {
			lstMaBean = sr.getResult();
			for (int i = 0; i < lstMaBean.size(); i++) {
				MytArraworkBean o = lstMaBean.get(i);
				int whichColor = 0;

				// 排班在线
				if (o.getWeekid().equals(nowWeek + "")
						&& nowTime.compareTo(o.getStarttime()) >= 0
						&& nowTime.compareTo(o.getEndtime()) <= 0) {
					whichColor = 1;

					// 判断医生是否停诊
					ServiceResult<MytPauseworkBean> srMp = pauseService
							.getEntity(o.getOperconfid(),
									DateOper.getNow(DateUtil.HMS_FORMAT),
									DateOper.getNow(DateUtil.YMD_FORMAT));
					MytPauseworkBean rltMpBean = srMp.getCode() > 0 ? srMp
							.getResult() : null;
					if (rltMpBean != null)
						// 医生停诊
						whichColor = 2;
					else {
						if (DoctorOnLine.get(o.getOperconfid()) != null)
							whichColor = 4;
						if (arraworkService.isBusy(o.getOperconfid()))
							whichColor = 4;
					}
				}

				// 判断是否有查看名医通医生电话的权限
				String consPhone = "";
				String dealPhone = "";
				String consphonetwo = "";

				if (isSP != 1) {
					ServiceResult<List<MytArraphoneViewBean>> srMav = arraworkService
							.getMytArraphoneView(o.getArraworkid() + "");
					if (srMav.getCode() > 0) {
						for (MytArraphoneViewBean mavBean : srMav.getResult()) {
							consPhone = mavBean.getConsphone();
							int length = consPhone.length();
							if (length == 11)
								dealPhone = consPhone.substring(0, 3) + "****"
										+ consPhone.substring(7);
							else if (length == 12)
								dealPhone = consPhone.substring(0, 4) + "****"
										+ consPhone.substring(8);
							else if (length == 7)
								dealPhone = consPhone.substring(0, 2) + "***"
										+ consPhone.substring(5);
							else if (length == 8)
								dealPhone = consPhone.substring(0, 2) + "****"
										+ consPhone.substring(6);
							if ("".equals(consphonetwo)) {
								consphonetwo = dealPhone;

							} else {
								consphonetwo += "," + dealPhone;
							}
						}
					}
				}
				o.setOperatorname(whichColor + "");
				o.setSevendoctorid(consphonetwo);
				o.setFeeid(feeName);
				o.setStarttime(o.getStarttime() + "--" + o.getEndtime());
				lstMaBean.remove(i);
				lstMaBean.add(o);
			}
		}
		return lstMaBean;
	}

	/**
	 * 验证卡是否可以咨询
	 * 
	 * @param balancetype
	 * @param doctorLevel
	 * @param deptID
	 * @param bossaccount
	 * @param doctorOrg
	 * @return
	 * @throws Exception
	 */
	public static String[] checkCards(String balancetype, String doctorLevel,
			String deptID, BossAccountBean bossaccount, int doctorOrg,
			int operConfid) throws Exception {
		// 调用Account远程API接口
	/*	IAccountService accService = Rpc.get(IAccountService.class, ConfigUtil
				.getInstance().getUrl("url.account"), 8000);*/
		//System.out.println(DateUtil.dateFormat(new Date()));
		String cardID = bossaccount.getCardID();
		String cardState = bossaccount.getCardState() + "";
		String cardType = bossaccount.getCardtypesn() + "";
		int accountSN = bossaccount.getAccountSN();

		// 返回信息
		int code = 0;// 0不可以咨询（无效的会员卡、没验证卡，余额或资源不足够咨询，不能咨询该级别医生）；1可以咨询
		String info = "";// 卡信息描述
		//System.out.println(DateUtil.dateFormat(new Date()));

		// 判断卡号是否存在，状态是否正常
		if ((cardID != null) && (!cardID.equals("")) && (cardState != null)
				&& (cardState.equals("3"))) {
			BusinessRuleParse businessRule = new BusinessRuleParse(cardID,
					Integer.parseInt(cardType), BaseDictionary.Business.myt);
			BusinessRule.MYTRule mytRuleObj = (MYTRule) businessRule.parse();
			// System.out.println(cardID+" "+ cardType +
			// ">>>>"+mytRuleObj.toString()+" "+mytRuleObj.isOk());
			String feeNo = "01";
			if (!"7".equals(doctorLevel)) {// 全科医生
				if (!mytRuleObj.isOk()) {
					return new String[] { "0", "该用户不可名医咨询", cardType,
							accountSN + "", cardID };
				}
			}else{
				feeNo = "03";
			}
			if (!mytRuleObj.isQkOk()) {
				return new String[] { "0", "该用户不能全科咨询", cardType,
						accountSN + "", cardID };
			}
			//System.out.println(DateUtil.dateFormat(new Date()));

			int[] feeArr = { 0, 0, 0 };
			// 获取该医生资费

			/*
			 * // 判断咨询的是全科医生还是其他医生 try { //JSONObject dcPrice =
			 * doctorAccPriceViewService
			 * .changeBilling(Integer.valueOf(operConfid),1);
			 * 
			 * 
			 * if (priceJs.getInt("typeId") == 1) { int beTime =
			 * priceJs.getInt("minuteBefore"); int bePrice =
			 * priceJs.getInt("minuteBeforePrice"); int afPrice =
			 * priceJs.getInt("perMinutePrice"); rt.put("price",
			 * priceJs.getInt("price")); rt.put("message", "前" + beTime + "分钟" +
			 * bePrice / 100 + "元，后每分钟" + afPrice / 100 + "元");
			 * rt.put("feeTemplateId", priceJs.getInt("feeTemplateId")); } else
			 * if (priceJs.getInt("typeId") == 2) { int total =
			 * priceJs.getInt("minuteNum"); int tolalPrice =
			 * priceJs.getInt("price"); rt.put("price",
			 * priceJs.getInt("price")); rt.put("message", "每" + total + "分钟" +
			 * tolalPrice / 100 + "元"); rt.put("feeTemplateId",
			 * priceJs.getInt("feeTemplateId")); } else { rt.put("price",
			 * priceJs.getInt("price")); rt.put("message", "医生本次咨询费用" +
			 * priceJs.getInt("price") / 100 + "元"); rt.put("feeTemplateId",
			 * priceJs.getInt("feeTemplateId")); }
			 * 
			 * 
			 * if ("7".equals(doctorLevel)) {// 全科医生 feeNo = "03"; if
			 * (balancetype.equals("0")) { feeArr = new int[] { 120, 0, 0 }; }
			 * else { // feeArr //
			 * =chargservice.getMytQK(cardID,Integer.parseInt(cardType)); feeArr
			 * = AccFeeConst.const_3(Integer.parseInt(doctorLevel), doctorOrg);
			 * } } else { if (Integer.parseInt(cardType) == 67 ||
			 * Integer.parseInt(cardType) == 68 || Integer.parseInt(cardType) ==
			 * 336 || Integer.parseInt(cardType) == 243 ||
			 * Integer.parseInt(cardType) == 244 || Integer.parseInt(cardType)
			 * == 11) { return new String[] { "0", "该卡类型不可名医咨询", cardType,
			 * accountSN+"", cardID }; } feeNo = "01"; if
			 * (balancetype.equals("0")) { feeArr = new int[] { 120, 0, 0 }; }
			 * else { // feeArr //
			 * =chargservice.getMyTFee(cardID,Integer.parseInt(cardType), //
			 * Integer.parseInt(doctorLevel), doctorOrg); feeArr =
			 * AccFeeConst.const_3(Integer.parseInt(doctorLevel), doctorOrg); }
			 * } } catch (Exception e) { return new String[] { "0",
			 * "资费未确定，不能咨询！", cardType, accountSN+"", cardID }; }
			 */
			// 判断该医生能否咨询
			if (!getAccountService().isPermitConsult(cardID, Integer.parseInt(cardType),
					Integer.parseInt(doctorLevel))) {

				return new String[] { "0", "不能咨询该级别医生！", cardType,
						accountSN + "", cardID };
			}

			// 获取卡模型
			AccCardtypeBean cardTypeObj = getAccountService().getCardTypeObj(
					Integer.parseInt(cardType));
			int mode = cardTypeObj.getModeid();

			info = "会员卡号：" + cardID + "\n";
			// 获取资源
			//int res = getAccountService().getResourceCount(cardID, "02", feeNo);
			String rt = PostService.getResourceCount(String.valueOf(operConfid), cardID, "02", String.valueOf(feeNo));
			System.out.println(rt);
			net.sf.json.JSONObject js =  net.sf.json.JSONObject.fromObject(rt);
			int res = 	js.getInt("Count");
			if ("7".equals(doctorLevel) && res > 0) {
				code = 1;
				info += "全科资源：" + res + "\n可以咨询：不限分钟（本次）\n";
			} else if (!"7".equals(doctorLevel) && res > 0) {
				code = 1;
				info += "名医资源：" + res + "\n可以咨询：不限分钟（本次）\n";
			} else {
/*				BalanceReturnBean balanceBean = accService.getAllBalance(
						accountSN, "02", feeNo);

				int balance = balanceBean.getAvailableBalance();*/
				// 特殊的免费医生
				if (balancetype.equals("0")) {
					AccAccountBean account = getAccountService()
							.getAccountObject(accountSN);
					int balance = account.getBalance();
					code = 1;
					info += "卡上余额：" + balance / 100 + "元\n可以咨询:120分钟（本次）\n";
				}else {
					// 资源不够查询余额
//					if (mode == 1) {
						BalanceReturnBean allBalance = getAccountService()
								.getAllBalance(accountSN,
										"02", feeNo);
						int balance = allBalance.getAvailableBalance();
						/*
						 * if (balance >= feeArr[1]) { code = 1; info += "卡上余额："
						 * + balance / 100 + "元\n可以咨询：" + (feeArr[0] + (balance
						 * - feeArr[1]) / feeArr[2]) + "分钟（本次）\n"; }
						 */
						com.yihu.baseinfo.api.DoctorServiceApi doctorServiceApi = Rpc
								.get(DoctorServiceApi.class, ConfigUtil
										.getInstance().getUrl("url.baseinfo"));
						JSONObject dcJson = new JSONObject();
						dcJson.put("doctorUid", operConfid);
						dcJson.put("serviceId", 1);
						ServiceResult<String> priceJson = doctorServiceApi
								.getDoctorPrice(dcJson.toString());
						if (priceJson.getCode() > 0) {
							JSONObject docPrice = new JSONObject(
									priceJson.getResult());
							JSONArray pris = docPrice
									.getJSONArray("serviceList");
							JSONObject priceJs = pris.getJSONObject(0);
							if (priceJs.getInt("typeId") == 1) {
								int beTime = priceJs.getInt("minuteBefore");
								int bePrice = priceJs
										.getInt("minuteBeforePrice");
								int afPrice = priceJs.getInt("perMinutePrice");
								if (balance >= bePrice) {
									code = 1;
									info += "卡上余额："
											+ balance
											/ 100
											+ "元\n可以咨询："
											+ (beTime + (balance - bePrice)
													/ afPrice) + "分钟（本次）\n";
								}
							} else if (priceJs.getInt("typeId") == 2) {
								int total = priceJs.getInt("minuteNum");
								int tolalPrice = priceJs.getInt("price");
								if (balance >= tolalPrice) {
									code = 1;
									info += "卡上余额："
											+ balance
											/ 100
											+ "元\n可以咨询："
											+ (1 + (balance - tolalPrice)
													/ tolalPrice) + "分钟（本次）\n";
								} else {
									code = 0;
								}
							} else {
								int price = priceJs.getInt("price");
								if (balance >= price) {
									code = 1;
									info += "卡上余额：" + balance / 100
											+ "元\n可以咨询："
											+ (1 + (balance - price) / price)
											+ "次\n";
								} else {
									code = 0;
								}
							}
						}
						// 查询是否有优惠
						int preArr[] = getPremium(cardID, accountSN, "02",
								feeNo);
						if (preArr[0] == 1) {
							if (preArr[1] >= 0)
								info += "咨询优惠：优惠" + preArr[1] / 100 + "元<br>";
							else
								info += "咨询优惠：" + preArr[2] / 100.0 + "折优惠<br>";
						}

//					} else
//						code = 0;

				}
			}

			info += "所有咨询优先扣资源";

			if (code == 0) {
				//cardType=336 福建电信用户
//				if (Integer.parseInt(cardType) != 336) {
					info = "会员卡余额、资源不足或本月咨询资源已用完，暂时不能咨询，请充值再来！";
//				} else {
//					code = 1;
//				}
			}

		}
		return new String[] { code + "", info, cardType, accountSN + "", cardID };
	}

	/**
	 * 获取优惠值
	 * 
	 * @param cardID
	 * @param accountSN
	 * @param productNo
	 * @param feeNo
	 * @return
	 * @throws Exception
	 */
	public static int[] getPremium(String cardID, int accountSN,
			String productNo, String feeNo) throws Exception {

		int isValid = 0; // 是否有优惠
		int cash = 0; // 优惠金额
		int rate = 0; // 优惠率

		// 调用Account远程API接口
		IAccountService accService = Rpc.get(IAccountService.class, ConfigUtil
				.getInstance().getUrl("url.account"), 8000);
		// 获取优惠对象
		com.yihu.account.api.AccAccountpremiumruleBean rltAapBean = accService
				.getPremium(cardID, productNo, feeNo);

		// 判断优惠是否存在，有效
		if (rltAapBean != null) {
			Date date = new Date();
			switch (rltAapBean.getType()) {
			case 1: {
				Date startDate = rltAapBean.getStartdate();
				Date endDate = rltAapBean.getEnddate();
				if (date.before(startDate) && date.after(endDate))
					isValid = 1;
				break;
			}
			case 2: {
				// 查询开卡时间 调用Account远程API接口
				com.yihu.account.api.AccAccountBean rltAaBean = accService
						.getAccountObject(accountSN);
				Calendar c = Calendar.getInstance();
				c.setTime(rltAaBean.getOpertime());
				c.add(Calendar.MONTH, rltAapBean.getMonthcount());
				Date pdate = c.getTime();

				if (date.after(pdate))
					isValid = 1;
				break;
			}
			case 3: {
				if (rltAapBean.getPremiumcount() > 0)
					isValid = 1;
				break;
			}
			}

			if (isValid == 1) {

				if (rltAapBean.getFeecash() >= 0)
					cash = rltAapBean.getFeecash();
				else
					rate = rltAapBean.getPremiumvalue();
			}
		}

		return new int[] { isValid, cash, rate };
	}

	/**
	 * 取字典的值
	 * 
	 * @param businTypeId
	 * @param businId
	 * @return
	 * @throws Exception
	 */
	public static String getBusinName(String businTypeId, String businId)
			throws Exception {
		Sql sql = DB.me().createSql(MySqlNameEnum.getDictionary);
		sql.addVar("@condition", " and busintypeid = ? and businid = ?");
		sql.addParamValue(businTypeId);
		sql.addParamValue(businId);
		Map<String, Object> mapinfo = DB.me().queryForMap(MyDatabaseEnum.boss,
				sql);
		if (mapinfo != null) {
			return StringUtil.isEmpty(mapinfo.get("businname")) ? "" : mapinfo
					.get("businname").toString();
		} else {
			return "";
		}
	}

	/**
	 * 取字典
	 * 
	 * @param businTypeId
	 * @param businId
	 * @return
	 * @throws Exception
	 */
	public static String getBusinName(String businTypeId) throws Exception {
		Sql sql = DB.me().createSql(MySqlNameEnum.getDictionary);
		sql.addVar("@condition", " and busintypeid = ? ");
		sql.addParamValue(businTypeId);
		JSONObject jsrt = DB.me().queryForJson(MyDatabaseEnum.boss, sql);
		return jsrt.toString();
	}
}
