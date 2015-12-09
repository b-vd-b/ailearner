package model;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import controller.Tokenizer;

public class BagOfWords implements Definitions {

	public HashMap<String, Integer> bagOfWords; 
	public int totalWordCount = 0;
	public int distinctWordCount = 0;
	private int totalDocumentCount = 0;
	
	public BagOfWords() {
		bagOfWords = new HashMap<String, Integer>();
	}
	
	public int getDocCount(){
		return totalDocumentCount;
	}
	
	public void readAllWordsInCategory(String category, BagOfWords bow){
		File folder = new File(TRAINING_DIR+"/"+SET+"/"+category);
		File[] listOfFiles = folder.listFiles();
		
		System.out.println(folder.getAbsolutePath());
		
		StringBuilder allwords = new StringBuilder();
		
		totalDocumentCount = listOfFiles.length;
		System.out.println("TOTAALDOCUMENTCOUNT: " + totalDocumentCount);
		
		for (int i=0; i < listOfFiles.length; i++){
			File file = listOfFiles[i];
			//System.out.println(file.getName());
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
		
		for (String word : result){
			totalWordCount++;
			Integer wordCount = bow.bagOfWords.get(word);
			if (wordCount == null){
				wordCount = 0;
				distinctWordCount++;
			}
			bow.bagOfWords.put(word, wordCount + 1);
		}
	}
}
