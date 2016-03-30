package views;

import controllerBeans.ClientAddressBean;
import controllerBeans.ClientsBean;
import entityClasses.ClientAddress;
import entityClasses.Clients;
import javafx.scene.layout.GridPane;
import net.miginfocom.swing.MigLayout;
import sun.awt.VariableGridLayout;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Created by stellafang. on 2016-03-29.
 */
public class ClientAddressUI extends JPanel{
    private JTextField clIDField = new JTextField(11);
    private JTextField fnameField = new JTextField(30);
    private JTextField lnameField = new JTextField(30);
    private JTextField PCField = new JTextField(7);
    private JTextField house_numField = new JTextField(11);
    private JTextField phone_numField = new JTextField(12);
    private JTextField countryField = new JTextField(20);
    private JTextField provinceField = new JTextField(2);
    private JTextField cityField = new JTextField(20);
    private JTextField street_nameField = new JTextField(30);

    private JButton createButton = new JButton("Create");
    private JButton updateButton = new JButton("Update");
    private JButton clearButton = new JButton("Clear");
    private JButton deleteButton = new JButton("Delete");
    private JButton firstButton = new JButton("First");
    private JButton lastButton = new JButton("Last");
    private JButton nextButton = new JButton("Next");
    private JButton previousButton = new JButton("Previous");
    private JButton nextStepButton = new JButton("Next Step");

    private int tabNumber;
    private String tabName;

    private ClientAddressBean bean = new ClientAddressBean();

    public ClientAddressUI(int tabNumber) {
        this.tabNumber = tabNumber;

        if (tabNumber == 1) {
            tabName = "Enter Sender Info";
        }
        else if (tabNumber == 2) {
            tabName = "Enter Receiver Info";
        }

        setBorder(new TitledBorder(
                new EtchedBorder(), tabName));
        setLayout(new BorderLayout(1, 1));
        add(initFields(), BorderLayout.NORTH);
        add(initButtons(), BorderLayout.CENTER);
        house_numField.setText(String.valueOf(0));
        clIDField.setText(String.valueOf(0));
        house_numField.setText(String.valueOf(0));
    }

    private JPanel initButtons() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 4, 4));
        if (tabNumber == 1) {
            panel.add(createButton);
            createButton.addActionListener(new ButtonHandler());
            panel.add(clearButton);
            clearButton.addActionListener(new ButtonHandler());
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
            panel.add(nextStepButton);
            nextStepButton.addActionListener(new ButtonHandler());
        }
        if(tabNumber == 2) {
            panel.add(updateButton);
            updateButton.addActionListener(new ButtonHandler());
            panel.add(clearButton);
            clearButton.addActionListener(new ButtonHandler());
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
        panel.add(new JLabel("Country"), "align label");
        panel.add(countryField, "wrap");
        panel.add(new JLabel("Province"), "align label");
        panel.add(provinceField, "wrap");
        panel.add(new JLabel("City"), "align label");
        panel.add(cityField, "wrap");
        panel.add(new JLabel("Street name"), "align label");
        panel.add(street_nameField, "wrap");
        return panel;
    }

    public ClientAddress getFieldData() {
        ClientAddress ca = new ClientAddress();
        ca.setClID(Integer.parseInt(clIDField.getText()));
        ca.setFname(fnameField.getText());
        ca.setLname(lnameField.getText());
        ca.setPC(PCField.getText());
        ca.setHouse_num(Integer.parseInt(house_numField.getText()));
        ca.setPhone_num(phone_numField.getText());
        ca.setCountry(countryField.getText());
        ca.setProvince(provinceField.getText());
        ca.setCity(cityField.getText());
        ca.setStreet_name(street_nameField.getText());
        return ca;
    }

    private void setFieldData(ClientAddress ca) {
        clIDField.setText(String.valueOf(ca.getClID()));
        fnameField.setText(ca.getFname());
        lnameField.setText(ca.getLname());
        PCField.setText(ca.getPC());
        house_numField.setText(String.valueOf(ca.getHouse_num()));
        phone_numField.setText(ca.getPhone_num());
        countryField.setText(ca.getCountry());
        provinceField.setText(ca.getProvince());
        cityField.setText(ca.getCity());
        street_nameField.setText(ca.getStreet_name());
    }

    private boolean isEmptyFieldData() {
        return (clIDField.getText().trim().isEmpty()
                && fnameField.getText().trim().isEmpty()
                && lnameField.getText().trim().isEmpty()
                && PCField.getText().trim().isEmpty()
                && house_numField.getText().trim().isEmpty()
                && phone_numField.getText().trim().isEmpty()
                && countryField.getText().trim().isEmpty()
                && provinceField.getText().trim().isEmpty()
                && cityField.getText().trim().isEmpty()
                && street_nameField.getText().trim().isEmpty());
    }

    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ClientAddress ca = getFieldData();
            switch (e.getActionCommand()) {

                case "Save":
                    if (isEmptyFieldData()) {
                        JOptionPane.showMessageDialog(null,
                                "Cannot create empty client");
                    }
                    if (bean.create(ca) != null) {
                        JOptionPane.showMessageDialog(null,
                                "New Client: " + ca.getFname() + " " + ca.getLname() +
                                        " with ClientID " + String.valueOf(ca.getClID())
                                        + " was created successfully.");
                        break;
                    }

                case "Create":
                    ca.setClID(new Random()
                            .nextInt(Integer.MAX_VALUE) + 1);
                    ca.setFname("");
                    ca.setLname("");
                    ca.setPC("");
                    ca.setHouse_num(0);
                    ca.setPhone_num("");
                    ca.setCountry("");
                    ca.setProvince("");
                    ca.setCity("");
                    ca.setStreet_name("");
                    setFieldData(ca);
                    createButton.setText("Save");
                    break;

                case "Update":
                    if (isEmptyFieldData()) {
                        JOptionPane.showMessageDialog(null,
                                "Can't update empty record");
                    }
                    if (bean.update(ca) != null) {
                        JOptionPane.showMessageDialog(null,
                                "Client: " + ca.getFname() + " " + ca.getLname() +
                                        " with ClientID " + String.valueOf(ca.getClID())
                                        + " was edited successfully.");

                    }
                    break;

                case "Delete":
                    JOptionPane.showMessageDialog(null,
                            "Delete all Deliveries associated with " +
                                    "Client in order " +
                                    "to delete Client.");
//                    if (isEmptyFieldData()) {
//                        JOptionPane.showMessageDialog(null,
//                                "Can't delete empty record");
//                    }
//                    //ca = bean.getCurrent();
//                    bean.delete(ca);
//                    JOptionPane.showMessageDialog(null,
//                            "Client: " + ca.getFname() + " " + ca.getLname() +
//                                    " with ClientID " + String.valueOf(ca.getClID())
//                                    + " was deleted successfully.");
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
                case "Next Step":
                    if(ca.getClID()==0) {
                        JOptionPane.showMessageDialog(null,
                                "Please at least enter a Client ID");
                    }
                    else {
                        setBorder(new TitledBorder(
                                new EtchedBorder(), "Create new Delivery "));
                        JFrame tab1_1Frame = new JFrame();
                        tab1_1Frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        tab1_1Frame.setSize(1500, 780);
                        JComponent tab1_1 = new JPanel();

                        JPanel panel1 = new JPanel();

                        ClerkUI showCenters = new ClerkUI("SELECT * from center", "Valid Centers");
                        tab1_1.add(panel1.add(showCenters));

                        DeliveryUI deliveryui = new DeliveryUI();
                        deliveryui.setPreferredSize(new Dimension(620,300));
                        tab1_1.add(panel1.add(deliveryui));
                        panel1.setAlignmentY(TOP_ALIGNMENT);



                        JPanel panel2 = new JPanel();

                        ClientAddressUI clientAddressUI = new ClientAddressUI(2);
                        clientAddressUI.setPreferredSize(new Dimension(620, 415));
                        tab1_1.add(panel2.add(clientAddressUI));

                        ParcelUI parcelUI = new ParcelUI();
                        parcelUI.setPreferredSize(new Dimension(400, 400));
                        tab1_1.add(panel2.add(parcelUI));

                        panel2.setAlignmentY(BOTTOM_ALIGNMENT);

                        tab1_1Frame.add(tab1_1);
                        tab1_1Frame.setVisible(true);








                    }
                    break;
                default:
                    JOptionPane.showMessageDialog(null,
                            "Invalid command");
            }
        }
    }
}
