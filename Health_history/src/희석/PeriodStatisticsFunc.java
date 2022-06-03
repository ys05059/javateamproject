package ��;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

import set����class.Set;
import set����class.c_set;
import set����class.dayRecord;
import set����class.exRecord;
import set����class.exercise;
import set����class.t_exRecord;
import set����class.t_set;
import set����class.wc_exRecord;
import set����class.wc_set;
public class PeriodStatisticsFunc {
	private String startStr;
	private String endStr;
	
	private LocalDate startDay;
	private LocalDate endDay;
	
	private String totalTimeStr;
	private String totalSetCntStr;
	private String totalWeightsStr;
	private String sen1;
	private ArrayList<String> cateStr;
	
	private String statistics;
	
	private LocalTime totalTime;
	private int setCnt;
	private double totalWeight;
	
	private ArrayList<dayRecord> curr_ary;

	private ArrayList<c_set> cset;
	private ArrayList<t_set> tset;
	private ArrayList<wc_set> wcset;
	
	private int[] cnts;
	private double[] percent;
	private HashMap<String, int[]> catemap; // { ī�װ� : {� ���� , � ��Ʈ��, � ��ǥ��Ʈ ��} }
	private HashMap<String, double[]> catemapPercent;
	public PeriodStatisticsFunc(ArrayList<dayRecord> dR_ary, LocalDate sDay, LocalDate eDay) {
		totalTimeStr = "�� ����ð� : ";
		totalSetCntStr = "�� ��Ʈ �� : ";
		totalWeightsStr = "�� ���� : ";
		sen1 = "ī�װ��� ���\t\t��Ʈ����\t��Ʈ������";
		cateStr = new ArrayList<String>();
		statistics="";
		totalTime=LocalTime.of(0, 0, 0);
		setCnt=0;
		totalWeight=0;
		catemap = new HashMap<String, int[]>();
		catemapPercent = new HashMap<String, double[]>();
		curr_ary = dR_ary;
		startStr = sDay.toString();
		endStr = eDay.toString();
		startDay = sDay;
		endDay = eDay;
		
		calTime(curr_ary);
		calSetCnt(curr_ary);
		calWeights(curr_ary);
		cateStatistic(curr_ary);
	}
	public LocalDate makeLocalDate(String dayStr) {
		StringTokenizer dateTokens = new StringTokenizer(dayStr, "-" , false);
		
		int y = Integer.parseInt(dateTokens.nextToken());
		int m = Integer.parseInt(dateTokens.nextToken());
		int d = Integer.parseInt(dateTokens.nextToken());
		
		LocalDate ld = LocalDate.of(y, m, d);
		return ld;
	}
	
	public void calTime(ArrayList<dayRecord> curr_ary) {
		for(dayRecord dr : curr_ary) {
			for(exRecord er : dr.getExr_ary()) {
				if(er instanceof t_exRecord) {
					tset = ((t_exRecord) er).gett_set_ary();
					for(int i=0; i < tset.size();i++) {
						totalTime = totalTime.plusSeconds(tset.get(i).getP_time().getSecond());
						totalTime = totalTime.plusMinutes(tset.get(i).getP_time().getMinute());
						totalTime = totalTime.plusHours(tset.get(i).getP_time().getHour());
					}
				}
			}
		}
		if(totalTime.getSecond()==0)
			totalTimeStr+=totalTime.toString()+":00";
		else 
			totalTimeStr+=totalTime.toString();
	}
	
	public void calSetCnt(ArrayList<dayRecord> curr_ary) { // 
		for(dayRecord dr : curr_ary) {
			for(exRecord er : dr.getExr_ary()){
				setCnt+=er.getCount_set();
			}
		}
		
		totalSetCntStr += String.valueOf(setCnt);
	}
	
	public void calWeights(ArrayList<dayRecord> curr_ary) {
		for(dayRecord dr : curr_ary) {
			for(exRecord er : dr.getExr_ary()){
				if(er instanceof wc_exRecord ) {
					wcset = ((wc_exRecord) er).getWc_set_ary();
					for(int i=0;i<wcset.size();i++) {
						totalWeight+=wcset.get(i).getP_weight() * wcset.get(i).getP_count();
					}
				}
			}
		}
		totalWeightsStr+=String.valueOf(totalWeight);
	}
	public void cateStatistic(ArrayList<dayRecord> curr_ary) {
		for(dayRecord dr: curr_ary) {
			for(exRecord er : dr.getExr_ary()) {
				exercise e = er.getEx();
				if(!catemap.containsKey(e.getcategory())) { // ī�װ� �ʿ� �ش� ��� ī�װ��� ������ ī�װ� �߰�
					cnts = new int[3];
					percent = new double[2];
					catemap.put(e.getcategory(), cnts);
					catemapPercent.put(e.getcategory(), percent);
				}
				catemap.get(e.getcategory())[0]+=1;
				catemap.get(e.getcategory())[1]+=er.getCount_set();
				catemap.get(e.getcategory())[2]+=er.getSet_goal();
			}
		}
		
		Iterator<String> iter = catemapPercent.keySet().iterator();
		while(iter.hasNext()) {
			
			String key = iter.next();
			catemapPercent.get(key)[0]=((double)catemap.get(key)[1]/setCnt)*100;
			catemapPercent.get(key)[1]=((double)catemap.get(key)[1]/catemap.get(key)[2])*100;
		}
		
		iter = catemapPercent.keySet().iterator();
		while(iter.hasNext()) {
			String key = iter.next();
			cateStr.add(key + " " + "� �� : " + catemap.get(key)[0] + 
					" ��Ʈ �� : " + catemap.get(key)[1] + "\t"+ Math.round(catemapPercent.get(key)[0])
					+"% \t" + Math.round(catemapPercent.get(key)[1])+"%");
		}
		
	}

	public String makeTextArea() {
		statistics = "";
		statistics += totalTimeStr + '\n';
		statistics += totalSetCntStr + '\n';
		statistics += totalWeightsStr + '\n';
		statistics += sen1 + '\n';
		
		for(String s : cateStr)
			statistics += s + '\n'; 

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
				
		if(eld.isEqual(sld)) // ������ ������ ������ 
			return 0; 
		else if(eld.isAfter(sld)) // �������� �����Ϻ��� �ڿ��ö�
			return 1;
		else
			return -1;
		
	}
}