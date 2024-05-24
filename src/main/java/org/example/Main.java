package org.example;

import org.example.business.absracts.IHandler;
import org.example.business.concretes.Handler;
import org.example.business.concretes.OrderConsoleManager;
import org.example.business.concretes.UserConsoleManager;
import org.example.dataAccess.concretes.OrderDao;
import org.example.dataAccess.concretes.UserDao;

import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author Huseyn Sharifzade and Shamil Abilov
 *
 */

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);

        IHandler handler = new Handler(scanner, new UserConsoleManager(
                new UserDao(), scanner
        ), new OrderConsoleManager(
                new OrderDao(), scanner
        ));

        handler.handleSplashPage();

    }
}