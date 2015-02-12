package com.yihu.myt.http;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import com.boss.sdk.HttpRequestContext;
import com.boss.sdk.HttpResponseContext;
import com.boss.sdk.OperatorInfo;
import com.common.json.JSONArray;
import com.common.json.JSONObject;
import com.coreframework.db.Order;
import com.coreframework.ioc.Ioc;
import com.coreframework.log.LogBody;
import com.coreframework.log.Logger;
import com.coreframework.remoting.reflect.Rpc;
import com.coreframework.remoting.standard.DateOper;
import com.coreframework.vo.ReturnValue;
import com.coreframework.vo.ServiceResult;
import com.yihu.baseinfo.api.DoctorInfoApi;
import com.yihu.baseinfo.vo.DoctorVo;
import com.yihu.basis.api.IBasisService;
import com.yihu.basis.api.OaEmployeeViewBean;
import com.yihu.myt.ConfigUtil;
import com.yihu.myt.IArraworkService;
import com.yihu.myt.IBookenrolService;
import com.yihu.myt.IPauseService;
import com.yihu.myt.enums.Constant;
import com.yihu.myt.enums.MytConst;
import com.yihu.myt.service.service.IDoctorInfoService;
import com.yihu.myt.service.service.IPostService;
import com.yihu.myt.util.DBCache;
import com.yihu.myt.util.DateUtil;
import com.yihu.myt.util.StringUtil;
import com.yihu.myt.vo.DoctorInfoVo;
import com.yihu.myt.vo.MytArraworkBean;
import com.yihu.myt.vo.MytBookenrolBean;
import com.yihu.myt.vo.MytBookenrolView;
import com.yihu.myt.vo.MytConsenrolBean;
import com.yihu.myt.vo.MytDoctorViewBean;
import com.yihu.myt.vo.MytPauseworkBean;
import com.yihu.myt.vo.MytRevertBean;
import com.yihu.myt.vo.Page;
import com.yihu.oa.api.IBasisWS;
import com.yihu.wsgw.api.InterfaceMessage;

public class BookenrolAction {

	private static IBookenrolService bookenrolService = Ioc
			.get(IBookenrolService.class); // 咨询流水服务接口
	private static IArraworkService arraworkService = Ioc
			.get(IArraworkService.class); // 排班服务接口
	private static IPauseService pauseService = Ioc.get(IPauseService.class); // 停诊服务接口
	private static IDoctorInfoService doctorInfoService = Ioc.get(IDoctorInfoService.class); // 停诊服务接口
	private static IPostService postService = Ioc
			.get(IPostService.class); 
	
	public HttpResponseContext queryBookenrol(HttpRequestContext request) {

		try {
			OperatorInfo operator = request.getOperator();
			if (operator == null) {
				return new HttpResponseContext("您还未登录或登录已超时。");
			}
			int regway = request.getInt("regway");

			MytBookenrolView view = new MytBookenrolView();

			BeanUtils.populate(view, request.getParamMap());
			String starttimes = request.getParameter("starttimes");
			if (StringUtils.isNotEmpty(starttimes)) {
				view.setBegOpertime(DateUtil.parse(DateUtil.stringFormat(
						starttimes, DateUtil.YMD_FORMAT).getTime()));
			}
			String endtimes = request.getParameter("endtimes");
			if (StringUtils.isNotEmpty(endtimes)) {
				view.setEndOpertime(DateUtil.parse(DateUtil.stringFormat(
						endtimes, DateUtil.YMD_FORMAT).getTime()));
			}
			view.setState(MytConst.EFFECTIVE.getValue());

			// System.out.println(view.toString());
			com.yihu.baseinfo.api.DoctorInfoApi
			doctorInfoApi=Rpc.get(DoctorInfoApi.class
					   , ConfigUtil.getInstance().getUrl("url.baseinfo"));
			//DoctorVoMore  obj=new DoctorVoMore();
			//obj.setStandardDeptID(node);//标准科室ID
			//obj.setServiceStatusSearch("2");//有开通电话咨询，固定传2
			//obj.setProvinceId(orgid);//省份ID
			//ServiceResult<List<DoctorVo>> dsr=doctorInfoApi.queryDoctorList(obj);
			JSONObject js = new JSONObject();
			if( view.getOrgid()!=null ){
				js.put("provinceId", view.getOrgid() + "");
			}
			//js.put("provinceId", "13");
			//js.put("doctorNameLike", "陈水仙");
			if(StringUtil.isNotEmpty(view.getDoctorname())){
				js.put("doctorNameLike", view.getDoctorname());
			}
			//json.put("standardDeptId", node);
			js.put("serviceStatusSearch", "2");
			//js.put("main", 1);
			DoctorInfoVo vo = new DoctorInfoVo();
			vo.setProvinceID(view.getOrgid());
			vo.setDoctorService("1");
			vo.setMain(1);
			List<DoctorInfoVo> docs= doctorInfoService.queryDoctorInfoListByCondition(vo);
			String uids = "";
			if(docs !=null){
				for(DoctorInfoVo doc : docs){
					uids = 	doc.getDoctorUid() +"," + uids ;
				}
				uids = StringUtils.substringBeforeLast(uids , ",");
				view.setOperconfids(uids);
			}else{
				JSONObject dataJson = new JSONObject();
				dataJson.put("page", "");
				dataJson.put("list", "");
				// System.out.println(jsonArray);
				return new HttpResponseContext(dataJson);
			}
			//ServiceResult<String>  dsr = doctorInfoApi.queryDoctorUids(js.toString());
			//String opid = "";
			/*if (dsr.getCode() > 0) {
				JSONArray doctors = new JSONArray(dsr.getResult());
				for(int i=0;i<doctors.length();i++){
				
					opid = opid +"" +  doctor.get("doctorUid") +",";
				}
				view.setOperconfids(dsr.getResult());
			}else{
				JSONObject dataJson = new JSONObject();
				dataJson.put("page", "");
				dataJson.put("list", "");
				// System.out.println(jsonArray);
				return new HttpResponseContext(dataJson);
			}*/
			// 设置分页及查询记录数
			Page<MytBookenrolView> page = new Page<MytBookenrolView>(
					request.getInt("pageIndex"), request.getInt("pageSize"));
			page.setOrderProp(Order.DESC("a.BOOKENROLID"));
			page.setTotalItems(bookenrolService.getMytBookenrolCountS(view,
					regway).getResult());
			JSONObject pageJson = new JSONObject();
			pageJson.put("pageIndex", page.getPageNo());
			pageJson.put("pageSize", page.getPageSize());
			pageJson.put("totalItems", page.getTotalItems());
			pageJson.put("totalPages", page.getTotalPages());
			pageJson.put("nextPage", page.getNextPage());

			// 查询记录集
			JSONArray jsonArray = new JSONArray();
			ServiceResult<List<MytBookenrolView>> srObj = bookenrolService
					.getMytBookenrolResultS(view, regway, page,
							MytConst.QUERY_JS.getValue());
			if (srObj.getCode() > 0) {
				List<MytBookenrolView> lstObjs = srObj.getResult();
				String date = DateUtil.dateFormat(DateOper.getNowDateTime(),
						DateUtil.YMD_FORMAT);
				String time = DateUtil.dateFormat(DateOper.getNowDateTime(),
						DateUtil.HM_FORMAT);
				if (lstObjs != null && !lstObjs.isEmpty()) {
					for (MytBookenrolView obj : lstObjs) {
						// if (rltMdvBean != null) {
						JSONObject json = new JSONObject();
						//System.out.println(obj.getOperconfid());
						DoctorInfoVo dc = new DoctorInfoVo();
						dc.setDoctorUid(obj.getOperconfid());
						dc = doctorInfoService.queryDoctorInfo(dc);
					//	ServiceResult<String> dc = doctorInfoApi.getDoctorByUid();
						//JSONObject dcJson = new JSONObject(dc.getResult());
						json.put("bookenrolid", obj.getBookenrolid());
						json.put("operconfid", obj.getOperconfid());
						json.put("orgname", DBCache.provinceMap.get(dc.getProvinceID().toString()));
						json.put("doctorname", dc.getDoctorName());
						json.put("deptname",  dc.getDeptName());
						json.put("comefrom", obj.getComefrom());
						// 判断医生是否排班在线
						int whichColor = 0;
						ServiceResult<MytArraworkBean> srMa = arraworkService
								.getMytArrawork(
										obj.getOperconfid(),
										time);
						if (srMa.getCode() > 0 && srMa.getResult() != null) {
							// 排班在线
							whichColor = 1;

							// 判断医生是否停诊
							ServiceResult<MytPauseworkBean> srMp = pauseService
									.getEntity(obj
											.getOperconfid(), time, date);
							if (srMp.getCode() > 0 && srMp.getResult() != null) {
								// 医生停诊
								whichColor = 2;
							} else {
								// 判断医生是否占线
								if (OperconfidAction.get("Y"
										+ obj.getOperconfid()) != null) {
									whichColor = 4;
								} else if (OperconfidAction.get("N"
										+ obj.getOperconfid()) != null) {
									// 计算在线时间
									int times = (int) (DateOper
											.getNowDateTime().getTime() - DateUtil
											.parse(OperconfidAction.get("N"
													+ obj.getOperconfid()),
													DateUtil.YMDHMS_FORMAT)
											.getTime());
									// 确定在线时间为10分钟
									if (times <= 600) {
										whichColor = 3;
									}
								}
								if (arraworkService.isBusy(obj
										.getOperconfid()))
									whichColor = 4;
							}
						}
						json.put("whichcolor", whichColor
								+ Constant.EMPTY_SYMBOL);
						json.put("cardid",
								obj.getCardid() == null ? Constant.CROSS_SYMBOL
										: obj.getCardid());
						json.put(
								"custname",
								obj.getCustname() == null ? Constant.CROSS_SYMBOL
										: obj.getCustname());
						json.put(
								"revertphone",
								obj.getRevertphone() == null ? Constant.CROSS_SYMBOL
										: obj.getRevertphone());
						json.put(
								"revertresult",
								obj.getRevertresult() == null ? Constant.CROSS_SYMBOL
										: obj.getRevertresult());
						// 获取咨询回访备注信息
						List<MytRevertBean> lstMrBean = bookenrolService
								.getMytRevert(obj.getBookenrolid() + "")
								.getResult();
						StringBuffer remark = new StringBuffer(
								"["
										+ (obj.getOperatorname() == null ? Constant.CROSS_SYMBOL
												: obj.getOperatorname())
										+ Constant.SPACE_SYMBOL
										+ (obj.getOpertime() == null ? Constant.CROSS_SYMBOL
												: DateOper.formatDate(
														obj.getOpertime(),
														DateUtil.YMDHMS_FORMAT)
														+ "];")
										+ (obj.getRemark() == null ? Constant.CROSS_SYMBOL
												: obj.getRemark()));
						if (lstMrBean != null && !lstMrBean.isEmpty()) {
							for (MytRevertBean mrBean : lstMrBean) {
								remark.append("  ["
										+ mrBean.getOperatorname()
										+ Constant.SPACE_SYMBOL
										+ DateOper.formatDate(
												mrBean.getOpertime(),
												DateUtil.YMDHMS_FORMAT) + "]："
										+ mrBean.getRemark());
							}
						}
						json.put("remark", remark);
						json.put(
								"opertime",
								obj.getOpertime() == null ? Constant.CROSS_SYMBOL
										: DateOper.formatDate(
												obj.getOpertime(),
												DateUtil.YMD_FORMAT));
						json.put(
								"dateweek",
								(obj.getDateweek() == null ? Constant.EMPTY_SYMBOL
										: obj.getDateweek())
										+ Constant.SPACE_SYMBOL
										+ (obj.getStarttime() == null ? Constant.EMPTY_SYMBOL
												: obj.getStarttime())
										+ "--"
										+ (obj.getEndtime() == null ? Constant.EMPTY_SYMBOL
												: obj.getEndtime()));
						jsonArray.put(json);
						// }
					}
				}
			}
			// 返回结果
			JSONObject dataJson = new JSONObject();
			dataJson.put("page", pageJson);
			dataJson.put("list", jsonArray);
			// System.out.println(jsonArray);
			return new HttpResponseContext(dataJson);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(-14444, e.getMessage());
		}
	}

	public HttpResponseContext Update(HttpRequestContext request) {

		try {
			OperatorInfo operator = request.getOperator();
			if (operator == null) {
				return new HttpResponseContext("您还未登录或登录已超时。");
			}
			int bookenrolid = request.getInt("bookenrolid");
			int operconfid = request.getInt("operconfid");
			String revertresult = request.getParameter("revertresult");
			String remark = request.getParameter("remark");
			String comefrom = request.getParameter("comefrom");
			System.out.println(bookenrolid + "_" + operconfid + "_"
					+ revertresult + "_" + remark + "_" + comefrom);
			ReturnValue rv = bookenrolService.updateMytConsenrol(bookenrolid,
					operconfid, revertresult, remark, operator);
			if(comefrom.equals("900019")){
				postService.telecounSelingUpdate(revertresult,
						String.valueOf(bookenrolid));
			}
			return new HttpResponseContext(rv.getCode(), rv.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(-14444, e.getMessage());
		}
	}

	/**
	 * 添加咨询登记
	 * 
	 * @param request
	 * @param response
	 */
	public HttpResponseContext AddConsenrol(HttpRequestContext request) {

		try {
			OperatorInfo operator = request.getOperator();
			if (operator == null) {
				return new HttpResponseContext(-1, "您还未登录或登录已超时。");
			}
			// String operconfid = request.getParameter("operconfid");
			// String dateweeks = request.getParameter("dateweeks");
			// String revertphone = request.getParameter("revertphone");

//			// 获取员工信息
//			ServiceResult<List<OaEmployeeViewBean>> sr = Rpc.get(
//					IBasisService.class,
//					ConfigUtil.getInstance().getUrl("url.basis"), 8000)
//					.getEmpViewListByEmployeeID(operator.getOperatorID());
//			if (sr.getCode() < 0) {
//				return new HttpResponseContext(sr.getCode(), sr.getMessage());
//			}
//			OaEmployeeViewBean rltOevBean = sr.getResult().get(0);

			JSONObject BasisSys = new JSONObject(); 
			InterfaceMessage message = new InterfaceMessage();
			BasisSys.put("EmployeeID", operator.getOperatorID());
			message.setParam(BasisSys.toString());
			IBasisWS ws = Rpc.get(IBasisWS.class, ConfigUtil.getInstance().getUrl("url.oa"));//此处设置调用URL为url.oa
			String retString = ws.getEmpViewListByEmployeeID(message);//调用接口
			JSONObject oper = new JSONObject(retString); 
			int provinceId;
			if(oper.getInt("Code")>0){
				provinceId = oper.getInt("entryProvince");
			}else{
				return new HttpResponseContext(-1, "您还未登录或登录已超时。");
			}
			// 保存咨询登记
			MytConsenrolBean bean = new MytConsenrolBean();
			BeanUtils.populate(bean, request.getParamMap());
			bean.setOperatorid(String.valueOf(request.getOperator()
					.getOperatorID()));
			bean.setProvinceId(provinceId);
			bean.setOperatorname(request.getOperator().getOperatorName());
			bean.setOrgid(request.getOperator().getOrgID() );
			ReturnValue rv = bookenrolService.addMytConsenrol(bean);
			return new HttpResponseContext(rv.getCode(), rv.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(-14444, e.getMessage());
		}
	}

	/**
	 * 咨询登记时根据医生编号查看信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public HttpResponseContext MySearchOperconfid(HttpRequestContext request) {

		try {
			String json = request.getParameter("json");
			String operconfid ;
			if(json == null){
				operconfid = request.getParameter("operconfid");
			}else{
				JSONObject jsonobj = new JSONObject(json);
				operconfid = jsonobj.get("operconfid") != null ? jsonobj.get("operconfid")
						.toString() : null;
			}
			if (StringUtils.isEmpty(operconfid)) {
				return new HttpResponseContext(new JSONObject().put("result",
						"医生配置编号不存在。"));
			}
			// 获取医生视图信息
			ServiceResult<MytDoctorViewBean> sr = arraworkService
					.getMytDoctorView(Integer.valueOf(operconfid));
			if (sr.getCode() < 0) {
				return new HttpResponseContext(new JSONObject().put("result",
						sr.getMessage()));
			}
			// 将查询结果放入jsonArray
			MytDoctorViewBean rltMdvBean = sr.getResult();
			JSONArray jsonArray = new JSONArray();
			jsonArray.put(new JSONObject().put("cmb_dept",
					rltMdvBean.getHospofficename()).put("cmb_doctor",
					rltMdvBean.getDoctorname()));
			return new HttpResponseContext(new JSONObject().put("result",
					jsonArray));
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(-14444, e.getMessage());
		}
	}

	/**
	 * 显示医生是否在线
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public HttpResponseContext getMytWork(HttpRequestContext request) {

		try {
			String operconfid = request.getParameter("operconfid");
			// 判断今天是周几
			Calendar cal = Calendar.getInstance();
			cal.setTime(DateOper.getNowDateTime());
			int week = cal.get(Calendar.DAY_OF_WEEK) - 1;

			// 查询排班
			ServiceResult<List<Object[]>> sr = arraworkService
					.getMytArraworkForDate(operconfid);
			List<Object[]> lstObj = sr.getCode() > 0 ? sr.getResult() : null;

			List<String> al = new ArrayList<String>();
			List<String> temp = new ArrayList<String>();

			for (Object[] objs : lstObj) {
				int wd = Integer.parseInt(objs[0].toString());
				// 计算实际日期差距
				int realWeek = wd;
				if (week > wd) {
					realWeek = wd + 7;
				}
				cal.setTime(DateOper.getNowDateTime());
				cal.add(Calendar.DAY_OF_YEAR, realWeek - week);

				String dateWeek = DateUtil.dateFormat(cal.getTime(),
						DateUtil.YMD_FORMAT)
						+ "("
						+ StringUtil.weekArr[wd]
						+ ")" + objs[1].toString() + "--" + objs[2].toString();

				if (week > wd) {
					al.add(0, dateWeek);
				} else {
					temp.add(dateWeek);
				}
			}
			for (String strTemp : temp) {
				al.add(0, strTemp);
			}

			// 将查询结果放入jsonArray
			JSONArray jsonArray = new JSONArray();
			if (al != null && !al.isEmpty()) {
				JSONObject obs = new JSONObject();
				obs.put("dateid", "99999");
				obs.put("datename", "--请选择--");
				obs.put("selected", true);
				jsonArray.put(obs);
				for (int i = 0; i < al.size(); i++) {
					JSONObject ob = new JSONObject();
					ob.put("dateid", i);
					ob.put("datename", al.get(i));
					jsonArray.put(ob);
				}
			}

			return new HttpResponseContext(new JSONObject().put("result",
					jsonArray));
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(-14444, e.getMessage());
		}
	}

	/**
	 * 根据卡号查询预约回复
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public HttpResponseContext SearchCardid(HttpRequestContext request) {
		try {
			String jsons = request.getParameter("json");
			String cardid = "";
			if (jsons == null) {
				cardid = request.getParameter("cardid");
			} else {
				JSONObject jsonobj = new JSONObject(jsons);
				cardid = jsonobj.get("cardid") != null ? jsonobj.get("cardid")
						.toString() : null;
			}
			MytBookenrolView qryMbBean = new MytBookenrolView();
			if (StringUtils.isNotEmpty(cardid)) {
				qryMbBean.setCardid(cardid);
			}
			qryMbBean.setState(1);

			// 设置分页及查询记录数
			Page<MytBookenrolView> page = new Page<MytBookenrolView>(
					request.getInt("start"), request.getInt("limit"));
			page.setOrderProp(Order.DESC("a.BOOKENROLID"));
			page.setTotalItems(bookenrolService.getMytBookenrolCountS(qryMbBean,
					0).getResult());
			
			
	
			JSONObject pageJson = new JSONObject();
			pageJson.put("pageIndex", page.getPageNo());
			pageJson.put("pageSize", page.getPageSize());
			pageJson.put("totalItems", page.getTotalItems());
			pageJson.put("totalPages", page.getTotalPages());
			pageJson.put("nextPage", page.getNextPage());

			// 查询记录集
			JSONArray jsonArray = new JSONArray();
			ServiceResult<List<MytBookenrolView>> srMb = bookenrolService
					.getMytBookenrolResultS(qryMbBean, 0, page,
							MytConst.QUERY_EXT.getValue());
			if (srMb.getCode() > 0) {
				List<MytBookenrolView> lstObjs = srMb.getResult();
				String date = DateUtil.dateFormat(DateOper.getNowDateTime(),
						DateUtil.YMD_FORMAT);
				String time = DateUtil.dateFormat(DateOper.getNowDateTime(),
						DateUtil.HM_FORMAT);
				if (lstObjs != null && !lstObjs.isEmpty()) {
					for (MytBookenrolView obj : lstObjs) {
						JSONObject json = new JSONObject();
						com.yihu.baseinfo.api.DoctorInfoApi doctorInfoApi=Rpc.get(DoctorInfoApi.class
								   , ConfigUtil.getInstance().getUrl("url.baseinfo"));
						ServiceResult<String> dc = doctorInfoApi.getDoctorByUid(obj.getOperconfid());
						JSONObject dcJson = new JSONObject(dc.getResult());
						json.put("bookenrolid", obj.getBookenrolid());
						json.put("operconfid", obj.getOperconfid());
						json.put("orgname", dcJson.get("provinceName"));
						json.put("doctorname", dcJson.get("doctorName"));
						json.put("deptname",  dcJson.get("deptName"));
						
						
						/*JSONObject json = new JSONObject();
						json.put("bookenrolid", obj.getBookenrolid());
						json.put("operconfid", obj.getOperconfid());
						json.put("orgname", obj.getOrgname());
						json.put("doctorname", obj.getDoctorname());
						json.put("deptname", obj.getHospofficename());*/

						// 判断医生是否排班在线
						int whichColor = 0;
						ServiceResult<MytArraworkBean> srMa = arraworkService
								.getMytArrawork(
										obj.getOperconfid(),
										time);
						if (srMa.getCode() > 0 && srMa.getResult() != null) {
							// 排班在线
							whichColor = 1;

							// 判断医生是否停诊
							ServiceResult<MytPauseworkBean> srMp = pauseService
									.getEntity(obj
											.getOperconfid(), time, date);
							if (srMp.getCode() > 0 && srMp.getResult() != null) {
								// 医生停诊
								whichColor = 2;
							} else {
								// 判断医生是否占线
								if (OperconfidAction.get("Y"
										+ obj.getOperconfid()) != null) {
									whichColor = 4;
								} else if (OperconfidAction.get("N"
										+ obj.getOperconfid()) != null) {
									// 计算在线时间
									int times = (int) (DateOper
											.getNowDateTime().getTime() - DateUtil
											.parse(OperconfidAction.get("N"
													+ obj.getOperconfid()),
													DateUtil.YMDHMS_FORMAT)
											.getTime());
									// 确定在线时间为10分钟
									if (times <= 600) {
										whichColor = 3;
									}
								}
								if (arraworkService.isBusy(obj
										.getOperconfid()))
									whichColor = 4;
							}
						}

						json.put("whichcolor", whichColor
								+ Constant.EMPTY_SYMBOL);
						json.put("cardid",
								obj.getCardid() == null ? Constant.CROSS_SYMBOL
										: obj.getCardid());
						json.put(
								"custname",
								obj.getCustname() == null ? Constant.CROSS_SYMBOL
										: obj.getCustname());
						json.put(
								"revertphone",
								obj.getRevertphone() == null ? Constant.CROSS_SYMBOL
										: obj.getRevertphone());
						json.put("revertresult", obj.getRevertresult());
						// 获取咨询回访备注信息
						List<MytRevertBean> lstMrBean = bookenrolService
								.getMytRevert(obj.getBookenrolid() + "")
								.getResult();
						StringBuffer remark = new StringBuffer(
								"["
										+ (obj.getOperatorname() == null ? Constant.CROSS_SYMBOL
												: obj.getOperatorname())
										+ Constant.SPACE_SYMBOL
										+ (obj.getOpertime() == null ? Constant.CROSS_SYMBOL
												: DateOper.formatDate(
														obj.getOpertime(),
														DateUtil.YMDHMS_FORMAT)
														+ "];")
										+ (obj.getRemark() == null ? Constant.CROSS_SYMBOL
												: obj.getRemark()));
						if (lstMrBean != null && !lstMrBean.isEmpty()) {
							for (MytRevertBean mrBean : lstMrBean) {
								remark.append("  ["
										+ mrBean.getOperatorname()
										+ Constant.SPACE_SYMBOL
										+ DateOper.formatDate(
												mrBean.getOpertime(),
												DateUtil.YMDHMS_FORMAT) + "]："
										+ mrBean.getRemark());
							}
						}
						json.put("remark", remark);
						json.put(
								"dateweek",
								(obj.getDateweek() == null ? Constant.EMPTY_SYMBOL
										: obj.getDateweek())
										+ Constant.SPACE_SYMBOL
										+ (obj.getStarttime() == null ? Constant.EMPTY_SYMBOL
												: obj.getStarttime())
										+ "--"
										+ (obj.getEndtime() == null ? Constant.EMPTY_SYMBOL
												: obj.getEndtime()));
						jsonArray.put(json);
					}
				}
			}

			// 返回结果
			JSONObject dataJson = new JSONObject();
			dataJson.put("totalProperty", pageJson.get("totalItems"));
			dataJson.put("result", jsonArray);
			return new HttpResponseContext(dataJson);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(-14444, e.getMessage());
		}
	}
}
