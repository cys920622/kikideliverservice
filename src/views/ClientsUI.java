package views;

import controllerBeans.ClerkBean;
import controllerBeans.ClientsBean;
import entityClasses.Clients;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Created by chuchutrainn on 2016-03-26.
 */
public class ClientsUI extends JPanel{
    private JTextField clIDField = new JTextField(11);
    private JTextField fnameField = new JTextField(30);
    private JTextField lnameField = new JTextField(30);
    private JTextField PCField = new JTextField(7);
    private JTextField house_numField = new JTextField(11);
    private JTextField phone_numField = new JTextField(12);

    private JButton createButton = new JButton("New...");
    private JButton updateButton = new JButton("Update");
    private JButton deleteButton = new JButton("Delete");
    private JButton firstButton = new JButton("First");
    private JButton lastButton = new JButton("Last");
    private JButton nextButton = new JButton("Next");

    private ClientsBean bean = new ClientsBean();

    private int clID = new Random()
            .nextInt((Integer.MAX_VALUE)+1);

    public ClientsUI() {
        setBorder(new TitledBorder(
                new EtchedBorder(), "Clients details"));
        setLayout(new BorderLayout(5, 5));
        add(initFields(), BorderLayout.NORTH);
        add(initButtons(), BorderLayout.CENTER);

        clIDField.setText(String.valueOf(0));
        house_numField.setText(String.valueOf(0));
    }

    private JPanel initButtons() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 4, 4));
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
        panel.add(new JLabel("Client ID"), "align label");
        panel.add(clIDField, "wrap");
        panel.add(new JLabel("First name"), "align label");
        panel.add(fnameField, "wrap");
        panel.add(new JLabel("Last name"), "align label");
        panel.add(lnameField, "wrap");
        panel.add(new JLabel("Postal code"), "align label");
        panel.add(PCField, "wrap");
        panel.add(new JLabel("House number"), "align label");
        panel.add(house_numField, "wrap");
        panel.add(new JLabel("Phone number"), "align label");
        panel.add(phone_numField, "wrap");
        return panel;
    }

    public Clients getFieldData() {
        Clients c = new Clients();
        c.setClID(Integer.parseInt(clIDField.getText()));
        c.setFname(fnameField.getText());
        c.setLname(lnameField.getText());
        c.setPC(PCField.getText());
        c.setHouse_num(Integer.parseInt(house_numField.getText()));
        c.setPhone_num(phone_numField.getText());
        return c;
    }

    private void setFieldData(Clients c) {
        clIDField.setText(String.valueOf(c.getClID()));
        fnameField.setText(c.getFname());
        lnameField.setText(c.getLname());
        PCField.setText(c.getPC());
        house_numField.setText(String.valueOf(c.getHouse_num()));
        phone_numField.setText(c.getPhone_num());
    }

    private boolean isEmptyFieldData() {
        return (clIDField.getText().trim().isEmpty()
                && fnameField.getText().trim().isEmpty()
                && lnameField.getText().trim().isEmpty()
                && PCField.getText().trim().isEmpty()
                && house_numField.getText().trim().isEmpty()
                && phone_numField.getText().trim().isEmpty());
    }

    public void setclID(int clID) {
        this.clID = clID;
    }

    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Clients c = getFieldData();
            switch (e.getActionCommand()) {

                case "Save":
                    if (isEmptyFieldData()) {
                        JOptionPane.showMessageDialog(null,
                                "Cannot create empty client");
                    }
                    if (bean.create(c) != null) {
                        JOptionPane.showMessageDialog(null,
                                "New client created at " + String.valueOf(c.getClID()));
                        break;
                    }

                case "New...":
                    c.setClID(clID);
                    c.setFname("");
                    c.setLname("");
                    c.setPC("");
                    c.setHouse_num(0);
                    c.setPhone_num("");
                    setFieldData(c);
                    break;

                case "Update":
                    if (isEmptyFieldData()) {
                        JOptionPane.showMessageDialog(null,
                                "Can't update empty record");
                    }
                    if (bean.update(c) != null) {
                        JOptionPane.showMessageDialog(null,
                            "Address at " + String.valueOf(c.getHouse_num())
                                    + " was updated.");

                }
                break;

                case "Delete":
                    if (isEmptyFieldData()) {
                        JOptionPane.showMessageDialog(null,
                                "Can't delete empty record");
                    }
                    c = bean.getCurrent();
                    bean.delete();
                    JOptionPane.showMessageDialog(null,
                            "Client with ID of " + String.valueOf(c.getClID())
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