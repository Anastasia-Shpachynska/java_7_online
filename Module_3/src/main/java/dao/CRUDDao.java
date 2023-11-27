package dao;

import entity.BaseEntity;
import entity.User;

import java.util.List;

public interface CRUDDao <E extends BaseEntity> {

    void create(E entity);
    void update(E entity);
    List<E> findAll();
}
