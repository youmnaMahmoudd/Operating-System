package application;

import java.util.ArrayList;
import java.util.Arrays;

import application.GanttChart.ExtraData;
import javafx.collections.FXCollections;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;

public class ChartDisplay {
	 
	GanttChart<Number,String> createChart(ArrayList<Things> data) {
		NumberAxis xAxis = new NumberAxis();
		CategoryAxis yAxis = new CategoryAxis();
		GanttChart<Number,String> chart = new GanttChart<Number,String>(xAxis,yAxis);		
		String machine =   "Processes" ;
		XYChart.Series series1 = new XYChart.Series(); 
		Color paint = new Color(0.5059, 0.6471, 0.7725, 1.0);

	     xAxis.setLabel("");
	     xAxis.setTickLabelFill(paint);
	     xAxis.setMinorTickCount(1);

	     yAxis.setLabel("");
	     yAxis.setTickLabelFill(paint);
	     yAxis.setTickLabelGap(10);
	     yAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(machine)));
	     chart.setLegendVisible(false);
	     chart.setBlockHeight( 50);
	     for(Things temp:data) {
				series1.getData().add(new XYChart.Data(temp.getStart(), machine, new ExtraData((temp.getEnd()-temp.getStart()), temp.getColor())));

	     }

	    chart.getData().addAll(series1);           
	     chart.getStylesheets().add(getClass().getResource("ganttchart.css").toExternalForm());
	     chart.setLayoutX(-1);
	     chart.setLayoutY(0);
		return chart;	
	}
	 
}