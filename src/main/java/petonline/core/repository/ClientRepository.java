package petonline.core.repository;

import org.springframework.data.repository.CrudRepository;
import petonline.core.model.Client;

public interface ClientRepository extends CrudRepository<Client, String> {
}
