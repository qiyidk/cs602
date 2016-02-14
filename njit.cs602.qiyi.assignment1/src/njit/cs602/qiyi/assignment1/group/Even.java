package njit.cs602.qiyi.assignment1.group;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * <p>
 * Even
 * </p>
 *
 * @author qiyi
 * @version 2016-2-11
 */
public class Even {
    
    public static boolean isEven(int num){
        return num % 2 == 0;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner in = new Scanner(System.in);   
        System.out.println("Please input the number:");
        System.out.println("Enter -1 to exit:");
        int num = 0;
        while(true){
            try{
                num = in.nextInt();
                if (num == -1) {
                    System.out.println("GoodBye!");
                    break;
                }
                System.out.println(num + " is an " + (isEven(num) ? "Even" : "Odd") + " number");
            }
            catch(InputMismatchException e){
                System.out.println("The input should be an integer");
                in.next();//discard mismatched data
            }
            catch(Exception e){
                // do nothing      
            }
        }
        in.close();
    }
}
