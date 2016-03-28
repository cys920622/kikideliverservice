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
    private JTextField center_addrField = new JTextField(40);

    private JButton submitButton = new JButton("Submit");
//    private JButton back = new JButton(("Back"));


    private CenterBean bean = new CenterBean();

//    private static AskForCentreUI center;
//
//    public static AskForCentreUI getInstance() {
//        if (center == null) {
//           return center = new AskForCentreUI();
//        } else {
//            return center;
//        }
//    }

    public AskForCentreUI() {
        setBorder(new TitledBorder(
                new EtchedBorder(), "Centre ID"
        ));
        setLayout(new BorderLayout(5, 5));
        add(initFields(), BorderLayout.NORTH);
        add(initButtons(), BorderLayout.EAST);
        JTable table = initTable("SELECT * FROM center ORDER BY cID");
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
//        panel.add(back);
//        back.addActionListener(new ButtonHandler());
        return panel;
    }
    private JPanel initFields() {
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout());
        panel.add(new JLabel("Center ID"), "align label");
        panel.add(cIDField, "wrap");
//        panel.add(new JLabel("Center Address"), "align label");
//        panel.add(center_addrField, "wrap");
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
        return (cIDField.getText().trim().isEmpty());
    }

    private String cid;

    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame f = new JFrame(cid);
            JDialog d = new JDialog();
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            f.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));

            switch (e.getActionCommand()) {
                case "Submit":
                    if (isEmptyFieldData()) {
                        JOptionPane.showMessageDialog(null,
                                "Please enter a center ID");
                    } else {
                        System.out.println("center ID = " + cIDField.getText());
                        cid = cIDField.getText();
                        //http://www.java2s.com/Tutorial/Java/0240__Swing/CreatingaJTable.htm
                        f.add(new CenterUI(cid));
                        System.out.println("new frame?");
                        f.setSize(700, 1200);
                        f.setVisible(true);
//                        panel.setVisible(false);
                        break;
//                        JTable submittable = initTable("select cID, pID from center natural " +
//                                "join parcel where cID = '" + cIDField.getText() + "'");
//                        JScrollPane scrollPane = new JScrollPane(submittable);
//                        JButton back = new JButton("Back");
//                        f.add(scrollPane, BorderLayout.SOUTH);
//                        f.setLocation(100,100);
//                        f.setSize(500, 400);
//                        f.setVisible(true);
//                    JTable submittable = initTable("select cID, pID from center natural " +
//                        "join parcel where cID = '" + cIDField.getText() + "'");
                    }
//                case "Back":
//                    HomeUI hui = HomeUI.getInstance();
//                    hui.setVisible(true);
//                    HomeUI.f.add(hui);
//
//                    setVisible(false);

            }
        }
    }
}
