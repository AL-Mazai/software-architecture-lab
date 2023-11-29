package lab3.lab3_3;

public class JiDanDecorator extends IngredientDecorator{
    public JiDanDecorator(RiceNoodle riceNoodle) {super(riceNoodle);}
    @Override
    public double cost() {
        return riceNoodle.cost() * quantity + this.price;
    }
    @Override
    public String description() {
        return riceNoodle.description() + "加鸡蛋";
    }
}
