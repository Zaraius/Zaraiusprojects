public class Protein {
    private final String flavor;
    private final NutritionalValue nValue;
    private final double servings;
    private final int tier;

    public Protein(String flavor, NutritionalValue nValue, double servings, int tier) {
        this.flavor = flavor;
        this.nValue = nValue;
        this.servings = servings;
        this.tier = tier; // 0 is the starting and makes the least money
    }

    //getter for Nutritional Value
    public NutritionalValue getNValue() {
        return nValue;
    }

    public String getFlavor() {
        return flavor;
    }
}
