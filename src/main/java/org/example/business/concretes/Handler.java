package org.example.business.concretes;

import org.example.business.absracts.IHandler;
import org.example.business.absracts.UserService;
import org.example.core.results.Result;

import java.sql.SQLException;
import java.util.Scanner;

public class Handler implements IHandler {

    private final Scanner scanner;
    private final UserService userService;

    public Handler(Scanner scanner, UserService userService) {
        this.scanner = scanner;
        this.userService = userService;
    }

    @Override
    public void handleSplashPage() throws SQLException, ClassNotFoundException {
        System.out.println("Hi. Welcome to our app.");
        line();
        System.out.println("1) Register");
        System.out.println("2) Login");
        line();
        int select = scanner.nextInt();

        Result result = switch (select){
            case 1 -> userService.prosesRegister();
            case 2 -> userService.processLogin();
            default -> throw new IllegalStateException("Unexpected value: " + select);
        };

        if (result.isSuccess()){
            handleOrderPage();
        }
        else{
            System.out.println(result.getMessage());
        }

    }

    private void handleOrderPage() {

    }


    private void line() {
        System.out.println("--------------------------");
    }
}
