package petonline.core.repository;

import org.springframework.data.repository.CrudRepository;
import petonline.core.model.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
