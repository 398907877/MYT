package com.yihu.api.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.yihu.account.api.IAccountService;
import com.yihu.account.api.ReturnValueBean;
import com.yihu.api.api.DoctorBillDataApi;
import com.yihu.baseinfo.api.DoctorAccountApi;
import com.yihu.baseinfo.api.DoctorServiceApi;
import com.yihu.myt.util.DateUtil;
import com.yihu.myt.util.StringUtil;
import com.coreframework.db.DB;
import com.coreframework.db.JdbcConnection;
import com.coreframework.ioc.Ioc;
import com.coreframework.log.LogBody;
import com.coreframework.log.Logger;
import com.coreframework.remoting.Url;
import com.coreframework.remoting.reflect.Rpc;
import com.coreframework.vo.ServiceResult;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONException;

import com.yihu.myt.ConfigUtil;
import com.yihu.myt.IConswaterService;
import com.yihu.myt.enums.MyDatabaseEnum;
import com.yihu.myt.mgr.ApiUtil;
import com.yihu.myt.mgr.BusinessManager;
import com.yihu.myt.service.service.ICloseMainService;
import com.yihu.myt.service.service.IConsumerOrdersService;
import com.yihu.myt.service.service.ICreditsRecordService;
import com.yihu.myt.service.service.IPatientService;
import com.yihu.myt.service.service.IQuesCloseWaterService;
import com.yihu.myt.service.service.IQuesMainService;
import com.yihu.myt.vo.CloseMainVo;
import com.yihu.myt.vo.ConsumerOrdersVo;
import com.yihu.myt.vo.CreditsRecordVo;
import com.yihu.myt.vo.DoctorVo;
import com.yihu.myt.vo.MytConswaterBean;
import com.yihu.myt.vo.PatientVo;
import com.yihu.myt.vo.QuesCloseWaterVo;
import com.yihu.myt.vo.QuesMainVo;
import com.yihu.wsgw.api.InterfaceMessage;
import com.yihu.wsgw.api.WsUtil;

public class DoctorBillDataApiImpl implements DoctorBillDataApi {
	private static IConswaterService conswaterService = Ioc
			.get(IConswaterService.class);
	private static IConsumerOrdersService consumerOrdersService = Ioc
			.get(IConsumerOrdersService.class);
	private static IQuesMainService quesMainService = Ioc
			.get(IQuesMainService.class);
	private static IPatientService patientService = Ioc
			.get(IPatientService.class);
	private static IQuesCloseWaterService quesCloseWaterService = Ioc
			.get(IQuesCloseWaterService.class);
	private static ICreditsRecordService creditsRecordService = Ioc
			.get(ICreditsRecordService.class);

	public static void main(String[] args) throws Exception {
		
		JSONObject json = new JSONObject();
		json.put("userid", "445835");
		json.put("coid", "27279");
		json.put("queID", "141902");
		json.put("OperatorId", "1111111");
		String overQuesRefund = "{'userid':'11392346','coid':'340','queID':'4047','OperatorId':'1111111'}";
		String getDoctorBillMyt = "{'year':'2013','month':'11','doctorUid':'5685','page':'2','pageSize':'10'}";
		String getMYTBillList = "{'year':2014,'month':06,'doctorUid':710040076,'page':0,'pageSize':10}";
		DoctorBillDataApiImpl api = new DoctorBillDataApiImpl();
		InterfaceMessage msg = new InterfaceMessage();
		msg.setParam(overQuesRefund);
		String rt = api.overQuesRefund(msg);
		System.out.println(rt.toString());
		
	}
	
	

	public String overQuesRefundforAPI(InterfaceMessage msg) {
		String tag = "overQuesRefundforAPI";
		try {
			JSONObject json = JSONObject.fromObject(msg.getParam());
			
			int doctorID = json.get("doctorID") == null ? null : json
					.getInt("doctorID");
			
			Integer userid = json.get("userid") == null ? null : json
					.getInt("userid");
			Integer coid = json.get("coid") == null ? null : json
					.getInt("coid");
			Integer queID = json.get("queID") == null ? null : json
					.getInt("queID");
			int operatorID = json.get("OperatorId") == null ? null : json
					.getInt("OperatorId");
			/*
			 * String operatorName = json.get("OperatorName") == null ? null :
			 * json.getString("OperatorName");
			 */

			com.yihu.account.api.IAccountService accountService = Rpc.get(
					IAccountService.class,
					ConfigUtil.getInstance().getUrl("url.account"));
			com.yihu.baseinfo.api.DoctorServiceApi doctorServiceApi = Rpc.get(
					DoctorServiceApi.class,
					ConfigUtil.getInstance().getUrl("url.baseinfo"));
			JdbcConnection conn = null;
			
			QuesMainVo qvo = new QuesMainVo();
			qvo.setQUESMAIN_ID(queID);
			qvo = quesMainService.queryQuesMainByCondition(qvo);
			ConsumerOrdersVo co = new ConsumerOrdersVo();
			co.setASK_QuesID(queID);
			co = consumerOrdersService.queryConsumerOrdersByQuesID(co);
			
			ReturnValueBean clrt = accountService.clearFrozen(userid, coid,
					"12", "01");
			if(clrt.getCode()!= -2000){
				if (clrt.getCode() < 0) {
					return ApiUtil.getJsonRt(-2000, clrt.getMessage());
				}
			}
			// 开始事务
			conn = DB.me().getConnection(MyDatabaseEnum.YiHuNet2008);
			conn.beginTransaction(50000);
			ConsumerOrdersVo cVo = new ConsumerOrdersVo();
			cVo.setCO_Status(-1);
			cVo.setCO_ID(coid);
			int cort = consumerOrdersService
					.updateCOrdersByCondition(cVo, conn);
			if (cort < 0) {
				conn.rollback();
				ApiUtil.getJsonRt(-14444, "更新订单状态失败");
			}
			QuesMainVo qusVo = new QuesMainVo();
			qusVo.setQUESMAIN_ID(queID);
			qusVo.setASK_UserID(userid);
			qusVo.setQD_Price(0);
			qusVo.setQD_DoctorGetPrice(0);
			qusVo.setQD_OrdersStatus(5);
			qusVo.setQD_CheckStatus(1);
			qusVo.setQD_Status(1);
			int qmrt = quesMainService.updateQMainByCondition(qusVo, conn);
			System.out.println("1111111111");
			if (qmrt < 0) {
				conn.rollback();
				ApiUtil.getJsonRt(-14444, "更新问题状态失败");
			}
			conn.commitTransaction(true);
			QuesCloseWaterVo vo = new QuesCloseWaterVo();
			if (operatorID == 1111111 || operatorID == 1000000
					|| operatorID == 9000029) {
				vo.setOperType(1);// 用户关闭
			} else if (operatorID == 9000023 || operatorID == 9000030) {
				vo.setOperType(3);// 医生关闭
			} else if (operatorID == 9000024 || operatorID == 9000031) {
				vo.setOperType(4);// 医学人员关闭
			} else {
				vo.setOperType(5);// 未知渠道关闭
			}
			vo.setCreateTime(DateUtil.dateFormat(new Date()));
			vo.setQuesID(queID);
			
			int rCont = quesCloseWaterService.queryQuesCloseWaterCountByCondition(vo);
			if(rCont ==0){
				int rtqu = quesCloseWaterService.insertQuesCloseWater(vo);
				if (rtqu < 0) {
					return WsUtil.getRetVal(msg.getOutType(), -14444,
							"问题关闭时间插入状态失败");
				}
			}
			// 医生退钱
			JSONObject dcBillJson = new JSONObject();
			dcBillJson.put("doctorUid", qvo.getASK_DoctorID());
			dcBillJson.put("serviceRecordId", co.getCO_ID());
			dcBillJson.put("serviceId", 2);
			dcBillJson.put("feeTemplateId", co.getFeeTemplateId());
			//dcBillJson.put("price",(-1)* price);// 医生扣费
			ServiceResult<String> rt = doctorServiceApi.exceptionBill
					(dcBillJson.toString());
			if (rt.getCode() < 0) {
				System.out.println(rt.getMessage());
			}
			
			
			
			//事件---
			
			
			EventUtilForMYT.sendEvenForClose(String.valueOf(doctorID),String.valueOf(queID));
			
			//TODO  wujiajun  --2
			
//			//--------------
//			System.out.println("医生的 id：：：：" +doctorID);
//			 DoctorVo docvo= closeMainService.getDocById(String.valueOf(doctorID) );
//			System.out.println(docvo.getDoctorName()+"医生名字！！！");
//			CloseMainVo clvo = new CloseMainVo();
//			clvo.setDOCID(docvo.getDoctorUid());
//			clvo.setDOCNAME(docvo.getDoctorName());
//			closeMainService.insertCloseMain(clvo);
//			//------------------ 
			
			
			
			return WsUtil.getRetVal(msg.getOutType(), 10000, "问题退款操作成功");
		} catch (JSONException e) {

			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("方法", tag).set("参数", msg.getParam())
							.set("异常", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"加载异常!" + StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("方法", tag).set("参数", msg.getParam())
							.set("异常", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"加载异常!" + StringUtil.getException(e));
		}
	}

	
	
	
	

	public ServiceResult<String> getDoctorBillMyt(String jsonStr) {
		ServiceResult<String> result = new ServiceResult<String>(-1, "入参有误");
		try {
			JSONObject json = JSONObject.fromObject(jsonStr);
			JSONArray jsonArr = new JSONArray();
			JSONObject dcJson = new JSONObject();
			int totalTime = 0;
			String year = json.get("year") == null ? null : json
					.getString("year");
			String month = json.get("month") == null ? null : json
					.getString("month");
			String doctorUid = json.get("doctorUid") == null ? null : json
					.getString("doctorUid");
			int page = json.get("page") == null ? null : json.getInt("page");
			int pageSize = json.get("pageSize") == null ? null : json
					.getInt("pageSize");
			com.yihu.baseinfo.api.DoctorServiceApi docServiceApi = Rpc.get(
					DoctorServiceApi.class,
					ConfigUtil.getInstance().getUrl("url.baseinfo"));
			String ids = "";
			dcJson.put("doctorUid", doctorUid);
			dcJson.put("year", year);
			dcJson.put("month", month);
			dcJson.put("serviceId", 1);
			dcJson.put("state", 1);
			JSONObject rtJSON = new JSONObject();
			ServiceResult<Integer> total = docServiceApi.queryBillCount(dcJson
					.toString());
			if (total.getCode() > 0) {
				rtJSON.put("total", total.getResult());
				if (total.getResult() == 0) {
					JSONArray bls = new JSONArray();
					rtJSON.put("totalPrice", 0);
					rtJSON.put("bills", bls);
					result.setResult(rtJSON.toString());
					result.setCode(1);
					result.setMessage("未获取医生明细数据");
					return result;
				}

			} else {
				result.setResult("");
				result.setCode(-14444);
				result.setMessage("数据获取失败");
				return result;
			}
			ServiceResult<String> bills = docServiceApi.queryBillList(
					dcJson.toString(), page, pageSize);
			if (bills.getCode() > 0) {
				JSONObject billsJs = JSONObject.fromObject(bills.getResult());
				JSONArray bs = billsJs.getJSONArray("bills");
				rtJSON.put("totalPrice", billsJs.getInt("totalPrice"));
				for (int i = 0; i < bs.size(); i++) {
					ids = ids + bs.getJSONObject(i).getInt("serviceRecordId")
							+ ", ";
				}
				int totalPrice = billsJs.getInt("totalPrice");
				if (ids == null || ids.equals("") || totalPrice == 0) {
					JSONArray bls = new JSONArray();
					rtJSON.put("totalPrice", 0);
					rtJSON.put("bills", bls);
					result.setResult(rtJSON.toString());
					result.setCode(1);
					result.setMessage("未获取医生明细数据");
					return result;
				}
				ids = StringUtils.substringBeforeLast(ids, ",");
				ServiceResult<List<MytConswaterBean>> cons = conswaterService
						.getMytConswaterResultList(ids);
				if (cons.getCode() > 0) {
					if (cons.getResult().size() > 0) {
						for (int i = 0; i < bs.size(); i++) {
							for (MytConswaterBean con : cons.getResult()) {
								if (con.getPkconswaterid().equals(
										bs.getJSONObject(i).getInt(
												"serviceRecordId"))) {
									JSONObject rtJons = new JSONObject();
									rtJons.put("startDateTime", DateUtil
											.dateFormat(con.getStartdatetime(),
													DateUtil.YMDHMS_FORMAT));
									rtJons.put("endDateTime", DateUtil
											.dateFormat(con.getEnddatetime(),
													DateUtil.YMDHMS_FORMAT));
									rtJons.put("inserTime", bs.getJSONObject(i)
											.getString("insertime"));
									rtJons.put("consMin", con.getConsmin());
									rtJons.put("price", bs.getJSONObject(i)
											.getInt("price"));
									jsonArr.add(rtJons);
									totalTime = totalTime + con.getConsmin();
									continue;
								}
							}
						}
					}
				} else {
					result.setResult("");
					result.setCode(-14444);
					result.setMessage("数据获取失败");
				}
			} else {
				result.setResult("");
				result.setCode(-14444);
				result.setMessage("数据获取失败");
			}
			rtJSON.put("totalTime", totalTime);
			rtJSON.put("bills", jsonArr);
			rtJSON.put("PageSize", pageSize);
			rtJSON.put("PageIndex", page);
			result.setCode(1);
			result.setMessage("数据加载成功");
			result.setResult(rtJSON.toString());
			return result;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			result.setResult("");
			result.setCode(-14444);
			result.setMessage("数据获取失败");
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public ServiceResult<String> getDoctorBillWys(String jsonStr) {
		ServiceResult<String> result = new ServiceResult<String>(-1, "入参有误");
		try {
			JSONArray jsonArr = new JSONArray();
			JSONObject json = JSONObject.fromObject(jsonStr);
			JSONObject dcJson = new JSONObject();
			String ids = "";
			String year = json.get("year") ==  null ? null : json
					.getString("year");
			String month = json.get("month") == null ? null : json
					.getString("month");
			String doctorUid = json.get("doctorUid") == null ? null : json
					.getString("doctorUid");
			int page = json.get("page") == null ? null : json.getInt("page");
			int pageSize = json.get("pageSize") == null ? null : json
					.getInt("pageSize");
			String serviceType = json.get("serviceType") == null ? null : json
					.getString("serviceType");
			com.yihu.baseinfo.api.DoctorServiceApi docServiceApi = Rpc.get(
					DoctorServiceApi.class,
					ConfigUtil.getInstance().getUrl("url.baseinfo"));
			dcJson.put("doctorUid", doctorUid);
			dcJson.put("year", year);
			dcJson.put("month", month);
			dcJson.put("serviceIds", serviceType);
			dcJson.put("state", 1);
			JSONObject rtJSON = new JSONObject();
			ServiceResult<Integer> total = docServiceApi.queryBillCount(dcJson
					.toString());
			if (total.getCode() > 0) {
				rtJSON.put("total", total.getResult());
				if (total.getResult() == 0) {
					JSONArray bls = new JSONArray();
					rtJSON.put("totalPrice", 0);
					rtJSON.put("bills", bls);
					result.setResult(rtJSON.toString());
					result.setCode(1);
					result.setMessage("未获取医生明细数据");
					return result;
				}
			} else {
				result.setResult("");
				result.setCode(-14444);
				result.setMessage("数据获取失败");
				return result;
			}
			ServiceResult<String> bills = docServiceApi.queryBillList(
					dcJson.toString(), page, pageSize);
			if (bills.getCode() > 0) {
				JSONObject billsJs = JSONObject.fromObject(bills.getResult());
				JSONArray bs = billsJs.getJSONArray("bills");
				rtJSON.put("totalPrice", billsJs.getInt("totalPrice"));
				for (int i = 0; i < bs.size(); i++) {
					ids = ids + bs.getJSONObject(i).getInt("serviceRecordId")
							+ ", ";
				}
				if (ids == null || ids.equals("")) {
					JSONArray bls = new JSONArray();
					rtJSON.put("totalPrice", 0);
					rtJSON.put("bills", bls);
					result.setResult(rtJSON.toString());
					result.setCode(1);
					result.setMessage("未获取医生明细数据");
					return result;
				}
				ids = StringUtils.substringBeforeLast(ids, ",");
				ConsumerOrdersVo vo = new ConsumerOrdersVo();
				vo.setCO_IDs(ids);
				List<ConsumerOrdersVo> cons = consumerOrdersService
						.queryConsumerOrdersListByCondition(vo);
				if (cons.size() > 0) {
					for (int i = 0; i < bs.size(); i++) {
						for (ConsumerOrdersVo con : cons) {
							int coID = con.getCO_ID();
							if (bs.getJSONObject(i).getInt("serviceRecordId") == coID) {
								bs.getJSONObject(i).put("content",
										con.getASK_Content());
								jsonArr.add(bs);
								continue;
							}
						}
					}
				}
			}
			rtJSON.put("bills", jsonArr);
			rtJSON.put("PageSize", pageSize);
			rtJSON.put("PageIndex", page);
			result.setResult(rtJSON.toString());
			result.setCode(1);
			result.setMessage("数据加载成功");
			return result;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			result.setResult("");
			result.setCode(-14444);
			result.setMessage("数据获取失败");
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result.setResult("");
			result.setCode(-14444);
			result.setMessage("数据获取失败");
			e.printStackTrace();
		}
		return result;
	}

	public ServiceResult<String> getDoctorCreditsRecord(String jsonStr) {
		ServiceResult<String> result = new ServiceResult<String>(-1, "入参有误");
		try {
			JSONObject json = JSONObject.fromObject(jsonStr);
			JSONArray jsonArr = new JSONArray();
			JSONObject rtJSON = new JSONObject();
			int doctorUid = json.get("doctorUid") == null ? null : json
					.getInt("doctorUid");
			int start = json.get("page") == null ? null : json.getInt("page");
			int pageSize = json.get("pageSize") == null ? null : json
					.getInt("pageSize");
			CreditsRecordVo crVo = new CreditsRecordVo();
			crVo.setASK_DoctorID(doctorUid);
			List<CreditsRecordVo> crLists = creditsRecordService
					.queryCreditsRecordList(crVo, start, pageSize);
			int cont = creditsRecordService.queryCreditsRecordListCont(crVo);
			JSONObject rtJons;

			JSONObject rts = JSONObject.fromObject(BusinessManager
					.getBusinName("MYT_CreditsType"));
			JSONArray crTypes = rts.getJSONArray("result");

			for (CreditsRecordVo vo : crLists) {
				rtJons = new JSONObject();
				rtJons.put("crType", vo.getCR_Type());
				rtJons.put("crCreateTime", vo.getCR_CreateTime());
				for (int i = 0; i < crTypes.size(); i++) {
					JSONObject crType = crTypes.getJSONObject(i);
					if (vo.getCR_CreditsType().equals("")
							|| vo.getCR_CreditsType() == null) {
						rtJons.put("crCreditsType", "完成咨询");
						continue;
					} else if (vo.getCR_CreditsType() == crType
							.getInt("businid")) {
						rtJons.put("crCreditsType",
								crType.getString("businname"));
						continue;
					}
				}
				rtJons.put("crCredits", vo.getCR_Credits());
				jsonArr.add(rtJons);
			}

			rtJSON.put("PageIndex", start);
			rtJSON.put("PageSize", pageSize);
			rtJSON.put("creditsRecords", jsonArr);
			rtJSON.put("creditsRecordCont", cont);
			result.setResult(rtJSON.toString());
			result.setCode(1);
			result.setMessage("数据加载成功");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			result.setResult("");
			result.setCode(-14444);
			result.setMessage("数据获取失败");
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result.setResult("");
			result.setCode(-14444);
			result.setMessage("数据获取失败");
			e.printStackTrace();
		}
		return result;
	}

	public String getWYSBillList(InterfaceMessage msg) {
		String tag = "getWYSBillList";
		try {
			JSONObject json = JSONObject.fromObject(msg.getParam());
			String year = json.get("year") == null ? null : json
					.getString("year");
			String month = json.get("month") == null ? null : json
					.getString("month");
			String doctorUid = json.get("doctorUid") == null ? null : json
					.getString("doctorUid");
			int page = json.get("page") == null ? null : json.getInt("page");
			int pageSize = json.get("pageSize") == null ? null : json
					.getInt("pageSize");
			String serviceType = json.get("serviceType") == null ? null : json
					.getString("serviceType");

			com.yihu.baseinfo.api.DoctorServiceApi docServiceApi = Rpc.get(
					DoctorServiceApi.class,
					ConfigUtil.getInstance().getUrl("url.baseinfo"));
			String ids = "";
			String queIDS = "";
			JSONObject dcJson = new JSONObject();
			dcJson.put("doctorUid", doctorUid);
			dcJson.put("year", year);
			dcJson.put("month", month);
			dcJson.put("serviceIds", serviceType);
			dcJson.put("state", 1);
			JSONObject rtJSON = new JSONObject();
			ServiceResult<Integer> total = docServiceApi.queryBillCount(dcJson
					.toString());
			if (total.getCode() > 0) {
				if (total.getResult() == 0) {
					return ApiUtil.getJsonRt(10000, "未获取医生明细数据");
				}
			} else {
				return ApiUtil.getJsonRt(-14444, "数据获取失败");
			}
			JSONArray bs = new JSONArray();
			int totalPrice = 0;
			ServiceResult<String> bills = docServiceApi.queryBillList(
					dcJson.toString(), page, pageSize);
			if (bills.getCode() > 0) {
				JSONObject billsJs = JSONObject.fromObject(bills.getResult());
				bs = billsJs.getJSONArray("bills");
				totalPrice = billsJs.getInt("totalPrice");
				for (int i = 0; i < bs.size(); i++) {
					if (bs.getJSONObject(i).getInt("serviceId") == 5) {
						queIDS = queIDS
								+ bs.getJSONObject(i).getInt("serviceRecordId")
								+ ", ";
					} else {
						ids = ids
								+ bs.getJSONObject(i).getInt("serviceRecordId")
								+ ", ";
					}
				}
				if (ids != "" || !ids.equals("")) {
					ids = StringUtils.substringBeforeLast(ids, ",");
					ConsumerOrdersVo conOrder = new ConsumerOrdersVo();
					conOrder.setCO_IDs(ids);
					List<ConsumerOrdersVo> cons = consumerOrdersService
							.queryConsumerOrdersListByCondition(conOrder);
					if (cons.size() > 0) {
						for (ConsumerOrdersVo con : cons) {
							queIDS = queIDS + con.getASK_QuesID() + ", ";
						}
						for (int i = 0; i < bs.size(); i++) {
							for (ConsumerOrdersVo con : cons) {
								int coID = con.getCO_ID();
								if (bs.getJSONObject(i).getInt(
										"serviceRecordId") == coID) {
									bs.getJSONObject(i).put("content",
											con.getASK_Content());
									bs.getJSONObject(i).put("quesID",
											con.getASK_QuesID());
								}
							}
						}
					}
				}
				if (queIDS == "" || queIDS.equals("")) {
					return ApiUtil.getJsonRt(10000, "未获取明细数据");
				}
				queIDS = StringUtils.substringBeforeLast(queIDS, ",");
				QuesMainVo qvs = new QuesMainVo();
				qvs.setQuesIDS(queIDS);
				String quesVos = quesMainService.queryQuesAndPatient(qvs);
				JSONObject quesVoJson = JSONObject.fromObject(quesVos);
				JSONArray qvos = quesVoJson.getJSONArray("result");
				for (int i = 0; i < bs.size(); i++) {
					for (int m = 0; m < qvos.size(); m++) {
						if (bs.getJSONObject(i).get("quesID") != null) {
							if (bs.getJSONObject(i).getInt("quesID") == qvos
									.getJSONObject(m).getInt("quesmain_id")) {
								bs.getJSONObject(i)
										.put("patientName",
												qvos.getJSONObject(m).get(
														"patient_name") == null ? null
														: qvos.getJSONObject(m)
																.getString(
																		"patient_name"));
								bs.getJSONObject(i).put(
										"patientBirth",
										qvos.getJSONObject(m).getString(
												"patient_birth"));
								bs.getJSONObject(i).put(
										"patientSex",
										qvos.getJSONObject(m).getString(
												"patient_sex"));
								bs.getJSONObject(i).put(
										"cityID",
										qvos.getJSONObject(m).getString(
												"ask_cityid"));
								bs.getJSONObject(i).put(
										"provinceName",
										qvos.getJSONObject(m).getString(
												"ask_provincename"));
								bs.getJSONObject(i).put(
										"cityName",
										qvos.getJSONObject(m).getString(
												"ask_cityname"));
								bs.getJSONObject(i).put(
										"provinceID",
										qvos.getJSONObject(m).getString(
												"ask_provinceid"));
								bs.getJSONObject(i).put(
										"sourceType",
										qvos.getJSONObject(m).getString(
												"qd_sourcetype"));
								bs.getJSONObject(i).put(
										"content",
										qvos.getJSONObject(m).getString(
												"quesmain_content"));
								bs.getJSONObject(i).put(
										"quesID",
										qvos.getJSONObject(m).getString(
												"quesmain_id"));
							}
						} else if (bs.getJSONObject(i).getInt("serviceId") == 5
								&& qvos.getJSONObject(m).getInt("quesmain_id") == bs
										.getJSONObject(i).getInt(
												"serviceRecordId")) {
							bs.getJSONObject(i)
									.put("patientName",
											qvos.getJSONObject(m).get(
													"patient_name") == null ? null
													: qvos.getJSONObject(m)
															.getString(
																	"patient_name"));
							bs.getJSONObject(i).put(
									"patientBirth",
									qvos.getJSONObject(m).getString(
											"patient_birth"));
							bs.getJSONObject(i).put(
									"patientSex",
									qvos.getJSONObject(m).getString(
											"patient_sex"));
							bs.getJSONObject(i).put(
									"cityID",
									qvos.getJSONObject(m).getString(
											"ask_cityid"));
							bs.getJSONObject(i).put(
									"provinceName",
									qvos.getJSONObject(m).getString(
											"ask_provincename"));
							bs.getJSONObject(i).put(
									"cityName",
									qvos.getJSONObject(m).getString(
											"ask_cityname"));
							bs.getJSONObject(i).put(
									"provinceID",
									qvos.getJSONObject(m).getString(
											"ask_provinceid"));
							bs.getJSONObject(i).put(
									"sourceType",
									qvos.getJSONObject(m).getString(
											"qd_sourcetype"));
							bs.getJSONObject(i).put(
									"content",
									qvos.getJSONObject(m).getString(
											"quesmain_content"));
							bs.getJSONObject(i).put(
									"quesID",
									qvos.getJSONObject(m).getString(
											"quesmain_id"));
						}

					}
				}
			}
			JSONObject rt = JSONObject.fromObject(ApiUtil
					.getJsonRt(10000, "成功"));
			rt.put("Total", total.getResult());
			rt.put("Result", bs.toString());
			rt.put("totalPrice", totalPrice);
			return rt.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("方法", tag).set("参数", msg.getParam())
							.set("异常", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"加载异常!" + StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("方法", tag).set("参数", msg.getParam())
							.set("异常", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"加载异常!" + StringUtil.getException(e));
		}
	}

	public String getMYTBillList(InterfaceMessage msg) {
		String tag = "getMYTBillList";
		try {
			JSONObject json = JSONObject.fromObject(msg.getParam());
			String year = json.get("year") == null ? null : json
					.getString("year");
			String month = json.get("month") == null ? null : json
					.getString("month");
			String doctorUid = json.get("doctorUid") == null ? null : json
					.getString("doctorUid");
			int page = json.get("page") == null ? null : json.getInt("page");
			int pageSize = json.get("pageSize") == null ? null : json
					.getInt("pageSize");
			com.yihu.baseinfo.api.DoctorServiceApi docServiceApi = Rpc.get(
					DoctorServiceApi.class,
					ConfigUtil.getInstance().getUrl("url.baseinfo"));
			String ids = "";
			String queIDS = "";
			JSONObject dcJson = new JSONObject();
			dcJson.put("doctorUid", doctorUid);
			dcJson.put("year", year);
			dcJson.put("month", month);
			dcJson.put("serviceId", 1);
			dcJson.put("state", 1);
			ServiceResult<Integer> total = docServiceApi.queryBillCount(dcJson
					.toString());
			if (total.getCode() > 0) {
				if (total.getResult() == 0) {
					return ApiUtil.getJsonRt(10000, "未获取医生明细数据");
				}
			} else {
				return ApiUtil.getJsonRt(-14444, "数据获取失败");
			}
			JSONArray bs = new JSONArray();
			ServiceResult<String> bills = docServiceApi.queryBillList(
					dcJson.toString(), page, pageSize);
			int totalPrice = 0;
			if (bills.getCode() > 0) {
				JSONObject billsJs = JSONObject.fromObject(bills.getResult());
				bs = billsJs.getJSONArray("bills");
				totalPrice = billsJs.getInt("totalPrice");
				for (int i = 0; i < bs.size(); i++) {
					ids = ids + bs.getJSONObject(i).getInt("serviceRecordId")
							+ ", ";
				}
				if (ids == null || ids.equals("")) {
					return ApiUtil.getJsonRt(1, "未获取医生明细数据");
				}
				ids = StringUtils.substringBeforeLast(ids, ",");
				ConsumerOrdersVo conOrder = new ConsumerOrdersVo();
				conOrder.setCO_IDs(ids);
				ServiceResult<List<MytConswaterBean>> cons = conswaterService
						.getMytConswaterResultList(ids);
				for (int i = 0; i < bs.size(); i++) {
					for (MytConswaterBean con : cons.getResult()) {
						if (con.getPkconswaterid().equals(
								bs.getJSONObject(i).getInt("serviceRecordId"))) {
							bs.getJSONObject(i).put(
									"startDateTime",
									DateUtil.dateFormat(con.getStartdatetime(),
											DateUtil.YMDHMS_FORMAT));
							bs.getJSONObject(i).put(
									"endDateTime",
									DateUtil.dateFormat(con.getEnddatetime(),
											DateUtil.YMDHMS_FORMAT));
							bs.getJSONObject(i)
									.put("consMin", con.getConsmin());
							bs.getJSONObject(i).put("custphone",
									con.getCustphone());
						}
					}
				}
			}
			JSONObject rt = JSONObject.fromObject(ApiUtil
					.getJsonRt(10000, "成功"));
			rt.put("Total", total.getResult());
			rt.put("Result", bs.toString());
			rt.put("totalPrice", totalPrice);
			return rt.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("方法", tag).set("参数", msg.getParam())
							.set("异常", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"加载异常!" + StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("方法", tag).set("参数", msg.getParam())
							.set("异常", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"加载异常!" + StringUtil.getException(e));
		}
	}
	
	
	
	
	private static ICloseMainService closeMainService = Ioc
			.get(ICloseMainService.class);
	
	public String overQuesRefund(InterfaceMessage msg) {
		String tag = "overQuesRefund";
		try {
			JSONObject json = JSONObject.fromObject(msg.getParam());
			
			int doctorID = json.get("doctorID") == null ? null : json
					.getInt("doctorID");
			
			Integer userid = json.get("userid") == null ? null : json
					.getInt("userid");
			Integer coid = json.get("coid") == null ? null : json
					.getInt("coid");
			Integer queID = json.get("queID") == null ? null : json
					.getInt("queID");
			int operatorID = json.get("OperatorId") == null ? null : json
					.getInt("OperatorId");
			/*
			 * String operatorName = json.get("OperatorName") == null ? null :
			 * json.getString("OperatorName");
			 */

			com.yihu.account.api.IAccountService accountService = Rpc.get(
					IAccountService.class,
					ConfigUtil.getInstance().getUrl("url.account"));
			com.yihu.baseinfo.api.DoctorServiceApi doctorServiceApi = Rpc.get(
					DoctorServiceApi.class,
					ConfigUtil.getInstance().getUrl("url.baseinfo"));
			JdbcConnection conn = null;
			
			QuesMainVo qvo = new QuesMainVo();
			qvo.setQUESMAIN_ID(queID);
			qvo = quesMainService.queryQuesMainByCondition(qvo);
			qvo.setOpenAuthFlag(null);
			ConsumerOrdersVo co = new ConsumerOrdersVo();
			co.setASK_QuesID(queID);
			co = consumerOrdersService.queryConsumerOrdersByQuesID(co);
			
			ReturnValueBean clrt = accountService.clearFrozen(userid, coid,
					"12", "01");
			if(clrt.getCode()!= -2000){
				if (clrt.getCode() < 0) {
					return ApiUtil.getJsonRt(-2000, clrt.getMessage());
				}
			}
			// 开始事务
			conn = DB.me().getConnection(MyDatabaseEnum.YiHuNet2008);
			conn.beginTransaction(50000);
			ConsumerOrdersVo cVo = new ConsumerOrdersVo();
			cVo.setCO_Status(-1);
			cVo.setCO_ID(coid);
			int cort = consumerOrdersService
					.updateCOrdersByCondition(cVo, conn);
			if (cort < 0) {
				conn.rollback();
				ApiUtil.getJsonRt(-14444, "更新订单状态失败");
			}
			QuesMainVo qusVo = new QuesMainVo();
			qusVo.setOpenAuthFlag(null);
			qusVo.setQUESMAIN_ID(queID);
			qusVo.setASK_UserID(userid);
			qusVo.setQD_Price(0);
			qusVo.setQD_DoctorGetPrice(0);
			qusVo.setQD_OrdersStatus(5);
			qusVo.setQD_CheckStatus(1);
			int qmrt = quesMainService.updateQMainByCondition(qusVo, conn);
			System.out.println("1111111111");
			if (qmrt < 0) {
				conn.rollback();
				ApiUtil.getJsonRt(-14444, "更新问题状态失败");
			}
			conn.commitTransaction(true);
			QuesCloseWaterVo vo = new QuesCloseWaterVo();
			if (operatorID == 1111111 || operatorID == 1000000
					|| operatorID == 9000029) {
				vo.setOperType(1);// 用户关闭
			} else if (operatorID == 9000023 || operatorID == 9000030) {
				vo.setOperType(3);// 医生关闭
			} else if (operatorID == 9000024 || operatorID == 9000031) {
				vo.setOperType(4);// 医学人员关闭
			} else {
				vo.setOperType(5);// 未知渠道关闭
			}
			vo.setCreateTime(DateUtil.dateFormat(new Date()));
			vo.setQuesID(queID);
			
			int rCont = quesCloseWaterService.queryQuesCloseWaterCountByCondition(vo);
			if(rCont ==0){
				int rtqu = quesCloseWaterService.insertQuesCloseWater(vo);
				if (rtqu < 0) {
					return WsUtil.getRetVal(msg.getOutType(), -14444,
							"问题关闭时间插入状态失败");
				}
			}
			// 医生退钱
			JSONObject dcBillJson = new JSONObject();
			dcBillJson.put("doctorUid", qvo.getASK_DoctorID());
			dcBillJson.put("serviceRecordId", co.getCO_ID());
			dcBillJson.put("serviceId", 2);
			dcBillJson.put("feeTemplateId", co.getFeeTemplateId());
			//dcBillJson.put("price",(-1)* price);// 医生扣费
			ServiceResult<String> rt = doctorServiceApi.exceptionBill
					(dcBillJson.toString());
			if (rt.getCode() < 0) {
				System.out.println(rt.getMessage());
			}
			
			
			
			//事件---
			
			EventUtilForMYT.sendEvenForClose(String.valueOf(doctorID),String.valueOf(queID));
			
//			
//			//TODO  wujiajun  --2
//			
//			//--------------
//			System.out.println("医生的 id：：：：" +doctorID);
//			 DoctorVo docvo= closeMainService.getDocById(String.valueOf(doctorID) );
//			System.out.println(docvo.getDoctorName()+"医生名字！！！");
//			CloseMainVo clvo = new CloseMainVo();
//			clvo.setDOCID(docvo.getDoctorUid());
//			clvo.setDOCNAME(docvo.getDoctorName());
//			closeMainService.insertCloseMain(clvo);
//			//------------------ 
			
			
			
			return WsUtil.getRetVal(msg.getOutType(), 10000, "问题退款操作成功");
		} catch (JSONException e) {

			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("方法", tag).set("参数", msg.getParam())
							.set("异常", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"加载异常!" + StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("方法", tag).set("参数", msg.getParam())
							.set("异常", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"加载异常!" + StringUtil.getException(e));
		}
	}

	
	public String overQuesRefundV2(InterfaceMessage msg) {
		String tag = "overQuesRefundV2";
		try {
			JSONObject json = JSONObject.fromObject(msg.getParam());
			Integer userid = json.get("userid") == null ? null : json
					.getInt("userid");
			Integer coid = json.get("coid") == null ? null : json
					.getInt("coid");
			Integer queID = json.get("queID") == null ? null : json
					.getInt("queID");
			int operatorID = json.get("OperatorId") == null ? null : json
					.getInt("OperatorId");
			/*
			 * String operatorName = json.get("OperatorName") == null ? null :
			 * json.getString("OperatorName");
			 */
			com.yihu.baseinfo.api.DoctorServiceApi doctorServiceApi = Rpc.get(
					DoctorServiceApi.class,
					ConfigUtil.getInstance().getUrl("url.baseinfo"));
			QuesMainVo qvo = new QuesMainVo();
			qvo.setQUESMAIN_ID(queID);
			qvo = quesMainService.queryQuesMainByCondition(qvo);
			qvo.setOpenAuthFlag(null);
			ConsumerOrdersVo co = new ConsumerOrdersVo();
			co.setASK_QuesID(queID);
			co = consumerOrdersService.queryConsumerOrdersByQuesID(co);
			if (qvo.getQD_DocReplayCount() > 0 || qvo.getQD_Price() > 0) {
				if (qvo.getQD_Price() > 0) {
					com.yihu.account.api.IAccountService accountService = Rpc
							.get(IAccountService.class, ConfigUtil
									.getInstance().getUrl("url.account"));
					JdbcConnection conn = null;
					ReturnValueBean clrt = accountService.clearFrozen(userid,
							coid, "12", "01");
					if (clrt.getCode() < 0) {
						return ApiUtil.getJsonRt(-2000, clrt.getMessage());
					}
					// 开始事务
					conn = DB.me().getConnection(MyDatabaseEnum.YiHuNet2008);
					conn.beginTransaction(50000);
					ConsumerOrdersVo cVo = new ConsumerOrdersVo();
					cVo.setCO_Status(-1);
					cVo.setCO_ID(coid);
					int cort = consumerOrdersService.updateCOrdersByCondition(
							cVo, conn);
					if (cort < 0) {
						conn.rollback();
						ApiUtil.getJsonRt(-14444, "更新订单状态失败");
					}
					QuesMainVo qusVo = new QuesMainVo();
					qusVo.setQUESMAIN_ID(queID);
					qusVo.setASK_UserID(userid);
					qusVo.setQD_Price(0);
					//qusVo.setQD_DoctorGetPrice(0);
					qusVo.setQD_OrdersStatus(5);
					qusVo.setQD_CheckStatus(1);
					int qmrt = quesMainService.updateQMainByCondition(qusVo,
							conn);
					if (qmrt < 0) {
						conn.rollback();
						ApiUtil.getJsonRt(-14444, "更新问题状态失败");
					}
					conn.commitTransaction(true);

				} else {
					qvo.setQUESMAIN_ID(queID);
					qvo.setQD_Status(1);
					qvo.setQD_RecordExpiredTime(DateUtil.dateFormat(new Date()));
					int qurt = quesMainService.updateQuesMain(qvo);
					if (qurt < 0) {
						return WsUtil.getRetVal(msg.getOutType(), -14444,
								"更新问题状态失败");
					}
					com.yihu.baseinfo.api.DoctorAccountApi doctorAccountApi = Rpc
							.get(DoctorAccountApi.class, ConfigUtil
									.getInstance().getUrl("url.baseinfo"));
					// 如果是回复过的过期问题 就加爱心值
					if (qvo.getQD_OrdersStatus() == 5) {
						// 爱心值增加(好评才增加流水)
						JSONObject axJson = new JSONObject();
						axJson.put("doctorUid", qvo.getASK_DoctorID());
						axJson.put("resId", queID);
						axJson.put("typeId", 4);
						ServiceResult<String> addlrt = doctorAccountApi
								.addDoctorLove(axJson.toString());
						if (addlrt.getCode() < 0) {
							return ApiUtil.getJsonRt(-14444, "爱心值流水插入失败。");
						}
						CreditsRecordVo crVo = new CreditsRecordVo();
						crVo.setASK_QuesID(queID);
						crVo.setASK_DoctorAccountID(qvo.getASK_DoctorID());
						crVo.setASK_DoctorID(qvo.getASK_DoctorID());
						crVo.setCR_Credits(1);
						crVo.setCR_CreditsType(2);
						crVo.setCR_Type(3);
						crVo.setCR_CreateTime(DateUtil.dateFormat(new Date()));
						int rtCr = creditsRecordService
								.insertCreditsRecord(crVo);
						if (rtCr < 0) {
							return ApiUtil.getJsonRt(-14444, "爱心值流水插入失败。");
						}
					}
				}
				QuesCloseWaterVo vo = new QuesCloseWaterVo();
				if (operatorID == 1111111 || operatorID == 1000000
						|| operatorID == 9000029) {
					vo.setOperType(1);// 用户关闭
				} else if (operatorID == 9000023 || operatorID == 9000030) {
					vo.setOperType(3);// 医生关闭
				} else if (operatorID == 9000024 || operatorID == 9000031) {
					vo.setOperType(4);// 医学人员关闭
				} else {
					vo.setOperType(5);// 未知渠道关闭
				}
				vo.setCreateTime(DateUtil.dateFormat(new Date()));
				vo.setQuesID(queID);
				int rCont = quesCloseWaterService.queryQuesCloseWaterCountByCondition(vo);
				if(rCont ==0){
					int rtqu = quesCloseWaterService.insertQuesCloseWater(vo);
					if (rtqu < 0) {
						return WsUtil.getRetVal(msg.getOutType(), -14444,
								"问题关闭时间插入状态失败");
					}
				}
				// 医生退钱
				JSONObject dcBillJson = new JSONObject();
				dcBillJson.put("doctorUid", qvo.getASK_DoctorID());
				dcBillJson.put("serviceRecordId", co.getCO_ID());
				dcBillJson.put("serviceId", 2);
				dcBillJson.put("feeTemplateId", co.getFeeTemplateId());
				//dcBillJson.put("price",(-1)* price);// 医生扣费
				ServiceResult<String> rt = doctorServiceApi.exceptionBill
						(dcBillJson.toString());
				if (rt.getCode() < 0) {
					System.out.println(rt.getMessage());
				}
				
				
				//事件
				EventUtilForMYT.sendEvenForClose(String.valueOf(qvo.getASK_DoctorID()),String.valueOf(queID));
				
				
				
			
				return WsUtil.getRetVal(msg.getOutType(), 10000, "问题退款操作成功");
			} else {
				return WsUtil.getRetVal(msg.getOutType(), 10000,
						"等待医生回复中，问题无法关闭。");
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("方法", tag).set("参数", msg.getParam())
							.set("异常", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"加载异常!" + StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("方法", tag).set("参数", msg.getParam())
							.set("异常", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"加载异常!" + StringUtil.getException(e));
		}
	}


	public String getDoctorBillWysV2(InterfaceMessage msg) {
		String tag = "getDoctorBillWysV2";
		try {
			JSONObject json = JSONObject.fromObject(msg.getParam());

			JSONArray jsonArr = new JSONArray();
			JSONObject dcJson = new JSONObject();
			int totalTime = 0;
			String year = json.get("year") == null ? null : json
					.getString("year");
			String month = json.get("month") == null ? null : json
					.getString("month");
			String doctorUid = json.get("doctorUid") == null ? null : json
					.getString("doctorUid");
			int page = json.get("page") == null ? null : json.getInt("page");
			int pageSize = json.get("pageSize") == null ? null : json
					.getInt("pageSize");
			String serviceType = json.get("serviceType") == null ? null : json
					.getString("serviceType");
			com.yihu.baseinfo.api.DoctorServiceApi docServiceApi = Rpc.get(
					DoctorServiceApi.class,
					ConfigUtil.getInstance().getUrl("url.baseinfo"));
			String ids = "";
			dcJson.put("doctorUid", doctorUid);
			dcJson.put("year", year);
			dcJson.put("month", month);
			dcJson.put("serviceIds", serviceType);
			dcJson.put("state", 1);
			JSONObject rtJSON = new JSONObject();
			ServiceResult<Integer> total = docServiceApi.queryBillCount(dcJson
					.toString());
			if (total.getCode() > 0) {
				rtJSON.put("total", total.getResult());
				if (total.getResult() == 0) {
					JSONArray bls = new JSONArray();
					rtJSON.put("totalPrice", 0);
					rtJSON.put("bills", bls);
					return ApiUtil.getJsonRt(10000, "未获取医生明细数据");
				}
			} else {
				return ApiUtil.getJsonRt(-14444, "未获取医生明细数据");
			}
			ServiceResult<String> bills = docServiceApi.queryBillList(
					dcJson.toString(), page, pageSize);
			JSONArray bs = new JSONArray();
			Integer totalPrice = 0;
			if (bills.getCode() > 0) {
				JSONObject billsJs = JSONObject.fromObject(bills.getResult());
				bs = billsJs.getJSONArray("bills");
				totalPrice = billsJs.getInt("totalPrice");
				for (int i = 0; i < bs.size(); i++) {
					ids = ids + bs.getJSONObject(i).getInt("serviceRecordId")
							+ ", ";
				}
				if (ids == null || ids.equals("")) {
					JSONArray bls = new JSONArray();
					rtJSON.put("totalPrice", 0);
					rtJSON.put("bills", bls);
					return ApiUtil.getJsonRt(10000, "未获取医生明细数据");
				}
				ids = StringUtils.substringBeforeLast(ids, ",");
				ConsumerOrdersVo vo = new ConsumerOrdersVo();
				vo.setCO_IDs(ids);
				List<ConsumerOrdersVo> cons = consumerOrdersService
						.queryConsumerOrdersListByCondition(vo);
				if (cons.size() > 0) {
					for (int i = 0; i < bs.size(); i++) {
						for (ConsumerOrdersVo con : cons) {
							int coID = con.getCO_ID();
							if (bs.getJSONObject(i).getInt("serviceRecordId") == coID) {
								bs.getJSONObject(i).put("content",
										con.getASK_Content());
							}
						}
					}
				}
			}
			JSONObject rt = JSONObject.fromObject(ApiUtil
					.getJsonRt(10000, "成功"));
			JSONObject bsrt = new JSONObject();
			bsrt.put("bills", bs);
			bsrt.put("totalTime", totalTime);
			bsrt.put("PageSize", pageSize);
			bsrt.put("PageIndex", page);
			bsrt.put("total", total.getResult());
			bsrt.put("totalPrice", totalPrice);
			rt.put("Result", bsrt);
			return rt.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("方法", tag).set("参数", msg.getParam())
							.set("异常", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"加载异常!" + StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("方法", tag).set("参数", msg.getParam())
							.set("异常", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"加载异常!" + StringUtil.getException(e));
		}
	}

	public String getOrderContents(InterfaceMessage msg) {
		String tag = "getOrderContents";
		try {
			JSONObject json = JSONObject.fromObject(msg.getParam());
			String orderIDs = json.get("orderIDs") == null ? null : json
					.getString("orderIDs");
			int orderType = json.get("orderType") == null ? null : json
					.getInt("orderType");
			JSONObject rtJson = JSONObject.fromObject(ApiUtil.getJsonRt(10000,
					"操作成功"));
			if (orderType == 2) {
				PatientVo vo = new PatientVo();
				vo.setPatIDs(orderIDs);
				List<PatientVo> list = patientService
						.queryPatientListByCondition(vo);
				JSONArray quesJson = JSONArray.fromObject(list);
				rtJson.put("Result", quesJson);
				return rtJson.toString();
			} else {
				MytConswaterBean conswater = new MytConswaterBean();
				conswater.setPkwaterIDs(orderIDs);
				com.common.json.JSONObject waters = conswaterService
						.getMytWaters(conswater);
				com.common.json.JSONArray quesJson = waters
						.getJSONArray("result");
				rtJson.put("Result", quesJson.toString());
				return rtJson.toString();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("方法", tag).set("参数", msg.getParam())
							.set("异常", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"加载异常!" + StringUtil.getException(e));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.get().error(
					tag,
					new LogBody().set("方法", tag).set("参数", msg.getParam())
							.set("异常", StringUtil.getException(e)));
			return ApiUtil.getJsonRt(-14444,
					"加载异常!" + StringUtil.getException(e));
		}
	}
}