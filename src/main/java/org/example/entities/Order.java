package org.example.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private int customerId;
    private Integer courierId;
    private String orderName;
    private Boolean active;
    private LocalDateTime creationDate;

}
