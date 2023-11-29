package lab3.lab3_2;

import org.jfree.data.xy.XYSeries;

import javax.swing.*;
import java.util.Random;

public class PorkMarketSystem {
    public static void main(String[] args) {
        // 猪肉行情数据对象
        PorkData porkData = new PorkData();
        // 历史价格数据序列对象
        XYSeries priceSeries = new XYSeries("history price");
        // UI界面对象
        PorkMarketUI porkMarketUI = new PorkMarketUI(priceSeries);
        // 创建统计分析观察者，传入历史价格数据
        StatisticalAnalysisObserver statisticalObserver = new StatisticalAnalysisObserver(priceSeries);
        // 注册观察者
        porkData.registerObserver(statisticalObserver);
        porkData.registerObserver(new WeeklyAverageObserver());
        porkData.registerObserver(new WeeklyChangeObserver());

        // 模拟猪肉行情变动
        Random random = new Random();
        for (int i = 0; i < 30; i++) {
            double price = random.nextDouble() * 10 + 10;
            // 价格变动，触发观察者更新
            porkData.marketChanged(price);
        }

        //UI界面
        SwingUtilities.invokeLater(() -> porkMarketUI.setVisible(true));
    }
}
