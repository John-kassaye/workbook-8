import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {

        if (args.length != 2){
            System.out.println("I need 2 parameters");
            System.exit(1);
        }

        String userName = args[0];
        String pw = args[1];

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind",userName,pw);

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Products");
        ResultSet rs = preparedStatement.executeQuery();

        while(rs.next()){
            String name = rs.getString("ProductName");
            System.out.println(name);
        }

        connection.close();

    }
}
