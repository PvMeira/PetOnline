package petonline.core.model;

import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import petonline.core.model.dto.ItemDTO;
import petonline.core.model.dto.ServiceOrderDTO;
import petonline.core.model.dto.ServicesDTO;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "SERVICE_ORDER")
@SequenceGenerator(name = "SERVICE_ORDER_SEQ", sequenceName = "SERVICE_ORDER_SEQ", allocationSize = 1)
public class ServiceOrder {

    @Id
    @GeneratedValue(generator = "SERVICE_ORDER_SEQ")
    @Column(name = "ID")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "SERVICES_ID")
    private Services service;
    @Column(name = "DISCOUNT_VALUE")
    private BigDecimal discountValue;
    @Column(name = "SERVICE_DATE", nullable = false)
    private LocalDate serviceDate;
    @Column(name = "OBSERVATION",length = 300)
    private String observation;

    public static ServiceOrder toEntity(ServiceOrderDTO e) {
        return ServiceOrder.builder()
                            .id(e.getId())
                            .service(Services.toEntity(e.getService()))
                            .discountValue(e.getDiscountValue())
                            .serviceDate(e.getServiceDate())
                            .observation(e.getObservation())
                            .build();
    }

    public static Page<ServiceOrder> toPageEntity(Page<ServiceOrderDTO> page) {
        List<ServiceOrder> content = page.map(ServiceOrder::toEntity).getContent();
        return new PageImpl<>(content,page.getPageable(),page.getTotalElements());
    }
}
