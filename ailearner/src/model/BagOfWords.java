package model;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;

import controller.Tokenizer;

public class BagOfWords {

	public HashMap<String, Integer> bagOfWords; 
	public int totalWordCount = 0;
	
	public BagOfWords() {
		bagOfWords = new HashMap<String, Integer>();
	}
	
	public void readAllWordsInCategory(String category, BagOfWords bow){
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
		
		for (String word : result){
			totalWordCount++;
			Integer wordCount = bow.bagOfWords.get(word);
			if (wordCount == null){
				wordCount = 0;
			}
			bow.bagOfWords.put(word, wordCount + 1);
		}
	}
}
