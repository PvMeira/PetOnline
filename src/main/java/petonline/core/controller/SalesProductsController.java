package petonline.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import petonline.core.config.Paths;
import petonline.core.model.dto.SalesProductsDTO;
import petonline.core.service.SalesProductsService;
import java.util.List;

@RestController
@RequestMapping(Paths.SALES_PRODUCTS)
public class SalesProductsController {

    @Autowired
    private SalesProductsService service;


    @GetMapping
    @ResponseBody
    public ResponseEntity<List<SalesProductsDTO>> search() {
        return new ResponseEntity<>(this.service.findAll(), HttpStatus.OK);
    }
}
