package controllerBeans;

import com.sun.rowset.JdbcRowSetImpl;
import entityClasses.Cash;


import javax.sql.rowset.JdbcRowSet;
import java.sql.SQLException;

/**
 * Created by stellafang. on 2016-03-27.
 */
public class CashBean {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/kiki's";
    static final String USER = "root";
    static final String PASS = "password";
    private JdbcRowSet rowSet = null;

    public CashBean() {
        try {
            Class.forName(JDBC_DRIVER);
            rowSet = new JdbcRowSetImpl();
            rowSet.setUrl(DB_URL);
            rowSet.setUsername(USER);
            rowSet.setPassword(PASS);
            rowSet.setCommand("select * from cash");
            rowSet.execute();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Cash create (Cash cash) {
        try {
            rowSet.moveToInsertRow();
            rowSet.updateInt("payID", cash.getPayID());
            rowSet.updateFloat("amount", cash.getAmount());
            rowSet.updateString("onDate", cash.getOnDate());
            rowSet.updateFloat("dID", cash.getdID());
            rowSet.insertRow();
            rowSet.moveToCurrentRow();
        } catch (SQLException e) {
            try {
                rowSet.rollback();
                cash = null;
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

            e.printStackTrace();
        }
        return cash;
    }

    public Cash update (Cash cash) {
        try {
            rowSet.updateInt("pay ID", cash.getPayID());
            rowSet.updateFloat("amount", cash.getAmount());
            rowSet.updateString("onDate", cash.getOnDate());
            rowSet.updateFloat("dID", cash.getdID());
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
        return cash;
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

    public Cash moveFirst() {
        Cash cash = new Cash();
        try {
            rowSet.first();
            cash.setAmount(rowSet.getFloat("amount"));
            cash.setPayID(rowSet.getInt("payID"));
            cash.setOnDate(rowSet.getString("onDate"));
            cash.setdID(rowSet.getInt("dID"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cash;
    }

    public Cash moveLast() {
        Cash cash = new Cash();
        try {
            rowSet.last();
            cash.setAmount(rowSet.getFloat("amount"));
            cash.setPayID(rowSet.getInt("payID"));
            cash.setOnDate(rowSet.getString("onDate"));
            cash.setdID(rowSet.getInt("dID"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cash;
    }

    public Cash moveNext() {
        Cash cash = new Cash();
        try {
            if (!rowSet.next()) {
                rowSet.previous();
            }
            cash.setAmount(rowSet.getFloat("amount"));
            cash.setPayID(rowSet.getInt("payID"));
            cash.setOnDate(rowSet.getString("onDate"));
            cash.setdID(rowSet.getInt("dID"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cash;
    }

    public Cash movePrevious() {
        Cash cash = new Cash();
        try {
            if (!rowSet.previous()) {
                rowSet.next();
            }
            cash.setAmount(rowSet.getFloat("amount"));
            cash.setPayID(rowSet.getInt("payID"));
            cash.setOnDate(rowSet.getString("onDate"));
            cash.setdID(rowSet.getInt("dID"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cash;
    }

    public Cash getCurrent() {
        Cash cash = new Cash();
        try {
            rowSet.moveToCurrentRow();
            cash.setAmount(rowSet.getFloat("amount"));
            cash.setPayID(rowSet.getInt("payID"));
            cash.setOnDate(rowSet.getString("onDate"));
            cash.setdID(rowSet.getInt("dID"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cash;
    }

}
