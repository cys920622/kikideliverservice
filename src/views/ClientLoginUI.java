package views;

import controllerBeans.ClientLoginBean;
import entityClasses.Clients;
import entityClasses.Delivery;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by danielchoi on 2016-03-28.
 */
public class ClientLoginUI extends JPanel {
    private JTextField cIDField = new JTextField(11);

    private JButton loginButton = new JButton("Login");
    private JRadioButton receiveRadioButton = new JRadioButton("Incoming packages");
    private JRadioButton sendRadioButton = new JRadioButton("Outgoing packages");
    private ButtonGroup group = new ButtonGroup();

    public ClientLoginUI() {
        setBorder(new TitledBorder(
                new EtchedBorder(), "Client login"));
        setLayout(new FlowLayout());
        add(initFields(), BorderLayout.NORTH);
        add(initButtons(), BorderLayout.CENTER);
    }

    private JPanel initFields() {
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout());
        panel.add(new JLabel("Client ID"), "align label");
        panel.add(cIDField, "wrap");
        return panel;
    }

    private JPanel initButtons() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));
        panel.add(loginButton);
        loginButton.addActionListener(new ButtonHandler());
        group.add(receiveRadioButton);
        group.add(sendRadioButton);
        panel.add(receiveRadioButton);
        panel.add(sendRadioButton);
        return panel;
    }

    private boolean isEmptyFieldData() {
        return (cIDField.getText().trim().isEmpty());
    }

    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "Login":
                    if (isEmptyFieldData()) {
                        JOptionPane.showMessageDialog(null,
                                "Please enter your client ID");
                    } else {
                        int cID = Integer.parseInt(cIDField.getText());
                        System.out.println("Client ID :" + cID);
                        ClientLoginBean bean = new ClientLoginBean(cID);
                        ArrayList<Delivery> in_packages = bean.getSentDeliveries();
                        ArrayList<Delivery> out_packages = bean.getReceivedDeliveries();
                        ArrayList<Clients> senders = bean.getSenderClients();
                        ArrayList<Clients> receivers = bean.getReceiverClients();

                        // Iterate and print sent delivery information
                        for (int i = 0; i < in_packages.size(); i++) {
                            Delivery in_package = in_packages.get(i);
                            Clients sender = senders.get(i);
                            int dID = in_package.getdID();
                            int sender_ID = in_package.getSender_ID();
                            int receiver_ID = in_package.getReceiver_ID();
                            String fname = sender.getFname();
                            String shippingType = in_package.getType();
                            System.out.println("SEND >>> dID: " + dID + ", Sender ID: " + sender_ID
                                    + ", Receiver ID: " + receiver_ID + ", Type: " + shippingType + ", To: "+fname);
                        }

                        // Iterate and print receive delivery information
                        for (int i = 0; i < out_packages.size(); i++) {
                            Delivery out_package = out_packages.get(i);
                            Clients receiver = receivers.get(i);
                            int dID = out_package.getdID();
                            int sender_ID = out_package.getSender_ID();
                            int receiver_ID = out_package.getReceiver_ID();
                            String fname = receiver.getFname();
                            String shippingType = out_package.getType();
                            System.out.println("RECEIVE >>> dID: " + dID + ", Sender ID: " + sender_ID
                                    + ", Receiver ID: " + receiver_ID + ", Type: " + shippingType + ", From: "+fname);
                        }
                    }
                    break;
            }
        }
    }
}
