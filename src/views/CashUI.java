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

/**
 * Created by stellafang. on 2016-03-27.
 */
public class CashUI extends JPanel{
    private JTextField amountField = new JTextField(12);
    private JTextField payIDField = new JTextField(3);
    private JTextField onDateField = new JTextField(10);
    private JTextField dIDField = new JTextField(6);

    private JButton createButton = new JButton("New...");
    private JButton updateButton = new JButton("Update");
    private JButton deleteButton = new JButton("Delete");
    private JButton firstButton = new JButton("First");
    private JButton lastButton = new JButton("Last");
    private JButton nextButton = new JButton("Next");

    private CashBean bean = new CashBean();

    public CashUI() {
        setBorder(new TitledBorder(
                new EtchedBorder(), "Parcel details"));
        setLayout(new BorderLayout(5, 5));
        add(initFields(), BorderLayout.NORTH);
        add(initButtons(), BorderLayout.CENTER);

        amountField.setText(String.valueOf(0.0));
        payIDField.setText(String.valueOf(0));
        dIDField.setText(String.valueOf(0));


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
        panel.add(new JLabel("Amount"), "align label");
        panel.add(amountField, "wrap");
        panel.add(new JLabel("Pay ID"), "align label");
        panel.add(payIDField, "wrap");
        panel.add(new JLabel("Paid on Date"), "align label");
        panel.add(onDateField, "wrap");
        panel.add(new JLabel("Delivery ID"), "align label");
        panel.add(dIDField, "wrap");
        return panel;
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

    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Cash c = getFieldData();
            switch (e.getActionCommand()) {
                case "Save":
                    if (isEmptyFieldData()) {
                        JOptionPane.showMessageDialog(null,
                                "Cannot create empty record");
                    }
                    if (bean.create(c) != null) {
                        JOptionPane.showMessageDialog(null,
                                "Cash transaction " + String.valueOf(c.getPayID()) +
                                        " for delivery" + String.valueOf(c.getdID())
                                        + " was successful");
                        createButton.setText("New...");
                        break;
                    }
                case "New...":
                    c.setdID(0);
                    c.setOnDate("");
                    c.setAmount(0);
                    c.setdID(0);
                    createButton.setText("Save");
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
                default:
                    JOptionPane.showMessageDialog(null,
                            "Invaild command");
            }
        }
    }
}
