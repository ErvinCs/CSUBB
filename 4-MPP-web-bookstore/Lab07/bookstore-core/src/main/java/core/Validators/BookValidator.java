package core.Validators;

import core.Domain.Book;

public class BookValidator implements Validator<Book> {
    @Override
    public void validate(Book entity) throws ValidatorException {
        String errors="";
        if(entity.getID()==null)
            errors+="Invalid ID!\n";
        if (entity.getISBN()<=0)
            errors+="Invalid ISBN!\n";
        if(entity.getAuthor()=="")
            errors+="Invalid Author!\n";
        if(entity.getName()=="")
            errors+="Invalid Name!\n";
        if (errors.length()>0)
            throw new ValidatorException(errors);
    }
}
