package views;

import controllerBeans.DeliveryBean;
import entityClasses.Delivery;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by danielchoi on 2016-03-26.
 */
public class DeliveryUI extends JPanel {
    private JTextField dIDField = new JTextField(6);
    private JTextField typeField = new JTextField(20);
    private JTextField statusField = new JTextField(20);
    private JTextField sender_IDField = new JTextField(6);
    private JTextField receiver_IDField = new JTextField(6);

    private JButton createButton = new JButton("New...");
    private JButton updateButton = new JButton("Update");
    private JButton deleteButton = new JButton("Delete");
    private JButton firstButton = new JButton("First");
    private JButton lastButton = new JButton("Last");
    private JButton nextButton = new JButton("Next");

    private DeliveryBean bean = new DeliveryBean();

    public DeliveryUI() {
        setBorder(new TitledBorder(
                new EtchedBorder(), "Delivery details"));
        setLayout(new BorderLayout(5, 5));
        add(initFields(), BorderLayout.NORTH);
        add(initButtons(), BorderLayout.CENTER);
    }

    private JPanel initButtons() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));
        panel.add(createButton);
        createButton.addActionListener(new ButtonHandler());
        panel.add(updateButton);
        updateButton.addActionListener(new ButtonHandler());
        panel.add(deleteButton);
        deleteButton.addActionListener(new ButtonHandler());
        panel.add(firstButton);
        firstButton.addActionListener(new ButtonHandler());
        panel.add(lastButton);
        lastButton.addActionListener(new ButtonHandler());
        panel.add(nextButton);
        nextButton.addActionListener(new ButtonHandler());
        return panel;
    }

    private JPanel initFields() {
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout());
        panel.add(new JLabel("Deliver ID"), "align label");
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

    private Delivery getFieldData() {
        Delivery d = new Delivery();
        d.setdID(Integer.parseInt(dIDField.getText()));
        d.setType(typeField.getText());
        d.setStatus(statusField.getText());
        d.setSender_ID(Integer.parseInt(sender_IDField.getText()));
        d.setReceiver_ID(Integer.parseInt(receiver_IDField.getText()));
        return d;
    }

    private void setFieldData(Delivery d) {
        dIDField.setText(String.valueOf(d.getdID()));
        typeField.setText(d.getType());
        statusField.setText(d.getStatus());
        sender_IDField.setText(String.valueOf(d.getSender_ID()));
        receiver_IDField.setText(String.valueOf(d.getReceiver_ID()));
    }

    private boolean isEmptyFieldData() {
        return (dIDField.getText().trim().isEmpty()
                && typeField.getText().trim().isEmpty()
                && statusField.getText().trim().isEmpty()
                && sender_IDField.getText().trim().isEmpty()
                && receiver_IDField.getText().trim().isEmpty());
    }

    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Delivery d = getFieldData();
            switch (e.getActionCommand()) {
                case "Save":
                    if (isEmptyFieldData()) {
                        JOptionPane.showMessageDialog(null,
                                "Cannot create empty record");
                    }
                    if (bean.create(d) != null) {
                        JOptionPane.showMessageDialog(null,
                                "New delivery created :"+ String.valueOf(d.getdID()));
                        createButton.setText("New...");
                        break;
                    }
                case "New...":
                    d.setdID(0); //TODO: should use a counter here
                    d.setType(""); //TODO: should use a radio button
                    d.setStatus("");
                    d.setSender_ID(0);
                    d.setReceiver_ID(0);
                    setFieldData(d);
                    createButton.setText("Save");
                    break;
                case "Update":
                    if (isEmptyFieldData()) {
                        JOptionPane.showMessageDialog(null,
                                "Can't update empty record");
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
                                "Can't delete empty record");
                    }
                    d = bean.getCurrent();
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
                default:
                    JOptionPane.showMessageDialog(null,
                            "Invalid command");
            }
        }
    }
}
