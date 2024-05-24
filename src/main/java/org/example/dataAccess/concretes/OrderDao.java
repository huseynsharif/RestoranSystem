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
        String sql = "INSERT INTO orders (customerId, orderName, active, creationDate) VALUES(?,?,?,?)";
        try (Connection connection = DatabaseConfig.connect()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, order.getCustomerId());
            preparedStatement.setString(2, order.getOrderName());
            preparedStatement.setBoolean(3, order.getActive());

            preparedStatement.executeUpdate();

        }
        catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }

    }

    @Override
    public List<Order> findAll() {
        String sql = "SELECT * FROM orders";
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
}
