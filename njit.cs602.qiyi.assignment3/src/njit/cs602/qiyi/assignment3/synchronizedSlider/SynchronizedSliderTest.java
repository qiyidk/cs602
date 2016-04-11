package njit.cs602.qiyi.assignment3.synchronizedSlider;

import javax.swing.JFrame;

/**
 * <p>
 * SynchronizedSliderTest
 * </p>
 *
 * @author qiyi
 * @version 2016-4-10
 */
public class SynchronizedSliderTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        JFrame f = new JFrame("SynchronizedSlider");
        SynchronizedSlider ss = new SynchronizedSlider();
        f.add(ss);
        f.setSize(300, 200);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

}
