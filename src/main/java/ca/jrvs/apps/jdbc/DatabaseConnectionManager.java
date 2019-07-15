package ca.jrvs.apps.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionManager {
    private final String url;
    private final Properties properties;

    /**
     * Connection manager to make a connection to our database
     * @param host
     * @param databaseName
     * @param username
     * @param password
     */

    public DatabaseConnectionManager(String host, String databaseName, String username, String password){
        this.url = "jdbc:postgresql://"+host+"/"+databaseName;
        this.properties = new Properties();
        this.properties.setProperty("user", username);
        this.properties.setProperty("password", password);
    }

    /**
     * method that returns a connection
     * @return connection
     * @throws SQLException
     */

    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection(this.url, this.properties);
    }
}
