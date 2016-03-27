package controllerBeans;

import com.sun.rowset.JdbcRowSetImpl;
import entityClasses.Center;
import javax.sql.rowset.JdbcRowSet;
import java.sql.SQLException;

/**
 * Created by chuchutrainn on 2016-03-27.
 */
public class CenterBean {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/Kiki's_DeliveryService";
    static final String USER = "root";
    static final String PASS = "Iloveme711";
    private JdbcRowSet rowSet = null;

    public CenterBean() {
        try {
            Class.forName(JDBC_DRIVER);
            rowSet = new JdbcRowSetImpl();
            rowSet.setUrl(DB_URL);
            rowSet.setUsername(USER);
            rowSet.setPassword(PASS);
            rowSet.setCommand("select * from center");
            rowSet.execute();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Center create (Center ctr) {
        try {
            rowSet.moveToInsertRow();
            rowSet.updateString("cID", ctr.getcID());
            rowSet.updateString("center_addr", ctr.getCenter_addr());
            rowSet.insertRow();
            rowSet.moveToCurrentRow();
        } catch (SQLException e) {
            try {
                rowSet.rollback();
                ctr = null;
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return ctr;
    }

    public Center update (Center ctr) {
        try {
            rowSet.updateString("cID", ctr.getcID());
            rowSet.updateString("center_addr", ctr.getCenter_addr());
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
        return ctr;
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

    public Center getCurrent() {
        Center ctr = new Center();
        try {
            rowSet.moveToCurrentRow();
            ctr.setcID(rowSet.getString("cID"));
            ctr.setCenter_addr(rowSet.getString("center_addr"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ctr;
    }

    public Center submit(String cID) {
        try {
            if rowSet.getString("cID") == cID {

            }
        }
    }
}
