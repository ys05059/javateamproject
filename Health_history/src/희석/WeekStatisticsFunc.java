package 희석;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.StringTokenizer;

import set단위class.Set;
import set단위class.dayRecord;
import set단위class.exRecord;
public class WeekStatisticsFunc {
	private String totalTimeStr = "총 수행시간 : ";
	private String totalSetCntStr = "총 세트 수 : ";
	private String totalWeightsStr = "총 무게 : ";
	private String sen1 = "카테고리별 통계";
	private String[] cateStr;
	
	private String statistics="";
	
	private String totalTIme="";
	private int setCnt=0;
	private double totalWeight=0;
	
	ArrayList<dayRecord> curr_ary;
	
	public WeekStatisticsFunc(ArrayList<dayRecord> dR_ary) {
		curr_ary = dR_ary;
		calTime(dR_ary);
		calSetCnt(dR_ary);
		calWeights(dR_ary);
	}
	
	public void calTime(ArrayList<dayRecord> curr_ary) {
		
	}
	
	public void calSetCnt(ArrayList<dayRecord> curr_ary) { // 
		for(dayRecord dr : curr_ary) {
			for(exRecord er : dr.getExr_ary()){
				setCnt+=er.getSet_goal();
			}
		}
		
		totalSetCntStr += String.valueOf(setCnt);
	}
	
	public void calWeights(ArrayList<dayRecord> curr_ary) {
		for(dayRecord dr : curr_ary) {
			for(exRecord er : dr.getExr_ary()){
				//totalWeight;
			}
		}
		
		//totalWeight += String.valueOf(setCnt);
	}
	
	public String makeTextArea() {
		statistics = "";
		statistics += totalTimeStr + '\n';
		statistics += totalSetCntStr + '\n';
		statistics += totalWeightsStr + '\n';
		statistics += sen1 + '\n';
		
		//for(String s : cateStr)
			//statistics += s + '\n'; 

		return statistics;
	}

	public static int chkDateSeq(String startDate, String endDate) {
		StringTokenizer dateTokens = new StringTokenizer(startDate, "-" , false);
		
		int y = Integer.parseInt(dateTokens.nextToken());
		int m = Integer.parseInt(dateTokens.nextToken());
		int d = Integer.parseInt(dateTokens.nextToken());
				
		LocalDate sld = LocalDate.of(y, m, d);
		
		dateTokens = new StringTokenizer(endDate, "-", false);	 
		y = Integer.parseInt(dateTokens.nextToken());
		m = Integer.parseInt(dateTokens.nextToken());
		d = Integer.parseInt(dateTokens.nextToken());
		
		LocalDate eld = LocalDate.of(y, m, d);
				
		if(eld.isEqual(sld)) // 시작일 종료일 같을때 
			return 0; 
		else if(eld.isAfter(sld)) // 종료일이 시작일보다 먼저올때
			return 1;
		else
			return -1;
		
	}
}