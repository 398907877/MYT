package com.yihu.myt.service.service.impl;
import java.sql.SQLException;
import java.util.List;
import com.coreframework.db.DB;
import com.coreframework.db.JdbcConnection;
import com.coreframework.db.Sql;
import com.yihu.myt.enums.MyDatabaseEnum;
import com.yihu.myt.enums.MyTableNameEnum;
import com.yihu.myt.enums.QuesEvaluateSqlNameEnum;
import com.yihu.myt.util.StringUtil;
import com.yihu.myt.vo.QuesEvaluateVo;
import com.yihu.myt.service.service.IQuesEvaluateService;

public class QuesEvaluateService implements IQuesEvaluateService{
	/**
	*��ȡ�б��¼��
	*/
	public Integer queryQuesEvaluateCountByCondition(QuesEvaluateVo vo) throws Exception{
		Sql sql = DB.me().createSql(QuesEvaluateSqlNameEnum.queryQuesEvaluateCountByCondition);
		StringBuilder condition = new StringBuilder();
		sql.addVar("@condition", condition.toString());
		Integer count = DB.me().queryForInteger(MyDatabaseEnum.YiHuNet2008, sql);
		return count;
	}
	/**
	*��ȡ�б�
	*/
	public List<QuesEvaluateVo> queryQuesEvaluateListByCondition(QuesEvaluateVo vo) throws Exception{
		Sql sql = DB.me().createSql(QuesEvaluateSqlNameEnum.queryQuesEvaluateListByCondition);
		StringBuilder condition = new StringBuilder();
		sql.addVar("@condition", condition.toString());
		sql.addVar("@page", "");
		List<QuesEvaluateVo> list = DB.me().queryForBeanList(MyDatabaseEnum.YiHuNet2008, sql,QuesEvaluateVo.class);
		return list;
	}
	/**
	*���
	*/
	public int insertQuesEvaluate(QuesEvaluateVo vo) throws Exception{
		try {
			int r = DB.me().insert(
					MyDatabaseEnum.YiHuNet2008,
					DB.me().createInsertSql(vo, MyTableNameEnum.ZiXun_QuesEvaluate,
							"dbo"));
			return r;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		
		
	}
	/**
	*���
	*/
	public int insertQEvaluate(QuesEvaluateVo vo,JdbcConnection conn) throws Exception{
		try {
			int r = DB.me().insert(
					conn,
					DB.me().createInsertSql(vo, MyTableNameEnum.ZiXun_QuesEvaluate,
							"dbo"));
			return r;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		
		
	}
	/**
	*�޸�
	*/
	public void updateQuesEvaluateByCondition(QuesEvaluateVo vo,JdbcConnection conn) throws Exception{Sql sql = DB.me().createSql(QuesEvaluateSqlNameEnum.updateQuesEvaluateByCondition);
		if (vo == null  || StringUtil.isEmpty(vo.getELT_ID())) {
			throw new Exception(" ����Ϊ�ջ��� ����id ����Ϊ��");
		}
		StringBuilder condition = new StringBuilder();
		if (condition.length() == 0) {
		throw new Exception("δ�и���SQL��ִ�У�");
		} else {
		condition.deleteCharAt(condition.length() - 1);
		sql.addVar("@condition", condition.toString());
		sql.addParamValue(vo.getELT_ID());
		}
		DB.me().update(conn, sql);
		}
}
