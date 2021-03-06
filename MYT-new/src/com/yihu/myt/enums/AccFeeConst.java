package com.yihu.myt.enums;

public class AccFeeConst {
	
	/**
	 * 0普通医生；1国务院津贴专家；2主任医生；3副主任医生；4特诊专家；5主治医生；6主任护师；7全科医生
	 * @param doctorLevel
	 * @param orgid
	 * @return
	 */
	public static int[] const_3(int doctorLevel,int orgid)
	{
		switch(orgid)
		{
			case 1://福建
				return new int[][]{{5,2000,300},{5,10000,1200},{5,3000,400},{5,3000,400},{5,5000,600},{5,2000,300},{5,2000,300},{1,400,400}}[doctorLevel];
			case 291://湖北
				return new int[][]{{5,2000,300},{5,10000,1200},{5,3000,400},{5,3000,400},{5,5000,600},{5,2000,300},{5,2000,300},{1,400,400}}[doctorLevel];
			case 381://厦门
				return new int[][]{{5,2000,300},{5,10000,1200},{5,3000,400},{5,3000,400},{5,5000,600},{5,2000,300},{5,2000,300},{1,400,400}}[doctorLevel];
			case 211://江西
				return new int[][]{{5,2000,300},{5,10000,1200},{5,3000,400},{5,3000,400},{5,5000,600},{5,2000,300},{5,2000,300},{1,400,400}}[doctorLevel];
			case 292://陕西
				return new int[][]{{5,2000,300},{5,10000,1200},{5,3000,400},{5,3000,400},{5,5000,600},{5,2000,300},{5,2000,300},{1,400,400}}[doctorLevel];
			case 71://广州
				return new int[][]{{5,2000,300},{5,10000,1200},{5,3000,400},{5,3000,400},{5,5000,600},{5,2000,300},{5,2000,300},{1,400,400}}[doctorLevel];
			case 820://湖南
				return new int[][]{{5,2000,300},{5,10000,1200},{5,3000,400},{5,3000,400},{5,5000,600},{5,2000,300},{5,2000,300},{1,400,400}}[doctorLevel];
			case 826://上海
				return new int[][]{{5,3000,400},{5,20000,2000},{5,8000,1000},{5,8000,1000},{5,15000,1500},{5,3000,400},{5,3000,400},{5,2000,300}}[doctorLevel];
			case 99999://北京
				return new int[][]{{5,3000,400},{5,20000,2000},{5,8000,1000},{5,8000,1000},{5,15000,1500},{5,3000,400},{5,3000,400},{5,2000,300}}[doctorLevel];				
			
			default:return null;
		}
	}
	public static int[] const_old(int doctorLevel,int orgid)
	{
		switch(orgid)
		{
			case 1://福建
				return new int[][]{{5,2000,300},{5,10000,1200},{5,3000,400},{5,3000,400},{5,5000,600},{5,2000,300},{5,2000,300},{1,200,200}}[doctorLevel];
			case 291://湖北
				return new int[][]{{5,2000,300},{5,10000,1200},{5,3000,400},{5,3000,400},{5,5000,600},{5,2000,300},{5,2000,300},{1,200,200}}[doctorLevel];
			case 381://厦门
				return new int[][]{{5,2000,300},{5,10000,1200},{5,3000,400},{5,3000,400},{5,5000,600},{5,2000,300},{5,2000,300},{1,200,200}}[doctorLevel];
			case 211://江西
				return new int[][]{{5,2000,300},{5,10000,1200},{5,3000,400},{5,3000,400},{5,5000,600},{5,2000,300},{5,2000,300},{1,200,200}}[doctorLevel];
			case 292://陕西
				return new int[][]{{5,2000,300},{5,10000,1200},{5,3000,400},{5,3000,400},{5,5000,600},{5,2000,300},{5,2000,300},{1,200,200}}[doctorLevel];
			case 71://广州
				return new int[][]{{5,2000,300},{5,10000,1200},{5,3000,400},{5,3000,400},{5,5000,600},{5,2000,300},{5,2000,300},{1,200,200}}[doctorLevel];
			case 826://上海
				return new int[][]{{5,3000,400},{5,20000,2000},{5,8000,1000},{5,8000,1000},{5,15000,1500},{5,3000,400},{5,3000,400},{1,200,200}}[doctorLevel];
			case 99999://北京
				return new int[][]{{5,3000,400},{5,20000,2000},{5,8000,1000},{5,8000,1000},{5,15000,1500},{5,3000,400},{5,3000,400},{1,200,200}}[doctorLevel];				
			
			default:return null;
		}
	}
	public static int[] const_4(int doctorLevel,int orgid)
	{
		switch(orgid)
		{
			case 1://福建
				return new int[][]{{5,1000,200},{5,6000,600},{5,2000,200},{5,2000,200},{5,3000,300},{5,1000,200},{5,2000,200},{1,200,200}}[doctorLevel];
			case 291://湖北
				return new int[][]{{5,1000,200},{5,6000,600},{5,2000,200},{5,2000,200},{5,3000,300},{5,1000,200},{5,1000,200}}[doctorLevel];
			case 381://厦门
				return new int[][]{{5,1000,200},{5,6000,600},{5,2000,200},{5,2000,200},{5,3000,300},{5,1000,200},{5,1000,200}}[doctorLevel];
			case 211://江西
				return new int[][]{{5,1500,200},{5,3000,300},{5,1500,200},{5,1500,200},{5,1500,200},{5,1500,200},{5,1500,200},{1,200,200}}[doctorLevel];
			case 292://陕西
				return new int[][]{{5,1000,200},{6,6000,600},{5,2000,300},{5,2000,300},{5,2000,300},{5,1000,200},{5,1000,200}}[doctorLevel];
			case 71://广州
				return new int[][]{{5,2000,200},{5,6000,600},{5,3000,300},{5,3000,300},{5,3000,300},{5,2000,200},{5,2000,200},{120,0,0}}[doctorLevel];				
			
			default:return null;
		}
	}
}
