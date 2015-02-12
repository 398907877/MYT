package com.yihu.myt.http;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.boss.sdk.HttpRequestContext;
import com.boss.sdk.HttpResponseContext;
import com.common.json.JSONArray;
import com.common.json.JSONObject;
import com.coreframework.ioc.Ioc;
import com.coreframework.log.LogBody;
import com.coreframework.log.Logger;
import com.coreframework.remoting.reflect.Rpc;
import com.coreframework.vo.ReturnValue;
import com.coreframework.vo.ServiceResult;
import com.yihu.baseinfo.api.CommonApi;
import com.yihu.baseinfo.api.DoctorInfoApi;
import com.yihu.baseinfo.vo.SmallDepartmentVo;
import com.yihu.basis.api.IBasisService;
import com.yihu.myt.ConfigUtil;
import com.yihu.myt.IBaseInfoService;
import com.yihu.myt.IOperconfidService;
import com.yihu.myt.mgr.ApiUtil;
import com.yihu.wsgw.api.InterfaceMessage;

public class MYTJSAction {

	private static IOperconfidService operconfService = Ioc.get(IOperconfidService.class);	// 医生配置信息服务接口
	private static IBaseInfoService baseInfoService = Ioc.get(IBaseInfoService.class);	
	
	/**
	 * 查询标准子科室
	 * @param request
	 * @return
	 */
	public HttpResponseContext getSmallStdDept(HttpRequestContext request) {
		try {
			return new HttpResponseContext(baseInfoService.getSmallStdDept(request.getInt("deptid")));
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(e);
		}
	}

	/**
	 * 根据机构ID获取有医生的科室
	 * @param request
	 * @return
	 */
	public HttpResponseContext getDeptByOrg(HttpRequestContext request) {
		try {
			return new HttpResponseContext(baseInfoService.getDeptByOrg(request.getInt("orgid")));
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(e);
		}
	}
	
	/**
	 * 获取机构下指定科室的医生
	 * @param request
	 * @return
	 */
	public HttpResponseContext getDoctorByOrgAndDept(HttpRequestContext request) {
		try {
			return new HttpResponseContext(baseInfoService.getDoctorByOrgAndDept(request.getInt("orgid"),request.getParameter("deptid")));
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(e);
		}
	}
	
	/**
	 * 查询医生信息
	 * @param request
	 * @return
	 */
	public HttpResponseContext getDoctorByOfficeInOrg(HttpRequestContext request) {
		try {
			// 远程调用Basis接口
			IBasisService ibs = Rpc.get(IBasisService.class,ConfigUtil.getInstance().getUrl("url.basis"), 8000);
			ReturnValue rv = ibs.getDoctorByOfficeInOrg(request.getParameter("deptId"), request.getParameter("orgId"));
			// 返回结果
			return new HttpResponseContext(rv.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.basis", LogBody.me().set(e));
			return new HttpResponseContext(e);
		}
	}
	
	/**
	 * 查询医生联系电话
	 * @param request
	 * @return
	 */
	public HttpResponseContext getConsphoneByDoctor(HttpRequestContext request) {
		try {
			return new HttpResponseContext(operconfService.getConsphoneByDoctor(request.getInt("operconfId")));
		} catch (Exception e) {
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(e);
		}
	}
	
	/**
	 * 根据省份获取所有有MYT的二级科室
	 * @param request
	 * @return
	 */
	public HttpResponseContext getMiniDeptByOrg(HttpRequestContext request) {
		try {
			JSONArray jsonArray = new JSONArray();
			com.yihu.baseinfo.api.CommonApi  api=Rpc.get(CommonApi.class
					   , ConfigUtil.getInstance().getUrl("url.baseinfo"));
			ServiceResult<List<SmallDepartmentVo>>  sr=api.getSmallDepartmentListByProvId(request.getInt("orgid"));//省份ID
			// 获取数据默认数据
			JSONObject mode = new JSONObject();
			mode.put("deptid", "0201");
			mode.put("deptname", "全科咨询");
			jsonArray.put(mode);
			//
			/*ServiceResult<List<Map<String, String>>> sr = operconfService
					.getStdOffice(orgid);*/
			if (sr.getCode() > 0&&sr.getResult()!= null ) {
				List<SmallDepartmentVo> list=sr.getResult();
				/*Collections.sort(list, new Comparator<SmallDepartmentVo>() {
					public int compare(SmallDepartmentVo arg0, SmallDepartmentVo arg1) {
						return arg0.getSequence().compareTo(arg1.getSequence());
					}
				});*/
				// 装载数据
				for (SmallDepartmentVo  o : list) {
					JSONObject jsonObj = new JSONObject();
					jsonObj.put("deptid", o.getDeptId());
					jsonObj.put("deptname", o.getName());
					//jsonObj.put("state", "closed");
					/*if (list == null) {
						jsonObj.put("leaf", true);
						jsonObj.put("attributes", true);

					} else {
						jsonObj.put("leaf", false);
						jsonObj.put("attributes", false);
					}*/
					jsonArray.put(jsonObj);
				}
				//lstTree.addAll(sr.getResult());
			}
			JSONObject rt = new JSONObject(ApiUtil.getJsonRt(1, "数据获取成功"));
			rt.put("Result", jsonArray);
			return new HttpResponseContext(rt);
		} catch (Exception e) {
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(e);
		}
	}
	/**
	 * 根据标准二级科室ID和省份取医生列表
	 * @param request
	 * @return
	 */
	public HttpResponseContext getDocListByOrgAndDept(HttpRequestContext request) {
		try {
			com.yihu.baseinfo.api.DoctorInfoApi
			doctorInfoApi=Rpc.get(DoctorInfoApi.class
					   , ConfigUtil.getInstance().getUrl("url.baseinfo"));
			
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("provinceId", request.getInt("orgid"));
			jsonObj.put("standardDeptId", request.getParameter("deptid"));
			jsonObj.put("serviceStatusSearch", 2);
			jsonObj.put("startRow", 0);
			jsonObj.put("pageSize", 500);
			jsonObj.put("columns", "doctorUid,doctorName");
			InterfaceMessage interfacemessage = new InterfaceMessage();
			interfacemessage.setParam(jsonObj.toString());
			String jsonArray = doctorInfoApi.queryComplexDoctorList(interfacemessage);
			/*JSONObject rt = new JSONObject(ApiUtil.getJsonRt(1, "数据获取成功"));
			rt.put("Result", jsonArray);*/
			return new HttpResponseContext(jsonArray);
		} catch (Exception e) {
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(e);
		}
	}
}
