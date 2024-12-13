package bookstore.angular.Controller;

import bookstore.angular.Model.Rental;
import bookstore.angular.Repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class RentalController {

    @Autowired
    private RentalRepository rentalRepository;

    public String addRental(Long bookId, Long clientId)
    {
        Rental r = new Rental();
        r.setBookID(bookId);
        r.setClientID(clientId);
        rentalRepository.save(r);

        return "SAVED";
    }

    public String removeRental(Long id)
    {
        rentalRepository.deleteById(id);

        return "DELETED";
    }

    public Iterable<Rental> getAll()
    {
        //return JSON or XML with all books
        return rentalRepository.findAll();
    }
}
