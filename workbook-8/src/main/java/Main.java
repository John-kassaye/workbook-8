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

        int id = 5;
        String phone = "555";
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM northwind.Shippers WHERE ShipperId > ? or Phone LIKE ?;");
        preparedStatement.setInt(1,id);
        preparedStatement.setString(2,"%" + phone + "%");
        ResultSet rs = preparedStatement.executeQuery();

        while(rs.next()){
            System.out.println("Id: " + rs.getString("ShipperId"));
            System.out.println("Phone: " + rs.getString("CompanyName"));
            System.out.println("Name: " + rs.getString("Phone"));

        }

        connection.close();

    }
}
