package views;

import controllerBeans.CashBean;
import controllerBeans.CreditCardBean;
import controllerBeans.DeliveryParcelBean;
import entityClasses.Cash;
import entityClasses.CreditCard;
import entityClasses.DeliveryParcel;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by stellafang. on 2016-04-01.
 */
public class CashCreditUI extends JPanel{

    private JTextField filterdIDCashField = new JTextField(6);
    private JTextField filterpayIDCashField = new JTextField(3);
    private JTextField filterdIDCreditField = new JTextField(6);
    private JTextField filterpayIDCreditField = new JTextField(3);

    private JButton filterdIDCashButton = new JButton("Filter cash transactions by delivery ID:");
    private JButton filterpayIDCashButton = new JButton("Filter cash transactions by payID:");
    private JButton filterdIDCreditButton = new JButton("Filter credit card transactions by delivery ID:");
    private JButton filterpayIDCreditButton = new JButton("Filter credit card transactions by payID:");
    private JButton avgCashAmountButton = new JButton("Calculate average cash amount");
    private JButton avgCreditAmountButton = new JButton("Calculate average credit card amount");
    private JButton maxCashAmountButton = new JButton("Calculate maximum cash amount");
    private JButton maxCreditAmountButton = new JButton("Calculate maximum credit card amount");
    private JButton minCashAmountButton = new JButton("Calculate minimum cash amount");
    private JButton minCreditAmountButton = new JButton("Calculate minimum credit card amount");


    private CashBean cbean = new CashBean();
    private CreditCardBean ccbean = new CreditCardBean();

    public CashCreditUI() {
        setBorder(new TitledBorder(
                new EtchedBorder(), "Transaction Info"));
        setLayout(new BorderLayout(5, 10));
        add(initFieldsAndButtons(), BorderLayout.CENTER);

        filterdIDCashField.setText(String.valueOf(0));
        filterpayIDCashField.setText(String.valueOf(0));
        filterdIDCreditField.setText(String.valueOf(0));
        filterpayIDCreditField.setText(String.valueOf(0));

    }

    private JPanel initFieldsAndButtons() {
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout());
        filterdIDCashButton.addActionListener(new ButtonHandler());
        panel.add(filterdIDCashButton);
        panel.add(filterdIDCashField, "wrap");
        filterpayIDCashButton.addActionListener(new ButtonHandler());
        panel.add(filterpayIDCashButton);
        panel.add(filterpayIDCashField, "wrap");
        filterdIDCreditButton.addActionListener(new ButtonHandler());
        panel.add(filterdIDCreditButton);
        panel.add(filterdIDCreditField, "wrap");
        filterpayIDCreditButton.addActionListener(new ButtonHandler());
        panel.add(filterpayIDCreditButton);
        panel.add(filterpayIDCreditField, "wrap");
        avgCashAmountButton.addActionListener(new ButtonHandler());
        panel.add(avgCashAmountButton, "wrap");
        avgCreditAmountButton.addActionListener(new ButtonHandler());
        panel.add(avgCreditAmountButton, "wrap");
        maxCashAmountButton.addActionListener(new ButtonHandler());
        panel.add(maxCashAmountButton, "wrap");
        minCashAmountButton.addActionListener(new ButtonHandler());
        panel.add(minCashAmountButton, "wrap");
        maxCreditAmountButton.addActionListener(new ButtonHandler());
        panel.add(maxCreditAmountButton, "wrap");
        minCreditAmountButton.addActionListener(new ButtonHandler());
        panel.add(minCreditAmountButton, "wrap");
        return panel;
    }

    public Boolean checkCashFieldData() {
        try {
            Integer.parseInt(filterpayIDCashField.getText());
            if (filterpayIDCashField.getText().length()>3) {
                JOptionPane.showMessageDialog(null, "The pay ID can only be max 3 numbers long");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "The pay ID can only be a number");
            return false;
        }

        try {
            Integer.parseInt(filterdIDCashField.getText());
            if (filterdIDCashField.getText().length() > 6) {
                JOptionPane.showMessageDialog(null, "The delivery ID can only be max 6 numbers long");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "The delivery ID can only be a number");
            return false;
        }

        return true;
    }


    private Cash getCashFieldData() {
        if(checkCashFieldData()) {
            Cash c = new Cash();
            c.setdID(Integer.parseInt(filterdIDCashField.getText()));
            c.setPayID(Integer.parseInt(filterpayIDCashField.getText()));
            return c;
        }return null;
    }

    public Boolean checkCreditFieldData() {
        try {
            Integer.parseInt(filterpayIDCreditField.getText());
            if (filterpayIDCreditField.getText().length()>3) {
                JOptionPane.showMessageDialog(null, "The pay ID can only be max 3 numbers long");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "The pay ID can only be a number");
            return false;
        }

        try {
            Integer.parseInt(filterdIDCreditField.getText());
            if (filterdIDCreditField.getText().length() > 6) {
                JOptionPane.showMessageDialog(null, "The delivery ID can only be max 6 numbers long");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "The delivery ID can only be a number");
            return false;
        }

        return true;
    }

    private CreditCard getCreditFieldData() {
        if (checkCreditFieldData()) {
            CreditCard c = new CreditCard();
            c.setdID(Integer.parseInt(filterdIDCreditField.getText()));
            c.setPayID(Integer.parseInt(filterpayIDCreditField.getText()));
            //c.setType(creditTypeField.getText());
            //c.setOnDate(dateTypeField.getText());
            return c;
        }
        return null;
    }

    public JPanel initCashTable(String sql, String tableName) {
        JPanel panel = new JPanel();
        JTable table = cbean.makeTable(sql);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setAutoCreateRowSorter(true);
        int tableWidth = (45 * table.getColumnCount());
        if (tableWidth < 400) {
            tableWidth = 400;
        }
        if (tableWidth > 570) {
            tableWidth = 570;
        }
        panel.setBorder(new TitledBorder(new EtchedBorder(), tableName));
        panel.setLayout(new BorderLayout(1, 1));
        panel.setPreferredSize(new Dimension(tableWidth, 250));
        panel.add(scrollPane);

        return panel;
    }

    public JPanel initCreditTable(String sql, String tableName) {
        JPanel panel = new JPanel();
        JTable table = ccbean.makeTable(sql);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setAutoCreateRowSorter(true);
        int tableWidth = (45 * table.getColumnCount());
        if (tableWidth < 400) {
            tableWidth = 400;
        }
        if (tableWidth > 570) {
            tableWidth = 570;
        }
        panel.setBorder(new TitledBorder(new EtchedBorder(), tableName));
        panel.setLayout(new BorderLayout(1, 1));
        panel.setPreferredSize(new Dimension(tableWidth, 250));
        panel.add(scrollPane);

        return panel;
    }

    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(900, 400);
            Cash c = getCashFieldData();
            CreditCard cc = getCreditFieldData();

            switch (e.getActionCommand()) {
                case "Filter cash transactions by delivery ID:":
                    if (filterdIDCashField.getText().trim().equals("0")){
                        JOptionPane.showMessageDialog(null, "Please enter a valid delivery ID");
                        break;
                    }
                    frame.add(initCashTable("SELECT * " +
                                    "FROM cash " +
                                    "LEFT JOIN delivery " +
                                    "ON delivery.dID=cash.dID " +
                                    "WHERE cash.dID='" + c.getdID() + "'",
                            "Cash transaction for Delivery with dID: " + c.getdID()));
                    frame.setVisible(true);
                    break;
                case "Filter cash transactions by payID:":
                    if (filterpayIDCashField.getText().trim().equals("0")){
                        JOptionPane.showMessageDialog(null, "Please enter a valid pay ID");
                        break;
                    }
                    frame.add(initCashTable("SELECT * " +
                                    "FROM cash " +
                                    "WHERE cash.payID='" + c.getPayID() + "'",
                            "Cash transaction of pay ID: " + c.getPayID()));
                    frame.setVisible(true);
                    break;
                case "Filter credit card transactions by delivery ID:":
                    if (filterdIDCreditField.getText().trim().equals("0")){
                        JOptionPane.showMessageDialog(null, "Please enter a valid delivery ID");
                        break;
                    }
                    frame.add(initCreditTable("SELECT * " +
                                    "FROM credit_card " +
                                    "LEFT JOIN delivery " +
                                    "ON delivery.dID=credit_card.dID " +
                                    "WHERE credit_card.dID='" + cc.getdID() + "'",
                            "Credit Card transaction for Delivery with dID: " + cc.getdID()));
                    frame.setVisible(true);
                    break;
                case "Filter credit card transactions by payID:":
                    if (filterpayIDCreditField.getText().trim().equals("0")){
                        JOptionPane.showMessageDialog(null, "Please enter a valid pay ID");
                        break;
                    }
                    frame.add(initCreditTable("SELECT * " +
                                    "FROM credit_card " +
                                    "WHERE credit_card.payID='" + cc.getPayID() + "'",
                            "Credit Card transaction of payID: " + cc.getPayID()));
                    frame.setVisible(true);
                    break;
                case "Calculate average cash amount":
                    JOptionPane.showMessageDialog(null,
                            cbean.Calculate("SELECT avg(amount) " +
                                    "FROM cash "));
                    frame.setVisible(true);
                    break;
                case "Calculate average credit card amount":
                    JOptionPane.showMessageDialog(null,
                            ccbean.Calculate("SELECT avg(amount) " +
                                    "FROM credit_card"));
                    break;
                case "Calculate maximum cash amount":
                    JOptionPane.showMessageDialog(null,
                            cbean.Calculate("SELECT MAX(amount) " +
                                    "FROM cash "));
                    break;
                case "Calculate maximum credit card amount":
                    JOptionPane.showMessageDialog(null,
                            ccbean.Calculate("SELECT MAX(amount) " +
                                    "FROM credit_card "));
                    break;
                case "Calculate minimum cash amount":
                    JOptionPane.showMessageDialog(null,
                            cbean.Calculate("SELECT MIN(amount) " +
                                    "FROM cash "));
                    break;
                case "Calculate minimum credit card amount":
                    JOptionPane.showMessageDialog(null,
                            ccbean.Calculate("SELECT MIN(amount) " +
                                    "FROM credit_card "));
                    break;
                default:
                    JOptionPane.showMessageDialog(null,
                            "Invaild command");
            }
        }
    }


}
//SELECT passengers.passport_no, COUNT(flight_no) AS 'number_of_flights'
//        FROM passengers LEFT JOIN reserves ON passengers.passport_no =    reserves.passport_no
//        GROUP BY passengers.passport_no
//        HAVING COUNT(​*) = (SELECT COUNT(*​) FROM flights);