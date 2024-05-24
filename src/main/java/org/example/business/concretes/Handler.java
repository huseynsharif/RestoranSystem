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
        while (true){
            line();
            System.out.println("Hi. Welcome to our app.");
            line();
            System.out.println("1) Register");
            System.out.println("2) Login");
            System.out.println("0) Exit");
            line();
            int select = scanner.nextInt();
            if (select==0) break;
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
    }

    public void handleCourierPage() {
        line();
        System.out.println("Welcome!");
        while (true){
            line();
            System.out.println("1) Get order");
            System.out.println("2) History");
            System.out.println("0) Exit");
            line();
            System.out.print("Select: ");
            int select = scanner.nextInt();
            if (select==0) break;
            switch (select){
                case 1 -> handleGetOrder();
                case 2 -> handleCourierHistory();
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void handleCourierHistory() {
        DataResult<List<Order>> result = this.orderService.getAllOrdersByCourierId();

        showGetAllResult(result);
    }

    private void handleGetOrder() {
        DataResult<Order> result = this.orderService.prosesGetOrder();

        if (result.isSuccess()){
            System.out.println("Your order: " + result.getData().getOrderName());
        }
        else{
            System.out.println(result.getMessage());
        }

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
                case 2 -> handleCustomerHistory();
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void handleCustomerHistory() {
        DataResult<List<Order>> result = orderService.getAllOrdersByCustomerId();

        showGetAllResult(result);

    }

    private void showGetAllResult(DataResult<List<Order>> result) {
        if (result.isSuccess()){
            line();
            System.out.println("Here is your orders: ");
            printList(result.getData());
            line();
        }
        else {
            System.out.println(result.getMessage());
        }
    }

    private void printList(List<Order> orders) {
        for (int i = 0; i < orders.size(); i++) {
            System.out.printf("%d. %s\n", i+1, orders.get(i).getOrderName());
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
