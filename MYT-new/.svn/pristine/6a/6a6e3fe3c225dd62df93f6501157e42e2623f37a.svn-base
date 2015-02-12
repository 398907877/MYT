package com.yihu.myt.http;

import java.sql.Timestamp;
import java.util.Date;
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
import com.yihu.account.api.IAccountService;
import com.yihu.myt.ConfigUtil;
import com.yihu.myt.IArraworkService;
import com.yihu.myt.IPauseService;
import com.yihu.myt.enums.Constant;
import com.yihu.myt.enums.MytConst;
import com.yihu.myt.mgr.BusinessManager;
import com.yihu.myt.service.service.impl.PostService;
import com.yihu.myt.util.DateUtil;
import com.yihu.myt.vo.BossAccountBean;
import com.yihu.myt.vo.MytArraphoneViewBean;
import com.yihu.myt.vo.MytArraworkBean;
import com.yihu.myt.vo.MytArraworkView;
import com.yihu.myt.vo.MytDoctorViewBean;
import com.yihu.myt.vo.MytPauseworkBean;
import com.yihu.myt.vo.MytPauseworkView;
import com.yihu.myt.vo.Page;

/**
 * 名医通排班及停诊
 * 
 * @author wangfeng
 * @company yihu.com 2012-8-3上午08:43:46
 */
public class ArrapauseAction {

	private static IArraworkService arraworkService = Ioc
			.get(IArraworkService.class);

	private static IPauseService pauseService = Ioc.get(IPauseService.class);
 	/**
	 * 咨询树医生排班信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public HttpResponseContext doctorsearch(HttpRequestContext request) {

		try {
			OperatorInfo operator = request.getOperator();
			if (operator == null) {
				return new HttpResponseContext("您还未登录或登录已超时。");
			}
			String json = request.getParameter("json");
			String day = "";
			String operconfid = "";
			String cardid = "";
			String[] retmsg = { Constant.EMPTY_SYMBOL,
					Constant.EMPTY_SYMBOL, Constant.EMPTY_SYMBOL,
					Constant.EMPTY_SYMBOL, Constant.EMPTY_SYMBOL };
			if (json == null) {
				day = request.getParameter("day");
				operconfid = request.getParameter("operconfid");
				cardid = request.getParameter("cardid");
			} else {
				JSONObject jsonobj = new JSONObject(json);
				day = jsonobj.get("day") != null ? jsonobj.get("day")
						.toString() : null;
				operconfid = jsonobj.get("operconfid") != null ? jsonobj.get(
						"operconfid").toString() : null;
				cardid = jsonobj.get("cardid") != null ? jsonobj.get("cardid")
						.toString() : null;
			}
			int arrCount = 0;

			// 将查询结果放入jsonArray
			JSONArray jsonArray = new JSONArray();
			MytDoctorViewBean rltMdvBean = null;
			if (StringUtils.isNotEmpty(operconfid)) {
				ServiceResult<MytDoctorViewBean> sr = arraworkService
						.getMytDoctorView(Integer.valueOf(operconfid));
				rltMdvBean = sr.getCode() == 1 ? sr.getResult() : null;
			}
			if (rltMdvBean != null) {
				BossAccountBean bossaccount = new BossAccountBean(
						request.getAccount());
				if (StringUtils.isNotEmpty(cardid)) {
					com.yihu.account.api.AccMembershipcardBean rltAmcBean = Rpc
							.get(IAccountService.class,
									ConfigUtil.getInstance().getUrl(
											"url.account"), 8000)
							.getMembershipcardObject(cardid);
					bossaccount.setAccountSN(rltAmcBean.getAccountsn());
					bossaccount.setCardID(rltAmcBean.getCardnumber());
					bossaccount.setCardState(Integer.parseInt(rltAmcBean
							.getState().trim()));
					bossaccount.setCardtypesn(rltAmcBean.getCardtypesn());
				}
				//System.out.println(DateUtil.dateFormat(new Date()));
				List<MytArraworkBean> lstMaBean = BusinessManager.getArraWorks(
						rltMdvBean.getBalancetype(), operator.getOperatorID(),
						day, rltMdvBean.getOperconfid().toString(), rltMdvBean
								.getRemark(), rltMdvBean.getDoctorlevel()
								.toString(), rltMdvBean.getHospofficeid(),
						bossaccount, rltMdvBean.getOrgid());
				retmsg = BusinessManager.checkCards(
						rltMdvBean.getBalancetype(), rltMdvBean
								.getDoctorlevel().toString(), rltMdvBean
								.getHospofficeid(), bossaccount, rltMdvBean
								.getOrgid(),rltMdvBean.getOperconfid());
				
				//System.out.println(DateUtil.dateFormat(new Date()));
				if (lstMaBean != null && !lstMaBean.isEmpty()) {
					arrCount = lstMaBean.size();
					for (MytArraworkBean one : lstMaBean) {
						JSONObject ob = new JSONObject();
						ob.put("code", retmsg[0]);
						ob.put("message", retmsg[1]);
						ob.put("cardType", retmsg[2]);
						ob.put("accountSN", retmsg[3]);
						ob.put("cardId", retmsg[4]);
						ob.put("arraworkid", one.getArraworkid());
						ob.put("feeid", one.getFeeid());
						ServiceResult<List<MytArraphoneViewBean>> srMav = arraworkService
								.getMytArraphoneView(one.getArraworkid()
										.toString());
						String consphone = Constant.EMPTY_SYMBOL;
						if (srMav.getCode() > 0) {
							for (MytArraphoneViewBean mav : srMav.getResult()) {
								if (consphone.equals(Constant.EMPTY_SYMBOL)) {
									consphone = mav.getConsphone();
								} else {
									consphone += Constant.COMMA_SYMBOL
											+ mav.getConsphone();
								}
							}
						}
						ob.put("consphone", consphone);
						ob.put("sevendoctorid", one.getSevendoctorid());// 实质存储加密的电话号码
						ob.put("weekid", one.getWeekid());
						ob.put("starttime", one.getStarttime());
						ob.put("operatorname", one.getOperatorname());
						ob.put("operatorid", one.getOperatorid());
						ob.put("operconfid", one.getOperconfid());
						ob.put("hospofficeid", rltMdvBean.getHospofficeid());
						ob.put("orgid", rltMdvBean.getOrgid());
						ob.put("doctorleve", rltMdvBean.getDoctorlevel()
								.toString());
						ob.put("deptname", rltMdvBean.getHospofficename());
						ob.put("doctorname", rltMdvBean.getDoctorname());
						jsonArray.put(ob);
					}
				}
			}
		
			return new HttpResponseContext(new JSONObject().put("result",
					jsonArray).put("totalProperty", arrCount).toString());
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(-14444, e.getMessage());
		}
	}

	/**
	 * 排班集合
	 * 
	 * @param request
	 * @return
	 */
	public HttpResponseContext Search(HttpRequestContext request) {

		try {
			MytArraworkView arrView = new MytArraworkView();
			try {
				BeanUtils.populate(arrView, request.getParamMap());
			} catch (Exception e) {
				e.printStackTrace();
			}
			arrView.setState(MytConst.EFFECTIVE.getValue());
			if ("-1".equals(arrView.getHospofficeid())) {
				arrView.setDoctorlevel(7);
				arrView.setHospofficeid(null);
			}
			// 设置分页及查询记录数
			Page<MytArraworkView> page = new Page<MytArraworkView>(
					request.getInt("start"), request.getInt("limit"));
			page.setOrderProp(Order.DESC("ARRAWORKID"));
			page.setTotalItems(arraworkService.getArraworkViewCount(arrView)
					.getResult());
			JSONObject pageJson = new JSONObject();
			pageJson.put("pageIndex", page.getPageNo());
			pageJson.put("pageSize", page.getPageSize());
			pageJson.put("totalItems", page.getTotalItems());
			pageJson.put("totalPages", page.getTotalPages());
			pageJson.put("nextPage", page.getNextPage());
			//System.out.println(pageJson.toString());

			// 获取记录集及显示
			ServiceResult<List<MytArraworkView>> srLstMa = arraworkService
					.getArraworkViewList(arrView, page);
			JSONArray jsonArray = new JSONArray();
			if (srLstMa.getCode() > 0) {
				for (MytArraworkView o : srLstMa.getResult()) {
					JSONObject ob = new JSONObject();
					String starttime = o.getStarttime() + Constant.CROSS_SYMBOL
							+ o.getEndtime();
					String opertime = (o.getOpertime() != null) ? DateUtil
							.dateFormat(o.getOpertime(), DateUtil.YMDHMS_FORMAT)
							: Constant.CROSS_SYMBOL;
					ob.put("arraworkid", o.getArraworkid());
					ob.put("operconfig", o.getOperconfid());
					ob.put("orgname", o.getOrgname());
					ob.put("doctorname", o.getDoctorname());
					ob.put("hospofficeid", o.getHospofficeid());
					ob.put("hospofficename", o.getHospofficename());
					ServiceResult<List<MytArraphoneViewBean>> res = arraworkService
							.getMytArraphoneView(o.getArraworkid().toString());
					String consphone = Constant.EMPTY_SYMBOL;
					if (res.getCode() > 0) {
						for (MytArraphoneViewBean mav : res.getResult()) {
							if (Constant.EMPTY_SYMBOL.equals(consphone)) {
								consphone = this.getMaskPhone(request
										.getOperator().getOperatorID(), mav
										.getConsphone());
							} else {
								consphone += Constant.COMMA_SYMBOL
										+ this.getMaskPhone(request
												.getOperator().getOperatorID(),
												mav.getConsphone());
							}
						}
					}
					ob.put("consphone", consphone);
					ob.put("weekid", o.getWeekid());
					ob.put("starttime", starttime);
					ob.put("operatorname", o.getOperatorname());
					ob.put("opertime", opertime);
					ob.put("remark", o.getRemark());
					jsonArray.put(ob);
				}
			}
			// 返回结果
			JSONObject dataJson = new JSONObject();
			dataJson.put("page", pageJson);
			dataJson.put("list", jsonArray);
			return new HttpResponseContext(dataJson);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(-14444, e.getMessage());
		}
	}

	private String getMaskPhone(int operatorid, String phone) {

		if (operatorid == 4112 || operatorid == 1515 || operatorid == 1511
				|| operatorid == 1514 || operatorid == 3307
				|| operatorid == 1265 || operatorid == 1274
				|| operatorid == 2368 || operatorid == 2425
				|| operatorid == 2561 || operatorid == 3013
				|| operatorid == 3205 || operatorid == 4204
				|| operatorid == 4583 || operatorid == 4945) {
			return phone;
		} else {
			phone = phone.substring(0, 3) + "********";
			return phone;
		}
	}

	/**
	 * 添加名医通排班
	 * 
	 * @param request
	 * @return
	 */
	public HttpResponseContext arraAdd(HttpRequestContext request) {

		try {
			OperatorInfo operator = request.getOperator();
			if (operator == null) {
				return new HttpResponseContext("您还未登录或登录已超时。");
			}

			// 添加页面输入参数到对象
			// MytArraworkBean parMaBean = new MytArraworkBean();
			// parMaBean.setOrgid(String.valueOf(request.getInt("org")));
			// parMaBean.setHospofficeid(String.valueOf(request.getInt("office")));
			// parMaBean.setOperconfid(request.getParameter("operconfid"));
			// parMaBean.setConsphone(request.getParameter("consphone"));
			// parMaBean.setStarttime(request.getParameter("starttimes"));
			// parMaBean.setEndtime(request.getParameter("endtime"));
			// parMaBean.setRemark(request.getParameter("remark"));
			// parMaBean.setWeekid(request.getParameter("week"));

			MytArraworkBean arrawork = new MytArraworkBean();
			BeanUtils.populate(arrawork, request.getParamMap());
			arrawork.setOperatorid(operator.getOperatorID() + "");
			arrawork.setOperatorname(operator.getOperatorName());
			arrawork.setOpertime(DateOper.getNowDateTime());
			arrawork.setState(1);
			//System.out.println(arrawork.toString());

			// 调用添加医生排班服务接口
			ReturnValue rv = arraworkService.addMytArrawork(arrawork);

			return new HttpResponseContext(rv.getCode(), rv.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(-14444, e.getMessage());
		}
	}

	/**
	 * 修改名医通排班
	 * 
	 * @param request
	 * @return
	 */
	public HttpResponseContext arraEdit(HttpRequestContext request) {

		try {
			OperatorInfo operator = request.getOperator();
			if (operator == null) {
				return new HttpResponseContext("您还未登录或登录已超时。");
			}
			Integer arraworkid = request.getInt("arraworkid");
			if (arraworkid == null || arraworkid == 0) {
				return new HttpResponseContext("医生排班编号不存在。");
			}
			MytArraworkBean arrawork = new MytArraworkBean();
			BeanUtils.populate(arrawork, request.getParamMap());
			// System.out.println(arrawork.toString());
			arrawork.setOperatorid(operator.getOperatorID() + "");
			arrawork.setOperatorname(operator.getOperatorName());
			arrawork.setOpertime(DateOper.getNowDateTime());

			// 调用添加医生排班服务接口
			ReturnValue rv = arraworkService.updateMytArrawork(arrawork);

			return new HttpResponseContext(rv.getCode(), rv.getMessage());
		} catch (Exception e) {
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(-14444, e.getMessage());
		}
	}

	/**
	 * 加载名医通排班
	 * 
	 * @param request
	 * @return
	 */
	public HttpResponseContext arraLoad(HttpRequestContext request) {

		try {
			OperatorInfo operator = request.getOperator();
			if (operator == null) {
				return new HttpResponseContext("您还未登录或登录已超时。");
			}

			int arraworkId = request.getInt("arraworkId");
			int operconfId = request.getInt("operconfId");
			if (arraworkId < 1) {
				return new HttpResponseContext("医生排班编号不存在。");
			}
			ServiceResult<JSONObject> sr = arraworkService.getMytArrawork(
					arraworkId, operconfId);
			if (sr.getCode() == -1) {
				return new HttpResponseContext(sr.getMessage());
			}
			return new HttpResponseContext(sr.getResult());
		} catch (Exception e) {
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(-14444, e.getMessage());
		}
	}

	/**
	 * 名医通停诊查询
	 * 
	 * @param request
	 * @return
	 */
	public HttpResponseContext pauseSearch(HttpRequestContext request) {

		try {
			OperatorInfo operator = request.getOperator();
			if (operator == null) {
				return new HttpResponseContext("您还未登录或登录已超时。");
			}

			// 设置查询参数
			MytPauseworkView pauseworkView = new MytPauseworkView();

			BeanUtils.populate(pauseworkView, request.getParamMap());

			// System.out.println(pauseworkView.toString());

			String startOpertime = request.getParameter("startopertime");
			String endOpertime = request.getParameter("endopertime");
			// 设置分页及查询记录数
			Page<MytPauseworkBean> page = new Page<MytPauseworkBean>(
					request.getInt("pageIndex"), request.getInt("pageSize"));
			page.setOrderProp(Order.DESC("opertime"));
			page.setTotalItems(pauseService.getCount(pauseworkView,
					startOpertime, endOpertime).getResult());
			JSONObject pageJson = new JSONObject();
			pageJson.put("pageIndex", page.getPageNo());
			pageJson.put("pageSize", page.getPageSize());
			pageJson.put("totalItems", page.getTotalItems());
			pageJson.put("totalPages", page.getTotalPages());
			pageJson.put("nextPage", page.getNextPage());

			JSONObject resObj = pauseService.getResults(pauseworkView,
					startOpertime, endOpertime, page);
			// 返回结果
			JSONObject dataJson = new JSONObject();
			dataJson.put("page", pageJson);
			dataJson.put("list", resObj.getJSONArray("result"));
			return new HttpResponseContext(dataJson);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(-14444, e.getMessage());
		}
	}

	/**
	 * 添加名医通停诊
	 * 
	 * @param request
	 * @return
	 */
	public HttpResponseContext pauseAdd(HttpRequestContext request) {

		try {
			OperatorInfo operator = request.getOperator();
			if (operator == null) {
				return new HttpResponseContext("您还未登录或登录已超时。");
			}
			// 获取页面元素值
			MytPauseworkBean pausework = new MytPauseworkBean();

			BeanUtils.populate(pausework, request.getParamMap());
			pausework.setOperatorid(operator.getOperatorID() + "");
			pausework.setOperatorname(operator.getOperatorName());
			pausework.setOpertime(DateOper.getNowDateTime());
			pausework.setState(1);
			pausework.setStartdate(new Timestamp(DateOper.parse(
					request.getParameter("startdatestr")).getTime()));
			pausework.setEnddate(new Timestamp(DateOper.parse(
					request.getParameter("enddatestr")).getTime()));

			// System.out.println(pausework.toString());

			if (DateOper.formatDate(pausework.getStartdate(), "yyyy-MM-dd")
					.compareTo(DateOper.getNow("yyyy-MM-dd")) < 0)
				return new HttpResponseContext(-1, "开始日期不能早于今天");
			if (pausework.getStartdate().getTime() > pausework.getEnddate()
					.getTime())
				return new HttpResponseContext(-1, "开始日期不能大于结束日期");
			if (pausework.getStartdate().getTime() == pausework.getEnddate()
					.getTime()
					&& pausework.getStarttime().compareTo(
							pausework.getEndtime()) > 0)
				return new HttpResponseContext(-1, "同一天中开始时间不能大于结束时间");

			ReturnValue rv = pauseService.add(pausework);

			return new HttpResponseContext(rv.getCode(), rv.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(-14444, e.getMessage());
		}
	}

	/**
	 * 修改名医通停诊
	 * 
	 * @param request
	 * @return
	 */
	public HttpResponseContext pauseEdit(HttpRequestContext request) {

		try {
			OperatorInfo operator = request.getOperator();
			if (operator == null) {
				return new HttpResponseContext("您还未登录或登录已超时。");
			}

			// 获取页面元素值
			MytPauseworkBean pausework = new MytPauseworkBean();
			BeanUtils.populate(pausework, request.getParamMap());
			pausework.setOperatorid(operator.getOperatorID() + "");
			pausework.setOperatorname(operator.getOperatorName());
			pausework.setOpertime(DateOper.getNowDateTime());
			pausework.setStartdate(new Timestamp(DateOper.parse(
					request.getParameter("startdatestr")).getTime()));
			pausework.setEnddate(new Timestamp(DateOper.parse(
					request.getParameter("enddatestr")).getTime()));

			// System.out.println(pausework.toString());

			if (DateOper.formatDate(pausework.getStartdate(), "yyyy-MM-dd")
					.compareTo(DateOper.getNow("yyyy-MM-dd")) < 0)
				return new HttpResponseContext(-1, "开始日期不能早于今天");
			if (pausework.getStartdate().getTime() > pausework.getEnddate()
					.getTime())
				return new HttpResponseContext(-1, "开始日期不能大于结束日期");
			if (pausework.getStartdate().getTime() == pausework.getEnddate()
					.getTime()
					&& pausework.getStarttime().compareTo(
							pausework.getEndtime()) > 0)
				return new HttpResponseContext(-1, "同一天中开始时间不能大于结束时间");

			// 修改后并返回
			ReturnValue rv = pauseService.update(pausework);
			return new HttpResponseContext(rv.getCode(), rv.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(-14444, e.getMessage());
		}
	}

	/**
	 * 加载名医通停诊
	 * 
	 * @param request
	 * @return
	 */
	public HttpResponseContext pauseLoad(HttpRequestContext request) {

		try {
			OperatorInfo operator = request.getOperator();
			if (operator == null) {
				return new HttpResponseContext("您还未登录或登录已超时。");
			}

			// 通过停诊ID获取停诊信息
			String pauseId = request.getParameter("pauseId");
			if (StringUtils.isEmpty(pauseId)) {
				return new HttpResponseContext("医生停诊编号不存在。");
			}
			MytPauseworkBean parMpBean = new MytPauseworkBean();
			parMpBean.setPauseid(Integer.parseInt(pauseId));
			ServiceResult<JSONObject> sr = pauseService.getEntity(parMpBean);
			if (sr.getCode() < 0) {
				return new HttpResponseContext("医生停诊信息不存在。");
			}

			return new HttpResponseContext(sr.getResult());
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(-14444, e.getMessage());
		}
	}

	/**
	 * 删除名医通停诊
	 * 
	 * @param request
	 * @return
	 */
	public HttpResponseContext pauseDelete(HttpRequestContext request) {

		try {
			OperatorInfo operator = request.getOperator();
			if (operator == null) {
				return new HttpResponseContext("您还未登录或登录已超时。");
			}

			// 通过停诊ID获取停诊信息
			String pauseId = request.getParameter("pauseId");
			if (StringUtils.isEmpty(pauseId)) {
				return new HttpResponseContext("医生停诊编号不存在。");
			}

			// 修改后并返回
			ReturnValue rv = pauseService.delete(Integer.parseInt(pauseId),
					operator);
			return new HttpResponseContext(rv.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(-14444, e.getMessage());
		}
	}

}
