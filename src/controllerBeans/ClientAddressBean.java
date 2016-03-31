package controllerBeans;

import com.sun.rowset.JdbcRowSetImpl;
import entityClasses.ClientAddress;
import entityClasses.Clients;

import javax.sql.rowset.JdbcRowSet;
import java.sql.*;

/**
 * Created by stellafang. on 2016-03-29.
 */
public class ClientAddressBean {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/kiki's";
    static final String USER = "root";
    static final String PASS = "password";
    private JdbcRowSet rowSet = null;

    private Connection conn;
    private Statement stmt;
    private ResultSet rs;


    public ClientAddressBean() {
        try {
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_SCROLL_SENSITIVE);
            rs = stmt.executeQuery("SELECT * " +
                    "FROM clients " +
                    "LEFT JOIN address " +
                    "ON clients.PC=address.PC " +
                    "and clients.house_num=address.house_num");

//
//            rowSet = new JdbcRowSetImpl();
//            rowSet.setUrl(DB_URL);
//            rowSet.setUsername(USER);
//            rowSet.setPassword(PASS);
//            rowSet.setCommand("SELECT * " +
//                    "FROM clients " +
//                    "LEFT JOIN address " +
//                    "ON clients.PC=address.PC " +
//                    "and clients.house_num=address.house_num");
//            rowSet.execute();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public ClientAddress create (ClientAddress ca) {
        try {
            stmt.executeUpdate("INSERT INTO address " +
                    "(country, province, city, street_name, house_num, PC)" +
                    "VALUES ('"+ ca.getCountry()+ "', '" +ca.getProvince()+ "', '"+ ca.getCity() + "', '"
                    + ca.getStreet_name() + "', '"+ ca.getHouse_num()+ "', '"+ ca.getPC() +"' )");

            stmt.executeUpdate("INSERT INTO clients " +
                    "(clID, fname, lname, PC, house_num, phone_num)" +
                    "VALUES ('"+ ca.getClID()+ "', '" +ca.getFname()+ "', '"+ ca.getLname() + "', '"
                    + ca.getPC() + "', '"+ ca.getHouse_num()+ "', '"+ ca.getPhone_num() +"' )");


//            rowSet.moveToInsertRow();
//            rowSet.updateInt("clID", ca.getClID());
//            rowSet.updateString("fname", ca.getFname());
//            rowSet.updateString("lname", ca.getLname());
//            rowSet.updateString("PC", ca.getPC());
//            rowSet.updateInt("house_num", ca.getHouse_num());
//            rowSet.updateString("phone_num", ca.getPhone_num());
//            rowSet.updateString("country", ca.getCountry());
//            rowSet.updateString("province", ca.getProvince());
//            rowSet.updateString("city", ca.getCity());
//            rowSet.updateString("street_name", ca.getStreet_name());
//            rowSet.insertRow();
//            rowSet.moveToCurrentRow();
        } catch (SQLException e) {
            try {
                rowSet.rollback();
                ca = null;
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

            e.printStackTrace();
        }
        return ca;
    }

    public ClientAddress update(ClientAddress ca) {
        try {

            stmt.executeUpdate("UPDATE clients " +
                    "SET clID='" +ca.getClID()+ "'" +
                    ",fname='" +ca.getFname()+"'" +
                    ",lname='" +ca.getLname()+"'" +
                    ",PC='" +ca.getPC()+"'" +
                    ",house_num='" +ca.getHouse_num()+"'" +
                    ",phone_num='"+ca.getPhone_num()+"'" +
                    "WHERE clID='"+ca.getClID()+"'");
            stmt.executeUpdate("UPDATE address " +
                    "SET PC='" +ca.getPC()+"'" +
                    ",house_num='"+ca.getHouse_num()+"'" +
                    ",country='"+ca.getCountry()+"'" +
                    ",province='"+ca.getProvince()+"'" +
                    ",city='"+ca.getCity()+"'" +
                    ",street_name='"+ca.getStreet_name()+"'" +
                    "WHERE PC='"+ca.getPC()+"'" +
                    " and house_num='"+ca.getHouse_num()+"'");
            //rs.updateRow();

//            rs.updateInt("clID", ca.getClID());
//            rs.updateString("fname", ca.getFname());
//            rs.updateString("lname", ca.getLname());
//            rs.updateString("PC", ca.getPC());
//            rs.updateInt("house_num", ca.getHouse_num());
//            rs.updateString("phone_num", ca.getPhone_num());
//            rs.updateString("country", ca.getCountry());
//            rs.updateString("province", ca.getProvince());
//            rs.updateString("city", ca.getCity());
//            rs.updateString("street_name", ca.getStreet_name());
//            rs.updateRow();
//            rs.moveToCurrentRow();


//            rowSet.updateInt("clID", ca.getClID());
//            rowSet.updateString("fname", ca.getFname());
//            rowSet.updateString("lname", ca.getLname());
//            rowSet.updateString("PC", ca.getPC());
//            rowSet.updateInt("house_num", ca.getHouse_num());
//            rowSet.updateString("phone_num", ca.getPhone_num());
//            rowSet.updateString("country", ca.getCountry());
//            rowSet.updateString("province", ca.getProvince());
//            rowSet.updateString("city", ca.getCity());
//            rowSet.updateString("street_name", ca.getStreet_name());
//            rowSet.updateRow();
//            rowSet.moveToCurrentRow();
        } catch (SQLException e) {
            try {
                rowSet.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return ca;
    }

    public boolean delete(ClientAddress ca) {

        try {
            stmt.executeUpdate("DELETE FROM address " +
                    "WHERE PC='"+ca.getPC()+"' and house_num='"+ca.getHouse_num()+"'");
            stmt.executeUpdate("DELETE FROM clients " +
                    "WHERE clID = '"+ca.getClID()+ "' ");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }


    }

    public ClientAddress moveFirst() {
        ClientAddress ca = new ClientAddress();
        try {
            rs.first();
            ca.setClID(rs.getInt("clID"));
            ca.setFname(rs.getString("fname"));
            ca.setLname(rs.getString("lname"));
            ca.setPC(rs.getString("PC"));
            ca.setHouse_num(rs.getInt("house_num"));
            ca.setPhone_num(rs.getString("phone_num"));
            ca.setCountry(rs.getString("country"));
            ca.setProvince(rs.getString("province"));
            ca.setCity(rs.getString("city"));
            ca.setStreet_name(rs.getString("street_name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ca;
    }

    public ClientAddress moveLast() {
        ClientAddress ca = new ClientAddress();
        try {
            rs.last();
            ca.setClID(rs.getInt("clID"));
            ca.setFname(rs.getString("fname"));
            ca.setLname(rs.getString("lname"));
            ca.setPC(rs.getString("PC"));
            ca.setHouse_num(rs.getInt("house_num"));
            ca.setPhone_num(rs.getString("phone_num"));
            ca.setCountry(rs.getString("country"));
            ca.setProvince(rs.getString("province"));
            ca.setCity(rs.getString("city"));
            ca.setStreet_name(rs.getString("street_name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ca;
    }

    public ClientAddress moveNext() {
        ClientAddress ca = new ClientAddress();
        try {
            if (!rs.next()) {
                rs.previous();
            }
            ca.setClID(rs.getInt("clID"));
            ca.setFname(rs.getString("fname"));
            ca.setLname(rs.getString("lname"));
            ca.setPC(rs.getString("PC"));
            ca.setHouse_num(rs.getInt("house_num"));
            ca.setPhone_num(rs.getString("phone_num"));
            ca.setCountry(rs.getString("country"));
            ca.setProvince(rs.getString("province"));
            ca.setCity(rs.getString("city"));
            ca.setStreet_name(rs.getString("street_name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ca;
    }

    public ClientAddress movePrevious() {
        ClientAddress ca = new ClientAddress();
        try {
            if (!rs.previous()) {
                rs.next();
            }
            rs.previous();
            ca.setCountry(rs.getString("country"));
            ca.setProvince(rs.getString("province"));
            ca.setCity(rs.getString("city"));
            ca.setStreet_name(rs.getString("street_name"));
            ca.setClID(rs.getInt("clID"));
            ca.setFname(rs.getString("fname"));
            ca.setLname(rs.getString("lname"));
            ca.setPC(rs.getString("PC"));
            ca.setHouse_num(rs.getInt("house_num"));
            ca.setPhone_num(rs.getString("phone_num"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ca;
    }

    public ClientAddress getCurrent() {
        ClientAddress ca = new ClientAddress();
        try {
            rs.moveToCurrentRow();
            ca.setClID(rs.getInt("clID"));
            ca.setFname(rs.getString("fname"));
            ca.setLname(rs.getString("lname"));
            ca.setPC(rs.getString("PC"));
            ca.setHouse_num(rs.getInt("house_num"));
            ca.setPhone_num(rs.getString("phone_num"));
            ca.setCountry(rs.getString("country"));
            ca.setProvince(rs.getString("province"));
            ca.setCity(rs.getString("city"));
            ca.setStreet_name(rs.getString("street_name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ca;
    }

}


