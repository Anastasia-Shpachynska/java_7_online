package dao;

import entity.BaseEntity;

import java.util.List;

public interface CRUDDao<E extends BaseEntity>{
    void create(E entity);
    void update(E entity);
    void delete(Long id);
    E findOne(Long id);
    List<E> findAll();
    boolean existsById(Long id);
}
