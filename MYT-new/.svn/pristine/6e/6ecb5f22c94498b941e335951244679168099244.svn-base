/**
 * 
 */
package com.yihu.main;

import net.sf.json.JSONObject;

import com.coreframework.remoting.reflect.Rpc;
import com.yihu.account.api.IAccountService;
import com.yihu.account.api.ReturnValueBean;
import com.yihu.baseinfo.api.DoctorServiceApi;
import com.yihu.myt.ConfigUtil;
import com.yihu.wsgw.api.InterfaceMessage;
import com.yihu.wsgw.api.WsUtil;

/**
 * @author Administrator
 *
 */
public class Test1 {

	public static class    User implements java.io.Serializable
	{
		private String name;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getMobile() {
			return mobile;
		}
		public void setMobile(String mobile) {
			this.mobile = mobile;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		private String mobile;
		private int age;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		try {
		/*	com.yihu.baseinfo.api.DoctorServiceApi doctorService= Rpc.get(
					DoctorServiceApi.class,
					ConfigUtil.getInstance().getUrl("url.baseinfo"));
			
			InterfaceMessage iMsg = new InterfaceMessage();
			JSONObject msgParam = new JSONObject();
			msgParam.put("feeTemplateId", 5397);
			iMsg.setParam(msgParam.toString());
			JSONObject dcPrice = JSONObject.fromObject(doctorService.queryFeeTemplateById(iMsg));*/
			com.yihu.account.api.IAccountService accountService = Rpc.get(
					IAccountService.class,
					ConfigUtil.getInstance().getUrl("url.account"));
			ReturnValueBean clrt = accountService.clearFrozen(11699867, 33530,
					"12", "01");
			System.out.print(clrt.getMessage());
			System.out.print(clrt.getCode());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	//	5397
		/**
		 * 接口方法返回值规范为只能返回JSON或者XML格式的字符串，并且必须带Code与Message节点
		 * 通过WsUtil.getRetVal方法可便捷组合返回值
		 *//*
		String ret=WsUtil.getRetVal(0, 10000, "成功");//只返回Code与Message的情况.参数1出参类型(0代表返回JSON，1代表返回XML)，参数2Code 参数3 Message (成功或失败的原因)
		*//**
		 * 以下输出结果{"Message":"成功","Code":1}
		 *//*
		System.out.println(ret);
		ret=WsUtil.getRetVal(1, 10000, "成功");//只返回Code与Message的情况.参数1出参类型(0代表返回JSON，1代表返回XML)，参数2Code 参数3 Message (成功或失败的原因)
		*//**
		 * 以下输出结果<?xml version="1.0" encoding="UTF-8"?><OutPut><Code>1</Code><Message>成功</Message></OutPut>
		 *//*
		System.out.println(ret);
		
		//组合JSON结果与Code  Message节点
		net.sf.json.JSONObject json=new net.sf.json.JSONObject();
		json.put("aaa", "aaaValue");
		json.put("bbb", "bbbValue");
		*//**
		 * 以下输出结果
		 * {"aaa":"aaaValue","bbb":"bbbValue","Code":1,"Message":"成功"}
		 *//*
	//	ret=WsUtil.getRetVal(1, "成功",json );//返回Code与Message外加处理结果的情况。参数1 Code  参数2 Message  参数3  结果集json
		
		System.out.println(ret);
		
		
		//组合java  bean结果与Code  Message节点
		
		*//**
		 * 以下输出结果
		 *{"Code":1,"Message":"成功","Age":22,"Mobile":"1000000","Name":"测试"}
		 *//*
		User  user=new User();
		user.setAge(22);
		user.setMobile("1000000");
		user.setName("测试");
		ret=WsUtil.getRetVal(0,1, "成功",user );//返回Code与Message外加处理结果的情况。参数1 出参类型，参数2 Code  参数3 Message  参数4  结果集bean
		
		System.out.println(ret);
		
		
		*//**
		 * 以下输出结果
		 *<?xml version="1.0" encoding="UTF-8"?><OutPut><Code>1</Code><Message>成功</Message><Age>22</Age><Mobile>1000000</Mobile><Name>测试</Name></OutPut>
		 *//*
		ret=WsUtil.getRetVal(1,1, "成功",user );////返回Code与Message外加处理结果的情况。参数1 出参类型，参数2 Code  参数3 Message  参数4  bean
		
		System.out.println(ret);
		
		
		//自定义返回节点的情况
		String ss=WsUtil.getRet(0, 10000, "成功").addNode("Age", 20).addNode("Name", "测试").toString();
		*//**
		 * 以下输出结果
		 * {"Code":10000,"Message":"成功","Age":20,"Name":"测试"}
		 *//*
		System.out.println(ss);
		
		
		ss=WsUtil.getRet(1, 10000, "成功").addNode("Age", 20).addNode("Name", "测试").toString();
		*//**
		 * 以下输出结果
		 * <?xml version="1.0" encoding="UTF-8"?><OutPut><Code>10000</Code><Message>成功</Message><Age>20</Age><Name>测试</Name></OutPut>
		 *//*
		System.out.println(ss);*/
	}

}
