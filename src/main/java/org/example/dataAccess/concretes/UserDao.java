package org.example.dataAccess.concretes;

import org.example.dataAccess.asbtracts.IUserDao;
import org.example.db.DatabaseConfig;
import org.example.entities.User;

import javax.print.DocFlavor;
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

    // TODO: email db-da varsa true yoxdursa false qaytarsin
    @Override
    public Boolean existsByEmail(String email) {
        return null;
    }

    // TODO: emaile gore user qaytarsin
    @Override
    public User findByEmail(String email) {
        return null;
    }
}
