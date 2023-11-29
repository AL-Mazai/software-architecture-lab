package lab3.lab3_3;

public class NiuRouDecorator extends IngredientDecorator{
    public NiuRouDecorator(RiceNoodle riceNoodle) {
        super(riceNoodle);
    }
    @Override
    public double cost() {
        return riceNoodle.cost() * quantity + 5.0;
    }
    @Override
    public String description() {
        return riceNoodle.description() + "加牛肉";
    }
}
