package njit.cs602.qiyi.assignment1.group;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * <p>
 * TicTacToe
 * </p>
 *
 * @author qiyi
 * @version 2016-2-11
 */
public class TicTacToe extends JFrame{
    
    private static final long serialVersionUID = 1L;

    private enum Value{
        X, O, EMPTY
    }
    
    private Value[][] board = new Value[3][3];
    private boolean hasAI;
    private boolean isAIfirst;
    private int steps = 0;// the steps that the players have already taken
    private String winner = null;
    private boolean clicked = false;
    private int[] location = new int[2]; //current clicked location
    
    public TicTacToe(){       
        
        //initiate UI
        super();
        this.setSize(500, 500);
        this.getContentPane().setLayout(null);
        this.setTitle("TicTacToe");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);;
        this.setVisible(true);
        
        //initiate mouse listening
        initMouseListener();
        
        //initiate AI setting
        initAISettings();
        
        //initiate board
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++)
                board[i][j] = Value.EMPTY;
        play();
    }
    private void play(){
        int[] move = new int[2];
        do{ 
            steps++;
            Value v = steps % 2 == 1 ? Value.X : Value.O;
            if ((hasAI) && (steps % 2 == (isAIfirst ? 1 : 0))) move = getAIInput();
            else {
                clicked = false;
                while(!clicked); //wait for mouse clicking
                move[0] = (location[1] - 100) / 100;
                move[1] = (location[0] - 100) / 100;
            }
            board[move[0]][move[1]] = v;
            drawPoint(move, v);
            
        }while(!win(move) && (steps < 9));
        if (winner != null){
            JOptionPane.showMessageDialog(rootPane, "The winner is: " + winner + "!");
        }else{
            JOptionPane.showMessageDialog(rootPane, "Draw!");
        }
    }
    private boolean canWin(int[] move, int steps){
        int x = move[0], y = move[1];
        if (steps <= 4) return false;
        Value v = steps % 2 == 1 ? Value.X : Value.O;
        return ((board[x][0] == v) && (board[x][1] == v) && (board[x][2] == v))
                  || ((board[0][y] == v) && (board[1][y] == v) && (board[2][y] == v))
                      || ((board[0][0] == v) && (board[1][1] == v) && (board[2][2] == v))
                          || ((board[0][2] == v) && (board[1][1] == v) && (board[2][0] == v));
    }
    private boolean win(int[] move){
        if (canWin(move, steps)) {
            if (hasAI)  winner = ((steps % 2 == 1) && (isAIfirst)) 
                    || ((steps % 2 == 0) && (!isAIfirst)) ? "Computer" : "PLAYER";
            else winner = steps % 2 == 1 ? "PLAYER 1" : "PLAYER2";
            return true;
        }
        else return false;
    }    
    
    public void paint(Graphics g){
        // paint board
        g.drawLine(100, 100, 400, 100);
        g.drawLine(100, 200, 400, 200);
        g.drawLine(100, 300, 400, 300);
        g.drawLine(100, 400, 400, 400);
        g.drawLine(100, 100, 100, 400);
        g.drawLine(200, 100, 200, 400);
        g.drawLine(300, 100, 300, 400);
        g.drawLine(400, 100, 400, 400);
    }
    
    private void drawPoint(int[] move, Value value){
        if (value == Value.X) {
            this.getGraphics().drawLine((move[1] + 1) * 100, (move[0] + 1) * 100, (move[1] + 2) * 100, (move[0] + 2) * 100);
            this.getGraphics().drawLine((move[1] + 2) * 100, (move[0] + 1) * 100, (move[1] + 1) * 100, (move[0] + 2) * 100);
        }
        else{
            this.getGraphics().drawOval((move[1] + 1) * 100, (move[0] + 1) * 100, 100, 100);
        }
    }
    
    private void initMouseListener(){
        
        this.addMouseListener(new MouseListener() {
            
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}        
            @Override
            public void mouseEntered(MouseEvent e) {}
            
            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub
                if (!clicked){
                    location[0] = e.getPoint().x;
                    location[1] = e.getPoint().y;
                    if (valid(location)) clicked = true;
                }
            }
        });
    }
    private void initAISettings(){
        hasAI = JOptionPane.showOptionDialog(rootPane, "Who would you like to play with?", "Choose a player", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Computer", "Another Player"}, null) == 0;
        if (hasAI){
            // select who makes a move first
            isAIfirst = JOptionPane.showOptionDialog(rootPane, "Who will move first?", "Choose a player", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Computer", "Player"}, null) == 0;
        }
    }
    
    // simple AI to get next move
    private int[] getAIInput(){
        if (steps == 0) return new int[]{1, 1};
        int[] input = new int[2];
        // check whether AI can win after a move, if so, do that move.
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++){
                if (board[i][j] == Value.EMPTY){
                    Value v = steps % 2 == 1 ? Value.X : Value.O;
                    board[i][j] = v;
                    if (canWin(new int[]{i , j}, steps)){
                        board[i][j] = Value.EMPTY;
                        return new int[]{i , j};
                    }
                    board[i][j] = Value.EMPTY;
                }
            }
        // check whether the opponent can win after a move, if so, try to prevent its opponent
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++){
                if (board[i][j] == Value.EMPTY){
                    Value v = (steps + 1) % 2 == 1 ? Value.X : Value.O;
                    board[i][j] = v;
                    if (canWin(new int[]{i , j}, steps + 1)){
                        board[i][j] = Value.EMPTY;
                        return new int[]{i , j};
                    }
                    board[i][j] = Value.EMPTY;
                }
            }
        // if the center is empty, make a move here
        if (board[1][1] == Value.EMPTY) return new int[]{1, 1};
        //otherwise, return the first empty position
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++)
                if (board[i][j] == Value.EMPTY) return new int[]{i , j};
        return input;
    }
    
    private boolean valid(int[] location){
        int x = location[0];
        int y = location[1];
        if ((x < 100) || (x > 400) || (y < 100) || (y > 400)) return false;
        int i = (y - 100) / 100;
        int j = (x - 100) / 100;
        return board[i][j] == Value.EMPTY;
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new TicTacToe();
    }
}
