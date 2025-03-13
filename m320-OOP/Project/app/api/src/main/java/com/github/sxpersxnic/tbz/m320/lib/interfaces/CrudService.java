package com.github.sxpersxnic.tbz.m320.lib.interfaces;

import java.util.List;

/// This interface contains the basic CRUD methods of an entity.
/// @param <E> Entity that the service is used for.
/// @param <I> Identifier type of entity. Recommended: {@link java.util.UUID}.
/// @author sxpersxnic
public interface CrudService<E, I> {
    /// A method to find all entities.
    List<E> findAll();
    /// A method to find an entity by its identifier.
    E findById(I id);
    /// A method to delete an entity by its identifier.
    void delete(I id);
    /// A method to create an entity.
    E create(E e);
    /// A method to update an entity.
    E update(E changing, I id);
}
