package petonline.core.repository;

import org.springframework.data.repository.CrudRepository;
import petonline.core.model.Permission;

public interface PermissionRepository extends CrudRepository<Permission, Integer> {
}
