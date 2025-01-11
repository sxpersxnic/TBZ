package com.m320.api.lib.interfaces;
import java.util.List;

public interface CrudService<E, ID> {
    E findById(ID id);
    E findByIdForUpdate(ID id);
    List<E> findAll();

    E create(E e);
    E update(E changing, ID id);
    void delete(ID id);
    void merge(E existing, E changing);

}
