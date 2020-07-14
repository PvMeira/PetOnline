package petonline.core.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "PERMISSION")
@SequenceGenerator(name = "PERMISSION_SEQ", sequenceName = "PERMISSION_SEQ", allocationSize = 1)
public class Permission implements GrantedAuthority {

    @Id
    @GeneratedValue(generator = "PERMISSION_SEQ")
    @Column(name = "ID")
    private Integer id;

    @Column(name = "DESCRIPTION", nullable = false, length = 120)
    private String description;

    @Override
    public String getAuthority() {
        return this.description;
    }
}
