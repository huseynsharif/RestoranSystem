package org.example;

import org.example.business.absracts.UserService;
import org.example.business.concretes.UserConsoleManager;
import org.example.dataAccess.concretes.UserDao;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        UserService userService = new UserConsoleManager(new UserDao(), new Scanner(System.in));
        userService.prosesRegister();

    }
}