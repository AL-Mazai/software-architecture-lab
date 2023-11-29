package lab3.lab3_2;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

public class PorkMarketUI extends JFrame {
    private XYSeries priceSeries;

    public PorkMarketUI(XYSeries priceSeries) {
        this.priceSeries = priceSeries;
        initUI();
    }

    private void initUI() {
        XYSeriesCollection dataset = new XYSeriesCollection(priceSeries);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Pork Market Analysis",
                "Time",
                "Price",
                dataset
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(560, 370));
        add(chartPanel);

        setTitle("Pork Market Analysis");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}