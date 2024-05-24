package org.example;

import org.example.business.absracts.IHandler;
import org.example.business.concretes.Handler;
import org.example.business.concretes.UserConsoleManager;
import org.example.dataAccess.concretes.UserDao;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        IHandler handler = new Handler(new Scanner(System.in), new UserConsoleManager(
                new UserDao(), new Scanner(System.in)
        ));

        handler.handleSplashPage();

        // orders table yarat  --- done
        // musteri order yaratmalidir -- done save
        // musteri butun orderlerine baxmalidir -- done findByCustomerIdAndStatusTrue
        // kuryer order goturmelidir -- done  findAllByIsStatusTrue and update order
        // kuryer orderlerine baxmalidir -- done findByCourierIdAndStatusTrue

    }
}