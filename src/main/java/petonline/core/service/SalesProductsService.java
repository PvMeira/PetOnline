package petonline.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import petonline.core.model.domain.products.Products;
import petonline.core.model.dto.SalesProductsDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalesProductsService {

    private ItemService itemService;
    private ServicesService servicesService;

    @Autowired
    public SalesProductsService(ItemService itemService, ServicesService servicesService) {
        this.itemService = itemService;
        this.servicesService = servicesService;
    }


    public List<SalesProductsDTO> findAll() {
        List<SalesProductsDTO> response = new ArrayList<>(0);
        response.addAll(this.itemService
                            .findAllWithoutPage()
                            .stream()
                            .map(item -> SalesProductsDTO
                                         .builder()
                                         .id(item.getId())
                                         .quantity(item.getQuantity())
                                         .category(item.getCategory())
                                         .description(item.getDescription())
                                         .name(item.getName())
                                         .value(item.getValue())
                                         .type(Products.ITEM)
                                         .build()).collect(Collectors.toList()));
        response.addAll(this.servicesService
                            .findAllWithoutPage()
                            .stream()
                            .map(services -> SalesProductsDTO
                                             .builder()
                                             .id(services.getId())
                                             .isActive(services.getIsActive())
                                             .description(services.getDescription())
                                             .name(services.getName())
                                             .value(services.getValue())
                                             .type(Products.SERVICES)
                                             .build()).collect(Collectors.toList()));
        return response;
    }
}
