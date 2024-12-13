package core.Domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class BaseEntity<ID extends Serializable>
{
    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false)
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
