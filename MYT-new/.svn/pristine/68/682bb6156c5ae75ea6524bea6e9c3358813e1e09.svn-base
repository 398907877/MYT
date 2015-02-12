package com.yihu.myt.job;

import java.sql.Timestamp;
import java.util.Date;

import com.boss.sdk.ExecutionContext;
import com.boss.sdk.ExecutionResult;
import com.common.json.JSONArray;
import com.common.json.JSONObject;
import com.coreframework.db.DB;
import com.coreframework.db.Sql;
import com.coreframework.log.LogBody;
import com.coreframework.log.Logger;
import com.coreframework.util.DateOper;
import com.yihu.myt.enums.MyDatabaseEnum;
import com.yihu.myt.enums.MySqlNameEnum;
import com.yihu.myt.enums.MyTableNameEnum;
import com.yihu.myt.util.DateUtil;
import com.yihu.myt.vo.DoctorAppointReport;

public class AppointReportJob {

	/**
	 * 生成前一天用户咨询医生统计报表
	 * 
	 * @param c
	 * @return
	 */
	public ExecutionResult createReport(ExecutionContext c) {
		try {
			//String yesterday = DateOper.addDate(DateOper.formatDate(new Date(), "yyyy-MM-dd"), -1, "yyyy-MM-dd");
			String yesterday = "2014-04-01";
			System.out.println("yesterday:" + yesterday);
			Sql sql = DB.me().createSql(MySqlNameEnum.getWaterReportForUser);
			sql.addParamValue(yesterday);
			JSONArray array = DB.me().queryForJson(MyDatabaseEnum.boss, sql).getJSONArray("result");
			if (array != null && array.length() > 0) {
				for (int i = 0; i < array.length(); i++) {
					JSONObject obj = (JSONObject) array.get(i);
					//System.out.println(obj);
					String custphone = obj.getString("custphone");
					if(custphone==null) {
						custphone = "";
					} else {
						//外地手机
						if(custphone.length()==12 && custphone.indexOf("01")==0)
							custphone = custphone.substring(1);
					}
					if(custphone.length()>6&&custphone.length()<=8)
						custphone = custphone.substring(0,3)+"****"+custphone.substring(custphone.length()-3);
					if(custphone.length()>=11)
						custphone = custphone.substring(0,4)+"****"+custphone.substring(custphone.length()-4);
					DoctorAppointReport report = new DoctorAppointReport();
					report.setYears(obj.getInt("years"));
					report.setMonths(obj.getInt("months"));
					report.setDoctorname(obj.getString("doctorname"));
					report.setBasedoctorid(Integer.parseInt(obj.getString("doctorid")));
					report.setAccountsn(obj.getInt("accountsn"));
					report.setDoctorUid(obj.getInt("doctoruid"));
					report.setTypeid(2);
					report.setMembername(custphone);
					DoctorAppointReport temp = DB.me().queryForBean(MyDatabaseEnum.ReportData,
						DB.me().createSelect(report, MyTableNameEnum.Doctor_AppointReport, "dbo"),
						DoctorAppointReport.class);
					if (temp != null && DateOper.formatDate(temp.getLastserivcetime(), "yyyy-MM-dd").equals(yesterday))
						return new ExecutionResult(yesterday + "报表已生成过，无需再生成");
					if (temp == null) {
						report.setMembername(custphone);
						report.setCount(obj.getInt("counter"));
						report.setInserttime(DateOper.getNowDateTime());
						report.setLastmodify(DateOper.getNowDateTime());
						report.setLastserivcetime(new Timestamp(DateOper.parse(
							obj.getString("lastservicetime").substring(0, 19)).getTime()));
						DB.me().insert(MyDatabaseEnum.ReportData,
							DB.me().createInsertSql(report, MyTableNameEnum.Doctor_AppointReport, "dbo"));
					} else {
						Timestamp lasttime = new Timestamp(DateOper.parse(
							obj.getString("lastservicetime").substring(0, 19)).getTime());
						temp.setCount(obj.getInt("counter") + temp.getCount());
						temp.setLastmodify(DateOper.getNowDateTime());
						if (temp.getLastserivcetime().getTime() < lasttime.getTime())
							temp.setLastserivcetime(lasttime);
						DB.me().update(
							MyDatabaseEnum.ReportData,
							DB.me().createUpdateSql(
								temp,
								"dbo",
								MyTableNameEnum.Doctor_AppointReport,
								" typeid=2 and doctorname ='" + temp.getDoctorname() + "' and months="
									+ temp.getMonths() + " and basedoctorid=" + temp.getBasedoctorid() + " and years="
									+ temp.getYears() + " and accountsn=" + temp.getAccountsn() +" and Membername='"+custphone+"'"));
					}
				}
			}

			return new ExecutionResult("生成" + yesterday + "报表完成,共更新" + (array != null ? array.length() : 0) + "条数据");
		} catch (Exception e) {
			e.printStackTrace();
			this.logInfo(e.getMessage());
			return new ExecutionResult("生成报表异常!"+e.getMessage());
		}
	}

	public static void main(String[] args) {
		AppointReportJob job = new AppointReportJob();
		System.out.println(job.createReport(null).getMessage());
		
	}

	private void logInfo(String msg) {
		Logger.get().info("com.yihu.myt.job.AppointReportJob", LogBody.me().set("msg", msg));
	}
}
