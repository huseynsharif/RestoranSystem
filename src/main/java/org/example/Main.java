package org.example;

import org.example.dataAccess.asbtracts.IUserDao;
import org.example.dataAccess.concretes.UserDao;
import org.example.entities.User;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        // Database in adini deyistim restaurant_system qoydum sonra role sildim ele String kimi verdim

        // users tebleni manul yaratdim
//        CREATE TABLE users (
//                id SERIAL PRIMARY KEY,
//                full_name VARCHAR(255) NOT NULL,
//                email VARCHAR(255) UNIQUE NOT NULL,
//                password VARCHAR(255) NOT NULL,
//                role VARCHAR(50) NOT NULL
//        );


        IUserDao userDao = new UserDao();

        for (User user : userDao.findAll()) {
            System.out.println(user);
        }

    }
}