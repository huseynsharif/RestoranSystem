package org.example.business.concretes;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.business.absracts.UserService;
import org.example.business.absracts.ValidateField;
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

        String email = getRequiredInput("Email", (a)->false);
        String password = getRequiredInput("Password", (p) -> p.length() < 8);

        return new UserLoginRequest(email, password);
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
        scanner.nextLine();
        String fullName = getRequiredInput("full name", (a)->false);
        String email = getRequiredInput("Email", userDao::existsByEmail);
        String password = getRequiredInput("password",
                (p) -> p.length() < 8
        );
        return new UserRegisterRequest(fullName, email, password, role);
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

    private String getRequiredInput(String fieldName, ValidateField validateField){
        System.out.printf("Enter your %s: \n", fieldName);
        String field = scanner.nextLine();

        while (field.isEmpty() || validateField.handle(field)){
            System.out.println("Something went wrong. Please, reenter: ");
            field = scanner.nextLine();
        }

        return field;
    }
}
