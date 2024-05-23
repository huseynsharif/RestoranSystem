package org.example;

import org.example.dataAccess.asbtracts.IUserDao;
import org.example.dataAccess.concretes.UserDao;
import org.example.entities.User;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        IUserDao userDao = new UserDao();

        for (User user : userDao.findAll()) {
            System.out.println(user.getFullName());
        }

    }
}