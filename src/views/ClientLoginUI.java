package views;

import controllerBeans.ClientLoginBean;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by danielchoi on 2016-03-28.
 */
public class ClientLoginUI extends JPanel {
    private JFrame parent;
    ClientLoginBean bean = new ClientLoginBean();
    private JTextField cIDField = new JTextField(11);
    private JTextField dIDField = new JTextField(6);
    private JTextField pIDField = new JTextField(6);

    private JButton cIDButton = new JButton("Find by Client ID");
    private JButton dIDButton = new JButton("Find by Delivery ID");
    private JButton pIDButton = new JButton("Find by Parcel ID");

//    private JTable resultTable = new JTable();

//    private JRadioButton receiveRadioButton = new JRadioButton("Incoming packages");
//    private JRadioButton sendRadioButton = new JRadioButton("Outgoing packages");
//    private ButtonGroup group = new ButtonGroup();

    public ClientLoginUI(JFrame f) {
        parent = f;
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
        panel.add(new JLabel("Delivery ID"), "align label");
        panel.add(dIDField, "wrap");
        panel.add(new JLabel("Parcel ID"), "align label");
        panel.add(pIDField, "wrap");
        return panel;
    }

    private JPanel initButtons() {
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout());
        panel.add(cIDButton, "wrap");
        panel.add(dIDButton, "wrap");
        panel.add(pIDButton, "wrap");
        cIDButton.addActionListener(new ButtonHandler());
        dIDButton.addActionListener(new ButtonHandler());
        pIDButton.addActionListener(new ButtonHandler());
//        group.add(receiveRadioButton);
//        group.add(sendRadioButton);
//        panel.add(receiveRadioButton);
//        panel.add(sendRadioButton);
        return panel;
    }

    private boolean isEmptyFieldData(JTextField field) {
        return (field.getText().trim().isEmpty());
    }

    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "Find by Client ID":
                    if (isEmptyFieldData(cIDField)) {
                        JOptionPane.showMessageDialog(null,
                                "Error: please enter your client ID");
                    } else {
                        try {
                            int cID = Integer.parseInt(cIDField.getText());
                            System.out.println("Client ID :" + cID);
                            JFrame child = new JFrame();
                            ClientResultUI childPanel = new ClientResultUI(parent, bean, cID, "cID");
                            child.add(childPanel);
                            child.pack();
                            int width = childPanel.getTableWidth();
                            if (width == 0) {
                                break;
                            }
                            child.setSize(width, 300);
                            child.setVisible(true);
                        } catch (NumberFormatException e1) {
                            JOptionPane.showMessageDialog(null,
                                    "Error: input must be numbers only");
                            e1.printStackTrace();
                        }
                    }
                    break;
                case "Find by Delivery ID":
                    if (isEmptyFieldData(dIDField)) {
                        JOptionPane.showMessageDialog(null,
                                "Error: please enter the delivery ID");
                    } else {

                        try {
                            int dID = Integer.parseInt(dIDField.getText());
                            System.out.println("Delivery ID :" + dID);
                            JFrame child = new JFrame();
                            ClientResultUI childPanel = new ClientResultUI(parent, bean, dID, "dID");
                            child.add(childPanel);
                            child.pack();
                            child.setSize(childPanel.getTableWidth(), 300);
                            child.setVisible(true);
//                        bean.testConnection();
//                        parent.dispose();
                        } catch (NumberFormatException e1) {
                            e1.printStackTrace();
                            JOptionPane.showMessageDialog(null,
                                    "Error: input must be numbers only");
                        }
                    }
                    break;
                case "Find by Parcel ID":
                    if (isEmptyFieldData(pIDField)) {
                        JOptionPane.showMessageDialog(null,
                                "Error: please enter the parcel ID");
                    } else {
                        try {
                            int pID = Integer.parseInt(pIDField.getText());
                            JFrame child = new JFrame();
                            child.add(bean.getParcelQueryAsJPanel(pID));
                            child.pack();
                            child.setVisible(true);
                        } catch (NumberFormatException e1) {
                            e1.printStackTrace();
                            JOptionPane.showMessageDialog(null,
                                    "Error: input must be numbers only");
                        }
                    }
                    break;
            }
        }
    }
}
