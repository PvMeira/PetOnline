package petonline.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import petonline.core.exceptions.ApiException;
import petonline.core.exceptions.SaleException;
import petonline.core.model.Item;
import petonline.core.model.Sale;
import petonline.core.repository.SaleRepository;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDateTime;

@Service
public class SaleService {

    private SaleRepository repository;
    private ItemService itemService;

    @Autowired
    public SaleService(SaleRepository repository, ItemService itemService) {
        this.repository = repository;
        this.itemService = itemService;
    }


    /**
     * Default action for saving a Sale
     *
     * @param sale
     * @return
     */
    public Sale save(Sale sale) throws SaleException {
        try {
            sale.setValue(BigDecimal.ZERO);
            for (Item s : sale.getItemList()) {
                BigDecimal value = s.getValue().multiply(new BigDecimal(s.getSalesQuantity()), MathContext.DECIMAL64);
                this.itemService.removeQuantity(s.getSalesQuantity(), s.getId());
                sale.setValue(sale.getValue().add(value));
            }
            sale.getServiceOrderList().forEach(a -> {
                BigDecimal value = a.getService().getValue().subtract(a.getDiscountValue(), MathContext.DECIMAL64);
                sale.setValue(sale.getValue().add(value));
            });
            sale.setSaleDate(LocalDateTime.now());
            return this.repository.save(sale);
        } catch (ApiException e) {
            throw new SaleException("Cannot complete Sale, quantity of one or more Item is grater than the current Stock.");
        } catch (Exception e) {
            throw new SaleException("A error has occur, please try again later. ");
        }
    }

    /**
     * Default findBy for Sale
     *
     * @param id
     * @return the found sale or null if none was found
     */
    public Sale find(Integer id) {
        return this.repository.findById(id).orElse(null);
    }

    /**
     * Default action for listing all Sales with pagination
     *
     * @param pageable
     * @return
     */
    public Page<Sale> findAll(Pageable pageable) {
        return this.repository.findAll(pageable);
    }
}
