package petonline.core.model.dto;

import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import petonline.core.model.Item;
import petonline.core.model.Services;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ServicesDTO {

    private Integer id;
    private String name;
    private String description;
    private Boolean isActive;
    private BigDecimal value;


    public static ServicesDTO toDTO(Services services) {
        return ServicesDTO.builder()
                .id(services.getId())
                .name(services.getName())
                .description(services.getDescription())
                .isActive(services.getIsActive())
                .value(services.getValue())
                .build();
    }
    public static Page<ServicesDTO> toPageDTO(Page<Services> page) {
        List<ServicesDTO> content = page.map(ServicesDTO::toDTO).getContent();
        return new PageImpl<>(content, page.getPageable(), page.getTotalElements());
    }
}
