import java.util.Objects;

public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		if (!str.isEmpty()) {
			return str.substring(1);
		} else {
			return "";
		}
	}

	public static int levenshtein(String word1, String word2) {
		if(word1.isEmpty()){
			return word2.length();
		}
		else if (word2.isEmpty()){
			return word1.length();
		}
		else if(word1.charAt(0) == word2.charAt(0)){
			return levenshtein(tail(word1),tail(word2));
		}
		else{
			int min = Math.min(Math.min(levenshtein(tail(word1),word2),levenshtein(word1,tail(word2))),levenshtein(tail(word1),tail(word2)));
			return (1 + min);
		}
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);

		for(int i= 0;i<dictionary.length;i++){
			dictionary[i] = in.readLine();
		}

		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		int minLev= threshold;
		String minLevWord = "";
		for(int i = 0; i<dictionary.length; i++){
			int currentLev= levenshtein(word.toLowerCase(),dictionary[i]);
			if(currentLev<=minLev){
				minLev= currentLev;
				minLevWord = dictionary[i];
			}
		}
		if(minLevWord == ""){
			return word;
		}
		return minLevWord;
	}

}
