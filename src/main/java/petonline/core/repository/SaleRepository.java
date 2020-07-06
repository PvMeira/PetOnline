package petonline.core.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import petonline.core.model.Sale;

public interface SaleRepository extends PagingAndSortingRepository<Sale,Integer> {
}
