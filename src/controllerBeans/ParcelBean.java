package controllerBeans;

import com.sun.rowset.JdbcRowSetImpl;
import entityClasses.Address;
import entityClasses.Center;
import entityClasses.Parcel;

import javax.sql.rowset.JdbcRowSet;
import java.sql.*;

/**
 * Created by stellafang. on 2016-03-26.
 */
public class ParcelBean {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/Kiki's_DeliveryService";
    static final String USER = "root";
    static final String PASS = "Iloveme711";
    private JdbcRowSet rowSet = null;
    private ResultSet rsCenter = null;

    private Connection conn;
    private Statement stmt;
    private ResultSet rs;


    public ParcelBean(String sql) {
        try {
            Class.forName(JDBC_DRIVER);
            rowSet = new JdbcRowSetImpl();
            rowSet.setUrl(DB_URL);
            rowSet.setUsername(USER);
            rowSet.setPassword(PASS);
            rowSet.setCommand(sql);
            //rowSet.setCommand("select * from parcel");
            rowSet.execute();

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            rsCenter = stmt.executeQuery("select * from center");

//            rowSetCenter = new JdbcRowSetImpl();
//            rowSetCenter.setUrl(DB_URL);
//            rowSetCenter.setUsername(USER);
//            rowSetCenter.setPassword(PASS);
//            rowSetCenter.setCommand("select * from center");
//            rowSetCenter.execute();

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Parcel create(Parcel parcel, String centerMatch, String nextCenterMatch) {
        try {
            Boolean isValidCenter = false;
            Boolean isValidNextCenter = false;
            while (rsCenter.next() && (!isValidCenter || !isValidNextCenter) ) {
                if (centerMatch.equals(rsCenter.getString("cID"))) {
                    isValidCenter = true;
                }
                if (nextCenterMatch.equals(rsCenter.getString("cID"))) {
                    isValidNextCenter = true;
                }
            }
            if (isValidCenter && isValidNextCenter) {
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
            } else return null;

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

    public Parcel update(Parcel parcel, String centerMatch, String nextCenterMatch) {
        try {
            Boolean isValidCenter = false;
            Boolean isValidNextCenter = false;
            while (rsCenter.next() && (!isValidCenter || !isValidNextCenter) ) {
                if (centerMatch.equals(rsCenter.getString("cID"))) {
                    isValidCenter = true;
                }
                if (nextCenterMatch.equals(rsCenter.getString("cID"))) {
                    isValidNextCenter = true;
                }
            }
            if (isValidCenter && isValidNextCenter) {
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
            } else return null;
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

    public void submit(Integer did) {
        try {
//            if (rowSet.getInt("dID") == did) {
            stmt.executeUpdate("UPDATE delivery " +
                    "SET status = 'arrived' where dID = '"
                    + did + "'");


        } catch (SQLException e) {

//                rowSet.rollback();
//            } catch (SQLException e1) {
//                e.printStackTrace();
//            }
                e.printStackTrace();

        }
    }
}