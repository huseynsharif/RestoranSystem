package org.example.business.concretes;

import org.example.business.absracts.UserService;
import org.example.dataAccess.asbtracts.IUserDao;
import org.example.entities.dto.UserLoginRequest;
import org.example.entities.dto.UserRegisterRequest;

import java.util.Scanner;

public class UserConsoleManager extends UserService {

    private final Scanner scanner;

    public UserConsoleManager(IUserDao userDao, Scanner scanner) {
        super(userDao);
        this.scanner = scanner;
    }

    @Override
    protected UserLoginRequest getLoginFields() {

        String email = getEmail();
        String password = getRequiredInput("Password");

        return null;
    }

    @Override
    public UserRegisterRequest getRegisterFields() {

        System.out.println("Select your role: ");
        System.out.println("1) Customer");
        System.out.println("2) Courier");
        line();
        System.out.print("Select one: ");
        int select = scanner.nextInt();
        while (select<1 || select>2){
            System.out.println("Invalid option. Please, reenter");
            select = scanner.nextInt();
        }

        String role = getRole(select);

        String fullName = getRequiredInput("full name");
        String email = getEmail();
        String password = getRequiredInput("password");



        return new UserRegisterRequest(fullName, email, password, role);
    }

    private String getEmail() {

        String email = getRequiredInput("email");

        while (userDao.existsByEmail(email)){
            email = getRequiredInput("email");
        }

        return email;
    }

    private String getRole(int select) {
        return switch (select) {
            case 1 -> "Customer";
            case 2 -> "Courier";
            default -> "";
        };
    }

    private void line() {
        System.out.println("--------------------------");
    }

    private String getRequiredInput(String fieldName){
        System.out.printf("Enter your %s: ", fieldName);
        String field = scanner.nextLine();
        while (field.isBlank()){
            System.out.printf("%s is required. Please, reenter: ", fieldName);
            field = scanner.nextLine();
        }

        return field;
    }
}
