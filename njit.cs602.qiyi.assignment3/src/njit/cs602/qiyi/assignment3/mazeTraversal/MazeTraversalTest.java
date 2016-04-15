package njit.cs602.qiyi.assignment3.mazeTraversal;

import java.util.List;

/**
 * <p>
 * Simulator
 * </p>
 *
 * @author qiyi
 * @version 2016-4-15
 */
public class MazeTraversalTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String[] mazeS = {
        "# # # # # # # # # # # #",
        "# . . . # . . . . . . #",
        ". . # . # . # # # # . #",
        "# # # . # . . . . # . #",
        "# . . . . # # # . # . .",
        "# # # # . # . # . # . #",
        "# . . # . # . # . # . #",
        "# # . # . # . # . # . #",
        "# . . . . . . . . # . #",
        "# # # # # # . # # # . #",
        "# . . . . . . # . . . #",
        "# # # # # # # # # # # #",};
        String[][] maze = new String[12][12];
        for (int i = 0; i < 12; i++) maze[i] = mazeS[i].split(" ");
        MazeTraversal mt = new MazeTraversal(maze, new int[]{2, 0});
        List<int[]> path = mt.getPath();
        if (path != null){
            for (int[] p : path){
                System.out.println(p[0] + "," + p[1]);
                maze[p[0]][p[1]] = "x";
            } 
            for (int i = 0 ; i < maze.length; i++){
                for (int j = 0; j < maze[0].length; j++) System.out.print(maze[i][j] + " ");
                System.out.println();
            }
        }
        else System.out.println("No valid path");
    }

}
