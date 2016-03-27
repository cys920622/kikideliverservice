package views;

import controllerBeans.CenterBean;
import entityClasses.Center;
import net.miginfocom.swing.MigLayout;
import oracle.jrockit.jfr.JFR;

import javax.sql.rowset.JdbcRowSet;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


/**
 * Created by chuchutrainn on 2016-03-27.
 */
public class AskForCentreUI extends JPanel{
    public static JPanel panel;
    private JTextField cIDField = new JTextField(30);
    private JTextField center_addrField = new JTextField(50);

    private JButton submitButton = new JButton("Submit");


    private CenterBean bean = new CenterBean();

    public AskForCentreUI() {
        setBorder(new TitledBorder(
                new EtchedBorder(), "Centre ID"
        ));
        setLayout(new BorderLayout(5, 5));
        add(initFields(), BorderLayout.NORTH);
        add(initButtons(), BorderLayout.EAST);
        JTable table = initTable("SELECT * FROM center natural join parcel ORDER BY cID");
        table.setAutoCreateRowSorter(true);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    private JTable initTable(String sql) {
        return bean.makeTable(sql);
    }

    private JPanel initButtons() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));
        panel.add(submitButton);
        submitButton.addActionListener(new ButtonHandler());
        return panel;
    }
    private JPanel initFields() {
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout());
        panel.add(new JLabel("Center ID"), "align label");
        panel.add(cIDField, "wrap");
        panel.add(new JLabel("Center Address"), "align label");
        panel.add(center_addrField, "wrap");
//        panel.add(new JTable(submittable));
        return panel;
    }

    private Center getFieldData() {
        Center c = new Center();
        c.setcID(cIDField.getText());
        c.setCenter_addr(center_addrField.getText());
        return c;
    }

    private void setFieldData(Center c) {
        cIDField.setText(c.getcID());
        center_addrField.setText(c.getCenter_addr());
    }

    private boolean isEmptyFieldData() {
        return (cIDField.getText().trim().isEmpty()
        && center_addrField.getText().trim().isEmpty());
    }

    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame f = new JFrame();
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));

            switch (e.getActionCommand()) {
                case "Submit":
                    if (isEmptyFieldData()) {
                        JOptionPane.showMessageDialog(null,
                                "Please enter a center ID");
                    }
                    System.out.println("center ID = " + cIDField.getText());
                    JTable submittable = initTable("select cID, pID from center natural " +
                            "join parcel where cID = '" + cIDField.getText() + "'");
                    JScrollPane scrollPane = new JScrollPane(submittable);
                    f.add(scrollPane, BorderLayout.SOUTH);
                    f.setSize(300, 150);
                    f.setVisible(true);
//                    JTable submittable = initTable("select cID, pID from center natural " +
//                        "join parcel where cID = '" + cIDField.getText() + "'");

            }
        }
    }
}
