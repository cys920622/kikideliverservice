package controllerBeans;

import com.sun.rowset.JdbcRowSetImpl;
import entityClasses.Delivery;

import javax.sql.rowset.JdbcRowSet;
import java.sql.SQLException;

/**
 * Created by danielchoi on 2016-03-26.
 */
public class DeliveryBean {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/Kiki's_DeliveryService";
    static final String USER = "root";
    static final String PASS = "Iloveme711";
    private JdbcRowSet rowSet = null;

    public DeliveryBean() {
        try {
            Class.forName(JDBC_DRIVER);
            rowSet = new JdbcRowSetImpl();
            rowSet.setUrl(DB_URL);
            rowSet.setUsername(USER);
            rowSet.setPassword(PASS);
            rowSet.setCommand("select * from Address");
            rowSet.execute();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Delivery create (Delivery d) {
        try {
            rowSet.moveToInsertRow();
            rowSet.updateInt("dID", d.getdID());
            rowSet.updateString("type", d.getType());
            rowSet.updateString("status", d.getStatus());
            rowSet.updateInt("sender_ID", d.getSender_ID());
            rowSet.updateInt("receiver_ID", d.getReceiver_ID());
        } catch (SQLException e) {
            try {
                rowSet.rollback();
                d = null;
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return d;
    }

    public Delivery update (Delivery d) {
        try {
            rowSet.updateInt("dID", d.getdID());
            rowSet.updateString("type", d.getType());
            rowSet.updateString("status", d.getStatus());
            rowSet.updateInt("sender_ID", d.getSender_ID());
            rowSet.updateInt("receiver_ID", d.getReceiver_ID());
        } catch (SQLException e) {
            try {
                rowSet.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return d;
    }

    public void delete() {
        try {
            rowSet.moveToCurrentRow();
            rowSet.deleteRow();
        } catch (SQLException e) {
            try {
                rowSet.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public Delivery moveFirst() {
        Delivery d = new Delivery();
        try {
            rowSet.first();
            d.setdID(rowSet.getInt("dID"));
            d.setType(rowSet.getString("type"));
            d.setStatus(rowSet.getString("status"));
            d.setSender_ID(rowSet.getInt("sender_ID"));
            d.setReceiver_ID(rowSet.getInt("receiver_ID"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return d;
    }

    public Delivery moveLast() {
        Delivery d = new Delivery();
        try {
            rowSet.last();
            d.setdID(rowSet.getInt("dID"));
            d.setType(rowSet.getString("type"));
            d.setStatus(rowSet.getString("status"));
            d.setSender_ID(rowSet.getInt("sender_ID"));
            d.setReceiver_ID(rowSet.getInt("receiver_ID"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return d;
    }

    public Delivery moveNext() {
        Delivery d = new Delivery();
        try {
            if (!rowSet.next()) {
                rowSet.previous();
            }
            d.setdID(rowSet.getInt("dID"));
            d.setType(rowSet.getString("type"));
            d.setStatus(rowSet.getString("status"));
            d.setSender_ID(rowSet.getInt("sender_ID"));
            d.setReceiver_ID(rowSet.getInt("receiver_ID"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return d;
    }

    public Delivery movePrevious() {
        Delivery d = new Delivery();
        try {
            if (!rowSet.previous()) {
                rowSet.next();
            }
            d.setdID(rowSet.getInt("dID"));
            d.setType(rowSet.getString("type"));
            d.setStatus(rowSet.getString("status"));
            d.setSender_ID(rowSet.getInt("sender_ID"));
            d.setReceiver_ID(rowSet.getInt("receiver_ID"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return d;
    }
    public Delivery getCurrent() {
        Delivery d = new Delivery();
        try {
            rowSet.moveToCurrentRow();
            d.setdID(rowSet.getInt("dID"));
            d.setType(rowSet.getString("type"));
            d.setStatus(rowSet.getString("status"));
            d.setSender_ID(rowSet.getInt("sender_ID"));
            d.setReceiver_ID(rowSet.getInt("receiver_ID"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return d;
    }
}
