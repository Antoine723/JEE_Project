package services;

import dao.GenericDao;
import entities.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderService extends GenericService<Order> {

    public OrderService(GenericDao<Order> internalDao) {
        super(internalDao);
    }

}