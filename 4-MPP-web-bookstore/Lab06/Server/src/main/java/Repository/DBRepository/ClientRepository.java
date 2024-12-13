package Repository.DBRepository;

import Domain.Client;

import java.util.List;

public interface ClientRepository {
    List<Client> findAll();
    String add(String item);
    String update(String item);
    String delete(Long id);
}
