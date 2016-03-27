package views;

import controllerBeans.ClerkBean;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

/**
 * Created by stellafang. on 2016-03-27.
 */
public class ClerkUI extends JPanel{



    private ClerkBean bean = new ClerkBean();

    public ClerkUI () {
        setBorder(new TitledBorder(
                new EtchedBorder(), "Clerk details"));
        setLayout(new BorderLayout(5, 5));
        JTable table1 = initTable("SELECT * FROM clients LEFT JOIN address ON clients.PC=address.PC");
        table1.setAutoCreateRowSorter(true);
        add(new JScrollPane(table1), BorderLayout.SOUTH);
        JTable table2 = initTable("select * from parcel");
        table2.setAutoCreateRowSorter(true);
        add(new JScrollPane(table2), BorderLayout.CENTER);
        //add(initFields(), BorderLayout.NORTH);
        //super("Clerk Window");
//
//        addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                System.exit(0);
//
//            }
//        });

//        setBorder(new TitledBorder(
//                new EtchedBorder(), "Clients details"));
//        setLayout(new BorderLayout(5, 5));
        //add(initFields(), BorderLayout.NORTH);
//        DefaultTableModel model = new DefaultTableModel();
//        table.setModel(bean.buildTableModel(model));
//
//        //JTable table = new JTable(bean.buildTableModel());
//        table.setAutoCreateRowSorter(true);
//        JScrollPane scroll= new JScrollPane(table);
//        add(scroll, BorderLayout.CENTER);
//        JOptionPane.showMessageDialog(null, new JScrollPane(table));

    }

    private JTable initTable(String sql) {
        return bean.makeTable(sql);
    }
}
