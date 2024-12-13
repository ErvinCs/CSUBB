package client;

import client.UI.Console;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import web.dto.BookDto;
import web.dto.BooksDto;
import web.dto.ClientsDto;
import web.dto.RentalsDto;


public class ClientApp {
    public static void main(String[] args) {
//        AnnotationConfigApplicationContext context =
//                new AnnotationConfigApplicationContext("client.Configuration");

//        RestTemplate restTemplate = context.getBean(RestTemplate.class);

//        BooksDto booksDto;
//        booksDto = restTemplate.getForObject("http://localhost:8080/api/books", BooksDto.class);
//        booksDto.getBooks()
//                .forEach(b -> System.out.println(b.toString()));

//        BookDto book = restTemplate
//                .postForObject("http://localhost:8080/api/books", new BookDto("Title", "Author", 111), BookDto.class);
//        System.out.println(book.toString());

        Console console = new Console();
        console.runConsole();
    }
}
