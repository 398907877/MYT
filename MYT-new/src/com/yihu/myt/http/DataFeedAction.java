package com.yihu.myt.http;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.boss.sdk.HttpRequestContext;
import com.boss.sdk.HttpResponseContext;
import com.common.json.JSONException;
import com.common.json.JSONObject;
import com.coreframework.ioc.Ioc;
import com.coreframework.log.LogBody;
import com.coreframework.log.Logger;
import com.coreframework.vo.ServiceResult;
import com.yihu.myt.IArraworkService;
import com.yihu.myt.IPauseService;
import com.yihu.myt.service.PauseService;
import com.yihu.myt.util.DateUtil;
import com.yihu.myt.vo.DataFeedBean;
import com.yihu.myt.vo.MytArraworkView;
import com.yihu.myt.vo.MytArraworkWBean;
import com.yihu.myt.vo.MytArraworkBean;
import com.yihu.myt.vo.MytPauseworkBean;

public class DataFeedAction {

	private static IArraworkService arraworkService = Ioc
			.get(IArraworkService.class);
	private static IPauseService pauseService = Ioc.get(IPauseService.class); // 医生停诊服务接口


	public HttpResponseContext getWorkList(HttpRequestContext request) {
		try {
			String begin = request.getParameter("showdate");
			String viewtype = request.getParameter("viewtype");
			//String timeZone = request.getParameter("timezone");
			String doctor = request.getParameter("docid");
			//String begin = request.getParameter("begin");
			//String end = request.getParameter("end");
			MytArraworkBean mytWorkBean = new MytArraworkBean();
			ServiceResult<List<MytArraworkBean>> workList = null;
			JSONObject dataJson = new JSONObject();
			ArrayList list = new ArrayList();
			ArrayList limList;
			if (doctor != null && !doctor.equals("999999")) {
				mytWorkBean.setOperconfid(Integer.valueOf(doctor));
				mytWorkBean.setState(1);
				workList = arraworkService.getMytArraworkList(mytWorkBean);
				if(workList.getCode() == -1){
					dataJson.put("events", "");
					dataJson.put("issort", "true");
					dataJson.put("error", "暂无此医生排班数据！");
					dataJson.put("start", DateUtil.dateFormat(new Date(),
							DateUtil.YMDHM_FORMAT_OTHER));
					dataJson.put("end", DateUtil.dateFormat(DateUtil.getAfterDate(
							new Date(), 7, DateUtil.YMDHM_FORMAT_OTHER)));
					 return new HttpResponseContext(dataJson);
				}
				int i = workList.getResult().size();
				String[][] events = new String[i][10];
				int h = 0;
				Date starTime;
				Date endTime;
				Date[] allWeek;
				// Date[] allWeek = DateUtil.getRollAllWeekZH(new Date());
				//	List<Date> allday  = 	DateUtil.getRollAllWeekZH(DateUtil.parse(begin, DateUtil.YMD_FORMAT));
				if(viewtype.equals("week")){
					allWeek = DateUtil.getRollWeekMTS(DateUtil.stringFormat(begin,DateUtil.YMD_FORMAT));
				}else{
					allWeek = DateUtil.getRollMonth(DateUtil.stringFormat(begin,DateUtil.YMD_FORMAT));
				}
				List<Date> month = DateUtil.getDatesBetweenTwoDate(allWeek[0],
						allWeek[1]);
				//allWeek = DateUtil.getRollAllWeekZH(new Date());
				for (Date day : month) {
					for (MytArraworkBean work : workList.getResult()) {
						if (work.getWeekid() != ""
								&& !work.getWeekid().equals("")
								&& Integer.valueOf(work.getWeekid()) == DateUtil
										.getDate(day)) {
							limList = new ArrayList();
							int week = Integer.valueOf(work.getWeekid());
							limList.add("arraworkId="+work.getArraworkid().toString()+"&operconfId=" + doctor);
							limList.add("正常上班");
							starTime = new Date();
							endTime = new Date();
							starTime = DateUtil.getTimeOfDay(day,
									work.getStarttime(),
									DateUtil.YMD_FORMAT_OTHER,
									DateUtil.YMDHM_FORMAT_OTHER);
							endTime = DateUtil.getTimeOfDay(day,
									work.getEndtime(),
									DateUtil.YMD_FORMAT_OTHER,
									DateUtil.YMDHM_FORMAT_OTHER);
							limList.add(DateUtil.dateFormat(starTime,
									DateUtil.YMDHM_FORMAT_OTHER));
							limList.add(DateUtil.dateFormat(endTime,
									DateUtil.YMDHM_FORMAT_OTHER));
							limList.add(0);
							limList.add(0);
							limList.add(0);
							limList.add(10);
							limList.add(1);
							limList.add("医院");
							limList.add("arrawork");
							list.add(limList);
						}
					}
				}
				MytPauseworkBean mytPWork = new MytPauseworkBean();
				mytPWork.setOperconfid(Integer.valueOf(doctor));
				ServiceResult<List<MytPauseworkBean>> psList = pauseService.getTimeResults(mytPWork);
				ArrayList limpw;
				if(psList.getCode()==1){
					for (MytPauseworkBean mytPs :  psList.getResult()) {
						List<Date> days = DateUtil.getDatesBetweenTwoDate(mytPs.getStartdate(),mytPs.getEnddate());
						for (Date psday : days) {
							limpw = new ArrayList();
							limpw.add(mytPs.getPauseid()+"&operconfId=" + doctor);
							limpw.add("停诊");
							starTime = DateUtil.getTimeOfDay(psday,
									mytPs.getStarttime(),
									DateUtil.YMD_FORMAT_OTHER,
									DateUtil.YMDHM_FORMAT_OTHER);
							endTime = DateUtil.getTimeOfDay(psday,
									mytPs.getEndtime(),
									DateUtil.YMD_FORMAT_OTHER,
									DateUtil.YMDHM_FORMAT_OTHER);
							limpw.add(DateUtil.dateFormat(starTime,
									DateUtil.YMDHM_FORMAT_OTHER));
							limpw.add(DateUtil.dateFormat(endTime,
									DateUtil.YMDHM_FORMAT_OTHER));
							limpw.add(0);
							limpw.add(0);
							limpw.add(0);
							limpw.add(1);
							limpw.add(1);
							limpw.add("");
							if(mytPs.getRemark()!=null){
								limpw.add(""+mytPs.getRemark());
							}else{
								limpw.add("pauseWork");
							}
							list.add(limpw);
						}
					}
				}
				dataJson.put("events", list);
				dataJson.put("issort", "true");
				dataJson.put("start", DateUtil.dateFormat(new Date(),
						DateUtil.YMDHM_FORMAT_OTHER));
				dataJson.put("end", DateUtil.dateFormat(DateUtil.getAfterDate(
						new Date(), 7, DateUtil.YMDHM_FORMAT_OTHER)));
			} else {
				limList = new ArrayList();
				list.add(limList);
				dataJson.put("events", list);
				dataJson.put("issort", "true");
				dataJson.put("start", DateUtil.dateFormat(new Date(),
						DateUtil.YMDHM_FORMAT_OTHER));
				dataJson.put("end", DateUtil.dateFormat(DateUtil.getAfterDate(
						new Date(), 7, DateUtil.YMDHM_FORMAT_OTHER)));
			}
			/*
			 * String rt =
			 * "{'issort':true,'start':'Date(1377964800000)','end':'Date(1377964800000)','error':null"
			 * +
			 * ", 'events': [['21821','正常上班','Date(1377964800000)','Date(1377964800000)','0','0','0','4','1','','']]}"
			 * ; JSONObject rtJson = new JSONObject(rt);
			 */
			return new HttpResponseContext(dataJson);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.get().error("com.yihu.myt", LogBody.me().set(e));
			JSONObject rtJson = new JSONObject();
			try {
				rtJson.put("issort", "true");
				rtJson.put("start", DateUtil.dateFormat(new Date(),
						DateUtil.YMDHM_FORMAT_OTHER));
				rtJson.put("end", DateUtil.dateFormat(DateUtil.getAfterDate(
						new Date(), 7, DateUtil.YMDHM_FORMAT_OTHER)));
				rtJson.put("error", e);
				rtJson.put("events", "");
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return new HttpResponseContext(rtJson);
		}

	}
}
