package com.yihu.myt.http;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.yihu.myt.ConfigUtil;
import com.yihu.myt.IArraworkService;
import com.yihu.myt.IBookenrolService;
import com.yihu.myt.IConswaterService;
import com.yihu.myt.IFaultDealService;
import com.yihu.myt.enums.Constant;
import com.yihu.myt.enums.MytConst;
import com.yihu.myt.mgr.ApiUtil;
import com.yihu.myt.service.service.ICommonService;
import com.yihu.myt.service.service.IConsWaterFollowService;
import com.yihu.myt.service.service.IConsWaterQualityService;
import com.yihu.myt.util.DateUtil;
import com.yihu.myt.util.StringUtil;
import com.yihu.myt.vo.ConsWaterFollowVo;
import com.yihu.myt.vo.ConsWaterQualityVo;
import com.yihu.myt.vo.Consstatistic;
import com.yihu.myt.vo.DicEntity;
import com.yihu.myt.vo.MytConsenrolBean;
import com.yihu.myt.vo.MytConswaterBean;
import com.yihu.myt.vo.MytConswaterSatisfactionBean;
import com.yihu.myt.vo.MytDoctorViewBean;
import com.yihu.myt.vo.Page;
import com.yihu.oa.api.IBasisWS;
import com.yihu.wsgw.api.InterfaceMessage;

public class ConswaterAction {

	private static IConswaterService conswaterService = Ioc.get(IConswaterService.class);	// 咨询计费流水服务接口
	private static IArraworkService arraworkService = Ioc.get(IArraworkService.class);	// 医生排班服务接口
	private static IBookenrolService bookenrolService = Ioc.get(IBookenrolService.class); 	// 预约回复服务接口
	private static IFaultDealService faultDealService = Ioc.get(IFaultDealService.class); 	// 名医通咨询流水操作
	
	private static IConsWaterFollowService consWaterFollowService = Ioc.get(IConsWaterFollowService.class); 	// 质检数据
	private static IConsWaterQualityService consWaterQualityService = Ioc.get(IConsWaterQualityService.class); 	// 跟进数据
	
	public static   ICommonService   commonService =  Ioc.get(ICommonService.class);
	
	
	public static org.apache.log4j.Logger  MyLog = org.apache.log4j.Logger.getLogger(ConswaterAction.class);
	
	 
	
	
	/**	
	 * 
	 * queryConsOperation
	 * 咨询运营支撑数据统计
	 * @param request
	 * @return
	 */
	public HttpResponseContext queryConsOperation(HttpRequestContext request) {
		
	//System.out.println("new  request");
		
		try {
			// 获取查询参数
			String starttime = request.getParameter("starttimes");
			String endtime = request.getParameter("endtimes");
			
			JSONArray jsonArray = new JSONArray();
			if (StringUtils.isNotEmpty(starttime) && StringUtils.isNotEmpty(endtime)) {
				//查询的条件
				Consstatistic bean = new Consstatistic();
				//时间 条件
				bean.setStartdate(starttime);
				bean.setEnddate(endtime);
				// 设置分页及查询记录数
				Page page = new Page(request.getInt("pageIndex"), request.getInt("pageSize"));
			//	page.setOrderProp(Order.DESC("a.CONSENROLID"));
				page.setTotalItems(bookenrolService.getAllConsstatisticCount(bean).getResult());
				JSONObject pageJson = new JSONObject();
				pageJson.put("pageIndex", page.getPageNo());
				pageJson.put("pageSize", page.getPageSize());
				pageJson.put("totalItems", page.getTotalItems());
				pageJson.put("totalPages", page.getTotalPages());
				pageJson.put("nextPage", page.getNextPage());
				// 查询记录集
				ServiceResult<List<Consstatistic>>  backresults = bookenrolService.getAllConsstatistic(bean, page);
				List<Consstatistic>  results= backresults.getResult();
				//results    转换  json
				net.sf.json.JSONArray  list = net.sf.json.JSONArray.fromObject(results);
//				
//				//获取字典的 MYT_DOC 并且封装到DicEntity 对象
//				
//				List<Map<String, Object>>   backlist= commonService.getBusinName("MYT_DOC");
//				List<DicEntity>  docquery = new ArrayList<DicEntity>();		
//				
//				for (Map<String, Object> mapp : backlist) {
//					
//					//
//					DicEntity entity = new DicEntity();
//					
//					for (String key : mapp.keySet()) {
//						
//						
//						
//						if("businID".equals(key)){
//							entity.setBusinID((String) mapp.get(key));
//							
//							
//						}
//		                if("businName".equals(key)){
//		                	entity.setBusinName((String) mapp.get(key));
//							
//						}
//					}
//					docquery.add(entity);
//				}
//				
//				
//				//获取的list
//				
//				for (DicEntity dic : docquery) {
//					
//					System.out.println("所有的名医ID：："+dic.getBusinID());
//					System.out.println("所有的名医NAME：："+dic.getBusinName());
//					
//					
//				}
			
	
				JSONObject dataJson = new JSONObject();
				dataJson.put("page", pageJson);
				dataJson.put("list", list);
				return new HttpResponseContext(dataJson);
			}
			return new HttpResponseContext(new JSONObject().put("result", jsonArray).put("totalProperty", 0));
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(e);
		}
		
	}

	
	
	
	
	
	
	/**
	 * 咨询流水集合
	 * @param request
	 * @return
	 */
	public HttpResponseContext queryConswater(HttpRequestContext request) {
		

		
		try {
			// 获取操作者信息
			OperatorInfo operator = request.getOperator();
			if (operator == null) {
				return new HttpResponseContext("您还未登录或登录已超时。");
			}
			String starttime =request.getParameter("starttimes"); // 获取开始时间
			String endtime=request.getParameter("endtimes"); // 获取结束时间
			int cardorgid=request.getInt("cardorgid"); // 会员机构
			String  custphone=request.getParameter("custphone"); // 号码
			
			String  operatorname=request.getParameter("operatorname"); // zuoxi
			

			// 设置条件获取
			MytConswaterBean qryMcBean = new MytConswaterBean();
			qryMcBean.setState(MytConst.EFFECTIVE.getValue() );
			String orgid = request.getParameter("orgSelect"); // 获取机构信息
			if (StringUtils.isNotEmpty(orgid)) {
				qryMcBean.setOrgid(Integer.parseInt(orgid));
			}
			String hospOfficeId = request.getParameter("officeSelect"); // 获取科室信息
			if ("-1".equals(hospOfficeId)) {
				qryMcBean.setDoctorlevel(MytConst.DOCTORLEVEL_GENERAL_PRACTITIONER.getValue() + Constant.EMPTY_SYMBOL);
			} else if (StringUtils.isNotEmpty(hospOfficeId)) {
				qryMcBean.setHospofficeid(hospOfficeId);
			}
			String doctorName = request.getParameter("doctorInput"); // 医生名称
			if (StringUtils.isNotEmpty(doctorName)) {
				qryMcBean.setDoctorname(doctorName);
			}
			if (StringUtils.isNotEmpty(starttime))
				qryMcBean.setMindatetime(DateUtil.parse(DateUtil.stringFormat(starttime, DateUtil.YMD_FORMAT).getTime()));
			if (StringUtils.isNotEmpty(endtime))
				qryMcBean.setMaxdatetime(DateUtil.parse(DateUtil.stringFormat(endtime, DateUtil.YMD_FORMAT).getTime()));
			String cardid = request.getParameter("cardInput"); // 会员卡号
			if (StringUtils.isNotEmpty(cardid)) {
				qryMcBean.setCardid(cardid);
			}
			
			if (StringUtils.isNotEmpty(custphone)) {
				qryMcBean.setCustphone(custphone);//haoma
			}
			if (StringUtils.isNotEmpty(operatorname)) {
				qryMcBean.setOperatorname(operatorname);//zuoxi2
			}
			
			
			
			String score = request.getParameter("scoreSelect"); // 满意度
			if (StringUtils.isNotEmpty(score)) {
				qryMcBean.setScore(Integer.parseInt(score));
			}

			// 设置分页及查询记录数
			Page<MytConswaterBean> page = new Page<MytConswaterBean>(request.getInt("pageIndex"),
				request.getInt("pageSize"));
			page.setOrderProp(Order.DESC("a.pkconswaterid"));
			page.setTotalItems(conswaterService.getMytConswaterCount(qryMcBean, cardorgid).getResult());
			JSONObject pageJson = new JSONObject();
			pageJson.put("pageIndex", page.getPageNo());
			pageJson.put("pageSize", page.getPageSize());
			pageJson.put("totalItems", page.getTotalItems());
			pageJson.put("totalPages", page.getTotalPages());
			pageJson.put("nextPage", page.getNextPage());

			// 获取记录集及显示
			ServiceResult<List<MytConswaterBean>> sr = conswaterService.getMytConswaterResult(qryMcBean, page, cardorgid);
			JSONArray jsonArray = new JSONArray();
			if (sr.getCode() > 0) {
				for (MytConswaterBean o : sr.getResult()) {
					JSONObject covJson = new JSONObject();
					// 查询医生视图信息
					MytDoctorViewBean rltMdvBean = arraworkService.getMytDoctorView(o.getOperconfid()).getResult();
					if (rltMdvBean != null) {
						covJson.put("pkconswaterid", o.getPkconswaterid());
						covJson.put("filepath", o.getFilepath());
						covJson.put("orgname", rltMdvBean.getOrgname());
						covJson.put("cardid", o.getCardid());
						covJson.put("doctorname", o.getDoctorname());
						covJson.put("hospofficename", o.getHospofficename());
						covJson.put(
							"startdatetime",
							o.getStartdatetime() == null ? Constant.CROSS_SYMBOL : DateUtil.dateFormat(
								o.getStartdatetime(), DateUtil.YMDHMS_FORMAT));
						covJson.put(
							"enddatetime",
							o.getEnddatetime() == null ? Constant.CROSS_SYMBOL : DateUtil.dateFormat(
								o.getEnddatetime(), DateUtil.HMS_FORMAT));
						covJson.put("consmin", o.getConsmin());
						covJson.put("charge", o.getCharge() / 100 * -1);
						covJson.put("custphone", o.getCustphone());
						covJson.put("mytfeename", o.getMytfeename());
						covJson.put("score", o.getScore());
						covJson.put("operatorname", o.getOperatorname());
						jsonArray.put(covJson);
					}
				}
			}
			// 返回记录
			JSONObject dataJson = new JSONObject();
			dataJson.put("page", pageJson);
			dataJson.put("list", jsonArray);
			return new HttpResponseContext(dataJson);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(e);
		}
	}
	
	/**
	 * 查询咨询流水满意度回访集合
	 * @param request
	 * @return
	 */
	public HttpResponseContext queryConswaterSatisfaction(HttpRequestContext request) {
		
		try {
			// 获取操作者信息
			OperatorInfo operator = request.getOperator();
			if (operator == null) {
				return new HttpResponseContext("您还未登录或登录已超时。");
			}
			
			// 查询参数设置
			MytConswaterSatisfactionBean qryMcsBean = new MytConswaterSatisfactionBean();	
			qryMcsBean.setFkconswaterid(request.getParameter("backId"));
			
			// 设置分页及查询记录数
			Page<MytConswaterSatisfactionBean> page = new Page<MytConswaterSatisfactionBean>(request.getInt("pageIndex"), request.getInt("pageSize"));
			page.setOrderProp(Order.DESC("a.pksatisfactionid"));
			page.setTotalItems(conswaterService.getMytConswaterSatisfactionCount(qryMcsBean).getResult());
			JSONObject pageJson = new JSONObject();
			pageJson.put("pageIndex", page.getPageNo());
			pageJson.put("pageSize", page.getPageSize());
			pageJson.put("totalItems", page.getTotalItems());
			pageJson.put("totalPages", page.getTotalPages());
			pageJson.put("nextPage", page.getNextPage());
			
			// 查询记录集并装载数据记录
			JSONArray jsons = new JSONArray();
			ServiceResult<List<MytConswaterSatisfactionBean>> sr = conswaterService.getMytConswaterSatisfactionResult(qryMcsBean, page);
			if (sr.getCode() > 0) {
				List<MytConswaterSatisfactionBean> lstMcsBean = sr.getResult();
				for (MytConswaterSatisfactionBean mcsBean : lstMcsBean) {
					if (mcsBean != null) {
						JSONObject json = new JSONObject();
						json.put("fkconswaterid", mcsBean.getFkconswaterid());
						json.put("custphone", mcsBean.getCustphone());
						json.put("remark", mcsBean.getRemark());
						json.put("reason", mcsBean.getReason());
						json.put("procresult", mcsBean.getProcresult());
						json.put("recvStatus", mcsBean.getRecvstatus());
						jsons.put(json);
					}
				}
			}
			
			// 返回记录
			JSONObject dataJson = new JSONObject();
			dataJson.put("page", pageJson);
			dataJson.put("list", jsons);
			return new HttpResponseContext(dataJson);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(e);
		}
	}
	
	/**
	 * 添加咨询流水满意度回访
	 * @param request
	 * @return
	 */
	public HttpResponseContext addSatisfaction(HttpRequestContext request) {
		
		try {
			OperatorInfo operator = request.getOperator();
			MytConswaterSatisfactionBean bean = new MytConswaterSatisfactionBean();
			try {
				BeanUtils.populate(bean, request.getParamMap());
			} catch (Exception e) {
				e.printStackTrace();
			}
			bean.setOperatorid(operator.getOperatorID());
			bean.setOperatorname(operator.getOperatorName());
			bean.setOpertime(DateOper.getNowDateTime());
			
			ReturnValue rv = conswaterService.addMytConsWaterSatisfaction(bean);
			if (rv.getCode() < 0) {
				return new HttpResponseContext(rv.getMessage());
			}
			return new HttpResponseContext(rv.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(e);
		}
	}
	
	
	/**
	 * 查询预约回复集合---OLD
	 */
	public HttpResponseContext oldqueryConsenrol(HttpRequestContext request) {
		
		System.out.println("old----------");

		try {
			// 获取查询参数
			String starttime = request.getParameter("starttimes");
			String endtime = request.getParameter("endtimes");
			
			JSONArray jsonArray = new JSONArray();
			if (StringUtils.isNotEmpty(starttime) && StringUtils.isNotEmpty(endtime)) {
				String doctorName = request.getParameter("doctorInput");
				int orgid = request.getInt("orgid");
				String turnresult = request.getParameter("turnresult");
				String constype = request.getParameter("constype");
				
				//机构
				int orgSelect = request.getInt("orgSelect");
				//cardInput 会员卡号
				String cardInput = request.getParameter("cardInput");
				//custphone 会员电话
				String custphone = request.getParameter("custphone");
				//operatorname坐席
				String operatorname = request.getParameter("operatorname");
				
				
				
				
				MytConsenrolBean qryMcBean = new MytConsenrolBean();
				
				if (StringUtils.isNotEmpty(operatorname)) {
					qryMcBean.setOperatorname(operatorname);
				}
				
				
				
				if (StringUtils.isNotEmpty(custphone)) {
					qryMcBean.setCustphone(custphone);
				}
				
				
				
				if (StringUtils.isNotEmpty(cardInput)) {
					qryMcBean.setCardid(cardInput);
				}
				
			
				
				
				
				qryMcBean.setState(MytConst.EFFECTIVE.getValue() );
				if (StringUtils.isNotEmpty(doctorName)) {
					qryMcBean.setDoctorname(doctorName);
				}
				if (orgid>0) {
					qryMcBean.setOrgid(orgid);
				}
				if (StringUtils.isNotEmpty(turnresult)) {
					qryMcBean.setTurnresult(turnresult);
				}
				if (StringUtils.isNotEmpty(constype)) {
					qryMcBean.setConstype(constype);
				}
				qryMcBean.setMinDateTime(starttime);
				qryMcBean.setMaxDateTime(endtime);
				
			
				
				// 设置分页及查询记录数
				
				Page<MytConsenrolBean> page = new Page<MytConsenrolBean>(request.getInt("start"), request.getInt("limit"));
				page.setOrderProp(Order.DESC("a.CONSENROLID"));
				page.setTotalItems(bookenrolService.getMytConsenrol(qryMcBean).getResult());
				JSONObject pageJson = new JSONObject();
				pageJson.put("pageIndex", page.getPageNo());
				pageJson.put("pageSize", page.getPageSize());
				pageJson.put("totalItems", page.getTotalItems());
				pageJson.put("totalPages", page.getTotalPages());
				pageJson.put("nextPage", page.getNextPage());
				
				
				
				// 查询记录集及数据装载
				ServiceResult<JSONArray> srMc = bookenrolService.getMytConsenrol(qryMcBean, page);
				
				
				
//				if (srMc.getCode() > 0) {
//					for (MytConsenrolBean mcBean : srMc.getResult()) {
//						JSONObject jsonMc = new JSONObject();
//						jsonMc.put("opertime", mcBean.getOpertime() == null ? Constant.CROSS_SYMBOL : DateUtil.dateFormat(mcBean.getOpertime(), DateUtil.YMDHMS_FORMAT));
//						jsonMc.put("cardid", mcBean.getCardid());
//						ServiceResult<MytDoctorViewBean> srMdvBean = arraworkService.getMytDoctorView(mcBean.getOperconfid());
//						jsonMc.put("doctorname", srMdvBean.getCode() > 0 ? srMdvBean.getResult().getDoctorname() : Constant.CROSS_SYMBOL);
//						jsonMc.put("constype", mcBean.getConstype());
//						jsonMc.put("turnresult", mcBean.getTurnresult());
//						jsonMc.put("turndefeat", mcBean.getTurndefeat());
//						jsonMc.put("isrevert", mcBean.getIsrevert());
//						jsonMc.put("operatorname", mcBean.getOperatorname());
//						jsonMc.put("remark", mcBean.getRemark());
//						jsonArray.put(jsonMc);
//					}
//				}
			
				// 返回数据
	    	JSONObject jsonResult = new JSONObject().put("result", srMc.getResult()).put("totalProperty", page.getTotalItems());
//				
				JSONObject dataJson = new JSONObject();
				dataJson.put("page", pageJson);
				dataJson.put("list", srMc.getResult().put("totalProperty", page.getTotalItems()));
				
				System.out.println(srMc.getResult().toString()+"++++++++++++");
				
				
				
				return new HttpResponseContext(jsonResult);
			}
			return new HttpResponseContext(new JSONObject().put("result", jsonArray).put("totalProperty", 0));
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(e);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 查询预约回复集合
	 * @param request
	 * @param response
	 */
	public HttpResponseContext queryConsenrol(HttpRequestContext request) {

		try {
			// 获取查询参数
			String starttime = request.getParameter("starttimes");
			String endtime = request.getParameter("endtimes");
			
			JSONArray jsonArray = new JSONArray();
			if (StringUtils.isNotEmpty(starttime) && StringUtils.isNotEmpty(endtime)) {
				String doctorName = request.getParameter("doctorInput");
				int orgid = request.getInt("orgid");
				String turnresult = request.getParameter("turnresult");
				String constype = request.getParameter("constype");
				
				//机构
				int orgSelect = request.getInt("orgSelect");
				//cardInput 会员卡号
				String cardInput = request.getParameter("cardInput");
				//custphone 会员电话
				String custphone = request.getParameter("custphone");
				//operatorname坐席
				String operatorname = request.getParameter("operatorname");
				
				
				
				
				MytConsenrolBean qryMcBean = new MytConsenrolBean();
				
				if (StringUtils.isNotEmpty(operatorname)) {
					qryMcBean.setOperatorname(operatorname);
				}
				
				
				
				if (StringUtils.isNotEmpty(custphone)) {
					qryMcBean.setCustphone(custphone);
				}
				
				
				
				if (StringUtils.isNotEmpty(cardInput)) {
					qryMcBean.setCardid(cardInput);
				}
				
			
				
				
				
				qryMcBean.setState(MytConst.EFFECTIVE.getValue() );
				if (StringUtils.isNotEmpty(doctorName)) {
					qryMcBean.setDoctorname(doctorName);
				}
				if (orgid>0) {
					qryMcBean.setOrgid(orgid);
				}
				if (StringUtils.isNotEmpty(turnresult)) {
					qryMcBean.setTurnresult(turnresult);
				}
				if (StringUtils.isNotEmpty(constype)) {
					qryMcBean.setConstype(constype);
				}
				qryMcBean.setMinDateTime(starttime);
				qryMcBean.setMaxDateTime(endtime);
				
			
				
				// 设置分页及查询记录数
				
				Page<MytConsenrolBean> page = new Page<MytConsenrolBean>(request.getInt("pageIndex"), request.getInt("pageSize"));
				page.setOrderProp(Order.DESC("a.CONSENROLID"));
				page.setTotalItems(bookenrolService.getMytConsenrol(qryMcBean).getResult());
				JSONObject pageJson = new JSONObject();
				pageJson.put("pageIndex", page.getPageNo());
				pageJson.put("pageSize", page.getPageSize());
				pageJson.put("totalItems", page.getTotalItems());
				pageJson.put("totalPages", page.getTotalPages());
				pageJson.put("nextPage", page.getNextPage());
				
				
				
				// 查询记录集及数据装载
				ServiceResult<JSONArray> srMc = bookenrolService.getMytConsenrol(qryMcBean, page);
				
				
				
//				if (srMc.getCode() > 0) {
//					for (MytConsenrolBean mcBean : srMc.getResult()) {
//						JSONObject jsonMc = new JSONObject();
//						jsonMc.put("opertime", mcBean.getOpertime() == null ? Constant.CROSS_SYMBOL : DateUtil.dateFormat(mcBean.getOpertime(), DateUtil.YMDHMS_FORMAT));
//						jsonMc.put("cardid", mcBean.getCardid());
//						ServiceResult<MytDoctorViewBean> srMdvBean = arraworkService.getMytDoctorView(mcBean.getOperconfid());
//						jsonMc.put("doctorname", srMdvBean.getCode() > 0 ? srMdvBean.getResult().getDoctorname() : Constant.CROSS_SYMBOL);
//						jsonMc.put("constype", mcBean.getConstype());
//						jsonMc.put("turnresult", mcBean.getTurnresult());
//						jsonMc.put("turndefeat", mcBean.getTurndefeat());
//						jsonMc.put("isrevert", mcBean.getIsrevert());
//						jsonMc.put("operatorname", mcBean.getOperatorname());
//						jsonMc.put("remark", mcBean.getRemark());
//						jsonArray.put(jsonMc);
//					}
//				}
			
				// 返回数据
	    // 		JSONObject jsonResult = new JSONObject().put("result", srMc.getResult()).put("totalProperty", page.getTotalItems());
//				
				JSONObject dataJson = new JSONObject();
				dataJson.put("page", pageJson);
				dataJson.put("list", srMc.getResult());
				
				System.out.println(srMc.getResult().toString()+"++++++++++++");
				
				
				
				return new HttpResponseContext(dataJson);
			}
			return new HttpResponseContext(new JSONObject().put("result", jsonArray).put("totalProperty", 0));
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(e);
		}
	}
	
	/**
	 * 根据咨询编号查询
	 * @param request
	 * @return
	 */
	public HttpResponseContext SearchPkconswaterid(HttpRequestContext request) {
		
		try {
			// 获取流水编号
			String pkconswaterid = request.getParameter("pkconswaterid");
			if (StringUtils.isEmpty(pkconswaterid)) {
				return new HttpResponseContext("流水编号不存在。");
			}
			
			// 查询流水信息
			MytConswaterBean qryMcBean = new MytConswaterBean();
			qryMcBean.setPkconswaterid(Integer.parseInt(pkconswaterid));
			ServiceResult<MytConswaterBean> sr = conswaterService.getMytConswaterEntity(qryMcBean);
			if (sr.getCode() < 0) {
				return new HttpResponseContext(sr.getCode(), sr.getMessage());
			}
			MytConswaterBean mcBean = sr.getResult();
			
			// 数据装载并返回
			JSONObject jsonMc = new JSONObject();
			jsonMc.put("cardid", mcBean.getCardid())
				.put("custphone", mcBean.getCustphone())
				.put("doctorname", mcBean.getDoctorname())
				.put("doctorphone", mcBean.getDoctorphone())
				.put("startdatetime", mcBean.getStartdatetime())
				.put("mytfeename", mcBean.getMytfeename())
				.put("operatorname", mcBean.getOperatorname());
			return new HttpResponseContext(new JSONObject().put("result", new JSONArray().put(jsonMc)));
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(e);
		}
	}
	
	/**
	 * 手动添加咨询流水
	 * @param request
	 * @return
	 * @throws SQLException 
	 */
	public HttpResponseContext Add(HttpRequestContext request) {
		try {
			// 获取操作者信息
			OperatorInfo operator = request.getOperator();	
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
			if (operator == null) {
				return new HttpResponseContext(-1, "您还未登录或登录已超时。");
			}
			MytConswaterBean water = new MytConswaterBean();
			water.setOperatorid(operator.getOperatorID());
			water.setOperatorname(operator.getOperatorName());
			water.setCardid(request.getParameter("cardid"));
			water.setHotline(request.getParameter("hotline"));
			water.setCustphone(request.getParameter("custphone"));
			water.setMeetid(request.getParameter("meetid"));
			water.setOrgid(request.getInt("orgid"));
			water.setProvinceId(provinceId);
			water.setHospofficeid(request.getParameter("hospofficeid"));
			water.setHospofficename(request.getParameter("hospofficename"));
			water.setOperconfid(Integer.valueOf(request.getParameter("operconfid")));
			water.setDoctorphone(request.getParameter("doctorphone"));
			water.setStartdatetime(DateUtil.parse(DateUtil.parse(request.getParameter("startdatetime"), DateUtil.YMD_FORMAT).getTime()));
			if(request.getParameter("startdatetime").compareTo("2013-10-25")<=0)
				return new HttpResponseContext(-1, "采用新资费模式，2013-10-25之前的记录请找技术处理");
			water.setSevendoctorid(request.getParameter("sevendoctorid"));
			water.setTendoctorid(request.getParameter("tendoctorid"));
			water.setScore(request.getInt("score"));
			water.setRemark(request.getParameter("remark"));
			//ReturnValue rv = conswaterService.addMytConswater(water);
			ReturnValue rv = conswaterService.payMytConswater(water);
			return new HttpResponseContext(rv.getCode(), rv.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(-14444, e.getMessage());
		}
	}
	
	/**
	 * 撤销咨询流水
	 * @param request
	 * @return
	 */
	public HttpResponseContext DelPkconswaterid(HttpRequestContext request) {
		
		try {
			// 获取操作者信息
			OperatorInfo operator = request.getOperator();
			int pkconswaterid = request.getInt("pkconswaterid");
			MytConswaterBean water = new MytConswaterBean();
			water.setOperatorid(operator.getOperatorID());
			water.setOperatorname(operator.getOperatorName());
			water.setRemark(request.getParameter("remark"));
			water.setPkconswaterid(pkconswaterid);
			ReturnValue rv = faultDealService.repealMytConswater(water);
			return new HttpResponseContext(rv.getCode(), rv.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(-14444, e.getMessage());
		}
	}
	
	/**
	 * 修改咨询流水
	 * @param request
	 * @return
	 */
	public HttpResponseContext UpdatePkconswaterid(HttpRequestContext request) {
		
		try {
			// 获取操作者信息
			OperatorInfo operator = request.getOperator();
			if (operator == null) {
				return new HttpResponseContext(-1, "您还未登录或登录已超时。");
			}
			int pkconswaterid = request.getInt("pkconswaterid");
			if (pkconswaterid<=0) {
				return new HttpResponseContext(-1, "流水编号不存在。");
			}
			ReturnValue rv = faultDealService.reviseMytConswater(pkconswaterid,request.getParameter("realcard"),request.getParameter("remark"),
				operator.getOperatorID(),operator.getOperatorName());
			return new HttpResponseContext(rv.getCode(), rv.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(-14444, e.getMessage());
		}
	}
	
	/**
	 * 名医咨询异常流水列表
	 * @param request
	 * @return
	 */
	public HttpResponseContext SearchWrong(HttpRequestContext request) {
		
		try {
			String orgId = request.getParameter("orgid");
			String cardId = request.getParameter("cardid");
			String hospofficeId = request.getParameter("hospofficeid");
			String doctorName = request.getParameter("doctorname");
			String score = request.getParameter("score");
			String starttime =request.getParameter("starttimes");
			String endtime=request.getParameter("endtimes");
			
			MytDoctorViewBean qryMdvBean = new MytDoctorViewBean();
			if (StringUtils.isNotEmpty(doctorName)
					&& StringUtils.isNotEmpty(hospofficeId)
					&& StringUtils.isNotBlank(orgId)) {

				qryMdvBean.setState(MytConst.EFFECTIVE.getValue());
				qryMdvBean.setDoctorname(doctorName);
				if ("-1".equals(hospofficeId))
					qryMdvBean.setDoctorlevel(7);
				else
					qryMdvBean.setHospofficeid(hospofficeId);

				qryMdvBean.setOrgid(Integer.parseInt(orgId));
			}
			ServiceResult<List<MytDoctorViewBean>> sr = arraworkService.getMytDoctorViewList(qryMdvBean, null);
			String operconfids = null;
			if (sr.getCode() > 0) {
				for (MytDoctorViewBean mdv : sr.getResult()) {
					if (operconfids == null)
						operconfids = mdv.getOperconfid() + Constant.EMPTY_SYMBOL;
					else
						operconfids = operconfids + Constant.COMMA_SYMBOL + mdv.getOperconfid();
				}
			}
			
			MytConswaterBean qryMcBean = new MytConswaterBean();
			if (StringUtils.isNotEmpty(orgId))
				qryMcBean.setOrgid(Integer.parseInt(orgId));
			if (StringUtils.isNotEmpty(cardId))
				qryMcBean.setCardid(cardId);
			if (StringUtils.isNotEmpty(hospofficeId))
				qryMcBean.setHospofficeid(hospofficeId);
			if (StringUtils.isNotEmpty(doctorName))
				qryMcBean.setDoctorname(doctorName);
			if (StringUtils.isNotEmpty(score))
				qryMcBean.setScore(Integer.parseInt(score));
			if (StringUtils.isNotEmpty(operconfids))
				qryMcBean.setOperconfids(operconfids);
			if (StringUtils.isNotEmpty(starttime))
				qryMcBean.setMindatetime(DateUtil.parse(DateUtil.stringFormat(starttime, DateUtil.YMD_FORMAT).getTime()));
			if (StringUtils.isNotEmpty(endtime))
				qryMcBean.setMaxdatetime(DateUtil.parse(DateUtil.stringFormat(endtime, DateUtil.YMD_FORMAT).getTime()));
			qryMcBean.setState(MytConst.EFFECTIVE.getValue());
			
			// 设置分页及查询记录数
			Page<MytConswaterBean> page = new Page<MytConswaterBean>(request.getInt("start"), request.getInt("limit"));
			page.setOrderProp(Order.DESC("a.PKCONSWATERID"));
			page.setTotalItems(conswaterService.getMytConswaterWrongCount(qryMcBean).getResult());
			JSONObject pageJson = new JSONObject();
			pageJson.put("pageIndex", page.getPageNo());
			pageJson.put("pageSize", page.getPageSize());
			pageJson.put("totalItems", page.getTotalItems());
			pageJson.put("totalPages", page.getTotalPages());
			pageJson.put("nextPage", page.getNextPage());
			
			// 获取记录集及显示
			ServiceResult<List<MytConswaterBean>> sr2 = conswaterService.getMytConswaterWrongResult(qryMcBean, page);
			JSONArray jsonArray = new JSONArray();
//			System.out.println(sr2.getCode()+":"+sr2.getMessage()+":"+sr2.getResult());
//			System.out.println(sr2.getCode() > 0);
			if (sr2.getCode() > 0) {
				for (MytConswaterBean o : sr2.getResult()) {
					JSONObject covJson = new JSONObject();
					// 查询医生视图信息
					MytDoctorViewBean rltMdvBean = arraworkService.getMytDoctorView(o.getOperconfid()).getResult();
					if (rltMdvBean != null) {
						covJson.put("pkconswaterid", o.getPkconswaterid());
						covJson.put("filepath", o.getFilepath());
						covJson.put("orgname", rltMdvBean.getOrgname());
						covJson.put("cardid", o.getCardid());
						covJson.put("doctorname", o.getDoctorname());
						covJson.put("hospofficename", o.getHospofficename());
						covJson.put("startdatetime", o.getStartdatetime() == null ? Constant.CROSS_SYMBOL : DateUtil.dateFormat(o.getStartdatetime(), DateUtil.YMDHMS_FORMAT));
						covJson.put("enddatetime", o.getEnddatetime() == null ? Constant.CROSS_SYMBOL : DateUtil.dateFormat(o.getEnddatetime(), DateUtil.HMS_FORMAT));
						covJson.put("consmin", o.getConsmin());
						covJson.put("charge", o.getCharge() / 100 * -1);
						covJson.put("custphone", o.getCustphone());
						covJson.put("mytfeename", o.getMytfeename());
						covJson.put("score", o.getScore());
						covJson.put("operatorname", o.getOperatorname());
						jsonArray.put(covJson);
					}
				}
			}
			// 返回记录
			JSONObject dataJson = new JSONObject();
			dataJson.put("totalProperty", pageJson.get("totalItems"));
			dataJson.put("result", jsonArray);
//			System.out.println(dataJson.toString());
			return new HttpResponseContext(dataJson);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(e);
		}
	}
	
	/**
	 * 根据编号查询错误流水信息
	 * @param request
	 * @return
	 */
	public HttpResponseContext SearchPkconswateridWrong(HttpRequestContext request) {
		
		try {
			String pkconswaterid = request.getParameter("pkconswaterid");
			if (StringUtils.isEmpty(pkconswaterid)) {
				return new HttpResponseContext(new JSONObject().put("result", "咨询流水编号错误。"));
			}
			MytConswaterBean qryMcBean = new MytConswaterBean();
			qryMcBean.setPkconswaterid(Integer.parseInt(pkconswaterid));
			ServiceResult<MytConswaterBean> sr = conswaterService.getMytConswaterEntity(qryMcBean);
			if (sr.getCode() < 0) {
				return new HttpResponseContext(new JSONObject().put("result", "咨询流水不存在。"));
			}
			MytConswaterBean o = sr.getResult();
			JSONArray jsonArray = new JSONArray();
			JSONObject ob = new JSONObject();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String startdatetime = sdf.format(o.getStartdatetime());
			String cardid = ("会员" + o.getCardid() + "咨询"
					+ o.getHospofficename() + "的" + o.getDoctorname()
					+ "医生流水异常" + o.getOperatorname());
			ob.put("cardid", cardid);
			ob.put("conswaterid", o.getConswaterid());
			ob.put("startdatetime", startdatetime);
			ob.put("pkconswaterid", o.getPkconswaterid());
			jsonArray.put(ob);		
			return new HttpResponseContext(new JSONObject().put("result", jsonArray));
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(e);
		}
	}
	
	/**
	 * 修正异常流水
	 * @param request
	 * @return
	 */
	public HttpResponseContext UpdatePkconswateridWrong(HttpRequestContext request) {
	
		try {
			// 获取操作者信息
			OperatorInfo operator = request.getOperator();
			if (operator == null) {
				return new HttpResponseContext("您还未登录或登录已超时。");
			}
			int pkconswaterid = request.getInt("pkconswaterid");
			if (pkconswaterid==0) {
				return new HttpResponseContext("咨询流水编号错误。");
			}
			ReturnValue rv = faultDealService.dealMytConswater(pkconswaterid, request.getParameter("starttime"),
				request.getParameter("endtime"), request.getParameter("remark"), operator.getOperatorID(),
				operator.getOperatorName());
			return new HttpResponseContext(rv.getCode(), rv.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(-14444, e.getMessage());
		}
	}
	
	public HttpResponseContext queryBill(HttpRequestContext request) {

		try {
			String starttime = request.getParameter("starttimes");
			String endtime = request.getParameter("endtimes");

			MytConswaterBean bean = new MytConswaterBean();
			try {
				BeanUtils.populate(bean, request.getParamMap());
				if (StringUtils.isNotEmpty(starttime))
					bean.setStartdatetime(new Timestamp(DateOper.parse(starttime).getTime()));
				if (StringUtils.isNotEmpty(endtime))
					bean.setEnddatetime(new Timestamp(DateOper.parse(endtime).getTime()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			// 设置分页及查询记录数
			Page page = new Page(request.getInt("start"), request.getInt("limit"));
			page.setOrderProp(Order.DESC("a.pkconswaterid"));
			page.setTotalItems(conswaterService.getBillCount(bean).getResult());
			JSONObject pageJson = new JSONObject();
			pageJson.put("pageIndex", page.getPageNo());
			pageJson.put("pageSize", page.getPageSize());
			pageJson.put("totalItems", page.getTotalItems());
			pageJson.put("totalPages", page.getTotalPages());
			pageJson.put("nextPage", page.getNextPage());

			// 获取记录集及显示
			ServiceResult<JSONObject> sr = conswaterService.getBillResult(bean, page);
			if (sr.getCode() == 1)
				return new HttpResponseContext(new JSONObject().put("result", sr.getResult().getJSONArray("result"))
					.put("totalProperty", page.getTotalItems()));
			else
				return new HttpResponseContext(sr.getCode(), sr.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(e);
		}
	}
	
	public HttpResponseContext getWaterChakeList(HttpRequestContext request) {
		try{
			String json = request.getParameter("json");
			net.sf.json.JSONObject jsonobj =  net.sf.json.JSONObject.fromObject(json);
			System.out.println( StringUtil.isEmpty(jsonobj.get("type")) );
			String doctorName =  jsonobj.get("doctorName")==null?null: jsonobj.get("doctorName").toString();
			Integer hospid =  StringUtil.isEmpty(jsonobj.get("hospid"))? null : jsonobj.getInt("hospid");
			Integer provinceid =StringUtil.isEmpty(jsonobj.get("provinceid"))? null : jsonobj.getInt("provinceid"); 
			Integer type =  StringUtil.isEmpty(jsonobj.get("type"))?  null :  jsonobj.getInt("type");  
			Integer score =  StringUtil.isEmpty(jsonobj.get("score"))?  null : jsonobj.getInt("score");  
			Integer min = StringUtil.isEmpty( jsonobj.get("min"))?  null: jsonobj.getInt("min");  
			Integer chagneType = StringUtil.isEmpty( jsonobj.get("chagneType"))? null : jsonobj.getInt("chagneType");
			Integer quesNo = StringUtil.isEmpty( jsonobj.get("quesNo"))?  null : jsonobj.getInt("quesNo");
			Integer  pageSize = request.getInt("rows");
			Integer pageIndex = request.getInt("page");
			if(pageIndex >0){
				pageIndex = pageIndex - 1;
			}
			MytConswaterBean mytCon = new MytConswaterBean();
			mytCon.setDoctorname(doctorName);
			mytCon.setHospitalid(hospid);
			mytCon.setProvinceId(provinceid);
			mytCon.setScore(score);
			mytCon.setConsmin(min);
			int waterCont = conswaterService.getMytWaterCountByCheck(mytCon, type,chagneType,quesNo);
			JSONObject rt = conswaterService.getMytWaterByCheck(mytCon, type,chagneType,quesNo, pageSize, pageIndex);
			rt.put("totalProperty", waterCont);
			return new HttpResponseContext(rt.toString());
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(e);
		}
	}
	public HttpResponseContext saveFollow(HttpRequestContext request) {
		try{
			OperatorInfo oper = request.getOperator();
			Integer opid= oper.getOperatorID();
			String opName = oper.getOperatorName();
			String follwResult = request.getParameter("follwResult");
			Integer follwType =request.getInt("follwType"); 
			Integer consWaterID =request.getInt("consWaterID"); 
			ConsWaterFollowVo vo = new ConsWaterFollowVo();
			vo.setFollwResult(follwResult);
			vo.setFollwType(follwType);
			vo.setConsWaterID(consWaterID);
			vo.setInputUserID(opid.toString());
			vo.setInputUserName(opName);
			vo.setInputTime(DateUtil.dateFormat(new Date()));
			consWaterFollowService.insertConsWaterFollow(vo);
			return new HttpResponseContext(ApiUtil.getJsonRt(10000, "操作成功。"));
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(e);
		}
	}
	
	public HttpResponseContext getFollowLizt(HttpRequestContext request) {
		try {
			String json = request.getParameter("json");
			net.sf.json.JSONObject jsonobj = net.sf.json.JSONObject
					.fromObject(json);
			Integer consWaterID = jsonobj.get("consWaterID") == null ? null : jsonobj
					.getInt("consWaterID");
			ConsWaterFollowVo vo = new ConsWaterFollowVo();
			vo.setConsWaterID(consWaterID);
			 List<ConsWaterFollowVo>  list=consWaterFollowService.queryConsWaterFollowListByCondition(vo);
			int waterCont = consWaterFollowService.queryConsWaterFollowCountByCondition(vo);
			String rt = ApiUtil.getJsonRt(10000, "操作成功。");
			net.sf.json.JSONArray rtJson =  net.sf.json.JSONArray.fromObject(list);
			net.sf.json.JSONObject rtj =  net.sf.json.JSONObject.fromObject(rt);
			rtj.put("result", rtJson);
			rtj.put("totalProperty", waterCont);
			return new HttpResponseContext(rtj.toString());
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(e);
		}
	}
	public HttpResponseContext saveQuality(HttpRequestContext request) {
		try{
			String qualityMemo = request.getParameter("qualityMemo"); 
			Integer consType =request.getInt("consType");
			Integer qualityReult = request.getInt("qualityReult");
			Integer consWaterID =request.getInt("consWaterID");
			Integer qualityID =request.getInt("qualityID");
			OperatorInfo oper = request.getOperator();
			Integer opid= oper.getOperatorID();
			String opName = oper.getOperatorName();
			ConsWaterQualityVo vo  = new ConsWaterQualityVo();
			vo.setConsType(consType);
			vo.setcWaterID(consWaterID);
			vo.setQualityID(qualityID);
			vo.setQualityMemo(qualityMemo);
			vo.setQualityReult(qualityReult);
			vo.setInputUserID(opid.toString());
			vo.setInputUserName(opName);
			vo.setInputTime(DateUtil.dateFormat(new Date()));
			if(qualityID>0){
				consWaterQualityService.updateConsWaterQuality(vo);
			}else{
				consWaterQualityService.insertConsWaterQuality(vo);
			}
			return new HttpResponseContext(ApiUtil.getJsonRt(10000, "操作成功。"));
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(e);
		}
	}
	public HttpResponseContext getQuality(HttpRequestContext request) {
		try {
		
			Integer consWaterID =request.getInt("consWaterID");
			ConsWaterQualityVo vo  = new ConsWaterQualityVo();
			vo.setcWaterID(consWaterID);
			ConsWaterQualityVo con=consWaterQualityService.queryConsWaterQuality(vo);
			String rt = ApiUtil.getJsonRt(10000, "操作成功。");
			net.sf.json.JSONObject rtJson =  net.sf.json.JSONObject.fromObject(con);
			net.sf.json.JSONObject rtj =  net.sf.json.JSONObject.fromObject(rt);
			rtj.put("Result", rtJson);
			return new HttpResponseContext(rtj.toString());
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(e);
		}
	}
}
