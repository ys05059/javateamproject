package 희석;

import java.util.ArrayList;

import set단위class.Set;
import set단위class.dayRecord;
import set단위class.exRecord;

public class DayStatisticsFunc {
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
	public DayStatisticsFunc(ArrayList<dayRecord> dR_ary) {
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
}