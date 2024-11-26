package m320.projekt.lib.interfaces;

public interface CrudMapper<E, REQ, RES> {
    E fromDTO(REQ dto);

    RES toDTO(E dto);
}
