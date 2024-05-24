package org.example.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private int id;
    private int customerId;
    private Integer courierId;
    private String orderName;
    private Boolean active;
    private LocalDate creationDate;

    public Order(int customerId, String orderName, LocalDate creationDate) {
        this.customerId = customerId;
        this.orderName = orderName;
        this.creationDate = creationDate;
    }
}
