package petonline.core.model.domain.products;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import petonline.core.model.domain.category.Category;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Products {

    ITEM("Item"),
    SERVICES("Services");

    private String name;

    @JsonCreator
    public static Products forValues(@JsonProperty("name") String name) {
        for (Products distance : Products.values()) {
            if (distance.name.equals(name)) {
                return distance;
            }
        }
        return null;
    }
}
