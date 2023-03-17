import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class SmoothieTester {

    static HashMap<String, NutritionalValue> nutritionalValueMap = new HashMap<>();
    /*
     * Create a game where you own a smoothie shop and start with the bare minimum
     * Take orders and make money and with that you can buy upgrades such as:
     * new fruits, protein, milk, faster blender, marketing(more customers)
     * Eventually you can hire employees to run the store by themselves and you start a new store all over again
     * Gotta watch out for allergies, angry customers, IRS is very very very deadly
     */
    public static void main(String... cheese) {

        Milk wholeMilk = new Milk("whole", nutritionalValueMap.get("Milk"), 1);
        Protein protein = new Protein("cookies and creme", nutritionalValueMap.get("Protein"), 1, 0);

        ArrayList<Fruit> fruitList = new ArrayList<>();
        fruitList.add(new Fruit("banana", nutritionalValueMap.get("Banana"), 1, 0));
        fruitList.add( new Fruit("mango", nutritionalValueMap.get("Mango"), 0, 0));
        Smoothie firstSmoothie = new Smoothie("Banana Beaters", wholeMilk, protein, true, fruitList.get(0), fruitList.get(1));
        System.out.println(firstSmoothie);

        Protein chocolateProtein = new Protein("chocolate", nutritionalValueMap.get("Protein"), 1, 0);
        fruitList.add( new Fruit("blueberry", nutritionalValueMap.get("Blueberry"), 0, 0));
        Smoothie secondSmoothie = new Smoothie("wayneJohnson", wholeMilk, chocolateProtein, true, fruitList.get(0), fruitList.get(2));
        System.out.println(secondSmoothie);
        System.out.println(secondSmoothie.getNutritionalVal());
    }


    public static void initializeNutritonalVal() {
        nutritionalValueMap.put("Milk", new NutritionalValue(12, 8, 8, false, false));
        nutritionalValueMap.put("Protein", new NutritionalValue(3, 24, 1, false, false));
        nutritionalValueMap.put("Banana", new NutritionalValue(28, 1, 0, false, true));
        nutritionalValueMap.put("Mango", new NutritionalValue(25, 1, 1, false, true));
        nutritionalValueMap.put("Blueberry", new NutritionalValue(21, 1, 0, true, true));
    }
}
