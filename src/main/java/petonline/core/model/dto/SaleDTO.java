package petonline.core.model.dto;

import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import petonline.core.model.Sale;
import petonline.core.model.domain.salesType.SalesType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SaleDTO {

    private Integer id;

    private Set<ServiceOrderDTO> serviceOrderList;

    private Set<ItemDTO> itemList;

    private ClientDTO client;

    private LocalDateTime saleDate;

    private BigDecimal value;

    private String observation;

    private SalesType type;


    public static SaleDTO toDTO(Sale sale) {
        return SaleDTO.builder()
                .id(sale.getId())
                .itemList(sale.getItemList().stream().map(ItemDTO::toDTO).collect(Collectors.toSet()))
                .serviceOrderList(sale.getServiceOrderList().stream().map(ServiceOrderDTO::toDTO).collect(Collectors.toSet()))
                .client(ClientDTO.toDTO(sale.getClient()))
                .saleDate(sale.getSaleDate())
                .value(sale.getValue())
                .observation(sale.getObservation())
                .type(sale.getType())
                .build();
    }

    public static Page<SaleDTO> toPageSaleDTO(Page<Sale> sale) {
        List<SaleDTO> content = sale.map(SaleDTO::toDTO).getContent();
        return new PageImpl<>(content,sale.getPageable(),sale.getTotalElements());
    }
}
