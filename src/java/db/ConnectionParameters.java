package db;

/**
 * It's better practice to store this information in a configuration file.
 */
public class ConnectionParameters {
    
    public static final String URL = "jdbc:derby://localhost:1527/customerDB";
    public static final String USERNAME = "dumb";
    public static final String PASSWORD = "dumb";
    
    // no instantiation allowed
    private ConnectionParameters() {}
    
} // end class ConnectionParameters
