package m320.projekt.lib.interfaces;

import java.util.List;

public interface CrudService<E, ID> {

    List<E> findAll();
    E findById(ID id);
    E findByIdForUpdate(ID id);
    E create(E entity);
    void delete(ID id);

    E update(E entity, ID id);
    void merge(E existing, E changing);
}