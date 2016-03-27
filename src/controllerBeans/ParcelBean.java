package controllerBeans;

import com.sun.rowset.JdbcRowSetImpl;
import entityClasses.Address;
import entityClasses.Parcel;

import javax.sql.rowset.JdbcRowSet;
import java.sql.*;

/**
 * Created by stellafang. on 2016-03-26.
 */
public class ParcelBean {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/Kiki's_DeliveryService";
    static final String USER = "root";
    static final String PASS = "password";
    private JdbcRowSet rowSet = null;

    public ParcelBean() {
        try {
//            Class.forName(JDBC_DRIVER);
//            System.out.println("Connecting to database...");
//            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
//            System.out.println("Creating statement...");
//            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
//            String sql;
//            sql = "Select * from parcel";
//            ResultSet rs = stmt.executeQuery(sql);
//            rowSet = new JdbcRowSetImpl(rs);

            Class.forName(JDBC_DRIVER);
            rowSet = new JdbcRowSetImpl();
            rowSet.setUrl(DB_URL);
            rowSet.setUsername(USER);
            rowSet.setPassword(PASS);
            rowSet.setCommand("select * from parcel");
            rowSet.execute();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Parcel create (Parcel parcel) {
        try {
            rowSet.moveToInsertRow();
            rowSet.updateInt("pID", parcel.getpID());
            rowSet.updateFloat("width", parcel.getWidth());
            rowSet.updateFloat("length", parcel.getLength());
            rowSet.updateFloat("height", parcel.getHeight());
            rowSet.updateFloat("weight", parcel.getWeight());
            rowSet.updateInt("dID", parcel.getdID());
            rowSet.updateString("cID", parcel.getcID());
            rowSet.updateString("next_cID", parcel.getNextcID());
            rowSet.insertRow();
            rowSet.moveToCurrentRow();
        } catch (SQLException e) {
            try {
                rowSet.rollback();
                parcel = null;
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

            e.printStackTrace();
        }
        return parcel;
    }

    public Parcel update (Parcel parcel) {
        try {
            rowSet.updateInt("pID", parcel.getpID());
            rowSet.updateFloat("width", parcel.getWidth());
            rowSet.updateFloat("length", parcel.getLength());
            rowSet.updateFloat("height", parcel.getHeight());
            rowSet.updateFloat("weight", parcel.getWeight());
            rowSet.updateInt("dID", parcel.getdID());
            rowSet.updateString("cID", parcel.getcID());
            rowSet.updateString("next_cID", parcel.getNextcID());
            rowSet.updateRow();
            rowSet.moveToCurrentRow();
        } catch (SQLException e) {
            try {
                rowSet.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return parcel;
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

    public Parcel moveFirst() {
        Parcel parcel = new Parcel();
        try {
            rowSet.first();
            parcel.setpID(rowSet.getInt("pID"));
            parcel.setWidth(rowSet.getFloat("width"));
            parcel.setLength(rowSet.getFloat("length"));
            parcel.setHeight(rowSet.getFloat("height"));
            parcel.setWeight(rowSet.getInt("weight"));
            parcel.setdID(rowSet.getInt("dID"));
            parcel.setcID(rowSet.getString("cID"));
            parcel.setNextcID(rowSet.getString("next_cID"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return parcel;
    }

    public Parcel moveLast() {
        Parcel parcel = new Parcel();
        try {
            rowSet.last();
            parcel.setpID(rowSet.getInt("pID"));
            parcel.setWidth(rowSet.getFloat("width"));
            parcel.setLength(rowSet.getFloat("length"));
            parcel.setHeight(rowSet.getFloat("height"));
            parcel.setWeight(rowSet.getInt("weight"));
            parcel.setdID(rowSet.getInt("dID"));
            parcel.setcID(rowSet.getString("cID"));
            parcel.setNextcID(rowSet.getString("next_cID"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return parcel;
    }

    public Parcel moveNext() {
        Parcel parcel = new Parcel();
        try {
            if (!rowSet.next()) {
                rowSet.previous();
            }
            parcel.setpID(rowSet.getInt("pID"));
            parcel.setWidth(rowSet.getFloat("width"));
            parcel.setLength(rowSet.getFloat("length"));
            parcel.setHeight(rowSet.getFloat("height"));
            parcel.setWeight(rowSet.getInt("weight"));
            parcel.setdID(rowSet.getInt("dID"));
            parcel.setcID(rowSet.getString("cID"));
            parcel.setNextcID(rowSet.getString("next_cID"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return parcel;
    }

    public Parcel movePrevious() {
        Parcel parcel = new Parcel();
        try {
            if (!rowSet.previous()) {
                rowSet.next();
            }
            parcel.setpID(rowSet.getInt("pID"));
            parcel.setWidth(rowSet.getFloat("width"));
            parcel.setLength(rowSet.getFloat("length"));
            parcel.setHeight(rowSet.getFloat("height"));
            parcel.setWeight(rowSet.getInt("weight"));
            parcel.setdID(rowSet.getInt("dID"));
            parcel.setcID(rowSet.getString("cID"));
            parcel.setNextcID(rowSet.getString("next_cID"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return parcel;
    }

    public Parcel getCurrent() {
        Parcel parcel = new Parcel();
        try {
            rowSet.moveToCurrentRow();
            parcel.setpID(rowSet.getInt("pID"));
            parcel.setWidth(rowSet.getFloat("width"));
            parcel.setLength(rowSet.getFloat("length"));
            parcel.setHeight(rowSet.getFloat("height"));
            parcel.setWeight(rowSet.getInt("weight"));
            parcel.setdID(rowSet.getInt("dID"));
            parcel.setcID(rowSet.getString("cID"));
            parcel.setNextcID(rowSet.getString("next_cID"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return parcel;
    }



}
