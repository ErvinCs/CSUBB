package web.dto;


import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookDto extends BaseDto {
    private String title;
    private String author;
    private int isbn;

//    public BookDto(String name, String author, int isbn) {
//        super();
//    }

    @Override
    public String toString() {
        return "BookDto{" +
                "title='" + title + '\'' +
                ", author=" + author +
                ", isbn=" + isbn +
                "} " + super.toString();
    }
}
