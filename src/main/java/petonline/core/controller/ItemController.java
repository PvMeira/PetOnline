package petonline.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import petonline.core.config.Paths;
import petonline.core.exceptions.ApiException;
import petonline.core.model.Item;
import petonline.core.model.domain.category.Category;
import petonline.core.model.dto.ItemDTO;
import petonline.core.service.ItemService;

import java.util.List;

@RestController
@RequestMapping(Paths.CONFIG_ITEMS)
public class ItemController {

    @Autowired
    private ItemService service;


    @PostMapping
    @ResponseBody
    public ResponseEntity<ItemDTO>save(@RequestBody ItemDTO item) {
        return new ResponseEntity<>(ItemDTO.toDTO(this.service.save(Item.toEntity(item))), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    @ResponseBody
    public ResponseEntity<ItemDTO> update(  @PathVariable(name = "id", required = true) Integer id
                                          , @RequestBody ItemDTO item) {
        return new ResponseEntity<>(ItemDTO.toDTO(this.service.update(id,Item.toEntity(item))), HttpStatus.OK);
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

    @GetMapping(path = "/categories")
    @ResponseBody
    public ResponseEntity<Category[]> listCategories() {
        return new ResponseEntity<>(Category.values(), HttpStatus.OK);
    }

    @GetMapping(path = "/search")
    @ResponseBody
    public ResponseEntity<Page<ItemDTO>> search(  @RequestParam(name = "page",defaultValue = "1") Integer page
                                             , @RequestParam(name = "size",defaultValue = "10") Integer size
                                             , @RequestParam(name = "name", defaultValue = "") String name) {
        return new ResponseEntity<>(ItemDTO.toPageDTO(this.service.search(name, PageRequest.of(page -1, size))), HttpStatus.OK);
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
