package org.example;

import org.example.business.absracts.IHandler;
import org.example.business.concretes.Handler;
import org.example.business.concretes.UserConsoleManager;
import org.example.dataAccess.concretes.UserDao;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        IHandler handler = new Handler(new Scanner(System.in), new UserConsoleManager(
                new UserDao(), new Scanner(System.in)
        ));

    }
}