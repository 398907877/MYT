package com.yihu.myt.enums;

import com.coreframework.db.TableEnum;

/**
 * ������ö��
 * @author wangfeng
 * @company yihu.com
 * 2012-7-26����05:53:43
 */
public enum MyTableNameEnum implements TableEnum {

	// ҽ���Ű��
	MYT_ARRAWORK,
	MYT_ARRAWORK_W,
	MYT_ARRAWORK_VIEW,

	// ҽ���Ű�绰��
	MYT_ARRAWORKPHONE,
	
	// ҽ��ͣ����Ϣ��
	MYT_PAUSEWORK,
	MYT_PAUSEWORK_VIEW,

	// ҽ���绰��Ϣ��
	MYT_CONSPHONE,

	// ҽ����Ϣ��
	MYT_DOCTOR_VIEW,

	// �Ű�ҽ���绰��Ϣ��
	MYT_ARRAPHONE_VIEW,

	// ԤԼ�ظ���
	MYT_CONSENROL,

	// ��ҽͨҽ�����ñ�
	MYT_OPERCONFIG,
	MYT_OPERCONFIG_W,

	// ԤԼ�ظ���
	MYT_BOOKENROL,
	MYT_BOOKENROL_VIEW,

	// ��ѯԤԼ�ظ����طã���
	MYT_REVERT,

	// ��ѯ�Ʒ���ˮ��
	MYT_CONSWATER,

	// ��ѯ�Ʒ���ˮ����Ȼطñ�
	MYT_CONSWATER_SATISFACTION,

	// ҽ��������Ϣ��
	BASE_DOCTOR_VIEW,

	// ��Ա����Ϣ��
	ACC_MEMBERSHIPCARD,

	// ��Ա��������Ϣ��
	ACC_CARDTYPE,
	
	// 
	ACC_PACKAGE,
	
	// �Ƽ�ҽ����Ϣ��
	MYT_MAININTRODOCTOR,
	
	// ������˱�
	DATA_CHECK,
	
	// ������˽����
	DATA_CHECKRESULT,
	
	// ҽ��������Ϣ��
	BASE_DOCTOR,
	
	// ������Ϣ��
	BASE_SMALLDEPARTMENT,
	
	//�û���ѯҽ������
	Doctor_AppointReport,
	
	DC_Bill,//ҽ���˻���Ŀ���ʵ���ϸ��
	DC_FeeTemplate,// (�ʷ�ģ���)
	DC_ServiceFeeConfig,//(�������շ����ñ�)
	DC_DoctorAccount ,//(ҽ���˻���)
	MYT_SelfHelp,//
	v_DoctorAccPrice,//ҽ���ʷѱ�
	ZiXun_Reply,//�ظ���
	ZiXun_QuesEvaluate,//���ۼ�¼��
	ZiXun_QuesMain,//�����
	ZiXun_AnswerRecord,//�����
	ZiXun_DocSubCloseQues,//ҽ���ύ�ر������
	ZiXun_Files,//������
	ZiXun_ConsumerOrders,//���Ѷ�����
	ZiXun_Departments,//�����-����
	ZiXun_Patient,//���߱�
	ZiXun_CreditsRecord,//����ֵ��
	ZiXun_FailOverQues,//����ر���־��
	ZiXun_DoctorFreeCount,//ҽ�������ѯ��
	ZiXun_DoctorFreeCountEdit,//ҽ�������ѯ������־��
	ZiXun_UserFreeCount,//�û������ѯ��ȡ��¼��
	ZiXun_QuesReplyMutual,//������
	ZiXun_Disease,//������
	ZiXun_QuesCloseWater//�رռ�¼��
	
	
}
