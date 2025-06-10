package monday;

import java.sql.*;
import java.util.Scanner;

public class ListEmployeeByCity {
    public static void main(String[] args) throws SQLException {
        // 1. connect
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind", args[0], args[1]);

        // 2. run query
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM northwind.Employees WHERE City LIKE ? ;");
        preparedStatement.setString(1, getUserInput());
        ResultSet resultSet = preparedStatement.executeQuery();

        // 3. process results
        while(resultSet.next()) {
            System.out.println("\nFirst name: " + resultSet.getString("FirstName"));
            System.out.println("Last name: " + resultSet.getString("LastName"));
            System.out.println("Title: " + resultSet.getString("Title"));
        }

        // 4. close all resources
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

    public static String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What City are you looking for?");
        return scanner.nextLine();
    }

}
