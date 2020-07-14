package petonline.core.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "USER_DETAIL")
@SequenceGenerator(name = "USER_DETAIL_SEQ", sequenceName = "USER_DETAIL_SEQ", allocationSize = 1)
public class User {

    @Id
    @GeneratedValue(generator = "USER_DETAIL_SEQ")
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME", nullable = false, length = 120)
    private String name;

    @Column(name = "EMAIL", nullable = false, length = 100, unique = true)
    private String email;

    @Column(name = "STATUS", nullable = false, length = 1)
    private String status;

    @Column(name = "PASSWORD", nullable = false, length = 400)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(  name = "USER_DETAIL_PERMISSION"
            , joinColumns = @JoinColumn(name = "ID_USER_DETAIL")
            , inverseJoinColumns = @JoinColumn(name = "ID_PERMISSION"))
    private List<Permission> permissions;




}
