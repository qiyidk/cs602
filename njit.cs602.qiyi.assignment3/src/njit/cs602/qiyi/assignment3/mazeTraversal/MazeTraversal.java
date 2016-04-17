package njit.cs602.qiyi.assignment3.mazeTraversal;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * MazeTraversal
 * </p>
 *
 * @author qiyi
 * @version 2016-4-15
 */
public class MazeTraversal {
    private LinkedList<int[]> path;
    private LinkedList<int[]> moves;
    private boolean hasPath;
    private final int[] startPoint;
    private String[][] maze;
    public  MazeTraversal(String[][] maze, int[] start){
        this.maze = new String[maze.length][maze[0].length];
        for (int i = 0 ; i < maze.length; i++)
            for (int j = 0; j < maze[0].length; j++)
                this.maze[i][j] = maze[i][j];
        startPoint = start;
        hasPath = false;
        path = new LinkedList<int[]>();
        moves = new LinkedList<int[]>();
        hasPath = dfs(this.maze, start, path, moves);
    }
    
    public List<int[]> getPath(){
        if (hasPath) return path;
        else return null;
    }
    
    public List<int[]> getMoves(){
        if (hasPath) return moves;
        else return null;
    }
    
    private boolean dfs(String[][] maze, int[] start, LinkedList<int[]> path, LinkedList<int[]> moves){
        path.add(start);
        moves.add(start);
        
        int x = start[0];
        int y = start[1];
        maze[x][y] = "#";// represent visited
        if ((x == 0 || x == maze.length - 1 || y == 0 || y == maze[0].length - 1) && !start.equals(startPoint)) hasPath = true;
        if (!hasPath && x != 0 && maze[x - 1][y].equals(".")) 
            hasPath = dfs(maze, new int[]{x - 1, y}, path, moves);
        if (!hasPath && x != maze.length - 1 && maze[x + 1][y].equals(".")) 
            hasPath = dfs(maze, new int[]{x + 1, y}, path, moves);
        if (!hasPath && y != 0 && maze[x][y - 1].equals(".")) 
            hasPath = dfs(maze, new int[]{x, y - 1}, path, moves);
        if (!hasPath && y != maze[0].length - 1 && maze[x][y + 1].equals(".")) 
            hasPath = dfs(maze, new int[]{x, y + 1}, path, moves);
        
        if (hasPath) return true;
        else{
            path.removeLast();
            moves.add(start); // add an extra move to represent backtrack
            return false;
        }
    }

}
