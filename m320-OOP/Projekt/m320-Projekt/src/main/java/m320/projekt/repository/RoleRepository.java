package m320.projekt.repository;

import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import m320.projekt.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "jakarta.persistence.lock.timeout", value = "0")})
    @Query("SELECT r FROM Role r WHERE r.id = :id")
    Optional<Role> findByIdForUpdate(Integer id);

    boolean existsByName(String name);
}
