package wednesday.language;

import javax.sql.DataSource;
import java.lang.invoke.SwitchPoint;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LanguageDAO {
    private final DataSource dataSource;

    public LanguageDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Language> getAll() {
        List<Language> languages = new ArrayList<>();

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT language_id , name FROM language");
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {

            while (resultSet.next()) {
                languages.add(new Language(resultSet.getString("name"), resultSet.getInt("language_id")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return languages;
    }

    public Language getId(int id) {
        Language language = null;

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT language_id , name FROM language WHERE language_id = ?;")
        ) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    language = new Language(resultSet.getString("name"), resultSet.getInt("language_id"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return language;
    }

    public Language getName(String name){
        Language language = null;

        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT language_id, name FROM language WHERE name = ?;");
                ) {
            preparedStatement.setString(1,name);
            try(
                    ResultSet resultSet = preparedStatement.executeQuery()
                    ){

                if (resultSet.next()){
                    language = new Language(resultSet.getString("name"), resultSet.getInt("language_id"));
                }
            }

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return language;
    }

    public void add(Language language){
        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO language (name) values (?);")
                ) {
            preparedStatement.setString(1,language.getName());
            int rowAffected = preparedStatement.executeUpdate();
            if (rowAffected != 1){
                throw new SQLException("Insertion failed");
            }

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void update(int id, Language language){
        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE language SET name = ? WHERE language_id = ?");
                ) {
            preparedStatement.setString(1, language.getName());
            preparedStatement.setInt(2,id);
            preparedStatement.executeUpdate();

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id){
        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM language WHERE language_id = ?")
                ){
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
}
