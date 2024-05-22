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
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setFullName(resultSet.getString("full_name"));
            user.setRole(resultSet.getString("email"));
            user.setRole(resultSet.getString("role"));

            users.add(user);
        }
        connection.close();
        statement.close();
        resultSet.close();
        return users;
    }
}
