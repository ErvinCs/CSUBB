package web.Converter;

import core.Domain.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import web.dto.BookDto;


@Component
public class BookConverter extends BaseConverter<Book, BookDto> {

    private static final Logger log = LoggerFactory.getLogger(BookConverter.class);

    @Override
    public Book convertDtoToModel(BookDto dto) {
        throw new RuntimeException("not yet implemented");
    }

    @Override
    public BookDto convertModelToDto(Book book) {
        BookDto bookDto = new BookDto(book.getName(), book.getAuthor(), book.getISBN());
        bookDto.setId(book.getID());
        //in BookDto - @Builder
        return bookDto;
    }
}
