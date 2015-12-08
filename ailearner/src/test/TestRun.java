package test;

import model.BagOfWords;
import model.Definitions;
import model.ProbabilityFormula;
import controller.*;

public class TestRun implements Definitions {
	
	public static void main(String[] args){
		BagOfWords bow1 = new BagOfWords();
		bow1.readAllWordsInCategory("F", bow1);
		//System.out.println(bow1.bagOfWords.toString());
		BagOfWords bow2 = new BagOfWords();
		bow2.readAllWordsInCategory("M", bow2);
		//System.out.println(bow2.bagOfWords.toString());
		ProbabilityFormula.totalDistinctWords = ProbabilityFormula.countDistinctWords(bow1, bow2);
		System.out.println("FemaleTotalWordCount:    " + bow1.totalWordCount);
		System.out.println("MaleTotalWordCount:      " + bow2.totalWordCount);
		System.out.println("FemaleDistinctWordCount: " + bow1.distinctWordCount);
		System.out.println("MaleDistinctWordCount:   " + bow2.distinctWordCount);
		System.out.println("TotalDistinctWordCount:  " + ProbabilityFormula.totalDistinctWords);
		System.out.println("Whuhahahaha: " + Classifier.classify(Classifier.getTestFiles(CATEGORY_A), bow1, bow2));
	}

}
