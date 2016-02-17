package njit.cs602.qiyi.assignment1.group;

import java.util.Random;

/**
 * <p>
 * DiceRolling
 * </p>
 *
 * @author qiyi
 * @version 2016Äê2ÔÂ14ÈÕ
 */
public class DiceRolling {
    
    private Random ran;
    private int num; // number of dices
    public DiceRolling(int num){
        ran = new Random();
        this.num = num;
    }
    private int roll(){
        int r = 0;
        for (int i = 0; i < num; i++) r+= ran.nextInt(6) + 1;
        return r;
    }
    /**
     * get distribution statistics of specified times of dice rolling and output the result
     * @param times
     * @return count of each value 
     */
    public int[] statistic(int times){
        int length = (6 * num - 1 * num + 1);
        int[] res = new int[length];
        for (int i = 0; i < times; i++){
            int r = roll();
            res[r - num]++;
        }
        outputBarChart(res, times);
        return res;
    }
    
    private void outputBarChart(int[] stat, int times){
        int max = 0;
        for (int n : stat) if (n > max) max = n;
        int sections = (int)(times / stat.length > 5 ? 10 : times / stat.length * 2);
        int interval = max / sections;
        for (int sec = sections - 1; sec >= 0; sec--){
            System.out.printf("\t");
            for(int n : stat) {
                if (n > interval * sec) System.out.printf("%-8s","**");
                else System.out.printf("\t");
            }
            System.out.println();
        }
        System.out.printf("%-8s","Value:");
        for(int i = num; i <= num * 6; i++) System.out.printf("%-8s",i);
        System.out.printf("\n%-8s","Count:");
        for(int n : stat) System.out.printf("%-8s", n);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        DiceRolling r = new DiceRolling(2);
        r.statistic(36000000);
    }

}
