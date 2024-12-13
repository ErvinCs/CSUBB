package Repository.FileRepository;

import Domain.Client;
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

public class ClientFileRepository extends FileRepository<Long, Client>
{
    public ClientFileRepository(Validator<Client> validator, String fileName)
    {
        super(validator, fileName);
    }

    @Override
    public void load()
    {
        Path path = Paths.get(fileName);

        try {
            Files.lines(path).forEach(line -> {
                List<String> items = Arrays.asList(line.split(","));

                Long id = Long.valueOf(items.get(0));
                String name = items.get(1);
                String country = items.get((2));

                Client client = new Client(name, country);
                client.setID(id);

                try {
                    super.save(client);
                } catch (ValidatorException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void saveToFile(Client client)
    {
        Path path = Paths.get(fileName);

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            bufferedWriter.write(
                    client.getID() + "," + client.getName() + "," + client.getCountry());
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}