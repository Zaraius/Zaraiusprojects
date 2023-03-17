public class Smoothie{
    private final String name;
    private final Milk milk;
    private final Protein protein;
    private final boolean ice;
    private final Fruit fruit1;
    private final Fruit fruit2;
    private NutritionalValue nValue;

    public Smoothie(String name, Milk milk, Protein protein, boolean ice, Fruit fruit1, Fruit fruit2) {
        this.name = name;
        this.milk = milk;
        this.protein = protein;
        this.ice = ice;
        this.fruit1 = fruit1;
        this.fruit2 = fruit2;
        //nValue = smoothieMilk.getNValue().addNutritionalValue(1, smoothieProtein.getNValue(), 1).addNutritionalValue(1, smoothieFruit1.getNValue(), 1).addNutritionalValue(1, smoothieFruit2.getNValue(), 0);
        // add the nutrtional value of all the ingerdients;
        // for this to work I need to implement addNutritionalValue
    }
    
    public String toString() {
        String ret = "You created " + name + " which contains " + milk.getName() + " milk, " + protein.getFlavor() + " protein "; //+ blah smoothie
        if(ice) {
            ret += "with ice ";
        } else {
            ret += "without ice ";
        }
        ret += "and " + fruit1.getName() + " + " + fruit2.getName();
        return ret;
    }

    public String getNutritionalVal() {
        NutritionalValue totalNValue =  NutritionalValue.getTotalNutritionalValue(milk.getNValue(), protein.getNValue(), fruit1.getNValue(), fruit2.getNValue());
        return "Your smoothie has " + totalNValue.getProtein() + " grams of protein, " + totalNValue.getCarbs() + " grams of carbs" + totalNValue.getFats() + " grams of fats";
    }
}
