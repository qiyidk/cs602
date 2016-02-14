package njit.cs602.qiyi.assignment1.group;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * <p>
 * Palindrome
 * </p>
 *
 * @author qiyi
 * @version 2016-2-11
 */
public class Palindrome {
    
    public static boolean isPalindrome(int num){
        if (num < 0) return false;
        int original = num;
        int reverse = 0;
        while(num != 0){
            reverse *= 10;
            reverse += num % 10;
            num /= 10;
        }
        return original == reverse;
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
                if ((num < 0) || (num / 10000 > 9) || (num / 10000 == 0)) {
                    System.out.println("The input should be a 5-digit positive integer");
                    continue;
                }
                System.out.println(num + " is" + (isPalindrome(num) ? " " : " not ") + "a Palindrome");
            }
            catch(InputMismatchException e){
                System.out.println("The input should be a 5-digit positive integer");
                in.next();//discard mismatched data
            }
            catch(Exception e){
                // do nothing      
            }
        }
        in.close();
    }
}
