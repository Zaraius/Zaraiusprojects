public class Fruit {
    private final String name;
    private final NutritionalValue nValue;
    private final double servings;
    private final int tier; // tier 0 makes the least money and it increases as it goes up

    public Fruit(String name, NutritionalValue nValue, double servings, int tier) {
        this.name = name;
        this.nValue = nValue;
        this.servings = servings;
        this.tier = tier;
    }

    //getter for Nutritional Value
    public NutritionalValue getNValue() {
        return nValue;
    }
    //getter for name
    public String getName() {
        return name;
    }


}
