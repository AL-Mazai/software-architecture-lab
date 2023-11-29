package lab3.lab3_2;

//被观察者抽象类
public interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}
