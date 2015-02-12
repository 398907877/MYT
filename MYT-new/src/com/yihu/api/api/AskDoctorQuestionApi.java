package com.yihu.api.api;

import com.yihu.api.impl.AskDoctorQuestionApiImpl;
import com.coreframework.ioc.By;
import com.coreframework.vo.ServiceResult;
import com.yihu.wsgw.api.InterfaceMessage;

@By(AskDoctorQuestionApiImpl.class)
public interface AskDoctorQuestionApi {
	public ServiceResult<String> overQuertion(String jsonStr);
	public ServiceResult<String> saveQuertion(String jsonStr);
	public String getCountQues(InterfaceMessage msg);
	public String getTeamQues(InterfaceMessage msg);
	public String getQuesCountForDoctor(InterfaceMessage msg) ;
	public String getQuesListByUser(InterfaceMessage msg) ;
	public String getReplyQues(InterfaceMessage msg);
	public String getQuesContentByQuesID(InterfaceMessage msg) ;
	public String getQuesContentByQuesIDForCheck(InterfaceMessage msg) ;
	public String doctorAnswer(InterfaceMessage msg) ;
	public String getQueReplyByQuesID(InterfaceMessage msg) ;
	public String replyQuertionDoctor(InterfaceMessage msg) ;
	public String overQuertionV2(InterfaceMessage msg);
}
