package controllerBeans;

import com.sun.rowset.JdbcRowSetImpl;
import entityClasses.Center;
import entityClasses.Parcel;
import javax.sql.rowset.JdbcRowSet;
import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.*;
import java.sql.SQLException;

/**
 * Created by chuchutrainn on 2016-03-27.
 */
public class CenterBean implements TableModel{
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/Kiki's_DeliveryService";
    static final String USER = "root";
    static final String PASS = "password";
    private JdbcRowSet rowSet = null;

    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private ResultSetMetaData rsmd;
    private  int ncols, nrows;

    public CenterBean() {
        try {
            Class.forName(JDBC_DRIVER);
//            rowSet = new JdbcRowSetImpl();
//            rowSet.setUrl(DB_URL);
//            rowSet.setUsername(USER);
//            rowSet.setPassword(PASS);
//            rowSet.setCommand("select * from center" +
//            "order by first asc");
//            rowSet.execute();

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            //String sql = "SELECT cID, center_addr, pid FROM Center natural join Parcel ORDER BY cID";
            //rs = stmt.executeQuery(sql);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public JTable makeTable(String sql) {
        DefaultTableModel model = new DefaultTableModel();
        try {
            rs = stmt.executeQuery(sql);
            rsmd = rs.getMetaData();
            ncols = rsmd.getColumnCount();
//            nrows = rsmd.get
            for (int colIndex = 1; colIndex <= ncols; colIndex++) {
                model.addColumn(rsmd.getColumnName(colIndex));
            }

            Object[] row = new Object[ncols];
            while (rs.next()) {
                for (int i=0; i<ncols; i++){
                    row[i] = rs.getObject(i+1);
                }
                model.addRow(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new JTable(model);

    }

    public Center create(Center ctr) {
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

    @Override
    public int getRowCount() {
        return 0;
    }

    @Override
    public int getColumnCount() {
        return 0;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            rs.absolute(rowIndex+1);                // Go to the specified row
            Object o = rs.getObject(columnIndex+1); // Get value of the column
            if (o == null) return null;
            else return o.toString();               // Convert it to a string
        } catch (SQLException e) { return e.toString(); }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }



    public Center update(Center ctr) {
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

    public void submit(Integer did) {
        try {
            if (rowSet.getInt("dID") == did) {
                stmt.executeUpdate("UPDATE delivery SET status = 'arrived' where dID = '"
                + did + "'");

            }
        } catch (SQLException e) {
            try {
                rowSet.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}

