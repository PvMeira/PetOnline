package petonline.core.model;

import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import petonline.core.model.domain.category.Category;
import petonline.core.model.dto.ItemDTO;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "ITEM")
@SequenceGenerator(name = "ITEM_SEQ", sequenceName = "ITEM_SEQ", allocationSize = 1)
public class Item {

    @Id
    @GeneratedValue(generator = "ITEM_SEQ")
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME", nullable = false, length = 120)
    private String name;

    @Column(name = "DESCRIPTION", length = 300)
    private String description;

    @Column(name = "VALUE", nullable = false)
    private BigDecimal value;

    @Column(name = "QUANTITY", nullable = false)
    private Integer quantity = 0;

    @Transient
    private Integer salesQuantity;

    @Column(name = "IMAGE", columnDefinition = "TEXT")
    private String image;

    @Column(name = "CATEGORY", nullable = false)
    private Category category;


    public static Item toEntity(ItemDTO dto) {
        return Item.builder()
                .id(dto.getId())
                .name(dto.getName())
                .salesQuantity(dto.getSalesQuantity())
                .description(dto.getDescription())
                .image(dto.getImage())
                .quantity(dto.getQuantity())
                .value(dto.getValue())
                .category(dto.getCategory())
                .build();
    }

    public static Page<Item> toPageDTO(Page<ItemDTO> page) {
        List<Item> content = page.map(Item::toEntity).getContent();
        return new PageImpl<>(content, page.getPageable(), page.getTotalElements());
    }

}
