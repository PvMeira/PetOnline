package petonline.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import petonline.core.exceptions.ApiException;
import petonline.core.model.Item;
import petonline.core.model.Services;
import petonline.core.repository.ServicesRepository;

import java.util.List;

@Service
public class ServicesService {

    private ServicesRepository repository;

    @Autowired
    public ServicesService(ServicesRepository repository) {
        this.repository = repository;
    }

    /**
     * Default Save action for SERVICES
     * @param service
     * @return the saved SERVICES with id
     */
    public Services save(Services service) {
        return this.repository.save(service);
    }

    @Transactional
    public Services update(Integer id, Services services) throws ApiException {
        Services servicesForEdit = this.find(id);
        if(null != servicesForEdit) {
            services.setId(id);
            return this.save(services);
        }
        throw  new ApiException("No Services was found for the given ID");
    }

    /**
     * Default FIND ALL with Page Request
     * @return A PAGE WITH THE CONTENT OF Services TABLE
     */
    public Page<Services> findAll() {
        return this.repository.findAll(PageRequest.of(0,20));
    }

    public List<Services> findAllWithoutPage() {return this.repository.findAllWithoutPagination();}

    /**
     * Search Services method thar recives a name for use in the query
     * @param name Required to pass, "" if no filter needs to be applied
     * @param pageable Size and Page
     * @return A PAGE with the content of search
     */
    public Page<Services>search(String name, Pageable pageable) {
        return this.repository.search(name, pageable);
    }

    /**
     * Default find by action for SERVICES using the ID
     * @param id
     * @return the found SERVICES or null if non was found
     */
    public Services find(Integer id) {
        return this.repository.findById(id).orElse(null);
    }

    /**
     * Action for Toggling status of the SERVICES
     * @param id
     * @return the SERVICES with the changed status
     */
    public Services enableDisable(Integer id) throws ApiException {
        Services services = this.find(id);
        if (null != services) {
            services.setIsActive(!services.getIsActive());
            return this.save(services);
        }
        throw new ApiException("No Service was found.");
    }

}
