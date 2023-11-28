package lab1;

public class ButtonController {
    private boolean executeB = false;
    private boolean executeC = false;

    public void executeA() {
        if (executeB) {
            System.out.println("执行B功能！");
            executeB = false;
        } else if (executeC) {
            System.out.println("执行C功能！");
            executeC = false;
        } else {
            System.out.println("Hello World！");
        }
    }

    public void executeB() {
        executeB = true;
    }

    public void executeC() {
        executeC = true;
    }
}
