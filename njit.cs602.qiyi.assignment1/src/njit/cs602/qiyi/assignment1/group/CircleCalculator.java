package njit.cs602.qiyi.assignment1.group;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * <p>
 * Circle
 * </p>
 *
 * @author qiyi
 * @version 2016-2-11
 */
public class CircleCalculator {
    
    public static void printResult(double radius){
        System.out.printf( "%-15s%10.2f\n", "Radius:", radius);
        System.out.printf( "%-15s%10.2f\n", "Diameter:", radius * 2);
        System.out.printf( "%-15s%10.2f\n", "Circumference:", 2 * Math.PI * radius);
        System.out.printf( "%-15s%10.2f\n", "Area:", Math.PI * radius * radius);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner in = new Scanner(System.in);   
        System.out.println("Please input the radius:");
        System.out.println("Enter -1 to exit:");
        int radius = 0;
        while(true){
            try{
                radius = in.nextInt();
                if (radius == -1) {
                    System.out.println("GoodBye!");
                    break;
                }
                printResult(radius);
            }
            catch(InputMismatchException e){
                System.out.println("The radius should be a positive integer");
                in.next();//discard mismatched data
            }
            catch(Exception e){
                // do nothing      
            }
        }
        in.close();
    }
}
