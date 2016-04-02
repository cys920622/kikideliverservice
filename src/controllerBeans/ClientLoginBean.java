package controllerBeans;

import com.sun.rowset.JdbcRowSetImpl;
import entityClasses.Clients;
import entityClasses.Delivery;
import net.miginfocom.swing.MigLayout;

import javax.sql.rowset.JdbcRowSet;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by danielchoi on 2016-03-28.
 */
public class ClientLoginBean {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
//    static final String DB_URL = "jdbc:mysql://localhost:3306/Kiki's_DeliveryService";
    static final String DB_URL = "jdbc:mysql://localhost/Kiki's_DeliveryService?&useSSL=false";
    static final String USER = "root";
    static final String PASS = "Iloveme711";
    private JdbcRowSet rowSet = null;

    private Connection conn;
    private Statement stmt;
    private ResultSet sentDeliveriesRs;
    private ResultSet receiveDeliveriesRs;
    private ArrayList<Delivery> sentDeliveries = new ArrayList<>();
    private ArrayList<Delivery> receivedDeliveries = new ArrayList<>();
    private ArrayList<Clients> senderClients = new ArrayList<>();
    private ArrayList<Clients> receiverClients = new ArrayList<>();

    public ClientLoginBean() {
        try {
            Class.forName(JDBC_DRIVER);
//            rowSet = new JdbcRowSetImpl();
//            rowSet.getURL(DB_URL);
//            rowSet.setUsername(USER);
//            rowSet.setPassword(PASS);
//            rowSet.setCommand("SELECT * FROM clients");
//            rowSet.execute();

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
//            String sql = "SELECT DISTINCT * FROM delivery, clients WHERE delivery.receiver_ID = clients.clID AND sender_ID = "+clientID;
//            sentDeliveriesRs = stmt.executeQuery(sql);
//            while (sentDeliveriesRs.next()) {
//                Delivery d = new Delivery();
//                d.setdID(sentDeliveriesRs.getInt("dID"));
//                d.setSender_ID(sentDeliveriesRs.getInt("sender_ID"));
//                d.setReceiver_ID(sentDeliveriesRs.getInt("receiver_ID"));
//                d.setType(sentDeliveriesRs.getString("type"));
//                d.setStatus("TEST");
//                sentDeliveries.add(d);
//
//                Clients c = new Clients();
//                c.setFname(sentDeliveriesRs.getString("fname"));
//                senderClients.add(c);
//            }

//            sql = "SELECT DISTINCT * FROM delivery, clients WHERE delivery.sender_ID = clients.clID AND receiver_ID = " +clientID;
//            receiveDeliveriesRs = stmt.executeQuery(sql);
//            while (receiveDeliveriesRs.next()) {
//                Delivery d = new Delivery();
//                d.setdID(receiveDeliveriesRs.getInt("dID"));
//                d.setSender_ID(receiveDeliveriesRs.getInt("sender_ID"));
//                d.setReceiver_ID(receiveDeliveriesRs.getInt("receiver_ID"));
//                d.setType(receiveDeliveriesRs.getString("type"));
//                d.setStatus("TEST");
//                receivedDeliveries.add(d);
//
//                Clients c = new Clients();
//                c.setFname(receiveDeliveriesRs.getString("fname"));
//                receiverClients.add(c);
//            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void testConnection() {
            String sql = "SELECT DISTINCT * FROM delivery, clients WHERE delivery.receiver_ID = clients.clID AND sender_ID = 139284";
        try {
            sentDeliveriesRs = stmt.executeQuery(sql);
            while (sentDeliveriesRs.next()) {
                System.out.println("dID: "+sentDeliveriesRs.getInt("dID"));
//                System.out.println(sentDeliveriesRs.getInt("sender_ID"));
//                System.out.println(sentDeliveriesRs.getInt("receiver_ID"));
//                System.out.println(sentDeliveriesRs.getString("type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public JPanel populateJPanel(JPanel panel, String sql) {
        try {
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            System.out.println("dID: " + rs.getInt("D.dID") + ", status: " + rs.getString("D.status")
                    + ", sender: "+rs.getString("S.fname")+" "+rs.getString("S.lname")+ " at: "+rs.getString("SA.city")
                    + ", receiver: "+rs.getString("R.fname")+" "+rs.getString("R.lname")+ " at: "+rs.getString("RA.city"));
            panel.setLayout(new MigLayout());
            panel.add(new JLabel("Delivery ID: "), "align label");
            panel.add(new JLabel(rs.getString("D.dID")), "wrap");
            panel.add(new JLabel("Delivery type: "), "align label");
            panel.add(new JLabel(rs.getString("D.type")), "wrap");
            panel.add(new JLabel("Status: "), "align label");
            panel.add(new JLabel(rs.getString("D.status")), "wrap");
            panel.add(new JLabel(" "), "wrap"); // spacer
            panel.add(new JLabel("Sender: "), "align label");
            panel.add(new JLabel(rs.getString("S.fname")+" "+rs.getString("S.lname")), "wrap");
            panel.add(new JLabel("Return address: "), "align label");
            panel.add(new JLabel(rs.getString("SA.house_num")+" "+rs.getString("SA.street_name")+ ", "+
                    rs.getString("SA.city")+ " "+rs.getString("SA.province")+", "+rs.getString("SA.country")), "wrap");
            panel.add(new JLabel(" "), "wrap"); // spacer
            panel.add(new JLabel("Receiver: "), "align label");
            panel.add(new JLabel(rs.getString("R.fname")+" "+rs.getString("R.lname")), "wrap");
            panel.add(new JLabel("Destination address: "), "align label");
            panel.add(new JLabel(rs.getString("RA.house_num")+" "+rs.getString("RA.street_name")+ ", "+
                    rs.getString("RA.city")+ " "+rs.getString("RA.province")+", "+rs.getString("RA.country")), "wrap");
            panel.add(new JLabel(" "), "wrap"); // spacer
            panel.add(new JLabel("Parcel weight: "), "align label");
            panel.add(new JLabel(rs.getString("P.weight")+"kg"), "wrap");
            panel.add(new JLabel("Parcel dimensions: "), "align label");
            panel.add(new JLabel(rs.getString("P.length")+ "cm x "+
                    rs.getString("P.width")+ "cm x "+rs.getString("P.height")+"cm"), "wrap");

        } catch (SQLException e) {
            e.printStackTrace();
            panel.setLayout(new MigLayout());
            panel.add(new JLabel("No results."), "align label");
        }
        return panel;
    }
    public JPanel getDeliveryQueryAsJPanel(int dID) {
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(
                new EtchedBorder(), "Query result"));
        String sql = "SELECT DISTINCT * " +
                "FROM delivery D, clients S, address SA, clients R, address RA, parcel P " +
                "WHERE D.sender_ID=S.clID AND D.receiver_ID=R.clID AND " +
                "SA.PC=S.PC AND SA.house_num=S.house_num AND " +
                "RA.PC=R.PC AND RA.house_num=R.house_num AND " +
                "P.dID=D.dID AND " +
                "D.dID = " + dID;
//        try {
//            ResultSet rs = stmt.executeQuery(sql);
//            rs.next();
//            System.out.println("dID: " + rs.getInt("D.dID") + ", status: " + rs.getString("D.status")
//            + ", sender: "+rs.getString("S.fname")+" "+rs.getString("S.lname")+ " at: "+rs.getString("SA.city")
//            + ", receiver: "+rs.getString("R.fname")+" "+rs.getString("R.lname")+ " at: "+rs.getString("RA.city"));
//            panel.setLayout(new MigLayout());
//            panel.add(new JLabel("Delivery ID: "), "align label");
//            panel.add(new JLabel(rs.getString("D.dID")), "wrap");
//            panel.add(new JLabel("Delivery type: "), "align label");
//            panel.add(new JLabel(rs.getString("D.type")), "wrap");
//            panel.add(new JLabel("Status: "), "align label");
//            panel.add(new JLabel(rs.getString("D.status")), "wrap");
//            panel.add(new JLabel(" "), "wrap"); // spacer
//            panel.add(new JLabel("Sender: "), "align label");
//            panel.add(new JLabel(rs.getString("S.fname")+" "+rs.getString("S.lname")), "wrap");
//            panel.add(new JLabel("Return address: "), "align label");
//            panel.add(new JLabel(rs.getString("SA.house_num")+" "+rs.getString("SA.street_name")+ ", "+
//                    rs.getString("SA.city")+ " "+rs.getString("SA.province")+", "+rs.getString("SA.country")), "wrap");
//            panel.add(new JLabel(" "), "wrap"); // spacer
//            panel.add(new JLabel("Receiver: "), "align label");
//            panel.add(new JLabel(rs.getString("R.fname")+" "+rs.getString("R.lname")), "wrap");
//            panel.add(new JLabel("Destination address: "), "align label");
//            panel.add(new JLabel(rs.getString("RA.house_num")+" "+rs.getString("RA.street_name")+ ", "+
//                    rs.getString("RA.city")+ " "+rs.getString("RA.province")+", "+rs.getString("RA.country")), "wrap");
//            panel.add(new JLabel(" "), "wrap"); // spacer
//            panel.add(new JLabel("Parcel weight: "), "align label");
//            panel.add(new JLabel(rs.getString("P.weight")+"kg"), "wrap");
//            panel.add(new JLabel("Parcel dimensions: "), "align label");
//            panel.add(new JLabel(rs.getString("P.length")+ "cm x "+
//                    rs.getString("P.width")+ "cm x "+rs.getString("P.height")+"cm"), "wrap");
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            panel.setLayout(new MigLayout());
//            panel.add(new JLabel("No results."), "align label");
//        }
        populateJPanel(panel, sql);
        return panel;
    }

    public JPanel getParcelQueryAsJPanel(int pID) {
//        DefaultTableModel model = new DefaultTableModel(new String[]{"Delivery ID", "Delivery type",
//        "Status", "Sender", "Return address", "Receiver", "Destination address", "Parcel weight",
//        "Parcel dimensions", "Payment"}, 0);
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(
                new EtchedBorder(), "Query result"));
        String sql = "SELECT DISTINCT * " +
                "FROM delivery D, clients S, address SA, clients R, address RA, parcel P " +
                "WHERE D.sender_ID=S.clID AND D.receiver_ID=R.clID AND " +
                "SA.PC=S.PC AND SA.house_num=S.house_num AND " +
                "RA.PC=R.PC AND RA.house_num=R.house_num AND " +
                "P.dID=D.dID AND " +
                "P.pID = " + pID;
        populateJPanel(panel, sql);
        return panel;
    }

    public ArrayList<Delivery> getSentDeliveries() {
        return sentDeliveries;
    }

    public ArrayList<Delivery> getReceivedDeliveries() {
        return receivedDeliveries;
    }

    // Returns list of Clients the client has sent a delivery to
    public ArrayList<Clients> getSenderClients() {
        return senderClients;
    }

    // Returns list of Clients the client has received a delivery from
    public ArrayList<Clients> getReceiverClients() {
        return receiverClients;
    }
}
