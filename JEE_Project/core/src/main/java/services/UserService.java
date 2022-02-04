package services;

import dao.GenericDao;
import entities.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService extends GenericService<User> {

    public UserService(GenericDao<User> internalDao) {
        super(internalDao);
    }

}