package web.Controller;

import core.Domain.Book;
import core.Service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.Converter.BookConverter;
import web.dto.BookDto;
import web.dto.BooksDto;
import web.dto.EmptyJsonResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
public class BookController {

    private static final Logger log = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @Autowired
    private BookConverter bookConverter;


    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public BooksDto getBooks() {
        log.trace("getBooks");

        List<Book> books = bookService.findAll();

        log.trace("getBooks: books={}", books);

        return new BooksDto(bookConverter.convertModelsToDtos(books));
    }


    @RequestMapping(value = "/books/{bookId}", method = RequestMethod.PUT)
    public BookDto updateBook(@PathVariable final Long bookId,
                              @RequestBody final BookDto bookDto) {
        log.trace("updateBook: bookId={}, bookDtoMap={}", bookId, bookDto);

        Book book = new Book(bookDto.getTitle(), bookDto.getAuthor(),bookDto.getIsbn());
        book.setID(bookId);

        Optional<Book> bookOptional = bookService.updateBook(bookId, book);

        Map<String, BookDto> result = new HashMap<>();
        if (bookOptional.isPresent())
            result.put("book", bookConverter.convertModelToDto(bookOptional.get()));
        else
            result.put("book", bookConverter.convertModelToDto(new Book()));

        log.trace("updateBook: result={}", result);

        return result.get("book");
    }

    @RequestMapping(value = "/books", method = RequestMethod.POST)
    public BookDto createBook(@RequestBody final BookDto bookDto) {
        log.trace("createBook: bookDtoMap={}", bookDto);

        Book b = new Book(bookDto.getTitle(), bookDto.getAuthor(),bookDto.getIsbn());
        b.setID(bookDto.getId());
        Book book = bookService.addBook(b);

        BookDto result = bookConverter.convertModelToDto(book);

        log.trace("createBook: result={}", result);
        return result;
    }


    @RequestMapping(value = "books/{bookId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteBook(@PathVariable final Long bookId) {
        log.trace("deleteBook: bookId={}", bookId);

        bookService.deleteBook(bookId);

        log.trace("deleteBook - method end");

        return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.OK);
    }
}
