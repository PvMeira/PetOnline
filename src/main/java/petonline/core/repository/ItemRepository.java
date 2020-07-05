package petonline.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import petonline.core.model.Item;

public interface ItemRepository extends PagingAndSortingRepository<Item, Integer> {

    Page<Item> findAll(Pageable pageable);

    @Query("FROM ITEM i WHERE LOWER(i.name) like %:name% ")
    Page<Item> search(@Param("name") String name, Pageable pageable);
}
