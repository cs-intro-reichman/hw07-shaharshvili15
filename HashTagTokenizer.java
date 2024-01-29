import java.util.Objects;

public class HashTagTokenizer {

	public static void main(String[] args) {

		String hashTag = args[0];
		String []dictionary = readDictionary("dictionary.txt");
		//System.out.print(existInDictionary("shahar",dictionary));
		breakHashTag(hashTag, dictionary);
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);

		for(int i= 0;i<dictionary.length;i++){
			dictionary[i] = in.readLine();
		}

		return dictionary;
	}

	public static boolean existInDictionary(String word, String []dictionary) {
		for(int i = 0 ; i<dictionary.length;i++){
			if(Objects.equals(dictionary[i], word)){
				return true;
			}
		}
		return false;
	}

	public static void breakHashTag(String hashtag, String[] dictionary) {
		String lowerCaseHashtag = hashtag.toLowerCase();
        if (lowerCaseHashtag.isEmpty()) {
            return;
        }
 
        int N = lowerCaseHashtag.length();

        for (int i = 1; i <= N; i++) {
			String substring = lowerCaseHashtag.substring(0,i);
			if(existInDictionary(substring,dictionary)){
				System.out.println(substring);
				String newHashtag = lowerCaseHashtag.substring(i,N);
				breakHashTag(newHashtag,dictionary);
				return;
			}
        }
    }

}
