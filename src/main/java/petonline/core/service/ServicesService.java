package petonline.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import petonline.core.exceptions.ApiException;
import petonline.core.model.Services;
import petonline.core.repository.ServicesRepository;

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

    /**
     * Default FindAll action for SERVICES
     * @return a Iterable objct of the existing Services
     */
    public Iterable<Services> findAll() {
        return this.repository.findAll();
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
