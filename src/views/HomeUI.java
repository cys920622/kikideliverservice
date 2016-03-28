package views;

import javax.management.Query;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by stellafang. on 2016-03-26.
 */
public class HomeUI extends JPanel {
    private JButton clientButton = new JButton("Client");
    private JButton clerkButton = new JButton("Clerk");
    private JButton delivererButton = new JButton("Deliverer");
    public static JFrame f = new JFrame();

//    private static HomeUI home;
//
//    public static HomeUI getInstance() {
//        if (home == null) {
//            return home = new HomeUI();
//        } else {
//            return home;
//        }
//    }


    public HomeUI() {
        setBorder(new TitledBorder(
                new EtchedBorder(), "User List"));
        add(initButtons());
    }

    private JPanel initButtons() {
        JPanel panel = new JPanel();
        //panel.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));
        panel.setSize(10,10);
        panel.add(clientButton);
        clientButton.addActionListener(new ButtonHandler());
        panel.add(clerkButton);
        clerkButton.addActionListener(new ButtonHandler());
        panel.add(delivererButton);
        delivererButton.addActionListener(new ButtonHandler());
        return panel;
    }

    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            f.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
            switch (e.getActionCommand()) {
                case "Client":
                    //need to only be able to input pID
                    //should get back parcel info from pID
                    //should get back sender/receiver/status from dID through pID
                    //should probably be able to get back all the pID corresponding to dID
                    f.add(new AddressUI());
                    f.add(new DeliveryUI());
                    f.add(new ParcelUI());
                    f.setSize(700, 1200);
                    break;

                case "Clerk":
                    f.add(new ClerkHomeUI());
                    f.setSize(1200, 650);
                    break;

                    //need to also add cash/credit info


                case "Deliverer":
                    //enters cID at the center its at to retrieve # of deliveries/parcels currently (aggregate)
                    //held there along with their dIDs and pIDs
                    //can get any info of parcel
                    //can get any info of delivery
                    //given the dID can also check cash/credit to see if paid for??? no? yes?
                    f.add(new AskForCentreUI());
                    f.setSize(1000, 500);
                    break;

            }
            f.setVisible(true);
        }

    }
}
