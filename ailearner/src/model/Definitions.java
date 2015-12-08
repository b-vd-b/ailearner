package model;

public interface Definitions {
	
	public final static String TRAINING_DIR = "training";
	public final static String TEST_DIR = "test";
	public final static String SET = "mail";
	public final static String CATEGORY_A = "ham";
	public final static String CATEGORY_B = "spam";
	public final static double SMOOTHING = 1.0; //Laplace smoothing
	public final static int NUMBER_OF_CATEGORIES = 2; // Needed for Laplace smoothing formula

}
