package petonline.core.model.dto;

import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import petonline.core.model.ServiceOrder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ServiceOrderDTO {

    private Integer id;
    private ServicesDTO service;
    private BigDecimal discountValue;
    private LocalDate serviceDate;
    private String observation;


    public static ServiceOrderDTO toDTO(ServiceOrder e) {
        return ServiceOrderDTO.builder()
                              .id(e.getId())
                              .service(ServicesDTO.toDTO(e.getService()))
                              .discountValue(e.getDiscountValue())
                              .serviceDate(e.getServiceDate())
                              .observation(e.getObservation())
                              .build();
    }

    public static Page<ServiceOrderDTO> toPageDTO(Page<ServiceOrder> page) {
        List<ServiceOrderDTO> content = page.map(ServiceOrderDTO::toDTO).getContent();
        return new PageImpl<>(content,page.getPageable(),page.getTotalElements());
    }
}
