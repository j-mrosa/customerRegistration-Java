
package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entity.Store;


/**
 * This class provides a central location for accessing the Store table.
 */
public class StoreAccessor {

    private static Connection conn = null;
    private static PreparedStatement selectAllStatement = null;
    
    // constructor is private - no instantiation allowed
    private StoreAccessor() {
    }

    /**
     * Used only by methods in this class to guarantee a database connection.
     *
     * @throws SQLException
     */
    private static void init() throws SQLException {
        if (conn == null) {
            conn = ConnectionManager.getConnection(ConnectionParameters.URL, ConnectionParameters.USERNAME, ConnectionParameters.PASSWORD);
            selectAllStatement = conn.prepareStatement("select * from stores");
        }
    }

    /**
     * Gets all stores.
     *
     * @return a List, possibly empty, of Store objects
     */
    public static List<Store> getAllStores() {
        List<Store> items = new ArrayList();
        try {
            init();
            ResultSet rs = selectAllStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("storeCode");
                String address = rs.getString("storeAddress");
                String phone = rs.getString("phone");
                Store item = new Store(id, address, phone);
                items.add(item);
            }
        } catch (SQLException ex) {
            System.err.println(ex);
            items = new ArrayList();
        }
        return items;
    }

} // end StoreAccessor
