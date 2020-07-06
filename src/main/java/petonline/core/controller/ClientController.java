package petonline.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import petonline.core.config.Paths;
import petonline.core.model.Client;
import petonline.core.model.dto.ClientDTO;
import petonline.core.service.ClientService;

@RestController
@RequestMapping(Paths.CONFIG_CLIENT)
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<ClientDTO> save(@RequestBody ClientDTO clientDTO) {
        return new ResponseEntity<>(ClientDTO.toDTO(this.clientService.save(Client.toEntity(clientDTO))), HttpStatus.CREATED);
    }

    @GetMapping(path = "/{cpf}")
    @ResponseBody
    public ResponseEntity<ClientDTO> findByCpf(@PathVariable("cpf")String cpf) {
        return new ResponseEntity<>(ClientDTO.toDTO(this.clientService.findByCpf(cpf)),HttpStatus.OK);
    }
}
