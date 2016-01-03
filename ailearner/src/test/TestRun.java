package test;

import model.BagOfWords;
import model.Definitions;
import model.ProbabilityFormula;

import java.io.File;
import java.util.Arrays;

import controller.*;

public class TestRun implements Definitions {
	
	public static void main(String[] args){
		System.out.println("Them stopwords: "+Arrays.toString(STOPWORDS));
		BagOfWords bow1 = new BagOfWords();
		bow1.readAllWordsInCategory(CATEGORY_A, bow1);
		//System.out.println(bow1.bagOfWords.toString());
		BagOfWords bow2 = new BagOfWords();
		bow2.readAllWordsInCategory(CATEGORY_B, bow2);
		//System.out.println(bow2.bagOfWords.toString());
		ProbabilityFormula.totalDistinctWords = ProbabilityFormula.countDistinctWords(bow1, bow2);
		System.out.println(CATEGORY_A+"WordCount: \t\t\t" + bow1.totalWordCount);
		System.out.println(CATEGORY_B+"WordCount: \t\t\t" + bow2.totalWordCount);
		System.out.println(CATEGORY_A+"WordCount: \t\t\t" + bow1.distinctWordCount);
		System.out.println(CATEGORY_B+"WordCount: \t\t\t" + bow2.distinctWordCount);
		System.out.println("TotalDistinctWordCount: \t" + ProbabilityFormula.totalDistinctWords);
		File folder = new File(TEST_DIR+"/"+SET+"/"+CATEGORY_A);
		File[] listOfFiles = folder.listFiles();
		for (int i=0; i < listOfFiles.length; i++){
			File file = listOfFiles[i];
			System.out.println("Scores: " + Classifier.classify(Classifier.getTestFile(file), bow1, bow2));
		}
		
	}

}
