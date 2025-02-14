package com.servlet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class SQL_store {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    
    //String query = “INSERT INTO evaluation (id, name, field, amount, profitability_level, status, recommended) VALUES (?, ?, ?, ?, ?, ?, ?)";
    // Insert data into the database
    public static void insertData(String name, String field, double amount, double profitability_level, String status, double recommended) {
    	// This will load the MySQL driver, each DB has its own driver
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // Setup the connection with the DB
        String url = "jdbc:mysql://localhost:3306/investment";
        String username = "root";
        String password = "password123";
    	//String sql = "INSERT INTO users (name, age, email) VALUES (?, ?, ?)"; // Replace 'users' with your table name
        String query = "INSERT INTO evaluation (name, field, amount, profitability_level, status, recommended) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Set the values dynamically
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, field);
            preparedStatement.setDouble(3, amount);
            preparedStatement.setDouble(4, profitability_level);
            preparedStatement.setString(5, status);
            preparedStatement.setDouble(6, recommended);

            // Execute the query
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Data inserted successfully!");
            }

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }
    
    public void createTable() throws Exception {
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Setup the connection with the DB
            String url = "jdbc:mysql://localhost:3306/investment";
            String username = "root";
            String password = "password123";

            Connection connect = DriverManager.getConnection(url, username, password);

            /*connect = DriverManager
                    .getConnection("jdbc:mysql://localhost/investment?"
                            + "user=root&password=password123");*/

            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            String query = "CREATE TABLE evaluation (id INT NOT NULL AUTO_INCREMENT, name VARCHAR(60) NOT NULL, field VARCHAR(60), amount DOUBLE(10, 2) NOT NULL, profitability_level DOUBLE(10, 2) NOT NULL, status VARCHAR(30) NOT NULL, recommended DOUBLE(10, 2) NOT NULL, PRIMARY KEY (ID))";
            // Result set get the result of the SQL query
            //resultSet = statement
            //        .executeQuery("select * from investment");
            int result = statement
                    .executeUpdate(query);
            System.out.println(result);
                        
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }

    }

    //close the resultSet
    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }

}
