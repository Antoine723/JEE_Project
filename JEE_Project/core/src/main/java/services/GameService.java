package services;

import dao.GenericDao;
import entities.Game;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GameService extends GenericService<Game> {

    public GameService(GenericDao<Game> internalDao) {
        super(internalDao);
    }

}