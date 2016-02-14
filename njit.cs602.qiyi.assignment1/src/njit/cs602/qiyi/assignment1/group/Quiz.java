package njit.cs602.qiyi.assignment1.group;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * <p>
 * Quiz
 * </p>
 *
 * @author qiyi
 * @version 2016-2-11
 */
public class Quiz {    
    public static final String[] questions = new String[]{"1","2","3","4","5"};
    public static final String[] choices = new String[]{"1,2,3,4","1,2,3,4","1,2,3,4","1,2,3,4","1,2,3,4"};
    public static final int[] answers = new int[]{1, 2, 3, 4, 4};
    public static void doQuiz(){
        System.out.println("Global Warming Facts Quiz");
        System.out.println("You will have five multiple choice questions");
        System.out.println("Your answer should be a number. For example, 123 represents choose 1,2,3");
        Scanner in = new Scanner(System.in);
        int correct = 0;
        for (int i = 0; i <= 4; i++){
            System.out.println(i + 1 + ":" + questions[i]);
            System.out.println(choices[i]);
            System.out.print("Your answer: ");
            try{
                int answer = in.nextInt();
                if (answer == answers[i]) correct++;
            }
            catch(InputMismatchException e){
                System.out.println("The answer should be a number");
                in.next();//discard mismatched data
            }
            catch(Exception e){
                // do nothing      
            }
        }
        System.out.println("Your score : " + correct);
        if (correct == 5) System.out.println("Excellent!");
        else if (correct == 4) System.out.println("Very Good!");
        else System.out.println("Time to brush up on your knowledge of global warming");
        System.out.println("You can find some useful information of global warming on XXX");
        in.close();
    }
    

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Quiz.doQuiz();
    }
}
