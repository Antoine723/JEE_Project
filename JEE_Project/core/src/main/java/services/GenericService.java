package services;

import dao.GenericDao;

import java.util.List;

public class GenericService<T> {
    private final GenericDao<T> internalDao;

    public GenericService(GenericDao<T> internalDao) {
        this.internalDao = internalDao;
    }

    public GenericDao<T> getInternalDao() {
        return internalDao;
    }

    public void deleteAll() {
        internalDao.deleteAll();
    }

    public void save(final T instance) {
        internalDao.save(instance);
    }

    public long countAll() {
        return internalDao.count();
    }

    public List<T> findAll(){
        return internalDao.findAll();
    }

}
