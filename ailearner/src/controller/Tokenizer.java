package controller;

public class Tokenizer {

	public static String[] tokenize(String in){
		String[] out = in.toLowerCase().replaceAll("[^a-z'0-9]"," ").split("\\s+");
		return out;
	}
}
