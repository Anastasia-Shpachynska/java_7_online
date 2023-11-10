package dao;

import entity.BaseEntity;

public interface CRUDDao<E extends BaseEntity>{
    void create(E entity);
    void update(E entity);
    void delete(Long id);
}
