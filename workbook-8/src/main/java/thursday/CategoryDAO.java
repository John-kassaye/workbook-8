package thursday;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;


public class CategoryDAO {
    private DataSource dataSource;

    public CategoryDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Category> getAll(){
        List<Category> categories = new ArrayList<>();
        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT category_id, name FROM category");
                ResultSet resultSet = preparedStatement.executeQuery();
                ) {

            while (resultSet.next()){
                categories.add(new Category(resultSet.getInt("category_id"), resultSet.getString("name")));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return categories;
    }

    public Category getName(String name){
        Category category = null;
        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT category_id, name FROM category WHERE name = ?;");
                ){
            preparedStatement.setString(1, name);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()){
                    category = new Category(resultSet.getInt("category_id"), resultSet.getString("name"));
                }
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return category;
    }

    public Category getId(int id){
        Category category = null;
        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT category_id, name FROM category WHERE category_id = ?;");
        ){
            preparedStatement.setInt(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()){
                    category = new Category(resultSet.getInt("category_id"), resultSet.getString("name"));
                }
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return category;
    }
}
