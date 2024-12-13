package Config;

import Domain.Book;
import Domain.Client;
import Domain.Rental;
import Service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

@Configuration
public class ClientConfig {
    @Bean
    BookService bookServiceClient() { return new BookServiceClient();}
    @Bean
    ClientService clientServiceClient() {return new ClientServiceClient();}
    @Bean
    RentalService rentalServiceClient() {return new RentalServiceClient();}

    @Bean
    RmiProxyFactoryBean rmiProxyFactoryBean()
    {
        RmiProxyFactoryBean proxy = new RmiProxyFactoryBean();
        proxy.setServiceInterface(BookService.class);
        proxy.setServiceUrl("rmi://localhost:1099/BookService");

        return proxy;
    }

    @Bean
    RmiProxyFactoryBean rmiProxyFactoryBean2()
    {
        RmiProxyFactoryBean proxy = new RmiProxyFactoryBean();
        proxy.setServiceInterface(ClientService.class);
        proxy.setServiceUrl("rmi://localhost:1099/ClientService");

        return proxy;
    }

    @Bean
    RmiProxyFactoryBean rmiProxyFactoryBean3()
    {
        RmiProxyFactoryBean proxy = new RmiProxyFactoryBean();
        proxy.setServiceInterface(RentalService.class);
        proxy.setServiceUrl("rmi://localhost:1099/RentalService");

        return proxy;
    }


}
