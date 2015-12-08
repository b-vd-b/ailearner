package test;

import model.BagOfWords;

public class TestRun {
	
	public static void main(String[] args){
		BagOfWords bow1 = new BagOfWords();
		bow1.readAllWordsInCategory("F", bow1);
		//System.out.println(bow1.bagOfWords.toString());
		BagOfWords bow2 = new BagOfWords();
		bow2.readAllWordsInCategory("M", bow2);
		//System.out.println(bow2.bagOfWords.toString());
		System.out.println("FemaleWordCount: " + bow1.totalWordCount);
		System.out.println("MaleWordCount:   " + bow2.totalWordCount);
	}

}
