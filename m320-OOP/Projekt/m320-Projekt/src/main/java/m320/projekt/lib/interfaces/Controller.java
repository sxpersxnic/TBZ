package m320.projekt.lib.interfaces;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface Controller<REQ, RES, ID> {
    ResponseEntity<RES> post(@Valid @RequestBody REQ request);
    ResponseEntity<RES> patch(@PathVariable ID id, @RequestBody REQ request);
    ResponseEntity<RES> getById(@PathVariable ID id);
    ResponseEntity<List<RES>> getAll();
    ResponseEntity<Void> delete(@PathVariable ID id);
}
