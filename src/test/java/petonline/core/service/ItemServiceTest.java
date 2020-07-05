package petonline.core.service;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import petonline.core.exceptions.ApiException;
import petonline.core.model.Item;
import petonline.core.repository.ItemRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ItemServiceTest {

    private ItemRepository repository = mock(ItemRepository.class);
    private ItemService service = new ItemService(repository);

    @Test
    void save() {
        Item itemToBeSave = this.createItemToBeSave();
        Item item = this.createItem();
        when(repository.save(any())).thenReturn(item);
        Item savedItem = service.save(itemToBeSave);
        assertEquals(savedItem.getId(), 1);
    }

    @Test
    void find() {
        Item itemToBeReturn = this.createItem();
        when(repository.findById(anyInt())).thenReturn(Optional.of(itemToBeReturn));
        Item item = this.service.find(1);
        assertEquals(item.getId(),itemToBeReturn.getId());
    }
    @Test
    void noItemWasFound() {
        when(repository.findById(anyInt())).thenReturn(Optional.empty());
        Item item = this.service.find(99);
        assertNull(item);
    }

    @Test
    void findAll() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        when(repository.findAll(any(PageRequest.class))).thenReturn(this.createPageWithContent(pageRequest));
        Page<Item> items = this.service.findAll();
        assertEquals(items.getTotalElements(), 10);

    }

    @Test
    void search() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        when(repository.search(anyString(),any(PageRequest.class))).thenReturn(this.createPageWithContent(pageRequest));
        Page<Item> items = this.service.search("Moc", pageRequest);
        assertEquals(items.getTotalElements(), 10);
    }

    @Test
    void removeWithExistingItem() {
        Item itemToBeReturn = this.createItem();
        when(repository.findById(anyInt())).thenReturn(Optional.of(itemToBeReturn));
        this.service.remove(1);
        verify(repository,times(1)).delete(any());
    }

    @Test
    void removeWithNotFoundItem() {
        when(repository.findById(anyInt())).thenReturn(Optional.empty());
        this.service.remove(122);
        verify(repository,times(0)).delete(any());
    }

    @Test
    void addQuantity() throws ApiException {
        Integer quantity = 1;
        Item itemToBeFound = this.createItem();
        when(repository.findById(anyInt())).thenReturn(Optional.of(itemToBeFound));

        Item itemToBeReturn = this.createItem();
        itemToBeReturn.setQuantity(itemToBeReturn.getQuantity() + quantity);
        when(repository.save(any())).thenReturn(itemToBeReturn);

        Item addQuantity = this.service.addQuantity(1, quantity);
        assertEquals(addQuantity.getQuantity(),11);
    }

    @Test
    void addQuantityItemNotFound() {
        when(repository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(ApiException.class, () -> this.service.addQuantity(1, 1));
    }

    @Test
    void removeQuantity() throws ApiException {
        Integer quantity = 1;
        Item itemToBeFound = this.createItem();
        when(repository.findById(anyInt())).thenReturn(Optional.of(itemToBeFound));

        Item itemToBeReturn = this.createItem();
        itemToBeReturn.setQuantity(itemToBeReturn.getQuantity() - quantity);
        when(repository.save(any())).thenReturn(itemToBeReturn);

        Item removeQuantity = this.service.removeQuantity(1, quantity);
        assertEquals(removeQuantity.getQuantity(),9);
    }

    @Test
    void removeQuantityItemNotFound() {
        when(repository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(ApiException.class, () -> this.service.removeQuantity(1, 1));
    }
    @Test
    void removeQuantityQuantityGreaterThanActualQuantity() {
        Integer quantity = 111;
        Item itemToBeFound = this.createItem();
        when(repository.findById(anyInt())).thenReturn(Optional.of(itemToBeFound));
        Item itemToBeReturn = this.createItem();
        when(repository.save(any())).thenReturn(itemToBeReturn);
        assertThrows(ApiException.class, () -> this.service.removeQuantity(quantity, 1));
    }

    private Item createItemToBeSave() {
        Item mockItem = new Item();
        mockItem.setName("Mock Item");
        mockItem.setDescription("Mock Descrition");
        mockItem.setQuantity(10);
        mockItem.setValue(new BigDecimal("1000.29"));
        mockItem.setImage("BASE64::::#(*#(*(#(");
        return mockItem;
    }

    private Item createItem() {
        Item item = this.createItemToBeSave();
        item.setId(1);
        return item;
    }

    private Page<Item> createPageWithContent(Pageable pageable) {
        List<Item> items = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            items.add(createItem());

        }
        return new PageImpl<>(items,pageable, 10);
    }
}