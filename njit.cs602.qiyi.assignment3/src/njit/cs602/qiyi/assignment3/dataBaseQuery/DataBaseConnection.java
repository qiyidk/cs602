package njit.cs602.qiyi.assignment3.dataBaseQuery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



/**
 * <p>
 * DataBaseConnection
 * </p>
 *
 * @author qiyi
 * @version 2016-4-10
 */
public class DataBaseConnection {
   
    public Connection connection = null;
    public DataBaseConnection(){       
        String connectionURL = "jdbc:mysql://" + SystemPara.connectionURL;

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            connection =  DriverManager.getConnection(connectionURL, SystemPara.userName, SystemPara.password);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void close(){
        if (connection != null)
            try {
                connection.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }
}
