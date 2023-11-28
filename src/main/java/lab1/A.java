package lab1;


public class A{
    public void doSomethingWithCallback(Callback callback) {
        if (callback.canProceed()) {
            System.out.println("成功");
        }
    }
}
