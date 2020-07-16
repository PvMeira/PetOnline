package petonline.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import petonline.core.model.Item;
import petonline.core.model.Services;

import java.util.List;

public interface ServicesRepository extends PagingAndSortingRepository<Services, Integer> {

    @Query("FROM SERVICES so WHERE so.name like %:name% OR so.description like %:name%")
    Page<Services> search(@Param("name") String name, Pageable pageable);

    @Query("FROM SERVICES")
    List<Services> findAllWithoutPagination();
}
