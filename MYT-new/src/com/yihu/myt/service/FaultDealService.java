package com.yihu.myt.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import com.common.json.JSONArray;
import com.common.json.JSONException;
import com.common.json.JSONObject;
import com.common.unique.AppUnique;
import com.coreframework.db.DB;
import com.coreframework.db.Sql;
import com.coreframework.remoting.Url;
import com.coreframework.remoting.reflect.Rpc;
import com.coreframework.remoting.standard.DateOper;
import com.coreframework.vo.ReturnValue;
import com.coreframework.vo.ServiceResult;
import com.yihu.account.api.ChargeReturnBean;
import com.yihu.account.api.IAccountService;
import com.yihu.myt.ConfigUtil;
import com.yihu.myt.IFaultDealService;
import com.yihu.myt.enums.AccFeeConst;
import com.yihu.myt.enums.MyDatabaseEnum;
import com.yihu.myt.enums.MySqlNameEnum;
import com.yihu.myt.enums.MyTableNameEnum;
import com.yihu.myt.enums.MytConst;
import com.yihu.myt.mgr.BusinessManager;
import com.yihu.myt.util.DateUtil;
import com.yihu.myt.vo.DoctorAccPriceView;
import com.yihu.myt.vo.DoctorAccountBean;
import com.yihu.myt.vo.DoctorBillBean;
import com.yihu.myt.vo.MytConswaterBean;
import com.yihu.myt.vo.MytDoctorViewBean;
import com.yihu.myt.vo.MytOperconfigBean;
import com.yihu.baseinfo.api.DoctorInfoApi;
import com.yihu.baseinfo.api.DoctorServiceApi;

public class FaultDealService implements IFaultDealService {
	public static void main(String[] args) {
		FaultDealService fl = new FaultDealService();
		JSONObject rt = fl.deleteDoctorPrice(34845, 263354);
		System.out.print(rt.toString());
	}
	private IAccountService  getAccountService()
	{
		try {
			return Rpc.get(IAccountService.class,ConfigUtil.getInstance().getUrl("url.account")
					, 8000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ReturnValue reviseMytConswater(int pkconswaterid, String realcard, String remark, int operatorId,
		String operatorName) {
		
		try {
			// 获取咨询收费流水信息
			MytConswaterBean conswater = new MytConswaterBean();
			conswater.setPkconswaterid(pkconswaterid);
			conswater = DB.me().queryForBean(MyDatabaseEnum.boss,
				DB.me().createSelect(conswater, MyTableNameEnum.MYT_CONSWATER, "BOSS"), MytConswaterBean.class);
			if (conswater == null)
				return new ReturnValue(-1, "查无咨询流水记录");
			if(DateOper.formatDate(conswater.getStartdatetime(), "yyyy-MM-dd").compareTo("2013-10-25")<=0)
				return new ReturnValue(-1, "采用新资费模式，2013-10-25之前的记录请找技术处理");
			com.yihu.account.api.AccMembershipcardBean card = getAccountService().getMembershipcardObject(realcard);
			if (card == null) {
				return new ReturnValue(-1, "会员卡不存在，请核对后再试");
			}
			if (!"3".equals(card.getState().replaceAll(" ", ""))) {
				return new ReturnValue(-1, "会员卡状态不正常。");
			}

			// 获取资费标准
			String feeNo = "01";
			int feeType = 3;

			// 获取名医通医生配置信息
			MytOperconfigBean operconfig = new MytOperconfigBean();
			operconfig.setOperconfid(conswater.getOperconfid());
			operconfig = DB.me().queryForBean(MyDatabaseEnum.boss,
				DB.me().createSelect(operconfig, MyTableNameEnum.MYT_OPERCONFIG, "BOSS"), MytOperconfigBean.class);
			if (operconfig != null && operconfig.getDoctorlevel() == 7) {
				feeNo = "03";
				feeType = 50;
			}
			int Chargeid = Integer.valueOf(conswater.getChargeid()  )  ;
			if(Chargeid>0){
				// 先返款给原记录上的会员
				ReturnValue rb = getAccountService().rollbackCharge(conswater.getCardid(), remark, "02", feeNo,
					Integer.parseInt(conswater.getChargeid()), operatorId, operatorName);
				System.out.println("回滚费用rollbackCharge:" + rb.getCode() + " " + rb.getMessage());
				if (rb.getCode() == -2 || rb.getCode() == -3) {
					return new ReturnValue(-1, rb.getMessage());
				}
			}
			// 获取资源
			Integer res = getAccountService().getResourceCount(realcard, "02", feeNo);
			int unit = 0;
			if (res != null && res > 0) {
				unit = 1;
			}

			// 再扣修正的会员的费用
			ChargeReturnBean ret = getAccountService().charge(realcard, card.getAccountsn(), card.getCardtypesn(), feeType,
				conswater.getPkconswaterid(), operatorId, operatorName, remark, conswater.getCharge() + "", unit + "",
				0, true, null, null);
			System.out.println("扣费charge:" + ret.getCode() + "|" + ret.getMessage() + "|" + ret.getChargewatersn() + "|"
				+ ret.getResultPayCash() + "|" + ret.getResultPayResource() + "|" +  feeType + "|" + conswater.getPkconswaterid()
				+ "|" +operatorId + "|" + operatorName + "|" + remark + "|" +  conswater.getCharge() + "|" + unit );
			
			//判断医生是否存在
			int rt = this.changeDoctorPrice(conswater.getOperconfid(), 1, 1, conswater.getPkconswaterid());
			if(rt <= 0){
				//给医生加钱
				this.saveDoctorPrice(conswater.getOperconfid(), 1, 1, conswater.getPkconswaterid(), -1*conswater.getCharge(), conswater.getFeeTemplateId());
			}
			// 更新流水
			conswater.setCardid(realcard);
			conswater.setChargeid(ret.getChargewatersn() + "");
			conswater.setRemark("[" + operatorName + " " + DateUtil.dateFormat(new Date(), DateUtil.YMDHMS_FORMAT) + "]:"
				+ remark);
			conswater.setPkconswaterid(null);
			DB.me().update(
				MyDatabaseEnum.boss,
				DB.me().createUpdateSql(conswater, "BOSS", MyTableNameEnum.MYT_CONSWATER,
					" pkconswaterid = " + pkconswaterid));

			return new ReturnValue(1, "修正名医通流水成功。");
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnValue(-1, e.getMessage());
		}
	}

	public ReturnValue repealMytConswater(MytConswaterBean water) {
		
		try {
			// 根据咨询流水号获取咨询流水信息
			MytConswaterBean conswater = new MytConswaterBean();
			conswater.setPkconswaterid(water.getPkconswaterid());
			conswater = DB.me().queryForBean(MyDatabaseEnum.boss, 
					DB.me().createSelect(conswater, MyTableNameEnum.MYT_CONSWATER, "BOSS"), 
					MytConswaterBean.class);
			if (conswater == null)
				return new ReturnValue(-1, "查无咨询流水记录");
			if(DateOper.formatDate(conswater.getStartdatetime(), "yyyy-MM-dd").compareTo("2013-10-25")<=0)
				return new ReturnValue(-1, "采用新资费模式，2013-10-25之前的记录请找技术处理");
			
			// 根据操作好获取医生配置信息及判断咨询类型
			String feeNo = "01";
			MytOperconfigBean operconfig = new MytOperconfigBean();
			operconfig.setOperconfid(conswater.getOperconfid());
			operconfig = DB.me().queryForBean(MyDatabaseEnum.boss, 
					DB.me().createSelect(operconfig, MyTableNameEnum.MYT_OPERCONFIG, "BOSS"), 
					MytOperconfigBean.class);
			if (operconfig != null && operconfig.getDoctorlevel() == 7) { // 全科医生
				feeNo = "03";
			}			
			// 返款
			
			//更新医生流水数据
			JSONObject rtCode  = this.deleteDoctorPrice(operconfig.getOperconfid(), water.getPkconswaterid());
			if(rtCode!=null){
				if(rtCode.getInt("code")==-2){
					return new ReturnValue(-1, "该流水已过结算账期，无法对医生账户进行扣费处理，请手动通过用户账户相关的功能处理。");

				}else if( rtCode.getInt("code")==-3){
					System.out.println("数据不存在。");
				}
				else if(rtCode.getInt("code")<0){
					return new ReturnValue(-1, rtCode.getString("message"));
				}
				
			}
			/*if()<0){
				return new ReturnValue(-1, rb.getMessage());
			}*/
			int chargeid =Integer.parseInt(conswater.getChargeid());
			if(chargeid>0){
				ReturnValue rb = getAccountService().rollbackCharge(conswater.getCardid(), water.getRemark(), "02", feeNo,
					Integer.parseInt(conswater.getChargeid()), water.getOperatorid(), water.getOperatorname());
				if (rb.getCode() != 1) {
					return new ReturnValue(-1, rb.getMessage());
				}
			}
  
			// 更新流水
			conswater.setState(MytConst.INVALID.getValue());
			conswater.setRemark("[" + water.getOperatorname() + " " + DateUtil.dateFormat(new Date(), DateUtil.YMDHMS_FORMAT) + "]:" + water.getRemark());
			conswater.setPkconswaterid(null);
			DB.me().update(MyDatabaseEnum.boss, DB.me().createUpdateSql(
					conswater, "BOSS", MyTableNameEnum.MYT_CONSWATER, 
					" pkconswaterid = " + water.getPkconswaterid()));
			    
			return new ReturnValue(1, "废除名医通流水成功。");
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnValue(-1, e.getMessage());
		}
	}

	public ReturnValue dealMytConswater(int pkconswaterid, String startTime, String endTime,
		String remark, int operatorId, String operatorName) {
		
		try {
			// 根据咨询流水号获取咨询流水信息
			MytConswaterBean conswater = new MytConswaterBean();
			conswater.setPkconswaterid(pkconswaterid);
			conswater = DB.me().queryForBean(MyDatabaseEnum.boss, 
					DB.me().createSelect(conswater, MyTableNameEnum.MYT_CONSWATER, "BOSS"), 
					MytConswaterBean.class);
			if (conswater == null)
				return new ReturnValue(-1, "查无咨询流水记录");
			if(DateOper.formatDate(conswater.getStartdatetime(), "yyyy-MM-dd").compareTo("2013-10-25")<=0)
				return new ReturnValue(-1, "采用新资费模式，2013-10-25之前的记录请找技术处理");
			
			// 格式化开始时间，结束时间
			String formatTime = DateUtil.dateFormat(conswater.getStartdatetime(), DateUtil.YMDHMS_FORMAT);
			// 借用sevendoctorid传递正确的起始时间，格式为HH:mm:ss
			Date begTime = DateUtil.parse(formatTime.substring(0,10) + " " + startTime, DateUtil.YMDHMS_FORMAT);
			// 借用tendoctorid传递正确的结束时间，格式为HH:mm:ss
			Date finishTime = DateUtil.parse(formatTime.substring(0,10) + " " + endTime, DateUtil.YMDHMS_FORMAT);
			// 咨询秒数,结束时间
			int consSec = (int)(finishTime.getTime() - begTime.getTime()) / 1000;
			
			// 请求计费总次数，分钟数
			int orders = 1;
			if ((consSec % 60) == 0) {
				orders = consSec / 60 + 1;
			} else {
				orders = consSec / 60 + 2;
			}
			int consMin = orders - 1;
			
			MytDoctorViewBean doctorView = new MytDoctorViewBean();
			doctorView.setOperconfid(conswater.getOperconfid());
			doctorView = DB.me().queryForBean(MyDatabaseEnum.boss, 
					DB.me().createSelect(doctorView, MyTableNameEnum.MYT_DOCTOR_VIEW, "BOSS"), 
					MytDoctorViewBean.class);
			
			// 获取资费标准
		/*	int[] feeArr = {0,0,0};
			String feeNo = "";
			int feeType = 3;
						
			if (doctorView != null && doctorView.getDoctorlevel() == 7) {// 全科医生
				feeNo = "03";
				feeType = 50;
				//feeArr =chargservice.getMytQK(consWater.getCardid(),consWater.getCardtypesn());
				feeArr = AccFeeConst.const_3(doctorView.getDoctorlevel(), doctorView.getOrgid());
			}else{
				feeNo = "01";
				//feeArr =chargservice.getMyTFee(consWater.getCardid(),consWater.getCardtypesn(), Integer.parseInt(consWater.getDoctorlevel()), mdv.getOrgid());
				feeArr = AccFeeConst.const_3(doctorView.getDoctorlevel(), doctorView.getOrgid());
			}*/
			
			int feeType = 3;
			String feeNo = "01";//计费代码
			if (doctorView != null && doctorView.getDoctorlevel() == 7) { // 全科医生
				feeType = 50;
				feeNo = "03";
			} else {
				feeNo = "01";
			}
			//重新计算资费
			JSONObject feeArr = this.changeBilling(doctorView.getOperconfid(), 1,1, consMin);
			int charge =(Integer) feeArr.get("price");
			if (feeArr == null) {
				conswater.setMytfeename("未能获取资费！");
			} else {
				/*water.setMytfeename("前" + feeArr[0] + "分钟" + feeArr[1]
						/ 100 + "元，后每分钟" + feeArr[2] / 100 + "元");*/
				conswater.setMytfeename((String) feeArr.get("message"));
			}
			
			com.yihu.account.api.AccMembershipcardBean card = getAccountService().getMembershipcardObject(conswater.getCardid());
			if (card == null) {
				return new ReturnValue(-1, "会员卡不存在，请核对后再试");
			}
			if (!"3".equals(card.getState().replaceAll(" ", ""))) {
				return new ReturnValue(-1, "会员卡状态不正常。");
			}
			
			// 获取优惠
			int preArr[] = BusinessManager.getPremium(conswater.getCardid(), card.getAccountsn(), "02", feeNo);
			
		/*	// 计算需扣费用
			int charge = 0;
			if (feeArr != null) {
				charge = feeArr[1];
				if (consMin >= feeArr[0])
					charge += (consMin - feeArr[0]) * feeArr[2];
			}
*/
			//先给医生账户扣费并注销流水
			
			JSONObject rtCode  = this.deleteDoctorPrice(conswater.getOperconfid(), conswater.getPkconswaterid());
			if (rtCode != null) {
				if (rtCode.getInt("code") == -2) {
					return new ReturnValue(-1,
							"该流水已过结算账期，无法对医生账户进行扣费处理，请手动通过用户账户相关的功能处理。");
				} else if (rtCode.getInt("code") == -3) {
					System.out.println("数据不存在。");
				} else if (rtCode.getInt("code") < 0) {
					return new ReturnValue(-1, rtCode.getString("message"));
				}
			}
			int chargeid =Integer.parseInt(conswater.getChargeid());
			if(chargeid>0){
				// 先返款
				ReturnValue rb = getAccountService().rollbackCharge(conswater.getCardid(), remark, "02", feeNo,
					Integer.parseInt(conswater.getChargeid()), operatorId, operatorName);
				System.out.println("回滚费用rollbackCharge:" + rb.getCode() + " " + rb.getMessage());
				if (rb.getCode() == -2 || rb.getCode() == -3) {
					return new ReturnValue(-1, rb.getMessage());
				}
			}
			
			// 获取资源
			Integer res = getAccountService().getResourceCount(conswater.getCardid(), "02", feeNo);
			int unit = 0;
			if (res != null && res > 0) {
				unit = 1;
			}

			// 再扣费用
			ChargeReturnBean ret = getAccountService().charge(conswater.getCardid(), card.getAccountsn(), card.getCardtypesn(), feeType,
				conswater.getPkconswaterid(), operatorId, operatorName, remark, charge * (-1) + "", unit + "",
				0, true, null, null);
			System.out.println("扣费charge:" + ret.getCode() + "|" + ret.getMessage() + "|" + ret.getChargewatersn() + "|"
				+ ret.getResultPayCash() + "|" + ret.getResultPayResource());
			//给医生加钱
			this.saveDoctorPrice(conswater.getOperconfid(), 1, 1, conswater.getPkconswaterid(), charge, feeArr.getInt("feeTemplateId"));
			
			// 更新流水
			conswater.setOrders(orders);
			conswater.setFlag(2);
			conswater.setStartdatetime(DateUtil.parse(begTime.getTime()));
			conswater.setEnddatetime(DateUtil.parse(finishTime.getTime()));
			String premiumName = "";
			if (preArr[0] == 1) {
				if (preArr[1] >= 0)
					premiumName = "（优惠" + preArr[1] / 100 + "元）";
				else
					premiumName = "（" + preArr[2] / 100.0 + "折优惠）";
			}
			
			
			/*if (feeArr == null) {
				conswater.setMytfeename("未能获取资费");
			} else {
				conswater.setMytfeename("前" + feeArr[0] + "分钟" + feeArr[1]
						/ 100 + "元，后每分钟" + feeArr[2] / 100 + "元" + premiumName);
			}*/
			conswater.setCharge(charge * (-1));
			if (ret.getCode() !=1) {
				conswater.setChargeid(ret.getCode() + "");
			} else {
				conswater.setChargeid(ret.getChargewatersn() + "");
			}
			conswater.setOpertime(DateUtil.parse(new Date().getTime()));
			conswater.setConsmin(consMin);
			conswater.setRemark("[" + operatorName + " "
					+ DateUtil.dateFormat(new Date(), DateUtil.YMDHMS_FORMAT)
					+ "]:" + remark);
			conswater.setPkconswaterid(null);
			conswater.setFlag(2);//修改完成后更改ID
			DB.me().update(MyDatabaseEnum.boss, DB.me().createUpdateSql(
					conswater, "BOSS", MyTableNameEnum.MYT_CONSWATER, 
					" pkconswaterid = " + pkconswaterid));
			
			return new ReturnValue(1, "处理名医通异常流水成功。");
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnValue(-1, e.getMessage());
		}
	}

	public JSONObject changeBilling(Integer resUid, Integer serviceId,
			Integer resId, Integer time) {
		JSONObject rt = new JSONObject();
		try {
			com.yihu.baseinfo.api.DoctorServiceApi doctorServiceApi = Rpc.get(
					DoctorServiceApi.class,
					ConfigUtil.getInstance().getUrl("url.baseinfo"));
			JSONObject dcJson = new JSONObject();
			dcJson.put("doctorUid", resUid);
			dcJson.put("serviceId", serviceId);
			ServiceResult<String> priceJson = doctorServiceApi
					.getDoctorPrice(dcJson.toString());
			if (priceJson.getCode() > 0) {
				JSONObject docPrice = new JSONObject(priceJson.getResult());
				if (docPrice.getInt("size") > 0) {
					JSONArray pris = docPrice.getJSONArray("serviceList");
					JSONObject price = pris.getJSONObject(0);
					if (price.getInt("typeId") == 1) {
						int beTime = price.getInt("minuteBefore");
						int bePrice = price.getInt("minuteBeforePrice");
						int afPrice = price.getInt("perMinutePrice");
						if (time > beTime) {
							int rtPrice = (time - beTime) * afPrice + bePrice;
							rt.put("price", rtPrice);
							rt.put("message", "前" + beTime + "分钟" + bePrice / 100 + "元，后每分钟" + afPrice / 100 + "元");
							rt.put("feeTemplateId", price.getInt("feeTemplateId"));
						} else {
							rt.put("price", bePrice);
							rt.put("message", "前" + beTime + "分钟" + bePrice / 100 + "元，后每分钟" + afPrice / 100 + "元");
							rt.put("feeTemplateId", price.getInt("feeTemplateId"));
						}
						return rt;
					} else if (price.getInt("typeId") == 2) {
						int total = price.getInt("minuteNum");
						int tolalPrice = price.getInt("price");
						int rtprice = (time % total > 0 ? (time / total + 1) : (time / total)) * tolalPrice;
						rt.put("price", rtprice);
						rt.put("message", "每" + total + "分钟" + tolalPrice / 100 + "元");
						rt.put("feeTemplateId", price.getInt("feeTemplateId"));
						return rt;
					} else {
						rt.put("price", price.getInt("price"));
						rt.put("message", "医生本次咨询费用" + price.getInt("price") / 100 + "元");
						rt.put("feeTemplateId", price.getInt("feeTemplateId"));
						return rt;
					}
				}
			}
			rt.put("price", 0);
			rt.put("message", "未找到相关付费数据。");
			return rt;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rt;
	}

	public JSONObject deleteDoctorPrice(Integer resUid, 
			Integer serviceRecordId) {
		JSONObject rt = new JSONObject();
			try {
				com.yihu.baseinfo.api.DoctorServiceApi doctorServiceApi = Rpc.get(
						DoctorServiceApi.class,
						ConfigUtil.getInstance().getUrl("url.baseinfo"));
				JSONObject dcJson = new JSONObject();
				dcJson.put("doctorUid", resUid);
				dcJson.put("serviceRecordId", serviceRecordId);
				dcJson.put("serviceId", "1");
				ServiceResult<String> rtJson = doctorServiceApi
						.exceptionBill(dcJson.toString());
				rt.put("code", rtJson.getCode());
				rt.put("message", rtJson.getMessage());
				return rt;
			
				
			/*	Sql sql = DB.me().createSql(MySqlNameEnum.getDoctorAccPrice);
			// 根据医生ID获取配置
			StringBuffer sbSql = new StringBuffer(" WHERE 1=1  ");
			if (resUid != null ) {
				sbSql.append(" AND doctorUid  = '" + resUid + "' ");
				sbSql.append(" AND ServiceId  = 1");
			}
			sql.addVar("@p", sbSql.toString());
			DoctorAccPriceView doctorB = DB.me().queryForBean(
					MyDatabaseEnum.BaseInfo, sql, DoctorAccPriceView.class);
			
			Sql dcsql = DB.me().createSql(MySqlNameEnum.getDoctorBill);
			StringBuffer dcsbSql = new StringBuffer(" WHERE state=1  ");
			dcsbSql.append(" AND DoctorUid = '" +doctorB.getDoctorUid() + "'");
			dcsbSql.append(" AND ServiceRecordId = '" +serviceRecordId+ "'");
			dcsbSql.append(" AND ServiceId = 1 ");
			dcsbSql.append(" AND DA_ID = '" +doctorB.getDa_id()+ "'");

			dcsql.addVar("@p", dcsbSql.toString());
			DoctorBillBean dcBill = DB.me().queryForBean(MyDatabaseEnum.BaseInfo, 
					dcsql, DoctorBillBean.class);
			DoctorBillBean dc = new DoctorBillBean();
			dc.setState(0);
			DB.me().update(
					MyDatabaseEnum.BaseInfo,
					DB.me().createUpdateSql(dc, "dbo",MyTableNameEnum.DC_Bill,
							" billWaterId= " + dcBill.getBillWaterId()));
			DoctorAccountBean dab = new DoctorAccountBean();
			dab.setBalance(doctorB.getBalance()-dcBill.getPrice());
			DB.me().update(MyDatabaseEnum.BaseInfo,
				DB.me().createUpdateSql(dab,"dbo", MyTableNameEnum.DC_DoctorAccount, " Da_id = " + doctorB.getDa_id()));
*/
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return rt;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return rt;
		}
	}
	
	public int saveDoctorPrice(Integer resUid,Integer serviceId,Integer resId,Integer serviceRecordId,Integer price ,int feeTemplateId){
		try {
			
			com.yihu.baseinfo.api.DoctorServiceApi
			doctorServiceApi=Rpc.get(DoctorServiceApi.class
					   , ConfigUtil.getInstance().getUrl("url.baseinfo"));
			JSONObject dcBillJson = new JSONObject();
			dcBillJson.put("doctorUid", resUid);
			dcBillJson.put("serviceRecordId", serviceRecordId);
			dcBillJson.put("serviceId", serviceId);
			dcBillJson.put("feeTemplateId", feeTemplateId);
			dcBillJson.put("price", price);
			System.out.println(dcBillJson.toString());
			ServiceResult<String> rt =  doctorServiceApi.insertBill(dcBillJson.toString());
			System.out.print(rt.getMessage());
			return rt.getCode();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	public int changeDoctorPrice(Integer resUid,Integer serviceId,Integer resId,Integer serviceRecordId){
		try {
			
			com.yihu.baseinfo.api.DoctorServiceApi
			doctorServiceApi=Rpc.get(DoctorServiceApi.class
					   , ConfigUtil.getInstance().getUrl("url.baseinfo"));
			JSONObject dcBillJson = new JSONObject();
			dcBillJson.put("doctorUid", resUid);
			dcBillJson.put("serviceRecordId", serviceRecordId);
			dcBillJson.put("serviceId", serviceId);
			ServiceResult<Integer> rt =  doctorServiceApi.queryBillCount(dcBillJson.toString());
			return rt.getResult();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
}
