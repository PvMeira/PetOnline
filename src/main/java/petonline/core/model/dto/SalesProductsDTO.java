package petonline.core.model.dto;

import lombok.*;
import petonline.core.model.domain.category.Category;
import petonline.core.model.domain.products.Products;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SalesProductsDTO {

    private Products type;
    private Integer id;
    private String name;
    private String description;
    private Boolean isActive;
    private BigDecimal value;

    private Integer quantity;
    private Category category;
}
