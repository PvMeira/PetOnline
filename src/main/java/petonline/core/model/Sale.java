package petonline.core.model;

import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import petonline.core.model.domain.salesType.SalesType;
import petonline.core.model.dto.SaleDTO;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "SALE")
@SequenceGenerator(name = "SALE_SEQ", sequenceName = "SALE_SEQ", allocationSize = 1)
public class Sale {

    @Id
    @GeneratedValue(generator = "SALE_SEQ")
    @Column(name = "ID")
    private Integer id;

    @ManyToMany
    @JoinTable(
            name = "service_order_item",
            joinColumns = @JoinColumn(name = "sale_id"),
            inverseJoinColumns = @JoinColumn(name = "service_order_id"))
    private Set<ServiceOrder> serviceOrderList;

    @ManyToMany
    @JoinTable(
            name = "sale_item",
            joinColumns = @JoinColumn(name = "sale_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private Set<Item> itemList;

    @ManyToOne
    @JoinColumn(name = "CLIENT_CPF")
    private Client client;

    @Column(name = "SALE_DATE", nullable = false)
    private LocalDateTime saleDate;

    @Column(name = "VALUE", nullable = false)
    private BigDecimal value;

    @Column(name = "OBSERVATION", length = 300)
    private String observation;

    @Column(name = "TYPE", nullable = false)
    private SalesType type;

    public static Sale toEntity(SaleDTO sale) {
        return Sale.builder()
                .id(sale.getId())
                .itemList(sale.getItemList().stream().map(Item::toEntity).collect(Collectors.toSet()))
                .serviceOrderList(sale.getServiceOrderList().stream().map(ServiceOrder::toEntity).collect(Collectors.toSet()))
                .client(Client.toEntity(sale.getClient()))
                .saleDate(sale.getSaleDate())
                .value(sale.getValue())
                .observation(sale.getObservation())
                .type(sale.getType())
                .build();
    }

    public static Page<Sale> toSaleDTO(Page<SaleDTO> sale) {
        List<Sale> content = sale.map(Sale::toEntity).getContent();
        return new PageImpl<>(content,sale.getPageable(),sale.getTotalElements());
    }
}
