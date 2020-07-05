package petonline.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import petonline.core.model.Services;

public interface ServicesRepository extends PagingAndSortingRepository<Services,Integer> {

    @Query("FROM SERVICES i WHERE LOWER(i.name) like %:name% ")
    Page<Services> search(@Param("name") String name, Pageable pageable);
}
