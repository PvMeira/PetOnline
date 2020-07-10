package petonline.core.model.domain.category;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Category {

    TOYS(1, "Toys"),
    FOOD(2, "Food"),
    BATH(3, "Bath"),
    ACCESSORIES(4, "Accessories"),
    MISC(5, "Miscellaneous");


    private Integer type;
    private String name;

    @JsonCreator
    public static Category forValues(@JsonProperty("type") Integer type,
                                     @JsonProperty("name") String name) {
        for (Category distance : Category.values()) {
            if (distance.type.equals(type) && distance.name.equalsIgnoreCase(name)) {
                return distance;
            }
        }
        return null;
    }
}
