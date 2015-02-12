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
	
	public static final int default_pkg = 201;//�������Ž����ܼҰ���
	public static final int default_pkg_year = 212;//�������Ž����ܼҰ���
	public static final int default_qw_1 = 392; //ȫ�����Ÿ���
	public static final int default_qw_2 = 393;	//ȫ�����ż�ͥ
	public static final int default_qw_jkkx = 391; //ȫ��������Ѷ
	public static final int default_qw_jkjtys_10 = 581; //ȫ�������ܼҼ�ͥҽ�����˰� 10Ԫ
	//���ű�ʶ
	public static final int sms_packageid_1 = 263;  //������Ѷ�Ϣ
	public static final int sms_packageid_2 = 262;  //����3Ԫ����
	public static final int sms_packageid_3 = 401; //ȫ����Ѷ�Ϣ
	public static final int jkgj_tysms_pkgid = 521; //ȫ���������������  0Ԫ
	public static final int cx_jkjczk_pkgid = 541; //ȫ�����������ܿ�  0Ԫ
	
	public static final int limt_time = 5; //���ö�����ʱʱ��
	
	//-------------------ȫ�������ײ� ������  ��Ʒ����-------------------
	public static final String jkgj_tydx = "135000000000000181039"; //�����ܼ�������� 0Ԫ  521
	public static final String jkkx = "135000000000000093510";//������Ѷ 391
	public static final String jkgj_geren = "135000000000000093553";//�����ܼ�-���˰�  392
	public static final String jkgj_jiating = "135000000000000093649";//�����ܼ�-��ͥ�� 393
	public static final String dx_qw_jkgj_10 = "135000000000000171954";//�����ܼҼ�ͥҽ�����˰�10Ԫ  581
	public static final String dx_qw_jkgj_20 = "135000000000000171958";//�����ܼҼ�ͥҽ����ͥ��20Ԫ  
	public static final String cx_jkjczk = "135000000000000172059"; //���Ž��������ܿ� 0Ԫ      541
	
	//--------------------ȫ������ �㲥�� ��Ʒ����-------------------
	public static final String jkkx_dianbo = "135000000000000092963";//������Ѷ-�㲥�� 1Ԫ
	public static final String jkkx_hddianbo = "135000000000000170389"; //���������㲥 0Ԫ
	
	//--------------------ĸ��ҵ��  �ײ�id-----------------------------------
	
	public static final int dx_qw_pkgid_my_5 = 601; //ĸӤ��԰��׼��Ա5Ԫ
	public static final int dx_qw_pkgid_my_10 = 602; //ĸӤ��԰�߼���Ա10Ԫ
	public static final int dx_qw_pkgid_my_30 = 603; //ĸӤ��԰�׽��Ա30Ԫ
	
	//--------------------ĸ��ҵ��  ��Ʒ����---------------------------------
	public static final String dx_qw_productsn_my_5 = "135000000000000018161"; //ĸӤ��԰��׼��Ա5Ԫ
	public static final String dx_qw_productsn_my_10 = "135000000000000018160"; //ĸӤ��԰�߼���Ա10Ԫ
	public static final String dx_qw_productsn_my_30 = "135000000000000018165"; //ĸӤ��԰�׽��Ա30Ԫ

	public static final int  qw_pqyx_days = 30;  //ȫ���ײ���ǰ��Ч����
	public static final String yx_remark_boss = "���Ӫ��";  
	public static final String yx_remark_zdt = "������Ӫ��";
	public static final String ph_OrderFlag = "ȫ����ʽ����"; //������ʽ����
	public static final String ph_CancelFlag = "ȫ����ʽ�˶�";
	public static final String ty_CancelFlag = "�����û��˶�";
	public static final String pq_DateEndCancelFlag = "ȫ����ǰ�����˶�"; //ȫ����ǰ��Ч�ڵ��� ϵͳ�˶�
	
	//��Ӧ������
	public static final int fj_mobile = 335;//�����ƶ�
	public static final int fj_dx = 336;//��������
	public static final int jx_mobile = 269;//�����ƶ�
	public static final int jx_tele = 272;//��������
	public static final int jx_union = 288;//������ͨ
	public static final int hb_mobile = 270;//�����ƶ�
	public static final int hb_tele = 273;//��������
	public static final int hb_union = 287;//������ͨ
	public static final int qw_dx = 392;  //ȫ������
	
	public static String formatState(int state)
	{
		switch(state)
		{
			case 1:return "����";
			case 2:return "���˶�δ��Ч";
			case 3:return "����Ч�˶�";
			case 0:return "��Ч";
			default:return "δ֪";
		}
	}
	//�Ƿ��ǻ�����
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
	//��ȡȫ���ײ�ID
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
		//-----20120725����------
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
	
	//����ȫ��pkgidת��Ϊ�����ڲ�ҵ�����
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
	
	
	//ȫ�������ܼҶ����ײ�
	public static boolean isSmsPackageID(int packageID)
	{
		if(packageID==default_qw_jkkx||packageID ==jkgj_tysms_pkgid
				||packageID == cx_jkjczk_pkgid||packageID==dx_qw_pkgid_my_5)
		{
			return true;
		}
		return false;
	}
	
	//ȫ�������ܼ�  ĸ��ҵ��
	public static boolean isJkgjPackage(int packageID)
	{
		if(packageID==default_qw_1||packageID==CoopDictionary.default_qw_2||packageID==default_qw_jkjtys_10
				||packageID==dx_qw_pkgid_my_10||packageID==dx_qw_pkgid_my_30)
		{
			return true;
		}
		return false;
	}
	//�Ƿ���ȫ�������ܼ��ײ�
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
	//���ȡһ������
	public static int getRandom()
	{
	    Random r=new Random();  //����0��2֮�������
	    int n = r.nextInt(3);
	    n = Math.abs(r.nextInt() % 3);
	    return n;
	}
	
	//������������  262 ����3Ԫ
	public static boolean isJKGJDX(int packageID)
	{
		if(packageID==sms_packageid_2)
		{
			return true;
		}
		return false;
	}
	/**
	 * 	   �ײ�����                    ����
		�����ܼң���ͥ�����8Ԫ��        1183370
		�����ܼң���ͥ�����96Ԫ��       1183371
		�����ܼң���ҵ�����89Ԫ��       1183372
		�����ܼң���ҵ�����39Ԫ��       1183373
		�����ܼң���ҵ�����169Ԫ��      1183374
		�����ܼң���ͥ�����5Ԫ��        1183375
		�����ܼң���װ��A3��             1183376
		�����ܼң����Ű����3Ԫ��        1183377
		
		�����ܼң���ҵ�����39Ԫ����id��620112723�� p8  272
		�����ܼң���ҵ�����89Ԫ����id��620112724�� p7       273
		�����ܼң���ҵ�����169Ԫ����id��620112725�� p6      274
		�����ܼң���ͥ�����5Ԫ����id��620112726�� p5     261
		�����ܼң���װ��A3����id��620112727�� p4          271
		�����ܼң����Ű����3Ԫ����id��620112728��  p3
		�����ܼң����£���id��620034479������Ϊ�������ܼң���ͥ�����8Ԫ��  p1   201
		�����ܼң����꣩��id��620034483������Ϊ�������ܼң���ͥ�����96Ԫ�� p2   212
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
	 * Ӫ�����������ķֹ�˾ID�������δ��ֹ�˾���ܹ�˾ID
	 * ����Ӫ����������ȡ����������ʡ��,��������������
	 * @param pkgid
	 * @return
	 */
	public static int getOrgID(int provinceid) 
	{
		switch(provinceid)
		{
			case 13: return 1;   //��������֮·
			case 14: return 211; //����
			case 19: return 71;  //����
			case 17: return 291; //����
			case 27: return 292; //����
			case 12: return 751; //����
			case 11: return 212; //�㽭
			default: return 99999; //�ܹ�˾
		}
	}
	
	/**
	 * Ӫ��Ա�����ķֹ�˾ID��������ܹ�˾ID
	 *�����ݴ���Ļ���id��
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
