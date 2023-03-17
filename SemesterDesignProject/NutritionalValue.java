public class NutritionalValue {
	private final int carbs;
	private final int proteins;
	private final int fats;
	private final int calories;
	private final boolean highFiber;
	private boolean vitamins;

	public NutritionalValue(int carbs, int proteins, int fats, boolean fiber, boolean vitamins) {
		this.carbs = carbs;
		this.proteins = proteins;
		this.fats = fats;
		calories = carbs * 4 + proteins * 4 + fats * 9; // do I use this. for this or not
		this.highFiber = fiber;
	}

	public int getCarbs() {
		return carbs;
	}

	public int getProtein() {
		return proteins;
	}

	public int getFats() {
		return fats;
	}

	public int getCalories() {
		return (carbs * 4) + (proteins * 4) + (fats * 9);
	}

	public boolean getFiber() {
		return highFiber;
	}

	public boolean getVitamins() {
		return vitamins;
	}

	public static NutritionalValue getTotalNutritionalValue(NutritionalValue milk, NutritionalValue protein, NutritionalValue fruit1, NutritionalValue fruit2) {
		return new NutritionalValue(milk.getCarbs() + protein.getCarbs() + fruit1.getCarbs() + fruit2.getCarbs(),
				milk.getProtein() + protein.getProtein() + fruit1.getProtein() + fruit2.getProtein(),
				milk.getFats() + protein.getFats() + fruit1.getFats() + fruit2.getFats(),
				milk.getFiber() || protein.getFiber() || fruit1.getFiber() || fruit2.getFiber(),
				milk.getVitamins() || protein.getVitamins() || fruit1.getVitamins() || fruit2.getVitamins());
	}
	//TODO: For this to work I have to add each individual component
	//public NutritionalValue addNutritionalValue(double originalServings, NutritionalValue othernValue, double servings) {
	//return this nutritional val * servings + other * servings
	//}


}
