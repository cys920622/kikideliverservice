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
    private JFrame parent;

//    private static HomeUI home;
//
//    public static HomeUI getInstance() {
//        if (home == null) {
//            return home = new HomeUI();
//        } else {
//            return home;
//        }
//    }


    public HomeUI(JFrame parent) {
        this.parent = parent;
        setBorder(new TitledBorder(
                new EtchedBorder(), "User List"));
        add(initButtons());
    }

    private JPanel initButtons() {

        JPanel panel = new JPanel();
        //panel.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));
        panel.setSize(3,3);
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
            parent.dispose();
            JFrame child = new JFrame();
            child.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            child.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
            switch (e.getActionCommand()) {
                case "Client":
                    //need to only be able to input pID
                    //should get back parcel info from pID
                    //should get back sender/receiver/status from dID through pID
                    //should probably be able to get back all the pID corresponding to dID
//                    f.add(new AddressUI());
//                    f.add(new DeliveryUI());
//                    f.add(new ParcelUI());
                    child.add(new ClientLoginUI(child));
                    child.setSize(700, 700);
                    break;

                case "Clerk":
                    child.add(new ClerkHomeUI());
                    child.setSize(1300, 700);
                    break;

                    //need to also add cash/credit info


                case "Deliverer":
                    //enters cID at the center its at to retrieve # of deliveries/parcels currently (aggregate)
                    //held there along with their dIDs and pIDs
                    //can get any info of parcel
                    //can get any info of delivery
                    //given the dID can also check cash/credit to see if paid for??? no? yes?
                    child.add(new AskForCentreUI());
                    child.setSize(1300, 600);
                    break;

            }
            child.setVisible(true);
        }

    }
}
