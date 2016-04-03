package views;

import controllerBeans.CashBean;
import controllerBeans.ParcelBean;
import entityClasses.Cash;
import entityClasses.Parcel;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.DateFormat;
import java.util.Random;

/**
 * Created by stellafang. on 2016-03-27.
 */
public class CashUI extends JPanel{
    private JTextField amountField = new JTextField(12);
    private JTextField payIDField = new JTextField(3);
    private JTextField onDateField = new JTextField(10);
    private JTextField dIDField = new JTextField(6);

    private JButton createButton = new JButton("Start");
    private JButton updateButton = new JButton("Update");
    private JButton deleteButton = new JButton("Delete");
    private JButton firstButton = new JButton("First");
    private JButton lastButton = new JButton("Last");
    private JButton nextButton = new JButton("Next");
    private JButton previousButton = new JButton("Previous");

    private CashBean bean = new CashBean();

    private Boolean isClerkView = false;

    private int dID = 0;

    public CashUI(Boolean isClerkView) {
        setBorder(new TitledBorder(
                new EtchedBorder(), "Cash info"));
        setLayout(new BorderLayout(5, 5));
        this.isClerkView = isClerkView;
        add(initFields(), BorderLayout.NORTH);
        add(initButtons(), BorderLayout.CENTER);

        amountField.setText(String.valueOf(0.0));
        payIDField.setText(String.valueOf(0));
        dIDField.setText(String.valueOf(0));

    }


    private JPanel initButtons() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));

        if (!isClerkView) {
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
            panel.add(previousButton);
            previousButton.addActionListener(new ButtonHandler());

        }
        if (isClerkView){
            panel.add(createButton);
            createButton.addActionListener(new ButtonHandler());
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
        return panel;
    }

    private Boolean checkFieldData() {
        try {
            Integer.parseInt(payIDField.getText());
            if (payIDField.getText().length()>3) {
                JOptionPane.showMessageDialog(null, "The pay ID can only be max 3 numbers long");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "The delivery ID can only be a number");
            return false;
        }

        try {
            Integer.parseInt(amountField.getText());
            if (amountField.getText().length()>6) {
                JOptionPane.showMessageDialog(null, "The delivery ID can only be max 6 numbers long");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "The delivery ID can only be a number");
            return false;
        }




        return true;
    }

    private Cash getFieldData() {
        Cash c = new Cash();
        c.setPayID(Integer.parseInt(payIDField.getText()));
        c.setAmount(Float.parseFloat(amountField.getText()));
        c.setOnDate(onDateField.getText());
        c.setdID(Integer.parseInt(dIDField.getText()));
        return c;
    }

    private void setFieldData(Cash c) {
        payIDField.setText(String.valueOf(c.getPayID()));
        amountField.setText(String.valueOf(c.getAmount()));
        onDateField.setText(c.getOnDate());
        dIDField.setText(String.valueOf(c.getdID()));
    }

    private boolean isEmptyFieldData() {
        return (payIDField.getText().trim().isEmpty()
                && amountField.getText().trim().isEmpty()
                && onDateField.getText().trim().isEmpty()
                && dIDField.getText().trim().isEmpty());
    }

    public void setdID(int dID) {
        this.dID = dID;
    }


    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Cash c = getFieldData();
            System.out.println(getFieldData());
            switch (e.getActionCommand()) {
                case "Submit":
                    if (isEmptyFieldData()) {
                        JOptionPane.showMessageDialog(null,
                                "Cannot create empty record");
                    }
                    if (bean.create(c) != null) {
                        JOptionPane.showMessageDialog(null,
                                "Cash transaction " + String.valueOf(c.getPayID()) +
                                        " for delivery" + String.valueOf(c.getdID())
                                        + " was successful");
                        createButton.setText("Start");
                        break;
                    }
                case "Start":
                    c.setPayID(new Random().nextInt(999));
                    c.setOnDate("");
                    c.setAmount(0);
                    c.setdID(dID);
                    setFieldData(c);
                    createButton.setText("Submit");
                    break;

                case "Update":
                    if (isEmptyFieldData()) {
                        JOptionPane.showMessageDialog(null,
                                "Can't update empty record");
                    }
                    if (bean.update(c) != null) {
                        JOptionPane.showMessageDialog(null,
                                "Cash transaction " + String.valueOf(c.getPayID()) +
                                        " for delivery" + String.valueOf(c.getdID())
                                        + " was updated");
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
                            "Cash transaction " + String.valueOf(c.getPayID()) +
                                    " for delivery" + String.valueOf(c.getdID())
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
                    break;
                default:
                    JOptionPane.showMessageDialog(null,
                            "Invaild command");
            }
        }
    }
}
