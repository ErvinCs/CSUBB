package Repository.FileRepository;

import Domain.Book;
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

public class BookFileRepository extends FileRepository<Long, Book>
{
    public BookFileRepository(Validator<Book> validator, String fileName)
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
                String author = items.get((2));
                int ISBN = Integer.parseInt(items.get(3));

                Book book = new Book(name, author, ISBN);
                book.setID(id);

                try {
                    super.save(book);
                } catch (ValidatorException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void saveToFile(Book book)
    {
        Path path = Paths.get(fileName);

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            bufferedWriter.write(
                    book.getID() + "," + book.getName() + "," + book.getAuthor() + "," + book.getISBN());
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
