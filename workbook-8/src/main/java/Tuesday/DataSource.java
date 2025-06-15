package Tuesday;

import java.sql.*;
import org.apache.commons.dbcp2.BasicDataSource;

public class DataSource {


        public static void main(String[] args) throws SQLException {
            if(args.length != 2) {
                System.out.println("I need 2 para.");
                System.exit(1);
            }

            String username = args[0];
            String password = args[1];
            int minShipperId = 3;
            String phoneSnippet = "555";


            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setUrl("jdbc:mysql://localhost:3306/northwind");
            dataSource.setUsername(username);
            dataSource.setPassword(password);

            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM northwind.Shippers WHERE ShipperId > ? OR Phone LIKE ?;");
            preparedStatement.setInt(1,minShipperId);
            preparedStatement.setString(2,"%" + phoneSnippet + "%");

        }
}
