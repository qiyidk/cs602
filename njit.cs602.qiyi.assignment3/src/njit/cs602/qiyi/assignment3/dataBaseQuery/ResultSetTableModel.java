package njit.cs602.qiyi.assignment3.dataBaseQuery;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.table.AbstractTableModel;

public class ResultSetTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private ResultSetMetaData metaData;
    private int numberOfRows;

    public ResultSetTableModel(String query) throws SQLException {

        try {
            // connect to database
            connection = new DataBaseConnection().connection;

            // create Statement to query database
            statement = connection.createStatement();

            String selectDataBase = "USE " + SystemPara.database;
            statement.executeQuery(selectDataBase);

            // set query and execute it
            setQuery(query);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // get class that represents column type
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Class getColumnClass(int column) throws IllegalStateException {

        // determine Java class of column
        try {
            String className = metaData.getColumnClassName(column + 1);

            // return Class object that represents className
            return Class.forName(className);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return Object.class;
    }

    // get number of columns in ResultSet
    public int getColumnCount() throws IllegalStateException {

        // determine number of columns
        try {
            return metaData.getColumnCount();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return 0;
    }

    // get name of a particular column in ResultSet
    public String getColumnName(int column) throws IllegalStateException {

        // determine column name
        try {
            return metaData.getColumnName(column + 1);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return "";
    }

    // return number of rows in ResultSet
    public int getRowCount() throws IllegalStateException {

        return numberOfRows;
    }

    // obtain value in particular row and column
    public Object getValueAt(int row, int column) throws IllegalStateException {

        // obtain a value at specified ResultSet row and column
        try {
            resultSet.absolute(row + 1);
            return resultSet.getObject(column + 1);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return "";
    }

    // set new database query string
    public void setQuery(String query)
            throws SQLException, IllegalStateException {

        // specify query and execute it
        resultSet = statement.executeQuery(query);

        // obtain meta data for ResultSet
        metaData = resultSet.getMetaData();

        // determine number of rows in ResultSet
        resultSet.last(); // move to last row
        numberOfRows = resultSet.getRow(); // get row number      

        // notify JTable that model has changed
        fireTableStructureChanged();
    }

    public void close() {
        try {
            if (connection != null)
                connection.close();
            if (statement != null)
                statement.close();
            if (resultSet != null)
                resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}