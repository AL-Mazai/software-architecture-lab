package lab3.lab3_2;

import javafx.application.Application;
import org.jfree.data.xy.XYSeries;

import java.util.ArrayList;
import java.util.List;

// 具体观察者实现 - 统计分析报告
public class StatisticalAnalysisObserver implements Observer{
    private XYSeries priceSeries;
    public StatisticalAnalysisObserver(XYSeries priceSeries) {
        this.priceSeries = priceSeries;
    }
    @Override
    public void update(Subject subject) {
        PorkData porkData = (PorkData) subject;
        double currentPrice = porkData.getPrice();

        if (currentPrice != 0) {
            priceSeries.add(priceSeries.getItemCount(), currentPrice);
        }
    }
}
