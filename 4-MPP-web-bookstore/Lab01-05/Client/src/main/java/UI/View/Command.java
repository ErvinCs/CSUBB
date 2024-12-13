package UI.View;

public abstract class Command {

    protected String key;
    protected String description;

    public Command()
    {
        key = null;
        description = null;
    }

    public Command(String key, String description)
    {
        this.key = key;
        this.description = description;
    }

    public String getKey() {return key;}

    public String getDescription() {return description;}

    public abstract void execute();

    @Override
    public String toString() {
        return key + ". " + description;
    }
}
