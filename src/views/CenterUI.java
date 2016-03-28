package views;

import controllerBeans.CenterBean;
import entityClasses.Center;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Created by chuchutrainn on 2016-03-27.
 */
public class CenterUI extends JPanel{
    private JButton centerInfo = new JButton("Center Information");
    private JButton browsePackageInfo = new JButton("Package Information");

    private CenterBean bean = new CenterBean();
    private JTable table;

    public CenterUI(String cid){
        setBorder(new TitledBorder(
                new EtchedBorder(), "Center ID: " + cid));

        JTabbedPane jtab = new JTabbedPane();

        //given center information
        JComponent centerInfo = new JPanel();
        ClerkUI browseCenterInfo = new ClerkUI("select * from center " +
                "where cID = '" + cid + "'", cid +" Information");
        browseCenterInfo.setSize(browseCenterInfo.getWidth(), browseCenterInfo.getHeight());
        centerInfo.add(browseCenterInfo);
//        centerInfo.add(new )
        jtab.add("Center Information", browseCenterInfo);
//        JTable submittable = initTable("select * from center where cID = '" + cid + "'");
//        JScrollPane scrollPane = new JScrollPane(submittable);
//        submittable.setSize(submittable.getWidth(), submittable.getHeight());
//        jtab.add(centerInfo, centerInfo);

        //packages at that center information
        JComponent packageInfo = new JPanel();
        ClerkUI browsePackageInfo = new ClerkUI("select pID, length, width, height, " +
                "next_cID, dID from center " +
                "natural join parcel where center.cID = '" + cid + "'", "Packages at " + cid);
        browsePackageInfo.setSize(browsePackageInfo.getWidth(), browsePackageInfo.getHeight());
        packageInfo.add(browsePackageInfo);
        jtab.add("Package at " + cid +" Information", browsePackageInfo);


        add(jtab, BorderLayout.CENTER);
        jtab.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

    }

    private JPanel initButtons() {
        JPanel panel = new JPanel();
        return panel;
    }


    private JTable initTable(String sql) {
        JTable table = bean.makeTable(sql);
        table.setAutoCreateRowSorter(true);
        table.setAutoResizeMode(5);
        return table;
    }




}
