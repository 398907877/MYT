package com.yihu.main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONException;
import com.coreframework.db.DB;
import com.coreframework.db.Sql;
import com.coreframework.ioc.Ioc;
import com.coreframework.remoting.Url;
import com.coreframework.remoting.reflect.Rpc;
import com.coreframework.vo.ServiceResult;
import com.yihu.api.api.DoctorBillDataApi;
import com.yihu.baseinfo.api.CommonApi;
import com.yihu.baseinfo.api.DoctorInfoApi;
import com.yihu.baseinfo.api.DoctorServiceApi;
import com.yihu.baseinfo.vo.DoctorVo;
import com.yihu.myt.ConfigUtil;
import com.yihu.myt.IConswaterService;
import com.yihu.myt.IOperconfidService;
import com.yihu.myt.enums.DepartmentsSqlNameEnum;
import com.yihu.myt.enums.MyDatabaseEnum;
import com.yihu.myt.enums.MyTableNameEnum;
import com.yihu.myt.enums.QuesMainSqlNameEnum;
import com.yihu.myt.service.service.IConsumerOrdersService;
import com.yihu.myt.service.service.IOperconfigService;
import com.yihu.myt.util.DateUtil;
import com.yihu.myt.vo.ConsumerOrdersVo;
import com.yihu.myt.vo.DepartmentsVo;


public class Test {
	private static IConswaterService conswaterService = Ioc
			.get(IConswaterService.class);
	private static IConsumerOrdersService consumerOrdersService = Ioc
			.get(IConsumerOrdersService.class);

	 private static IOperconfidService operService = Ioc
		.get(IOperconfidService.class); // 医生配置信息服务接口(旧)
	 
	public static void main(String[] args) {
		 try {

		// DB.me().addDataSource(MyDatabaseEnum.YiHuNet2008.toString(),
		// "YiHuNet2008", "10.0.200.6", 1433, "sa", "123");

		/*
		 * com.yihu.baseinfo.api.DoctorInfoApi doctorInfoApi; doctorInfoApi =
		 * Rpc.get(DoctorInfoApi.class ,
		 * ConfigUtil.getInstance().getUrl("url.baseinfo"));
		 */
		// DoctorVoMore obj=new DoctorVoMore();
		// obj.setStandardDeptID(node);//标准科室ID
		// obj.setServiceStatusSearch("2");//有开通电话咨询，固定传2
		// obj.setProvinceId(orgid);//省份ID
		// ServiceResult<List<DoctorVo>> dsr=doctorInfoApi.queryDoctorList(obj);
		/*
		 * System.out.println(ConfigUtil.getInstance().getUrl("url.baseinfo"));
		 * JSONObject json = new JSONObject(); json.put("provinceId", "19");
		 * json.put("standardDeptId", "0306"); json.put("serviceStatusSearch",
		 * 2); json.put("main", 1);
		 * 
		 * 
		 * JSONObject obj = new JSONObject(); obj.put("serviceStatusSearch",
		 * "2"); obj.put("provinceId", "19"); //obj.put("doctorNameLike",
		 * "王玮蓁");
		 * 
		 * com.yihu.baseinfo.api.DoctorServiceApi doctorServiceApi = Rpc.get(
		 * DoctorServiceApi.class,
		 * ConfigUtil.getInstance().getUrl("url.baseinfo"));
		 * obj.put("doctorUid", 36036); obj.put("serviceRecordId", 242442);
		 * obj.put("serviceId", 1); ServiceResult<String> returnVa
		 * =doctorServiceApi.exceptionBill(obj.toString()); JSONObject doctors =
		 * new JSONObject(returnVa.getResult());
		 */
		/*
		 * DoctorBillDataApi ApiClient = Rpc.get(DoctorBillDataApi.class, new
		 * Url("10.0.100.124", 8912));
		 * 
		 * String ids="275, 274";
		 * 
		 * ConsumerOrdersVo vo = new ConsumerOrdersVo(); vo.setCO_IDs(ids);
		 * List<ConsumerOrdersVo> cons = consumerOrdersService
		 * .queryConsumerOrdersListByCondition(vo);
		 * //ServiceResult<List<DoctorVo>> dsr =
		 * doctorInfoApi.queryDoctorList(json.toString());
		 * //System.out.println(dsr); int i = 5; int m = 4; //Date[] days =
		 * DateUtil.getRollWeekZH(new Date()); Date[] days
		 * =DateUtil.getRollMonth(new Date());
		 * 
		 * System.out.println(cons);
		 */

		/*
		 * SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd"); String
		 * str="20110823"; Date dt=sdf.parse(str); Calendar rightNow =
		 * Calendar.getInstance(); rightNow.setTime(new Date());
		 * rightNow.add(Calendar.YEAR,-1);//日期减1年
		 * rightNow.add(Calendar.MONTH,3);//日期加3个月
		 * rightNow.add(Calendar.DAY_OF_YEAR,10);//日期加10天
		 * 
		 * rightNow.add(Calendar.YEAR, 10); Date dt1=rightNow.getTime(); String
		 * reStr = DateUtil.dateFormat(dt1); System.out.println(reStr);
		 */
		/*
		 * com.yihu.baseinfo.api.DoctorInfoApi
		 * doctorInfoApi=Rpc.get(DoctorInfoApi.class ,
		 * ConfigUtil.getInstance().getUrl("url.baseinfo"));
		 */
		/*
		 * json.put("provinceId", "14"); json.put("standardDeptId", "0502");
		 * json.put("serviceStatusSearch", 2); json.put("main", 1);
		 */

		// ServiceResult<List<DoctorVo>> dsr =
		// doctorInfoApi.queryDoctorList(json.toString());
		/*
		 * JSONObject json = new JSONObject(); json.put("doctorUid",
		 * "710014460"); com.yihu.baseinfo.api.CommonApi commonApi = Rpc.get(
		 * CommonApi.class, ConfigUtil.getInstance().getUrl("url.baseinfo"));
		 * ServiceResult<String> rtdepts = commonApi
		 * .getSmallDepartmentList(json.toString());
		 */

		/*
		 * com.yihu.baseinfo.api.DoctorInfoApi doctorInfoApi = Rpc.get(
		 * DoctorInfoApi.class,
		 * ConfigUtil.getInstance().getUrl("url.baseinfo"));
		 * ServiceResult<String> rtdc = doctorInfoApi
		 * .getComplexDoctorByUid(710014460);
		 */

		/*
		 * try { com.yihu.baseinfo.api.DoctorInfoApi doctorInfoApi = Rpc.get(
		 * DoctorInfoApi.class,
		 * ConfigUtil.getInstance().getUrl("url.baseinfo"));
		 * 
		 * 
		 * ServiceResult<String> rtdc = doctorInfoApi
		 * .getComplexDoctorByUid(28306); System.out.print(rtdc.getResult());
		 * 
		 * } catch (Exception e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */

		/* System.out.println(-2000 == -2000); */
		/*
		 * } catch (JSONException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (Exception e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); }
		 */
		/* String s1 ="2013-12-19 23:59:59" ;
				String s2 = DateUtil.dateFormat(new Date());
				java.text.DateFormat df = new java.text.SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				java.util.Calendar c1 = java.util.Calendar.getInstance();
				java.util.Calendar c2 = java.util.Calendar.getInstance();
				c1.setTime(df.parse(s1));
				c2.setTime(df.parse(s2));
				int result = c1.compareTo(c2);
		System.out.println(result );*/
			/*Date beginDate =  DateUtil.stringFormat("2013-08-10 23:59:59");
			//Date beginDate =  DateUtil.stringFormat("2013-08-10 23:59:59");
			
			List<Date> dates = DateUtil.getDatesBetweenTwoDate(beginDate, new Date());
			for(Date day : dates){
				Sql sql = DB.me().createSql(
						QuesMainSqlNameEnum.insertRepotData);
				sql.addParamValue(DateUtil.dateFormat(day));
				sql.addParamValue(DateUtil.dateFormat(day));
				sql.addParamValue(DateUtil.dateFormat(day));
				sql.addParamValue(DateUtil.dateFormat(day));
				sql.addParamValue(DateUtil.dateFormat(day));
				sql.addParamValue(DateUtil.dateFormat(day));
				int r = DB.me().insert(
						MyDatabaseEnum.YiHuNet2008,sql
						);
				System.out.println(r);
				System.out.println(DateUtil.dateFormat(day));
			}*/
		 
			 
			/* String str = "a,b,c,";
			 String str1 = ",a,b,c,";
			 String[] strs = str.split(",");
			 String[] str1s = str1.split(",");
			 for(int i=0;i<strs.length ;i++){
				 System.out.println(strs[i] + "_");
			 }
			 for(int i=0;i<str1s.length ;i++){
				 System.out.println(str1s[i] + "_");
			 }*/
			 /*com.common.json.JSONObject obj = operService.getConsphoneByDoctor(6944);
			 com.common.json.JSONArray phones = obj.getJSONArray("result");
			 for(int i = 0 ; i < phones.length() ;i++){
					 System.out.print(	phones.getJSONObject(i).getString("consphone"));
				}
			*/
			 
		/*	 
			 String s1 = DateUtil.dateFormat(new Date(),DateUtil.YMD_FORMAT);
			 String s2 = s1 + " 09:00:00";
			 String s3 = s1 + " 22:00:00";
			 DateUtil.getIfInTime(s2, s3)
			 */
			/* String serviceStatus = "202";
			 String rt= serviceStatus.substring(1,2) ;
			 System.out.println( serviceStatus.substring(1,2).equals("0"));*/
		/*com.yihu.baseinfo.api.DoctorInfoApi doctorInfoApi;
		try {
			doctorInfoApi = Rpc.get(DoctorInfoApi.class, ConfigUtil
					.getInstance().getUrl("url.baseinfo"));

			JSONObject obj = new JSONObject();
			obj.put("provinceId", "17");
			obj.put("standardDeptId", "7102");
			obj.put("serviceStatusSearch", "2");
			// json.put("main", 1);
			ServiceResult<List<DoctorVo>> dsr = doctorInfoApi
					.queryDoctorList(obj.toString());
			List<DoctorVo> lstMdvBean = dsr.getResult();
			ArrayList dcs = new ArrayList();
			Integer temp = 0;
			for (int i = 0; i < lstMdvBean.size(); i++) {
				System.out.println(((DoctorVo) lstMdvBean.get(i))
						.getDoctorName()
						+ "==="
						+ ((DoctorVo) lstMdvBean.get(i)).getDoctorUid());
			}
			for (DoctorVo dc : lstMdvBean) {
				if (!dcs.isEmpty()) {
					for (int i = 0; i < dcs.size(); i++) {
						if (((DoctorVo) dcs.get(i)).getDoctorUid().equals(
								dc.getDoctorUid())) {
							System.out.println(((DoctorVo) dcs.get(i))
									.getDoctorUid()
									+ "=="
									+ ((DoctorVo) dcs.get(i)).getDoctorName());
							temp = 1;
						}
					}
				}
				if (temp.equals(0)) {
					dcs.add(dc);
					System.out.println(dc.getDoctorName() + "--"
							+ dc.getDoctorUid());
					
				}
				temp = 0;
			}
			for (int i = 0; i < dcs.size(); i++) {
				// System.out.println(((DoctorVo)dcs.get(i)).getDoctorName());
			}*/

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
