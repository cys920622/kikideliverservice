package views;

import controllerBeans.CreditCardBean;
import entityClasses.CreditCard;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Random;

/**
 * Created by stellafang. on 2016-03-27.
 */
public class CreditCardUI extends JPanel {
    private JTextField amountField = new JTextField(12);
    private JTextField payIDField = new JTextField(3);
    private JTextField onDateField = new JTextField(10);
    private JTextField dIDField = new JTextField(6);
    private JTextField cc_numField = new JTextField(30);
    private JTextField csvField = new JTextField(3);
    private JTextField nameField = new JTextField(30);
    private JTextField expiryField = new JTextField(5);
    private JTextField typeField = new JTextField(255);

    private JButton createButton = new JButton("Start");
    private JButton updateButton = new JButton("Update");
    private JButton deleteButton = new JButton("Delete");
    private JButton firstButton = new JButton("First");
    private JButton lastButton = new JButton("Last");
    private JButton nextButton = new JButton("Next");
    private JButton previousButton = new JButton("Previous");
    private JButton clearButton = new JButton("Clear");

    private Boolean isClerkView = false;
    private final int payID = new Random().nextInt((999 - 0) + 1);

    private CreditCardBean bean = new CreditCardBean();
    private int dID = 0;
    private String onDate = onDateField.getText().trim();
    private String expiryDate = expiryField.getText().trim();

    public CreditCardUI(Boolean isClerkView) {
        setBorder(new TitledBorder(
                new EtchedBorder(), "Credit Card Info"));
        setLayout(new BorderLayout(5, 5));
        this.isClerkView = isClerkView;
        add(initFields(), BorderLayout.NORTH);
        add(initButtons(), BorderLayout.CENTER);

        amountField.setText(String.valueOf(0));
        payIDField.setText(String.valueOf(0));
        dIDField.setText(String.valueOf(0));
        csvField.setText(String.valueOf(0));

    }

    private JPanel initButtons() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));
        if (isClerkView) {
            panel.add(createButton);
            createButton.addActionListener(new ButtonHandler());
            panel.add(clearButton);
            clearButton.addActionListener(new ButtonHandler());
        }
        else {
            panel.add(createButton);
            createButton.addActionListener(new ButtonHandler());
            panel.add(clearButton);
            clearButton.addActionListener(new ButtonHandler());
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
            panel.add(previousButton);
            previousButton.addActionListener(new ButtonHandler());
        }

        return panel;
    }

    private JPanel initFields() {
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout());
        panel.add(new JLabel("Pay ID"), "align label");
        panel.add(payIDField, "wrap");
        panel.add(new JLabel("Amount"), "align label");
        panel.add(amountField, "wrap");
        panel.add(new JLabel("Paid on Date"), "align label");
        panel.add(onDateField, "wrap");
        panel.add(new JLabel("Delivery ID"), "align label");
        panel.add(dIDField, "wrap");
        panel.add(new JLabel("Credit Card Number"), "align label");
        panel.add(cc_numField, "wrap");
        panel.add(new JLabel("CSV"), "align label");
        panel.add(csvField, "wrap");
        panel.add(new JLabel("Name"), "align label");
        panel.add(nameField, "wrap");
        panel.add(new JLabel("Expiry Date"), "align label");
        panel.add(expiryField, "wrap");
        panel.add(new JLabel("Type"), "align label");
        panel.add(typeField, "wrap");
        return panel;
    }

    private Boolean checkFieldData() {
        try {
            if (payIDField.getText().length()>3) {
                JOptionPane.showMessageDialog(null, "The pay ID can only be max 3 numbers long");
                return false;
            }
            Integer.parseInt(payIDField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "The delivery ID can only be a number");
            return false;
        }

        try {
            Float.parseFloat(amountField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "The amount needs to be a decimal number");
            return false;
        }

        try {
            Float.parseFloat(dIDField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "The delivery ID needs to be a decimal number");
            return false;
        }

        try {
            Integer.parseInt(dIDField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "The delivery ID needs to be a number");
            return false;
        }

        try {
            Integer.parseInt(csvField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "The CSV needs to be a number");
            return false;
        }

        if (onDateField.getText().length()>10) {
            JOptionPane.showMessageDialog(null, "The Date has to be can only be max 10 characters long");
            return false;
        }

        if (cc_numField.getText().length()>30) {
            JOptionPane.showMessageDialog(null, "The credit card number can only be max 30 characters long");
            return false;
        }

        if (nameField.getText().length()>30) {
            JOptionPane.showMessageDialog(null, "The name can only be max 30 characters long");
            return false;
        }
        if (expiryField.getText().length()>5) {
            JOptionPane.showMessageDialog(null, "the expiry date needs is 5 characters long in the format mm/yy");
            return false;
        }

        if (typeField.getText().length()>255) {
            JOptionPane.showMessageDialog(null, "The credit card type can only be max 255 characters long");
            return false;
        }
        //should check credit card types??
        return true;
    }

    private CreditCard getFieldData() {
        if(checkFieldData()) {
            CreditCard cc = new CreditCard();
            cc.setPayID(Integer.parseInt(payIDField.getText()));
            cc.setAmount(Float.parseFloat(amountField.getText()));
            cc.setOnDate(onDateField.getText());
            cc.setdID(Integer.parseInt(dIDField.getText()));
            cc.setCredit_card_num(cc_numField.getText());
            cc.setCSV(Integer.parseInt(csvField.getText()));
            cc.setName(nameField.getText());
            cc.setExpiry_date(expiryField.getText());
            cc.setType(typeField.getText());
            return cc;
        }
        return null;
    }

    private void setFieldData(CreditCard cc) {
        payIDField.setText(String.valueOf(cc.getPayID()));
        amountField.setText(String.valueOf(cc.getAmount()));
        onDateField.setText(cc.getOnDate());
        dIDField.setText(String.valueOf(cc.getdID()));
        cc_numField.setText(cc.getCredit_card_num());
        csvField.setText(String.valueOf(cc.getCSV()));
        nameField.setText(cc.getName());
        expiryField.setText(cc.getExpiry_date());
        typeField.setText(cc.getType());
    }

    private boolean isEmptyFieldData() {
        return (payIDField.getText().trim().equals("0")
                || amountField.getText().trim().equals("0.0")
                || onDateField.getText().trim().isEmpty()
                || dIDField.getText().trim().equals("0")
                || cc_numField.getText().trim().isEmpty()
                || csvField.getText().trim().equals("0")
                || nameField.getText().trim().isEmpty()
                || expiryField.getText().trim().isEmpty()
                || typeField.getText().trim().isEmpty());

    }

    public void setdID(int dID) {
        this.dID = dID;
    }

    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            CreditCard cc = getFieldData();
            switch (e.getActionCommand()) {
                case "Submit":
                    if (isEmptyFieldData()) {
                        JOptionPane.showMessageDialog(null,
                                "Please fill in remaining fields");
                        break;
                    }
                    if (cc.getOnDate().length()<10) {
                        JOptionPane.showMessageDialog(null, "The Date has to be exactly 10 characters long");
                        break;
                    }
                    if (cc.getExpiry_date().length()<5) {
                        JOptionPane.showMessageDialog(null, "The Date has to be exactly 10 characters long");
                        break;
                    }
                    if (onDate.substring(3, 4)!= "/" &&
                            onDate.substring(6,7)!= "/"){
                        JOptionPane.showMessageDialog(null, "Please write transaction date in the format: dd/mm/yyyy");
                        break;
                    }
                    if (expiryDate.substring(3, 4)!= "/"){
                        JOptionPane.showMessageDialog(null, "Please write the expiry date in the format: dd/yy");
                        break;
                    }
                    if (bean.create(cc) != null) {
                        JOptionPane.showMessageDialog(null,
                                "Credit Card transaction " + String.valueOf(cc.getPayID()) +
                                        " for delivery" + String.valueOf(cc.getdID())
                                        + " was successful");
                        createButton.setText("Start");
                        break;
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Please check if you have entered a unique pay ID, and an existing delivery ID");
                        break;
                    }
                case "Start":
                    cc.setPayID(payID);
                    cc.setOnDate("");
                    cc.setAmount(0);
                    cc.setdID(dID);
                    cc.setCredit_card_num("");
                    cc.setCSV(0);
                    cc.setName("");
                    cc.setExpiry_date("");
                    cc.setType("");
                    setFieldData(cc);
                    createButton.setText("Submit");
                    break;
                case "Clear":
                    cc.setPayID(payID);
                    cc.setOnDate("");
                    cc.setAmount(0);
                    cc.setdID(dID);
                    cc.setCredit_card_num("");
                    cc.setCSV(0);
                    cc.setName("");
                    cc.setExpiry_date("");
                    cc.setType("");
                    setFieldData(cc);
                    break;
                case "Update":
                    if (isEmptyFieldData()) {
                        JOptionPane.showMessageDialog(null,
                                "Please fill in missing info");
                        break;
                    }
                    if (cc.getOnDate().length()<10) {
                        JOptionPane.showMessageDialog(null, "The Date has to be exactly 10 characters long");
                        break;
                    }
                    if (cc.getExpiry_date().length()<5) {
                        JOptionPane.showMessageDialog(null, "The Date has to be exactly 10 characters long");
                        break;
                    }
                    if (!cc.getOnDate().trim().substring(2, 3).equals("/") ||
                            !cc.getOnDate().trim().substring(5, 6).equals("/")){
                        JOptionPane.showMessageDialog(null, "Please write the transaction date in the format: dd/mm/yyyy");
                        break;
                    }
                    if (!cc.getExpiry_date().substring(2, 3).equals("/")){
                        JOptionPane.showMessageDialog(null, "Please write the expiry date in the format: dd/yy");
                        break;
                    }
                    if (bean.update(cc) != null) {
                        JOptionPane.showMessageDialog(null,
                                "Credit Cash transaction " + String.valueOf(cc.getPayID()) +
                                        " for delivery" + String.valueOf(cc.getdID())
                                        + " was updated");
                    }
                    break;
                case "Delete":
                    if (isEmptyFieldData()) {
                        JOptionPane.showMessageDialog(null,
                                "Please fill in missing info");
                        break;
                    }
                    if (cc.getOnDate().length()<10) {
                        JOptionPane.showMessageDialog(null, "The Date has to be exactly 10 characters long");
                        break;
                    }
                    if (cc.getExpiry_date().length()<5) {
                        JOptionPane.showMessageDialog(null, "The Date has to be exactly 10 characters long");
                        break;
                    }
                    if (!cc.getOnDate().trim().substring(2, 3).equals("/") ||
                            !cc.getOnDate().trim().substring(5, 6).equals("/")){
                        JOptionPane.showMessageDialog(null, "Please write the transaction date in the format: dd/mm/yyyy");
                        break;
                    }
                    if (!cc.getExpiry_date().substring(2, 3).equals("/")){
                        JOptionPane.showMessageDialog(null, "Please write the expiry date in the format: dd/yy");
                        break;
                    }
                    cc = bean.getCurrent();
                    bean.delete();
                    JOptionPane.showMessageDialog(null,
                            "Cash transaction " + String.valueOf(cc.getPayID()) +
                                    " for delivery" + String.valueOf(cc.getdID())
                                    + " was deleted");
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
