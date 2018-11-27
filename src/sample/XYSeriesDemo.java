package sample;//package org.jfree.chart.demo;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import com.company.DataFrame;

import javax.swing.*;


public class XYSeriesDemo extends ApplicationFrame {

    public XYSeriesDemo(final String title, int x, int y, DataFrame dataFrame) {

        super(title);
        XYSeries series = new XYSeries("");
        for (int i = 0; i < dataFrame.size(); i++) {
            series.add(Double.parseDouble(dataFrame.getData().get(i).get(x).toString()), Double.parseDouble(dataFrame.getData().get(i).get(y).toString()));
        }
        XYSeriesCollection data = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "XY Series Demo",
                "X",
                "Y",
                data,
                PlotOrientation.VERTICAL,
                false,
                true,
                false
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        setContentPane(chartPanel);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }
}
