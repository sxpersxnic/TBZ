package com.github.sxpersxnic.tbz.m320.lib.interfaces;

import java.util.List;

public interface CrudService<E, I> {
    E findById(I id);
    List<E> findAll();
    void delete(I id);
    E create(E e);
    E update(E changing, I id);
    void merge(E changing, E existing);
}
