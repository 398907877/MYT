package com.yihu.myt.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import com.common.json.JSONException;
import com.common.json.JSONObject;
import com.coreframework.db.DB;
import com.coreframework.db.JdbcConnection;
import com.coreframework.db.Sql;
import com.coreframework.remoting.standard.DateOper;
import com.coreframework.vo.ReturnValue;
import com.coreframework.vo.ServiceResult;
import com.yihu.myt.IOperconfidService;
import com.yihu.myt.enums.MyDatabaseEnum;
import com.yihu.myt.enums.MySqlNameEnum;
import com.yihu.myt.enums.MyTableNameEnum;
import com.yihu.myt.enums.MytConst;
import com.yihu.myt.enums.QuesMainSqlNameEnum;
import com.yihu.myt.vo.BaseDoctorBean;
import com.yihu.myt.vo.BaseSmalldepartmentBean;
import com.yihu.myt.vo.MytConsphoneBean;
import com.yihu.myt.vo.MytDoctorViewBean;
import com.yihu.myt.vo.MytMainintrodoctorBean;
import com.yihu.myt.vo.MytOperconfigBean;
import com.yihu.myt.vo.MytOperconfigWBean;
import com.yihu.myt.vo.OperconfigVo;


/**
 * ��ҽͨҽ�����÷���ʵ����
 * @author wangfeng
 * @company yihu.com
 * 2012-8-8����05:13:23
 */
public class OperconfidService implements IOperconfidService {

	public ServiceResult<List<Map<String, String>>> getStdOffice(int orgId) {
		
		ServiceResult<List<Map<String, String>>> sr = new ServiceResult<List<Map<String, String>>>(-1, null);
		try {
			Sql sqlObj = DB.me().createSql(MySqlNameEnum.getDeptByOrg);
			sqlObj.addParamValue(orgId);
			List<Object[]> lstObj = DB.me().queryForList(MyDatabaseEnum.boss, sqlObj);
			if (lstObj == null || lstObj.isEmpty()) {
				sr.setMessage("������Ϣ�����ڡ�");
				sr.setResult(null);
			} else {
				sr.setCode(1);
				sr.setMessage("��ȡ������Ϣ�ɹ���");
				List<Map<String, String>> lstMap = new ArrayList<Map<String, String>>();
				for (Object[] objs : lstObj) {
					Map<String, String> map = new HashMap<String, String>();
					map.put("hospofficeid", objs[0].toString());
					map.put("hospofficename", objs[1].toString());
					lstMap.add(map);
				}
				sr.setResult(lstMap);
			}
			return sr;
		} catch (Exception e) {
			e.printStackTrace();
			sr.setMessage("��ȡ������Ϣ����");
			sr.setResult(null);
			return sr;
		}
	}
public String getDoctorIndexList(String name,int row,int page) {
		JSONObject result = new JSONObject();
		try {
			StringBuffer sbSql = new StringBuffer(" WHERE 1=1");
			sbSql.append(" AND a.doctorName = '" + name + "'");
			Sql sql = DB.me().createSql(MySqlNameEnum.getDoctor);
			sql.addVar("@p", sbSql.toString());
			result = DB.me().queryForJson(MyDatabaseEnum.boss, 
					sql,row,page);
		}catch (Exception e) {
					e.printStackTrace();
					try {
						result.put("code", "-1");
						result.put("result", e.getLocalizedMessage());
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
		return result.toString();
	}
	public ServiceResult<List<MytDoctorViewBean>> getDoctor(int orgID, String deptID) {
		
		ServiceResult<List<MytDoctorViewBean>> sr = new ServiceResult<List<MytDoctorViewBean>>(-1, null);
		try {
			StringBuffer sbSql = new StringBuffer(" WHERE 1=1");
			sbSql.append(" AND a.STATE = 1");
			sbSql.append(" AND a.ORGID = " + orgID);
			if ("-1".equals(deptID)) {
				sbSql.append(" AND a.DOCTORLEVEL = 7");
			} else {
				sbSql.append(" AND a.HOSPOFFICEID = " + deptID);
				sbSql.append(" AND a.DOCTORLEVEL <> 7");
			}
			Sql sql = DB.me().createSql(MySqlNameEnum.getDoctor);
			sql.addVar("@p", sbSql.toString());
			List<MytDoctorViewBean> lstMdvBean = DB.me().queryForBeanList(MyDatabaseEnum.boss, 
					sql, MytDoctorViewBean.class);
			
			if (lstMdvBean == null || lstMdvBean.isEmpty()) {
				sr.setMessage("ҽ����ͼ��Ϣ�����ڡ�");
				sr.setResult(null);
			} else {
				sr.setCode(1);
				sr.setMessage("��ȡҽ����ͼ��Ϣ�ɹ���");
				sr.setResult(lstMdvBean);
			}
			return sr;
		} catch (Exception e) {
			e.printStackTrace();
			sr.setMessage("��ȡҽ����ͼ��Ϣ����");
			sr.setResult(null);
			return sr;
		}
	}
	
	public ServiceResult<List<MytMainintrodoctorBean>> getMainintrodoctor(int operconfid) {
		
		ServiceResult<List<MytMainintrodoctorBean>> sr = new ServiceResult<List<MytMainintrodoctorBean>>(-1, null);
		try {
			Sql sqlMmBean = DB.me().createSql(MySqlNameEnum.getMainintrodoctor);
			sqlMmBean.addParamValue(MytConst.EFFECTIVE.getValue());
			sqlMmBean.addParamValue(operconfid);
			List<MytMainintrodoctorBean> lstMmBean = DB.me().queryForBeanList(MyDatabaseEnum.boss, 
					sqlMmBean, MytMainintrodoctorBean.class);
			
			if (lstMmBean == null || lstMmBean.isEmpty()) {
				sr.setMessage("ҽ�������Ϣ�����ڡ�");
				sr.setResult(null);
				return sr;
			}
			sr.setCode(1);
			sr.setMessage("��ȡҽ�������Ϣ�ɹ���");
			sr.setResult(lstMmBean);
			return sr;
		} catch (Exception e) {
			e.printStackTrace();
			sr.setMessage("��ȡҽ�������Ϣ����");
			sr.setResult(null);
			return sr;
		}
	}
	
	public ReturnValue addDoctor(int operator, String operatorname, MytOperconfigBean parMoBean, String consphone) {

		JdbcConnection connBoss = null;
		try {
			// ����ҽ��������Ϣ
			parMoBean.setState(MytConst.EFFECTIVE.getValue());		
			parMoBean.setOperatorid(operator+"");
			parMoBean.setOperatorname(operatorname);
			parMoBean.setOpertime(DateOper.getNowDateTime());
			
			// ����doctorid��ѯҽ��������Ϣ
			BaseDoctorBean qryBdBean = new BaseDoctorBean();
			qryBdBean.setDoctorid(parMoBean.getDoctorid());
			BaseDoctorBean rltBdBean = DB.me().queryForBean(MyDatabaseEnum.boss, 
					DB.me().createSelect(qryBdBean, MyTableNameEnum.BASE_DOCTOR, "BOSS"), 
					BaseDoctorBean.class);
			if (rltBdBean == null) {
				return new ReturnValue(-1, "ҽ��������Ϣ�����ڡ�");
			}
			
			// ��ѯ������Ϣ
			BaseSmalldepartmentBean qryBsBean = new BaseSmalldepartmentBean();
			qryBsBean.setDeptid(rltBdBean.getDeptid());
			List<BaseSmalldepartmentBean> rltBsBeans = DB.me().queryForBeanList(MyDatabaseEnum.boss, 
					DB.me().createSelect(qryBdBean, MyTableNameEnum.BASE_DOCTOR, "BOSS"), 
					BaseSmalldepartmentBean.class);
			if (rltBsBeans == null || rltBsBeans.isEmpty()) {
				return new ReturnValue(-1, "���ҽ������������׼�����쳣��");
			}
			
			// ����ҽ��������Ϣ
			parMoBean.setDoctorid(rltBdBean.getDoctorid());
			
			// ��ȡboss���ݿ����񲢿�ʼ����
			connBoss = DB.me().getConnection(MyDatabaseEnum.boss);
			connBoss.beginTransaction(3000);
			int rMoBean = DB.me().insert(connBoss, 
					DB.me().createInsertSql(parMoBean, MyTableNameEnum.MYT_OPERCONFIG, "BOSS"));
			if (rMoBean == 0) {
				connBoss.rollbackAndclose();
				return new ReturnValue(-1, "������ҽͨҽ������ʧ�ܡ�");
			}
			
			String[] phoneArr = consphone.split(",");
			for (int i = 0; i < phoneArr.length; i++) {
				MytConsphoneBean proMcBean = new MytConsphoneBean();
				proMcBean.setConsphone(phoneArr[i]);
				proMcBean.setState(1);
				proMcBean.setDoctorid(parMoBean.getDoctorid());
				int rMcBean = DB.me().insert(connBoss, 
						DB.me().createInsertSql(proMcBean, MyTableNameEnum.MYT_CONSPHONE, "dbo"));
				if (rMcBean == 0) {
					connBoss.rollbackAndclose();
					return new ReturnValue(-1, "���ҽ����ϵ��ʽ����ʧ�ܡ�");
				}
			}
			
			// ��ȡMytOperconfig��Ϣ
			MytOperconfigBean qryMoBean = new MytOperconfigBean();
			qryMoBean.setOperconfid(parMoBean.getOperconfid());
			MytOperconfigBean rltMoBean = DB.me().queryForBean(MyDatabaseEnum.boss, 
					DB.me().createSelect(qryMoBean, MyTableNameEnum.MYT_OPERCONFIG, "BOSS"), 
					MytOperconfigBean.class);
			if (rltMoBean == null) {
				connBoss.rollbackAndclose();
				return new ReturnValue(-1, "ҽ��������Ϣ�����ڡ�");
			}
			
			// ����ҽ��������Ϣ
			MytOperconfigWBean proMowBean = new MytOperconfigWBean();
			BeanUtils.copyProperties(proMowBean, rltMoBean);
			int rMowBean = DB.me().insert(MyDatabaseEnum.bossdata, 
					DB.me().createInsertSql(proMowBean, MyTableNameEnum.MYT_OPERCONFIG_W, "dbo"));
			if (rMowBean == 0) {
				connBoss.rollbackAndclose();
				return new ReturnValue(-1, "����ҽ��������Ϣ����ʧ�ܡ�");
			}
			
			connBoss.commitTransaction(true);
			return new ReturnValue(1, "����ҽ��������Ϣ�����ɹ���");
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnValue(-1, e.getMessage());
		}
	}

	public ReturnValue addNODoctor(MytOperconfigBean parMoBean) {
		
		JdbcConnection connBossData = null;
		try {
			// ��ȡbossdata���ݿ�����ʼ����
			connBossData = DB.me().getConnection(MyDatabaseEnum.bossdata);
			connBossData.beginTransaction(3000);
						
			// ��parMoBean���󿽱���proMowBean����
			MytOperconfigWBean proMowBean = new MytOperconfigWBean();
			BeanUtils.copyProperties(proMowBean, parMoBean);
			
			// ����MytOperconfigWBean����
			int rMowBean = DB.me().insert(connBossData, 
					DB.me().createInsertSql(proMowBean, MyTableNameEnum.MYT_OPERCONFIG_W, "dbo"));
			if (rMowBean == 0) {
				connBossData.rollbackAndclose();
				return new ReturnValue(-1, "��ӷ�ҽԺҽ������");
			}
			
			// ����MytOperconfigBean����
			int rMoBean = DB.me().insert(MyDatabaseEnum.boss, 
					DB.me().createInsertSql(parMoBean, MyTableNameEnum.MYT_OPERCONFIG, "BOSS"));
			if (rMoBean == 0) {
				connBossData.rollbackAndclose();
				return new ReturnValue(-1, "��ӷ�ҽԺҽ������");
			}
			
			// �ύ���񲢷��ؽ��
			connBossData.commitTransaction(true);
			return new ReturnValue(1, "��ӷ�ҽԺҽ���ɹ���");
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnValue(-1, e.getMessage());
		}
	}
	
	public ReturnValue updateDoctor(int operconfid, MytOperconfigBean parMoBean) {
		
		JdbcConnection connBossData = null;
		try {
			// ��ȡMytOperconfig��Ϣ
			MytOperconfigBean qryMoBean = new MytOperconfigBean();
			qryMoBean.setOperconfid(operconfid);
			MytOperconfigBean rltMoBean = DB.me().queryForBean(MyDatabaseEnum.boss, 
					DB.me().createSelect(qryMoBean, MyTableNameEnum.MYT_OPERCONFIG, "BOSS"), 
					MytOperconfigBean.class);
			if (rltMoBean == null) {
				return new ReturnValue(-1, "ҽ��������Ϣ�����ڡ�");
			}
			parMoBean.setOrgid(rltMoBean.getOrgid());
			parMoBean.setCityid(rltMoBean.getCityid());
			parMoBean.setState(MytConst.EFFECTIVE.getValue());
			parMoBean.setDoctorid(rltMoBean.getDoctorid());
			parMoBean.setSevendoctorid(rltMoBean.getSevendoctorid());
			parMoBean.setTendoctorid(rltMoBean.getTendoctorid());
			parMoBean.setRealname(rltMoBean.getRealname());
			parMoBean.setYiHustatus(rltMoBean.getYiHustatus());
			
			// ��MytOperconfigBean������MytOperconfigWBean����
			MytOperconfigWBean proMowBean = new MytOperconfigWBean();
			BeanUtils.copyProperties(proMowBean, parMoBean);
			
			// ��ȡbossdata���ݿ����Ӳ���ʼ����
			connBossData = DB.me().getConnection(MyDatabaseEnum.bossdata);
			connBossData.beginTransaction(3000);
			
			int rMowBean = DB.me().insert(connBossData, 
					DB.me().createInsertSql(proMowBean, MyTableNameEnum.MYT_OPERCONFIG_W, "dbo"));
			if (rMowBean == 0) {
				return new ReturnValue(-1, "���ҽ��������Ϣ����");
			}
			
			// ����MytOperconfigBean��ҽ��������Ϣ��
			parMoBean.setOperconfid(null);
			int rMoBean = DB.me().update(MyDatabaseEnum.boss, 
					DB.me().createUpdateSql(parMoBean, "BOSS", MyTableNameEnum.MYT_OPERCONFIG, " operconfid = " + operconfid));
			if (rMoBean == 0) {
				connBossData.rollbackAndclose();
				return new ReturnValue(-1, "�޸�ҽ��������Ϣ����");
			}
			
			// �ύ���񲢷��ؽ��
			connBossData.commitTransaction(true);
			return new ReturnValue(1, "���ҽ����Ϣ�ɹ���");
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnValue(-1, e.getMessage());
		}
	}
	
	public ReturnValue deleteDoctor(int operconfid,int operator,String operatorname) {
		
		JdbcConnection connBossData = null;
		try {
			// ��ȡMytOperconfig��Ϣ
			MytOperconfigBean qryMoBean = new MytOperconfigBean();
			qryMoBean.setOperconfid(operconfid);
			MytOperconfigBean rltMoBean = DB.me().queryForBean(MyDatabaseEnum.boss, 
					DB.me().createSelect(qryMoBean, MyTableNameEnum.MYT_OPERCONFIG, "BOSS"), 
					MytOperconfigBean.class);
			if (rltMoBean == null) {
				return new ReturnValue(-1, "ҽ��������Ϣ�����ڡ�");
			}
			rltMoBean.setOperatorid(operator + "");
			rltMoBean.setOperatorname(operatorname);
			rltMoBean.setOpertime(DateOper.getNowDateTime());
			rltMoBean.setState(MytConst.PAUSE.getValue() );
			
			// ��MytOperconfigBean������MytOperconfigWBean����
			MytOperconfigWBean proMowBean = new MytOperconfigWBean();
			BeanUtils.copyProperties(proMowBean, rltMoBean);
			
			// ��ȡbossdata���ݿ����Ӳ���ʼ����
			connBossData = DB.me().getConnection(MyDatabaseEnum.bossdata);
			connBossData.beginTransaction(3000);
			
			int rMowBean = DB.me().insert(connBossData, 
					DB.me().createInsertSql(proMowBean, MyTableNameEnum.MYT_OPERCONFIG_W, "dbo"));
			if (rMowBean == 0) {
				return new ReturnValue(-1, "���ҽ��������Ϣ����");
			}
			
			// ����MytOperconfigBean��ҽ��������Ϣ��
			rltMoBean.setOperconfid(null);
			int rMoBean = DB.me().update(MyDatabaseEnum.boss, 
					DB.me().createUpdateSql(rltMoBean, "BOSS", MyTableNameEnum.MYT_OPERCONFIG, " operconfid = " + operconfid));
			if (rMoBean == 0) {
				connBossData.rollbackAndclose();
				return new ReturnValue(-1, "�޸�ҽ��������Ϣ����");
			}
			
			// �ύ���񲢷��ؽ��
			connBossData.commitTransaction(true);
			return new ReturnValue(1, "ɾ��ҽ����Ϣ�ɹ���");
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnValue(-1, e.getMessage());
		}
	}
	
	public ReturnValue NOdeleteDoctor(int operconfid, int operator, String operatorname) {
		
		JdbcConnection connBossData = null;
		try {
			// ��ȡMytOperconfig��Ϣ
			MytOperconfigBean qryMoBean = new MytOperconfigBean();
			qryMoBean.setOperconfid(operconfid);
			MytOperconfigBean rltMoBean = DB.me().queryForBean(MyDatabaseEnum.boss, 
					DB.me().createSelect(qryMoBean, MyTableNameEnum.MYT_OPERCONFIG, "BOSS"), 
					MytOperconfigBean.class);
			if (rltMoBean == null) {
				return new ReturnValue(-1, "ҽ��������Ϣ�����ڡ�");
			}
			rltMoBean.setOperatorid(operator + "");
			rltMoBean.setOperatorname(operatorname);
			rltMoBean.setOpertime(DateOper.getNowDateTime());
			rltMoBean.setState(MytConst.EFFECTIVE.getValue() );
			
			// ��MytOperconfigBean������MytOperconfigWBean����
			MytOperconfigWBean proMowBean = new MytOperconfigWBean();
			BeanUtils.copyProperties(proMowBean, rltMoBean);
			
			// ��ȡbossdata���ݿ����Ӳ���ʼ����
			connBossData = DB.me().getConnection(MyDatabaseEnum.bossdata);
			connBossData.beginTransaction(3000);
			
			int rMowBean = DB.me().insert(connBossData, 
					DB.me().createInsertSql(proMowBean, MyTableNameEnum.MYT_OPERCONFIG_W, "dbo"));
			if (rMowBean == 0) {
				connBossData.close();
				return new ReturnValue(-1, "���ҽ��������Ϣ����");
			}
			
			// ����MytOperconfigBean��ҽ��������Ϣ��
			rltMoBean.setOperconfid(null);
			int rMoBean = DB.me().update(MyDatabaseEnum.boss, 
					DB.me().createUpdateSql(rltMoBean, "BOSS", MyTableNameEnum.MYT_OPERCONFIG, " operconfid = " + operconfid));
			if (rMoBean == 0) {
				connBossData.rollbackAndclose();
				return new ReturnValue(-1, "�޸�ҽ��������Ϣ����");
			}
			
			connBossData.commitTransaction(true);
			return new ReturnValue(1, "�ָ�ҽ���ɹ���");
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnValue(-1, e.getMessage());
		}
	}

	public ReturnValue addDept(String cmbSdept, String sdept, String remark, int operator, String operatorname, MytOperconfigBean parMoBean) {
		
		JdbcConnection connBossData = null;
		try {
			// ����MytOperconfigBean����
			MytOperconfigBean proMoBean = new MytOperconfigBean();
			proMoBean.setDoctorid(parMoBean.getDoctorid());
			proMoBean.setSevendoctorid(parMoBean.getSevendoctorid());
			proMoBean.setTendoctorid(parMoBean.getTendoctorid());
			proMoBean.setRealname(parMoBean.getRealname());
			proMoBean.setDoctorname(parMoBean.getDoctorname());
			proMoBean.setHospofficeid(cmbSdept);
			proMoBean.setHospofficename(sdept);				
			proMoBean.setOperatorid(operator+"");
			proMoBean.setOperatorname(operatorname);
			proMoBean.setOpertime(DateOper.getNowDateTime());
			proMoBean.setDoctoridiom(parMoBean.getDoctoridiom());
			proMoBean.setDoctorlevel(parMoBean.getDoctorlevel());
			proMoBean.setPaytype(parMoBean.getPaytype());
			proMoBean.setBalancetype(parMoBean.getBalancetype());
			proMoBean.setSendtype(parMoBean.getSendtype());
			proMoBean.setOrgid(parMoBean.getOrgid());
			proMoBean.setCityid(parMoBean.getCityid());
			proMoBean.setState(parMoBean.getState());
			proMoBean.setRemark(remark);
			proMoBean.setIspaydoctor(parMoBean.getIspaydoctor());
			
			// ��MytOperconfigBean���󿽱���MytOperconfigWBean����
			MytOperconfigWBean proMowBean = new MytOperconfigWBean();
			BeanUtils.copyProperties(proMowBean, proMoBean);
			
			// ��ȡbossdata���ݿ����ӿ�ʼ����
			connBossData = DB.me().getConnection(MyDatabaseEnum.bossdata);
			connBossData.beginTransaction(3000);
			
			// ����MytOperconfigWBean����
			int rMowBean = DB.me().insert(connBossData, 
					DB.me().createInsertSql(proMowBean, MyTableNameEnum.MYT_OPERCONFIG_W, "dbo"));
			if (rMowBean == 0) {
				connBossData.close();
				return new ReturnValue(-1, "��ӷ�ҽԺҽ������");
			}
			
			// ����MytOperconfigBean����
			int rMoBean = DB.me().insert(MyDatabaseEnum.boss, 
					DB.me().createInsertSql(parMoBean, MyTableNameEnum.MYT_OPERCONFIG, "BOSS"));
			if (rMoBean == 0) {
				connBossData.rollbackAndclose();
				return new ReturnValue(-1, "��ӷ�ҽԺҽ������");
			}
			
			// �ύ���񲢷��ؽ��
			connBossData.commitTransaction(true);
			return new ReturnValue(1, "��ӷ�ҽԺҽ���ɹ���");
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnValue(-1, e.getMessage());
		}
	}

	public ServiceResult<Boolean> isExsistMytOperconfig(String doctorId, String state) {
		
		ServiceResult<Boolean> sr = new ServiceResult<Boolean>(-1, null);
		try {
			Sql sql = DB.me().createSql(MySqlNameEnum.getMytOperconfigCount);
			StringBuffer sbSql = new StringBuffer(" WHERE 1=1");
			if (StringUtils.isNotEmpty(doctorId)) {
				sbSql.append(" AND a.DOCTORID = " + doctorId);
			}
			if (StringUtils.isNotEmpty(state)) {
				sbSql.append(" AND a.STATE <> " + state);
			}
			sql.addVar("@p", sbSql.toString());
			Integer count = DB.me().queryForInteger(MyDatabaseEnum.boss, sql);
			if (count == null || count == 0) {
				sr.setMessage("�����ڡ�");
				sr.setResult(false);
				return sr;
			}
			sr.setCode(1);
			sr.setMessage("���ڡ�");
			sr.setResult(true);
			return sr;
		} catch (Exception e) {
			e.printStackTrace();
			sr.setMessage(e.getMessage());
			sr.setResult(true);
			return sr;
		}
	}
	
	public JSONObject getConsphoneByDoctor(int operconfId){
		JSONObject obj = new JSONObject();
		try {
			Sql sql = DB.me().createSql(MySqlNameEnum.getConsphoneByDoctor);
			sql.addParamValue(operconfId);
			obj = DB.me().queryForJson(MyDatabaseEnum.boss, sql);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
	
}
