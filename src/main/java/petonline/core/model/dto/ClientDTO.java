package petonline.core.model.dto;

import lombok.*;
import petonline.core.model.Client;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ClientDTO {

    private String cpf;
    private String name;
    private String petName;
    private String phoneNumber;
    private String address;


    public static ClientDTO toDTO(Client client) {
        return ClientDTO.builder()
                .cpf(client.getCpf())
                .name(client.getName())
                .petName(client.getPetName())
                .phoneNumber(client.getPhoneNumber())
                .address(client.getAddress())
                .build();
    }
}
