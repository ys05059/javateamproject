package ��;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import set����class.Set;
import set����class.c_exRecord;
import set����class.c_set;
import set����class.dayRecord;
import set����class.exRecord;
import set����class.exercise;
import set����class.t_exRecord;
import set����class.t_set;
import set����class.wc_exRecord;
import set����class.wc_set;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

public class DayStatisticsFunc {
	private LocalDate today;
	private String totalTimeStr;
	private String totalSetCntStr;
	private String totalWeightsStr;
	private String sen1;
	
	private ArrayList<String> cateStr;
	
	private String statistics="";
	
	private LocalTime totalTime;
	private int setCnt;
	private double totalWeight;
	
	private ArrayList<dayRecord> curr_ary;

	private ArrayList<c_set> cset;
	private ArrayList<t_set> tset;
	private ArrayList<wc_set> wcset;
	
	int[] cnts;
	
	HashMap<String, int[]> catemap; // { ī�װ� : {� ���� , � ��Ʈ�� } }
	public DayStatisticsFunc(ArrayList<dayRecord> dR_ary, LocalDate currDate) {
		totalTimeStr = "�� ����ð� : ";
		totalSetCntStr = "�� ��Ʈ �� : ";
		totalWeightsStr = "�� ���� : ";
		sen1 = "ī�װ��� ���";
		cateStr = new ArrayList<String>();
		catemap = new HashMap<String, int[]>();
		statistics="";

		totalTime=LocalTime.of(0, 0, 0, 0);
		setCnt=0;
		totalWeight=0;
		
		curr_ary = dR_ary;
		today = currDate;
				
		calTime(today);
		calSetCnt(today);
		calWeights(today);
		cateStatistic(today);
	}
	public void calTime(LocalDate today) { // t_exRecord Ŭ���� ���� �ð� ����
		for(dayRecord dr : curr_ary) {
			if(dr.getToday_date().equals(today)) {
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
		}
		if(totalTime.getSecond()==0)
			totalTimeStr+=totalTime.toString()+":00";
		else 
			totalTimeStr+=totalTime.toString();
	}
	
	public void calSetCnt(LocalDate today) { // ��� � ��Ʈ�� ��
		for(dayRecord dr : curr_ary) {
			if(dr.getToday_date().equals(today)) {
				for(exRecord er : dr.getExr_ary()){
					setCnt+=er.getCount_set();
				}
			}
		}
		totalSetCntStr += String.valueOf(setCnt);
	}
	
	public void calWeights(LocalDate today) { // wc_exRecord ����*Ƚ���� �ѹ��� ���ϱ�
		for(dayRecord dr : curr_ary) {
			if(dr.getToday_date().equals(today)) {
				for(exRecord er : dr.getExr_ary()){
					if(er instanceof wc_exRecord) {
						wcset = ((wc_exRecord) er).getWc_set_ary();
						for(int i=0;i<wcset.size();i++) {
							totalWeight+=wcset.get(i).getP_weight() * wcset.get(i).getP_count();
						}
					}
				}
			}
		}
		totalWeightsStr+=String.valueOf(totalWeight);
	}
	public void cateStatistic(LocalDate today) {	//ī�װ� �� � ���� ���ϴ� �޼ҵ�
		for(dayRecord dr: curr_ary) {
			if(dr.getToday_date().equals(today)) {
				for(exRecord er : dr.getExr_ary()) {
					exercise e = er.getEx();
					if(!catemap.containsKey(e.getcategory())) { // ī�װ� �ʿ� �ش� ��� ī�װ��� ������ ī�װ� �߰�
						cnts = new int[2];
						catemap.put(e.getcategory(), cnts);
					}
					catemap.get(e.getcategory())[0]+=1;
					catemap.get(e.getcategory())[1]+=er.getCount_set();
				}
			}
		}
		Iterator<String> iter = catemap.keySet().iterator();
		while(iter.hasNext()) {
			String key = iter.next();
			cateStr.add(key + " " + "� �� : " + catemap.get(key)[0] + 
					" ��Ʈ �� : " + catemap.get(key)[1]);
		}
	}
	public String makeTextArea() { // DayStatisticsDemo�� statistics String������ �Ҵ�
		statistics = "";
		statistics += totalTimeStr + '\n';
		statistics += totalSetCntStr + '\n';
		statistics += totalWeightsStr + '\n';
		statistics += sen1 + '\n';
		
		for(String s : cateStr)
			statistics += s + '\n'; 

		return statistics;
	}

}