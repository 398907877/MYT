/**
 * 
 */
package com.yihu.myt.service;

import java.util.Random;

/**
 * @author Administrator
 *
 */
public class CoopDictionary {

	public static final int DX = 1;
	public static final int YD = 2;
	public static final int LT = 3;
	
	public static final int default_pkg = 201;//福建电信健康管家包月
	public static final int default_pkg_year = 212;//福建电信健康管家包年
	public static final int default_qw_1 = 392; //全网电信个人
	public static final int default_qw_2 = 393;	//全网电信家庭
	public static final int default_qw_jkkx = 391; //全网健康快讯
	public static final int default_qw_jkjtys_10 = 581; //全网健康管家家庭医生个人版 10元
	//短信标识
	public static final int sms_packageid_1 = 263;  //福建免费短息
	public static final int sms_packageid_2 = 262;  //福建3元短信
	public static final int sms_packageid_3 = 401; //全网免费短息
	public static final int jkgj_tysms_pkgid = 521; //全网健康短信体验版  0元
	public static final int cx_jkjczk_pkgid = 541; //全网健康彩信周刊  0元
	
	public static final int limt_time = 5; //设置订购超时时间
	
	//-------------------全网电信套餐 订购类  产品编码-------------------
	public static final String jkgj_tydx = "135000000000000181039"; //健康管家体验短信 0元  521
	public static final String jkkx = "135000000000000093510";//健康快讯 391
	public static final String jkgj_geren = "135000000000000093553";//健康管家-个人版  392
	public static final String jkgj_jiating = "135000000000000093649";//健康管家-家庭版 393
	public static final String dx_qw_jkgj_10 = "135000000000000171954";//健康管家家庭医生个人版10元  581
	public static final String dx_qw_jkgj_20 = "135000000000000171958";//健康管家家庭医生家庭版20元  
	public static final String cx_jkjczk = "135000000000000172059"; //彩信健康精彩周刊 0元      541
	
	//--------------------全网电信 点播类 产品编码-------------------
	public static final String jkkx_dianbo = "135000000000000092963";//健康快讯-点播类 1元
	public static final String jkkx_hddianbo = "135000000000000170389"; //健康互动点播 0元
	
	//--------------------母音业务  套餐id-----------------------------------
	
	public static final int dx_qw_pkgid_my_5 = 601; //母婴乐园标准会员5元
	public static final int dx_qw_pkgid_my_10 = 602; //母婴乐园高级会员10元
	public static final int dx_qw_pkgid_my_30 = 603; //母婴乐园白金会员30元
	
	//--------------------母音业务  产品编码---------------------------------
	public static final String dx_qw_productsn_my_5 = "135000000000000018161"; //母婴乐园标准会员5元
	public static final String dx_qw_productsn_my_10 = "135000000000000018160"; //母婴乐园高级会员10元
	public static final String dx_qw_productsn_my_30 = "135000000000000018165"; //母婴乐园白金会员30元

	public static final int  qw_pqyx_days = 30;  //全网套餐批前有效天数
	public static final String yx_remark_boss = "外呼营销";  
	public static final String yx_remark_zdt = "金三桥营销";
	public static final String ph_OrderFlag = "全网正式订购"; //批后正式订购
	public static final String ph_CancelFlag = "全网正式退订";
	public static final String ty_CancelFlag = "体验用户退订";
	public static final String pq_DateEndCancelFlag = "全网批前到期退订"; //全网批前有效期到期 系统退订
	
	//对应卡类型
	public static final int fj_mobile = 335;//福建移动
	public static final int fj_dx = 336;//福建电信
	public static final int jx_mobile = 269;//江西移动
	public static final int jx_tele = 272;//江西电信
	public static final int jx_union = 288;//江西联通
	public static final int hb_mobile = 270;//湖北移动
	public static final int hb_tele = 273;//湖北电信
	public static final int hb_union = 287;//湖北联通
	public static final int qw_dx = 392;  //全网电信
	
	public static String formatState(int state)
	{
		switch(state)
		{
			case 1:return "正常";
			case 2:return "已退定未生效";
			case 3:return "已生效退定";
			case 0:return "无效";
			default:return "未知";
		}
	}
	//是否是基本包
	public static boolean isCoop(int cardtypesn)
	{
		if(cardtypesn==jx_mobile||cardtypesn==hb_mobile||cardtypesn==jx_tele||cardtypesn==hb_tele
				||cardtypesn==hb_union||cardtypesn==jx_union||cardtypesn==fj_mobile
				||cardtypesn==fj_dx||cardtypesn==qw_dx)
		{
			return true;
		}
		return false;
	}

	

	
	public static int getBossPackageID(int value) {
		switch (value) {
		case 620034479:
			return 201;
		case 620034483:
			return 212;
		case 620112726:
			return 261;
		case 620112727:
			return 271;
		case 620112728:
			return 262; 
		case 620112723:
			return 272;
		case 620112724:
			return 273;
		case 620112725:
			return 274;
		case 620034463:
			return 201;
		case 620034471:
			return 201;
		default:
			return 0;
		}
	}
	//获取全网套餐ID
	public static int getBossQwPackageID(String value) 
	{
		if(jkkx.equals(value))
		{
			return default_qw_jkkx;
		}
		else if(jkgj_geren.equals(value))
		{
			return default_qw_1;
		}
		else if(jkgj_jiating.equals(value))
		{
			return default_qw_2;
		}
		else if(jkgj_tydx.equals(value))
		{
			return jkgj_tysms_pkgid ;
		}
		else if(cx_jkjczk.equals(value))
		{
			return cx_jkjczk_pkgid;
		}
		else if(dx_qw_jkgj_10.equals(value))
		{
			return default_qw_jkjtys_10;
		}
		//-----20120725新增------
		else if(dx_qw_productsn_my_5.equals(value))
		{
			return dx_qw_pkgid_my_5;
		}
		else if(dx_qw_productsn_my_10.equals(value))
		{
			return dx_qw_pkgid_my_10;
		}
		else if(dx_qw_productsn_my_30.equals(value))
		{
			return dx_qw_pkgid_my_30;
		}
		else
		{
			return 0;
		}
	}
	
	//电信全网pkgid转化为电信内部业务代码
	public static String getProductId(int pkgID)
	{
		String productId=null;
		switch(pkgID)
		{
			case default_qw_jkkx:productId=jkkx;break;    //391
			case default_qw_1:productId=jkgj_geren;break;  //392
			case default_qw_2:productId=jkgj_jiating;break; //393
			case jkgj_tysms_pkgid:productId=jkgj_tydx;break; //521
			case cx_jkjczk_pkgid:productId=cx_jkjczk;break;  //541
			case default_qw_jkjtys_10:productId = dx_qw_jkgj_10;break; //581
			case dx_qw_pkgid_my_5:productId = dx_qw_productsn_my_5;break;
			case dx_qw_pkgid_my_10:productId = dx_qw_productsn_my_10;break;
			case dx_qw_pkgid_my_30:productId = dx_qw_productsn_my_30;break;
			default:break;
		}
		return productId;
	}
	
	
	//全网健康管家短信套餐
	public static boolean isSmsPackageID(int packageID)
	{
		if(packageID==default_qw_jkkx||packageID ==jkgj_tysms_pkgid
				||packageID == cx_jkjczk_pkgid||packageID==dx_qw_pkgid_my_5)
		{
			return true;
		}
		return false;
	}
	
	//全网健康管家  母音业务
	public static boolean isJkgjPackage(int packageID)
	{
		if(packageID==default_qw_1||packageID==CoopDictionary.default_qw_2||packageID==default_qw_jkjtys_10
				||packageID==dx_qw_pkgid_my_10||packageID==dx_qw_pkgid_my_30)
		{
			return true;
		}
		return false;
	}
	//是否是全网健康管家套餐
	public static boolean isQwPackageID(int packageID)
	{
		if(packageID==default_qw_jkkx||packageID==default_qw_1
			||packageID==default_qw_2||packageID ==jkgj_tysms_pkgid
			||packageID == cx_jkjczk_pkgid||packageID==default_qw_jkjtys_10
			||packageID==dx_qw_pkgid_my_5||packageID==dx_qw_pkgid_my_10||packageID==dx_qw_pkgid_my_30)
		{
			return true;
		}
		return false;
	}
	//随机取一个整数
	public static int getRandom()
	{
	    Random r=new Random();  //生成0到2之间的整数
	    int n = r.nextInt(3);
	    n = Math.abs(r.nextInt() % 3);
	    return n;
	}
	
	//福建健康短信  262 福建3元
	public static boolean isJKGJDX(int packageID)
	{
		if(packageID==sms_packageid_2)
		{
			return true;
		}
		return false;
	}
	/**
	 * 	   套餐名称                    代码
		健康管家（家庭版包月8元）        1183370
		健康管家（家庭版包年96元）       1183371
		健康管家（企业版包月89元）       1183372
		健康管家（企业版包月39元）       1183373
		健康管家（企业版包月169元）      1183374
		健康管家（家庭版包月5元）        1183375
		健康管家（加装包A3）             1183376
		健康管家（短信版包月3元）        1183377
		
		健康管家（企业版包月39元）（id：620112723） p8  272
		健康管家（企业版包月89元）（id：620112724） p7       273
		健康管家（企业版包月169元）（id：620112725） p6      274
		健康管家（家庭版包月5元）（id：620112726） p5     261
		健康管家（加装包A3）（id：620112727） p4          271
		健康管家（短信版包月3元）（id：620112728）  p3
		健康管家（包月）（id：620034479）改名为“健康管家（家庭版包月8元）  p1   201
		健康管家（包年）（id：620034483）改名为“健康管家（家庭版包年96元） p2   212
	 * @param pkgid
	 * @return
	 */
	public static String getServiceID(Integer pkgid) {
		if(pkgid==null)
		{
			return null;
		}
		switch (pkgid) {
		case 1:return "1183370";
		case 201:
			return "1183370";
		case 212:
			return "1183371";
		case 2:return "1183371";
		case 273:
			return "1183372";
		case 272:
			return "1183373";
		case 274:
			return "1183374";
		case 261:
			return "1183375";
		case 271:
			return "1183376";
		case 262:
			return "1183377";
		default:
			return null;
		}
	}
	
	/**
	 * 营销号码所属的分公司ID，如果暂未设分公司填总公司ID
	 * 电信营销，根据所取号码所属的省份,来设置所属机构
	 * @param pkgid
	 * @return
	 */
	public static int getOrgID(int provinceid) 
	{
		switch(provinceid)
		{
			case 13: return 1;   //福建健康之路
			case 14: return 211; //江西
			case 19: return 71;  //广州
			case 17: return 291; //湖北
			case 27: return 292; //陕西
			case 12: return 751; //安徽
			case 11: return 212; //浙江
			default: return 99999; //总公司
		}
	}
	
	/**
	 * 营销员所属的分公司ID，外包填总公司ID
	 *（根据传入的机构id）
	 */
	public static int getMarketOrgID(int orgid) 
	{
		
		if(orgid==1||orgid==211||orgid==71||orgid==291||orgid==292||orgid==751||orgid==212)
		{
			return orgid;
		}
		else{
			return 99999;
		}
	}
}
