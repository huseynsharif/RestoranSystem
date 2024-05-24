package org.example.dataAccess.concretes;

import org.example.dataAccess.asbtracts.IOrderDao;
import org.example.db.DatabaseConfig;
import org.example.entities.Order;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao implements IOrderDao {
    @Override
    public void save(Order order) {
        String sql = "INSERT INTO orders (customer_id, order_name, active, creation_date) VALUES(?,?,?,?)";
        try (Connection connection = DatabaseConfig.connect()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, order.getCustomerId());
            preparedStatement.setString(2, order.getOrderName());
            preparedStatement.setBoolean(3, true);
            preparedStatement.setDate(4, Date.valueOf(order.getCreationDate()));

            preparedStatement.executeUpdate();

        }
        catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }

    }

    @Override
    public List<Order> findAllByIsStatusTrue() {
        String sql = "SELECT * FROM orders WHERE active=true";
        List<Order> orders = new ArrayList<>();
        try (Connection connection = DatabaseConfig.connect()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Order order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setCustomerId(resultSet.getInt("customer_id"));
                order.setCourierId(resultSet.getInt("courier_id"));
                order.setOrderName(resultSet.getString("order_name"));
                order.setActive(resultSet.getBoolean("active"));
                order.setCreationDate(new Date(resultSet.getDate("creation_date").getTime()).toLocalDate());
                orders.add(order);
            }
        }
        catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public Order findById(int id) {
        String sql = "SELECT * FROM orders WHERE id = ?";
        Order order = null;

        try (Connection connection = DatabaseConfig.connect()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setCustomerId(resultSet.getInt("customer_id"));
                order.setCourierId(resultSet.getInt("courier_id"));
                order.setOrderName(resultSet.getString("order_name"));
                order.setActive(resultSet.getBoolean("active"));
                order.setCreationDate(new Date(resultSet.getDate("creation_date").getTime()).toLocalDate());
            }
        }
        catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }

        return order;
    }

    @Override
    public List<Order> findByCustomerId(int customerId) {
        String sql = "SELECT * FROM orders WHERE customer_id=?";
        List<Order> orders = new ArrayList<>();
        try (Connection connection = DatabaseConfig.connect()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, customerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Order order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setCustomerId(resultSet.getInt("customer_id"));
                order.setCourierId(resultSet.getInt("courier_id"));
                order.setOrderName(resultSet.getString("order_name"));
                order.setActive(resultSet.getBoolean("active"));
                order.setCreationDate(new Date(resultSet.getDate("creation_date").getTime()).toLocalDate());
                orders.add(order);
            }
        }
        catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public List<Order> findByCourierId(int courierId) {
        String sql = "SELECT * FROM orders WHERE courierId=?";
        List<Order> orders = new ArrayList<>();
        try (Connection connection = DatabaseConfig.connect()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, courierId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Order order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setCustomerId(resultSet.getInt("customer_id"));
                order.setCourierId(resultSet.getInt("courier_id"));
                order.setOrderName(resultSet.getString("order_name"));
                order.setActive(resultSet.getBoolean("active"));
                order.setCreationDate(new Date(resultSet.getDate("creation_date").getTime()).toLocalDate());
                orders.add(order);
            }
        }
        catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public void update(Order order) {
        String sql = "UPDATE orders SET customer_id = ?, courier_id = ?, order_name = ?, active = ?, creation_date = ? WHERE id = ?";

        try (Connection connection = DatabaseConfig.connect()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, order.getCustomerId());
            preparedStatement.setInt(2, order.getCourierId());
            preparedStatement.setString(3, order.getOrderName());
            preparedStatement.setBoolean(4, order.getActive());
            preparedStatement.setDate(5, Date.valueOf(order.getCreationDate()));
            preparedStatement.setInt(6, order.getId());
            preparedStatement.executeUpdate();
        }
        catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
