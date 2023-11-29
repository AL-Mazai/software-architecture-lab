package lab3.lab3_3;

//基础配料抽象类
public abstract class IngredientDecorator extends RiceNoodle{
    protected RiceNoodle riceNoodle;
    protected int quantity;
    protected double price;
    public IngredientDecorator(RiceNoodle riceNoodle) {
        this.riceNoodle = riceNoodle;
        this.quantity = 0;
        this.price = 0;
    }
}
