package Service;

import Domain.Rental;
import Repository.DBRepository.RentalRepository;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.rmi.RemoteException;
import java.util.List;


public class RentalServiceImpl implements RentalService
{
    @Override
    public List<Rental> findAll() throws RemoteException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("Config");
        RentalRepository rentalRepository = context.getBean(RentalRepository.class);

        return rentalRepository.findAll();
    }

    @Override
    public String add(String item) throws RemoteException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("Config");
        RentalRepository rentalRepository = context.getBean(RentalRepository.class);

        return rentalRepository.add(item);
    }

    @Override
    public String update(String item) throws RemoteException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("Config");
        RentalRepository rentalRepository = context.getBean(RentalRepository.class);

        return rentalRepository.update(item);
    }

    @Override
    public String delete(Long id) throws RemoteException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("Config");
        RentalRepository rentalRepository = context.getBean(RentalRepository.class);

        return rentalRepository.delete(id);
    }
}
