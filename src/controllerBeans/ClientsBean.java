package controllerBeans;

import com.sun.rowset.JdbcRowSetImpl;
import entityClasses.Clients;
import javax.sql.rowset.JdbcRowSet;
import java.sql.SQLException;

/**
 * Created by chuchutrainn on 2016-03-26.
 */
public class ClientsBean {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/Kiki's_DeliveryService";
    static final String USER = "root";
    static final String PASS = "password";
    private JdbcRowSet rowSet = null;

    public ClientsBean() {
        try {
            Class.forName(JDBC_DRIVER);
            rowSet = new JdbcRowSetImpl();
            rowSet.setUrl(DB_URL);
            rowSet.setUsername(USER);
            rowSet.setPassword(PASS);
            rowSet.setCommand("select * from Clients");
            rowSet.execute();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Clients create (Clients cl) {
        try {
            rowSet.moveToInsertRow();
            rowSet.updateInt("clID", cl.getClID());
            rowSet.updateString("fname", cl.getFname());
            rowSet.updateString("lname", cl.getLname());
            rowSet.updateString("PC", cl.getPC());
            rowSet.updateInt("house_num", cl.getHouse_num());
            rowSet.updateString("phone_num", cl.getPhone_num());
            rowSet.insertRow();
            rowSet.moveToCurrentRow();
        } catch (SQLException e) {
            try {
                rowSet.rollback();
                cl = null;
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

            e.printStackTrace();
        }
        return cl;
    }

    public Clients update (Clients cl) {
        try {
            rowSet.updateInt("clID", cl.getClID());
            rowSet.updateString("fname", cl.getFname());
            rowSet.updateString("lname", cl.getLname());
            rowSet.updateString("PC", cl.getPC());
            rowSet.updateInt("house_num", cl.getHouse_num());
            rowSet.updateString("phone_num", cl.getPhone_num());
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
        return cl;
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

    public Clients moveFirst() {
        Clients cl = new Clients();
        try {
            rowSet.first();
            cl.setClID(rowSet.getInt("clID"));
            cl.setFname(rowSet.getString("fname"));
            cl.setLname(rowSet.getString("lname"));
            cl.setPC(rowSet.getString("PC"));
            cl.setHouse_num(rowSet.getInt("house_num"));
            cl.setPhone_num(rowSet.getString("phone_num"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cl;
    }

    public Clients moveLast() {
        Clients cl = new Clients();
        try {
            rowSet.last();
            cl.setClID(rowSet.getInt("clID"));
            cl.setFname(rowSet.getString("fname"));
            cl.setLname(rowSet.getString("lname"));
            cl.setPC(rowSet.getString("PC"));
            cl.setHouse_num(rowSet.getInt("house_num"));
            cl.setPhone_num(rowSet.getString("phone_num"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cl;
    }

    public Clients moveNext() {
        Clients cl = new Clients();
        try {
            if (!rowSet.next()) {
                rowSet.previous();
            }
            rowSet.next();
            cl.setClID(rowSet.getInt("clID"));
            cl.setFname(rowSet.getString("fname"));
            cl.setLname(rowSet.getString("lname"));
            cl.setPC(rowSet.getString("PC"));
            cl.setHouse_num(rowSet.getInt("house_num"));
            cl.setPhone_num(rowSet.getString("phone_num"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cl;
    }

    public Clients movePrevious() {
        Clients cl = new Clients();
        try {
            if (!rowSet.previous()) {
                rowSet.next();
            }
            rowSet.previous();
            cl.setClID(rowSet.getInt("clID"));
            cl.setFname(rowSet.getString("fname"));
            cl.setLname(rowSet.getString("lname"));
            cl.setPC(rowSet.getString("PC"));
            cl.setHouse_num(rowSet.getInt("house_num"));
            cl.setPhone_num(rowSet.getString("phone_num"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cl;
    }

    public Clients getCurrent() {
        Clients cl = new Clients();
        try {
            rowSet.moveToCurrentRow();
            cl.setClID(rowSet.getInt("clID"));
            cl.setFname(rowSet.getString("fname"));
            cl.setLname(rowSet.getString("lname"));
            cl.setPC(rowSet.getString("PC"));
            cl.setHouse_num(rowSet.getInt("house_num"));
            cl.setPhone_num(rowSet.getString("phone_num"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cl;
    }
}