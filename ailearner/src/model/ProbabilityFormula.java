package model;

import java.util.Map;

public final class ProbabilityFormula implements Definitions {
	
	public static int totalDistinctWords = 0;
	
	/*
	 * Gets the score of a given input, against a Bag of Words.
	 * 
	 * Output is always a number <= 1.
	 */
	public static double getMultinomialWordScore(String input, BagOfWords bow1, BagOfWords bow2) {
		if (bow1.bagOfWords.get(input) != null) {
			//System.out.println((bow1.bagOfWords.get(input)+SMOOTHING)/(bow1.totalWordCount+(SMOOTHING*(totalDistinctWords))));
			return (bow1.bagOfWords.get(input)+SMOOTHING)/(bow1.totalWordCount+(SMOOTHING*(totalDistinctWords)));
		} else {
			return 1.0;
		}
	}
	
	public static int countDistinctWords(BagOfWords bow1, BagOfWords bow2){
		int counter = bow1.distinctWordCount;
		for (Map.Entry<String, Integer> entry : bow2.bagOfWords.entrySet()){
			if (!bow1.bagOfWords.containsKey(entry.getKey())){
				counter++;
			}
		}
		return counter;
	}
}
