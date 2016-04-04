package views;

import controllerBeans.DeliveryBean;
import entityClasses.Delivery;
import entityClasses.Parcel;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Created by danielchoi on 2016-03-26.
 */
public class DeliveryUI extends JPanel {
    private JTextField dIDField = new JTextField(6);
    private JTextField typeField = new JTextField(20);
    private JTextField statusField = new JTextField(20);
    private JTextField sender_IDField = new JTextField(11);
    private JTextField receiver_IDField = new JTextField(11);

    private JButton createButton = new JButton("Start");
    private JButton updateButton = new JButton("Update");
    private JButton deleteButton = new JButton("Delete");
    private JButton firstButton = new JButton("First");
    private JButton lastButton = new JButton("Last");
    private JButton nextButton = new JButton("Next");
    private JButton previousButton = new JButton("Previous");
    private JButton clearButton = new JButton("Clear");

    private DeliveryBean bean;
    private int randdID = new Random().nextInt((999999-0) +1);
    private int sender = 0;
    private int receiver = 0;
    private boolean isUpdateable = false;


    public DeliveryUI(Boolean isUpdatable, String sql) {
        setBorder(new TitledBorder(
                new EtchedBorder(), "Delivery details"));
        setLayout(new BorderLayout(5, 5));

        bean = new DeliveryBean(sql);

        this.isUpdateable = isUpdatable;
        add(initFields(), BorderLayout.NORTH);
        add(initButtons(), BorderLayout.CENTER);

        dIDField.setText(String.valueOf(0));
        sender_IDField.setText(String.valueOf(0));
        receiver_IDField.setText(String.valueOf(0));
    }

    private JPanel initButtons() {
        JPanel panel = new JPanel();

        if(isUpdateable) {
            panel.add(updateButton);
            updateButton.addActionListener(new ButtonHandler());
            panel.add(clearButton);
            clearButton.addActionListener(new ButtonHandler());
            panel.add(deleteButton);
            deleteButton.addActionListener(new ButtonHandler());
            panel.add(deleteButton);
            deleteButton.addActionListener(new ButtonHandler());
            panel.add(firstButton);
            firstButton.addActionListener(new ButtonHandler());
            panel.add(lastButton);
            lastButton.addActionListener(new ButtonHandler());
            panel.add(nextButton);
            nextButton.addActionListener(new ButtonHandler());
            panel.add(previousButton);
            previousButton.addActionListener(new ButtonHandler());
        }
        if (!isUpdateable) {
            panel.add(createButton);
            createButton.addActionListener(new ButtonHandler());
            panel.add(clearButton);
            clearButton.addActionListener(new ButtonHandler());
        }
        return panel;
    }

    private JPanel initFields() {
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout());
        panel.add(new JLabel("Delivery ID"), "align label");
        panel.add(dIDField, "wrap");
        panel.add(new JLabel("Delivery type"), "align label");
        panel.add(typeField, "wrap");
        panel.add(new JLabel("Status"), "align label");
        panel.add(statusField, "wrap");
        panel.add(new JLabel("Sender ID"), "align label");
        panel.add(sender_IDField, "wrap");
        panel.add(new JLabel("Receiver ID"), "align label");
        panel.add(receiver_IDField, "wrap");
        return panel;
    }
    private Boolean checkFieldData() {
        try {
            if (dIDField.getText().length()>6) {
                JOptionPane.showMessageDialog(null, "The delivery ID can only be max 6 numbers long");
                return false;
            }
            Integer.parseInt(dIDField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "The delivery ID can only be a number");
            return false;
        }

        try {
            if (sender_IDField.getText().length() > 11) {
                JOptionPane.showMessageDialog(null, "The sender ID can only be max 6 numbers long");
                return false;
            }
            Integer.parseInt(sender_IDField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "The sender ID can only be a number");
            return false;
        }

        try {
            if ( receiver_IDField.getText().length() > 6) {
                JOptionPane.showMessageDialog(null, "The receiver ID can only be max 6 numbers long");
                return false;
            }
            Integer.parseInt(receiver_IDField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "The receiver ID can only be a number");
            return false;
        }

        String type = typeField.getText().toLowerCase();
        if(type.length()>20) {
            JOptionPane.showMessageDialog(null, "The delivery type can only max 20 characters long");
            return false;
        }

        String status = statusField.getText().toLowerCase();
        if(status.length()>20) {
            JOptionPane.showMessageDialog(null, "The delivery status can only max 20 characters long");
            return false;
        }
        return true;
    }

    private Delivery getFieldData() {
        Delivery d = new Delivery();
//        if (checkFieldData()) {
            d.setdID(Integer.parseInt(dIDField.getText()));
            d.setType(typeField.getText());
            d.setStatus(statusField.getText());
            d.setSender_ID(Integer.parseInt(sender_IDField.getText()));
            d.setReceiver_ID(Integer.parseInt(receiver_IDField.getText()));
//            return d;
//        }
        return d;
    }

    private void setFieldData(Delivery d) {
        dIDField.setText(String.valueOf(d.getdID()));
        typeField.setText(d.getType());
        statusField.setText(d.getStatus());
        sender_IDField.setText(String.valueOf(d.getSender_ID()));
        receiver_IDField.setText(String.valueOf(d.getReceiver_ID()));
    }

    public boolean isEmptyFieldData() {
        return (dIDField.getText().trim().equals("0")
                || typeField.getText().trim().isEmpty()
                || statusField.getText().trim().isEmpty()
                || sender_IDField.getText().trim().equals("0")
                || receiver_IDField.getText().trim().equals("0"));
    }

    public void setRanddID (int rand) {
        this.randdID = rand;
    }
    public void setSender (int clID) {
        this.sender = clID;

    }

    public void setReceiver (int clID) {
        this.receiver = clID;

    }
    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Delivery d = getFieldData();
            //String type = d.getType().toLowerCase();
            //String status = d.getStatus().toLowerCase();
            // = typeField.getText().toLowerCase();
            //String status = statusField.getText().toLowerCase();
            switch (e.getActionCommand()) {
                case "Save":
                    if (isEmptyFieldData()) {
                        JOptionPane.showMessageDialog(null,
                                "Please fill in missing info");
                        break;
                    }
                    if(!d.getType().equals("standard")&&!d.getType().equals("expedited")&&!d.getType().equals("express")) {
                        JOptionPane.showMessageDialog(null, "The valid delivery types are: standard, expedited, express");
                        break;
                    }
                    if(!d.getStatus().equals("in transit")&&!d.getStatus().equals("delivered")&&!d.getStatus().equals("just left")) {

                        JOptionPane.showMessageDialog(null, "The valid delivery statuses are: in transit, delivered, just left");
                        break;
                    }
                    if (bean.create(d) != null) {
                        JOptionPane.showMessageDialog(null,
                                "New delivery created :" + String.valueOf(d.getdID()));
                        createButton.setText("Start");
                        break;
                    }else {
                        JOptionPane.showMessageDialog(null,
                                "Please check if you have entered a unique delivery ID and an existing sender/receiver");
                        break;
                    }
                case "Start":
                    d.setdID(randdID); //TODO: should use a counter here
                    d.setType(""); //TODO: should use a radio button
                    d.setStatus("");
                    d.setSender_ID(sender);
                    d.setReceiver_ID(receiver);
                    setFieldData(d);
                    createButton.setText("Save");
                    break;

                case "Clear":
                    d.setdID(randdID); //TODO: should use a counter here
                    d.setType(""); //TODO: should use a radio button
                    d.setStatus("");
                    d.setSender_ID(sender);
                    d.setReceiver_ID(receiver);
                    setFieldData(d);
                    break;
                case "Update":
                    if (isEmptyFieldData()) {
                        JOptionPane.showMessageDialog(null,
                                "Please fill in missing info");
                        break;
                    }
                    if(!d.getType().equals("standard")&&!d.getType().equals("expedited")&&!d.getType().equals("express")) {
                        JOptionPane.showMessageDialog(null, "The valid delivery types are: standard, expedited, express");
                        break;
                    }
                    if(!d.getStatus().equals("in transit")&&!d.getStatus().equals("delivered")&&!d.getStatus().equals("just left")) {
                        JOptionPane.showMessageDialog(null, "The valid delivery statuses are: in transit, delivered, just left");
                        break;
                    }
                    if (bean.update(d) != null) {
                        JOptionPane.showMessageDialog(null,
                                "Delivery " + String.valueOf(d.getdID())
                                        + " was updated.");
                    }
                    break;
                case "Delete":
                    if (isEmptyFieldData()) {
                        JOptionPane.showMessageDialog(null,
                                "Please fill in missing info");
                        break;
                    }
                    if(!d.getType().equals("standard")&&!d.getType().equals("expedited")&&!d.getType().equals("express")) {
                        JOptionPane.showMessageDialog(null, "The valid delivery types are: standard, expedited, express");
                        break;
                    }
                    if(!d.getStatus().equals("in transit")&&!d.getStatus().equals("delivered")&&!d.getStatus().equals("just left")) {
                        JOptionPane.showMessageDialog(null, "The valid delivery statuses are: in transit, delivered, just left");
                        break;
                    }
                    //d = bean.getCurrent();
                    bean.delete();
                    JOptionPane.showMessageDialog(null,
                            "Delivery " + String.valueOf(d.getdID())
                                    + " was deleted.");
                    break;
                case "First":
                    setFieldData(bean.moveFirst());
                    break;
                case "Last":
                    setFieldData(bean.moveLast());
                    break;
                case "Next":
                    setFieldData(bean.moveNext());
                    break;
                case "Previous":
                    setFieldData(bean.movePrevious());
                    break;
                default:
                    JOptionPane.showMessageDialog(null,
                            "Invalid command");
            }
        }
    }
}
