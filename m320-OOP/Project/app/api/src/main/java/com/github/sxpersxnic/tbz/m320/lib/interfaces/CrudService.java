package com.github.sxpersxnic.tbz.m320.lib.interfaces;

import java.util.List;

/**
 * This interface contains the basic CRUD methods of an entity.
 *
 * @param <E> Entity that the service is used for.
 * @param <I> Identifier type of entity. Recommended: {@link java.util.UUID}.
 *
 * @author sxpersxnic
 */
public interface CrudService<E, I> {
    E findById(I id);
    List<E> findAll();
    void delete(I id);
    E create(E e);
    E update(E changing, I id);
    void merge(E changing, E existing);
}
