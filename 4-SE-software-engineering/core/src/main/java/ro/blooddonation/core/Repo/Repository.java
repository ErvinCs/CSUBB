package ro.blooddonation.core.Repo;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;
import ro.blooddonation.core.Domain.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

@NoRepositoryBean
@Transactional
public interface Repository<T extends BaseEntity<ID>, ID extends Serializable>
        extends JpaRepository<T, ID> {
}
