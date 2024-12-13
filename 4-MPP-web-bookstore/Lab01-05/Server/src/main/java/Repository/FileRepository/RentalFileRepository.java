package Repository.FileRepository;

import Domain.Rental;
import Domain.Validators.Validator;
import Domain.Validators.ValidatorException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

public class RentalFileRepository extends FileRepository<Long, Rental>
{
    public RentalFileRepository(Validator<Rental> validator, String fileName)
    {
        super(validator, fileName);
    }

    @Override
    void load()
    {
        Path path = Paths.get(fileName);

        try {
            Files.lines(path).forEach(line -> {
                List<String> items = Arrays.asList(line.split(","));

                Long id = Long.valueOf(items.get(0));
                Long bookID = Long.valueOf(items.get(1));
                Long clientID = Long.valueOf(items.get((2)));

                Rental rental = new Rental(bookID, clientID);
                rental.setID(id);

                try {
                    super.save(rental);
                } catch (ValidatorException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    void saveToFile(Rental rental)
    {
        Path path = Paths.get(fileName);

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            bufferedWriter.write(
                    rental.getID() + "," + rental.getBookID() + "," + rental.getClientID());
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}