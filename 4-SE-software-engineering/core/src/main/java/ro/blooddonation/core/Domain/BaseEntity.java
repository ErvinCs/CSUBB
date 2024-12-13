package ro.blooddonation.core.Domain;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public abstract class BaseEntity<ID extends Serializable> implements Serializable
{
    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false)
    private ID id;

    public BaseEntity() { }

    public BaseEntity(ID id)
    {
        this.id = id;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id)
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