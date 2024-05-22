package org.example.dataAccess.asbtracts;

import org.example.entities.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDao {
    List<User> findAll() throws SQLException, ClassNotFoundException;
}
