package njit.cs602.qiyi.assignment2.group.checkProtection;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * <p>
 * CheckProtection
 * </p>
 *
 * @author qiyi
 * @version 2016-3-24
 */
public class CheckProtection {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner in = new Scanner(System.in);   
        System.out.println("Please input the amount:");
        System.out.println("Enter non-positive number to exit:");
        double amount = 0;
        while(true){
            try{
                amount = in.nextDouble();
                if (amount <= 0) {
                    System.out.println("GoodBye!");
                    break;
                }
                if (amount >= 1000000) System.out.println("The amount is too big to convert");
                else printResult(amount);
            }
            catch(InputMismatchException e){
                System.out.println("The amount should be a positive number");
                in.next();//discard mismatched data
            }
            catch(Exception e){// do nothing      
            }
        }
        in.close();
    }

    private static void printResult(double amount) {
        // TODO Auto-generated method stub
        String[] r = Double.toString(amount).split("[.]");
        char[] i = r[0].toCharArray();
        char[] d = amount == (int)amount? new char[]{'0', '0'} : r[1].toCharArray();
        char[] res = new char[9];
        int p = 8;// pointer of res
        if (d != null){
            // if has decimal, deal with decimal first
            if (d.length > 7) {
                System.out.println("The amount is too big to convert");
                return;
            }
            else {
                System.arraycopy(d, 0, res, 8 + 1 - d.length, d.length);
                p = 8 - d.length;
                res[p--] = '.';
            }
        }
        // deal with integer part
        int j = i.length - 1;
        int f = 0;
        for (; p >= 0; p--){
            f = (f + 1) % 4;
            if (f == 0 && j != -1) res[p] = ',';
            else{
                res[p] = j >= 0 ? i[j--] : '*';
            }
        }
        if (j != -1) {
            System.out.println("The amount is too big to convert");
            return;
        }
        else System.out.println(new String(res));
    }
}
