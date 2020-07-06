package petonline.core.model;

import lombok.*;
import petonline.core.model.dto.ClientDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "CLIENT")
public class Client {

    @Id
    @Column(name = "CPF",nullable = false, length = 11)
    private String cpf;

    @Column(name = "NAME", nullable = false, length = 120)
    private String name;

    @Column(name = "PET_NAME", nullable = false, length = 120)
    private String petName;

    @Column(name = "PHONE_NUMBER", nullable = false, length = 22)
    private String phoneNumber;

    @Column(name = "ADDRESS",length = 120)
    private String address;

    public static Client toEntity(ClientDTO dto) {
        return Client.builder()
                .cpf(dto.getCpf())
                .name(dto.getName())
                .petName(dto.getPetName())
                .phoneNumber(dto.getPhoneNumber())
                .address(dto.getAddress())
                .build();
    }

}
