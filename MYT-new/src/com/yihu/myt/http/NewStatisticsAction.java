package com.yihu.myt.http;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;










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
import com.yihu.myt.mgr.ApiUtil;
import com.yihu.myt.service.service.IDoctorInfoService;
import com.yihu.myt.service.service.INewDoctorService;
import com.yihu.myt.service.service.IPostService;
import com.yihu.myt.util.DBCache;
import com.yihu.myt.util.DateUtil;
import com.yihu.myt.util.PostClient;
import com.yihu.myt.util.StringUtil;
import com.yihu.myt.vo.Consstatistic;
import com.yihu.myt.vo.DoctorInfoVo;
import com.yihu.myt.vo.MytArraworkBean;
import com.yihu.myt.vo.MytBookenrolBean;
import com.yihu.myt.vo.MytBookenrolView;
import com.yihu.myt.vo.MytConsenrolBean;
import com.yihu.myt.vo.MytDoctorViewBean;
import com.yihu.myt.vo.MytPauseworkBean;
import com.yihu.myt.vo.MytRevertBean;
import com.yihu.myt.vo.NewDocProblem;
import com.yihu.myt.vo.NewDocProblemParaVO;
import com.yihu.myt.vo.Page;
import com.yihu.myt.vo.SuchDownDocMSGVo;
import com.yihu.myt.vo.XlsDto;
import com.yihu.oa.api.IBasisWS;
import com.yihu.wsgw.api.InterfaceMessage;
/**
 * @author WUJIAJUN
 * 统计医生报表 
 * */
public class NewStatisticsAction {

	private static IBookenrolService bookenrolService = Ioc
			.get(IBookenrolService.class); // 咨询流水服务接口
	private static IArraworkService arraworkService = Ioc
			.get(IArraworkService.class); // 排班服务接口
	private static IPauseService pauseService = Ioc.get(IPauseService.class); // 停诊服务接口
	private static IDoctorInfoService doctorInfoService = Ioc.get(IDoctorInfoService.class); // 停诊服务接口
	private static IPostService postService = Ioc
			.get(IPostService.class); 
	
	
	
	private static INewDoctorService newDoctorService = Ioc
			.get(INewDoctorService.class); 
	
	
	

	/**
	 * 统计一个月 用户 和医生的回复量
	 * @param request
	 * @return
	 */
public HttpResponseContext listEveryDay(HttpRequestContext request) {
		
		
				try {
					
					
					 Calendar aa = Calendar.getInstance();  
				      int yearrrrrrrr= aa.get(Calendar.YEAR);
				      int monthrrrr= aa.get(Calendar.MONTH);
			     	 System.out.println("年份：：："+yearrrrrrrr);
				      
			    	 System.out.println("月份：：："+monthrrrr);
				      
				    aa.set(Calendar.YEAR, yearrrrrrrr);  
				    aa.set(Calendar.MONTH, monthrrrr-1);  
				    aa.set(Calendar.DATE, 1);//把日期设置为当月第一天  
				    aa.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天  
				    int maxDate = aa.get(Calendar.DATE);
				    
				    System.out.println("这个月   有       "+maxDate+"   天");
					
					String start= yearrrrrrrr+"-"+monthrrrr+"-"+"01";
					String end= yearrrrrrrr+"-"+monthrrrr+"-"+maxDate;
					
					System.out.println(start+"::::::"+end);
				    
					
					
					//nowdate 获取到当前的 年月 以及 这个月几天
					String nowdate = yearrrrrrrr+"-"+monthrrrr;
					System.out.println("年+月：：："+nowdate);
					
					JSONArray jsonArray = new JSONArray();
					
					if (StringUtils.isNotEmpty(nowdate)) {
						
						
						//封装到 newdocproparavo中
						NewDocProblemParaVO  suchbean = new NewDocProblemParaVO();
						
						
						
						
					
						// 设置分页及查询记录数
						Page page = new Page(request.getInt("pageIndex"), request.getInt("pageSize"));
					//	page.setOrderProp(Order.DESC("a.CONSENROLID"));
						
						
						//TODO  wujiajun  count的 查询
						
						JSONObject pageJson = new JSONObject();
						
						List<NewDocProblem>  results=new ArrayList<NewDocProblem>();
						
						

							
						results = newDoctorService.queryListEveryDay(start,end);

						
					
						
						page.setTotalItems(results.size());
						pageJson.put("pageIndex", page.getPageNo());
						pageJson.put("pageSize", page.getPageSize());
						pageJson.put("totalItems", page.getTotalItems());
						pageJson.put("totalPages", page.getTotalPages());
						pageJson.put("nextPage", page.getNextPage());
						
						
						//拿分页 （把结果集的 list 数据 拿出page的页数）
						List<NewDocProblem>  backlist = new ArrayList<NewDocProblem>();
				//		System.out.println( page.getPageNo()*page.getPageSize()+"页数++++=");
						
						
						
						//a 是  从第几页开始
						int a=page.getPageNo()*page.getPageSize();
						for (int i = page.getPageNo()*page.getPageSize(); i < a+page.getPageSize(); i++) {
							
							if(i>page.getTotalItems()-1){
								break;
							}else{
							
							backlist.add(results.get(i));
							}
						}
						
						
						//END
						

						//results    转换  json
						net.sf.json.JSONArray  list = net.sf.json.JSONArray.fromObject(backlist);
						
			
						JSONObject dataJson = new JSONObject();
						
					    dataJson.put("page", pageJson);
						dataJson.put("list", list);

						System.out.println("返回：：：：：：：：：：");
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
	 * 统计一个月每天  渠道的 接入量
	 * @param request
	 * @return
	 */
public HttpResponseContext listByChannel(HttpRequestContext request) {
		
		
				try {
					
					
					 Calendar aa = Calendar.getInstance();  
				      int yearrrrrrrr= aa.get(Calendar.YEAR);
				      int monthrrrr= aa.get(Calendar.MONTH)+1;
			     	 System.out.println("年份：：："+yearrrrrrrr);
				      
			    	 System.out.println("月份：：："+monthrrrr);
				      
				    aa.set(Calendar.YEAR, yearrrrrrrr);  
				    aa.set(Calendar.MONTH, monthrrrr-1);  
				    aa.set(Calendar.DATE, 1);//把日期设置为当月第一天  
				    aa.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天  
				    int maxDate = aa.get(Calendar.DATE);
				    
				    System.out.println("这个月   有       "+maxDate+"   天");
					
					
				    
					
					
					//nowdate 获取到当前的 年月 以及 这个月几天
					String nowdate = yearrrrrrrr+"-"+monthrrrr;
					System.out.println("年+月：：："+nowdate);
					
					JSONArray jsonArray = new JSONArray();
					
					if (StringUtils.isNotEmpty(nowdate)) {
						
						
						//封装到 newdocproparavo中
						NewDocProblemParaVO  suchbean = new NewDocProblemParaVO();
						
						
						
						
					
						// 设置分页及查询记录数
						Page page = new Page(request.getInt("pageIndex"), request.getInt("pageSize"));
					//	page.setOrderProp(Order.DESC("a.CONSENROLID"));
						
						
						//TODO  wujiajun  count的 查询
						
						JSONObject pageJson = new JSONObject();
						List<NewDocProblem>  suchresult=null;
						List<NewDocProblem>  results=new ArrayList<NewDocProblem>();
						
						
						for (int i = 0; i < maxDate; i++) {
							
			
							
							suchresult = newDoctorService.queryOneDateChannel(nowdate+"-"+(i+1),i+1);
						
							results.add(suchresult.get(0));
						}
						
						
					
						
						page.setTotalItems(results.size());
						pageJson.put("pageIndex", page.getPageNo());
						pageJson.put("pageSize", page.getPageSize());
						pageJson.put("totalItems", page.getTotalItems());
						pageJson.put("totalPages", page.getTotalPages());
						pageJson.put("nextPage", page.getNextPage());
						
						
						//拿分页 （把结果集的 list 数据 拿出page的页数）
						List<NewDocProblem>  backlist = new ArrayList<NewDocProblem>();
				//		System.out.println( page.getPageNo()*page.getPageSize()+"页数++++=");
						
						
						
						//a 是  从第几页开始
						int a=page.getPageNo()*page.getPageSize();
						for (int i = page.getPageNo()*page.getPageSize(); i < a+page.getPageSize(); i++) {
							
							if(i>page.getTotalItems()-1){
								break;
							}else{
							
							backlist.add(results.get(i));
							}
						}
						
						
						//END
						

						//results    转换  json
						net.sf.json.JSONArray  list = net.sf.json.JSONArray.fromObject(backlist);
						
			
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
	 * 
	 * @param 医生总数据  用省份城市查询数据
	 * @return
	 */
public HttpResponseContext getNewDoctorMessage(HttpRequestContext request) {
		
		//1.获取页面的数据     查询数据  和   分页数据
		//2.把数据 放到service 中 查询结果  （结果封装）
		//3.数据显示到页面
		
		
				try {
					
					String  province = request.getParameter("jo1");  //省份
					String  city = request.getParameter("jo2");//城市
					String  hospital = request.getParameter("jo3");//医院
					String  department = request.getParameter("jo4");//科室
					
					String starttime = request.getParameter("starttimes");
					String endtime = request.getParameter("endtimes");
					
					
					JSONArray jsonArray = new JSONArray();
					
					if (StringUtils.isNotEmpty(starttime) && StringUtils.isNotEmpty(endtime)) {
						
						
						//封装到 newdocproparavo中
						NewDocProblemParaVO  suchbean = new NewDocProblemParaVO();
						
						
						suchbean.setStarttime(starttime);
						suchbean.setEndtime(endtime);
						
						
						if(StringUtils.isNotEmpty(province)){
							
						
							//查询
							suchbean.setProvince(province);
							
						}
						
						if(StringUtils.isNotEmpty(city)){
							
				
							//查询
							suchbean.setCity(city);
							
						}
						
						if(StringUtils.isNotEmpty(hospital)){

							//查询
							suchbean.setHospital(hospital);
						}
						
						if(StringUtils.isNotEmpty(department)){
	
							//查询
							suchbean.setDepartment(department);
							
						}
						
						
						
						
					
						// 设置分页及查询记录数
						Page page = new Page(request.getInt("pageIndex"), request.getInt("pageSize"));
					//	page.setOrderProp(Order.DESC("a.CONSENROLID"));
						
						
						//TODO  wujiajun  count的 查询
						
						JSONObject pageJson = new JSONObject();
						
						
						
						
						// 查询记录集  （所有数据）
						List<NewDocProblem>  results = newDoctorService.queryNewDoctorList(suchbean);
					
						
						page.setTotalItems(results.size());
						pageJson.put("pageIndex", page.getPageNo());
						pageJson.put("pageSize", page.getPageSize());
						pageJson.put("totalItems", page.getTotalItems());
						pageJson.put("totalPages", page.getTotalPages());
						pageJson.put("nextPage", page.getNextPage());
						
						
						//拿分页 （把结果集的 list 数据 拿出page的页数）
						List<NewDocProblem>  backlist = new ArrayList<NewDocProblem>();
				//		System.out.println( page.getPageNo()*page.getPageSize()+"页数++++=");
						
						//a 是  从第几页开始
						int a=page.getPageNo()*page.getPageSize();
						for (int i = page.getPageNo()*page.getPageSize(); i < a+page.getPageSize(); i++) {
							
							if(i>page.getTotalItems()-1){
								break;
							}else{
							
							backlist.add(results.get(i));
							}
						}
						
						
						//END
						

						//results    转换  json
						net.sf.json.JSONArray  list = net.sf.json.JSONArray.fromObject(backlist);
						
			
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
	 * 医生分科室数据查询   一二级科室查
	 * @author WUJIAJUN
	 * @param request
	 * @return
	 */
	public HttpResponseContext getAllMessage(HttpRequestContext request) {
		
		
		//TODO wujiajun
		  
        //1.	只有一级科室的话   吧所有的 val  id 取出来  sql 用  in
	     Map<String, Map<String, String>> keshiname=DBCache.smallDepartmentListBySn;
	     
	     //2.   如果二级科室一级科室都 有值得话！      那么 就直接查二级科室     
	   
	     
	     List<String> twodeptids = new ArrayList<String>();
	     for (String  val : keshiname.keySet()) {
	    	 
	    	 if(val.equals(request.getParameter("onedept"))){
	    		 
	    		 Map<String, String> erjikeshi = keshiname.get(val);
	    		 
	    		 for (String key : erjikeshi.keySet()) {
				
					twodeptids.add(key);
				}
		}
	    	 }
	     
	     
	     
	     
	     
	     

	     net.sf.json.JSONObject jsonsss=   net.sf.json.JSONObject.fromObject(keshiname);
	    // System.out.println("map是：：：：：：：："+jsonsss.toString());
		//END
		
		
		
		
		//1.获取页面的数据     查询数据  和   分页数据
				//2.把数据 放到service 中 查询结果  （结果封装）
				//3.数据显示到页面
				
				
		
	
				
						try {
							
							String  onedept = request.getParameter("onedept");  //一级科室
							String  twodept = request.getParameter("twodept");//二级科室
							
							String starttime = request.getParameter("starttimes");
							String endtime = request.getParameter("endtimes");
							
							
							JSONArray jsonArray = new JSONArray();
							
							if (StringUtils.isNotEmpty(starttime) && StringUtils.isNotEmpty(endtime)) {
								
								
								//封装到 newdocproparavo中
								NewDocProblemParaVO  suchbean = new NewDocProblemParaVO();
								
								
								suchbean.setStarttime(starttime);
								suchbean.setEndtime(endtime);
								
								
								if(StringUtils.isNotEmpty(onedept)){
									
							
									//查询
									suchbean.setOnedept(onedept);;
									
								}
								
								if(StringUtils.isNotEmpty(twodept)){
									
							
									//查询
									suchbean.setTwodept(twodept);
									
								}
								
								
								
							
								
							
								// 设置分页及查询记录数
								Page page = new Page(request.getInt("pageIndex"), request.getInt("pageSize"));
							//	page.setOrderProp(Order.DESC("a.CONSENROLID"));
								
								
								//TODO  wujiajun  count的 查询
								
								JSONObject pageJson = new JSONObject();
								
								
								
								
								// 查询记录集  （所有数据）
								List<NewDocProblem>  results = newDoctorService.queryNewALLDoctorList(suchbean, twodeptids) ;
							
								
								page.setTotalItems(results.size());
								pageJson.put("pageIndex", page.getPageNo());
								pageJson.put("pageSize", page.getPageSize());
								pageJson.put("totalItems", page.getTotalItems());
								pageJson.put("totalPages", page.getTotalPages());
								pageJson.put("nextPage", page.getNextPage());

								//拿分页 （把结果集的 list 数据 拿出page的页数）
								List<NewDocProblem>  backlist = new ArrayList<NewDocProblem>();
							
								
								//a 是  从第几页开始
								int a=page.getPageNo()*page.getPageSize();
								for (int i = page.getPageNo()*page.getPageSize(); i < a+page.getPageSize(); i++) {
									
									if(i>page.getTotalItems()-1){
										break;
									}else{
									
									backlist.add(results.get(i));
									}
								}
								
								
								//END
								
								
								
								
								
								//TODO WUJIAJUN
								//backlist  循环   获取到 的 二级dept 调用远程接口 查询 一级科室  然后从新封装

								JSONObject jsonObj = new JSONObject();

		
								//把一级科室  装入json
								List<NewDocProblem>  endlist = new ArrayList<NewDocProblem>();
								
								for (NewDocProblem newDocProblem : backlist) {
									
									jsonObj.put("deptId", newDocProblem.getStandarddeptid());
									net.sf.json.JSONObject  back  =net.sf.json.JSONObject.fromObject(postService.getBigAndSmallDepartmentBySmallDepartmentId(jsonObj.toString())) ;
									int code = back.getInt("Code");
									int proid = 0;
									if(code > 0 ){
										net.sf.json.JSONArray  rts= back.getJSONArray("Result");
										if(rts.size() == 0){
											return new HttpResponseContext(new JSONObject().put("result", jsonArray).put("totalProperty", 0));
										}
										System.out.println("查询的 结果  post：：：：："+   	net.sf.json.JSONObject.fromObject(rts.getString(0)).getString("bigDeptName")  );
										
										newDocProblem.setPostonedeptname(	net.sf.json.JSONObject.fromObject(rts.getString(0)).getString("bigDeptName") );
										//deptName 也set进去即可
										newDocProblem.setDeptname(net.sf.json.JSONObject.fromObject(rts.getString(0)).getString("deptName"));
										
								
									}else{
										return new HttpResponseContext(new JSONObject().put("result", jsonArray).put("totalProperty", 0));
									}
									
								}
								
								
								
								
					
								
								

								//results    转换  json
								net.sf.json.JSONArray  list = net.sf.json.JSONArray.fromObject(backlist);
								
					
								JSONObject dataJson = new JSONObject();
								
								System.out.println("JSON：：：：：：：：：：：："+list.toString());
								
								
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
	 * 
	 * @param 获取医生数据    suchDownDocMSGJEE.HTML
	 * @return
	 */
public HttpResponseContext suchDownDocMSG(HttpRequestContext request) {
		
	System.out.println("come in...");
		
		
				try {
					
					String  province = request.getParameter("jo1");  //省份
					String  city = request.getParameter("jo2");//城市
					String  hospital = request.getParameter("jo3");//医院
				
					String  doctorName = request.getParameter("doctorname");//医生 姓名
					
					
					String starttime = request.getParameter("starttimes");
					String endtime = request.getParameter("endtimes");
					
					
					JSONArray jsonArray = new JSONArray();
					
					if (StringUtils.isNotEmpty(starttime) && StringUtils.isNotEmpty(endtime)) {
						
						
						//封装到 newdocproparavo中
						NewDocProblemParaVO  suchbean = new NewDocProblemParaVO();
						
						
						suchbean.setStarttime(starttime);
						suchbean.setEndtime(endtime);
						
						
						if(StringUtils.isNotEmpty(province)){
							
						
							//查询
							suchbean.setProvince(province);
							
						}
						
						if(StringUtils.isNotEmpty(city)){
							
				
							//查询
							suchbean.setCity(city);
							
						}
						
						if(StringUtils.isNotEmpty(hospital)){

							//查询
							suchbean.setHospital(hospital);
						}
						
		
						
						
						
						
					
						// 设置分页及查询记录数
						Page page = new Page(request.getInt("pageIndex"), request.getInt("pageSize"));
					//	page.setOrderProp(Order.DESC("a.CONSENROLID"));
						
						
						//TODO  wujiajun  count的 查询
						
						JSONObject pageJson = new JSONObject();
						
						
						int   startrow=request.getInt("pageIndex");
						int endrow= startrow+request.getInt("pageSize");
						
			
						int count = newDoctorService.suchDownDocMSGcount(starttime,endtime,province,city,hospital,doctorName,startrow,endrow);
					
						
						page.setTotalItems(count);
						pageJson.put("pageIndex", page.getPageNo());
						pageJson.put("pageSize", page.getPageSize());
						pageJson.put("totalItems", page.getTotalItems());
						pageJson.put("totalPages", page.getTotalPages());
						pageJson.put("nextPage", page.getNextPage());
						
						
						 List<SuchDownDocMSGVo> listback= newDoctorService.suchDownDocMSGlist(starttime,endtime,province,city,hospital,doctorName,startrow,endrow);
						
					
						

						//results    转换  json
						net.sf.json.JSONArray  list = net.sf.json.JSONArray.fromObject(listback);
						
			
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
 * 
 * @param 下载 表格    suchDownDocMSGJEE.HTML
 * @return
 */
public  HttpResponseContext  toDownDocMSG(HttpRequestContext request) {
	
System.out.println("come in...DOWN");
	
	
			try {
				
				
				
				String  province = request.getParameter("jo1");  //省份
				String  city = request.getParameter("jo2");//城市
				String  hospital = request.getParameter("jo3");//医院
			
				String  doctorName = request.getParameter("doctorname");//医生 姓名
				
				
				String starttime = request.getParameter("starttimes");
				String endtime = request.getParameter("endtimes");
				
				
				JSONArray jsonArray = new JSONArray();
				
				if (StringUtils.isNotEmpty(starttime) && StringUtils.isNotEmpty(endtime)) {
					
					
					//封装到 newdocproparavo中
					NewDocProblemParaVO  suchbean = new NewDocProblemParaVO();
					
					
					suchbean.setStarttime(starttime);
					suchbean.setEndtime(endtime);
					
					
					if(StringUtils.isNotEmpty(province)){
						
					
						//查询
						suchbean.setProvince(province);
						
					}
					
					if(StringUtils.isNotEmpty(city)){
						
			
						//查询
						suchbean.setCity(city);
						
					}
					
					if(StringUtils.isNotEmpty(hospital)){

						//查询
						suchbean.setHospital(hospital);
					}
					
	
					
					
					
					
				
					// 设置分页及查询记录数
					Page page = new Page(request.getInt("pageIndex"), request.getInt("pageSize"));
				//	page.setOrderProp(Order.DESC("a.CONSENROLID"));
					
					
					//TODO  wujiajun  count的 查询
					
					JSONObject pageJson = new JSONObject();
					
					
					int   startrow=request.getInt("pageIndex");
					int endrow= startrow+request.getInt("pageSize");
					
	
					
					
					 List<SuchDownDocMSGVo> listback= newDoctorService.suchDownDocMSGlist(starttime,endtime,province,city,hospital,doctorName,startrow,endrow);
					
					 //把这个listback  写成  excel
					 
					 this.xlsDto2Excel(listback);
					 
					 
					 

				       Map<String, String>  para = new HashMap<String, String>();
				       para.put("Api", "WBJ");
				       para.put("File", "true");
				       para.put("filename", "pldrxkxxmb.xls");
				       para.put("param", "{hosid:1024727}");
				       
				       String  back = PostClient.dopost(System.getProperty("user.dir")+"\\pldrxkxxmb.xls", para);
				       
				       net.sf.json.JSONObject json = net.sf.json.JSONObject.fromObject(back);
				       String filename= (String) json.get("Uri");
					 
					 System.out.println(filename);
					
					return new HttpResponseContext(new JSONObject().put("downUrl",filename ));
				}
				return new HttpResponseContext(new JSONObject().put("code", -1).put("message", "生成EXCEL失败"));
			} catch (Exception e) {
				e.printStackTrace();
				Logger.get().error("com.yihu.myt", LogBody.me().set(e));
				return new HttpResponseContext(e);
			}

		

}






public  void xlsDto2Excel(List<SuchDownDocMSGVo> xls) throws Exception {  
       // 获取总列数  
       int CountColumnNum =8;  
       // 创建Excel文档  
       HSSFWorkbook hwb = new HSSFWorkbook();  
       SuchDownDocMSGVo vo = null;  
       // sheet 对应一个工作页  
       HSSFSheet sheet = hwb.createSheet("咨询数据");  
       HSSFRow firstrow = sheet.createRow(0); // 下标为0的行开始  
       HSSFCell[] firstcell = new HSSFCell[CountColumnNum];  
       String[] names = new String[CountColumnNum];  
       names[0] = "医院";  
       names[1] = "科室";  
       names[2] = "医生";  
       names[3] = "指定咨询 总数";  
       names[4] = "指定咨询 完成数";  
       names[5] = "指定免费咨询总数";
       names[6] = "指定免费咨询完成数";
       names[7] = "公共咨询完成数";
       
     
       for (int j = 0; j < CountColumnNum; j++) {  
           firstcell[j] = firstrow.createCell(j);  
           firstcell[j].setCellValue(new HSSFRichTextString(names[j]));  
       }  
       for (int i = 0; i < xls.size(); i++) {  
           // 创建一行  
           HSSFRow row = sheet.createRow(i + 1);  
           // 得到要插入的每一条记录  
           vo = xls.get(i);  
           for (int colu = 0; colu <= 8; colu++) {  
               // 在一行内循环  
               HSSFCell xh = row.createCell(0);  
               xh.setCellValue(vo.getHosname());  
               HSSFCell xm = row.createCell(1);  
               xm.setCellValue(vo.getDeptname());  
               HSSFCell yxsmc = row.createCell(2);  
               yxsmc.setCellValue(vo.getDoctorname());  
               HSSFCell kcm = row.createCell(3);  
               kcm.setCellValue(vo.getNum1());  
               HSSFCell cj = row.createCell(4);  
               cj.setCellValue(vo.getNum2());  
               
               
               HSSFCell num3 = row.createCell(5);  
               num3.setCellValue(vo.getNum3());  
               
               HSSFCell num4 = row.createCell(6);  
               num4.setCellValue(vo.getNum4());  
               
               HSSFCell num5 = row.createCell(7);  
               num5.setCellValue(vo.getNum5());  
               
               
               
           }  
       }  
       
       
       // 创建文件输出流，准备输出电子表格  
       OutputStream out = new FileOutputStream(System.getProperty("user.dir")+"\\pldrxkxxmb.xls");  
       hwb.write(out);  
       out.close();  
       
       
       
       
       
       
       
       
       System.out.println("数据库导出成功");  
   }  
	
	
	
	
	
	
	
	
	
	
	
}
