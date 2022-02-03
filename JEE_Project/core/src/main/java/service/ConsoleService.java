package service;

import dao.GenericDao;
import entities.Console;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ConsoleService extends GenericService<Console> {

    public ConsoleService(GenericDao<Console> internalDao) {
        super(internalDao);
    }

}