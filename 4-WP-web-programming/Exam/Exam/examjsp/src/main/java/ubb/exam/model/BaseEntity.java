package ubb.exam.model;

public abstract class BaseEntity {
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        BaseEntity other = (BaseEntity)obj;
        return this.id.equals(other.getId());
    }

    @Override
    public String toString() {
        return "BaseEntity{id=" + id +
                "}";
    }
}
