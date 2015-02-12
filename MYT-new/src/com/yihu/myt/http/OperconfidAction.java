package com.yihu.myt.http;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.boss.sdk.HttpRequestContext;
import com.boss.sdk.HttpResponseContext;
import com.boss.sdk.OperatorInfo;
import com.common.json.JSONArray;
import com.common.json.JSONObject;
import com.coreframework.ioc.Ioc;
import com.coreframework.log.LogBody;
import com.coreframework.log.Logger;
import com.coreframework.remoting.Url;
import com.coreframework.remoting.reflect.Rpc;
import com.coreframework.remoting.standard.DateOper;
import com.coreframework.vo.ReturnValue;
import com.coreframework.vo.ServiceResult;
import com.yihu.baseinfo.api.CommonApi;
import com.yihu.baseinfo.api.DoctorInfoApi;
import com.yihu.baseinfo.vo.DoctorVo;
import com.yihu.baseinfo.vo.DoctorVoMore;
import com.yihu.baseinfo.vo.SmallDepartmentVo;
import com.yihu.myt.ConfigUtil;
import com.yihu.myt.IArraworkService;
import com.yihu.myt.IBookenrolService;
import com.yihu.myt.IOperconfidService;
import com.yihu.myt.IPauseService;
import com.yihu.myt.enums.Constant;
import com.yihu.myt.enums.MytConst;
import com.yihu.myt.mgr.ApiUtil;
import com.yihu.myt.service.DoctorOnLine;
import com.yihu.myt.service.service.IDoctorInfoService;
import com.yihu.myt.util.DateUtil;
import com.yihu.myt.util.StringUtil;
import com.yihu.myt.vo.DoctorInfoVo;
import com.yihu.myt.vo.MytArraworkBean;
import com.yihu.myt.vo.MytBookenrolBean;
import com.yihu.myt.vo.MytDoctorViewBean;
import com.yihu.myt.vo.MytMainintrodoctorBean;
import com.yihu.myt.vo.MytOperconfigBean;
import com.yihu.myt.vo.MytPauseworkBean;
import com.yihu.myt.vo.SysOperatorBean;
import com.yihu.user.api.UserInfoApi;
import com.yihu.wsgw.api.InterfaceMessage;

public class OperconfidAction {

	private static HashMap map;

	private static IOperconfidService operconfService = Ioc
			.get(IOperconfidService.class); // ҽ��������Ϣ����ӿ�
	private static IArraworkService arraworkService = Ioc
			.get(IArraworkService.class); // ҽ���Ű����ӿ�
	private static IPauseService pauseService = Ioc.get(IPauseService.class); // ҽ��ͣ�����ӿ�
	private static IBookenrolService bookenrolService = Ioc
			.get(IBookenrolService.class); // ��ѯ��ˮ����ӿ�
	private static IDoctorInfoService doctorInfoService = Ioc.get(IDoctorInfoService.class); // ͣ�����ӿ�

	/**
	 * ����ҽ�����Ʋ�ѯҽ����Ϣ
	 * 
	 * @param request
	 * @param response
	 */
	public HttpResponseContext getDoctorIndexList(HttpRequestContext request) {
		try {
			Integer  start = request.getInt("start");
			Integer limit = request.getInt("limit");
			String json = request.getParameter("json");
			String doctorname;
			if (json == null) {
				doctorname = request.getParameter("doctorname");
			} else {
				JSONObject jsonobj= new JSONObject(json);
				doctorname = jsonobj.get("doctorname") != null ? jsonobj.get(
						"doctorname").toString() : null;
			}
			String rt= operconfService
					.getDoctorIndexList(doctorname,start,limit);
			return new HttpResponseContext(rt);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(-14444, e.getMessage());
		}
	}
	
	/**
	 * ��ҽ��ѯ��
	 * 
	 * @param request
	 * @return
	 */
	public HttpResponseContext treeDoctorList(HttpRequestContext request) {

		try {
			JSONArray jsonArray = new JSONArray();
			String node = request.getParameter("node");
			Integer orgid = request.getInt("orgid");
			if (StringUtils.isEmpty(node)) {
				return new HttpResponseContext(jsonArray.toString());
			}
			//List<Map<String, String>> lstTree = null;
			if (node.equals("1") || node.equals("71") || node.equals("151")
					|| node.equals("211") || node.equals("212")
					|| node.equals("291") || node.equals("292")
					|| node.equals("381") || node.equals("9999")) {
				//ConfigUtil.getInstance().getUrl("url.baseinfo")
				//����ʡ��ID�����п�ͨ�绰��ѯ�ı�׼����ID������
				/*com.yihu.baseinfo.api.CommonApi  api=Rpc.get(CommonApi.class
						   , new Url("10.0.100.124", 8912));*/
				com.yihu.baseinfo.api.CommonApi  api=Rpc.get(CommonApi.class
						   , ConfigUtil.getInstance().getUrl("url.baseinfo"));
				ServiceResult<List<SmallDepartmentVo>>  sr=api.getSmallDepartmentListByProvId(orgid);//ʡ��ID
				/*for (SmallDepartmentVo smallDepartmentVo : list) {
					System.out.println(smallDepartmentVo.getDeptId()+","+smallDepartmentVo.getName());
				}*/
				/*lstTree = new ArrayList<Map<String, String>>();
				Map<String, String> mapDef = new HashMap<String, String>();
				mapDef.put("hospofficeid", "0201");
				mapDef.put("hospofficename", "ȫ����ѯ");

				mapDef.put("isintro", "0");
				lstTree.add(mapDef);*/
				
				// ��ȡ����Ĭ������
				JSONObject mode = new JSONObject();
				mode.put("id", "0201");
				mode.put("text", "ȫ����ѯ");
				mode.put("state", "closed");
				jsonArray.put(mode);
				//
				/*ServiceResult<List<Map<String, String>>> sr = operconfService
						.getStdOffice(orgid);*/
				if (sr.getCode() > 0&&sr.getResult()!= null ) {
					List<SmallDepartmentVo> list=sr.getResult();
					Collections.sort(list, new Comparator<SmallDepartmentVo>() {
						public int compare(SmallDepartmentVo arg0, SmallDepartmentVo arg1) {
							return arg0.getSequence().compareTo(arg1.getSequence());
						}
					});
					// װ������
					for (SmallDepartmentVo  o : list) {
						JSONObject jsonObj = new JSONObject();
						jsonObj.put("id", o.getDeptId());
						jsonObj.put("text", o.getName());
						jsonObj.put("state", "closed");
						if (list == null) {
							jsonObj.put("leaf", true);
							jsonObj.put("attributes", true);

						} else {
							jsonObj.put("leaf", false);
							jsonObj.put("attributes", false);
						}
						jsonArray.put(jsonObj);
					}
					//lstTree.addAll(sr.getResult());
				}
				return new HttpResponseContext(jsonArray.toString());
			} else {
				//���ݱ�׼����ID���ʡ�ݱ�׼�������п�ͨ�绰��ѯ��ҽ��
				/*com.yihu.baseinfo.api.DoctorInfoApi
				doctorInfoApi=Rpc.get(DoctorInfoApi.class
						   , ConfigUtil.getInstance().getUrl("url.baseinfo"));*/
			//	172.18.0.15:8928 new Url("172.18.0.15", 8928)
				/*com.yihu.user.api.UserInfoApi userInfoApi=Rpc.get(UserInfoApi.class
						   , ConfigUtil.getInstance().getUrl("url.CB_User_test"));
				com.yihu.user.api.UserInfoApi userInfoApi=Rpc.get(UserInfoApi.class
						   , new Url("172.18.0.15", 8928));
				//DoctorVoMore  obj=new DoctorVoMore();
				//obj.setStandardDeptID(node);//��׼����ID
				//obj.setServiceStatusSearch("2");//�п�ͨ�绰��ѯ���̶���2
				//obj.setProvinceId(orgid);//ʡ��ID
				//ServiceResult<List<DoctorVo>> dsr=doctorInfoApi.queryDoctorList(obj);
				JSONObject json = new JSONObject();
				json.put("provinceId", orgid);
				json.put("standardDeptId", node);
				json.put("doctorService_phone", "1");
				json.put("columns", "doctorName,doctorUid");
				json.put("pageSize", "1000");
				json.put("pageIndex", "1");
				//json.put("main", 1);
				InterfaceMessage inMsg = new InterfaceMessage();
				inMsg.setParam(json.toString());
				String str = userInfoApi.queryComplexDoctorList(inMsg);
				//System.out.println(str);
				net.sf.json.JSONObject docs = net.sf.json.JSONObject.fromObject(str) ;
				//ServiceResult<List<DoctorVo>> dsr = doctorInfoApi.queryDoctorList(json.toString());
				Integer tp = 0;
				if(docs.getInt("Code") > 0){
					net.sf.json.JSONArray array = docs.getJSONArray("Result");
					net.sf.json.JSONArray arrayTp = new net.sf.json.JSONArray();
					for(int r = 0;r<array.size();r++){
						if(!arrayTp.isEmpty()){
							for(int m= 0;m<arrayTp.size();m++){
								//System.out.println(arrayTp.getJSONObject(r).getString("doctorUid") + "--" + arrayTp.getJSONObject(m).getString("doctorUid"));
								if(arrayTp.getJSONObject(m).getString("doctorUid").equals(array.getJSONObject(r).getString("doctorUid"))){
									tp = 1;
								}
							}	
						}
						if(tp.equals(0)){
							arrayTp.add(array.getJSONObject(r));
							tp = 0;
						}
					}
					//System.out.print(arrayTp.toString());
					String nowTime = DateUtil.dateFormat(
							DateOper.getNowDateTime(), DateUtil.HM_FORMAT);
					String nowDate = DateUtil.dateFormat(
							DateOper.getNowDateTime(), DateUtil.YMD_FORMAT);
					for(int h= 0;h<arrayTp.size();h++){
						JSONObject jsonMdv = new JSONObject();
						// ��ȡ�Ű���Ϣ
						MytArraworkBean rltMaBean = arraworkService
								.getMytArrawork(arrayTp.getJSONObject(h).getInt("doctorUid"), nowTime)
								.getResult();
						int whichColor = 0;
						if (rltMaBean != null) {
							// �Ű�����
							whichColor = 1;
							// ��ȡͣ����Ϣ
							MytPauseworkBean rltMpBean = pauseService
									.getEntity(arrayTp.getJSONObject(h).getInt("doctorUid"), nowTime,
											nowDate).getResult();
							if (rltMpBean != null) {
								whichColor = 2;
							} else {
								if (DoctorOnLine.get(arrayTp.getJSONObject(h).getInt("doctorUid")
										) != null) {
									whichColor = 4;
								}
								if (arraworkService.isBusy(arrayTp.getJSONObject(h).getInt("doctorUid")))
									whichColor = 4;
							}
						}

						// Ĭ��ҽ��������ѯ
						String doctorname = "<font color='#999999' title='��ǰ������ѯ'>"
								+ arrayTp.getJSONObject(h).getString("doctorName") + "</font>";
						// ��ȡҽ������
						ServiceResult<List<MytMainintrodoctorBean>> srMmBean = operconfService
								.getMainintrodoctor(arrayTp.getJSONObject(h).getInt("doctorUid"));
						if (whichColor == 1) {
							doctorname = "<font color='#33cc00' title='��ǰ����ѯ'>"
									+  arrayTp.getJSONObject(h).getString("doctorName")+ "</font>";
							if (srMmBean.getCode() == 1)
								doctorname = "<font color='#33cc00' title='��ǰ����ѯ'>"
										+  arrayTp.getJSONObject(h).getString("doctorName")
										+ "<img src='/BOSS/MYT/image/intro.gif'></font>";
						} else if (whichColor == 2) {
							doctorname = "<font color='#ffcc00' title='��ǰͣ��'>"
									+ arrayTp.getJSONObject(h).getString("doctorName") + "</font>";
							if (srMmBean.getCode() == 1)
								doctorname = "<font color='#ffcc00' title='��ǰͣ��'>"
										+  arrayTp.getJSONObject(h).getString("doctorName")
										+ "<img src='/BOSS/MYT/image/intro.gif'></font>";
						} else if (whichColor == 3) {
							doctorname = "<font color='#9900ff' title='��ǰȷ������ѯ'>"
									+  arrayTp.getJSONObject(h).getString("doctorName")+ "</font>";
							if (srMmBean.getCode() == 1)
								doctorname = "<font color='#9900ff' title='��ǰȷ������ѯ'>"
										+ arrayTp.getJSONObject(h).getString("doctorName")
										+ "<img src='/BOSS/MYT/image/intro.gif'></font>";
						} else if (whichColor == 4) {
							doctorname = "<font color='#ff0000' title='��ǰ������ѯ'>"
									+ arrayTp.getJSONObject(h).getString("doctorName") + "</font>";
							if (srMmBean.getCode() == 1)
								doctorname = "<font color='#ff0000' title='��ǰ������ѯ'>"
										+ arrayTp.getJSONObject(h).getString("doctorName")
										+ "<img src='/BOSS/MYT/image/intro.gif'></font>";
						} else if (whichColor == 0) {
							doctorname = "<font color='#999999' title='��ǰ������ѯ'>"
									+  arrayTp.getJSONObject(h).getString("doctorName")+ "</font>";
							if (srMmBean.getCode() == 1)
								doctorname = "<font color='#999999' title='��ǰ������ѯ'>"
										+  arrayTp.getJSONObject(h).getString("doctorName")
										+ "<img src='/BOSS/MYT/image/intro.gif'></font>";
						}
						jsonMdv.put("text", doctorname);
						jsonMdv.put("id", arrayTp.getJSONObject(h).getInt("doctorUid"));
						jsonMdv.put("leaf", true);
						jsonMdv.put("attributes", true);
						jsonArray.put(jsonMdv);
					}
				}*/
				//json.put("main", 1);
				DoctorInfoVo vo = new DoctorInfoVo();
				vo.setProvinceID(orgid);
				vo.setStandardDeptID(node);
				vo.setDoctorService("1");
				vo.setMain(1);
				
				List<DoctorInfoVo> docs= doctorInfoService.queryDoctorInfoListByCondition(vo);
				if (docs!=null) {
					//List<DoctorVo>  lstMdvBean=dsr.getResult();
					String nowTime = DateUtil.dateFormat(
							DateOper.getNowDateTime(), DateUtil.HM_FORMAT);
					String nowDate = DateUtil.dateFormat(
							DateOper.getNowDateTime(), DateUtil.YMD_FORMAT);
					//ȥ���ظ�uidҽ��
					List<DoctorInfoVo> dcs = new ArrayList<DoctorInfoVo>();
					Integer temp = 0;
					for (DoctorInfoVo dc : docs) {
						if(!dcs.isEmpty()){
						for(int i =0; i< dcs.size(); i ++){
							if(((DoctorInfoVo) dcs.get(i)).getDoctorUid().equals(dc.getDoctorUid())){
								temp=1;
							}
						}}
						if(temp.equals(0)){
							dcs.add(dc);
						}
						temp=0;
					}
					//lstMdvBean =  dcs;
					for (DoctorInfoVo o : dcs) {
						JSONObject jsonMdv = new JSONObject();
						// ��ȡ�Ű���Ϣ
						MytArraworkBean rltMaBean = arraworkService
								.getMytArrawork(o.getDoctorUid(), nowTime)
								.getResult();
						int whichColor = 0;
						if (rltMaBean != null) {
							// �Ű�����
							whichColor = 1;
							// ��ȡͣ����Ϣ
							MytPauseworkBean rltMpBean = pauseService
									.getEntity(o.getDoctorUid(), nowTime,
											nowDate).getResult();
							if (rltMpBean != null) {
								whichColor = 2;
							} else {
								if (DoctorOnLine.get(o.getDoctorUid()
										) != null) {
									whichColor = 4;
								}
								if (arraworkService.isBusy(o.getDoctorUid()))
									whichColor = 4;
							}
						}

						// Ĭ��ҽ��������ѯ
						String doctorname = "<font color='#999999' title='��ǰ������ѯ'>"
								+ o.getDoctorName() + "</font>";
						// ��ȡҽ������
						ServiceResult<List<MytMainintrodoctorBean>> srMmBean = operconfService
								.getMainintrodoctor(o.getDoctorUid());
						if (whichColor == 1) {
							doctorname = "<font color='#33cc00' title='��ǰ����ѯ'>"
									+ o.getDoctorName() + "</font>";
							if (srMmBean.getCode() == 1)
								doctorname = "<font color='#33cc00' title='��ǰ����ѯ'>"
										+ o.getDoctorName()
										+ "<img src='/BOSS/MYT/image/intro.gif'></font>";
						} else if (whichColor == 2) {
							doctorname = "<font color='#ffcc00' title='��ǰͣ��'>"
									+ o.getDoctorName() + "</font>";
							if (srMmBean.getCode() == 1)
								doctorname = "<font color='#ffcc00' title='��ǰͣ��'>"
										+ o.getDoctorName()
										+ "<img src='/BOSS/MYT/image/intro.gif'></font>";
						} else if (whichColor == 3) {
							doctorname = "<font color='#9900ff' title='��ǰȷ������ѯ'>"
									+ o.getDoctorName() + "</font>";
							if (srMmBean.getCode() == 1)
								doctorname = "<font color='#9900ff' title='��ǰȷ������ѯ'>"
										+ o.getDoctorName()
										+ "<img src='/BOSS/MYT/image/intro.gif'></font>";
						} else if (whichColor == 4) {
							doctorname = "<font color='#ff0000' title='��ǰ������ѯ'>"
									+ o.getDoctorName() + "</font>";
							if (srMmBean.getCode() == 1)
								doctorname = "<font color='#ff0000' title='��ǰ������ѯ'>"
										+ o.getDoctorName()
										+ "<img src='/BOSS/MYT/image/intro.gif'></font>";
						} else if (whichColor == 0) {
							doctorname = "<font color='#999999' title='��ǰ������ѯ'>"
									+ o.getDoctorName() + "</font>";
							if (srMmBean.getCode() == 1)
								doctorname = "<font color='#999999' title='��ǰ������ѯ'>"
										+ o.getDoctorName()
										+ "<img src='/BOSS/MYT/image/intro.gif'></font>";
						}
						jsonMdv.put("text", doctorname);
						jsonMdv.put("id", o.getDoctorUid());
						jsonMdv.put("leaf", true);
						jsonMdv.put("attributes", true);
						jsonArray.put(jsonMdv);
					}
				
				}
				
				
				
				/*JSONObject json = new JSONObject();
				json.put("provinceId", orgid);
				json.put("standardDeptId", node);
				json.put("serviceStatusSearch", 2);
				ServiceResult<List<DoctorVo>> dsr = doctorInfoApi.queryDoctorList(json.toString());
				//List<DoctorVo>  list=dsr.getResult();
				if (dsr.getCode() > 0) {
					List<DoctorVo>  lstMdvBean=dsr.getResult();
					String nowTime = DateUtil.dateFormat(
							DateOper.getNowDateTime(), DateUtil.HM_FORMAT);
					String nowDate = DateUtil.dateFormat(
							DateOper.getNowDateTime(), DateUtil.YMD_FORMAT);
					//ȥ���ظ�uidҽ��
					List<DoctorVo> dcs = new ArrayList<DoctorVo>();
					Integer temp = 0;
					for (DoctorVo dc : lstMdvBean) {
						if(!dcs.isEmpty()){
						for(int i =0; i< dcs.size(); i ++){
							if(((DoctorVo) dcs.get(i)).getDoctorUid().equals(dc.getDoctorUid())){
								temp=1;
							}
						}}
						if(temp.equals(0)){
							dcs.add(dc);
						}
						temp=0;
					}
					lstMdvBean =  dcs;
					for (DoctorVo o : lstMdvBean) {
						JSONObject jsonMdv = new JSONObject();
						// ��ȡ�Ű���Ϣ
						MytArraworkBean rltMaBean = arraworkService
								.getMytArrawork(o.getDoctorUid(), nowTime)
								.getResult();
						int whichColor = 0;
						if (rltMaBean != null) {
							// �Ű�����
							whichColor = 1;
							// ��ȡͣ����Ϣ
							MytPauseworkBean rltMpBean = pauseService
									.getEntity(o.getDoctorUid(), nowTime,
											nowDate).getResult();
							if (rltMpBean != null) {
								whichColor = 2;
							} else {
								if (DoctorOnLine.get(o.getDoctorUid()
										) != null) {
									whichColor = 4;
								}
								if (arraworkService.isBusy(o.getDoctorUid()))
									whichColor = 4;
							}
						}

						// Ĭ��ҽ��������ѯ
						String doctorname = "<font color='#999999' title='��ǰ������ѯ'>"
								+ o.getDoctorName() + "</font>";
						// ��ȡҽ������
						ServiceResult<List<MytMainintrodoctorBean>> srMmBean = operconfService
								.getMainintrodoctor(o.getDoctorUid());
						if (whichColor == 1) {
							doctorname = "<font color='#33cc00' title='��ǰ����ѯ'>"
									+ o.getDoctorName() + "</font>";
							if (srMmBean.getCode() == 1)
								doctorname = "<font color='#33cc00' title='��ǰ����ѯ'>"
										+ o.getDoctorName()
										+ "<img src='/BOSS/MYT/image/intro.gif'></font>";
						} else if (whichColor == 2) {
							doctorname = "<font color='#ffcc00' title='��ǰͣ��'>"
									+ o.getDoctorName() + "</font>";
							if (srMmBean.getCode() == 1)
								doctorname = "<font color='#ffcc00' title='��ǰͣ��'>"
										+ o.getDoctorName()
										+ "<img src='/BOSS/MYT/image/intro.gif'></font>";
						} else if (whichColor == 3) {
							doctorname = "<font color='#9900ff' title='��ǰȷ������ѯ'>"
									+ o.getDoctorName() + "</font>";
							if (srMmBean.getCode() == 1)
								doctorname = "<font color='#9900ff' title='��ǰȷ������ѯ'>"
										+ o.getDoctorName()
										+ "<img src='/BOSS/MYT/image/intro.gif'></font>";
						} else if (whichColor == 4) {
							doctorname = "<font color='#ff0000' title='��ǰ������ѯ'>"
									+ o.getDoctorName() + "</font>";
							if (srMmBean.getCode() == 1)
								doctorname = "<font color='#ff0000' title='��ǰ������ѯ'>"
										+ o.getDoctorName()
										+ "<img src='/BOSS/MYT/image/intro.gif'></font>";
						} else if (whichColor == 0) {
							doctorname = "<font color='#999999' title='��ǰ������ѯ'>"
									+ o.getDoctorName() + "</font>";
							if (srMmBean.getCode() == 1)
								doctorname = "<font color='#999999' title='��ǰ������ѯ'>"
										+ o.getDoctorName()
										+ "<img src='/BOSS/MYT/image/intro.gif'></font>";
						}
						jsonMdv.put("text", doctorname);
						jsonMdv.put("id", o.getDoctorUid());
						jsonMdv.put("leaf", true);
						jsonMdv.put("attributes", true);
						jsonArray.put(jsonMdv);
					}
				}*/

				return new HttpResponseContext(jsonArray.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(e);
		}
	}
	public HttpResponseContext getDoctorInfo(HttpRequestContext request) {

		try {
			String nname = request.getParameter("nname");
			String number = request.getParameter("number");
			String operconfid = request.getParameter("operconfid");
			
			MytDoctorViewBean qryMdvBean = new MytDoctorViewBean();
			if (StringUtils.isNotEmpty(operconfid)) {
				qryMdvBean.setOperconfid(Integer.parseInt(operconfid));
			}
			if (nname != null
					&& !nname.replaceAll(Constant.SPACE_SYMBOL,
							Constant.EMPTY_SYMBOL)
							.equals(Constant.EMPTY_SYMBOL)) {
				qryMdvBean.setDoctorname(nname.replaceAll(
						Constant.SPACE_SYMBOL, Constant.EMPTY_SYMBOL));
			}
			if (number.length() == 7) {
				qryMdvBean.setSevendoctorid(number.replaceAll(
						Constant.SPACE_SYMBOL, Constant.EMPTY_SYMBOL));
			} else if (number.length() == 10) {
				qryMdvBean.setTendoctorid(number.replaceAll(
						Constant.SPACE_SYMBOL, Constant.EMPTY_SYMBOL));
			} else {
				if (StringUtils.isNotEmpty(number)) {
					qryMdvBean.setDoctorid(Integer.valueOf(number.replaceAll(
							Constant.SPACE_SYMBOL, Constant.EMPTY_SYMBOL)));
				}
			}
			if (StringUtils.isEmpty(nname) && StringUtils.isEmpty(number)
					&& StringUtils.isEmpty(operconfid)) {
				qryMdvBean.setOperconfid(0);
			}
			List<MytDoctorViewBean> lstMdvBean = null;
			MytDoctorViewBean rltMdvBean = null ;
			JSONArray jsonArray = new JSONArray();
			
		
			DoctorVoMore docm = new DoctorVoMore();
			DoctorInfoVo vo = new DoctorInfoVo();
			if(qryMdvBean.getOperconfid()!=null){
				docm.setDoctorUid(qryMdvBean.getOperconfid());
				vo.setDoctorUid(qryMdvBean.getOperconfid());
			}else if(!StringUtils.isEmpty(qryMdvBean.getDoctorname())){
				docm.setDoctorName(qryMdvBean.getDoctorname());
				vo.setDoctorName(qryMdvBean.getDoctorname());
			}else if(qryMdvBean.getDoctorid()!= null){
				docm.setBasedoctorid(qryMdvBean.getDoctorid());
				vo.setBaseDoctorID(qryMdvBean.getDoctorid());
			}else{
				return new HttpResponseContext(new JSONObject().put(
						"result", jsonArray));
			}
			/*com.yihu.baseinfo.api.DoctorInfoApi
			   api=Rpc.get(DoctorInfoApi.class
					   , ConfigUtil.getInstance().getUrl("url.baseinfo"));//����API
			docm.setServiceStatusSearch("2");//�п�ͨ�绰��ѯ���̶���2				
			ServiceResult<List< DoctorVo >>  docSr=api.queryDoctorList (docm);
			List< DoctorVo >  doctors=docSr.getResult();*/
			//vo.setDoctorService("1");
			vo.setMain(1);
			List<DoctorInfoVo> docs= doctorInfoService.queryDoctorInfoListByCondition(vo);
			if(docs.size()== 0){
				return new HttpResponseContext(new JSONObject().put(
						"result", jsonArray));
			}else{
				if(qryMdvBean.getOperconfid()!=null ){
					DoctorInfoVo dvo = new DoctorInfoVo();
					dvo.setDoctorUid(qryMdvBean.getOperconfid());
					//dvo.setDoctorService("1");
					dvo.setMain(1);
					dvo = doctorInfoService.queryDoctorInfo(dvo);
					//ServiceResult<String>  dcscr =api.getDoctorByUid(qryMdvBean.getOperconfid());

					JSONObject dcJson = new JSONObject(dvo);
					ServiceResult<MytDoctorViewBean> sr = arraworkService
							.getMytDoctor(dcJson.getInt("doctorUid"));
					if(sr.getCode() > 0){
						rltMdvBean =  sr.getResult();
					}else{
						rltMdvBean = new MytDoctorViewBean();
					}
					rltMdvBean.setSex(dcJson.getString("doctorSex"));
					rltMdvBean.setOrgid(dcJson.getInt("provinceID") );
					rltMdvBean.setDoctorid( dcJson.getInt("doctorUid"));
					rltMdvBean.setDoctorname( dcJson.getString("doctorName"));
					rltMdvBean.setHospitalname(dcJson.getString("hosName"));
					rltMdvBean.setHospofficename( dcJson.getString("deptName"));
					if( !StringUtils.isEmpty(dcJson.getString("skill"))){
						java.sql.Clob clb   =  new  javax.sql.rowset.serial.SerialClob( (dcJson.getString("skill")).toCharArray() );
						rltMdvBean.setMazskill(clb);
					}else{
						rltMdvBean.setMazskill(null);
					}
					rltMdvBean.setOperconfid(dcJson.getInt("doctorUid"));
				}else if(docs.size()==1){
					ServiceResult<MytDoctorViewBean> sr = arraworkService
							.getMytDoctor(docs.get(0).getDoctorUid());
					if(sr.getCode() > 0){
						rltMdvBean =  sr.getResult();
					}else{
						rltMdvBean = new MytDoctorViewBean();
					}
					
					rltMdvBean.setSex(docs.get(0).getDoctorSex().toString());
					rltMdvBean.setOrgid(docs.get(0).getProvinceID());
					rltMdvBean.setDoctorid(docs.get(0).getBaseDoctorID());
					rltMdvBean.setDoctorname(docs.get(0).getDoctorName());
					rltMdvBean.setHospitalname(docs.get(0).getHosName());
					rltMdvBean.setHospofficename(docs.get(0).getDeptName());
					if( !StringUtils.isEmpty(docs.get(0).getSkill())){
						java.sql.Clob clb   =  new  javax.sql.rowset.serial.SerialClob( docs.get(0).getSkill().toCharArray() );
						rltMdvBean.setMazskill(clb);
					}else{
						rltMdvBean.setMazskill(null);
					}
					rltMdvBean.setOperconfid(docs.get(0).getDoctorUid());
				}else{
					for (DoctorInfoVo o : docs) {
						JSONObject jsr = new JSONObject();
						jsr.put("doctorname", o.getDoctorName());
						jsonArray.put(jsr);
					}
					return new HttpResponseContext(new JSONObject().put(
							"result", jsonArray));
					
				}
			}
			
			/*if(docSr.getResult().size()== 0){
				return new HttpResponseContext(new JSONObject().put(
						"result", jsonArray));
			}else{
				if(qryMdvBean.getOperconfid()!=null ){
					ServiceResult<String>  dcscr =api.getDoctorByUid(qryMdvBean.getOperconfid());

					JSONObject dcJson = new JSONObject(dcscr.getResult());
					ServiceResult<MytDoctorViewBean> sr = arraworkService
							.getMytDoctor(dcJson.getInt("doctorUid"));
					if(sr.getCode() > 0){
						rltMdvBean =  sr.getResult();
					}else{
						rltMdvBean = new MytDoctorViewBean();
					}
					rltMdvBean.setSex(dcJson.getString("doctorSex"));
					rltMdvBean.setOrgid(dcJson.getInt("provinceId") );
					rltMdvBean.setDoctorid( dcJson.getInt("doctorUid"));
					rltMdvBean.setDoctorname( dcJson.getString("doctorName"));
					rltMdvBean.setHospitalname(dcJson.getString("hosName"));
					rltMdvBean.setHospofficename( dcJson.getString("deptName"));
					if( !StringUtils.isEmpty(dcJson.getString("skill"))){
						java.sql.Clob clb   =  new  javax.sql.rowset.serial.SerialClob( (dcJson.getString("skill")).toCharArray() );
						rltMdvBean.setMazskill(clb);
					}else{
						rltMdvBean.setMazskill(null);
					}
					rltMdvBean.setOperconfid(dcJson.getInt("doctorUid"));
				}else if(docSr.getResult().size()==1){
					ServiceResult<MytDoctorViewBean> sr = arraworkService
							.getMytDoctor(doctors.get(0).getDoctorUid());
					if(sr.getCode() > 0){
						rltMdvBean =  sr.getResult();
					}else{
						rltMdvBean = new MytDoctorViewBean();
					}
					
					rltMdvBean.setSex(doctors.get(0).getDoctorSex().toString());
					rltMdvBean.setOrgid(doctors.get(0).getProvinceId());
					rltMdvBean.setDoctorid(doctors.get(0).getBasedoctorid());
					rltMdvBean.setDoctorname(doctors.get(0).getDoctorName());
					rltMdvBean.setHospitalname(doctors.get(0).getHosName());
					rltMdvBean.setHospofficename(doctors.get(0).getDeptName());
					if( !StringUtils.isEmpty(doctors.get(0).getSkill())){
						java.sql.Clob clb   =  new  javax.sql.rowset.serial.SerialClob( doctors.get(0).getSkill().toCharArray() );
						rltMdvBean.setMazskill(clb);
					}else{
						rltMdvBean.setMazskill(null);
					}
					rltMdvBean.setOperconfid(doctors.get(0).getDoctorUid());
				}else{
					for (DoctorVo o : doctors) {
						JSONObject jsr = new JSONObject();
						jsr.put("doctorname", o.getDoctorName());
						jsonArray.put(jsr);
					}
					return new HttpResponseContext(new JSONObject().put(
							"result", jsonArray));
					
				}
			}*/			
			//--------------------
			/*if (qryMdvBean.getOperconfid() != null
					&& (!qryMdvBean.getOperconfid().equals(""))) {
				DoctorVoMore docm = new DoctorVoMore();
				docm.setDoctorSn(qryMdvBean.getOperconfid());
				docm.setServiceStatusSearch("2");//�п�ͨ�绰��ѯ���̶���2				
				ServiceResult<List< DoctorVo >>  docSr=api.queryDoctorList (docm);
				List< DoctorVo >  doctors=docSr.getResult();
				if(doctors.size()>0&& rltMdvBean!= null){
					rltMdvBean.setSex(doctors.get(0).getDoctorSex().toString());
					rltMdvBean.setOrgid(doctors.get(0).getProvinceId());
					rltMdvBean.setDoctorid(doctors.get(0).getBasedoctorid());
					rltMdvBean.setDoctorname(doctors.get(0).getDoctorName());
					rltMdvBean.setHospitalname(doctors.get(0).getHosName());
					rltMdvBean.setHospofficename(doctors.get(0).getDeptName());
					java.sql.Clob clb   =  new  javax.sql.rowset.serial.SerialClob(doctors.get(0).getSkill().toCharArray());
					rltMdvBean.setMazskill(clb);
				}
			} else {
				
				DoctorVoMore docm = new DoctorVoMore();
				docm.setDoctorName(qryMdvBean.getDoctorname());
				docm.setServiceStatusSearch("2");//�п�ͨ�绰��ѯ���̶���2				
				ServiceResult<List< DoctorVo >>  docSr=api.queryDoctorList (docm);
				List< DoctorVo >  doctors=docSr.getResult();
				lstMdvBean = arraworkService.getMytDoctorList(qryMdvBean.getDoctorname()).getResult();
				if (lstMdvBean != null && !lstMdvBean.isEmpty()
						&& lstMdvBean.size() == 1) {
						rltMdvBean = lstMdvBean.get(0);
						rltMdvBean.setSex(doctors.get(0).getDoctorSex().toString());
						rltMdvBean.setOrgid(doctors.get(0).getProvinceId());
						rltMdvBean.setDoctorid(doctors.get(0).getBasedoctorid());
						rltMdvBean.setDoctorname(doctors.get(0).getDoctorName());
						rltMdvBean.setHospitalname(doctors.get(0).getHosName());
						rltMdvBean.setHospofficename(doctors.get(0).getDeptName());
						java.sql.Clob clb   =  new  javax.sql.rowset.serial.SerialClob(doctors.get(0).getSkill().toCharArray());
						rltMdvBean.setMazskill(clb);
				} else if (lstMdvBean == null) {
					jsonArray.put("");
					return new HttpResponseContext(new JSONObject().put(
							"result", jsonArray));
				} else {
					for (MytDoctorViewBean o : lstMdvBean) {
						JSONObject jsr = new JSONObject();
						jsr.put("doctorname", o.getDoctorname());
						jsonArray.put(jsr);
					}
					return new HttpResponseContext(new JSONObject().put(
							"result", jsonArray));
				}
			}*/
			
			JSONObject jsonRlt = new JSONObject();
			if (StringUtils.isEmpty(rltMdvBean.getSex())) {
				jsonRlt.put("sex", Constant.EMPTY_SYMBOL);
			} else if (String.valueOf(MytConst.MALE.getValue()).equals(
					rltMdvBean.getSex())) {
				jsonRlt.put("sex", MytConst.MALE.getInfo());
			} else if (String.valueOf(MytConst.FEMALE.getValue()).equals(
					rltMdvBean.getSex())) {
				jsonRlt.put("sex", MytConst.FEMALE.getInfo());
			}else if (String.valueOf(MytConst.UKNOW.getValue()).equals(
					rltMdvBean.getSex())) {
				jsonRlt.put("sex", MytConst.UKNOW.getInfo());
			}
			// �жϱ������������
			if (rltMdvBean.getDoctorlevel() == null) {
				jsonRlt.put("baselevel", Constant.EMPTY_SYMBOL);
				jsonRlt.put("mytlevel", Constant.EMPTY_SYMBOL);
			} else if (rltMdvBean.getDoctorlevel() == 0) {
				jsonRlt.put("baselevel",
						MytConst.DOCTORLEVEL_ORDINARY_PRACTITIONER.getInfo());
				jsonRlt.put("mytlevel",
						MytConst.DOCTORLEVEL_ORDINARY_PRACTITIONER.getInfo());
			} else if (rltMdvBean.getDoctorlevel() == 1) {
				jsonRlt.put("baselevel",
						MytConst.DOCTORLEVEL_SPECIAL_ALLOWANCE.getInfo());
				jsonRlt.put("mytlevel",
						MytConst.DOCTORLEVEL_SPECIAL_ALLOWANCE.getInfo());
			} else if (rltMdvBean.getDoctorlevel() == 2) {
				jsonRlt.put("baselevel",
						MytConst.DOCTORLEVEL_SPECIAL_CLINIC_EXPERTS.getInfo());
				jsonRlt.put("mytlevel",
						MytConst.DOCTORLEVEL_SPECIAL_CLINIC_EXPERTS.getInfo());
			} else if (rltMdvBean.getDoctorlevel() == 3) {
				jsonRlt.put("baselevel",
						MytConst.DOCTORLEVEL_DIRECTOR.getInfo());
				jsonRlt.put("mytlevel", MytConst.DOCTORLEVEL_DIRECTOR.getInfo());
			} else if (rltMdvBean.getDoctorlevel() == 4) {
				jsonRlt.put("baselevel",
						MytConst.DOCTORLEVEL_VICE_DIRECTOR.getInfo());
				jsonRlt.put("mytlevel",
						MytConst.DOCTORLEVEL_VICE_DIRECTOR.getInfo());
			} else if (rltMdvBean.getDoctorlevel() == 5) {
				jsonRlt.put("baselevel", MytConst.DOCTORLEVEL_DOCTOR.getInfo());
				jsonRlt.put("mytlevel", MytConst.DOCTORLEVEL_DOCTOR.getInfo());
			} else if (rltMdvBean.getDoctorlevel() == 6) {
				jsonRlt.put("baselevel",
						MytConst.DOCTORLEVEL_LEAD_NURSE.getInfo());
				jsonRlt.put("mytlevel",
						MytConst.DOCTORLEVEL_LEAD_NURSE.getInfo());
			} else if (rltMdvBean.getDoctorlevel() == 7) {
				jsonRlt.put("baselevel",
						MytConst.DOCTORLEVEL_GENERAL_PRACTITIONER.getInfo());
				jsonRlt.put("mytlevel",
						MytConst.DOCTORLEVEL_GENERAL_PRACTITIONER.getInfo());
			}

			// ��ȡͣ���¼��
			ServiceResult<List<MytPauseworkBean>> sr = pauseService
					.getResult(rltMdvBean.getOperconfid());
			List<MytPauseworkBean> lstMpBean = sr.getCode() > 0 ? sr
					.getResult() : null;
			if (lstMpBean != null && !lstMpBean.isEmpty()) {
				for (MytPauseworkBean mpBean : lstMpBean) {
					String pause = Constant.FROM_SYMBOL
							+ DateUtil.dateFormat(mpBean.getStartdate(),
									DateUtil.YMD_FORMAT)
							+ Constant.TO_SYMBOL
							+ DateUtil.dateFormat(mpBean.getEnddate(),
									DateUtil.YMD_FORMAT) + "ͣ�ʱ�Σ�"
							+ mpBean.getStarttime() + Constant.CROSS_SYMBOL
							+ mpBean.getEndtime();
					jsonRlt.put("pause", pause);
				}
			} else {
				jsonRlt.put("pause", Constant.CROSS_SYMBOL);
			}

			// ��ȡ��ѯ��ˮ��¼��
			MytBookenrolBean qryMbBean = new MytBookenrolBean();
			qryMbBean.setOperconfid(rltMdvBean.getOperconfid() );
			ServiceResult<List<MytBookenrolBean>> srMb = bookenrolService
					.getMytBookenrolResult(qryMbBean);
			MytBookenrolBean rltMbBean = srMb.getCode() > 0 ? srMb.getResult()
					.get(0) : null;

			jsonRlt.put("orgid", rltMdvBean == null ? Constant.CROSS_SYMBOL
					: rltMdvBean.getOrgid());
			jsonRlt.put("replyphone", (rltMbBean != null && rltMbBean
					.getRevertphone() != null) ? rltMbBean.getRevertphone()
					: "NoPhone");
			jsonRlt.put(
					"hospofficeid",
					rltMdvBean == null ? Constant.CROSS_SYMBOL : rltMdvBean
							.getHospofficeid());
			jsonRlt.put(
					"operconfid",
					rltMdvBean == null ? Constant.CROSS_SYMBOL : rltMdvBean
							.getOperconfid());
			jsonRlt.put("doctorid", rltMdvBean == null ? Constant.CROSS_SYMBOL
					: rltMdvBean.getTendoctorid());
			jsonRlt.put(
					"basedoctorid",
					rltMdvBean == null ? Constant.CROSS_SYMBOL : rltMdvBean
							.getDoctorid());
			jsonRlt.put(
					"doctorname",
					rltMdvBean == null ? Constant.CROSS_SYMBOL : rltMdvBean
							.getDoctorname());
			jsonRlt.put(
					"hospitalname",
					rltMdvBean == null ? Constant.CROSS_SYMBOL : rltMdvBean
							.getHospitalname());
			jsonRlt.put(
					"hospofficename",
					rltMdvBean == null ? Constant.CROSS_SYMBOL : rltMdvBean
							.getHospofficename());
			jsonRlt.put("doctoridiom", (rltMdvBean == null || rltMdvBean
					.getDoctoridiom() == null) ? Constant.CROSS_SYMBOL
					: rltMdvBean.getDoctoridiom());
			jsonRlt.put(
					"remark",
					(rltMdvBean == null || rltMdvBean.getRemark() == null) ? Constant.CROSS_SYMBOL
							: rltMdvBean.getRemark());
			jsonRlt.put("mazskill", (rltMdvBean == null || rltMdvBean
					.getMazskill() == null) ? Constant.CROSS_SYMBOL
					: StringUtil.ClobToString(rltMdvBean.getMazskill()));
			jsonArray.put(jsonRlt);

			return new HttpResponseContext(new JSONObject().put("result",
					jsonArray));
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(e);
		}
	}

	/**
	 * ��ͨ��ҽ��ѯ
	 * 
	 * @param request
	 * @return
	 */
	public HttpResponseContext Add(HttpRequestContext request) {

		try {
			OperatorInfo operator = request.getOperator();
			if (operator == null) {
				return new HttpResponseContext("����δ��¼���¼�ѳ�ʱ��");
			}

			SysOperatorBean proSoBean = new SysOperatorBean();
			proSoBean.setOperatorID(operator.getOperatorID());
			proSoBean.setOperatorName(operator.getOperatorName());
			proSoBean.setDeptID(operator.getDeptID());
			proSoBean.setDeptName(operator.getDeptName());

			MytOperconfigBean proMoBean = new MytOperconfigBean();
			proMoBean.setDoctorid(Integer.valueOf(request.getParameter("doctorid")));
			proMoBean.setDoctoridiom(request.getParameter("doctoridiom"));
			proMoBean.setDoctorlevel(request.getInt("doctorlevel"));
			proMoBean.setIspaydoctor(request.getInt("ispaydoctor"));
			proMoBean.setSendtype(request.getParameter("sendtype"));
			proMoBean.setPaytype(request.getParameter("paytype"));
			proMoBean.setBalancetype(request.getParameter("balancetype"));
			proMoBean.setRemark(request.getParameter("remark"));

			Boolean bln = operconfService.isExsistMytOperconfig(
					proMoBean.getDoctorid().toString() , "0").getResult();
			if (bln) { // true ��ʾ���ڼ�¼
				return new HttpResponseContext(-1, "��ҽ���ѿ�ͨ��ҽ��ѯ��");
			} else {
				String consphone = request.getParameter("consphone");
				ReturnValue rv = operconfService.addDoctor(
						operator.getOperatorID(), operator.getOperatorName(),
						proMoBean, consphone);
				return new HttpResponseContext(rv.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			return new HttpResponseContext(e);
		}
	}

	/**
	 * ����ҽ������Ƴ�ҽ����������
	 * 
	 * @param doctorId
	 */
	public static void remove(String doctorId) {
		if (map != null) {
			map.remove("Y" + doctorId);
			map.remove("N" + doctorId);
			map.remove(doctorId);
		}
	}

	/**
	 * ��ҽ��������Ϣ����map��
	 * 
	 * @param yndoctorId
	 */
	@SuppressWarnings("unchecked")
	public synchronized static void put(String yndoctorId, String olDate) {
		if (map == null) {
			map = new HashMap();
		}

		map.put(yndoctorId, olDate);
	}

	@SuppressWarnings("unchecked")
	public static Iterator get() {
		if (map == null) {
			return null;
		}

		return map.keySet().iterator();
	}

	public static String get(String yndoctorId) {
		if (map == null) {
			return null;
		}

		return (String) map.get(yndoctorId);
	}
}
