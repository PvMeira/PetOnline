package petonline.core.model.domain.salesType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class SalesTypeConverter implements AttributeConverter<SalesType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(SalesType salesType) {
        if (salesType == null) {
            return null;
        }
        return salesType.getType();
    }

    @Override
    public SalesType convertToEntityAttribute(Integer code) {
        if (code == null) {
            return null;
        }

        return Stream.of(SalesType.values())
                .filter(c -> c.getType().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
