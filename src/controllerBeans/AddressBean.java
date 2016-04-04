package controllerBeans;

import com.sun.rowset.JdbcRowSetImpl;
import entityClasses.Address;
import views.AddressUI;

import javax.sql.rowset.JdbcRowSet;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.transform.Result;
import java.sql.*;

/**
 * Created by danielchoi on 2016-03-25.
 */
public class AddressBean {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
//    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/kiki's";
//    static final String USER = "root";
//    static final String PASS = "password";
    static final String DB_URL = "jdbc:mysql://localhost/Kiki's_DeliveryService";
    static final String USER = "root";
    static final String PASS = "Iloveme711";
    private JdbcRowSet rowSet = null;

    private Connection conn;
    private Statement stmt;
    private ResultSet rs;

    public AddressBean() {
        try {
            Class.forName(JDBC_DRIVER);
            rowSet = new JdbcRowSetImpl();
            rowSet.setUrl(DB_URL);
            rowSet.setUsername(USER);
            rowSet.setPassword(PASS);
            rowSet.setCommand("Select * from address");
            rowSet.execute();

//            Class.forName(JDBC_DRIVER);
//            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Creating statement...");
//            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt = conn.createStatement();
            String sql = "Select * from address";
            rs = stmt.executeQuery(sql);
//            rowSet = new JdbcRowSetImpl(rs);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public JTable makeTable () {
        DefaultTableModel model = new DefaultTableModel(new String[]{"Country", "Province",
        "City", "Street name", "Postal code"}, 0);
        try {
            while (rs.next()) {
                String c = rs.getString("country");
                String p = rs.getString("province");
                String ci = rs.getString("city");
                String sn = rs.getString("street_name");
                String pc = rs.getString("PC");
                model.addRow(new Object[]{c,p,ci,sn,pc});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new JTable(model);
    }

    public Address create (Address addr) {
        try {
            rowSet.moveToInsertRow();
            rowSet.updateString("country", addr.getCountry());
            rowSet.updateString("province", addr.getProvince());
            rowSet.updateString("city", addr.getCity());
            rowSet.updateString("street_name", addr.getStreet_name());
            rowSet.updateInt("house_num", addr.getHouse_num());
            rowSet.updateString("PC", addr.getPC());
            rowSet.insertRow();
            rowSet.moveToCurrentRow();
        } catch (SQLException e) {
            try {
                rowSet.rollback();
                addr = null;
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return addr;
    }

    public Address update (Address addr) {
        try {
            rowSet.updateString("country", addr.getCountry());
            rowSet.updateString("province", addr.getProvince());
            rowSet.updateString("city", addr.getCity());
            rowSet.updateString("street_name", addr.getStreet_name());
            rowSet.updateInt("house_num", addr.getHouse_num());
            rowSet.updateString("PC", addr.getPC());
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
        return addr;
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

    public Address moveFirst() {
        Address addr = new Address();
        try {
            rowSet.first();
            addr.setCountry(rowSet.getString("country"));
            addr.setProvince(rowSet.getString("province"));
            addr.setCity(rowSet.getString("city"));
            addr.setStreet_name(rowSet.getString("street_name"));
            addr.setHouse_num(rowSet.getInt("house_num"));
            addr.setPC(rowSet.getString("PC"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addr;
    }

    public Address moveLast() {
        Address addr = new Address();
        try {
            rowSet.last();
            addr.setCountry(rowSet.getString("country"));
            addr.setProvince(rowSet.getString("province"));
            addr.setCity(rowSet.getString("city"));
            addr.setStreet_name(rowSet.getString("street_name"));
            addr.setHouse_num(rowSet.getInt("house_num"));
            addr.setPC(rowSet.getString("PC"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addr;
    }

    public Address moveNext() {
        Address addr = new Address();
        try {
            if (!rowSet.next()) {
                rowSet.previous();
            }
            addr.setCountry(rowSet.getString("country"));
            addr.setProvince(rowSet.getString("province"));
            addr.setCity(rowSet.getString("city"));
            addr.setStreet_name(rowSet.getString("street_name"));
            addr.setHouse_num(rowSet.getInt("house_num"));
            addr.setPC(rowSet.getString("PC"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addr;
    }

    public Address movePrevious() {
        Address addr = new Address();
        try {
            if (!rowSet.previous()) {
                rowSet.next();
            }
            addr.setCountry(rowSet.getString("country"));
            addr.setProvince(rowSet.getString("province"));
            addr.setCity(rowSet.getString("city"));
            addr.setStreet_name(rowSet.getString("street_name"));
            addr.setHouse_num(rowSet.getInt("house_num"));
            addr.setPC(rowSet.getString("PC"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addr;
    }

    public Address getCurrent() {
        Address addr = new Address();
        try {
            rowSet.moveToCurrentRow();
            addr.setCountry(rowSet.getString("country"));
            addr.setProvince(rowSet.getString("province"));
            addr.setCity(rowSet.getString("city"));
            addr.setStreet_name(rowSet.getString("street_name"));
            addr.setHouse_num(rowSet.getInt("house_num"));
            addr.setPC(rowSet.getString("PC"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addr;
    }
}
