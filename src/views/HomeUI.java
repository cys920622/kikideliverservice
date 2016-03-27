package views;

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
    private JButton packageHandlerButton = new JButton("Package Handler");
    private JButton distributorButton = new JButton("Distributor");
    private JButton delivererButton = new JButton("Deliverer");


    public HomeUI() {
        setBorder(new TitledBorder(
                new EtchedBorder(), "Parcel details"));
        setLayout(new BorderLayout(5, 5));
        add(initButtons(), BorderLayout.CENTER);
    }

    private JPanel initButtons() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));
        panel.add(clientButton);
        clientButton.addActionListener(new ButtonHandler());
        panel.add(clerkButton);
        clerkButton.addActionListener(new ButtonHandler());
        panel.add(packageHandlerButton);
        packageHandlerButton.addActionListener(new ButtonHandler());
        panel.add(distributorButton);
        distributorButton.addActionListener(new ButtonHandler());
        panel.add(delivererButton);
        delivererButton.addActionListener(new ButtonHandler());
        return panel;
    }

    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame f = new JFrame();
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
            switch (e.getActionCommand()) {
                case "Client":
                    //need to only be able to input pID
                    //should get back parcel info from pID
                    //should get back sender/receiver/status from dID through pID
                    //should probably be able to get back all the pID corresponding to dID
                    f.add(new DeliveryUI());
                    f.add(new ParcelUI());
                case "Clerk":
                    f.add(new DeliveryUI());
                    f.add(new ParcelUI());
                    //need to also add cash/credit info

                case "Package Handler":
                    //organizes pkgs at each center
                    //enters cID at the center its at to retrieve # of deliveries/parcels currently
                    //held there along with their dIDs and pIDs
                    //can get any info of parcel
                    //can get any info of delivery
                    //given the dID can also check cash/credit to see if paid for


                case "Distributor":
                    //delivers pkgs from center to center

                case "Deliverer":
                    //delivers pkgs from center to receiver
                    //inputs centerID (cID) to be able to find Parcels or Deliveries
                    //associated with that cID to then find the address of sender/receiver
                    //from the dID.. in order to deliver..
            }
            f.setSize(600, 1200);
            f.setVisible(true);
        }

    }
}
