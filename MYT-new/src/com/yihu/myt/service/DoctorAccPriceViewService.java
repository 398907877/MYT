package com.yihu.myt.service;

import java.sql.SQLException;

import com.yihu.baseinfo.api.DoctorServiceApi;
import com.yihu.myt.IDoctorAccPriceViewService;
import com.yihu.myt.enums.MyDatabaseEnum;
import com.yihu.myt.enums.MySqlNameEnum;
import com.yihu.myt.vo.DoctorAccPriceView;
import com.yihu.myt.ConfigUtil;
import com.common.json.JSONArray;
import com.common.json.JSONException;
import com.common.json.JSONObject;
import com.coreframework.db.DB;
import com.coreframework.db.Sql;
import com.coreframework.remoting.reflect.Rpc;
import com.coreframework.vo.ServiceResult;

public class DoctorAccPriceViewService implements IDoctorAccPriceViewService{
	public JSONObject getBill(Integer resUid, Integer serviceId,Integer resId){
		JSONObject rt = new JSONObject();
		try {
			DoctorAccPriceView vDoctroAccPrice = new DoctorAccPriceView();
			Sql sql = DB.me().createSql(MySqlNameEnum.getDoctorAccPrice);
			StringBuffer sbSql = new StringBuffer(" WHERE 1=1 ");
			if(resUid!=null){
				sbSql.append(" AND doctorUid  ='"+resUid+"'");
			}
			if(serviceId!=null){
				sbSql.append(" AND serviceId  ='"+serviceId+"'");
			}if(resId!=null){
				sbSql.append(" AND resId  ='"+resId+"'");
			}
			sql.addVar("@p", sbSql.toString());
			vDoctroAccPrice = DB.me().queryForBean(MyDatabaseEnum.BaseInfo, sql,
					DoctorAccPriceView.class);
			if(vDoctroAccPrice==null){
				rt.put("message", "咨费未确定，不能咨询！");
				return rt;
			}
			if (vDoctroAccPrice.getTypeId() == 1) {
				int beTime = vDoctroAccPrice.getMinuteBefore();
				int bePrice = vDoctroAccPrice.getMinuteBeforePrice();
				int afPrice = vDoctroAccPrice.getPerMinutePrice();
				rt.put("message", "前" + beTime + "分钟" + bePrice/100 + "元，后每分钟"
							+ afPrice/100 + "元");
				return rt;
			} else if (vDoctroAccPrice.getTypeId() == 2) {
				int tolalPrice = vDoctroAccPrice.getPrice();			
				rt.put("message", "医生咨询费用" + tolalPrice/100 + "元");
				return rt;
			} else {
				rt.put("message", "医生咨询费用" + vDoctroAccPrice.getPrice()/100 + "元");
				return rt;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return rt;
		
	}
	public JSONObject getDoctorBill(Integer resUid, Integer serviceId,Integer resId,Integer time){
		JSONObject rt = new JSONObject();
		try {
			DoctorAccPriceView vDoctroAccPrice = new DoctorAccPriceView();
			vDoctroAccPrice.setDoctorUid(resUid);
			vDoctroAccPrice.setServiceId(serviceId);
			Sql sql = DB.me().createSql(MySqlNameEnum.getDoctorAccPrice);
			StringBuffer sbSql = new StringBuffer(" WHERE 1=1 ");
			if(resUid!=null){
				sbSql.append(" AND doctorUid  ='"+resUid+"'");
			}
			if(serviceId!=null){
				sbSql.append(" AND serviceId  ='"+serviceId+"'");
			}
			if(resId!=null){
				sbSql.append(" AND resId  ='"+resId+"'");
			}
			sql.addVar("@p", sbSql.toString());
			vDoctroAccPrice = DB.me().queryForBean(MyDatabaseEnum.BaseInfo, sql,
					DoctorAccPriceView.class);
			if (vDoctroAccPrice.getPrice() == 0) {
				rt.put("price", 0);
				rt.put("message", "未找到相关付费数据。");
				return rt;
			}
			if (vDoctroAccPrice.getTypeId() == 1) {
				int beTime = vDoctroAccPrice.getMinuteBefore();
				int bePrice = vDoctroAccPrice.getMinuteBeforePrice();
				int afPrice = vDoctroAccPrice.getPerMinutePrice();
				if (time > beTime) {
					rt.put("price", ((time - beTime) * afPrice + bePrice) *6/10/100 );
					
				} else {
					rt.put("price", bePrice*6/10/100);
				}
				return rt;
			} else if (vDoctroAccPrice.getTypeId() == 2) {
				int total = vDoctroAccPrice.getMinuteNum();
				int tolalPrice = vDoctroAccPrice.getPrice();
				int price = (time / total + 1) * tolalPrice;
				rt.put("price", price*6/10/100);
				return rt;
			} else {
				rt.put("price", vDoctroAccPrice.getPrice()*6/10/100);
				return rt;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return rt;
		} catch (JSONException e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return rt;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rt;

		
	}
	/**
	 * 获取医生资费信息
	 * @param restParam
	 * @return
	 */
	public JSONObject changeBilling(Integer resUid, Integer serviceId)
			 {
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
						rt.put("price", price.getInt("price"));
						rt.put("message", "前" + beTime + "分钟" + bePrice / 100 + "元，后每分钟" + afPrice / 100 + "元");
						rt.put("feeTemplateId", price.getInt("feeTemplateId"));
						return rt;
					} else if (price.getInt("typeId") == 2) {
						int total = price.getInt("minuteNum");
						int tolalPrice = price.getInt("price");
						rt.put("price", price.getInt("price"));
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
}
