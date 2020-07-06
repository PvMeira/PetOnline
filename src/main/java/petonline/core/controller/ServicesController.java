package petonline.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import petonline.core.config.Paths;
import petonline.core.exceptions.ApiException;
import petonline.core.model.Services;
import petonline.core.model.dto.ServicesDTO;
import petonline.core.service.ServicesService;

@RestController
@RequestMapping(Paths.CONFIG_SERVICES)
public class ServicesController {

    @Autowired
    private ServicesService service;


    @PostMapping
    @ResponseBody
    public ResponseEntity<ServicesDTO> save(@RequestBody ServicesDTO servicesDTO) {
        return new ResponseEntity<>(ServicesDTO.toDTO(this.service.save(Services.toEntity(servicesDTO))), HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    @ResponseBody
    public ResponseEntity<ServicesDTO> find(@PathVariable(name = "id") Integer id) {
        Services services = this.service.find(id);
        return new ResponseEntity<>(ServicesDTO.toDTO(services), null != services ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<Iterable<Services>> findAll() {
        return new ResponseEntity<>(this.service.findAll(), HttpStatus.OK);
    }

    @PutMapping(path = "/enable-disable/{id}")
    public ResponseEntity<ServicesDTO>enableDisable(@PathVariable(name = "id") Integer id) throws ApiException {
        return new ResponseEntity<>(ServicesDTO.toDTO(this.service.enableDisable(id)),HttpStatus.OK);
    }

}
