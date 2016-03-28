package views;

import controllerBeans.ClerkBean;
import entityClasses.Address;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import static javax.swing.event.TableModelEvent.UPDATE;

/**
 * Created by stellafang. on 2016-03-27.
 */
public class ClerkUI extends JPanel implements TableModelListener {
    private JButton browseClients = new JButton("Browse Clients");
    private JButton editClients = new JButton("Create or Update Clients");
    private JButton browseDeliveries = new JButton("Browse Deliveries");
    private JButton editDeliveries= new JButton("Create or Update Deliveries");
    private JButton browseParcels = new JButton("Browse Parcels");
    private ClerkBean bean = new ClerkBean();

    public ClerkUI() {
        setBorder(new TitledBorder(
                new EtchedBorder(), "Clerk details"));
        setLayout(new BorderLayout(100, 10));
        add(initButtons(), BorderLayout.CENTER);


    }

    private JTable initTable(String sql) {
        return bean.makeTable(sql);
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        int row = e.getFirstRow();
        int column = e.getColumn();
        TableModel model = (TableModel) e.getSource();
        String columnName = model.getColumnName(column);
        Object data = model.getValueAt(row, column);
        System.out.println("test");

    }

    private JPanel initButtons() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));
        panel.add(browseClients);
        browseClients.addActionListener(new ButtonHandler());
        panel.add(editClients);
        editClients.addActionListener(new ButtonHandler());
        panel.add(browseDeliveries);
        browseDeliveries.addActionListener(new ButtonHandler());
        panel.add(editDeliveries);
        editDeliveries.addActionListener(new ButtonHandler());
//        panel.add(browseParcels);
//        browseParcels.addActionListener(new ButtonHandler());
        return panel;
    }

    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
//            JFrame f = new JFrame();
//            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//            f.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
            switch (e.getActionCommand()) {
                case "Browse Clients":
                    initButtons().setVisible(false);

                    JTable table1 = initTable("SELECT * FROM clients LEFT JOIN address ON clients.PC=address.PC and clients.house_num=address.house_num");
                    table1.setAutoCreateRowSorter(true);
                    add(new JScrollPane(table1), BorderLayout.CENTER);
                    JTable table2 = initTable("select * from parcel");
                    add(new JScrollPane(table2), BorderLayout.AFTER_LAST_LINE);

                    break;
                case "Create or Update Clients":
                    break;
                case "Browse Deliveries":

                    break;
                case "Create or Update Deliveries":
                    break;
                case "First":

                    break;
                default:
                    JOptionPane.showMessageDialog(null,
                            "Invalid command");
            }
        }
    }



}