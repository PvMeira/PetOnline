package petonline.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import petonline.core.exceptions.ApiException;
import petonline.core.model.Item;
import petonline.core.model.dto.ItemDTO;
import petonline.core.service.ItemService;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService service;


    @PostMapping
    @ResponseBody
    public ResponseEntity<Item>save(@RequestBody Item item) {
        return new ResponseEntity<>(this.service.save(item), HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    @ResponseBody
    public ResponseEntity<ItemDTO>find(@PathVariable(name = "id", required = true) Integer id) {
        Item item = this.service.find(id);
        return new ResponseEntity<>(ItemDTO.toDTO(item), null != item ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<Page<ItemDTO>> findAll() {
        return new ResponseEntity<>(ItemDTO.toPageDTO(this.service.findAll()), HttpStatus.OK);
    }

    @GetMapping(path = "/search")
    @ResponseBody
    public ResponseEntity<Page<ItemDTO>> search(  @RequestParam(name = "page",defaultValue = "0") Integer page
                                             , @RequestParam(name = "size",defaultValue = "10") Integer size
                                             , @RequestParam(name = "name", defaultValue = "") String name) {
        return new ResponseEntity<>(ItemDTO.toPageDTO(this.service.search(name, PageRequest.of(page, size))), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public void remove(@PathVariable(name = "id", required = true) Integer id) {
        this.service.remove(id);
    }

    @PutMapping(path = "/add/{id}/{quantity}")
    @ResponseBody
    public ResponseEntity<ItemDTO> addQuantity(@PathVariable(name = "id", required = true) Integer id,
                                            @PathVariable(name = "quantity", required = true) Integer quantity) throws ApiException {
        return new ResponseEntity<>(ItemDTO.toDTO(this.service.addQuantity(quantity,id)), HttpStatus.OK);
    }

    @PutMapping(path = "/remove/{id}/{quantity}")
    @ResponseBody
    public ResponseEntity<ItemDTO> removeQuantity(@PathVariable(name = "id", required = true) Integer id,
                                               @PathVariable(name = "quantity", required = true) Integer quantity) throws ApiException {
        return new ResponseEntity<>(ItemDTO.toDTO(this.service.removeQuantity(quantity,id)), HttpStatus.OK);
    }
}