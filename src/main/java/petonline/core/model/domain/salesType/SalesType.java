package petonline.core.model.domain.salesType;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum SalesType {
    SERVICE(1,"Service"),
    SALE(2,"Sale"),
    SERVICE_SALE(3,"Sale and Service."),
    OTHER(4,"Other");


    private Integer type;
    private String name;

}
