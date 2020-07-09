package petonline.core.model.domain.category;

import petonline.core.model.domain.salesType.SalesType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class CategoryTypeConverter  implements AttributeConverter<Category, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Category category) {
        if (category == null) {
            return null;
        }
        return category.getType();
    }

    @Override
    public Category convertToEntityAttribute(Integer code) {
        if (code == null) {
            return null;
        }

        return Stream.of(Category.values())
                .filter(c -> c.getType().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
