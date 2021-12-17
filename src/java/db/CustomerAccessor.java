package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entity.Customer;

/**
 * This class provides a central location for accessing the Customer table.
 */
public class CustomerAccessor {

    private static Connection conn = null;
    private static PreparedStatement selectAllStatement = null;
    private static PreparedStatement selectByIdStatement = null;
    private static PreparedStatement deleteStatement = null;
    private static PreparedStatement updateStatement = null;
    private static PreparedStatement insertStatement = null;

    // constructor is private - no instantiation allowed
    private CustomerAccessor() {
    }

    /**
     * Used only by methods in this class to guarantee a database connection.
     *
     * @throws SQLException
     */
    private static void init() throws SQLException {
        if (conn == null) {
            conn = ConnectionManager.getConnection(ConnectionParameters.URL, ConnectionParameters.USERNAME, ConnectionParameters.PASSWORD);
            selectAllStatement = conn.prepareStatement("select * from customers");
            selectByIdStatement = conn.prepareStatement("select * from customers where customerID = ?");
            deleteStatement = conn.prepareStatement("delete from customers where customerID = ?");
            updateStatement = conn.prepareStatement("update Customers set firstName = ?, lastName = ?, dateOfBirth = ?, phone = ?, email = ?, loyaltyMember = ?, favoriteStore = ? where customerID = ?");
            insertStatement = conn.prepareStatement("insert into Customers values (?,?,?,?,?,?,?,?)");

        }
    }

    /**
     * Gets all customers.
     *
     * @return a List, possibly empty, of Customer objects
     */
    public static List<Customer> getAllCustomers() {
        List<Customer> items = new ArrayList();
        try {
            init();
            ResultSet rs = selectAllStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("customerID");
                String firstName = rs.getString("firstName");
                String lastname = rs.getString("lastName");
                String dob = rs.getString("dateOfBirth");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                boolean loyalty = rs.getBoolean("loyaltyMember");
                int favStore = rs.getInt("favoriteStore");
                Customer item = new Customer(id, firstName, lastname, dob, phone, email, loyalty, favStore);
                items.add(item);
            }
        } catch (SQLException ex) {
            System.err.println(ex);
            items = new ArrayList();
        }
        return items;
    }

    /**
     * Gets customer that matches the id sent in
     * 
     * @param id the of Customer to retrieve
     * @return the Customer whose id matches the id sent in
     */
    public static Customer getCustomerByID(int id) {
        Customer c;

        String firstName = "";
        String lastName = "";
        String dob = "";
        String phone = "";
        String email = "";
        boolean loyalty = false;
        int favStore = 201;
        
        try {
            init();
            selectByIdStatement.setInt(1, id);//1 = question mark
            ResultSet rs = selectByIdStatement.executeQuery();
            while (rs.next()) {
                firstName = rs.getString("firstName");
                lastName = rs.getString("lastName");
                dob = rs.getString("dateOfBirth");
                phone = rs.getString("phone");
                email = rs.getString("email");
                loyalty = rs.getBoolean("loyaltyMember");
                favStore = rs.getInt("favoriteStore");
            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }
        c = new Customer(id, firstName, lastName, dob, phone, email, loyalty, favStore);
        return c;
    }

    /**
     * Deletes the Customer with the same ID as the specified item.
     *
     * @param c the Customer whose ID should be used to match the item to delete
     * @return <code>true</code> if an item was deleted; <code>false</code>
     * otherwise
     */
    public static boolean deleteCustomer(Customer c) {
        boolean res;

        try {
            init();
            deleteStatement.setInt(1, c.getId()); // 1 = first question mark
            int rowCount = deleteStatement.executeUpdate();
            res = (rowCount == 1);
        } catch (SQLException ex) {
            res = false;
        }
        return res;
    }

    /**
     * Deletes the Customer with the specified ID.
     *
     * @param id the ID of the item to delete
     * @return <code>true</code> if a customer was deleted; <code>false</code>
     * otherwise
     */
    public static boolean deleteCustomerById(int id) {
        Customer dummy = new Customer(id, "any", "any", "any", "any", "any", false, 201);
        return deleteCustomer(dummy);
    }

    /**
     * Updates the Customer with the same ID as the specified item.
     *
     * @param c the Customer whose ID should be used to match the item to update
     * @return <code>true</code> if an item was updated; <code>false</code>
     * otherwise
     */
    public static boolean updateCustomer(Customer c) {
        boolean res;

        try {
            init();

            //set statement parameters
            updateStatement.setString(1, c.getFirstName()); // 1 = first question mark
            updateStatement.setString(2, c.getLastName());
            updateStatement.setString(3, c.getDob());
            updateStatement.setString(4, c.getPhone());
            updateStatement.setString(5, c.getEmail());
            updateStatement.setBoolean(6, c.isLoyalty());
            updateStatement.setInt(7, c.getFavStore());
            updateStatement.setInt(8, c.getId());

            int rowCount = updateStatement.executeUpdate();
            res = (rowCount == 1);
        } catch (SQLException ex) {
            res = false;
        }
        return res;
    }

    /**
     * Creates a new Customer.
     *
     * @param c the Customer to be created
     * @return <code>true</code> if an item was created; <code>false</code>
     * otherwise
     */
    public static boolean createCustomer(Customer c) {
        boolean res;
        
        try {
            init();

            //set statement parameters
            insertStatement.setInt(1, c.getId()); // 1 = first question mark
            insertStatement.setString(2, c.getFirstName());
            insertStatement.setString(3, c.getLastName());
            insertStatement.setString(4, c.getDob());
            insertStatement.setString(5, c.getPhone());
            insertStatement.setString(6, c.getEmail());
            insertStatement.setBoolean(7, c.isLoyalty());
            insertStatement.setInt(8, c.getFavStore());

            int rowCount = insertStatement.executeUpdate();
            res = (rowCount == 1);
        } catch (SQLException ex) {
            res = false;
        }
        return res;
    }

} // end CustomerAccessor
