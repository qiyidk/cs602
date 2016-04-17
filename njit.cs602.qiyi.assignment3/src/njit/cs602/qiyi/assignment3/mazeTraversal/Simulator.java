package njit.cs602.qiyi.assignment3.mazeTraversal;

import java.awt.Graphics;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * <p>
 * Simulator
 * </p>
 *
 * @author qiyi
 * @version 2016-4-17
 */
@SuppressWarnings("serial")
public class Simulator extends JFrame{

    private String[][] maze;
    private List<int[]> moves;
    public Simulator(String[][] maze, List<int[]> moves){
        super();
        this.maze = maze;
        this.moves = moves;
        this.setTitle("Maze Traversal");
        this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    public void play(){
        if (moves != null){
            for (int i = 0; i < moves.size(); i++){
                int[] move = moves.get(i);
                int x = move[0];
                int y = move[1];
                if (maze[x][y] == "+"){
                    // move twice represents backtrack
                    maze[x][y] = ".";
                }
                else{
                    maze[x][y] = "+";
                }
                repaint();
                
                try{
                    Thread.sleep(1000);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        else JOptionPane.showMessageDialog(getContentPane(), "No Valid Path!");
    }
    
    public void paint(Graphics g){
        super.paint(g);
        for (int i = 0; i < maze.length; i++){
            for (int j = 0; j < maze[0].length; j++){
                int x = 80 + i * 20;
                int y = 80 + j * 20;
                g.drawString(maze[i][j], y, x);
            }
        }
    }
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
        List<int[]> moves = mt.getMoves();
        
        Simulator s = new Simulator(maze, moves);
        
        s.play();
    }
}
