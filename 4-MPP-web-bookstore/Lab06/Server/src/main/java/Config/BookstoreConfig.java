package Config;

import Repository.DBRepository.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookstoreConfig {

    @Bean
    ClientRepository clientRepository() {return new ClientDBRepository();}

    @Bean
    BookRepository bookRepository() {return new BookDBRepository();}

    @Bean
    RentalRepository rentalRepository() {return new RentalDBRepository();}
}
