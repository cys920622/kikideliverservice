package views;

import controllerBeans.DeliveryParcelBean;
import entityClasses.DeliveryParcel;
import entityClasses.Parcel;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by stellafang. on 2016-03-31.
 */
public class DeliveryParcelUI extends JPanel{
    private JTextField filterdIDField = new JTextField(6);
    private JTextField filtertypeField = new JTextField(20);
    private JTextField filterstatusField = new JTextField(20);
    private JTextField filtersender_IDField = new JTextField(6);
    private JTextField filterreceiver_IDField = new JTextField(6);

    private JTextField totalpIDField = new JTextField(6);
    private JTextField totaltypeField = new JTextField(20);
    private JTextField totalstatusField = new JTextField(20);

    private JButton filterdIDButton = new JButton("Filter deliveries by delivery ID:");
    private JButton filtertypeButton = new JButton("Filter deliveries by type:");
    private JButton filterstatusButton = new JButton("Filter deliveries by status:");
    private JButton filtersenderButton = new JButton("Filter deliveries by sender ID:");
    private JButton filterreceiverButton = new JButton("Filter deliveries by receiver ID:");
    private JButton totaldIDButton = new JButton("Calculate total deliveries");
    private JButton totalpIDButton = new JButton("Calculate total parcels of delivery ID:");
    private JButton totaltypeButton = new JButton("Calculate total deliveries of type:");
    private JButton totalstatusButton = new JButton("Calculate total deliveries with status:");

    private JRadioButton maxButton = new JRadioButton("Maximum");
    private JRadioButton minButton = new JRadioButton("Minimum");
    private ButtonGroup radioGroup;

    private DeliveryParcelBean bean = new DeliveryParcelBean();

    public DeliveryParcelUI() {
        setBorder(new TitledBorder(
                new EtchedBorder(), "Delivery details"));
        setLayout(new BorderLayout(1, 1));
        add(initFieldsAndButtons(), BorderLayout.CENTER);

        filterdIDField.setText(String.valueOf(0));
        filtersender_IDField.setText(String.valueOf(0));
        filterreceiver_IDField.setText(String.valueOf(0));
        totalpIDField.setText(String.valueOf(0));
    }

    private JPanel initFieldsAndButtons() {
        JPanel panel = new JPanel();
        radioGroup = new ButtonGroup();
        panel.setLayout(new MigLayout());
        panel.add(totaldIDButton, "wrap");
        totaldIDButton.addActionListener(new ButtonHandler());
        panel.add(filterdIDButton);
        filterdIDButton.addActionListener(new ButtonHandler());
        panel.add(filterdIDField, "wrap");
        panel.add(filtertypeButton);
        filtertypeButton.addActionListener(new ButtonHandler());
        panel.add(filtertypeField, "wrap");
        panel.add(filterstatusButton);
        filterstatusButton.addActionListener(new ButtonHandler());
        panel.add(filterstatusField, "wrap");
        panel.add(filtersenderButton);
        filtersenderButton.addActionListener(new ButtonHandler());
        panel.add(filtersender_IDField, "wrap");
        panel.add(filterreceiverButton);
        filterreceiverButton.addActionListener(new ButtonHandler());
        panel.add(filterreceiver_IDField, "wrap");
        //panel.add(totaldIDField, "wrap");
        panel.add(totalpIDButton);
        totalpIDButton.addActionListener(new ButtonHandler());
        panel.add(totalpIDField, "wrap");
        panel.add(totaltypeButton);
        totaltypeButton.addActionListener(new ButtonHandler());
        panel.add(totaltypeField, "wrap");
        panel.add(totalstatusButton);
        totalstatusButton.addActionListener(new ButtonHandler());
        panel.add(totalstatusField, "wrap");
        panel.add(new JLabel("Get the max/min avg parcel weight from the centers:"), "wrap");
        panel.add(maxButton);
        panel.add(minButton);
        radioGroup.add(maxButton);
        radioGroup.add(minButton);
        maxButton.setActionCommand("max");
        minButton.setActionCommand("min");
        maxButton.addActionListener(new ButtonHandler());
        minButton.addActionListener(new ButtonHandler());
        return panel;
    }

    public Boolean checkFieldData() {
            try {
                Integer.parseInt(filterdIDField.getText());
                if (filterdIDField.getText().length()>6) {
                    JOptionPane.showMessageDialog(null, "The delivery ID can only be max 6 numbers long");
                    return false;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "The delivery ID can only be a number");
                return false;
            }



            try {
                Integer.parseInt(filtersender_IDField.getText());
                if (filtersender_IDField.getText().length() > 6) {
                    JOptionPane.showMessageDialog(null, "The sender ID can only be max 6 numbers long");
                    return false;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "The sender ID can only be a number");
                return false;
            }




            try {
                Integer.parseInt(filterreceiver_IDField.getText());
                if ( filterreceiver_IDField.getText().length() > 6) {
                    JOptionPane.showMessageDialog(null, "The receiver ID can only be max 6 numbers long");
                    return false;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "The receiver ID can only be a number");
                return false;
            }


            try {
                Integer.parseInt(totalpIDField.getText());
                if ( totalpIDField.getText().length() > 6) {
                    JOptionPane.showMessageDialog(null, "The parcel ID can only be max 6 numbers long");
                    return false;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "The parcel ID can only be a number");
                return false;
            }



        if((filtertypeField.getText().length()>20 && !filtertypeField.getText().isEmpty())||
                (totaltypeField.getText().length()>20 && !totaltypeField.getText().isEmpty())) {
            JOptionPane.showMessageDialog(null, "The delivery type can only max 20 characters long");
            return false;
        }

        if((filterstatusField.getText().length()>20 && !filterstatusField.getText().isEmpty())
                || (totalstatusField.getText().length()>20 && !totalstatusField.getText().isEmpty())) {
            JOptionPane.showMessageDialog(null, "The delivery status can only max 20 characters long");
            return false;
        }

        return true;
    }


    private DeliveryParcel getFieldData() {
        if(checkFieldData()) {
            DeliveryParcel dp = new DeliveryParcel();
            dp.setFilterdID(Integer.parseInt(filterdIDField.getText()));
            dp.setFiltertype(filtertypeField.getText());
            dp.setFilterstatus(filterstatusField.getText());
            dp.setFiltersender(Integer.parseInt(filtersender_IDField.getText()));
            dp.setFilterreceiver(Integer.parseInt(filterreceiver_IDField.getText()));
            dp.setTotalpID(Integer.parseInt(totalpIDField.getText()));
            dp.setTotaltype(totaltypeField.getText());
            dp.setTotalstatus(totalstatusField.getText());
            return dp;
        } return null;
    }

    public JPanel initTable(String sql, String tableName) {
        JPanel panel = new JPanel();
        JTable table = bean.makeTable(sql);
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
            DeliveryParcel dp = getFieldData();
            switch (e.getActionCommand()) {
                case "max":
                    frame.add(initTable("SELECT MAX(weight) " +
                                    "FROM parcel " +
                                    "LEFT JOIN center " +
                                    "ON parcel.cID = center.cID " +
                                    "WHERE parcel.weight IN (" +
                                    "SELECT AVG(weight)" +
                                    "FROM parcel " +
                                    "LEFT JOIN center " +
                                    "ON parcel.cID=center.cID " +
                                    "GROUP BY center.cID)",
                            "The maximum of average weight of parcels from every center"));
                    frame.setVisible(true);
                    break;
                case "min":
                    frame.add(initTable("SELECT MIN(weight) " +
                                    "FROM parcel " +
                                    "LEFT JOIN center " +
                                    "ON parcel.cID = center.cID " +
                                    "WHERE parcel.weight IN (" +
                                    "SELECT AVG(weight)" +
                                    "FROM parcel "+
                                    "LEFT JOIN center " +
                                    "ON parcel.cID=center.cID " +
                                    "GROUP BY center.cID)",
                            "The minimum of average weight of parcels from every center"));
                    frame.setVisible(true);
                    break;
                case "Filter deliveries by delivery ID:":
                    frame.add(initTable("SELECT * " +
                            "FROM delivery " +
                            "LEFT JOIN parcel " +
                            "ON delivery.dID=parcel.dID " +
                            "WHERE delivery.dID='"+dp.getFilterdID()+"'",
                            "Parcels belonging to Deliveries with dID: "+ dp.getFilterdID()));
                    frame.setVisible(true);
                    break;
                case "Filter deliveries by type:":
                    frame.add(initTable("SELECT * " +
                                    "FROM delivery " +
                                    "LEFT JOIN parcel " +
                                    "ON delivery.dID=parcel.dID " +
                                    "WHERE delivery.type='" + dp.getFiltertype() + "'",
                                    "Deliveries of type: "+ dp.getFiltertype()));
                    frame.setVisible(true);
                    break;
                case "Filter deliveries by status:":
                    frame.add(initTable("SELECT * " +
                                    "FROM delivery " +
                                    "LEFT JOIN parcel " +
                                    "ON delivery.dID=parcel.dID " +
                                    "WHERE delivery.status='" + dp.getFilterstatus() + "'",
                            "Deliveries of status: "+ dp.getFilterstatus()));
                    frame.setVisible(true);
                    break;
                case "Filter deliveries by sender ID:":
                    frame.add(initTable("SELECT * " +
                                    "FROM delivery " +
                                    "LEFT JOIN parcel " +
                                    "ON delivery.dID=parcel.dID " +
                                    "WHERE delivery.sender_ID='" + dp.getFiltersender() + "'",
                            "Deliveries from sender: "+ dp.getFiltersender()));
                    frame.setVisible(true);
                    break;
                case "Filter deliveries by receiver ID:":
                    frame.add(initTable("SELECT * " +
                                    "FROM delivery " +
                                    "LEFT JOIN parcel " +
                                    "ON delivery.dID=parcel.dID " +
                                    "WHERE delivery.receiver_ID='" + dp.getFilterreceiver() + "'",
                            "Deliveries to receiver: "+ dp.getFilterreceiver()));
                    frame.setVisible(true);
                    break;
                case "Calculate total deliveries":
                    System.out.println("hi");
                    JOptionPane.showMessageDialog(null,
                            bean.Calculate("SELECT count(*) " +
                                    "FROM delivery"));
                    break;
                case "Calculate total parcels of delivery ID:":
                    if (dp.getTotalpID() == 0) {
                        JOptionPane.showMessageDialog(null,
                                bean.Calculate("SELECT count(*) " +
                                        "FROM parcel "));
                        break;
                    }
                    else {
                        JOptionPane.showMessageDialog(null,
                                bean.Calculate("SELECT count(*) " +
                                        "FROM parcel "+
                                        "WHERE parcel.dID='"+dp.getTotalpID()+"'"));
                    }
                    break;
                case "Calculate total deliveries with type:":
                    JOptionPane.showMessageDialog(null,
                            bean.Calculate("SELECT count(*) " +
                                    "FROM delivery "+
                                    "WHERE delivery.type='"+dp.getTotaltype()+"'"));
                    break;
                case "Calculate total deliveries with status:":
                    JOptionPane.showMessageDialog(null,
                            bean.Calculate("SELECT count(*) " +
                                    "FROM delivery " +
                                    "WHERE delivery.status='" + dp.getTotalstatus() + "'"));
                    break;
                default:
                    JOptionPane.showMessageDialog(null,
                            "Invaild command");
            }
        }
    }
}
