package model;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;

import controller.Tokenizer;

public class BagOfWords {

	private static HashMap<String, Integer> vocabulary; 
	public static int totalWordCount = 0;
	
	public static void main(String[] args){
		readAllWordsInCategory("F");
		System.out.println(vocabulary.toString());
		//readAllWordsInCategory("M");
		//System.out.println(vocabulary.toString());
		System.out.println(totalWordCount);
	}
	
	public static void readAllWordsInCategory(String category){
		File folder = new File("training/"+Definitions.SET+"/"+category);
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
		
		String[] result = Tokenizer.tokenize(allwords.toString());
		vocabulary = new HashMap<String, Integer>();
		
		for (String word : result){
			totalWordCount++;
			Integer wordCount = vocabulary.get(word);
			if (wordCount == null){
				wordCount = 0;
			}
			vocabulary.put(word, wordCount + 1);
		}
	}
}
