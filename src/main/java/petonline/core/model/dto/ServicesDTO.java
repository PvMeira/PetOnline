package petonline.core.model.dto;

import lombok.*;
import petonline.core.model.Services;

import java.math.BigDecimal;

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
}
