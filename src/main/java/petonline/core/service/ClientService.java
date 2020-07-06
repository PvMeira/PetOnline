package petonline.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import petonline.core.model.Client;
import petonline.core.repository.ClientRepository;

@Service
public class ClientService {

    private ClientRepository repository;

    @Autowired
    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    /**
     * Default Save action for Cliente
     *
     * @param client
     * @return
     */
    public Client save(Client client) {
        return this.repository.save(client);
    }

    /**
     * Search Client by his CPF
     * @param cpf
     * @return the Client found or null if none was found
     */
    public Client findByCpf(String cpf) {
        return this.repository.findById(cpf).orElse(null);
    }

}
