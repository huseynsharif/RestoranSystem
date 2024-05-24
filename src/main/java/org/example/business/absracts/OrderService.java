package org.example.business.absracts;

import org.example.Session;
import org.example.core.results.*;
import org.example.dataAccess.asbtracts.IOrderDao;
import org.example.entities.Order;

import java.time.LocalDate;
import java.util.List;

public abstract class OrderService {

    private final IOrderDao orderDao;

    protected OrderService(IOrderDao orderDao) {
        this.orderDao = orderDao;
    }


    public Result prosesAddOrder(){
        String orderName = getOrderName();

        this.orderDao.save(
                new Order(Session.loggedInUserId, orderName, LocalDate.now())
        );
        return new SuccessResult("Order Successfully saved.");
    }

    public DataResult<Order> prosesGetOrder(){
        List<Order> orders = this.orderDao.findAllByIsStatusTrue();

        if (orders.isEmpty()){
            return new ErrorDataResult<>("There is no active order.");
        }
        else {
            Order order = orders.get(0);
            order.setActive(false);
            order.setCourierId(Session.loggedInUserId);
            this.orderDao.update(order);
            return new SuccessDataResult<>(order, "Order successfully done.");
        }

    }

    protected abstract String getOrderName();

    public DataResult<List<Order>> getAllOrdersByCustomerId(){
        return new SuccessDataResult<>(this.orderDao.findByCustomerId(Session.loggedInUserId),
                "All orders listed by customer id");
    }

    public DataResult<List<Order>> getAllOrdersByCourierId(){
        return new SuccessDataResult<>(this.orderDao.findByCourierId(Session.loggedInUserId),
                "All orders listed by courier id");
    }

}
