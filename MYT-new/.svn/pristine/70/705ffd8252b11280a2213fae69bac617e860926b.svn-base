package com.yihu.myt.enums;

/**
 * 状态值表示含义
 * @author wangfeng
 * @company yihu.com
 * 2012-8-8上午08:39:41
 */
public enum MytConst {

	// 状态枚举
	INVALID(0, "无效"),
	EFFECTIVE(1, "有效"),
	PAUSE(2, "暂停"),
	
	// 是否:全科咨询/推荐医生有效/指定医生/付费/账单发放/回复/网站使用状态有效/欠费咨询
	NO(0, "否"),
	YES(1, "是"),
	
	// 男女
	MALE(1, "男"),
	FEMALE(2, "女"),
	UKNOW(3, "未知"),
	// 咨询类型
	CONSTYPE_SPECIFIED(1, "指定咨询"),
	CONSTYPE_RECOMMEND(2, "推荐咨询"),

	// 推荐/转接结果
	SUCCESS(1, "成功"),
	FAILURE(2, "失败"),
	
	// 推荐失败原因
	VOUCHDEFEAT_NONE(1, "无"),
	VOUCHDEFEAT_HIGHPAY(2, "费用高"),
	VOUCHDEFEAT_UNNEED(3, "不需要"),
	VOUCHDEFEAT_GOHOSP(4, "去医院就诊"),
	VOUCHDEFEAT_LESSCASH(5, "余额不足"),
	
	// 转接失败原因
	TURNDEFEAT_NONE(1, "无"),
	TURNDEFEAT_OFFLINE(2, "医生不在线"),
	TURNDEFEAT_SYSTEM(3, "系统问题"),
	TURNDEFEAT_HIGHPAY(4, "费用太高"),
	TURNDEFEAT_UNANSWER(5, "医生无人接听"),
	TURNDEFEAT_POWEROFF(6, "关机"),
	TURNDEFEAT_BUSY(7, "占线"),
	TURNDEFEAT_REJECT(8, "拒接"),
	TURNDEFEAT_INCANSWER(9, "不方便接听"),
	TURNDEFEAT_DOWNTIME(10, "停机"),
	TURNDEFEAT_UNCONNECT(11, "暂时无法接通"),
	
	// 回复时段
	RSPERIOD_MORNING(1, "上午"),
	RSPERIOD_AFTERNOON(2, "下午"),
	RSPERIOD_EVENING(3, "晚上"),
	RSPERIOD_ALL(4, "全体"),
	RSPERIOD_ONLINE(5, "医生在线咨询"),
	
	// 回复结果
	UN_RESPONSE(1, "未回复"),
	BE_RESPONSE(2, "待回复"),
	HAS_ADVISORY(3, "已咨询成功"),
	MEMBER_UNADVISORY(4, "会员不咨询"),
	DOCTOR_UNCONNECT(5, "医生无法接通"),
	MEMBER_UNCONNECT(6, "会员无法接通"),
	MEMBER_SITE_CANCEL(7, "会员网站自动取消"),
	BOOKENOL_AGAIN(8, "重新登记"),
	LESS_BALANCE(9, "余额不足"),
	BOOKENOL_ERROR(10, "登记错误"),
	
	// 不回复原因
	NOREVERT_NONE(1, "无"),
	NOREVERT_GOHOSP(2, "去医院就诊"),
	NOREVERT_UNADVICE(3, "来电告之不咨询"),
	NOREVERT_ADVICED(4, "已咨询"),
	NOREVERT_NOREASON(5, "会员不需要但未说明原因"),
	NOREVERT_WRONGPHONE(6, "电话有误"),
	NOREVERT_WRONGDIAL(7, "打错电话"),
	
	// 支付方式
	PAYTYPE_CASH(1, "现金支付"),
	PAYTYPE_BANK(2, "银行转账"),
	
	// 结算方式
	BALANCETYPE_MONTH(1, "月结"),
	BALANCETYPE_SEASON(2, "季结"),
	BALANCETYPE_2SEASONS(3, "半年结算"),
	BALANCETYPE_YEAR(4, "年度结算"),
	BALANCETYPE_ALL(5, "累积金额结算"),
	
	// 是否满足条件支付
	IFOUT_UNPAY(0, "未支付"),
	IFOUT_PAYED(1, "支付"),
	
	// 满意度调查
	SCORE_WRONGBUTTON(-1, "按错键"),
	SCORE_VERYSATISFIED(1, "非常满意"),
	SCORE_SATISFIED(2, "满意"),
	SCORE_NORMAL(3, "一般"),
	SCORE_UNSATISFIED(4, "不满意"),
	
	// 医生级别
	DOCTORLEVEL_ORDINARY_PRACTITIONER(0, "普通医生"),
	DOCTORLEVEL_SPECIAL_ALLOWANCE(1, "国务院特殊津贴专家"),
	DOCTORLEVEL_SPECIAL_CLINIC_EXPERTS(2, "主任医生"),
	DOCTORLEVEL_DIRECTOR(3, "主副主任医生"),
	DOCTORLEVEL_VICE_DIRECTOR(4, "特诊专家"),
	DOCTORLEVEL_DOCTOR(5, "主治医生"),
	DOCTORLEVEL_LEAD_NURSE(6, "主任护师"),
	DOCTORLEVEL_GENERAL_PRACTITIONER(7, "全科医生"),
	
	// 查询模式
	QUERY_EXT(1, "ext查询"),
	QUERY_JS(2, "js查询");
	
	
	private Integer value;
	private String info;

	MytConst(Integer value, String info) {
		this.value = value;
		this.info = info;
	}

	public Integer getValue() {
		return value;
	}
	
	public String getInfo() {
		return info;
	}

	public static boolean isValue(Integer value) {
		for (MytConst u : MytConst.values()) {
			if (u.getValue().equals(value))
				return true;
		}
		return false;
	}

	public static MytConst valueOf(Integer value) {
		for (MytConst u : MytConst.values()) {
			if (u.getValue().equals(value))
				return u;
		}
		return null;
	}

	public String toString() {
		return "ParmConst{" + "value=" + value + " info=" + info + "}";
	}
}
