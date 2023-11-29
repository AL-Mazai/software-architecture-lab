package lab3.lab3_2;

// 具体观察者实现 - 每周涨跌报告
public class WeeklyChangeObserver implements Observer{
    private double lastWeekAverage = 0;

    @Override
    public void update(Subject subject) {
        PorkData porkData = (PorkData) subject;
        double currentPrice = porkData.getPrice();

        if (currentPrice != 0) {
            if (lastWeekAverage == 0) {
                lastWeekAverage = currentPrice;
            } else {
                double change = currentPrice - lastWeekAverage;
                System.out.println("涨跌情况: " + (change >= 0 ? "跌价了" : "涨价了"));
                lastWeekAverage = currentPrice;
            }
        }
    }
}
