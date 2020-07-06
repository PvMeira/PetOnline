package petonline.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import petonline.core.model.Sale;
import petonline.core.repository.SaleRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class SaleService {

    private SaleRepository repository;

    @Autowired
    public SaleService(SaleRepository repository) {
        this.repository = repository;
    }


    /**
     * Default action for saving a Sale
     * @param sale
     * @return
     */
    public Sale save(Sale sale) {
        sale.setValue(BigDecimal.ZERO);
        sale.getItemList().forEach(s-> {
            sale.setValue(sale.getValue().add(s.getValue()));
        });
        sale.getServiceOrderList().forEach(a-> {
            sale.setValue(sale.getValue().add(a.getService().getValue()));
        });
        sale.setSaleDate(LocalDateTime.now());
        return this.repository.save(sale);
    }

    /**
     * Default findBy for Sale
     * @param id
     * @return the found sale or null if none was found
     */
    public Sale find(Integer id) {
        return this.repository.findById(id).orElse(null);
    }

    /**
     * Default action for listing all Sales with pagination
     * @param pageable
     * @return
     */
    public Page<Sale> findAll(Pageable pageable) {
        return this.repository.findAll(pageable);
    }
}
