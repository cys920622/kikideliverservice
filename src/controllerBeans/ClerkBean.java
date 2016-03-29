package controllerBeans;

import com.sun.rowset.JdbcRowSetImpl;
import com.sun.tools.javac.util.Name;
import entityClasses.Clients;
import entityClasses.Parcel;
import sun.misc.Cache;
import views.ClerkHomeUI;
import views.ClientsUI;

import javax.sql.RowSetEvent;
import javax.sql.RowSetListener;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JdbcRowSet;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.Vector;

/**
 * Created by stellafang. on 2016-03-27.
 */
public class ClerkBean extends AbstractTableModel implements TableModelListener, RowSetListener{
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/kiki's";
    static final String USER = "root";
    static final String PASS = "password";

    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private ResultSetMetaData rsmd;
    private int numcols, numrows;
    private DefaultTableModel model;

    private JdbcRowSet rowSet;


    public ClerkBean() {
        try {
//            Class.forName(JDBC_DRIVER);
//            System.out.println("Connecting to database...");
//            conn = DriverManager.getConnection(DB_URL, USER, PASS);
//            System.out.println("Creating statement...");
//            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);


            Class.forName(JDBC_DRIVER);
            rowSet = new JdbcRowSetImpl();
            rowSet.setUrl(DB_URL);
            rowSet.setUsername(USER);
            rowSet.setPassword(PASS);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public JTable makeTable(String sql) {
        model = new DefaultTableModel();
        try {
            rowSet.setCommand(sql);
            rowSet.execute();
            rowSet.addRowSetListener(this);
            rsmd = rowSet.getMetaData();
            numcols = rsmd.getColumnCount();

//            rs = stmt.executeQuery(sql);
//            rsmd = rs.getMetaData();
//            numcols = rsmd.getColumnCount();


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



    @Override
    public int getRowCount() {
        try {
            rowSet.last();
            numrows = rowSet.getRow();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numrows;
    }

    @Override
    public int getColumnCount() {
        return numcols;
    }

    @Override
    public String getColumnName(int columnIndex) {
        try {
            return rsmd.getColumnName(columnIndex + 1);
        } catch (SQLException e) {
            return e.toString();
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return getValueAt(0, columnIndex).getClass();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            if (rowSet.last()) {
                return (rowSet.getRow());
            } else {
                return 0;
            }
        } catch (SQLException e) {
            return e.toString();
        }

//        try {
//            rs.absolute(rowIndex + 1);
//            Object o = rs.getObject(columnIndex + 1);
//            if (o == null) return null;
//            else return o.toString();
//        } catch (SQLException e) {
//            return e.toString();
//        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        try {
            if (!rowSet.absolute(rowIndex + 1)) {
                return;
            }
            rowSet.updateObject(columnIndex + 1, aValue);
            fireTableCellUpdated(rowIndex, columnIndex);
        } catch (SQLException e) {
        }
    }


    @Override
    public void addTableModelListener(TableModelListener l) {
        fireTableDataChanged();
        System.out.println("hi");

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }

    @Override
    public void tableChanged(TableModelEvent e) {
//        e.getSource();
//        fireTableDataChanged();
//        ClientsBean clientB = new ClientsBean();
//        //clientB.setRowSet(rowSet);
//        ClientsUI clientui = new ClientsUI();
//        Clients c = clientui.getFieldData();
//        //clientB.update(c);

        int row = e.getFirstRow();
        int column = e.getColumn();
        TableModel model = (TableModel)e.getSource();
        String columnName = model.getColumnName(column);
        Object data = model.getValueAt(row, column);
        Clients c = new Clients();
        if (columnName.equals("fname")){
            try {
                rowSet.updateString("fname", data.toString());
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }


    }

    @Override
    public void rowSetChanged(RowSetEvent event) {
        fireTableStructureChanged();
    }

    @Override
    public void rowChanged(RowSetEvent event) {
        try {
            int row = rowSet.getRow();
            System.out.println("i got here");
            if (rowSet.rowDeleted()) {
                fireTableRowsDeleted(row, row);
            } else if (rowSet.rowInserted()) {
                fireTableRowsInserted(row, row);
            } else if (rowSet.rowUpdated()) {
                fireTableRowsUpdated(row, row);
                System.out.println("update?");
            }
        } catch (SQLException e) {
        }
    }

    @Override
    public void cursorMoved(RowSetEvent event) {

    }
}
