package petonline.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import petonline.core.config.Paths;
import petonline.core.model.Sale;
import petonline.core.model.dto.SaleDTO;
import petonline.core.service.SaleService;


@RestController
@RequestMapping(Paths.SALES)
public class SaleController {

    @Autowired
    private SaleService service;


    @PostMapping
    @ResponseBody
    public ResponseEntity<SaleDTO> save(@RequestBody SaleDTO saleDTO) {
        return new ResponseEntity<>(SaleDTO.toDTO(this.service.save(Sale.toEntity(saleDTO))), HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    @ResponseBody
    public ResponseEntity<SaleDTO> find(@PathVariable("id")Integer id) {
        return new ResponseEntity<>(SaleDTO.toDTO(this.service.find(id)),HttpStatus.OK);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<Page<SaleDTO>> findAll(  @RequestParam(name = "page",defaultValue = "0") Integer page
                                                 , @RequestParam(name = "size",defaultValue = "10") Integer size) {
        return new ResponseEntity<>(SaleDTO.toPageSaleDTO(this.service.findAll(PageRequest.of(page,size))),HttpStatus.OK);
    }
}
