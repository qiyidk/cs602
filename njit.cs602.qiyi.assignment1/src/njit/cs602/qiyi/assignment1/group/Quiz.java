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
    public static final String[] questions = new String[]{};
    public static final String[] choices = new String[]{};
    public static final int[] answers = new int[]{};
    public static void doQuiz(){
        System.out.println("Global Warming Facts Quiz");
        Scanner in = new Scanner(System.in);
        int correct = 0;
        for (int i = 1; i <= 5; i++){
            System.out.println(i + ":" + questions[i - 1]);
            System.out.println(i + ":" + choices[i - 1]);
            System.out.print("Your answer :");
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
        if (correct == 5) System.out.println("Excellent!");
        else if (correct == 4) System.out.println("Very Good!");
        else System.out.println("Time to brush up on your knowledge of global warming");
        System.out.println("Questions come from XX");
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
