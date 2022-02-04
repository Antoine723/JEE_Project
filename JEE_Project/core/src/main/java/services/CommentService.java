package services;

import dao.GenericDao;
import entities.Comment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommentService extends GenericService<Comment> {

    public CommentService(GenericDao<Comment> internalDao) {
        super(internalDao);
    }

}