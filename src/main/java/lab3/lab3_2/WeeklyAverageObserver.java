package lab3.lab3_2;

// 具体观察者实现 - 每周均价报告
public class WeeklyAverageObserver implements Observer{
    private double weeklyTotal = 0;
    private int weeklyCount = 0;
    @Override
    public void update(Subject subject) {
        PorkData porkData = (PorkData) subject;
        double currentPrice = porkData.getPrice();
        if (currentPrice != 0) {
            weeklyTotal += currentPrice;
            weeklyCount++;
            if (weeklyCount == 7) {
                double weeklyAverage = weeklyTotal / 7;
                System.out.println("每周均价: " + weeklyAverage);
                weeklyTotal = 0;
                weeklyCount = 0;
            }
        }
    }
}
