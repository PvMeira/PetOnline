package petonline.core.model.dto;

import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import petonline.core.model.Item;
import petonline.core.model.domain.category.Category;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ItemDTO {

    private Integer id;
    private String name;
    private String description;
    private BigDecimal value;
    private Integer quantity;
    private String image;
    private Integer salesQuantity;
    private Category category;


    public static ItemDTO toDTO(Item item) {
        return ItemDTO.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .image(item.getImage())
                .quantity(item.getQuantity())
                .value(item.getValue())
                .category(item.getCategory())
                .build();
    }

    public static Page<ItemDTO> toPageDTO(Page<Item> page) {
        List<ItemDTO> content = page.map(ItemDTO::toDTO).getContent();
        return new PageImpl<>(content, page.getPageable(), page.getTotalElements());
    }
}
