package njit.cs602.qiyi.assignment3.synchronizedSlider;

import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

/**
 * <p>
 * SynchronizedSlider
 * </p>
 *
 * @author qiyi
 * @version 2016-4-10
 */
public class SynchronizedSlider extends JPanel{

    private static final long serialVersionUID = 1L;
    private int value;
    private JSlider js;
    private JTextField textField;
    private JLabel lb_val;
    private JLabel lb_input;
    
    public SynchronizedSlider(){
        super();
        init();
    }
    
    private void init(){
        
        this.setLayout(null);
        value = 50;
        
        lb_val = new JLabel("Current Value: ");
        lb_val.setBounds(80, 20, 100, 20);
        add(lb_val);
        
        js = new JSlider(0, 100, 50);
        js.setBounds(40, 50, 200, 50);
        js.setMajorTickSpacing(10);
        js.setPaintTicks(true);
        js.addChangeListener(new ChangeListener() {
            
            @Override
            public void stateChanged(ChangeEvent e) {
                // TODO Auto-generated method stub
                updateValue(js.getValue());
            }
        });
        add(js);
        
        lb_input = new JLabel("Enter value:");
        lb_input.setBounds(30, 100, 80, 20);
        add(lb_input);
        
        textField = new JTextField(String.valueOf(value));
        textField.setBounds(100, 100, 100, 20);
        Document d = textField.getDocument();
        d.addDocumentListener(new DocumentListener() {
            
            @Override
            public void removeUpdate(DocumentEvent e) {
                // TODO Auto-generated method stub
            }
            
            @Override
            public void insertUpdate(DocumentEvent e) {
                // TODO Auto-generated method stub
                if (!validate(textField.getText())){
                    updateValue(Integer.parseInt("0"));
                }
                else updateValue(Integer.parseInt(textField.getText()));
            }
            
            @Override
            public void changedUpdate(DocumentEvent e) {
                // TODO Auto-generated method stub
                if (!validate(textField.getText())){
                    updateValue(Integer.parseInt("0"));
                }
                else updateValue(Integer.parseInt(textField.getText()));
            }
        });
        add(textField);
    }
    
    private boolean validate(String v){
        try{
            int i = Integer.parseInt(v);
            if (i < 0 || i > 100) return false;
            else return true;
        }
        catch(Exception e){
            return false;
        }
    }
    private void updateValue(int v){
        value = v;
        repaint();
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawString("0", 33, 70);
        g.drawString("100", 240, 70);
        
        js.setValue(value);
        g.drawString(String.valueOf(value), 170, 35);
        if (!String.valueOf(value).equals(textField.getText())) textField.setText(String.valueOf(value));
    }


}
