public class Milk {
    private final String type; // such as whole, fat-free, almond, soy
    private final NutritionalValue nValue;
    private final double servings;
    private final boolean isDairy;
    public Milk(String type, NutritionalValue nValue, double servings) {
        this.type = type;
        this.nValue = nValue;
        this.servings = servings;
        // determine if the milk has dairy or not based on the type of milk
        isDairy = type.equals("whole") || type.equals("fat-free");
    }

    //getter for Nutritional Value
    public NutritionalValue getNValue() {
        return nValue;
    }
    //getter for name of milk
    public String getName() {
        return type;
    }
}
