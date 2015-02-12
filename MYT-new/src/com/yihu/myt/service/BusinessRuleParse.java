package com.yihu.myt.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;


import com.coreframework.db.DB;
import com.coreframework.db.Sql;
import com.yihu.myt.enums.BaseDictionary;
import com.yihu.myt.enums.MyDatabaseEnum;
import com.yihu.myt.enums.MySqlNameEnum;
import com.yihu.myt.vo.GhtHospitalView;

public class BusinessRuleParse {
	private Element ruleNode;
	private String businessName;
	private String nextrow="\r\n";
	private int cardtypesn;
	
	private void fromCardType() throws Exception
	{
		//if(!this.isPkg()||cardID==null||cardID.equals(""))
		//{
			String[] config=ConfigManage.get(cardtypesn);				
			if(config==null)
			{
				throw new Exception("未找到卡类型规则配置");
			}
			Document doc=DocumentHelper.parseText(config[1]);
			List nodeList=doc.selectNodes("//Business");
			for(int i=0;i<nodeList.size();i++)
			{
				Element current=(Element)nodeList.get(i);
				if(current.attributeValue("name").equals(businessName))
				{
					ruleNode=current;
					dealEmptyNode(ruleNode);
					//System.out.println(ruleNode);
					break;
				}
				
			}
		//}
	}
	private void fromPkg(String cardID) throws Exception
	{
		//查找此卡号订购的套餐
		Sql sql = DB.me().createSql(MySqlNameEnum.getAccJkgjAccountPkgId);
		sql.addParamValue(cardID);
		Integer pkgid = DB.me().queryForInteger(MyDatabaseEnum.boss, sql);
		if(pkgid==null)
		{
//			throw new Exception("未指定套餐资费");
			fromCardType();
			return;
		}
		String[] config=PkgConfigManage.get(pkgid);	
		if(config==null)
		{
			//throw new Exception("未找到套餐规则配置");
			fromCardType();
			return;
		}
		Document doc=DocumentHelper.parseText(config[1]);
		List nodeList=doc.selectNodes("//Business");
		for(int i=0;i<nodeList.size();i++)
		{
			Element current=(Element)nodeList.get(i);
			if(current.attributeValue("name").equals(businessName))
			{
				ruleNode=current;
				dealEmptyNode(ruleNode);
				//System.out.println(ruleNode);
				break;
			}
			
		}
		if(ruleNode==null)
		{
			fromCardType();
		}
	}
	public BusinessRuleParse(String cardID,int cardtypesn,String businessName) throws Exception
	{
		this.cardtypesn=cardtypesn;
		this.businessName=businessName;
		if(!CoopDictionary.isCoop(cardtypesn)||cardID==null||cardID.equals(""))
		{
			fromCardType();
			return;
		}
		fromPkg(cardID);
	}
	
	public  void dealEmptyNode(Element node)
	{
		if(node==null)
		{
			return;
		}
		List list=node.elements();
		//System.out.println(list.getLength());
		for(int i=0;i<list.size();i++)
		{
			Element current=(Element)list.get(i);
			//System.out.println("*"+current.getNodeName()+"*");
			if(current==null||current.getName().trim().equals("#text"))
			{
				node.remove(current);
				//.removeChild(current);
				//System.out.println(node);
			}
		}
	}
	private void  appendLine(StringBuffer sb,String str)
	{
		sb.append(str);
		sb.append(nextrow);
	}
	/**
	 * 获取业务规则的文字描述
	 * @return
	 * @throws Exception 
	 * @throws Exception
	 */
	public String getHosNames(String ghtHosIDS) throws Exception {
		
		if(ghtHosIDS.endsWith(","))
		{
			ghtHosIDS=ghtHosIDS.substring(0, ghtHosIDS.length()-1);
		}
		Sql sql = DB.me().createSql(MySqlNameEnum.queryGhtHospitalViewSql);
		sql.addVar("@p", "ghthospitalid in(" + ghtHosIDS + ")");
		List hosList = DB.me().queryForBeanList(MyDatabaseEnum.boss, sql,
				GhtHospitalView.class);

		String ret = "";
		for (int i = 0; i < hosList.size(); i++) {
			ret = ret + ((GhtHospitalView) hosList.get(i)).getCname() + ",";
		}
		if(ret.endsWith(","))
		{
			return ret.substring(0, ret.length()-1);
		}
		return ret;
	}
	
	/**
	 * 解析生成业务规则对象
	 * @return
	 * @throws Exception
	 */ 
	public BusinessRule.Rule parse() throws Exception
	{
		
		BusinessRule  ruleObj=new BusinessRule();
		if(businessName.equals(BaseDictionary.Business.ght))
		{
			BusinessRule.GHTRule  ghtRuleObj=ruleObj.new GHTRule();
			ghtRuleObj.setPermit(true);
			if(ruleNode==null)
			{
				return ghtRuleObj;
			}
			
			List nodeList=ruleNode.elements();
			//System.out.println(nodeList.getLength());
			for(int i=0;i<nodeList.size();i++)
			{
				Element current=(Element)nodeList.get(i);
				//System.out.println("current.getNodeName()="+current.getNodeName());
				if(current.getName().equals("Item_ForwardDay"))
				{
					HashMap  map=new HashMap();
					map.put("0",current.attributeValue("default"));
					List cityNodeList=current.selectNodes("//city");
					
					for(int j=0;j<cityNodeList.size();j++)
					{
							Element city=(Element)cityNodeList.get(j);
							map.put(city.attributeValue("id"),city.getTextTrim());
					}
					
					ghtRuleObj.setForwardDay(map);
					continue;
				}
				if(current.getName().equals("Item_hasGhFee"))
				{
					//System.out.println(BNXmlUtil.getNodeValue(current));
					if(current.getTextTrim().equals("false"))
					{
						ghtRuleObj.setHasGhFee(false);
					}
					else
					{
						ghtRuleObj.setHasGhFee(true);
					}
					continue;
				}
				if(current.getName().equals("Item_occupyWay"))
				{
					ghtRuleObj.setOccupyWay(Integer.parseInt(current.getTextTrim()));
					continue;
				}
				if(current.getName().equals("Item_HospitalRestrain"))
				{
					String[] s=new String[]{current.attributeValue("type"),current.getTextTrim()};
					ghtRuleObj.setHospitalRestrain(s);
					continue;
				}
				if(current.getName().equals("Item_NumLimit"))
				{
					HashMap  map=new HashMap();
					map.put("0",current.attributeValue("default"));
					List numLimitNodeList=current.selectNodes("//doctor");
						//XmlUtil.findNodes(current, current.getNodeName()+"/doctor");
					for(int j=0;j<numLimitNodeList.size();j++)
					{
							Element doctor=(Element)numLimitNodeList.get(j);
							map.put(doctor.attributeValue("sn"),doctor.attributeValue("count"));
					}
					ghtRuleObj.setNumLimit(map);
					continue;
				}
				if(current.getName().equals("Item_DoctorLevel"))
				{
					ghtRuleObj.setDoctorLevel(current.attributeValue("default"));
				}
			
				if(current.getName().equals("Item_Permit"))
				{
					//System.out.println("current.getTextTrim()="+current.getTextTrim());
					if(current.getTextTrim().equals("false"))
					{
						ghtRuleObj.setPermit(false);
					}
					//ghtRuleObj.setDoctorLevel(current.attributeValue("default"));
				}
			}
			return ghtRuleObj;
		}//end 解析挂号通业务规则
		if(businessName.equals(BaseDictionary.Business.myt))
		{
			
			BusinessRule.MYTRule  mytRuleObj=ruleObj.new MYTRule();
			mytRuleObj.setOk(true);
			mytRuleObj.setQkOk(true);
			if(ruleNode==null)
			{
				return mytRuleObj;
			}
			List nodeList=ruleNode.elements();
			for(int i=0;i<nodeList.size();i++)
			{
				Element current=(Element)nodeList.get(i);
				
				if(current.getName().equals("Item_Permit"))
				{
					
				  mytRuleObj.setOk(Boolean.valueOf(current.getTextTrim()).booleanValue());
				  continue;
				}
				if(current.getName().equals("Item_PermitQK"))
				{
				  mytRuleObj.setQkOk(Boolean.valueOf(current.getTextTrim()).booleanValue());
				  continue;
				}
				if(current.getName().equals("Item_LevelRestrain"))
				{
					
					mytRuleObj.setLevelRestrain(current.getTextTrim());
					continue;
				}
				
			}
			return mytRuleObj;
		}//end 解析名医通业务规则
		if(businessName.equals(BaseDictionary.Business.acc))
		{
			BusinessRule.ACCRule accRuleObj = ruleObj.new ACCRule();
			
			if(ruleNode == null){
				return accRuleObj;
			}
			List nodeList = ruleNode.elements();
			for(int i=0; i<nodeList.size(); i++){
				Element current = (Element)nodeList.get(i);
				if(current.getName().equals("Item_ChargeOk")){	
					accRuleObj.setChargeOk(Boolean.valueOf(current.getTextTrim()).booleanValue());
					continue;
				}	
				if(current.getName().equals("Item_GradeOk")){	
					accRuleObj.setGradeOk(Boolean.valueOf(current.getTextTrim()).booleanValue());
					continue;
				}
				if(current.getName().equals("Item_BackCardOk")){	
					accRuleObj.setBackCardOk(Boolean.valueOf(current.getTextTrim()).booleanValue());
					continue;
				}
				if(current.getName().equals("Item_ReplacementCardOk")){	
					accRuleObj.setReplacementCardOk(Boolean.valueOf(current.getTextTrim()).booleanValue());
					continue;
				}
			}
			return accRuleObj;
		}//end 解析账户业务规则
		return null;
	}
	public String getNextrow() {
		return nextrow;
	}
	public void setNextrow(String nextrow) {
		this.nextrow = nextrow;
	} 
}
