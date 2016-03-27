package controllerBeans;

import com.sun.rowset.JdbcRowSetImpl;
import entityClasses.Parcel;

import javax.sql.rowset.JdbcRowSet;
import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.Vector;

/**
 * Created by stellafang. on 2016-03-27.
 */
public class ClerkBean implements TableModel {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/Kiki's_DeliveryService";
    static final String USER = "root";
    static final String PASS = "password";

    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private ResultSetMetaData rsmd;
    private int numcols, numrows;

    public ClerkBean() {
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Creating statement...");
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //String sql = "Select * from address";
            //rs = stmt.executeQuery(sql);
            //rs.last();
            //numrows = rs.getRow();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public JTable makeTable(String sql) {

        DefaultTableModel model = new DefaultTableModel();
        try {
            rs = stmt.executeQuery(sql);
            rsmd = rs.getMetaData();
            numcols = rsmd.getColumnCount();

            for (int colIndex = 1; colIndex <= numcols; colIndex++) {
                model.addColumn(rsmd.getColumnName(colIndex));
            }

            Object[] row = new Object[numcols];
            while (rs.next()) {
                for (int i=0; i<numcols; i++){
                    row[i] = rs.getObject(i+1);
                }
                model.addRow(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new JTable(model);

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
