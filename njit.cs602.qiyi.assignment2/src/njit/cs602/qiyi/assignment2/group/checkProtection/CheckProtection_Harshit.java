package njit.cs602.qiyi.assignment2.group.checkProtection;

import java.util.Scanner;
public class CheckProtection_Harshit {

    public static void main(String[] args) {
    String amount = "";
    Scanner in = new Scanner(System.in);
    System.out.println("Please Enter the Amount:");
    amount = in.nextLine();
    amount = CheckProtection_Harshit.Check(amount.toCharArray());
    System.out.printf("The amount to be printed on check : %s",amount);
    in.close();
    }
    public static String Check(char [] amount)
    {
        char [] ValidatedAmount = new char[9];
        int l = amount.length;
        for(int i=8,j=l-1;i>=0;i--,j--)
        {
            if(j>=0)
            {
                ValidatedAmount[i] = amount[j];
            }
            else
            {
                ValidatedAmount[i] = '*';
            }
        }
        return String.valueOf(ValidatedAmount);
    }
}
