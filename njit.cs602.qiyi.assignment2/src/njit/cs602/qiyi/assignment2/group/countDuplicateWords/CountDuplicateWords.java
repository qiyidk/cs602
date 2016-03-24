package njit.cs602.qiyi.assignment2.group.countDuplicateWords;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * <p>
 * CountDuplicateWords
 * </p>
 *
 * @author qiyi
 * @version 2016-3-24
 */
public class CountDuplicateWords {
    
    public static Map<String, Integer> count(String sentence){
        Map<String, Integer> res = new HashMap<String, Integer>();
        StringBuilder sb = new StringBuilder();
        char[] c = sentence.toLowerCase().toCharArray();
        for (int i = 0; i < c.length; i++){
            if (c[i] >= 'a' && c[i] <= 'z') {
                sb.append(c[i]);
                if (i != c.length - 1) continue; // do not reach the end of the word
            }
            // we get a new word either c[i] is not a character or reach the end of the sentence.
            if (sb.length() == 0) continue;// ignore empty word
            else {
                // get a new word
                String word = sb.toString();
                Integer count = res.get(word);
                if (count == null) res.put(word, 1);
                else res.put(word, count + 1);
                sb = new StringBuilder(); 
            }
        }
        return res;
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner in = new Scanner(System.in);   
        System.out.println("Please input the sentence:");
        System.out.println("Enter \"exit\" to exit:");
        String sentence;
        while(true){
            sentence = in.nextLine();
            if (sentence == null || sentence.length() == 0) continue;
            if (sentence.equalsIgnoreCase("exit")) {
                System.out.println("GoodBye!");
                break;
            }
            Map<String, Integer> res = count(sentence);
            boolean hasDuplicate = false;
            for (String word : res.keySet()){
                int count = res.get(word);
                if (count > 1) {
                    hasDuplicate = true;
                    System.out.println(word + ": " + count);
                }
            }
            if (!hasDuplicate) System.out.println("No Duplicate Word!");
        }
        in.close();
    }
}
