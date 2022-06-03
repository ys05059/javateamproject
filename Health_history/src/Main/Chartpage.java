package Main;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.markers.SeriesMarkers;

import set¥‹¿ßclass.dayRecord;

public class Chartpage extends JFrame{
	public Chartpage(ArrayList<dayRecord> dR_ary) {
		setTitle("Chartpage");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(500,400);
    	XYChart chart = new XYChartBuilder().xAxisTitle("X").yAxisTitle("Y").width(600).height(400).build();
		chart.getStyler().setYAxisMin(0.0);
		chart.getStyler().setYAxisMax(10.0);
		
		
		ArrayList<Date> ary_x = new ArrayList<>();
	    for (dayRecord dr : dR_ary) {
	    	Date date = Date.from(dr.getToday_date().atStartOfDay(ZoneId.systemDefault()).toInstant());
	    	ary_x.add(date);
	    }
	    
	    ArrayList<Integer> ary_y = new ArrayList<>();
	    for (dayRecord dr : dR_ary) {
	    	ary_y.add(dr.getExr_ary().size());
	    }
	    
		XYSeries series = chart.addSeries(""+1 ,ary_x,ary_y);
		series.setMarker(SeriesMarkers.NONE);
		
		// pie chart
		PieChart chart2 = new PieChartBuilder().width(800).height(600).title("pie").build();
		 
	    Color[] sliceColors = new Color[] { new Color(224, 68, 14), new Color(230, 105, 62), new Color(236, 143, 110), new Color(243, 180, 159), new Color(246, 199, 182) };
	    chart2.getStyler().setSeriesColors(sliceColors);
	 
	    // Series
	    chart2.addSeries("Gold", 24);
	    chart2.addSeries("Silver", 21);
	    chart2.addSeries("Platinum", 39);
	    chart2.addSeries("Copper", 17);
	    chart2.addSeries("Zinc", 40);
	    
		//JFrame frame=new JFrame("Main");
		//sframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  
		JButton clickMe=new JButton("LineChart");
		clickMe.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	displayXYChart(Chartpage.this, chart);
		    }
		});
		setContentPane(clickMe);
		pack();
		//setVisible(true);
  	    
    }
    
    public static void displayXYChart(JFrame owner, XYChart chart) {
        XChartPanel<XYChart> panel=new XChartPanel<XYChart>(chart);
        JDialog d=new JDialog(owner, "Chart");
        d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        d.setContentPane(panel);
        d.pack();
        d.setLocationRelativeTo(owner);
        d.setVisible(true);
    }
    public static void displayPieChart(JFrame owner, PieChart chart) {
        XChartPanel<PieChart> panel=new XChartPanel<PieChart>(chart);
        JDialog d=new JDialog(owner, "Chart");
        d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        d.setContentPane(panel);
        d.pack();
        d.setLocationRelativeTo(owner);
        d.setVisible(true);
    }
}
