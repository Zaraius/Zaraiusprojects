import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;

public class SmoothieTester extends JFrame implements ActionListener {

    static HashMap<String, NutritionalValue> nutritionalValueMap = new HashMap<>();
    static HashMap<String, Milk> milkHashMap = new HashMap<>();
    static HashMap<String, Protein> proteinHashMap = new HashMap<>();
    static HashMap<String, Fruit> fruitHashMap = new HashMap<>();

    static JButton milkButton = new JButton("Milk Choice");
    static JButton proteinButton = new JButton("Protein Choice");
    static JButton fruitButton = new JButton("Fruit choice");
    static JButton iceButton = new JButton("Ice choice");


    static JButton wholeMilkButton = new JButton("Whole Milk");
    static JButton almondMilkButton = new JButton("Almond Milk");
    static JButton coconutMilkButton = new JButton("Coconut Milk");
    static JButton soyMilkButton = new JButton("Soy Milk");
    static JButton chocolateMilkButton = new JButton("Chocolate Milk");

    static JButton chocolateProteinButton = new JButton("Chocolate");
    static JButton blueberryCobblerProteinButton = new JButton("Blueberry Gobbler");

    static JButton bananaButton = new JButton("Banana");
    static JButton mangoButton = new JButton("Mango");
    static JButton blueberryButton = new JButton("Blueberry");

    static JButton yesIce = new JButton("Yes Ice");
    static JButton noIce = new JButton("No Ice");

    static JLabel smoothieDescription = new JLabel("");
    static JLabel smoothieNutritionalVal = new JLabel("");


    static JButton createButton = new JButton("CREATE");

    boolean milkchosen = true;
    boolean proteinchosen = true;
    boolean fruitchosen = true;
    boolean iceChosen = true;

    Milk selectedMilk;
    Protein selectedProtein;
    Fruit selectedFruit;
    boolean selectedIce;



    /*
     * Create a game where you own a smoothie shop and start with the bare minimum
     * Take orders and make money and with that you can buy upgrades such as:
     * new fruits, protein, milk, faster blender, marketing(more customers)
     * Eventually you can hire employees to run the store by themselves and you start a new store all over again
     * Gotta watch out for allergies, angry customers, IRS is very very very deadly
     */


    public static void main(String... cheese) {
        initializeNutritonalVal();
        Smoothie firstSmoothie = new Smoothie("Banana Beaters", milkHashMap.get("Whole Milk"), proteinHashMap.get("Chocolate"), true, fruitHashMap.get("Banana"));
        System.out.println(firstSmoothie);
        Smoothie secondSmoothie = new Smoothie("wayneJohnson", milkHashMap.get("Whole Milk"), proteinHashMap.get("Chocolate"), true, fruitHashMap.get("Mango"));
        System.out.println(secondSmoothie);
//        System.out.println(secondSmoothie.getNutritionalVal());

        swing();
    }

    public static void swing() {
        JFrame f = new JFrame();
        SmoothieTester window = new SmoothieTester();

        milkButton.setBounds(100,100,150, 40);
        proteinButton.setBounds(250, 100, 150, 40);
        fruitButton.setBounds(400, 100, 150, 40);
        iceButton.setBounds(550, 100, 150, 40);

        wholeMilkButton.setBounds(100, 175, 150, 20);
        almondMilkButton.setBounds(100, 200, 150, 20);
        soyMilkButton.setBounds(100, 225, 150, 20);
        coconutMilkButton.setBounds(100, 250, 150, 20);
        chocolateMilkButton.setBounds(100, 275, 150, 20);

        chocolateProteinButton.setBounds(250, 175, 150, 20);
        blueberryCobblerProteinButton.setBounds(250, 200, 150, 20);

        bananaButton.setBounds(400, 175, 150, 20);
        mangoButton.setBounds(400, 200, 150, 20);
        blueberryButton.setBounds(400, 225, 150, 20);

        yesIce.setBounds(550, 175, 150, 20);
        noIce.setBounds(550, 200, 150, 20);

        createButton.setBounds(300, 300, 150, 75);
        smoothieDescription.setBounds(20, 350, 700, 75);
        smoothieNutritionalVal.setBounds(20, 400, 700, 75);

        f.add(milkButton);
        f.add(proteinButton);
        f.add(fruitButton);
        f.add(iceButton);

        f.add(wholeMilkButton);
        f.add(almondMilkButton);
        f.add(soyMilkButton);
        f.add(coconutMilkButton);
        f.add(chocolateMilkButton);

        f.add(chocolateProteinButton);
        f.add(blueberryCobblerProteinButton);

        f.add(bananaButton);
        f.add(mangoButton);
        f.add(blueberryButton);

        f.add(yesIce);
        f.add(noIce);


        f.add(createButton);
        f.add(smoothieDescription);
        f.add(smoothieNutritionalVal);

        wholeMilkButton.setVisible(false);
        almondMilkButton.setVisible(false);
        soyMilkButton.setVisible(false);
        coconutMilkButton.setVisible(false);
        chocolateMilkButton.setVisible(false);

        chocolateProteinButton.setVisible(false);
        blueberryCobblerProteinButton.setVisible(false);

        bananaButton.setVisible(false);
        mangoButton.setVisible(false);
        blueberryButton.setVisible(false);

        yesIce.setVisible(false);
        noIce.setVisible(false);

        createButton.setVisible(false);
        smoothieDescription.setVisible(false);
        smoothieNutritionalVal.setVisible(false);

        milkButton.addActionListener(window);
        proteinButton.addActionListener(window);
        fruitButton.addActionListener(window);
        iceButton.addActionListener(window);

        wholeMilkButton.addActionListener(window);
        almondMilkButton.addActionListener(window);
        soyMilkButton.addActionListener(window);
        coconutMilkButton.addActionListener(window);
        chocolateMilkButton.addActionListener(window);

        chocolateProteinButton.addActionListener(window);
        blueberryCobblerProteinButton.addActionListener(window);
        bananaButton.addActionListener(window);
        mangoButton.addActionListener(window);
        blueberryButton.addActionListener(window);
        yesIce.addActionListener(window);
        noIce.addActionListener(window);
        createButton.addActionListener(window);
        f.setSize(800,500);
        f.setLayout(null);
        f.setVisible(true);
    }


    public void buttonClick(JButton chosen) {
        // milk
        if (chosen == milkButton) {
            if (milkchosen) {
                System.out.println("Milkk true");
                wholeMilkButton.setVisible(true);
                almondMilkButton.setVisible(true);
                soyMilkButton.setVisible(true);
                coconutMilkButton.setVisible(true);
                chocolateMilkButton.setVisible(true);
                milkchosen = false;
            } else {
                System.out.println("Milk false");
                wholeMilkButton.setVisible(false);
                almondMilkButton.setVisible(false);
                soyMilkButton.setVisible(false);
                coconutMilkButton.setVisible(false);
                chocolateMilkButton.setVisible(false);
                milkchosen = true;
            }
        // proteim
        } else if (chosen == proteinButton) {
            if (proteinchosen) {
                chocolateProteinButton.setVisible(true);
                blueberryCobblerProteinButton.setVisible(true);
                proteinchosen = false;
            } else {
                chocolateProteinButton.setVisible(false);
                blueberryCobblerProteinButton.setVisible(false);
                proteinchosen = true;
            }
            System.out.println("proteinnnn");


        // fruit
        } else if (chosen == fruitButton) {
            System.out.println("fruittit");
            if (fruitchosen) {
                bananaButton.setVisible(true);
                mangoButton.setVisible(true);
                blueberryButton.setVisible(true);
                fruitchosen = false;
            } else {
                bananaButton.setVisible(false);
                mangoButton.setVisible(false);
                blueberryButton.setVisible(false);
                fruitchosen = true;
            }
        } else if(chosen == iceButton) {
            System.out.println("ice");
            if(iceChosen) {
                yesIce.setVisible(true);
                noIce.setVisible(true);
                iceChosen = false;
            } else {
                yesIce.setVisible(false);
                noIce.setVisible(false);
                iceChosen = true;
            }
        }


        if (chosen == wholeMilkButton) {
            System.out.println("You chosen whole milk");
            selectedMilk = milkHashMap.get("Whole Milk");

        } else if (chosen == almondMilkButton) {
            System.out.println("You chosen almond milk");
            selectedMilk = milkHashMap.get("Almond Milk");
        } else if (chosen == soyMilkButton) {
            System.out.println("You chose soy milk");
            selectedMilk = milkHashMap.get("Soy Milk");
        } else if (chosen == coconutMilkButton) {
            System.out.println("You chose coconut milk");
            selectedMilk = milkHashMap.get("Coconut Milk");

        } else if (chosen == chocolateMilkButton) {
            System.out.println("You chose chocolate milk");
            selectedMilk = milkHashMap.get("Chocolate Milk");
        }

         if (chosen == chocolateProteinButton) {
            System.out.println("You chose chocolate protein");
            selectedProtein = proteinHashMap.get("Chocolate");
        } else if (chosen == blueberryCobblerProteinButton) {
            System.out.println("You chose blueberry cobbler protein");
            selectedProtein = proteinHashMap.get("Blueberry");

        } if (chosen == mangoButton) {
            System.out.println("You chose mango");
            selectedFruit = fruitHashMap.get("Mango");
        } else if (chosen == bananaButton) {
            System.out.println("You chose banana");
            selectedFruit = fruitHashMap.get("Banana");
        } else if (chosen == blueberryButton) {
            System.out.println("You chose blueberry");
            selectedFruit = fruitHashMap.get("Blueberry");
        }

        if (chosen == yesIce) {
            selectedIce = true;
        } else if (chosen == noIce) {
            selectedIce = false;
        }

        if (selectedMilk != null && selectedProtein != null && selectedFruit != null) {
            createButton.setVisible(true);
        }
        if (chosen == createButton) {
            Smoothie createdSmoothie = new Smoothie("an AMAZING smoothie", selectedMilk, selectedProtein, selectedIce, selectedFruit);
            System.out.println("Your smoothie is created");
            String s = createdSmoothie.toString();
            System.out.println(s);
            smoothieDescription.setText(s);
            smoothieNutritionalVal.setText(createdSmoothie.getNutritionalVal());
            smoothieDescription.setVisible(true);
            smoothieNutritionalVal.setVisible(true);
        }
    }
    public static void initializeNutritonalVal() {
        nutritionalValueMap.put("Whole Milk Nut", new NutritionalValue(12, 8, 8, false, false));
        nutritionalValueMap.put("Almond Milk Nut", new NutritionalValue(4, 10, 3, false, false));
        nutritionalValueMap.put("Soy Milk Nut", new NutritionalValue(8, 7, 4, false, false));
        nutritionalValueMap.put("Coconut Milk Nut", new NutritionalValue(3, 15, 5, false, false));
        nutritionalValueMap.put("Chocolate Milk Nut", new NutritionalValue(10, 4, 3, false, false));
        nutritionalValueMap.put("Protein Nut", new NutritionalValue(3, 24, 1, false, false));
        nutritionalValueMap.put("Banana Nut", new NutritionalValue(28, 1, 0, false, true));
        nutritionalValueMap.put("Mango Nut", new NutritionalValue(25, 1, 1, false, true));
        nutritionalValueMap.put("Blueberry Nut", new NutritionalValue(21, 1, 0, true, true));

        Milk wholeMilk = new Milk("whole", nutritionalValueMap.get("Whole Milk Nut"), 1);
        Milk almondMilk = new Milk("almond", nutritionalValueMap.get("Almond Milk Nut"), 1);
        Milk soyMilk = new Milk("soy", nutritionalValueMap.get("Soy Milk Nut"), 1);
        Milk coconutMilk = new Milk("coconut", nutritionalValueMap.get("Coconut Milk Nut"), 1);
        Milk chocolateMilk = new Milk("chocolate", nutritionalValueMap.get("Chocolate Milk Nut"), 1);
        Protein blueberryCobblerProtein = new Protein("blueberry cobbler", nutritionalValueMap.get("Protein Nut"), 1, 0);
        Protein chocolateProtein = new Protein("chocolate", nutritionalValueMap.get("Protein Nut"), 1, 0);

        Fruit banana = new Fruit("banana", nutritionalValueMap.get("Banana Nut"), 1, 0);
        Fruit mango = new Fruit("mango", nutritionalValueMap.get("Mango Nut"), 1, 0);
        Fruit blueberry = new Fruit("blueberry", nutritionalValueMap.get("Blueberry Nut"), 1, 0);

        milkHashMap.put("Whole Milk", wholeMilk);
        milkHashMap.put("Almond Milk", almondMilk);
        milkHashMap.put("Soy Milk", soyMilk);
        milkHashMap.put("Coconut Milk", coconutMilk);
        milkHashMap.put("Chocolate Milk", chocolateMilk);

        proteinHashMap.put("Blueberry", blueberryCobblerProtein);
        proteinHashMap.put("Chocolate", chocolateProtein);

        fruitHashMap.put("Banana", banana);
        fruitHashMap.put("Mango", mango);
        fruitHashMap.put("Blueberry", blueberry);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        buttonClick((JButton) actionEvent.getSource());

    }
}
