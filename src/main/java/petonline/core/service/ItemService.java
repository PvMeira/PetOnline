package petonline.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import petonline.core.exceptions.ApiException;
import petonline.core.model.Item;
import petonline.core.repository.ItemRepository;

@Service
public class ItemService {

    private ItemRepository repository;

    @Autowired
    public ItemService(ItemRepository repository) {
        this.repository = repository;
    }

    /**
     * Default Save action for ITEM
     * @param item
     * @return the saved ITEM with id
     */
    public Item save(Item item) {
        return this.repository.save(item);
    }

    public Item update(Integer id, Item item) throws ApiException {
        Item itemForEdit = this.find(id);
        if(null != itemForEdit) {
            item.setId(id);
            return this.save(item);
        }
        throw  new ApiException("No Item was found for the given ID");
    }

    /**
     * Default FIND using the given ID
     * @param id
     * @return the found ITEM or null if non was found
     */
    public Item find(Integer id) {
        return this.repository.findById(id).orElse(null);
    }

    /**
     * Default FIND ALL with Page Request
     * @return A PAGE WITH THE CONTENT OF ITEM TABLE
     */
    public Page<Item> findAll() {
        return this.repository.findAll(PageRequest.of(0,20));
    }

    /**
     * Search ITEM method thar recives a name for use in the query
     * @param name Required to pass, "" if no filter needs to be applied
     * @param pageable Size and Page
     * @return A PAGE with the content of search
     */
    public Page<Item>search(String name, Pageable pageable) {
        return this.repository.search(name, pageable);
    }

    /**
     * Default remove method for ITEM
     * @param id
     */
    public void remove(Integer id) {
        this.repository.findById(id).ifPresent(this.repository::delete);
    }

    /**
     * Add the given quantity to the QUANTITY from ITEM
     * @param quantity
     * @param id
     * @return
     * @throws ApiException When no ITEM was found.
     */
    public Item addQuantity(Integer quantity, Integer id) throws ApiException {
        Item savedItem = this.find(id);
        if (null != savedItem) {
            savedItem.setQuantity(savedItem.getQuantity() + quantity);
            return this.repository.save(savedItem);
        }
        throw new ApiException("Item not found.");
    }

    /**
     * Remove the given quantity to the QUANTITY from ITEM
     * @param quantity
     * @param id
     * @return
     * @throws ApiException when no ITEM was found or the Quantity was Greater than the found ITEM QUANTITY
     */
    public Item removeQuantity(Integer quantity, Integer id) throws ApiException {
        Item savedItem = this.find(id);
        if (null != savedItem) {
            if (quantity > savedItem.getQuantity()) throw new ApiException("Quantity was greater than current item quantity.");
            savedItem.setQuantity(savedItem.getQuantity() - quantity);
            return this.repository.save(savedItem);
        }
        throw new ApiException("Item not Found");
    }
}
