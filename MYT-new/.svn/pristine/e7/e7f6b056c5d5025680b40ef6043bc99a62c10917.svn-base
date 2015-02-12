package com.yihu.myt.http;

import java.sql.Timestamp;
import java.util.List;

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
import com.coreframework.remoting.standard.DateOper;
import com.coreframework.vo.ReturnValue;
import com.coreframework.vo.ServiceResult;
import com.yihu.myt.IMIDoctorService;
import com.yihu.myt.enums.Constant;
import com.yihu.myt.enums.MytConst;
import com.yihu.myt.util.DateUtil;
import com.yihu.myt.vo.MytMainintrodoctorBean;
import com.yihu.myt.vo.Page;

/**
 * 名医通——推荐医生
 * 
 * @author wangfeng
 * @company yihu.com 2012-9-13上午9:57:00
 */
public class MIDoctorAction {

	private static IMIDoctorService miDoctorService = Ioc.get(IMIDoctorService.class);

	/**
	 * 添加推荐医生
	 * 
	 * @param request
	 * @return
	 */
	public HttpResponseContext Add(HttpRequestContext request) {

		try {
			// 获取操作者信息
			OperatorInfo operator = request.getOperator();
			if (operator == null) {
				return new HttpResponseContext(new JSONObject().put("message", "您还未登录或登录已超时。"));
			}
			// 获取添加推荐医生信息
			MytMainintrodoctorBean proMmBean = new MytMainintrodoctorBean();
			proMmBean.setOperatorid(operator.getOperatorID() + Constant.EMPTY_SYMBOL);
			proMmBean.setOperatorname(operator.getOperatorName());
			proMmBean.setOperconfid(Integer.valueOf( request.getParameter("operconfid")));
			proMmBean.setStartdate(DateUtil.parse(DateUtil.stringFormat(request.getParameter("startdate"),
				DateUtil.YMD_FORMAT).getTime()));
			proMmBean.setEnddate(DateUtil.parse(DateUtil.stringFormat(request.getParameter("enddate"),
				DateUtil.YMD_FORMAT).getTime()));
			proMmBean.setStarttime(request.getParameter("starttime"));
			proMmBean.setEndtime(request.getParameter("endtime"));
			proMmBean.setRemark(request.getParameter("remark"));
			proMmBean.setState(MytConst.EFFECTIVE.getValue() );
			proMmBean.setOpertime(DateOper.getNowDateTime());

			// 保存并返回消息
			ReturnValue rv = miDoctorService.insert(proMmBean);
			return new HttpResponseContext(new JSONObject().put("message", rv.getMessage()));
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(e);
		}
	}

	public HttpResponseContext Update(HttpRequestContext request) {

		try {
			// 获取操作者信息
			OperatorInfo operator = request.getOperator();
			if (operator == null) {
				return new HttpResponseContext(new JSONObject().put("message", "您还未登录或登录已超时。"));
			}
			String mainintrodoctorid = request.getParameter("mainintrodoctorid");
			// 获取添加推荐医生信息
			MytMainintrodoctorBean proMmBean = new MytMainintrodoctorBean();
			proMmBean.setMainintrodoctorid(Integer.parseInt(mainintrodoctorid));
			proMmBean.setOperatorid(operator.getOperatorID() + Constant.EMPTY_SYMBOL);
			proMmBean.setOperatorname(operator.getOperatorName());
			proMmBean.setOperconfid(Integer.valueOf(request.getParameter("operconfid")));
			proMmBean.setStartdate(DateUtil.parse(DateUtil.stringFormat(request.getParameter("startdate"),
				DateUtil.YMD_FORMAT).getTime()));
			proMmBean.setEnddate(DateUtil.parse(DateUtil.stringFormat(request.getParameter("enddate"),
				DateUtil.YMD_FORMAT).getTime()));
			proMmBean.setStarttime(request.getParameter("starttime"));
			proMmBean.setEndtime(request.getParameter("endtime"));
			proMmBean.setRemark(request.getParameter("remark"));
			proMmBean.setState(Integer.valueOf(request.getParameter("state")));

			// 保存并返回消息
			ReturnValue rv = miDoctorService.update(proMmBean);
			return new HttpResponseContext(new JSONObject().put("message", rv.getMessage()));
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(e);
		}
	}

	public HttpResponseContext MySearch(HttpRequestContext request) {

		try {
			String mainintrodoctorid = request.getParameter("mainintrodoctorid");
			if (StringUtils.isEmpty(mainintrodoctorid)) {
				return new HttpResponseContext(new JSONObject().put("message", "推荐医生编号为空或不正确。"));
			}
			ServiceResult<MytMainintrodoctorBean> sr = miDoctorService.queryEntity(mainintrodoctorid);
			if (sr.getCode() < 0) {
				return new HttpResponseContext(new JSONObject().put("message", "推荐医生信息不存在。"));
			}
			MytMainintrodoctorBean o = sr.getResult();
			JSONObject jsonO = new JSONObject();
			jsonO.put("mainintrodoctorid", o.getMainintrodoctorid());
			jsonO.put("state", o.getState());
			jsonO.put("startdate", DateUtil.dateFormat(o.getStartdate(), DateUtil.YMD_FORMAT));
			jsonO.put("enddate", DateUtil.dateFormat(o.getEnddate(), DateUtil.YMD_FORMAT));
			jsonO.put("starttime", o.getStarttime());
			jsonO.put("endtime", o.getEndtime());
			jsonO.put("remark", o.getRemark());
			JSONObject jsonRlt = new JSONObject().put("result", new JSONArray().put(jsonO));
			return new HttpResponseContext(jsonRlt);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(e);
		}
	}

	/**
	 * 查询推荐医生
	 * 
	 * @param request
	 * @return
	 */
	public HttpResponseContext Search(HttpRequestContext request) {

		try {
			// 获取操作者信息
			OperatorInfo operator = request.getOperator();
			if (operator == null) {
				return new HttpResponseContext("您还未登录或登录已超时。");
			}

			String starttime = request.getParameter("starttimes"); // 获取开始时间
			String endtime = request.getParameter("endtimes"); // 获取结束时间
			// 设置查询条件
			MytMainintrodoctorBean qryMmBean = new MytMainintrodoctorBean();
			String orgid = request.getParameter("orgid");
			if (StringUtils.isNotEmpty(orgid)) {
				qryMmBean.setQryOrgid(orgid);
			}
			String doctorname = request.getParameter("doctorname");
			if (StringUtils.isNotEmpty(doctorname)) {
				qryMmBean.setQryDoctorname(doctorname);
			}
			String hospofficeid = request.getParameter("hospofficeid");
			if (StringUtils.isNotEmpty(hospofficeid)) {
				qryMmBean.setQryHospofficeid(hospofficeid);
			}
			if (StringUtils.isNotEmpty(starttime))
				qryMmBean.setQryMinOpertime(DateUtil.parse(DateUtil.stringFormat(starttime, DateUtil.YMD_FORMAT)
					.getTime()));
			if (StringUtils.isNotEmpty(endtime))
				qryMmBean.setQryMaxOpertime(DateUtil.parse(DateUtil.stringFormat(endtime, DateUtil.YMD_FORMAT)
					.getTime()));

			// 设置分页及查询记录数
			Page<MytMainintrodoctorBean> page = new Page<MytMainintrodoctorBean>(request.getInt("start"),
				request.getInt("limit"));
			page.setTotalItems(miDoctorService.queryCount(qryMmBean).getResult());
			JSONObject pageJson = new JSONObject();
			pageJson.put("pageIndex", page.getPageNo());
			pageJson.put("pageSize", page.getPageSize());
			pageJson.put("totalItems", page.getTotalItems());
			pageJson.put("totalPages", page.getTotalPages());
			pageJson.put("nextPage", page.getNextPage());

			// 获取记录集及显示
			ServiceResult<JSONObject> sr = miDoctorService.queryResult(qryMmBean, page);
//			JSONArray jsonArray = new JSONArray();
//			if (sr.getCode() > 0) {
//				for (Object o : sr.getResult()) {
//					JSONObject json = new JSONObject();
//					Object[] obj = (Object[]) o;
//					json.put("mainintrodoctorid", obj[0]);
//					json.put("operconfid", obj[1]);
//					json.put("orgname", obj[2]);
//					json.put("doctorname", obj[3]);
//					json.put("hospofficename", obj[4]);
//					json.put("stardate", DateUtil.dateFormat((Timestamp) obj[10], DateUtil.YMD_FORMAT) + "--"
//						+ DateUtil.dateFormat((Timestamp) obj[10], DateUtil.YMD_FORMAT));
//					json.put("startime", obj[7] + " -- " + obj[8]);
//					json.put("operatorname", obj[9]);
//					json.put("opertime", DateUtil.dateFormat((Timestamp) obj[10], DateUtil.YMDHMS_FORMAT));
//					json.put("enddate", DateUtil.dateFormat((Timestamp) obj[10], DateUtil.YMD_FORMAT));
//
//					jsonArray.put(json);
//				}
//			}

			// 返回记录
			JSONObject dataJson = new JSONObject();
			dataJson.put("totalProperty", pageJson.get("totalItems"));
			dataJson.put("result", sr.getCode() == 1 ? sr.getResult().get("result") : new JSONArray());
			return new HttpResponseContext(dataJson);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(e);
		}
	}
}
