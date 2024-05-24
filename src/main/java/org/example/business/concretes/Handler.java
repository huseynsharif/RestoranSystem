package org.example.business.concretes;

import org.example.Session;
import org.example.business.absracts.IHandler;
import org.example.business.absracts.OrderService;
import org.example.business.absracts.UserService;
import org.example.core.results.DataResult;
import org.example.core.results.Result;
import org.example.entities.Order;
import org.example.entities.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Handler implements IHandler {

    private final Scanner scanner;
    private final UserService userService;
    private final OrderService orderService;

    public Handler(Scanner scanner, UserService userService, OrderService orderService) {
        this.scanner = scanner;
        this.userService = userService;
        this.orderService = orderService;
    }

    @Override
    public void handleSplashPage() throws SQLException, ClassNotFoundException {
        System.out.println("Hi. Welcome to our app.");
        line();
        System.out.println("1) Register");
        System.out.println("2) Login");
        line();
        int select = scanner.nextInt();

        DataResult<User> result = switch (select){
            case 1 -> userService.prosesRegister();
            case 2 -> userService.processLogin();
            default -> throw new IllegalStateException("Unexpected value: " + select);
        };

        if (result.isSuccess()){

            Session.loggedInUserId = result.getData().getId();

            if (result.getData().getRole().equalsIgnoreCase("Customer")){
                handleCustomerPage();
            }
            else {
                handleCourierPage();
            }
        }
        else{
            System.out.println(result.getMessage());
        }

    }

    public void handleCourierPage() {
        System.out.println("curier");
    }

    private void handleCustomerPage() {
        line();
        System.out.println("Welcome!");
        while (true){
            line();
            System.out.println("1) Add order");
            System.out.println("2) History");
            System.out.println("0) Exit");
            line();
            System.out.print("Select: ");
            int select = scanner.nextInt();

            if (select==0) break;
            switch (select){
                case 1 -> handleAddOrder();
                case 2 -> handleHistory();
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void handleHistory() {
        DataResult<List<Order>> result = orderService.getAllOrdersByCustomerId();

        if (result.isSuccess()){
            printList(result.getData());
        }
        else {
            System.out.println(result.getMessage());
        }

    }

    private void printList(List<Order> orders) {
        for (int i = 0; i < orders.size(); i++) {
            System.out.printf("%d. %s\n", i, orders.get(i).getOrderName());
        }
    }

    private void handleAddOrder() {
        Result result = this.orderService.prosesAddOrder();

        if (result.isSuccess()){
            System.out.println("Order added successfully");
        }
        else {
            System.out.println(result.getMessage());
        }
    }


    private void line() {
        System.out.println("--------------------------");
    }
}
