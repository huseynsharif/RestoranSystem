package org.example.dataAccess.concretes;

import org.example.dataAccess.asbtracts.IUserDao;
import org.example.db.DatabaseConfig;
import org.example.entities.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements IUserDao {

    @Override
    public List<User> findAll() throws SQLException, ClassNotFoundException {

        // Connection acilir ve Statment yaradilir hemin Statment de query execute edirsen ve Result set e menimsedirsen sonra da is
        // bitdikden close edirsen bu qeder besit
        Connection connection = DatabaseConfig.connect();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from users");
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            User user = new User(
                    resultSet.getInt("id"),
                    resultSet.getString("full_name"),
                    resultSet.getString("email"),
                    resultSet.getString("password"),
                    resultSet.getString("role")
            );
            users.add(user);
        }
        connection.close();
        statement.close();
        resultSet.close();
        return users;
    }

    // TODO: add metodu yazilmalidir, bir de bilmirem db-a id ile birlikde gondermeliyik ya yox.
    //  Men yazdiqimda id-e hele bele 0 verirem. Lazimsizdirsa ozun deyisersen ne isteyirsen et
    @Override
    public void add(User user) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO users (full_name, email, password, role) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setString(1, user.getFullName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getRole());
            preparedStatement.executeUpdate();
            System.out.println("User added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Boolean existsByEmail(String email) {
        String sql = "select * from users where email = ?";

        try (Connection connection = DatabaseConfig.connect()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        }
        catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User findByEmail(String email) {
        String sql = "select * from users where email = ?";

        try(Connection connection = DatabaseConfig.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setFullName(resultSet.getString("full_name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("role"));
                return user;
            }
        }
        catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }
}
