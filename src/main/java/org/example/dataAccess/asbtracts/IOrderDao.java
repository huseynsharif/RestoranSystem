package org.example.dataAccess.asbtracts;

import org.example.entities.Order;

import java.util.List;

public interface IOrderDao {
    void save(Order order);
    List<Order> findAll();
}
