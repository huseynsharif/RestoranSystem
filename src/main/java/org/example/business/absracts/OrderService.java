package org.example.business.absracts;

import org.example.Session;
import org.example.core.results.DataResult;
import org.example.core.results.Result;
import org.example.core.results.SuccessDataResult;
import org.example.core.results.SuccessResult;
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

    protected abstract String getOrderName();

    public DataResult<List<Order>> getAllOrdersByCustomerId(){
        return new SuccessDataResult<>(this.orderDao.findByCustomerId(Session.loggedInUserId),
                "All orders listed by customer id");
    }

}
