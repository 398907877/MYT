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
 * ��ҽͨ�����Ƽ�ҽ��
 * 
 * @author wangfeng
 * @company yihu.com 2012-9-13����9:57:00
 */
public class MIDoctorAction {

	private static IMIDoctorService miDoctorService = Ioc.get(IMIDoctorService.class);

	/**
	 * ����Ƽ�ҽ��
	 * 
	 * @param request
	 * @return
	 */
	public HttpResponseContext Add(HttpRequestContext request) {

		try {
			// ��ȡ��������Ϣ
			OperatorInfo operator = request.getOperator();
			if (operator == null) {
				return new HttpResponseContext(new JSONObject().put("message", "����δ��¼���¼�ѳ�ʱ��"));
			}
			// ��ȡ����Ƽ�ҽ����Ϣ
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

			// ���沢������Ϣ
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
			// ��ȡ��������Ϣ
			OperatorInfo operator = request.getOperator();
			if (operator == null) {
				return new HttpResponseContext(new JSONObject().put("message", "����δ��¼���¼�ѳ�ʱ��"));
			}
			String mainintrodoctorid = request.getParameter("mainintrodoctorid");
			// ��ȡ����Ƽ�ҽ����Ϣ
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

			// ���沢������Ϣ
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
				return new HttpResponseContext(new JSONObject().put("message", "�Ƽ�ҽ�����Ϊ�ջ���ȷ��"));
			}
			ServiceResult<MytMainintrodoctorBean> sr = miDoctorService.queryEntity(mainintrodoctorid);
			if (sr.getCode() < 0) {
				return new HttpResponseContext(new JSONObject().put("message", "�Ƽ�ҽ����Ϣ�����ڡ�"));
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
	 * ��ѯ�Ƽ�ҽ��
	 * 
	 * @param request
	 * @return
	 */
	public HttpResponseContext Search(HttpRequestContext request) {

		try {
			// ��ȡ��������Ϣ
			OperatorInfo operator = request.getOperator();
			if (operator == null) {
				return new HttpResponseContext("����δ��¼���¼�ѳ�ʱ��");
			}

			String starttime = request.getParameter("starttimes"); // ��ȡ��ʼʱ��
			String endtime = request.getParameter("endtimes"); // ��ȡ����ʱ��
			// ���ò�ѯ����
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

			// ���÷�ҳ����ѯ��¼��
			Page<MytMainintrodoctorBean> page = new Page<MytMainintrodoctorBean>(request.getInt("start"),
				request.getInt("limit"));
			page.setTotalItems(miDoctorService.queryCount(qryMmBean).getResult());
			JSONObject pageJson = new JSONObject();
			pageJson.put("pageIndex", page.getPageNo());
			pageJson.put("pageSize", page.getPageSize());
			pageJson.put("totalItems", page.getTotalItems());
			pageJson.put("totalPages", page.getTotalPages());
			pageJson.put("nextPage", page.getNextPage());

			// ��ȡ��¼������ʾ
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

			// ���ؼ�¼
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
