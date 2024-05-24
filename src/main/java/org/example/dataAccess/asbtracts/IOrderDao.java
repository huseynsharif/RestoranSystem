package org.example.dataAccess.asbtracts;

import org.example.entities.Order;

import java.util.List;

public interface IOrderDao {
    void save(Order order);
    List<Order> findAllByIsStatusTrue();
    Order findById(int id);
    void update(Order order);
    List<Order> findByCustomerId(int customerId);
    List<Order> findByCourierId(int courierId);
}
