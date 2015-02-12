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
 * ͳ��ҽ������ 
 * */
public class NewStatisticsAction {

	private static IBookenrolService bookenrolService = Ioc
			.get(IBookenrolService.class); // ��ѯ��ˮ����ӿ�
	private static IArraworkService arraworkService = Ioc
			.get(IArraworkService.class); // �Ű����ӿ�
	private static IPauseService pauseService = Ioc.get(IPauseService.class); // ͣ�����ӿ�
	private static IDoctorInfoService doctorInfoService = Ioc.get(IDoctorInfoService.class); // ͣ�����ӿ�
	private static IPostService postService = Ioc
			.get(IPostService.class); 
	
	
	
	private static INewDoctorService newDoctorService = Ioc
			.get(INewDoctorService.class); 
	
	
	

	/**
	 * ͳ��һ���� �û� ��ҽ���Ļظ���
	 * @param request
	 * @return
	 */
public HttpResponseContext listEveryDay(HttpRequestContext request) {
		
		
				try {
					
					
					 Calendar aa = Calendar.getInstance();  
				      int yearrrrrrrr= aa.get(Calendar.YEAR);
				      int monthrrrr= aa.get(Calendar.MONTH);
			     	 System.out.println("��ݣ�����"+yearrrrrrrr);
				      
			    	 System.out.println("�·ݣ�����"+monthrrrr);
				      
				    aa.set(Calendar.YEAR, yearrrrrrrr);  
				    aa.set(Calendar.MONTH, monthrrrr-1);  
				    aa.set(Calendar.DATE, 1);//����������Ϊ���µ�һ��  
				    aa.roll(Calendar.DATE, -1);//���ڻع�һ�죬Ҳ�������һ��  
				    int maxDate = aa.get(Calendar.DATE);
				    
				    System.out.println("�����   ��       "+maxDate+"   ��");
					
					String start= yearrrrrrrr+"-"+monthrrrr+"-"+"01";
					String end= yearrrrrrrr+"-"+monthrrrr+"-"+maxDate;
					
					System.out.println(start+"::::::"+end);
				    
					
					
					//nowdate ��ȡ����ǰ�� ���� �Լ� ����¼���
					String nowdate = yearrrrrrrr+"-"+monthrrrr;
					System.out.println("��+�£�����"+nowdate);
					
					JSONArray jsonArray = new JSONArray();
					
					if (StringUtils.isNotEmpty(nowdate)) {
						
						
						//��װ�� newdocproparavo��
						NewDocProblemParaVO  suchbean = new NewDocProblemParaVO();
						
						
						
						
					
						// ���÷�ҳ����ѯ��¼��
						Page page = new Page(request.getInt("pageIndex"), request.getInt("pageSize"));
					//	page.setOrderProp(Order.DESC("a.CONSENROLID"));
						
						
						//TODO  wujiajun  count�� ��ѯ
						
						JSONObject pageJson = new JSONObject();
						
						List<NewDocProblem>  results=new ArrayList<NewDocProblem>();
						
						

							
						results = newDoctorService.queryListEveryDay(start,end);

						
					
						
						page.setTotalItems(results.size());
						pageJson.put("pageIndex", page.getPageNo());
						pageJson.put("pageSize", page.getPageSize());
						pageJson.put("totalItems", page.getTotalItems());
						pageJson.put("totalPages", page.getTotalPages());
						pageJson.put("nextPage", page.getNextPage());
						
						
						//�÷�ҳ ���ѽ������ list ���� �ó�page��ҳ����
						List<NewDocProblem>  backlist = new ArrayList<NewDocProblem>();
				//		System.out.println( page.getPageNo()*page.getPageSize()+"ҳ��++++=");
						
						
						
						//a ��  �ӵڼ�ҳ��ʼ
						int a=page.getPageNo()*page.getPageSize();
						for (int i = page.getPageNo()*page.getPageSize(); i < a+page.getPageSize(); i++) {
							
							if(i>page.getTotalItems()-1){
								break;
							}else{
							
							backlist.add(results.get(i));
							}
						}
						
						
						//END
						

						//results    ת��  json
						net.sf.json.JSONArray  list = net.sf.json.JSONArray.fromObject(backlist);
						
			
						JSONObject dataJson = new JSONObject();
						
					    dataJson.put("page", pageJson);
						dataJson.put("list", list);

						System.out.println("���أ�������������������");
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
	 * ͳ��һ����ÿ��  ������ ������
	 * @param request
	 * @return
	 */
public HttpResponseContext listByChannel(HttpRequestContext request) {
		
		
				try {
					
					
					 Calendar aa = Calendar.getInstance();  
				      int yearrrrrrrr= aa.get(Calendar.YEAR);
				      int monthrrrr= aa.get(Calendar.MONTH)+1;
			     	 System.out.println("��ݣ�����"+yearrrrrrrr);
				      
			    	 System.out.println("�·ݣ�����"+monthrrrr);
				      
				    aa.set(Calendar.YEAR, yearrrrrrrr);  
				    aa.set(Calendar.MONTH, monthrrrr-1);  
				    aa.set(Calendar.DATE, 1);//����������Ϊ���µ�һ��  
				    aa.roll(Calendar.DATE, -1);//���ڻع�һ�죬Ҳ�������һ��  
				    int maxDate = aa.get(Calendar.DATE);
				    
				    System.out.println("�����   ��       "+maxDate+"   ��");
					
					
				    
					
					
					//nowdate ��ȡ����ǰ�� ���� �Լ� ����¼���
					String nowdate = yearrrrrrrr+"-"+monthrrrr;
					System.out.println("��+�£�����"+nowdate);
					
					JSONArray jsonArray = new JSONArray();
					
					if (StringUtils.isNotEmpty(nowdate)) {
						
						
						//��װ�� newdocproparavo��
						NewDocProblemParaVO  suchbean = new NewDocProblemParaVO();
						
						
						
						
					
						// ���÷�ҳ����ѯ��¼��
						Page page = new Page(request.getInt("pageIndex"), request.getInt("pageSize"));
					//	page.setOrderProp(Order.DESC("a.CONSENROLID"));
						
						
						//TODO  wujiajun  count�� ��ѯ
						
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
						
						
						//�÷�ҳ ���ѽ������ list ���� �ó�page��ҳ����
						List<NewDocProblem>  backlist = new ArrayList<NewDocProblem>();
				//		System.out.println( page.getPageNo()*page.getPageSize()+"ҳ��++++=");
						
						
						
						//a ��  �ӵڼ�ҳ��ʼ
						int a=page.getPageNo()*page.getPageSize();
						for (int i = page.getPageNo()*page.getPageSize(); i < a+page.getPageSize(); i++) {
							
							if(i>page.getTotalItems()-1){
								break;
							}else{
							
							backlist.add(results.get(i));
							}
						}
						
						
						//END
						

						//results    ת��  json
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
	 * @param ҽ��������  ��ʡ�ݳ��в�ѯ����
	 * @return
	 */
public HttpResponseContext getNewDoctorMessage(HttpRequestContext request) {
		
		//1.��ȡҳ�������     ��ѯ����  ��   ��ҳ����
		//2.������ �ŵ�service �� ��ѯ���  �������װ��
		//3.������ʾ��ҳ��
		
		
				try {
					
					String  province = request.getParameter("jo1");  //ʡ��
					String  city = request.getParameter("jo2");//����
					String  hospital = request.getParameter("jo3");//ҽԺ
					String  department = request.getParameter("jo4");//����
					
					String starttime = request.getParameter("starttimes");
					String endtime = request.getParameter("endtimes");
					
					
					JSONArray jsonArray = new JSONArray();
					
					if (StringUtils.isNotEmpty(starttime) && StringUtils.isNotEmpty(endtime)) {
						
						
						//��װ�� newdocproparavo��
						NewDocProblemParaVO  suchbean = new NewDocProblemParaVO();
						
						
						suchbean.setStarttime(starttime);
						suchbean.setEndtime(endtime);
						
						
						if(StringUtils.isNotEmpty(province)){
							
						
							//��ѯ
							suchbean.setProvince(province);
							
						}
						
						if(StringUtils.isNotEmpty(city)){
							
				
							//��ѯ
							suchbean.setCity(city);
							
						}
						
						if(StringUtils.isNotEmpty(hospital)){

							//��ѯ
							suchbean.setHospital(hospital);
						}
						
						if(StringUtils.isNotEmpty(department)){
	
							//��ѯ
							suchbean.setDepartment(department);
							
						}
						
						
						
						
					
						// ���÷�ҳ����ѯ��¼��
						Page page = new Page(request.getInt("pageIndex"), request.getInt("pageSize"));
					//	page.setOrderProp(Order.DESC("a.CONSENROLID"));
						
						
						//TODO  wujiajun  count�� ��ѯ
						
						JSONObject pageJson = new JSONObject();
						
						
						
						
						// ��ѯ��¼��  ���������ݣ�
						List<NewDocProblem>  results = newDoctorService.queryNewDoctorList(suchbean);
					
						
						page.setTotalItems(results.size());
						pageJson.put("pageIndex", page.getPageNo());
						pageJson.put("pageSize", page.getPageSize());
						pageJson.put("totalItems", page.getTotalItems());
						pageJson.put("totalPages", page.getTotalPages());
						pageJson.put("nextPage", page.getNextPage());
						
						
						//�÷�ҳ ���ѽ������ list ���� �ó�page��ҳ����
						List<NewDocProblem>  backlist = new ArrayList<NewDocProblem>();
				//		System.out.println( page.getPageNo()*page.getPageSize()+"ҳ��++++=");
						
						//a ��  �ӵڼ�ҳ��ʼ
						int a=page.getPageNo()*page.getPageSize();
						for (int i = page.getPageNo()*page.getPageSize(); i < a+page.getPageSize(); i++) {
							
							if(i>page.getTotalItems()-1){
								break;
							}else{
							
							backlist.add(results.get(i));
							}
						}
						
						
						//END
						

						//results    ת��  json
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
	 * ҽ���ֿ������ݲ�ѯ   һ�������Ҳ�
	 * @author WUJIAJUN
	 * @param request
	 * @return
	 */
	public HttpResponseContext getAllMessage(HttpRequestContext request) {
		
		
		//TODO wujiajun
		  
        //1.	ֻ��һ�����ҵĻ�   �����е� val  id ȡ����  sql ��  in
	     Map<String, Map<String, String>> keshiname=DBCache.smallDepartmentListBySn;
	     
	     //2.   �����������һ�����Ҷ� ��ֵ�û���      ��ô ��ֱ�Ӳ��������     
	   
	     
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
	    // System.out.println("map�ǣ���������������"+jsonsss.toString());
		//END
		
		
		
		
		//1.��ȡҳ�������     ��ѯ����  ��   ��ҳ����
				//2.������ �ŵ�service �� ��ѯ���  �������װ��
				//3.������ʾ��ҳ��
				
				
		
	
				
						try {
							
							String  onedept = request.getParameter("onedept");  //һ������
							String  twodept = request.getParameter("twodept");//��������
							
							String starttime = request.getParameter("starttimes");
							String endtime = request.getParameter("endtimes");
							
							
							JSONArray jsonArray = new JSONArray();
							
							if (StringUtils.isNotEmpty(starttime) && StringUtils.isNotEmpty(endtime)) {
								
								
								//��װ�� newdocproparavo��
								NewDocProblemParaVO  suchbean = new NewDocProblemParaVO();
								
								
								suchbean.setStarttime(starttime);
								suchbean.setEndtime(endtime);
								
								
								if(StringUtils.isNotEmpty(onedept)){
									
							
									//��ѯ
									suchbean.setOnedept(onedept);;
									
								}
								
								if(StringUtils.isNotEmpty(twodept)){
									
							
									//��ѯ
									suchbean.setTwodept(twodept);
									
								}
								
								
								
							
								
							
								// ���÷�ҳ����ѯ��¼��
								Page page = new Page(request.getInt("pageIndex"), request.getInt("pageSize"));
							//	page.setOrderProp(Order.DESC("a.CONSENROLID"));
								
								
								//TODO  wujiajun  count�� ��ѯ
								
								JSONObject pageJson = new JSONObject();
								
								
								
								
								// ��ѯ��¼��  ���������ݣ�
								List<NewDocProblem>  results = newDoctorService.queryNewALLDoctorList(suchbean, twodeptids) ;
							
								
								page.setTotalItems(results.size());
								pageJson.put("pageIndex", page.getPageNo());
								pageJson.put("pageSize", page.getPageSize());
								pageJson.put("totalItems", page.getTotalItems());
								pageJson.put("totalPages", page.getTotalPages());
								pageJson.put("nextPage", page.getNextPage());

								//�÷�ҳ ���ѽ������ list ���� �ó�page��ҳ����
								List<NewDocProblem>  backlist = new ArrayList<NewDocProblem>();
							
								
								//a ��  �ӵڼ�ҳ��ʼ
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
								//backlist  ѭ��   ��ȡ�� �� ����dept ����Զ�̽ӿ� ��ѯ һ������  Ȼ����·�װ

								JSONObject jsonObj = new JSONObject();

		
								//��һ������  װ��json
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
										System.out.println("��ѯ�� ���  post����������"+   	net.sf.json.JSONObject.fromObject(rts.getString(0)).getString("bigDeptName")  );
										
										newDocProblem.setPostonedeptname(	net.sf.json.JSONObject.fromObject(rts.getString(0)).getString("bigDeptName") );
										//deptName Ҳset��ȥ����
										newDocProblem.setDeptname(net.sf.json.JSONObject.fromObject(rts.getString(0)).getString("deptName"));
										
								
									}else{
										return new HttpResponseContext(new JSONObject().put("result", jsonArray).put("totalProperty", 0));
									}
									
								}
								
								
								
								
					
								
								

								//results    ת��  json
								net.sf.json.JSONArray  list = net.sf.json.JSONArray.fromObject(backlist);
								
					
								JSONObject dataJson = new JSONObject();
								
								System.out.println("JSON������������������������"+list.toString());
								
								
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
	 * @param ��ȡҽ������    suchDownDocMSGJEE.HTML
	 * @return
	 */
public HttpResponseContext suchDownDocMSG(HttpRequestContext request) {
		
	System.out.println("come in...");
		
		
				try {
					
					String  province = request.getParameter("jo1");  //ʡ��
					String  city = request.getParameter("jo2");//����
					String  hospital = request.getParameter("jo3");//ҽԺ
				
					String  doctorName = request.getParameter("doctorname");//ҽ�� ����
					
					
					String starttime = request.getParameter("starttimes");
					String endtime = request.getParameter("endtimes");
					
					
					JSONArray jsonArray = new JSONArray();
					
					if (StringUtils.isNotEmpty(starttime) && StringUtils.isNotEmpty(endtime)) {
						
						
						//��װ�� newdocproparavo��
						NewDocProblemParaVO  suchbean = new NewDocProblemParaVO();
						
						
						suchbean.setStarttime(starttime);
						suchbean.setEndtime(endtime);
						
						
						if(StringUtils.isNotEmpty(province)){
							
						
							//��ѯ
							suchbean.setProvince(province);
							
						}
						
						if(StringUtils.isNotEmpty(city)){
							
				
							//��ѯ
							suchbean.setCity(city);
							
						}
						
						if(StringUtils.isNotEmpty(hospital)){

							//��ѯ
							suchbean.setHospital(hospital);
						}
						
		
						
						
						
						
					
						// ���÷�ҳ����ѯ��¼��
						Page page = new Page(request.getInt("pageIndex"), request.getInt("pageSize"));
					//	page.setOrderProp(Order.DESC("a.CONSENROLID"));
						
						
						//TODO  wujiajun  count�� ��ѯ
						
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
						
					
						

						//results    ת��  json
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
 * @param ���� ����    suchDownDocMSGJEE.HTML
 * @return
 */
public  HttpResponseContext  toDownDocMSG(HttpRequestContext request) {
	
System.out.println("come in...DOWN");
	
	
			try {
				
				
				
				String  province = request.getParameter("jo1");  //ʡ��
				String  city = request.getParameter("jo2");//����
				String  hospital = request.getParameter("jo3");//ҽԺ
			
				String  doctorName = request.getParameter("doctorname");//ҽ�� ����
				
				
				String starttime = request.getParameter("starttimes");
				String endtime = request.getParameter("endtimes");
				
				
				JSONArray jsonArray = new JSONArray();
				
				if (StringUtils.isNotEmpty(starttime) && StringUtils.isNotEmpty(endtime)) {
					
					
					//��װ�� newdocproparavo��
					NewDocProblemParaVO  suchbean = new NewDocProblemParaVO();
					
					
					suchbean.setStarttime(starttime);
					suchbean.setEndtime(endtime);
					
					
					if(StringUtils.isNotEmpty(province)){
						
					
						//��ѯ
						suchbean.setProvince(province);
						
					}
					
					if(StringUtils.isNotEmpty(city)){
						
			
						//��ѯ
						suchbean.setCity(city);
						
					}
					
					if(StringUtils.isNotEmpty(hospital)){

						//��ѯ
						suchbean.setHospital(hospital);
					}
					
	
					
					
					
					
				
					// ���÷�ҳ����ѯ��¼��
					Page page = new Page(request.getInt("pageIndex"), request.getInt("pageSize"));
				//	page.setOrderProp(Order.DESC("a.CONSENROLID"));
					
					
					//TODO  wujiajun  count�� ��ѯ
					
					JSONObject pageJson = new JSONObject();
					
					
					int   startrow=request.getInt("pageIndex");
					int endrow= startrow+request.getInt("pageSize");
					
	
					
					
					 List<SuchDownDocMSGVo> listback= newDoctorService.suchDownDocMSGlist(starttime,endtime,province,city,hospital,doctorName,startrow,endrow);
					
					 //�����listback  д��  excel
					 
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
				return new HttpResponseContext(new JSONObject().put("code", -1).put("message", "����EXCELʧ��"));
			} catch (Exception e) {
				e.printStackTrace();
				Logger.get().error("com.yihu.myt", LogBody.me().set(e));
				return new HttpResponseContext(e);
			}

		

}






public  void xlsDto2Excel(List<SuchDownDocMSGVo> xls) throws Exception {  
       // ��ȡ������  
       int CountColumnNum =8;  
       // ����Excel�ĵ�  
       HSSFWorkbook hwb = new HSSFWorkbook();  
       SuchDownDocMSGVo vo = null;  
       // sheet ��Ӧһ������ҳ  
       HSSFSheet sheet = hwb.createSheet("��ѯ����");  
       HSSFRow firstrow = sheet.createRow(0); // �±�Ϊ0���п�ʼ  
       HSSFCell[] firstcell = new HSSFCell[CountColumnNum];  
       String[] names = new String[CountColumnNum];  
       names[0] = "ҽԺ";  
       names[1] = "����";  
       names[2] = "ҽ��";  
       names[3] = "ָ����ѯ ����";  
       names[4] = "ָ����ѯ �����";  
       names[5] = "ָ�������ѯ����";
       names[6] = "ָ�������ѯ�����";
       names[7] = "������ѯ�����";
       
     
       for (int j = 0; j < CountColumnNum; j++) {  
           firstcell[j] = firstrow.createCell(j);  
           firstcell[j].setCellValue(new HSSFRichTextString(names[j]));  
       }  
       for (int i = 0; i < xls.size(); i++) {  
           // ����һ��  
           HSSFRow row = sheet.createRow(i + 1);  
           // �õ�Ҫ�����ÿһ����¼  
           vo = xls.get(i);  
           for (int colu = 0; colu <= 8; colu++) {  
               // ��һ����ѭ��  
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
       
       
       // �����ļ��������׼��������ӱ���  
       OutputStream out = new FileOutputStream(System.getProperty("user.dir")+"\\pldrxkxxmb.xls");  
       hwb.write(out);  
       out.close();  
       
       
       
       
       
       
       
       
       System.out.println("���ݿ⵼���ɹ�");  
   }  
	
	
	
	
	
	
	
	
	
	
	
}