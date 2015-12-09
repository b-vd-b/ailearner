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
	
	public static String[] getAllTestFiles(String category){
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
	
	public static String[] getTestFile(File file){
			
		StringBuilder allwords = new StringBuilder();
		
		System.out.print(file.getName() + "\t| ");
		if (file.isFile() && file.getName().endsWith(".txt")){
			try {
				allwords.append(Files.readAllLines(file.toPath(), StandardCharsets.UTF_8));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
//		System.out.println("Went through the file");
		
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
		
		/*	First the prior for both categories are calculated	 */
		double priorA = (bow1.getDocCount()*1.0)/((bow1.getDocCount()+bow2.getDocCount())*1.0);
		double priorB = (bow2.getDocCount()*1.0)/((bow1.getDocCount()+bow2.getDocCount())*1.0);
		
		/*	The score for Category A is calculated,
		 *  the outcome is put into a HashMap
		 */
		
		double scoreBow1 = 1.0;
		for (String word : input){
			scoreBow1 += Math.abs(Math.log(ProbabilityFormula.getMultinomialWordScore(word, bow1, bow2)));
		}
		scoreBow1 *= priorA;
		resultMap.put(CATEGORY_A, scoreBow1);
				
		
		/*	The score for Category B is calculated,
		 *  the outcome is put into a HashMap
		 */
		double scoreBow2 = 1.0;
		for (String word : input){
			scoreBow2 += Math.abs(Math.log(ProbabilityFormula.getMultinomialWordScore(word, bow2, bow1)));
		}
		scoreBow2 *= priorB;
		resultMap.put(CATEGORY_B, scoreBow2);
		System.out.print("The file is classified " + verdict(resultMap) + "\t| ");
		return resultMap;
		
	}

	public static String verdict(HashMap<String,Double> scores){
		String result ="";
		if (scores.get(CATEGORY_A)>scores.get(CATEGORY_B)){
			result = CATEGORY_A;
		}else if (scores.get(CATEGORY_B)>scores.get(CATEGORY_A)){
			result = CATEGORY_B;
		}else {
			result = "equal score";
		}
		return result;
	}
}
