package Domain;

import java.io.Serializable;

public abstract class BaseEntity<ID> implements Serializable
{
    private ID id;

    public ID getID() {
        return id;
    }

    public void setID(ID id)
    {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                '}';
    }
}
