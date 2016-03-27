package views;

import controllerBeans.AddressBean;
import entityClasses.Address;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by danielchoi on 2016-03-25.
 */
public class AddressUI extends JPanel {
    private JTextField countryField = new JTextField(20);
    private JTextField provinceField = new JTextField(2);
    private JTextField cityField = new JTextField(20);
    private JTextField street_nameField = new JTextField(30);
    private JTextField house_numField = new JTextField(10);
    public JTextField PCField = new JTextField(7);

    private JButton createButton = new JButton("New...");
    private JButton updateButton = new JButton("Update");
    private JButton deleteButton = new JButton("Delete");
    private JButton firstButton = new JButton("First");
    private JButton lastButton = new JButton("Last");
    private JButton nextButton = new JButton("Next");

    private AddressBean bean = new AddressBean();

    public AddressUI() {
        setBorder(new TitledBorder(
                new EtchedBorder(), "Address details"));
        setLayout(new BorderLayout(5, 5));
        add(initFields(), BorderLayout.NORTH);
        add(initButtons(), BorderLayout.CENTER);
        house_numField.setText(String.valueOf(0));
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
        panel.add(new JLabel("Country"), "align label");
        panel.add(countryField, "wrap");
        panel.add(new JLabel("Province"), "align label");
        panel.add(provinceField, "wrap");
        panel.add(new JLabel("City"), "align label");
        panel.add(cityField, "wrap");
        panel.add(new JLabel("Street name"), "align label");
        panel.add(street_nameField, "wrap");
        panel.add(new JLabel("House number"), "align label");
        panel.add(house_numField, "wrap");
        panel.add(new JLabel("Postal code"), "align label");
        panel.add(PCField, "wrap");
        return panel;
    }

    private Address getFieldData() {
        Address a = new Address();
        a.setCountry(countryField.getText());
        a.setProvince(provinceField.getText());
        a.setCity(cityField.getText());
        a.setStreet_name(street_nameField.getText());
        a.setHouse_num(Integer.parseInt(house_numField.getText()));
        a.setPC(PCField.getText());
        return a;
    }

    private void setFieldData(Address a) {
        countryField.setText(a.getCountry());
        provinceField.setText(a.getProvince());
        cityField.setText(a.getCity());
        street_nameField.setText(a.getStreet_name());
        house_numField.setText(String.valueOf(a.getHouse_num()));
        PCField.setText(a.getPC());
    }

    private boolean isEmptyFieldData() {
        return (countryField.getText().trim().isEmpty()
        && provinceField.getText().trim().isEmpty()
        && cityField.getText().trim().isEmpty()
        && street_nameField.getText().trim().isEmpty()
        && house_numField.getText().trim().isEmpty()
        && PCField.getText().trim().isEmpty());
    }

    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Address a = getFieldData();
            switch (e.getActionCommand()) {
                case "Save":
                    if (isEmptyFieldData()) {
                        JOptionPane.showMessageDialog(null,
                                "Cannot create empty record");
                    }
                    if (bean.create(a) != null) {
                        JOptionPane.showMessageDialog(null,
                                "New address created at "+ String.valueOf(a.getHouse_num()));
                        createButton.setText("New...");
                        break;
                    }
                case "New...":
                    a.setCountry("");
                    a.setProvince("");
                    a.setCity("");
                    a.setStreet_name("");
                    a.setHouse_num(0);
                    a.setPC("");
                    setFieldData(a);
                    createButton.setText("Save");
                    break;
                case "Update":
                    if (isEmptyFieldData()) {
                        JOptionPane.showMessageDialog(null,
                                "Can't update empty record");
                    }
                    if (bean.update(a) != null) {
                        JOptionPane.showMessageDialog(null,
                                "Address at " + String.valueOf(a.getHouse_num())
                                        + " was updated.");
                    }
                    break;
                case "Delete":
                    if (isEmptyFieldData()) {
                        JOptionPane.showMessageDialog(null,
                                "Can't delete empty record");
                    }
                    a = bean.getCurrent();
                    bean.delete();
                    JOptionPane.showMessageDialog(null,
                            "Address at " + String.valueOf(a.getHouse_num())
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
                            "Invaild command");
            }
        }
    }

}
