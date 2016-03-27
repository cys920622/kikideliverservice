package views;

import controllerBeans.ClerkBean;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

/**
 * Created by stellafang. on 2016-03-27.
 */
public class ClerkUI extends JFrame{
    JTextField query;
    JTable table;
    JLabel msgline;
    ClerkBean bean;

    public ClerkUI () {
        super("Clerk Window");

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                    System.exit(0);

            }
        });

        query = new JTextField();
        table = new JTable();
        msgline = new JLabel();

        Container contentPane = getContentPane();
        contentPane.add(query, BorderLayout.NORTH);
        contentPane.add(new JScrollPane(table), BorderLayout.CENTER);
        contentPane.add(msgline, BorderLayout.SOUTH);

        query.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayQueryResults(query.getText());
            }
        });

    }

    public void displayQueryResults(final String q) {
        msgline.setText("Contacting database...");

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                bean = new ClerkBean(q);
                table.setModel(bean);
                msgline.setText(" ");
            }
        });
    }

}
