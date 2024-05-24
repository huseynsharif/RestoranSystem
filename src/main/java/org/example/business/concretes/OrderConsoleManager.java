package org.example.business.concretes;

import org.example.business.absracts.OrderService;
import org.example.dataAccess.asbtracts.IOrderDao;

import java.util.Scanner;

public class OrderConsoleManager extends OrderService {

    private final Scanner scanner;

    public OrderConsoleManager(IOrderDao orderDao, Scanner scanner) {
        super(orderDao);
        this.scanner = scanner;
    }


    @Override
    protected String getOrderName() {
        scanner.nextLine();
        return getRequiredInput("Order name");
    }

    private String getRequiredInput(String fieldName){
        System.out.printf("Enter your %s: \n", fieldName);
        String field = scanner.nextLine();

        while (field.isEmpty()){
            System.out.println("Something went wrong. Please, reenter: ");
            field = scanner.nextLine();
        }

        return field;
    }
}
