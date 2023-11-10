package service;

import entity.BaseEntity;

import java.util.List;

public interface CRUDService <E extends BaseEntity> {
    void create(E entity);
    void update(E entity);
    void delete(Long id);
}
