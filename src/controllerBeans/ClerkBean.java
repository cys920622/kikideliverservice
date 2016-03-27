package controllerBeans;

import com.sun.rowset.JdbcRowSetImpl;

import javax.sql.rowset.JdbcRowSet;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import javax.xml.transform.Result;
import java.sql.*;

/**
 * Created by stellafang. on 2016-03-27.
 */
public class ClerkBean implements TableModel {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://127.0.01:3306/Kiki's_DeliveryService";
    static final String USER = "root";
    static final String PASS = "password";
    private ResultSet rs;
    private ResultSetMetaData rsmd;
    private int numcols, numrows;

    public ClerkBean(String sql) {
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to database...");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Creating statement...");
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //String sql;
            //sql = "Select * from address";
            rs = stmt.executeQuery(sql);
            rsmd = rs.getMetaData();
            numcols = rsmd.getColumnCount();
            rs.last();
            numrows = rs.getRow();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getResultSet() {
        return rs;
    }


    @Override
    public int getRowCount() {
        return numrows;
    }

    @Override
    public int getColumnCount() {
        return numcols;
    }

    @Override
    public String getColumnName(int columnIndex) {
        try {
            return rsmd.getColumnLabel(columnIndex+1);
        } catch (SQLException e) {
            return e.toString();
        }
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
}
