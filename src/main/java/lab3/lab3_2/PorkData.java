package lab3.lab3_2;


import java.util.ArrayList;
import java.util.List;

//具体被观察者实现类
public class PorkData implements Subject{
    private List<Observer> observers = new ArrayList<>();
    private double currentPrice;

    //通知猪肉成交价格的变动
    public void marketChanged(double price) {
        this.currentPrice = price;
        notifyObservers();
    }
    //获取猪肉当前价格
    public double getPrice() {
        return currentPrice;
    }
    //新增观察者
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }
    //删除观察者
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
    //通知观察者
    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }
}
