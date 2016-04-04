package views;

import controllerBeans.ClerkBean;
import javafx.scene.control.ComboBox;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by chuchutrainn on 2016-04-03.
 */
public class ViewParcelsUI extends JPanel {

    private ClerkBean bean = new ClerkBean();
    private int tableWidth;
    private DefaultComboBoxModel cb = new DefaultComboBoxModel();
    private JComboBox firstSelection = new JComboBox(cb);
    private JButton showButton = new JButton("Show");
    private JInternalFrame pIDFrame = new JInternalFrame("Showing all pIDs");
    private JInternalFrame lengthFrame = new JInternalFrame("Showing lengths of all parcels");
    private JInternalFrame widthFrame = new JInternalFrame("Showing width of all parcels");
    private JInternalFrame weightFrame = new JInternalFrame("Showing weight of all parcels");
    private JInternalFrame heightFrame = new JInternalFrame("Showing height of all parcels");
    private JInternalFrame cIDFrame = new JInternalFrame("Showing associated cIDs of all parcels");
    private JInternalFrame next_cIDFrame = new JInternalFrame("Showing associated next_cIDs of all parcels");
    private JInternalFrame dIDFrame = new JInternalFrame("Showing associated dID of all parcels");
    private JScrollPane scroll = new JScrollPane();

    private JPanel initButtons() {
        cb.addElement("pID");
        cb.addElement("length");
        cb.addElement("width");
        cb.addElement("weight");
        cb.addElement("height");
        cb.addElement("cID");
        cb.addElement("next_cID");
        cb.addElement("dID");
        firstSelection.setSelectedIndex(0);
        JScrollPane first = new JScrollPane(firstSelection);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));
        panel.setSize(550, 550);
        panel.add(first);
        panel.add(showButton);
        showButton.addActionListener(new ButtonHandler());
        return panel;
    }

    public ViewParcelsUI() {

        pIDFrame.setIconifiable(true);
        pIDFrame.setClosable(true);
        pIDFrame.setSize(240, 480);
        pIDFrame.setVisible(false);
        pIDFrame.setLocation(50, 50);
        pIDFrame.setResizable(true);
        add(pIDFrame);

        lengthFrame.setIconifiable(true);
        lengthFrame.setClosable(true);
        lengthFrame.setSize(320, 320);
        lengthFrame.setVisible(false);
        lengthFrame.setLocation(50, 50);
        lengthFrame.setResizable(true);
        add(lengthFrame);

        widthFrame.setIconifiable(true);
        widthFrame.setClosable(true);
        widthFrame.setSize(320, 320);
        widthFrame.setVisible(false);
        widthFrame.setLocation(50, 50);
        widthFrame.setResizable(true);
        add(widthFrame);

        weightFrame.setIconifiable(true);
        weightFrame.setClosable(true);
        weightFrame.setSize(320, 320);
        weightFrame.setVisible(false);
        weightFrame.setLocation(50, 50);
        weightFrame.setResizable(true);
        add(weightFrame);

        heightFrame.setIconifiable(true);
        heightFrame.setClosable(true);
        heightFrame.setSize(320, 320);
        heightFrame.setVisible(false);
        heightFrame.setLocation(50, 50);
        heightFrame.setResizable(true);
        add(heightFrame);

        cIDFrame.setIconifiable(true);
        cIDFrame.setClosable(true);
        cIDFrame.setSize(320, 320);
        cIDFrame.setVisible(false);
        cIDFrame.setLocation(50, 50);
        cIDFrame.setResizable(true);
        add(cIDFrame);

        next_cIDFrame.setIconifiable(true);
        next_cIDFrame.setClosable(true);
        next_cIDFrame.setSize(320, 320);
        next_cIDFrame.setVisible(false);
        next_cIDFrame.setLocation(50, 50);
        next_cIDFrame.setResizable(true);
        add(next_cIDFrame);

        dIDFrame.setIconifiable(true);
        dIDFrame.setClosable(true);
        dIDFrame.setSize(320, 320);
        next_cIDFrame.setLocation(50, 50);
        dIDFrame.setVisible(false);
        dIDFrame.setResizable(true);
        add(dIDFrame);


        add(initButtons(), BorderLayout.CENTER);
        setBorder(new TitledBorder(
                new EtchedBorder(), "All Parcels Information"));
        JTabbedPane jtab = new JTabbedPane();

        setBorder(new TitledBorder(new EtchedBorder()));
        setLayout(new BorderLayout(1, 1));
//        JScrollPane scrollPane = new JScrollPane(initTable(sql));

        setPreferredSize(new Dimension(tableWidth, 250));
//        add(scrollPane);

//        JComponent centerInfo = new JPanel();
//        ClerkUI browseCenterInfo = new ClerkUI("select * from center " +
//                "where cID = '" + cid + "'", cid +" Information");
//        browseCenterInfo.setSize(browseCenterInfo.getWidth(), browseCenterInfo.getHeight());
//        centerInfo.add(browseCenterInfo);
////        centerInfo.add(new )
//        jtab.add("Center Info", browseCenterInfo);
//
    }

    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (firstSelection.getSelectedIndex() != -1 && (firstSelection.getItemAt(firstSelection.getSelectedIndex()) == "pID")) {
                String sql = "select " + firstSelection.getItemAt(firstSelection.getSelectedIndex()) + " from parcel";
                System.out.println("sql for firstSelection: " + sql);
                pIDFrame.add(initTable(sql));
                pIDFrame.setSize(480, 320);
                pIDFrame.setVisible(true);
            } else if (firstSelection.getSelectedIndex() != -1 && (firstSelection.getItemAt(firstSelection.getSelectedIndex()) == "length")) {
                String sql = "select " + firstSelection.getItemAt(firstSelection.getSelectedIndex()) + " from parcel";
                System.out.println("sql for firstSelection: " + sql);
                lengthFrame.add(initTable(sql));
                lengthFrame.setSize(480, 320);
                lengthFrame.setVisible(true);
            } else if (firstSelection.getSelectedIndex() != -1 && (firstSelection.getItemAt(firstSelection.getSelectedIndex()) == "width")) {
                String sql = "select " + firstSelection.getItemAt(firstSelection.getSelectedIndex()) + " from parcel";
                System.out.println("sql for firstSelection: " + sql);
                widthFrame.add(initTable(sql));
                widthFrame.setSize(480, 320);
                widthFrame.setVisible(true);
            } else if (firstSelection.getSelectedIndex() != -1 && (firstSelection.getItemAt(firstSelection.getSelectedIndex()) == "weight")) {
                String sql = "select " + firstSelection.getItemAt(firstSelection.getSelectedIndex()) + " from parcel";
                System.out.println("sql for firstSelection: " + sql);
                weightFrame.add(initTable(sql));
                weightFrame.setSize(480, 320);
                weightFrame.setVisible(true);
            } else if (firstSelection.getSelectedIndex() != -1 && (firstSelection.getItemAt(firstSelection.getSelectedIndex()) == "height")) {
                String sql = "select " + firstSelection.getItemAt(firstSelection.getSelectedIndex()) + " from parcel";
                System.out.println("sql for firstSelection: " + sql);
                heightFrame.add(initTable(sql));
                heightFrame.setSize(480, 320);
                heightFrame.setVisible(true);
            } else if (firstSelection.getSelectedIndex() != -1 && (firstSelection.getItemAt(firstSelection.getSelectedIndex()) == "cID")) {
                String sql = "select " + firstSelection.getItemAt(firstSelection.getSelectedIndex()) + " from parcel";
                System.out.println("sql for firstSelection: " + sql);
                cIDFrame.add(initTable(sql));
                cIDFrame.setSize(480, 320);
                cIDFrame.setVisible(true);
            } else if (firstSelection.getSelectedIndex() != -1 && (firstSelection.getItemAt(firstSelection.getSelectedIndex()) == "next_cID")) {
                String sql = "select " + firstSelection.getItemAt(firstSelection.getSelectedIndex()) + " from parcel";
                System.out.println("sql for firstSelection: " + sql);
                next_cIDFrame.add(initTable(sql));
                next_cIDFrame.setSize(480, 320);
                next_cIDFrame.setVisible(true);
            } else if (firstSelection.getSelectedIndex() != -1 && (firstSelection.getItemAt(firstSelection.getSelectedIndex()) == "dID")) {
                String sql = "select " + firstSelection.getItemAt(firstSelection.getSelectedIndex()) + " from parcel";
                System.out.println("sql for firstSelection: " + sql);
                dIDFrame.add(initTable(sql));
                dIDFrame.setSize(480, 320);
                dIDFrame.setVisible(true);
            }
        }
    }

    public JTable initTable(String sql) {
        JTable table = bean.makeTable(sql);
        table.setAutoCreateRowSorter(true);
        tableWidth = (45 * table.getColumnCount());
        if (tableWidth < 400) {
            tableWidth = 400;
        }
        if (tableWidth > 570) {
            tableWidth = 570;
        }
        return table;
    }
}






