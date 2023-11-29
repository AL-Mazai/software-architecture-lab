package lab3.lab3_2;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;

public class LineChartExample extends JFrame{
    public LineChartExample(String title, XYSeries series) {
        super(title);

        // Create dataset
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        // Create chart
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Pork Price Trend",
                "Week",
                "Price",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        // Create Panel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 370));
        setContentPane(chartPanel);
    }

    public static void main(String[] args) {
        // Create a sample series
        XYSeries series = new XYSeries("Pork Price");
        series.add(1, 10.0);
        series.add(2, 12.0);
        series.add(3, 8.0);

        SwingUtilities.invokeLater(() -> {
            LineChartExample example = new LineChartExample("Pork Price Trend", series);
            example.setSize(800, 400);
            example.setLocationRelativeTo(null);
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            example.setVisible(true);
        });
    }
}
