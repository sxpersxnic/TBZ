package com.m320.api.lib.interfaces;

import java.util.List;

public interface CrudService<E, ID, REQ, RES> {

    RES findById(ID id);

    E findByIdForUpdate(ID id);

    List<RES> findAll();

    RES update(REQ changing, ID id);

    void delete(ID id);

    void merge(E existing, E changing);

}
