package petonline.core.model.domain.products;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class ProductsTypeConverter implements AttributeConverter<Products, String> {

    @Override
    public String convertToDatabaseColumn(Products salesType) {
        if (salesType == null) {
            return null;
        }
        return salesType.getName();
    }

    @Override
    public Products convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(Products.values())
                .filter(c -> c.getName().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
