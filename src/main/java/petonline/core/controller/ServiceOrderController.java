package petonline.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import petonline.core.config.Paths;
import petonline.core.model.ServiceOrder;
import petonline.core.model.dto.ServiceOrderDTO;
import petonline.core.service.ServiceOrderService;

@RestController
@RequestMapping(Paths.SERVICE_ORDER)
public class ServiceOrderController {

    @Autowired
    private ServiceOrderService service;


    @PostMapping
    @ResponseBody
    public ResponseEntity<ServiceOrderDTO> create(@RequestBody ServiceOrderDTO dto) {
        return new ResponseEntity<>(ServiceOrderDTO.toDTO(this.service.create(ServiceOrder.toEntity(dto))), HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    @ResponseBody
    public ResponseEntity<ServiceOrderDTO>find(@PathVariable(name = "id") Integer id) {
        ServiceOrder item = this.service.find(id);
        return new ResponseEntity<>(ServiceOrderDTO.toDTO(item), null != item ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/search")
    @ResponseBody
    public ResponseEntity<Page<ServiceOrderDTO>> search(  @RequestParam(name = "page",defaultValue = "0") Integer page
                                                        , @RequestParam(name = "size",defaultValue = "10") Integer size
                                                        , @RequestParam(name = "serviceID", defaultValue = "") Integer serviceID) {
        return new ResponseEntity<>(ServiceOrderDTO.toPageDTO(this.service.search(serviceID, PageRequest.of(page, size))), HttpStatus.OK);
    }
}
