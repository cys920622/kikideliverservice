import views.AddressUI;
import views.ParcelUI;

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
        f.getContentPane().add(new ParcelUI());
        f.setSize(600, 700);
        f.setVisible(true);
    }
}
