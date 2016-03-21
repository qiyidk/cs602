package njit.cs602.qiyi.assignment2.group.guessNumber;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * <p>
 * GuessNumber
 * </p>
 *
 * @author qiyi
 * @version 2016-3-20
 */
public class GuessNumber extends JFrame {
    
    private static final long serialVersionUID = 1L;
    private int max; //the upper bound of the number to be guessed
    private int secretNumber; // the number to be guessed
    private int lastNumber;// last number that was guessed
    
    private JPanel toolbar;
    private JButton restart;
    private JLabel numberRange;
    private JLabel lastN;
    private JLabel guessingResult;
    
    private JPanel guessingPanel;
    private JLabel textLabel;
    private JTextField textField;
    private JButton guess;
    
   
    
    /**
     * constructor
     * @param max the upper bound of the number to be guessed
     */
    public GuessNumber(int max){
        super();
        this.max = max;
        init();
        play();
    }

    public GuessNumber(){
        this(1000);
    }
    
    private void init(){
        this.setSize(600, 300);
        this.setTitle("Guess Number");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        toolbar = new JPanel(new GridLayout(1, 4, 5, 0));
        add(toolbar, BorderLayout.NORTH);
        
        restart = new JButton("Play Again");
        restart.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                play();
            }
        });
        toolbar.add(restart);
        
        numberRange = new JLabel("Number Range: " + "1 - " + max);
        numberRange.setHorizontalAlignment(SwingConstants.CENTER);
        toolbar.add(numberRange);
        
        lastN = new JLabel();
        lastN.setHorizontalAlignment(SwingConstants.CENTER);
        toolbar.add(lastN);
        
        guessingResult = new JLabel();
        guessingResult.setHorizontalAlignment(SwingConstants.CENTER);
        toolbar.add(guessingResult);
        
        guessingPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        guessingPanel.setOpaque(true);
        add(guessingPanel);
        
        textLabel = new JLabel("Number:      ");
        guessingPanel.add(textLabel);
        
        textField = new JTextField(20);
        guessingPanel.add(textField);
        
        guess = new JButton("Guess");
        guess.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                int number = 0;
                try{
                    number = Integer.parseInt(textField.getText());
                    if (number <= 0 || number > max) throw new Exception("Invalid input");
                    setBackgroundColor(number);
                    setLabelContent(number);
                    lastNumber = number;
                    if (number == secretNumber){
                        JOptionPane.showMessageDialog(rootPane, "Correct");
                        guessingPanel.setBackground(Color.WHITE);
                        textField.setEditable(false);
                        guess.setEnabled(false);
                    }
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(rootPane, "Invalid input");
                }
                
            }
        });
        guessingPanel.add(guess);
        this.setVisible(true);
    }
    private void play(){
        
        // get a new secret number
        setSecretNumber();
        // reset
        this.guessingPanel.setBackground(Color.WHITE);
        this.guessingResult.setText("Have a try");
        this.lastN.setText("Last Number:");
        textField.setText("");
        textField.setEditable(true);
        guess.setEnabled(true);
    }
    // calculate and set the number to be guessed
    private void setSecretNumber(){
        secretNumber = (int)(Math.random() * max) + 1;
    }
    
    // set the color of background
    private void setBackgroundColor(int number){
        if (Math.abs(number - secretNumber) >= Math.abs(lastNumber - secretNumber)){
            //get colder
            this.guessingPanel.setBackground(Color.BLUE);
        }
        else{
            // get warmer
            this.guessingPanel.setBackground(Color.RED);this.setVisible(true);
        }
    }
    
    // set the content of last number and result label
    private void setLabelContent(int number){
        if (number == secretNumber) this.guessingResult.setText("Correct");
        else if (number > secretNumber) this.guessingResult.setText("Too High");
        else this.guessingResult.setText("Too Low");
        if (number > 0) lastN.setText("Last Number: " + String.valueOf(number));
    }


}
