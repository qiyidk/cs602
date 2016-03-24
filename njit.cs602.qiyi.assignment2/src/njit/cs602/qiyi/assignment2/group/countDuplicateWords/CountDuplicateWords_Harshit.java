package njit.cs602.qiyi.assignment2.group.countDuplicateWords;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class CountDuplicateWords_Harshit {
    public static void main(String[] args) {
        String sentence = "";
        List<String> words = new ArrayList<String>();
        Scanner in = new Scanner(System.in);
        System.out.print("Please enter the complete sentence");
        sentence = in.nextLine();
        words = CountDuplicateWords_Harshit.DuplicateWords(sentence);
        System.out.printf("No of duplicate words: %d%n", words.size());
        for (int i = 0; i < words.size(); i++)
        {
            System.out.println(words.get(i));
        }
        in.close();
    }
    public static List<String> DuplicateWords(String sen)
    {
        int length, l;
        String word = "", temp = "";
        List<String> words = new ArrayList<String>();
        List<String> duplicate = new ArrayList<String>();
        char[] chars = sen.toCharArray();
        length = sen.length();
        for (int i = 0; i < length; i++)
        {
            if (chars[i] == ' ')
            {
                if (!word.isEmpty())
                {
                    words.add(word);
                    word = "";
                }
            }
            else
            {
                word += chars[i];
            }
            if (i == length - 1)
            {
                words.add(word);
            }
        }
        l = words.size();
        for (int i = 0; i < l; i++)
        {
            temp = words.get(i);
            if (!CountDuplicateWords_Harshit.Contains(duplicate, temp))

            {
                for (int j = i + 1; j < l; j++)
                {
                    if (temp.equalsIgnoreCase(words.get(j)))
                    {
                        duplicate.add(temp);
                        break;
                    }
                }
            }
        }
        return duplicate;
    }
    public static boolean Contains(List<String> duplicate, String word)

    {
        boolean flag = false;
        for (String str : duplicate)

        {
            if (word.equalsIgnoreCase(str))
            {
                flag = true;
                break;
            }
        }
        return flag;
    }

}