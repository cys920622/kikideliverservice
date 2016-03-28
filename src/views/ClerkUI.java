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
public class ClerkUI extends JPanel {
    private JButton browseClients = new JButton("Browse Clients");
    private JButton editClients = new JButton("Create or Update Clients");
    private JButton browseDeliveries = new JButton("Browse Deliveries");
    private JButton editDeliveries= new JButton("Create or Update Deliveries");
    private ClerkBean bean = new ClerkBean();

    public ClerkUI() {
        setBorder(new TitledBorder(
                new EtchedBorder(), "Clerk details"));
        setLayout(new BorderLayout(100, 10));
        initButtons().setVisible(true);
        add(initButtons(), BorderLayout.CENTER);
    }

    private JTable initTable(String sql) {
        return bean.makeTable(sql);
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
        return panel;
    }

    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "Browse Clients":
                    JTable table1 = initTable("SELECT * FROM clients LEFT JOIN address ON clients.PC=address.PC and clients.house_num=address.house_num");
                    table1.setAutoCreateRowSorter(true);
                    table1.getAutoResizeMode();
                    add(new JScrollPane(table1), BorderLayout.LINE_END);
                    break;
                case "Create or Update Clients":
                    ClientsUI clientsUI = new ClientsUI();
                    add(clientsUI);
                    break;
                case "Browse Deliveries":

                    break;
                case "Create or Update Deliveries":
                    break;

                default:
                    JOptionPane.showMessageDialog(null,
                            "Invalid command");
            }
        }
    }



}