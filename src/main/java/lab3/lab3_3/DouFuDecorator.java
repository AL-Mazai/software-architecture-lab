package lab3.lab3_3;

public class DouFuDecorator extends IngredientDecorator {
    public DouFuDecorator(RiceNoodle riceNoodle) {
        super(riceNoodle);
    }
    @Override
    public double cost() {
        return riceNoodle.cost() * quantity + 2.0;
    }
    @Override
    public String description() {
        return riceNoodle.description() + "加豆腐";
    }
}
