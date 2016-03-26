import views.AddressUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by danielchoi on 2016-03-25.
 */
public class AppMain {
    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
        f.getContentPane().add(new AddressUI());
        f.setSize(600, 350);
        f.setVisible(true);
    }
}
