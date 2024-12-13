package web.dto;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RentalDto extends BaseDto {
    private Long bid;
    private Long cid;

//    public RentalDto(Long bookID, Long clientID) {
//
//    }

    @Override
    public String toString() {
        return "RentalDto{" +
                "bookID='" + bid + '\'' +
                ", clientID=" + cid +
                "} " + super.toString();
    }
}
