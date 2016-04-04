package controllerBeans;

import com.sun.rowset.JdbcRowSetImpl;
import entityClasses.DeliveryParcel;

import javax.sql.rowset.JdbcRowSet;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

/**
 * Created by stellafang. on 2016-03-31.
 */
public class DeliveryParcelBean {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/kiki's";
    static final String USER = "root";
    static final String PASS = "password";
//    static final String DB_URL = "jdbc:mysql://localhost/Kiki's_DeliveryService";
//    static final String USER = "root";
//    static final String PASS = "Iloveme711";
    private JdbcRowSet rowSet;

    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private DefaultTableModel model;
    private ResultSetMetaData rsmd;
    private int numcols, numrows;

    public DeliveryParcelBean() {
        try {
            Class.forName(JDBC_DRIVER);
            rowSet = new JdbcRowSetImpl();
            rowSet.setUrl(DB_URL);
            rowSet.setUsername(USER);
            rowSet.setPassword(PASS);
            rowSet.setCommand("SELECT * " +
                    "FROM delivery " +
                    "LEFT JOIN parcel " +
                    "ON delivery.dID=parcel.dID");
            rowSet.execute();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    public JTable makeTable(String sql) {
        model = new DefaultTableModel();
        try {
            rowSet.setCommand(sql);
            rowSet.execute();
            rsmd = rowSet.getMetaData();
            numcols = rsmd.getColumnCount();

            for (int colIndex = 1; colIndex <= numcols; colIndex++) {
                model.addColumn(rsmd.getColumnName(colIndex));
            }

            Object[] row = new Object[numcols];
            while (rowSet.next()) {
                for (int i=0; i<numcols; i++){
                    row[i] = rowSet.getObject(i+1);
                }
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new JTable(model);
    }

    public JTable Calculate(String sql) {
        model = new DefaultTableModel();
        try {
            rowSet.setCommand(sql);
            rowSet.execute();
            rsmd = rowSet.getMetaData();
            numcols = rsmd.getColumnCount();

            for (int colIndex = 1; colIndex <= numcols; colIndex++) {
                model.addColumn(rsmd.getColumnName(colIndex));
            }

            Object[] row = new Object[numcols];
            while (rowSet.next()) {
                for (int i=0; i<numcols; i++){
                    row[i] = rowSet.getObject(i+1);
                }
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new JTable(model);
    }

}
