package petonline.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import petonline.core.model.ServiceOrder;
import petonline.core.repository.ServiceOrderReposiotry;

@Service
public class ServiceOrderService {

    private ServiceOrderReposiotry reposiotry;

    @Autowired
    public ServiceOrderService(ServiceOrderReposiotry reposiotry) {
        this.reposiotry = reposiotry;
    }

    /**
     * Create a service-order
     * @param so
     * @return
     */
    public ServiceOrder create(ServiceOrder so) {
        return this.reposiotry.save(so);
    }

    /**
     * Find Service order by ID
     * @param id
     * @return the Service order if found or null
     */
    public ServiceOrder find(Integer id) {
        return this.reposiotry.findById(id).orElse(null);
    }

    public Page<ServiceOrder> search(Integer serviceId, Pageable pageable) {
        if(null == serviceId) return this.reposiotry.findAll(pageable);
        return this.reposiotry.search(serviceId,pageable);
    }


}
