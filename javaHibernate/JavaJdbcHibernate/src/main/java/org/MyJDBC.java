package org;

import java.sql.*;

public class MyJDBC {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/jdbc_database";
        String user = "root";
        String password = "root1234";

        try {
            // Open a connection
            System.out.println("Connecting to database...");
            Connection connection = DriverManager.getConnection(url, user, password);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from employees");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                int salary = resultSet.getInt("salary");
                System.out.println("Id: " + id + ", firstName: " + firstName + ", lastName: " + lastName + ", salary: " + salary);
            }

            // Close the connection
            connection.close();
            System.out.println("Connection closed.");
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("VendorError: " + e.getErrorCode());
            e.printStackTrace();
        }
    }
}