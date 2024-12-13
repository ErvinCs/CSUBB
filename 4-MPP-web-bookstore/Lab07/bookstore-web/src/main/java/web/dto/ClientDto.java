package web.dto;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClientDto extends BaseDto {
    private String name;
    private String country;

//    public ClientDto(String name, String country) {
//
//    }

    @Override
    public String toString() {
        return "ClientDto{" +
                "name='" + name + '\'' +
                ", country=" + country +
                "} " + super.toString();
    }
}
