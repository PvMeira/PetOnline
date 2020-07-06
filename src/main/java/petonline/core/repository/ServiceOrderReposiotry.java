package petonline.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import petonline.core.model.ServiceOrder;

public interface ServiceOrderReposiotry extends PagingAndSortingRepository<ServiceOrder, Integer> {

    @Query("FROM SERVICE_ORDER so WHERE so.service.id = :serviceID ")
    Page<ServiceOrder> search(@Param("serviceID") Integer serviceID, Pageable pageable);
}
