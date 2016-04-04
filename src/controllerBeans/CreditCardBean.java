package controllerBeans;

import com.sun.rowset.JdbcRowSetImpl;
import entityClasses.Cash;
import entityClasses.CreditCard;

import javax.sql.rowset.JdbcRowSet;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * Created by stellafang. on 2016-03-27.
 */
public class CreditCardBean {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/kiki's";
    static final String USER = "root";
    static final String PASS = "password";
//    static final String DB_URL = "jdbc:mysql://localhost/Kiki's_DeliveryService";
//    static final String USER = "root";
//    static final String PASS = "Iloveme711";
    private JdbcRowSet rowSet = null;
    private DefaultTableModel model;
    private ResultSetMetaData rsmd;
    private int numcols, numrows;

    public CreditCardBean() {
        try {
            Class.forName(JDBC_DRIVER);
            rowSet = new JdbcRowSetImpl();
            rowSet.setUrl(DB_URL);
            rowSet.setUsername(USER);
            rowSet.setPassword(PASS);
            rowSet.setCommand("select * from credit_card");
            rowSet.execute();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public CreditCard create (CreditCard cc) {
        try {
            rowSet.moveToInsertRow();
            rowSet.updateInt("payID", cc.getPayID());
            rowSet.updateFloat("amount", cc.getAmount());
            rowSet.updateString("onDate", cc.getOnDate());
            rowSet.updateInt("dID", cc.getdID());
            rowSet.updateString("credit_card_num", cc.getCredit_card_num());
            rowSet.updateInt("CSV", cc.getCSV());
            rowSet.updateString("name", cc.getName());
            rowSet.updateString("expiry_date", cc.getExpiry_date());
            rowSet.updateString("type", cc.getType());
            rowSet.insertRow();
            rowSet.moveToCurrentRow();
        } catch (SQLException e) {
            try {
                rowSet.rollback();
                cc = null;
            } catch (SQLException e1) {
                e1.printStackTrace();
                return null;
            }
            e.printStackTrace();
        }
        return cc;
    }

    public CreditCard update (CreditCard cc) {
        try {
            rowSet.updateInt("payID", cc.getPayID());
            rowSet.updateFloat("amount", cc.getAmount());
            rowSet.updateString("onDate", cc.getOnDate());
            rowSet.updateInt("dID", cc.getdID());
            rowSet.updateString("credit_card_num", cc.getCredit_card_num());
            rowSet.updateInt("CSV", cc.getCSV());
            rowSet.updateString("name", cc.getName());
            rowSet.updateString("expiry_date", cc.getExpiry_date());
            rowSet.updateString("type", cc.getType());
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
        return cc;
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

    public CreditCard moveFirst() {
        CreditCard cc = new CreditCard();
        try {
            rowSet.first();
            cc.setAmount(rowSet.getFloat("amount"));
            cc.setPayID(rowSet.getInt("payID"));
            cc.setOnDate(rowSet.getString("onDate"));
            cc.setdID(rowSet.getInt("dID"));
            cc.setCredit_card_num(rowSet.getString("credit_card_num"));
            cc.setCSV(rowSet.getInt("CSV"));
            cc.setName(rowSet.getString("name"));
            cc.setExpiry_date(rowSet.getString("expiry_date"));
            cc.setType(rowSet.getString("type"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cc;
    }

    public CreditCard moveLast() {
        CreditCard cc = new CreditCard();
        try {
            rowSet.last();
            cc.setAmount(rowSet.getFloat("amount"));
            cc.setPayID(rowSet.getInt("payID"));
            cc.setOnDate(rowSet.getString("onDate"));
            cc.setdID(rowSet.getInt("dID"));
            cc.setCredit_card_num(rowSet.getString("credit_card_num"));
            cc.setCSV(rowSet.getInt("CSV"));
            cc.setName(rowSet.getString("name"));
            cc.setExpiry_date(rowSet.getString("expiry_date"));
            cc.setType(rowSet.getString("type"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cc;
    }

    public CreditCard moveNext() {
        CreditCard cc = new CreditCard();
        try {
            if (!rowSet.next()) {
                rowSet.previous();
            }
            cc.setAmount(rowSet.getFloat("amount"));
            cc.setPayID(rowSet.getInt("payID"));
            cc.setOnDate(rowSet.getString("onDate"));
            cc.setdID(rowSet.getInt("dID"));
            cc.setCredit_card_num(rowSet.getString("credit_card_num"));
            cc.setCSV(rowSet.getInt("CSV"));
            cc.setName(rowSet.getString("name"));
            cc.setExpiry_date(rowSet.getString("expiry_date"));
            cc.setType(rowSet.getString("type"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cc;
    }

    public CreditCard movePrevious() {
        CreditCard cc = new CreditCard();
        try {
            if (!rowSet.previous()) {
                rowSet.next();
            }
            cc.setAmount(rowSet.getFloat("amount"));
            cc.setPayID(rowSet.getInt("payID"));
            cc.setOnDate(rowSet.getString("onDate"));
            cc.setdID(rowSet.getInt("dID"));
            cc.setCredit_card_num(rowSet.getString("credit_card_num"));
            cc.setCSV(rowSet.getInt("CSV"));
            cc.setName(rowSet.getString("name"));
            cc.setExpiry_date(rowSet.getString("expiry_date"));
            cc.setType(rowSet.getString("type"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cc;
    }

    public CreditCard getCurrent() {
        CreditCard cc = new CreditCard();
        try {
            rowSet.moveToCurrentRow();
            cc.setAmount(rowSet.getFloat("amount"));
            cc.setPayID(rowSet.getInt("payID"));
            cc.setOnDate(rowSet.getString("onDate"));
            cc.setdID(rowSet.getInt("dID"));
            cc.setCredit_card_num(rowSet.getString("credit_card_num"));
            cc.setCSV(rowSet.getInt("CSV"));
            cc.setName(rowSet.getString("name"));
            cc.setExpiry_date(rowSet.getString("expiry_date"));
            cc.setType(rowSet.getString("type"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cc;
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

}
