package 희석;

import java.awt.Color;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.style.Styler.LegendPosition;
import org.knowm.xchart.style.lines.SeriesLines;
import org.knowm.xchart.style.markers.SeriesMarkers;

import set단위class.dayRecord;

public class PeriodStatisticsDemo extends JFrame {

	private JLabel date_of_start_day_label;
	private JLabel date_of_end_day_label;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	private JTextArea startDayTextArea;
	private JTextArea endDayTextArea;
	
	private JPanel chartpanel;
	
	private String startDatestr;
	private String endDatestr;
	private LocalDate sld;
	private LocalDate eld;
	public ArrayList<dayRecord> curr_dR_ary;

	public static PeriodStatisticsFunc pfunc;
	
	private String statistics; // 통계내용

	public PeriodStatisticsDemo(ArrayList<dayRecord> dR_ary, LocalDate startld, LocalDate endld) {
		setTitle("WeekStatisticsDemo");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1000,550);
		getContentPane().setLayout(null);
		curr_dR_ary = dR_ary;
		startDatestr=startld.toString();
		endDatestr=endld.toString();
		sld = startld;
		eld = endld;
		
		date_of_start_day_label = new JLabel("시작일");
		date_of_start_day_label.setBounds(41, 39, 50, 15);
		getContentPane().add(date_of_start_day_label);

		startDayTextArea = new JTextArea(startDatestr);
		startDayTextArea.setBounds(91,36,96,21);
		startDayTextArea.setEditable(false);
		getContentPane().add(startDayTextArea);
		
		date_of_end_day_label = new JLabel("종료일");
		date_of_end_day_label.setBounds(277, 39, 50, 15);
		getContentPane().add(date_of_end_day_label);

		endDayTextArea = new JTextArea(endDatestr);
		endDayTextArea.setBounds(330,36,96,21);
		endDayTextArea.setEditable(false);
		getContentPane().add(endDayTextArea);
	
		scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 69, 428, 150);
		getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		pfunc = new PeriodStatisticsFunc(curr_dR_ary, sld, eld);
		statistics = pfunc.makeTextArea();
		textArea.setText(statistics);
		setVisible(true);
		
		
		int count_max=10;
		
		ArrayList<dayRecord> tmp_drary = new ArrayList<>();
		for(dayRecord dr : dR_ary) {
			if(dr.getToday_date().isAfter(startld) && dr.getToday_date().isBefore(endld))
				tmp_drary.add(new dayRecord(dr));
			else if(dr.getToday_date().equals(startld) || dr.getToday_date().equals(endld))
				tmp_drary.add(new dayRecord(dr));
		}
		
		Collections.sort(tmp_drary);
		ArrayList<Date> ary_x = new ArrayList<>();
	    for (dayRecord dr : tmp_drary) {
	    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH");
	    	Date date = new Date();
			try {
				date = df.parse(Integer.toString(dr.getToday_date().getYear())+"-"+Integer.toString(dr.getToday_date().getMonthValue()) +"-"+Integer.toString(dr.getToday_date().getDayOfMonth())+"T09");
			} catch (ParseException e) {
				e.printStackTrace();
			}
	    	ary_x.add(date);
	    }
	    
	    int tmp;
	    ArrayList<Integer> ary_y = new ArrayList<>();
	    for (dayRecord dr : tmp_drary) {
	    	 tmp= dr.get_total_countset();
	    	ary_y.add(tmp);
	    	if(tmp > count_max) {
	    		count_max = (tmp/10+1)*10;
	    	}
	    }
	    
	    XYChart chart = new XYChartBuilder().xAxisTitle("날짜").yAxisTitle("총 세트수").width(600).height(400).build();
		XYSeries series = chart.addSeries("총 세트수" ,ary_x,ary_y);
		series.setMarker(SeriesMarkers.CIRCLE);
		chart.getStyler().setDatePattern("YYYY-MM-dd");
		chart.getStyler().setLegendPosition(LegendPosition.InsideNE);
		chart.getStyler().setXAxisLabelRotation(45);
		chart.getStyler().setYAxisMin(0.0);
		chart.getStyler().setYAxisMax((double)count_max);
		XChartPanel<XYChart> panel=new XChartPanel<XYChart>(chart);
		panel.setBounds(23, 230,428, 250);
		getContentPane().add(panel);
		
		PieChart chart2 = new PieChartBuilder().width(800).height(600).title("카테고리별 세트비율").build();
	    Color[] sliceColors = new Color[] { new Color(237, 85, 101),new Color(252, 110, 81),new Color(255, 206, 84),
	    		new Color(160, 212, 104), new Color(72, 207, 173), new Color(79, 193, 233), new Color(93, 156, 236),
	    		new Color(172, 146, 236), new Color(236, 135, 192),new Color(204, 209, 217)}; 
	    chart2.getStyler().setSeriesColors(sliceColors);
	    
	    HashMap<String, int[]> catemap= pfunc.getCatemap();
	    Iterator<String> iter = catemap.keySet().iterator();
	    String key="";
	    while(iter.hasNext()) {
	    	key  = iter.next();
	    	chart2.addSeries(key, catemap.get(key)[1]);
	    }
	    XChartPanel<PieChart> pie_panel=new XChartPanel<PieChart>(chart2);
	    pie_panel.setBounds(500, 230,428, 250);
		getContentPane().add(pie_panel);
		
	    
	}
	
}
