package views;

import controllerBeans.ClientLoginBean;
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
                        for (Delivery in_package : in_packages) {
                            Delivery d = in_package;
                            int dID = d.getdID();
                            int sender_ID = d.getSender_ID();
                            int receiver_ID = d.getReceiver_ID();
                            String shippingType = d.getType();
                            System.out.println("SEND:");
                            System.out.println("dID: " + dID +", Sender ID: " + sender_ID
                            + ", Receiver ID: " + receiver_ID + ", Type: " + shippingType);
                        }
                        for (Delivery out_package : out_packages) {
                            Delivery d = out_package;
                            int dID = d.getdID();
                            int sender_ID = d.getSender_ID();
                            int receiver_ID = d.getReceiver_ID();
                            String shippingType = d.getType();
                            System.out.println("RECEIVE:");
                            System.out.println("dID: " + dID +", Sender ID: " + sender_ID
                                    + ", Receiver ID: " + receiver_ID + ", Type: " + shippingType);
                        }
                    }
                    break;
            }
        }
    }
}
