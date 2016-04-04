package views;

import apple.laf.JRSUIUtils;
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
public class AskForCentreUI extends JPanel {
    JDesktopPane desktopPane = new JDesktopPane();
    JInternalFrame intFrame = new JInternalFrame("Number of parcels at this location");
    JInternalFrame maxWeightFrame = new JInternalFrame("Max weight of parcel at this location");
    JInternalFrame divisionFrame = new JInternalFrame("Any deliveries that have parcels at all centers?");

    public static JPanel panel;
    private JTextField cIDField = new JTextField(30);
    private JTextField center_addrField = new JTextField(40);
    private JTextField parcelAtThatCenterField = new JTextField(30);


    private JButton submitButton = new JButton("Submit");
    private JButton searchButton = new JButton("Search for Number of Parcels");
    private JButton maxWeightButton = new JButton("Search for Max Weight of Parcel");
    private JButton divisionButton = new JButton("Search for deliveries that have parcels at all centers");
    private JButton allParcelButton = new JButton("View all parcels information");
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
//        setBorder(new TitledBorder(
//                new EtchedBorder(), "Centre ID"
//        ));
        intFrame.setMaximizable(true);
        intFrame.setIconifiable(true);
        intFrame.setClosable(true);
        intFrame.setSize(320, 240);
        intFrame.setVisible(false);

        maxWeightFrame.setMaximizable(true);
        maxWeightFrame.setIconifiable(true);
        maxWeightFrame.setClosable(true);
        maxWeightFrame.setSize(320, 240);
        maxWeightFrame.setLocation(100,100);
        maxWeightFrame.setVisible(false);

        divisionFrame.setMaximizable(true);
        divisionFrame.setIconifiable(true);
        divisionFrame.setClosable(true);
        divisionFrame.setSize(320, 240);
        divisionFrame.setLocation(100,100);
        divisionFrame.setVisible(false);

        desktopPane.add(intFrame);
        desktopPane.add(maxWeightFrame);
        desktopPane.add(divisionFrame);
        add(intFrame);
        add(maxWeightFrame);
        add(divisionFrame);

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
        panel.setLayout(new MigLayout());
//        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));
        panel.add(submitButton, "wrap");
        submitButton.addActionListener(new ButtonHandler());
        panel.add(searchButton, "wrap");
        searchButton.addActionListener(new ButtonHandler());
        panel.add(maxWeightButton, "wrap");
        maxWeightButton.addActionListener(new ButtonHandler());
        panel.add(divisionButton, "wrap");
        divisionButton.addActionListener(new ButtonHandler());
        panel.add(allParcelButton, "wrap");
        allParcelButton.addActionListener(new ButtonHandler());
//        panel.add(back);
//        back.addActionListener(new ButtonHandler());
        return panel;
    }
    private JPanel initFields() {
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout());
        panel.add(new JLabel("Center ID"), "align label");
        panel.add(cIDField, "wrap");
//        panel.add(new JLabel("Enter cID for parcel"), "align label");
//        panel.add(parcelAtThatCenterField, "wrap");
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

    private boolean isAlpha(String name) {
        return name.matches("[a-zA-Z]+");
    }

    private boolean isEmptyFieldDatacID() {
        return (cIDField.getText().trim().isEmpty());
    }
    private boolean isSearchingForParcelEmpty() { return (parcelAtThatCenterField.getText().trim().isEmpty()); }

    private String cid;

    private boolean isValidcID() {
        return !(cIDField.getText().trim().equals("ubc") || cIDField.getText().trim().equals("burnabysouth") || cIDField.getText().trim().equals("burnabynorth")
        || cIDField.getText().trim().equals("surrey") || cIDField.getText().trim().equals("portcoquitlam"));
    }

    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame f = new JFrame(cid);
            JFrame pars = new JFrame();
            JDialog d = new JDialog();
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            f.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
//            JInternalFrame iFrame = new JInternalFrame(cid);
//            iFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            switch (e.getActionCommand()) {
                case "Submit":
                    if (isEmptyFieldDatacID()) {
                        JOptionPane.showMessageDialog(null,
                                "Please enter a center ID");
                    } else if (isValidcID()) {
                        System.out.println("isValidcID: " + isValidcID());
                        JOptionPane.showMessageDialog(null,
                        "Please enter an existing center ID");
                    } else {
                        System.out.println("center ID = " + cIDField.getText());
                        cid = cIDField.getText();
                        //http://www.java2s.com/Tutorial/Java/0240__Swing/CreatingaJTable.htm
                        f.add(new CenterUI(cid));
                        System.out.println("new frame?");
                        f.setSize(700, 1200);
                        f.setVisible(true);
//                        panel.setVisible(false);
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
                    break;

                case "Search for Number of Parcels":
                    if (isEmptyFieldDatacID()) {
                        JOptionPane.showMessageDialog(null,
                                "Please enter a center ID");
                    } else if (isValidcID()) {
                        System.out.println("isValidcID: " + isValidcID());
                        JOptionPane.showMessageDialog(null,
                                "Please enter an existing center ID");
                    } else {
                        cid = cIDField.getText();
                        System.out.println("get number of parcels for center ID = " + cid);
//                        AskForCentreUI iFrame = new AskForCentreUI();
//                        iFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//                        add(intFrame);
                        intFrame.add(initTable("select pID, count(*) from parcel where parcel.cid= '" + cid + "' " +
                                "group by pID"));
                        intFrame.setSize(320, 480);
                        intFrame.setVisible(true);
                    }
                    break;

                case "Search for Max Weight of Parcel":
                    if (isEmptyFieldDatacID()) {
                        JOptionPane.showMessageDialog(null,
                                "Please enter a center ID");
                    } else if (isValidcID()) {
                        System.out.println("isValidcID: " + isValidcID());
                        JOptionPane.showMessageDialog(null,
                                "Please enter an existing center ID");
                    } else {
                        cid = cIDField.getText();
                        System.out.println("executing max weight for "+cid);
                        maxWeightFrame.add(initTable("select max(weight) from parcel where cid='" + cid + "' "));
                       maxWeightFrame.setSize(320, 240);
                        maxWeightFrame.setVisible(true);
                    }
                    break;

                case "Search for deliveries that have parcels at all centers":
                    divisionFrame.add(initTable("select delivery.did, count(cid) \n" +
                            "from parcel left join delivery on parcel.did = delivery.did \n" +
                            "group by delivery.did \n" +
                            "having count(*) = (select count(*) from center);"));
                    divisionFrame.setSize(480, 240);
                    divisionFrame.setVisible(true);
                    break;

                case "View all parcels information":
                    System.out.println("Executing all parcels information");
                    pars.add(new ViewParcelsUI());
                    pars.setSize(550, 550);
                    pars.setVisible(true);
                    break;

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
