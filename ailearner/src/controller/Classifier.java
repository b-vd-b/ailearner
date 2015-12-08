package controller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;

import model.BagOfWords;
import model.Definitions;
import model.ProbabilityFormula;

public class Classifier implements Definitions {
	
	public static String[] getTestFiles(String category){
		File folder = new File(TEST_DIR+"/"+SET+"/"+category);
		File[] listOfFiles = folder.listFiles();
		
		System.out.println(folder.getAbsolutePath());
		
		StringBuilder allwords = new StringBuilder();
		
		for (int i=0; i < listOfFiles.length; i++){
			File file = listOfFiles[i];
			System.out.println(file.getName());
			if (file.isFile() && file.getName().endsWith(".txt")){
				try {
					allwords.append(Files.readAllLines(file.toPath(), StandardCharsets.UTF_8));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		System.out.println("Went through all the files");
		
		String[] result = Tokenizer.tokenize(allwords.toString());
		
		return result;
	}
	
	public static String[] getRandomTestFile(String category){
		File folder = new File(TEST_DIR+"/"+SET+"/"+category);
		File[] listOfFiles = folder.listFiles();
		
		System.out.println(folder.getAbsolutePath());
		
		StringBuilder allwords = new StringBuilder();
		
		int randomFile = (int) (Math.random()*listOfFiles.length);
		File file = listOfFiles[randomFile];
		System.out.println(file.getName());
		if (file.isFile() && file.getName().endsWith(".txt")){
			try {
				allwords.append(Files.readAllLines(file.toPath(), StandardCharsets.UTF_8));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("Went through a random file");
		
		String[] result = Tokenizer.tokenize(allwords.toString());
		
		return result;
	}
	
	public static HashMap<String, Double> classify(String[] input, BagOfWords bow1, BagOfWords bow2){
		HashMap<String, Double> resultMap = new HashMap<String, Double>();
		double scoreBow1 = 0.0;
		for (String word : input){
			scoreBow1 += ProbabilityFormula.getMultinomialWordScore(word, bow1, bow2);
		}
		resultMap.put(CATEGORY_A, scoreBow1);
		double scoreBow2 = 0.0;
		for (String word : input){
			scoreBow2 += ProbabilityFormula.getMultinomialWordScore(word, bow2, bow1);
		}
		resultMap.put(CATEGORY_B, scoreBow2);
		return resultMap;
		
	}

}
