package Config;

import Service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;

@Configuration
public class ServerConfig {

    @Bean
    ClientService clientService() {return new ClientServiceImpl();}
    @Bean
    BookService bookService() {return new BookServiceImpl();}
    @Bean
    RentalService rentalService() {return new RentalServiceImpl();}

    @Bean
    RmiServiceExporter rmiServiceExporter()
    {
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setServiceName("ClientService");
        exporter.setServiceInterface(ClientService.class);
        exporter.setService(clientService());

        return exporter;
    }

    @Bean
    RmiServiceExporter rmiServiceExporter2()
    {
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setServiceName("BookService");
        exporter.setServiceInterface(BookService.class);
        exporter.setService(bookService());

        return exporter;
    }

    @Bean
    RmiServiceExporter rmiServiceExporter3()
    {
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setServiceName("RentalService");
        exporter.setServiceInterface(RentalService.class);
        exporter.setService(rentalService());

        return exporter;
    }


}
