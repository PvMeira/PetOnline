package petonline.core.model.domain.category;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Category {

    TOYS(1,"Toys"),
    FOOD(2,"Food"),
    BATH(3,"Bath"),
    ACCESSORIES(4,"Accessories"),
    MISC(5,"Miscellaneous");


    private Integer type;
    private String name;
}
