package petonline.core.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "SERVICES")
@SequenceGenerator(name = "SERVICES_SEQ", sequenceName = "SERVICES_SEQ", allocationSize = 1)
public class Services {

    @Id
    @GeneratedValue(generator = "SERVICES_SEQ")
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME",length = 120, nullable = false)
    private String name;

    @Column(name = "DESCRIPTION", length = 300)
    private String description;

    @Column(name = "IS_ACTIVE",nullable = false)
    private Boolean isActive;

    @Column(name = "VALUE", nullable = false)
    private BigDecimal value;
}
