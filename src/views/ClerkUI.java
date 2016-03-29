package views;

import controllerBeans.ClerkBean;
import entityClasses.Address;

import javax.sql.RowSetEvent;
import javax.sql.RowSetListener;
import javax.sql.rowset.CachedRowSet;
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
public class ClerkUI extends JPanel{
    private JButton browseClients = new JButton("Browse Clients");
    private JButton editClients = new JButton("Create or Update Clients");
    private JButton browseDeliveries = new JButton("Browse Deliveries");
    private JButton editDeliveries= new JButton("Create or Update Deliveries");

    private ClerkBean bean = new ClerkBean();
    private JTable table;

    public ClerkUI(String sql, String tableName) {

        setBorder(new TitledBorder(
                new EtchedBorder(), tableName));
        setLayout(new BorderLayout(1, 1));
        add(new JScrollPane(initTable(sql)), BorderLayout.PAGE_END);

    }

    public JTable initTable(String sql) {
        JTable table = bean.makeTable(sql);
        table.setAutoCreateRowSorter(true);
        table.setAutoResizeMode(5);
        return table;
    }



}